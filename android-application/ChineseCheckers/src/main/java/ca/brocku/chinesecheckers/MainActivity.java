package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ccapp.UserRegistrationResult;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.UUID;

import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.network.SpicedGcmActivity;
import ca.brocku.chinesecheckers.network.spice.ApiRequestListener;
import ca.brocku.chinesecheckers.network.spice.pojos.FollowerList;
import ca.brocku.chinesecheckers.network.spice.requests.RegisterUserRequest;

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
    private LinearLayout networkConnectivityContainer;

    private NetworkStateReceiver networkStateReceiver; //for connectivity changes

    public static final String PREF_DONE_INITIAL_SETUP = "DONE_INITIAL_SETUP";
    public static final String PREF_SHOW_MOVES = "SHOW_MOVES";
    public static final String PREF_USER_ID = "USER_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialPreferences(); //only sets the prefs on first launch

        networkStateReceiver = new NetworkStateReceiver();

        //Bind Controls
        offlineActivityButton = (Button)findViewById(R.id.offlineConfigurationActivityButton);
        onlineNotificationIcon = (TextView)findViewById(R.id.onlineMoveNotificationTextView);
        onlineActivityButton = (Button)findViewById(R.id.onlineListActivityButton);
        helpActivityButton = (Button)findViewById(R.id.helpActivityButton);
        settingsActivityButton = (Button)findViewById(R.id.settingsActivityButton);
        networkConnectivityContainer = (LinearLayout)findViewById(R.id.networkConnectivityContainer);


        //Bind Handlers
        offlineActivityButton.setOnClickListener(new OfflineActivityButtonHandler());
        onlineActivityButton.setOnClickListener(new OnlineActivityButtonHandler());
        helpActivityButton.setOnClickListener(new HelpActivityButtonHandler());
        settingsActivityButton.setOnClickListener(new SettingsActivityButtonHandler());

        //performRequest("MyNewTestUser"); // TODO: FOR TESTING -- REMOVE
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkStateReceiver); //for connectivity change
    }

    @Override
    protected void onResume() {
        super.onResume();

        //set the icon which shows the number of games in which it is your turn
        //TODO: make API call HERE to get number of "current move" games and set variable
        int numberOfCurrentMoveGames = 1;
        if(numberOfCurrentMoveGames > 0) {
            onlineNotificationIcon.setText(Integer.toString(numberOfCurrentMoveGames));
            onlineNotificationIcon.setVisibility(View.VISIBLE);
        } else {
            onlineNotificationIcon.setVisibility(View.INVISIBLE);
        }

        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); //for connectivity change
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
                .putString(PREF_USER_ID, UUID.randomUUID().toString())
                .commit();
        }
    }

    // TODO: PLACEHOLDER EXAMPLE -- DELETE WHEN THERE IS A REAL API
    private void performRequest(String user) {
        this.setProgressBarIndeterminateVisibility(true);

        RegisterUserRequest request = new RegisterUserRequest(user);

        spiceManager.execute(request, new ApiRequestListener<UserRegistrationResult>() {
            @Override
            public void onTaskFailure(int code, String message) {
                Toast.makeText(MainActivity.this, "Task Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskSuccess(UserRegistrationResult result) {
                Toast.makeText(MainActivity.this, "User ID: " + result.userId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(MainActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
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

    private class NetworkStateReceiver extends BroadcastReceiver{
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
}
