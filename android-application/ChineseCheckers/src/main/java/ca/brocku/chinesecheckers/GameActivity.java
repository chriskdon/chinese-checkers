package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.gamestate.HumanPlayer;
import ca.brocku.chinesecheckers.gamestate.MovePath;
import ca.brocku.chinesecheckers.gamestate.OnlineHumanPlayer;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.network.SpicedGcmActivity;
import ca.brocku.chinesecheckers.network.spice.SpicedActivity;
import ca.brocku.chinesecheckers.uiengine.BoardUiEngine;

@SuppressLint("All")
public class GameActivity extends SpicedGcmActivity {
    private GameStateManager gameStateManager;  // Manages everything in the game
    private Boolean isGameOver; //a boolean which can prevent saving the state
    private boolean isOnlineGame; //flag whether or not this is an online game

    private Popup resumeDialog; //dialog that appears when there is a saved game


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game);

        gameStateManager = getIntent().getExtras().getParcelable("GAME_STATE_MANAGER");
        isGameOver = false;
        isOnlineGame = getIntent().getExtras().getBoolean("IS_ONLINE_GAME", false);

        //passes player array to the offline game fragment
        Fragment offlineGameFragment = new OfflineGameFragment(this, spiceManager);
        Bundle offlineGameFragmentBundle = new Bundle();
        offlineGameFragmentBundle.putParcelable("GAME_STATE_MANAGER", gameStateManager);
        offlineGameFragment.setArguments(offlineGameFragmentBundle);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, offlineGameFragment, "OfflineGameFragment")
                    .commit();
        }
        //Show the Resume Game dialog box if we are loading a saves game
        if (getIntent().getBooleanExtra("SAVED_GAME", false)) {

            resumeDialog = new Popup(this);
            resumeDialog.setTitleText(R.string.offline_resume_title)
                    .setMessageText(R.string.offline_resume_message)
                    .setAcceptButtonText(R.string.offline_resume_accept)
                    .setDeclineButtonText(R.string.offline_resume_decline)
                    .setAcceptClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resumeDialog.dismiss();

                            ((OfflineGameFragment) getFragmentManager().findFragmentByTag("OfflineGameFragment")).startGame(); //start the saved game after the resume game dialog is closed
                        }
                    })
                    .setCancelClickListener(new Button.OnClickListener() {
                        //Handler to quit the current game and go to the config screen if quit is chosen
                        @Override
                        public void onClick(View view) {
                            isGameOver = true; //set to not save the state (as we are quitting the current game)

                            //Delete the current saved game
                            File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME);
                            savedOfflineGame.delete();

                            resumeDialog.dismiss();
                            GameActivity.this.finish();
                            startActivity(new Intent(GameActivity.this, OfflineConfigurationActivity.class));
                        }
                    })
                    .disableBackButton(true)
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            startActivity(new Intent(GameActivity.this, HelpActivity.class));
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(GameActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        gameStateManager.stopGame();

//        mMediaPlayer.pause();

        super.onPause();

        if (!isOnlineGame && !isGameOver) { //don't save if game is over or if it's an online game

            try { //try to serialize the GameStateManager

                //Prepare to write out the GameStateManager
                FileOutputStream fos = openFileOutput(GameStateManager.SERIALIZED_FILENAME, Activity.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                //Get the GameStateManager from the OfflineGameFragment
                GameStateManager gameStateManager = ((OfflineGameFragment) getFragmentManager()
                        .findFragmentByTag("OfflineGameFragment"))
                        .getGameStateManager();

                //Write out the GameStateManager
                oos.writeObject(gameStateManager);
                oos.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        BoomBoomMusic.start(this);
    }

    /**
     * This function handles the end of game state.
     * <p/>
     * It disables saving, deletes the currently saved game, and shows the end of game dialog.
     *
     * @param p the player who won
     * @return whether or not this was handled
     */
    public Boolean onEndGame(Player p) {
        isGameOver = true; //prevent saving this (finished) game

        //Delete the current saved game
        File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME);
        savedOfflineGame.delete();

        //Plays game over sound depending on if device owner won
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPrefs.getString(MainActivity.PREF_USERNAME, "").equals(p.getName())){
            BoomBoomMusic.onPlayerWin();
        }
        else{
            BoomBoomMusic.onPlayerLose();
        }

        //Show the end of game dialog
        final Popup endGameDialog = new Popup(this);
        endGameDialog.setTitleText(p.getName() + " Wins!!")
                .setMessageText(R.string.offline_end_game_message)
                .setAcceptButtonText(R.string.offline_end_game_accept)
                .setDeclineButtonText(R.string.offline_end_game_decline)
                .setAcceptClickListener(new Button.OnClickListener() {
                    //Handler to close the dialog if option to play new game is chosen
                    @Override
                    public void onClick(View view) {
                        endGameDialog.dismiss();
                        BoomBoomMusic.stopSP(getApplicationContext());
                        GameActivity.this.finish();
                        startActivity(new Intent(GameActivity.this, OfflineConfigurationActivity.class));
                    }
                })
                .setCancelClickListener(new Button.OnClickListener() {
                    //Handler for "Home" button to quit the current game and go to the main activity
                    @Override
                    public void onClick(View view) {
                        endGameDialog.dismiss();
                        BoomBoomMusic.stopSP(getApplicationContext());
                        GameActivity.this.finish();
                        startActivity(new Intent(GameActivity.this, MainActivity.class));
                    }
                })
                .show();

        //disable the Reset and Done buttons
        ((OfflineGameFragment) getFragmentManager()
                .findFragmentByTag("OfflineGameFragment")).disableButtons();

        return true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Fragment containing game board, controls, and player turn indicator
     */
    @SuppressLint("ValidFragment")
    public static class OfflineGameFragment extends Fragment {
        private GameStateManager gameStateManager;
        private BoardUiEngine boardUiEngine;
        private Boolean isShowMoves; //preference whether or not to show available moves
        private Button resetMove;
        private Button doneMove;
        private View rootView;
        private Button titleBarButton;

        private Player currentPlayer;

        // Things for a human players turn
        private MovePath movePath = new MovePath();
        private Piece currentPiece;
        private GameBoard board = null;
        private Position[] possibleMoves;

        public GameActivity activity;
        private SpiceManager spiceManager;


        public OfflineGameFragment() {
        }

        public OfflineGameFragment(GameActivity activity, SpiceManager spiceManager) {
            this.activity = activity;
            this.spiceManager = spiceManager;
        }

        /**
         * Get a modifiable version of the current game board.
         *
         * @return
         */
        private GameBoard getModifiableBoard() {
            if (board == null) {
                board = gameStateManager.getGameBoard().getDeepCopy();
            }

            return board;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_offline_game, container, false);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();

            gameStateManager = getActivity().getIntent().getExtras().getParcelable("GAME_STATE_MANAGER");

            gameStateManager.setGameStateEventsHandler(new GameStateEventsHandler());

            // Setup Game Board
            this.boardUiEngine = (BoardUiEngine) rootView.findViewById(R.id.gameBoardSurface);

            boardUiEngine.setBoardEventsHandler(new BoardEventsHandler());
            boardUiEngine.initializeBoard(gameStateManager.getGameBoard());

            //Get preference for showing possible moves
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            isShowMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);

            // Bind Controls
            titleBarButton = (Button) rootView.findViewById(R.id.gamePlayerListButton);
            resetMove = (Button) rootView.findViewById(R.id.gameMoveResetButton);
            doneMove = (Button) rootView.findViewById(R.id.gameMoveDoneButton);

            // Bind Handlers
            titleBarButton.setOnClickListener(new TitleBarButtonHandler());
            resetMove.setOnClickListener(new ResetMoveHandler());
            doneMove.setOnClickListener(new DoneMoveHandler());

            setTitleBar(gameStateManager.getCurrentPlayer());

            //start the game if the resume dialog is not showing
            if (activity.resumeDialog == null || !activity.resumeDialog.isShowing()) {
                gameStateManager.startGame(activity);
            }

        }

        @Override
        public void onPause() {
            gameStateManager.stopGame();
            super.onPause();
            BoomBoomMusic.pause();
        }

        /**
         * Is the current player who's turn it is a Human.
         *
         * @return True if the turn is for a human.
         */
        private boolean isHumanTurn() {
            return (currentPlayer != null && currentPlayer instanceof HumanPlayer);
        }

        /**
         * This is a getter method for the fragment's GameStateManager
         *
         * @return the GameStateManager
         */
        public GameStateManager getGameStateManager() {
            return gameStateManager;
        }

        /**
         * Set the color and name for the title bar based on the player.
         *
         * @param player The The player who's turn it is.
         */
        private void setTitleBar(Player player) {
            titleBarButton.setText(player.getName());

            switch (player.getPlayerColor()) {
                case RED:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_red);
                    return;
                case PURPLE:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_purple);
                    return;
                case BLUE:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_blue);
                    return;
                case GREEN:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_green);
                    return;
                case YELLOW:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_yellow);
                    return;
                case ORANGE:
                    titleBarButton.setBackgroundResource(R.drawable.chch_button_square_orange);
                    return;
            }

            throw new IllegalArgumentException("A valid player must be specified.");
        }

        public void disableButtons() {
            doneMove.setClickable(false);
            resetMove.setClickable(false);
        }

        /**
         * Handle the user clicking the title bar.
         */
        private class TitleBarButtonHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                gameStateManager.stopGame();

                Popup playerListDialog = new Popup(getActivity());
                playerListDialog
                        .setTitleText("Players")
                        .enablePlayerList(gameStateManager.getPlayers())
                        .hideButtons(true)
                        .setDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                if (!activity.isGameOver()) {
                                    gameStateManager.startGame(activity);
                                }
                            }
                        })
                        .show();
            }
        }

        /**
         * Handler the user clicking the reset button.
         */
        private class ResetMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (isHumanTurn()) { // It must be a humans turn
                    resetHumanState();
                    boardUiEngine.drawBoard(gameStateManager.getGameBoard());
                    boardUiEngine.showHintPositions(null);
                    boardUiEngine.highlightPiece(null);
                }
            }
        }

        /**
         * Handle the user clicking the done button.
         */
        private class DoneMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (isHumanTurn()) { // Not a humans turn
                    if (movePath.size() < 2) {
                        // TODO: REPLACE THIS WITH A REAL MESSAGE
                        Toast.makeText(getActivity(), "Make a move.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (currentPlayer instanceof OnlineHumanPlayer) {
                        ((OnlineHumanPlayer) currentPlayer)
                                .signalMove(spiceManager, movePath);
                    } else if(currentPlayer instanceof HumanPlayer) {
                        ((HumanPlayer) currentPlayer)
                                .signalMove(movePath);
                    } else {
                        throw new RuntimeException("Invalid Player");
                    }

                    if (movePath.getPath().size() >= 9) {
                        checkKonamiCode(currentPlayer, movePath);
                    }

                    resetHumanState();
                }
            }
        }

        /*
        * checkKonamiCode
        * Sees if the player's name and path adds up to the Konami Code
        */
        private void checkKonamiCode(Player p, MovePath m) {
//        Log.e("Holla", "Entered");
            if ((m.getPosition(0).getRow() > m.getPosition(1).getRow())) { // && //Up
//            Log.e("Holla","Up");
                if (m.getPosition(1).getRow() > m.getPosition(2).getRow()) { // && //Up
//                Log.e("Holla","Up");
                    if (m.getPosition(2).getRow() < m.getPosition(3).getRow()) { //&& //Down
//                    Log.e("Holla","Down");
                        if (m.getPosition(3).getRow() < m.getPosition(4).getRow()) { //&& //Down
//                        Log.e("Holla","Down");
                            if (m.getPosition(4).getIndex() > m.getPosition(5).getIndex()) { // && //Left
//                            Log.e("Holla","Left");
                                if (m.getPosition(5).getIndex() < m.getPosition(6).getIndex()) { // && //Right
//                                Log.e("Holla","Right");
                                    if (m.getPosition(6).getIndex() > m.getPosition(7).getIndex()) { // && //Left
//                                    Log.e("Holla","Left");
                                        if (m.getPosition(7).getIndex() < m.getPosition(8).getIndex()) { // && //Right
//                                        Log.e("Holla","Right");
                                            if (p.getName().contains("ba")) {//){
//                                              Log.e("Holla", "Winn");
                                                BoomBoomMusic.konamiCodeEntered(getActivity().getApplicationContext());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /**
         * Reset the objects related to the human move state
         */
        private void resetHumanState() {
            currentPiece = null;
            board = null;
            possibleMoves = null;
            movePath = new MovePath();
        }

        /**
         * Set who the current player is.
         *
         * @param p The player to set as current.
         */
        private void setCurrentPlayer(Player p) {
            currentPlayer = p;
            if (isHumanTurn()) {
                resetHumanState();
            }
        }

        private void startGame() {
            gameStateManager.startGame(activity);
        }

        /**
         * Handle the position be touched, and what to display when the user touches it.
         */
        private class BoardEventsHandler implements BoardUiEngine.BoardUiEventsHandler {
            @Override
            public void positionTouched(final Position position) {
                if (!activity.isGameOver()) { //disable board events if game is over
                    if (isHumanTurn()) {
                        GameBoard tempBoard = getModifiableBoard();
                        Piece piece = tempBoard.getPiece(position);

                        if (piece == null && possibleMoves != null && possibleMoves.length > 0) { // Moving to hint
                            for (Position p : possibleMoves) {
                                if (p != null && position.equals(p)) { // User Clicked a valid piece
                                    BoomBoomMusic.onPieceTap();
                                    tempBoard.movePiece(currentPiece, p);
                                    currentPiece = tempBoard.getPiece(p);
                                    possibleMoves = tempBoard.getPossibleHops(currentPiece);
                                    movePath.addToPath(p);
                                }
                            }
                        } else if (piece != null && movePath.size() <= 1 && piece.getPlayerNumber() == currentPlayer.getPlayerNumber()) { // Selecting first valid piece
                            resetHumanState();
                            BoomBoomMusic.onPieceTap();
                            currentPiece = piece;
                            possibleMoves = tempBoard.getPossibleMoves(currentPiece);
                            movePath = new MovePath(new Position(piece.getPosition().getRow(), piece.getPosition().getIndex()));
                        } else if (movePath.size() <= 1) {
                            resetHumanState();
                        }

                        // Update Drawing
                        boardUiEngine.drawBoard(new ReadOnlyGameBoard(tempBoard));
                        boardUiEngine.highlightPiece(currentPiece);
                        if (isShowMoves) { //show moves if enabled in preferences
                            boardUiEngine.showHintPositions(possibleMoves);
                        }
                    }
                }
            }
        }

        /**
         * Handle game state changes.
         */
        private class GameStateEventsHandler implements GameStateManager.GameStateEvents {
            /**
             * Fired when it is a new players turn.
             *
             * @param player The player who's turn it is.
             */
            @Override
            public synchronized void onPlayerTurn(Player player) {
                // Rotate the board an amount depending on num players
                // Rotation needs to be calculated when it is a new players turn
                // there is a special case for the very first turn
                /*
                int degreesToRotate = 0;
                switch (gameStateManager.getNumberOfPlayers()) {
                    case 2:
                        degreesToRotate = -180;
                        break;
                    case 3:
                        degreesToRotate = -120;
                        break;
                    case 4:
                        switch (gameStateManager.getCurrentPlayer().getPlayerColor()) {
                            case RED:
                            case GREEN:
                                degreesToRotate = -120;
                                break;
                            case BLUE:
                            case ORANGE:
                                degreesToRotate = -60;
                                break;
                        }
                        break;
                    case 6:
                        degreesToRotate = -60;
                        break;
                }
                */

                setCurrentPlayer(player);
                setTitleBar(player);
            }

            /**
             * Fired when a piece on the board is moved from one position to another.
             *
             * @param player        The player who modified the board
             * @param originalBoard The original copied state of the board.
             * @param currentBoard  The current game board.
             * @param movePath      The path describing the movePath.
             */
            @Override
            public synchronized void onBoardModified(Player player, GameBoard originalBoard, ReadOnlyGameBoard currentBoard, MovePath movePath) {
                boardUiEngine.drawBoard(currentBoard);
                boardUiEngine.showHintPositions(null);
                boardUiEngine.highlightPiece(null);
            }

            /**
             * Occurs when a player forfeit the game.
             * <p/>
             * TODO: Is this just because they quit, or could this be a result of them taking to long to make a movePath and being kicked out.
             *
             * @param player The player who forfeited.
             */
            @Override
            public synchronized void onForfeit(Player player) {

            }

            /**
             * Occurs when a player wins the game.
             *
             * @param player   The player that won.
             * @param position The position they finished in (1st, 2nd, 3rd, etc.).
             */
            @Override
            public synchronized void onPlayerWon(Player player, int position) {
                setTitleBar(player);
                ((GameActivity) getActivity()).onEndGame(player);
            }
        }
    }
}
