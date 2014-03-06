package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.gamestate.Move;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.BoardUiEngine;

import static ca.brocku.chinesecheckers.uiengine.PlayerColorManager.ColorSate;
import static ca.brocku.chinesecheckers.uiengine.PlayerColorManager.getPlayerColor;

@SuppressLint("All")
public class OfflineGameActivity extends Activity {
    private GameStateManager gameStateManager;  // Manages everything in the game
    private Boolean isEndCurrentGame; //a boolean which can prevent saving the state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game);

        gameStateManager = getIntent().getExtras().getParcelable("GAME_STATE_MANAGER");
        isEndCurrentGame = false;

        //passes player array to the offline game fragment
        Fragment offlineGameFragment = new OfflineGameFragment();
        Bundle offlineGameFragmentBundle = new Bundle();
        offlineGameFragmentBundle.putParcelable("GAME_STATE_MANAGER", gameStateManager);
        offlineGameFragment.setArguments(offlineGameFragmentBundle);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, offlineGameFragment, "OfflineGameFragment")
                    .commit();
        }

        //Show the Resume Game dialog box if we are loading a saves game
        if(getIntent().getBooleanExtra("SAVED_GAME", false)) {

            final Popup resumeDialog = new Popup(this);
            resumeDialog.setTitleText(R.string.offline_resume_title)
                    .setMessageText(R.string.offline_resume_message)
                    .setAcceptButtonText(R.string.offline_resume_accept)
                    .setDeclineButtonText(R.string.offline_resume_decline)
                    .setAcceptClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resumeDialog.dismiss();
                        }
                    })
                    .setCancelClickListener(new Button.OnClickListener() {
                        //Handler to quit the current game and go to the config screen if quit is chosen
                        @Override
                        public void onClick(View view) {
                            isEndCurrentGame = true; //set to not save the state (as we are quitting the current game)

                            //Delete the current saved game
                            File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME);
                            savedOfflineGame.delete();

                            resumeDialog.dismiss();
                            OfflineGameActivity.this.finish();
                            startActivity(new Intent(OfflineGameActivity.this, OfflineConfigurationActivity.class));
                        }
                    })
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
        if(id == R.id.action_help) {
            startActivity(new Intent(OfflineGameActivity.this, HelpActivity.class));
        } else if(id == R.id.action_settings) {
            startActivity(new Intent(OfflineGameActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(!isEndCurrentGame) { //only save the state if Quit Game was not selected form dialog

            try { //try to serialize the GameStateManager

                //Prepare to write out the GameStateManager
                FileOutputStream fos = openFileOutput(GameStateManager.SERIALIZED_FILENAME, Activity.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                //Get the GameStateManager from the OfflineGameFragment
                GameStateManager gameStateManager = ((OfflineGameFragment)getFragmentManager()
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

    /** This function handles the end of game state.
     *
     * It disables saving, deletes the currently saved game, and shows the end of game dialog.
     *
     * @param p the player who won
     * @return whether or not this was handled
     */
    public Boolean onEndGame(Player p) {
        isEndCurrentGame = true; //prevent saving this (finished) game

        //Delete the current saved game
        File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME);
        savedOfflineGame.delete();

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
                        OfflineGameActivity.this.finish();
                        startActivity(new Intent(OfflineGameActivity.this, OfflineConfigurationActivity.class));
                    }
                })
                .setCancelClickListener(new Button.OnClickListener() {
                    //Handler for "Home" button to quit the current game and go to the main activity
                    @Override
                    public void onClick(View view) {
                        endGameDialog.dismiss();
                        OfflineGameActivity.this.finish();
                        startActivity(new Intent(OfflineGameActivity.this, MainActivity.class));
                    }
                })
                .disableBackButton(true)
                .show();

        return true;
    }

    /**
     *  Fragment containing game board, controls, and player turn indicator
     */
    @SuppressLint("ValidFragment")
    private static class OfflineGameFragment extends Fragment {
        private GameStateManager gameStateManager;
        private BoardUiEngine boardUiEngine;
        private Button resetMove;
        private Button doneMove;
        private View rootView;
        private Button titleBarButton;
        private boolean moved = false;
        private Position[] hints;
        private Piece currentTouched;

        private Position start;

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

            // Bind Controls
            titleBarButton = (Button)rootView.findViewById(R.id.gamePlayerListButton);
            resetMove = (Button)rootView.findViewById(R.id.gameMoveResetButton);
            doneMove = (Button)rootView.findViewById(R.id.gameMoveDoneButton);

            // Bind Handlers
            titleBarButton.setOnClickListener(new TitleBarButtonHandler());
            resetMove.setOnClickListener(new ResetMoveHandler());
            doneMove.setOnClickListener(new DoneMoveHandler());

            gameStateManager.startGame();

        }

        private class TitleBarButtonHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                Popup playerListDialog = new Popup(getActivity());
                playerListDialog.setTitleText("Temp Dialog")
                        .setMessageText("This will have a list of players. There won't be a title or buttons")
                        .show();
            }
        }

        private class ResetMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if(start != null) {
                    gameStateManager.resetPiece(currentTouched, start);
                    boardUiEngine.drawBoard(gameStateManager.getGameBoard());
                }

                currentTouched = null;
                start = null;
                moved = false;

                boardUiEngine.showHintPositions(null);
                boardUiEngine.highlightPiece(null);
            }
        }

        private class DoneMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                moved = false;
                currentTouched = null;
                hints = null;

                boardUiEngine.showHintPositions(null);
                boardUiEngine.highlightPiece(null);

                //rotate the board an amount depending on num players
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

                //boardUiEngine.rotateBoard(degreesToRotate, null);
                gameStateManager.nextPlayer();
            }
        }

        /**
         * Handle the position be touched, and what to display when the user touches it.
         */
        private class BoardEventsHandler implements BoardUiEngine.BoardUiEventsHandler {


            @Override
            public void positionTouched(final Position position) {
                GameBoard board = gameStateManager.getGameBoard();
                Piece piece = board.getPiece(position);

                // Did they touch their own piece.
                if(!moved) {
                    if(piece != null && Player.getPlayerColor(piece.getPlayerNumber()) == gameStateManager.getCurrentPlayer().getPlayerColor()) {
                        if(currentTouched == null || !currentTouched.getPosition().equals(position)) {
                            currentTouched = piece;
                            hints = board.getPossibleMoves(piece);
                            start = piece.getPosition();
                        }
                    } else if(piece == null) {
                        // Did the player click a hint
                        if(hints != null) {
                            for(Position h : hints) {
                                if(h.equals(position)) {
                                    gameStateManager.movePiece(currentTouched, position);       // Move board
                                    boardUiEngine.drawBoard(gameStateManager.getGameBoard());
                                    moved = true;

                                    Position[] hops = gameStateManager.getGameBoard().getPossibleHops(currentTouched);
                                    if(hops != null) {
                                        hints = hops;
                                        boardUiEngine.showHintPositions(hops);
                                        moved = false;
                                    } else {
                                        hints = null;
                                    }

                                    break;
                                }
                            }
                        }
                    }
                }

                boardUiEngine.highlightPiece(currentTouched);
                boardUiEngine.showHintPositions(hints);
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
            public void onPlayerTurn(Player player) {
                setTitleBarButtonColor(player);
                //titleBarButton.setBackgroundColor(getPlayerColor(getResources(), player.getPlayerColor(), ColorSate.NORMAL));
                titleBarButton.setText(gameStateManager.getCurrentPlayer().getName());
            }

            /**
             * Fired when a piece on the board is moved from one position to another.
             *
             * @param player The player who made the move.
             * @param move   The path describing the move.
             */
            @Override
            public void onPieceMoved(Player player, Move move) {

            }

            /**
             * Occurs when a player forfeit the game.
             * <p/>
             * TODO: Is this just because they quit, or could this be a result of them taking to long to make a move and being kicked out.
             *
             * @param player The player who forfeited.
             */
            @Override
            public void onForfeit(Player player) {

            }

            /**
             * Occurs when a player wins the game.
             *
             * @param player   The player that won.
             * @param position The position they finished in (1st, 2nd, 3rd, etc.).
             */
            @Override
            public void onPlayerWon(Player player, int position) {
                ((OfflineGameActivity)getActivity()).onEndGame(player);
            }
        }

        /** This is a getter method for the fragment's GameStateManager
         *
         * @return the GameStateManager
         */
        public GameStateManager getGameStateManager() {
            return gameStateManager;
        }

        /** This is a setter method for the GameStateManager
         *
         */
        public void setGameStateManager(GameStateManager gameStateManager) {
            this.gameStateManager = gameStateManager;
        }

        /** This is a getter method for the fragment's BoardUiEngine
         *
         * @return  the UI Engine
         */
        public void setBoardUiEngine(BoardUiEngine boardUiEngine) {
            this.boardUiEngine = boardUiEngine;
        }

        private void setTitleBarButtonColor(Player player) {
            switch (player.getPlayerColor()) {
                    case RED:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_red);
                        break;
                    case PURPLE:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_purple);
                        break;
                    case BLUE:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_blue);
                        break;
                    case GREEN:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_green);
                        break;
                    case YELLOW:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_yellow);
                        break;
                    case ORANGE:
                        titleBarButton.setBackgroundResource(R.drawable.chch_button_square_orange);
                        break;
                }
        }

    }

}
