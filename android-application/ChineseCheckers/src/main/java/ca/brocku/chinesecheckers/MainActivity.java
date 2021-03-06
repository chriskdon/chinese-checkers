package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccapi.GameListItem;
import com.ccapi.receivables.GameListReceivable;
import com.ccapi.receivables.GameOverNotificationReceivable;
import com.ccapi.receivables.JoinGameReceivable;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.UUID;

import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.network.SpicedGcmActivity;
import ca.brocku.chinesecheckers.network.spice.ApiRequestListener;
import ca.brocku.chinesecheckers.network.spice.requests.GameListRequest;
import ca.brocku.chinesecheckers.network.spice.requests.JoinGameRequest;

/** This is the activity for the home screen of Chinese Checkers.
 *
 * It controls navigation to the different parts of the application including the different game
 * modes and user settings.
 *
 */
@SuppressLint("all")
public class MainActivity extends SpicedGcmActivity {
    private Button offlineActivityButton;
    private TextView onlineNotificationIcon;
    private Button onlineActivityButton;
    private Button helpActivityButton;
    private Button settingsActivityButton;

    public static final String PREF_DONE_INITIAL_SETUP = "DONE_INITIAL_SETUP";
    public static final String PREF_SHOW_MOVES = "SHOW_MOVES";
    public static final String PREF_USERNAME = "USERNAME";
    public static final String PREF_USER_ID = "USER_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialPreferences(); //only sets the prefs on first launch

        //Bind Controls
        offlineActivityButton = (Button)findViewById(R.id.offlineConfigurationActivityButton);
        onlineNotificationIcon = (TextView)findViewById(R.id.onlineMoveNotificationTextView);
        onlineActivityButton = (Button)findViewById(R.id.onlineListActivityButton);
        helpActivityButton = (Button)findViewById(R.id.helpActivityButton);
        settingsActivityButton = (Button)findViewById(R.id.settingsActivityButton);

        //Bind Handlers
        offlineActivityButton.setOnClickListener(new OfflineActivityButtonHandler());
        onlineActivityButton.setOnClickListener(new OnlineActivityButtonHandler());
        helpActivityButton.setOnClickListener(new HelpActivityButtonHandler());
        settingsActivityButton.setOnClickListener(new SettingsActivityButtonHandler());

        /*
        int notificationId = 123456789;

        //Build the notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo_image)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);

        //Create backstack and add it as the notification's intent
        Intent resultIntent = new Intent(this, OnlineListActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);

        //Issue notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        BoomBoomMusic.start(this);
        displayOnlineGameNotification();
    }

    protected void onPause(){
        BoomBoomMusic.pause();
        super.onPause();
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
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        } else if(id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /** Sets initial preferences if it is the first time the application is launched.
     *
     * The preferences set are:
     *      Show possible moves to true
     *      user's ID to a generated UUID
     *
     */
    private void setInitialPreferences() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //Gets boolean to determine if this is the first time app is launched
        Boolean isInitialSetupDone = sharedPref.getBoolean(PREF_DONE_INITIAL_SETUP, false);

        //Sets the default preferences. This is only ran the first time application is launched.
        if(!isInitialSetupDone) {
            SharedPreferences.Editor editor = sharedPref.edit(); //editor for the prefs

            editor
                    .putBoolean(PREF_DONE_INITIAL_SETUP, true)
                    .putBoolean(PREF_SHOW_MOVES, true)
                    .putString(PREF_USERNAME, UUID.randomUUID().toString())
                    .commit();

            if(super.isConnected) registerUser(); //try to register the user on first launch
        }
    }

    /** makes API request for number of games in which it is the user's turn and displays the
     * notification icon with the number of moves to be made (if there are any).

     */
    private void displayOnlineGameNotification() {

        GameListRequest gameListRequest = new GameListRequest(userId);
        spiceManager.execute(gameListRequest, new RequestListener<GameListReceivable>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(GameListReceivable gameListReceivable) {
                int num = 0;
                for(GameListItem gameListItem: gameListReceivable.gameListItems) {
                    if(gameListItem.isPlayerTurn() && gameListItem.isReady) {
                        num++;
                    }
                }
                if(num > 0) {
                    onlineNotificationIcon.setText(Integer.toString(num));
                    onlineNotificationIcon.setVisibility(View.VISIBLE);
                } else {
                    onlineNotificationIcon.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void onEvent(final GameOverNotificationReceivable event) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                displayOnlineGameNotification();
            }

        });
    }

    /** Handles clicking on the "Offline" game button.
     *
     */
    private class OfflineActivityButtonHandler implements Button.OnClickListener {

        /** OnClick event which starts the OfflineConfigurationActivity activity.
         *
         * @param view the Offline button
         */
        @Override
        public void onClick(View view) {
            File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME); //get the serialized file

            if(savedOfflineGame.exists()) { //if there is a saved game file
                try {
                    //Load the GameStateManager from storage
                    FileInputStream fis = openFileInput(GameStateManager.SERIALIZED_FILENAME);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    GameStateManager gameStateManager = (GameStateManager)ois.readObject();
                    ois.close();
                    fis.close();

                    //Bundle information and start the GameActivity
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("GAME_STATE_MANAGER", (Parcelable) gameStateManager); //Store GameStateManager
                    intent.putExtra("SAVED_GAME", true); //Store flag that this is a saved game
                    startActivity(intent);

                } catch (Exception e) {
                    savedOfflineGame.delete(); //delete the saved game as it couldn't be loaded
                    e.printStackTrace();
                }
            } else { //there is no saved game, go to configuration for the offline game
                startActivity(new Intent(MainActivity.this, OfflineConfigurationActivity.class));
            }
        }
    }

    private class OnlineActivityButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, OnlineListActivity.class));
        }
    }

    private class HelpActivityButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }
    }

    private class SettingsActivityButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
    }
}
