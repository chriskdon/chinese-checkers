package ca.brocku.chinesecheckers;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccapi.receivables.SuccessReceivable;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ca.brocku.chinesecheckers.network.SpicedGcmActivity;
import ca.brocku.chinesecheckers.network.spice.ApiRequestListener;
import ca.brocku.chinesecheckers.network.spice.SpicedActivity;
import ca.brocku.chinesecheckers.network.spice.requests.ChangeUsernameRequest;
import ca.brocku.chinesecheckers.network.spice.requests.JoinGameRequest;

/**
 *
 * @author  Jakub Subczynski
 * @date    April 02, 2014
 */
public class SettingsActivity extends SpicedGcmActivity {
    private RadioGroup showMovesRadio;
    private TextView usernameErrorTextView;
    private EditText usernameEditText;
    private SeekBar backgroundSlider, effectsSlider;
    private TextView backPerText, effectPerText;
    private SharedPreferences sharedPrefs;

    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Bind Controls
        usernameEditText = (EditText) findViewById(R.id.settingsUsernameEditText);
        backgroundSlider = (SeekBar) findViewById(R.id.settingsBackSoundSlider);
        effectsSlider = (SeekBar) findViewById(R.id.settingsEffectSoundSlider);
        backPerText = (TextView) findViewById(R.id.settingsBackSoundText);
        effectPerText = (TextView) findViewById(R.id.settingsEffectSoundText);
        showMovesRadio = (RadioGroup)findViewById(R.id.settingsShowMovesRadioGroup);
        usernameErrorTextView = (TextView)findViewById(R.id.settingsUsernameErrorTextView);
        usernameEditText = (EditText)findViewById(R.id.settingsUsernameEditText);

        //Set Controls with currently set preferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        showMovesRadio.check(showMoves ? R.id.settingsShowMoveOnButton : R.id.settingsShowMoveOffButton);
        String username = sharedPrefs.getString(MainActivity.PREF_USERNAME, "");
        usernameEditText.setText(username);

        backgroundSlider.setProgress(sharedPrefs.getInt(BoomBoomMusic.BACKSOUNDPREF, 100));
        backPerText.setText(sharedPrefs.getInt(BoomBoomMusic.BACKSOUNDPREF, 100) + "%");
        effectsSlider.setProgress(sharedPrefs.getInt(BoomBoomMusic.EFFCTSOUNDPREF, 100));
        effectPerText.setText(sharedPrefs.getInt(BoomBoomMusic.EFFCTSOUNDPREF,100) + "%");

        backgroundSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                backPerText.setText(i + "%");
                BoomBoomMusic.setBackgroundVolume(i);
                editor.putInt(BoomBoomMusic.BACKSOUNDPREF, i);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        effectsSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferences.Editor editor = sharedPrefs.edit();
                effectPerText.setText(i + "%");
                editor.putInt(BoomBoomMusic.EFFCTSOUNDPREF, i);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            startActivity(new Intent(SettingsActivity.this, HelpActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BoomBoomMusic.start(this);
    }

    /** Updates the preferences.
     */
    @Override
    protected void onPause() {
        super.onPause();

        //Get preferences from the UI
        View checkedChild = findViewById(showMovesRadio.getCheckedRadioButtonId());
        boolean showMoves = (showMovesRadio.indexOfChild(checkedChild) == 0);
        final String newUsername = usernameEditText.getText().toString();


        //try to save username if it has changed
        if(!newUsername.equals(sharedPrefs.getString(MainActivity.PREF_USERNAME, ""))) {
            if(super.isConnected) {
                //Make request only if the user is registered
                if(super.userId != -1) {
                    ChangeUsernameRequest changeUsernameRequest = new ChangeUsernameRequest(super.userId, newUsername);
                    spiceManager.execute(changeUsernameRequest, new ApiRequestListener<SuccessReceivable>() {
                        @Override
                        public void onTaskFailure(int code, String message) {
                            Toast.makeText(SettingsActivity.this, "Error. Username exists.", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onTaskSuccess(SuccessReceivable result) {
                            sharedPrefs.edit()
                                    .putString(MainActivity.PREF_USERNAME, newUsername)
                                    .commit();
                            Toast.makeText(SettingsActivity.this, "Username updated.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onRequestFailure(SpiceException spiceException) {

                        }
                    });
                }

            } else {
                Toast.makeText(this, "Couldn't save username. No internet connection.", Toast.LENGTH_LONG).show();
            }
        }

        //save the show possible moves setting
        sharedPrefs.edit()
                .putBoolean(MainActivity.PREF_SHOW_MOVES, showMoves)
                .commit();

        BoomBoomMusic.pause();
    }

    @Override
    protected void onNetworkConnected() {
        super.onNetworkConnected();

        usernameErrorTextView.setVisibility(View.GONE);

        usernameEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
    }

    @Override
    protected void onNetworkDisconnected() {
        super.onNetworkDisconnected();

        usernameEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                usernameErrorTextView.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }
}
