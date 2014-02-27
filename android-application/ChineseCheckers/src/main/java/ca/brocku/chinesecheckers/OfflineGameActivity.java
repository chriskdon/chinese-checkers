package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.uiengine.BoardUiEngine;

public class OfflineGameActivity extends Activity {
    private String[] players; //an array of the players' names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game);

        // Make sure variables are setup before creating fragment
        players = getIntent().getExtras().getStringArray("PLAYER_NAMES");

        //passes player array to the offline game fragment
        Fragment offlineGameFragment = new OfflineGameFragment();
        Bundle offlineGameFragmentBundle = new Bundle();
        offlineGameFragmentBundle.putStringArray("PLAYER_NAMES", players);
        offlineGameFragment.setArguments(offlineGameFragmentBundle);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, offlineGameFragment)
                    .commit();
        }

        if(false) { //TODO change to: if there is a saved game using passed info from main or something local
            ResumeDialog dialog = new ResumeDialog(this, R.style.CustomDialogTheme);
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_help) {
            startActivity(new Intent(OfflineGameActivity.this, HelpActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public Boolean onEndGame() {
        EndOfGameDialog dialog = new EndOfGameDialog(this, R.style.CustomDialogTheme);
        dialog.setWinner("Kuba");
        dialog.show();

        return true;
    }

    /**
     *  Fragment containing game board, controls, and player turn indicator
     */
    private static class OfflineGameFragment extends Fragment {
        private BoardUiEngine boardUiEngine;
        private TextView currentPlayerName;
        private Button resetMove;
        private Button doneMove;
        private String[] playerNames;
        private GameStateManager gameStateManager;  // Manages everything in the game

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_offline_game, container, false);

            playerNames = getArguments().getStringArray("PLAYER_NAMES");
            gameStateManager = getActivity().getIntent().getExtras().getParcelable("GAME_STATE_MANAGER");

            // Setup Game Board
            this.boardUiEngine = (BoardUiEngine) rootView.findViewById(R.id.gameBoardSurface);

            boardUiEngine.setBoardEventsHandler(new BoardEventsHandler());
            boardUiEngine.initializeBoard(gameStateManager.getGameBoard().getAllPieces());

            // Bind Controls
            currentPlayerName = (TextView)rootView.findViewById(R.id.offlineCurrentPlayerTextView);
            resetMove = (Button)rootView.findViewById(R.id.offlineMoveResetButton);
            doneMove = (Button)rootView.findViewById(R.id.offlineMoveDoneButton);

            // Bind Handlers
            resetMove.setOnClickListener(new ResetMoveHandler());
            doneMove.setOnClickListener(new DoneMoveHandler());

            currentPlayerName.setText(playerNames[0]);

            return rootView;
        }

        private class ResetMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {

            }
        }

        private class DoneMoveHandler implements View.OnClickListener {
            @Override
            public void onClick(View view) {

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
                Position[] possibleMoves = null;

                if(piece != null) {
                    possibleMoves = board.getPossibleMoves(piece);
                }

                boardUiEngine.highlightPiece(piece);
                boardUiEngine.showHintPositions(possibleMoves);
            }
        }
    }

    /**
     * This class represents a custom dialog box which appears when there is a saved game. It
     * prompts the user to decide whether to resume the game or quit it.
     */
    private class ResumeDialog extends Dialog {
        public ResumeDialog(final Context context, int theme) {
            super(context, theme);

            setContentView(R.layout.fragment_offline_game_resume_dialog); //dialog layout

            //Bind controls for the dialog options
            Button resumeButton = (Button)findViewById(R.id.offlineAcceptContinuationButton);
            Button quitButton = (Button)findViewById(R.id.offlineDeclineContinuationButton);

            //Handler to close the dialog if option to resume is chosen
            resumeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

            //Handler to quit the current game and go to the config screen if quit is chosen
            quitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                    startActivity(new Intent(context, OfflineConfigurationActivity.class));
                }
            });
        }
    }

    /**
     * This class represents a custom dialog box which appears when someone wins. It prompts the
     * user to decide whether to play a new game or go to the home screen.
     */
    private class EndOfGameDialog extends Dialog {
        private TextView title; //the title of the Dialog (i.e. "<WINNER_NAME> Wins!")

        public EndOfGameDialog(final Context context, int theme) {
            super(context, theme);

            setContentView(R.layout.fragment_offline_game_end_dialog); //dialog layout

            //Bind controls for the dialog options and title
            Button newGameButton = (Button)findViewById(R.id.offlineGameEndToNewButton);
            Button homeButton = (Button)findViewById(R.id.offlineGameEndToHomeButton);
            title = (TextView)findViewById(R.id.offlineGameEndTitle);

            //Handler to close the dialog if option to play new game is chosen
            newGameButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((Activity)context).finish();
                    startActivity(new Intent(context, OfflineConfigurationActivity.class));
                }
            });

            //Handler for "Home" button to quit the current game and go to the main activity
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                    startActivity(new Intent(context, MainActivity.class));
                }
            });
        }

        public void setWinner(String winner) {
            title.setText(winner + " Wins!!");
        }
    }
}
