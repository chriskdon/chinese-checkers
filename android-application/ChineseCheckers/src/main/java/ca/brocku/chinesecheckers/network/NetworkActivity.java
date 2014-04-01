package ca.brocku.chinesecheckers.network;

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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ccapi.receivables.UserRegistrationReceivable;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.network.spice.requests.RegisterUserRequest;

/**
 * @author kubasub
 * @date March 27, 2014
 */
public class NetworkActivity extends Activity {
    private NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver();
    private LinearLayout networkConnectivityContainer;
    private long userId;

    protected boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Stores the user ID
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        userId = sharedPrefs.getLong(MainActivity.PREF_USER_ID, -1);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_network);

        networkConnectivityContainer = (LinearLayout)findViewById(R.id.networkConnectivityContainer);

        FrameLayout subActivityContainer = (FrameLayout)findViewById(R.id.activityLayoutContainer);
        getLayoutInflater().inflate(layoutResID, subActivityContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); //for changes in connectivity
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkStateReceiver);
    }

    protected void onNetworkConnected() {
        isConnected = true;
        networkConnectivityContainer.setVisibility(View.GONE);

        if (userId == -1) //if user ID hasn't been set yet
            registerUser();
    }

    protected void onNetworkDisconnected() {
        isConnected = false;
        networkConnectivityContainer.setVisibility(View.VISIBLE);
    }

    private class NetworkStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if((mobile!=null && mobile.isConnected()) || (wifi!=null && wifi.isConnected())) {
                    NetworkActivity.this.onNetworkConnected();
                } else {
                    NetworkActivity.this.onNetworkDisconnected();
                }
            }
        }
    }

    protected void registerUser() {
        Toast.makeText(NetworkActivity.this, "Trying to registerUser()", Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPrefs.getString(MainActivity.PREF_USERNAME, null);

        //Flag to go through initial setup in MainActivity if username hasn't been generated
        if(username == null) {
            sharedPrefs.edit()
                    .putBoolean(MainActivity.PREF_DONE_INITIAL_SETUP, false)
                    .commit();
        } else {
            RegisterUserRequest registerUserRequest = new RegisterUserRequest(username);
            SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
            spiceManager.execute(registerUserRequest, new RequestListener<UserRegistrationReceivable>() {
                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    Toast.makeText(NetworkActivity.this, "ERROR REGISTERING USER", Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
                }

                @Override
                public void onRequestSuccess(UserRegistrationReceivable userRegistrationReceivable) {
                    sharedPrefs.edit()
                            .putLong(MainActivity.PREF_USER_ID, userRegistrationReceivable.userId)
                            .commit();
                    userId = userRegistrationReceivable.userId;
                    Toast.makeText(NetworkActivity.this, "Successfully registered as user"+userId, Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
                }
            });
        }
    }
}