package ca.brocku.chinesecheckers.network;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.ccapi.receivables.UserRegistrationReceivable;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;

import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.network.gcm.GcmActivity;
import ca.brocku.chinesecheckers.network.spice.ApiRequestListener;
import ca.brocku.chinesecheckers.network.spice.requests.RegisterUserRequest;

/**
 * An activity that needs to have both RoboSpice and GCM services.
 *
 * TODO: There may be a cleaner way of doing this. Currently this code is repeated in SpicedActivity.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class SpicedGcmActivity extends GcmActivity {
    protected SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
    protected long userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Stores the user ID
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        userId = sharedPrefs.getLong(MainActivity.PREF_USER_ID, -1);
    }

    /**
     * Start the spice manager.
     */
    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    /**
     * Stop the spice manager.
     */
    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void onNetworkConnected() {
        super.onNetworkConnected();

        if (userId == -1) //if user ID hasn't been set yet
            registerUser();
    }

    protected void registerUser() {
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPrefs.getString(MainActivity.PREF_USERNAME, null);

        //Flag to go through initial setup in MainActivity if username hasn't been generated
        if(username == null) {
            sharedPrefs.edit()
                    .putBoolean(MainActivity.PREF_DONE_INITIAL_SETUP, false)
                    .commit();
        } else {
            RegisterUserRequest registerUserRequest = new RegisterUserRequest(username);
            spiceManager.execute(registerUserRequest, new ApiRequestListener<UserRegistrationReceivable>() {
                @Override
                public void onTaskFailure(int code, String message) {
                    Toast.makeText(SpicedGcmActivity.this, message, Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
                }

                @Override
                public void onTaskSuccess(UserRegistrationReceivable result) {
                    sharedPrefs.edit()
                            .putLong(MainActivity.PREF_USER_ID, result.userId)
                            .commit();
                    userId = result.userId;
                    Toast.makeText(SpicedGcmActivity.this, "Successfully registered as user" + userId, Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
                }

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    Toast.makeText(SpicedGcmActivity.this, "FAILURE", Toast.LENGTH_LONG).show(); //TODO: delete toast after testing
                }
            });
        }
    }
}
