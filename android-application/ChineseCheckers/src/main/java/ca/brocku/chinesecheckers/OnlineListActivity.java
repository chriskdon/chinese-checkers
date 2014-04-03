package ca.brocku.chinesecheckers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ccapi.GameListItem;
import com.ccapi.PieceInformation;
import com.ccapi.PlayerInformation;
import com.ccapi.receivables.GameListReceivable;
import com.ccapi.receivables.GameOverNotificationReceivable;
import com.ccapi.receivables.GameReadyNotificationReceivable;
import com.ccapi.receivables.GameStateReceivable;
import com.ccapi.receivables.JoinGameReceivable;
import com.ccapi.receivables.SuccessReceivable;
import com.octo.android.robospice.persistence.exception.SpiceException;

import java.util.ArrayList;
import java.util.LinkedList;

import ca.brocku.chinesecheckers.gameboard.CcGameBoard;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.GridPiece;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.gamestate.NetworkPlayer;
import ca.brocku.chinesecheckers.gamestate.OnlineHumanPlayer;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.network.SpicedGcmActivity;
import ca.brocku.chinesecheckers.network.spice.ApiRequestListener;
import ca.brocku.chinesecheckers.network.spice.requests.DeleteGameRequest;
import ca.brocku.chinesecheckers.network.spice.requests.GameListRequest;
import ca.brocku.chinesecheckers.network.spice.requests.GameStateRequest;
import ca.brocku.chinesecheckers.network.spice.requests.JoinGameRequest;
import de.greenrobot.event.EventBus;

/**
 * Created by kubasub on 2014-03-06.
 */
public class OnlineListActivity extends SpicedGcmActivity {
    private LinearLayout gameListContainer;
    private Button newGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_list);

        //Bind Controls
        newGameButton = (Button)findViewById(R.id.onlineNewGameButton);
        gameListContainer = (LinearLayout)findViewById(R.id.onlineGameListContainer);

        //Bind Handlers
        newGameButton.setOnClickListener(new NewGameHandler());
    }


    @Override
    protected void onResume() {
        super.onResume();
        BoomBoomMusic.start(this);
        
        //Populates the list of online games
        populateList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BoomBoomMusic.pause();
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
            startActivity(new Intent(OnlineListActivity.this, HelpActivity.class));
        } else if(id == R.id.action_settings) {
            startActivity(new Intent(OnlineListActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /** Gets and populates the list of current games.
     *
     *  Makes a server call to get the list of games. The list items are parsed into individual
     *  views which are then added to the ViewManager.
     *
     */
    private void populateList() {
        GameListRequest gameListRequest = new GameListRequest(super.userId);
        spiceManager.execute(gameListRequest, new ApiRequestListener<GameListReceivable>() {
            @Override
            public void onTaskFailure(int code, String message) {
                Toast.makeText(OnlineListActivity.this, "Failed to load list of games.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTaskSuccess(GameListReceivable result) {
                if(result.gameListItems.length == 0) { //Opens the new game dialog if there are no games
                    newGameButton.performClick();

                } else {
                    gameListContainer.removeAllViews();
                    for(GameListItem game : result.gameListItems) { //for each game received
                        View listItem = createListItemView(game);
                        gameListContainer.addView(listItem);
                    }
                }
            }

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(OnlineListActivity.this, "Failed to load list of games.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /** Sets all of the elements of a game list item View.
     *
     * @param gameListItem The object containing the data for the view
     * @return The list item view
     */
    private View createListItemView(GameListItem gameListItem) {
        //Inflate the game view
        View newGame = getLayoutInflater().inflate(R.layout.fragment_online_list_item, null);

        if (newGame != null) {

            //Set the margin at the bottom of the list item (in dp)
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, (float) 18, getResources().getDisplayMetrics());
            newGame.setLayoutParams(layoutParams);

            //Set click listeners
            newGame.setOnClickListener(new startOnlineGameHandler());
            newGame.setOnLongClickListener(new DeleteGameHandler());

            newGame.setTag(gameListItem.gameId);

            //Set Game ID
            ((TextView) newGame.findViewById(R.id.onlineGameIdTextView)).setText("#"+Long.toString(gameListItem.gameId));

            if(!gameListItem.isReady) {
                //Set all of the icons to white if the game is not ready yet
                ((ImageView)newGame.findViewById(R.id.onlineListItemRedIcon)).setImageResource(R.drawable.ic_player_peg_white);
                ((ImageView)newGame.findViewById(R.id.onlineListItemOrangeIcon)).setImageResource(R.drawable.ic_player_peg_white);
                ((ImageView)newGame.findViewById(R.id.onlineListItemYellowIcon)).setImageResource(R.drawable.ic_player_peg_white);
                ((ImageView)newGame.findViewById(R.id.onlineListItemGreenIcon)).setImageResource(R.drawable.ic_player_peg_white);
                ((ImageView)newGame.findViewById(R.id.onlineListItemBlueIcon)).setImageResource(R.drawable.ic_player_peg_white);
                ((ImageView)newGame.findViewById(R.id.onlineListItemPurpleIcon)).setImageResource(R.drawable.ic_player_peg_white);

                //Set the text view under the player icons
                newGame.findViewById(R.id.onlineWinnerContainer).setVisibility(View.VISIBLE);
                ((TextView) newGame.findViewById(R.id.onlineWinnerTextView)).setText(R.string.waiting_for_players);
            }

            //Set Player icons
            switch (gameListItem.numberOfPlayers) {
                case 2:
                    newGame.findViewById(R.id.onlineListItemRedIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemGreenIcon).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    newGame.findViewById(R.id.onlineListItemRedIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemYellowIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemBlueIcon).setVisibility(View.VISIBLE);
                    break;
                case 4:
                    newGame.findViewById(R.id.onlineListItemRedIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemOrangeIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemGreenIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemBlueIcon).setVisibility(View.VISIBLE);
                    break;
                case 6:
                    newGame.findViewById(R.id.onlineListItemRedIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemOrangeIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemYellowIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemGreenIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemBlueIcon).setVisibility(View.VISIBLE);
                    newGame.findViewById(R.id.onlineListItemPurpleIcon).setVisibility(View.VISIBLE);
                    break;
            }

            if(gameListItem.isReady) {
                //Sets User's icon (with star)
                switch (Player.getPlayerColor(gameListItem.playerNumber)) {
                    case RED:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemRedIcon)).setImageResource(R.drawable.ic_player_peg_star_red);
                        break;
                    case PURPLE:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemPurpleIcon)).setImageResource(R.drawable.ic_player_peg_star_purple);
                        break;
                    case BLUE:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemBlueIcon)).setImageResource(R.drawable.ic_player_peg_star_blue);
                        break;
                    case GREEN:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemGreenIcon)).setImageResource(R.drawable.ic_player_peg_star_green);
                        break;
                    case YELLOW:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemYellowIcon)).setImageResource(R.drawable.ic_player_peg_star_yellow);
                        break;
                    case ORANGE:
                        ((ImageView) newGame.findViewById(R.id.onlineListItemOrangeIcon)).setImageResource(R.drawable.ic_player_peg_star_orange);
                        break;
                }

                //Set notification icon if this user's turn
                if (gameListItem.isPlayerTurn()) {
                    newGame.findViewById(R.id.onlineListItemNotificationIcon).setVisibility(View.VISIBLE);
                }
            }

            //Set winner section (if game has a winner)
            if (gameListItem.isWinner()) {
                ((TextView) newGame.findViewById(R.id.onlineWinnerTextView)).setText(gameListItem.winnerUsername);

                //Sets the trophy colour
                switch (Player.getPlayerColor(gameListItem.winnerNumber)) {
                    case RED:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_red);
                        break;
                    case PURPLE:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_purple);
                        break;
                    case BLUE:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_blue);
                        break;
                    case GREEN:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_green);
                        break;
                    case YELLOW:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_yellow);
                        break;
                    case ORANGE:
                        ((ImageView) newGame.findViewById(R.id.onlineWinnerIcon)).setImageResource(R.drawable.ic_player_trophy_orange);
                        break;
                }

                newGame.findViewById(R.id.onlineWinnerContainer).setVisibility(View.VISIBLE);
            }

        }
        return newGame;
    }

    /**
     * Update the board if a game has become ready while looking at it.
     *
     * @param event
     */
    public void onEvent(GameReadyNotificationReceivable event) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populateList();
            }
        });
    }

    /**
     * A game has ended.
     *
     * @param event
     */
    public void onEvent(GameOverNotificationReceivable event) {
        // TODO: Game Over Do something
    }

    /** This handler starts one of the online games.
     *
     * It makes a call to the server to gather the required state information, bundles any data that
     * needs to be sent to the GameActivity, and then starts that activity.
     *
     */
    private class startOnlineGameHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final long gameId = (Long)view.getTag();
            CharSequence charSequence = ((TextView)view.findViewById(R.id.onlineWinnerTextView)).getText();
            if(charSequence!=null && charSequence.toString().equals("Waiting for players...")) {
                Toast.makeText(OnlineListActivity.this, "Still looking for players.", Toast.LENGTH_SHORT).show();
            } else {
                GameStateRequest gameStateRequest = new GameStateRequest(gameId);
                spiceManager.execute(gameStateRequest, new ApiRequestListener<GameStateReceivable>() {
                    @Override
                    public void onTaskFailure(int code, String message) {
                        Toast.makeText(OnlineListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onTaskSuccess(GameStateReceivable result) {
                        //Create all of the players
                        ArrayList<Player> players = new ArrayList<Player>();
                        for(PlayerInformation player: result.players) {

                            if(player.userId == OnlineListActivity.super.userId) {
                                players.add(new OnlineHumanPlayer(result.gameId, player.userId, player.username, player.number));
                            } else {
                                players.add(new NetworkPlayer(player.userId, gameId, player.username, player.number));
                            }
                        }

                        //Create all of the pieces
                        Piece[] pieces = new Piece[result.gameState.pieces.length];
                        int piecesParced = 0;
                        for(PieceInformation piece: result.gameState.pieces) {
                            Position position = new Position(piece.row, piece.index);
                            pieces[piecesParced++] = new GridPiece(position, piece.playerNumber);
                        }

                        //Create the game board
                        GameBoard gameBoard = new CcGameBoard(pieces);

                        //Create the GameStateManager
                        Player.PlayerColor currentPlayerColor =
                                Player.getPlayerColor(result.gameState.currentPlayerNumber);
                        GameStateManager gameStateManager =
                                new GameStateManager(gameBoard, players, currentPlayerColor);

                        //Bundle information and start the GameActivity
                        Intent intent = new Intent(OnlineListActivity.this, GameActivity.class);
                        intent.putExtra("GAME_STATE_MANAGER", (Parcelable) gameStateManager); //Store GameStateManager
                        intent.putExtra("IS_ONLINE_GAME", true); //sets flag for the GameActivity
                        OnlineListActivity.this.startActivity(intent);
                    }

                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        Toast.makeText(OnlineListActivity.this, spiceException.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /** This handler creates and manages a new-online-game dialog. The dialog contains radio buttons
     * for the number of players.
     *
     * When join is clicked, this class makes a request to the server to be added to a game with the
     * number of players which was selected.
     *
     */
    private class NewGameHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final Popup newGameDialog = new Popup(OnlineListActivity.this);

            newGameDialog
                    .setTitleText("New Game")
                    .enableNewGameOptions()
                    .setAcceptButtonText("Join")
                    .setDeclineButtonText("Cancel")
                    .setCancelClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            newGameDialog.dismiss();
                        }
                    })
                    .setAcceptClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestNewGame(newGameDialog.getNumberOfPlayers()); //make the server call

                            newGameDialog.dismiss();
                        }
                    })
                    .show();
        }

        private void requestNewGame(int numberOfPlayers) {
            JoinGameRequest joinGameRequest =
                    new JoinGameRequest(OnlineListActivity.super.userId, numberOfPlayers);

            spiceManager.execute(joinGameRequest, new ApiRequestListener<JoinGameReceivable>() {
                @Override
                public void onTaskFailure(int code, String message) {
                    Toast.makeText(OnlineListActivity.this, "Error. Please try again later.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onTaskSuccess(JoinGameReceivable result) {
                    View newGameListItem = createListItemView(result.gameListItem);
                    gameListContainer.addView(newGameListItem);
                }

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    Toast.makeText(OnlineListActivity.this, "Error. Please try again later.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /** This class handles deleting a game from the online list of games.
     *
     * If the game is not over, the user will be notified that they will be forfeiting the game. If
     * The game is over, they will still be asked to confirm the deletion.
     *
     * The game will only be removed from the list once the server confirms that removal was
     * successful.
     *
     */
    private class DeleteGameHandler implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            final View listItem = view;
            final long gameId = Long.parseLong(((TextView)view.findViewById(R.id.onlineGameIdTextView)).getText().toString().substring(1));
            final Popup deleteGameDialog = new Popup(OnlineListActivity.this);

            //Set mutual dialog settings
            deleteGameDialog
                    .setCancelClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteGameDialog.dismiss();
                        }
                    })
                    .setAcceptClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteGameDialog.dismiss();

                            DeleteGameRequest deleteGameRequest = new DeleteGameRequest(OnlineListActivity.super.userId, gameId);
                            spiceManager.execute(deleteGameRequest, new ApiRequestListener<SuccessReceivable>() {
                                @Override
                                public void onTaskFailure(int code, String message) {
                                    Toast.makeText(OnlineListActivity.this, message, Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onTaskSuccess(SuccessReceivable result) {
                                    gameListContainer.removeView(listItem);
                                }

                                @Override
                                public void onRequestFailure(SpiceException spiceException) {
                                    Toast.makeText(OnlineListActivity.this, spiceException.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });


            if(view.findViewById(R.id.onlineWinnerContainer).getVisibility() == View.VISIBLE) { //construct plain delete dialog
                deleteGameDialog
                        .setTitleText("Delete Game?")
                        .setMessageText("This game will be removed from your list of online games.")
                        .setAcceptButtonText("Delete");

            } else { //construct forfeit dialog
                deleteGameDialog
                        .setTitleText("Forfeit Game?")
                        .setMessageText("If you delete this game, you will automatically forfeit and lose the game.")
                        .setAcceptButtonText("Forfeit");
            }
            deleteGameDialog.show();

            return true;
        }
    }
}
