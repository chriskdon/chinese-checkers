package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

/**
 * Created by kubasub on 2014-03-06.
 */
public class SettingsActivity extends Activity {
    private RadioGroup showMovesRadio;
    private EditText usernameEditText;
    private SharedPreferences sharedPrefs;
    private LinearLayout networkConnectivityContainer;

    private NetworkStateReceiver networkStateReceiver; //for connectivity changes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        networkStateReceiver = new NetworkStateReceiver();

        //Bind Controls
        showMovesRadio = (RadioGroup)findViewById(R.id.settingsShowMovesRadioGroup);
        usernameEditText = (EditText)findViewById(R.id.settingsUsernameEditText);
        networkConnectivityContainer = (LinearLayout)findViewById(R.id.networkConnectivityContainer);

        //Set Controls with currently set preferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        showMovesRadio.check(showMoves ? R.id.settingsShowMoveOnButton : R.id.settingsShowMoveOffButton);
        String username = sharedPrefs.getString(MainActivity.PREF_USER_ID, "");
        usernameEditText.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_help) {
            startActivity(new Intent(SettingsActivity.this, HelpActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); //for changes in connectivity
    }

    /** Updates the preferences.
     *
     */
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPrefs.edit();

        //Get preferences from the UI
        View checkedChild = findViewById(showMovesRadio.getCheckedRadioButtonId());
        boolean showMoves = (showMovesRadio.indexOfChild(checkedChild) == 0);
        String newUsername = usernameEditText.getText().toString();

        //try to save username if it has changed
        if(!newUsername.equals(sharedPrefs.getString(MainActivity.PREF_USER_ID, ""))) {
            //TODO: make server request to accept new username
            //TODO: save username or show Toast with error
            editor.putString(MainActivity.PREF_USER_ID, newUsername);
        }

        //save the show possible moves setting
        editor.putBoolean(MainActivity.PREF_SHOW_MOVES, showMoves);

        editor.commit();

        unregisterReceiver(networkStateReceiver);
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

}
