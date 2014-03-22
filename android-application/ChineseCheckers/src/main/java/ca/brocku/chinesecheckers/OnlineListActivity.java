package ca.brocku.chinesecheckers;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import ca.brocku.chinesecheckers.gamestate.Player;

/**
 * Created by kubasub on 2014-03-06.
 */
public class OnlineListActivity extends Activity {
    private LinearLayout gameListContainer;
    private Button newGameButton;
    private LinearLayout networkConnectivityContainer;

    private NetworkStateReceiver networkStateReceiver;

    private ViewManager onlineGameViewManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_list);

        networkStateReceiver = new NetworkStateReceiver(); //for connectivity change

        //Bind Controls
        newGameButton = (Button)findViewById(R.id.onlineNewGameButton);
        gameListContainer = (LinearLayout)findViewById(R.id.onlineGameListContainer);
        networkConnectivityContainer = (LinearLayout)findViewById(R.id.networkConnectivityContainer);

        //Bind Handlers
        newGameButton.setOnClickListener(new NewGameHandler());

        //Creates a ViewManager for the list of games and populates the list
        onlineGameViewManager = new OnlineListViewManager();
        populateList();

        //Opens the new game dialog if there are no games
        if(gameListContainer.getChildCount() == 0) {
            newGameButton.performClick();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); //for connectivity change
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkStateReceiver);
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
        //TODO: server request for current games array
        GameItemData[] gameItemData = new GameItemData[2];

        gameItemData[0] = new GameItemData(15024, true, 2, Player.PlayerColor.RED, false, null, null);
        gameItemData[1] = new GameItemData(123, false, 4, Player.PlayerColor.GREEN, true, "randoGuy", Player.PlayerColor.RED);

        for (GameItemData aGameItemData : gameItemData) { //for each game received

            //Inflate the game view
            View newGame = getLayoutInflater().inflate(R.layout.fragment_online_list_item, null);
            onlineGameViewManager.addView(newGame, null);

            if (newGame != null) {

                //Set the margin at the bottom of the list item (in dp)
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        (float) 18, getResources().getDisplayMetrics());
                layoutParams.bottomMargin = value;
                newGame.setLayoutParams(layoutParams);

                newGame.setOnClickListener(new startOnlineGameHandler());
                newGame.setOnLongClickListener(new DeleteGameHandler());

                //Set view tag as game ID
                newGame.setTag(aGameItemData.getGameId());

                //Set Game ID
                ((TextView) newGame.findViewById(R.id.onlineGameIdTextView)).setText("#"+Integer.toString(aGameItemData.getGameId()));

                //Set Player icons
                switch (aGameItemData.getNumberOfPlayers()) {
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

                //Sets User's icon (with star)
                switch (aGameItemData.getPlayerColor()) {
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
                if (aGameItemData.isPlayerTurn()) {
                    newGame.findViewById(R.id.onlineListItemNotificationIcon).setVisibility(View.VISIBLE);
                }

                //Set winner section (if game has a winner)
                if (aGameItemData.isWinner()) {
                    ((TextView) newGame.findViewById(R.id.onlineWinnerTextView)).setText(aGameItemData.getWinnerUsername());

                    //Sets the trophy colour
                    switch (aGameItemData.getWinnerColor()) {
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
                gameListContainer.addView(newGame);
            }
        }
    }

    //TODO: see if this can be deleted after updating the list has been put in place
    /** This class manages the list of online game views.
     *
     */
    private class OnlineListViewManager implements ViewManager {
        LinkedList<View> listItems = new LinkedList<View>();

        @Override
        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            listItems.add(view);
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            int index = listItems.indexOf(view); //get index of view to be updated
            listItems.get(index).setLayoutParams(layoutParams); //get the view and set layout params
        }

        @Override
        public void removeView(View view) {
            gameListContainer.removeView(view);
            listItems.remove(view);
        }

        /** Removes a list item based on it's tag.
         *
         * @param gameIdTag the list items tag (it's game ID)
         */
        public void removeView(int gameIdTag) {
            for(View view : listItems) {
                if((Integer)view.getTag() == gameIdTag) { //delete the view with the given gameIdTag
                    removeView(view);
                }
            }
        }

        /** Gets a list item based on it's tag. Returns null if it cannot find the specified view.
         *
         * @param gameIdTag the list items tag (it's game ID)
         * @return the list item
         */
        public View getView(int gameIdTag) {
            for(View view : listItems) {
                if((Integer)view.getTag() == gameIdTag) {
                    return view;
                }
            }
            return null;
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
                            int numberOfPlayers = newGameDialog.getNumberOfPlayers();

                            //TODO: make server call to join game, remove Toast
                            Toast.makeText(OnlineListActivity.this, Integer.toString(numberOfPlayers), Toast.LENGTH_LONG).show();

                            newGameDialog.dismiss();
                        }
                    })
                    .show();
        }
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
            //TODO: Make API call to get game state, set up Parcelable stuff and then start the activity
            //OnlineListActivity.this.finish();
            //OnlineListActivity.this.startActivity(new Intent(OnlineListActivity.this, GameActivity.class));
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

                            //TODO: make API call to request removal from game
                            boolean isDeletionProcessed = true;
                            if (isDeletionProcessed) {
                                onlineGameViewManager.removeView(listItem);
                                gameListContainer.removeView(listItem);
                            }
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

    private class NetworkStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if((mobile!=null && mobile.isConnected()) || (wifi!=null && wifi.isConnected())) {
                    networkConnectivityContainer.setVisibility(View.GONE);
                } else {
                    networkConnectivityContainer.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    //TODO: delete
    /** THIS IS A TEMP CLASS WHICH IS TO BE DELETE WHEN WE CAN MAKE AN API CALL AND GET A LIST OF
     * EACH GAME ITEM'S DATA.
     *
     */
    private class GameItemData {
        private int gameId;
        private boolean isPlayerTurn;
        private int numberOfPlayers;
        private Player.PlayerColor playerColor;
        private boolean isWinner;
        private String winnerUsername;
        private Player.PlayerColor winnerColor;

        private GameItemData(int gameId, boolean isPlayerTurn, int numberOfPlayers, Player.PlayerColor playerColor, boolean isWinner, String winnerUsername, Player.PlayerColor winnerColor) {
            this.gameId = gameId;
            this.isPlayerTurn = isPlayerTurn;
            this.numberOfPlayers = numberOfPlayers;
            this.playerColor = playerColor;
            this.isWinner = isWinner;
            this.winnerUsername = winnerUsername;
            this.winnerColor = winnerColor;
        }

        public int getGameId() {
            return gameId;
        }

        public boolean isPlayerTurn() {
            return isPlayerTurn;
        }

        public int getNumberOfPlayers() {
            return numberOfPlayers;
        }

        public Player.PlayerColor getPlayerColor() {
            return playerColor;
        }

        public boolean isWinner() {
            return isWinner;
        }

        public String getWinnerUsername() {
            return winnerUsername;
        }

        public Player.PlayerColor getWinnerColor() {
            return winnerColor;
        }
    }
}
