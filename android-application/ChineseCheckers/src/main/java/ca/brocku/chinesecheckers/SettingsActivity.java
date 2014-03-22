package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by kubasub on 2014-03-06.
 */
public class SettingsActivity extends Activity {
    private RadioGroup showMovesRadio;
    private EditText usernameEditText;
    private SeekBar backgroundSlider, effectsSlider;
    private TextView backPerText, effectPerText;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Bind Controls
        showMovesRadio = (RadioGroup) findViewById(R.id.settingsShowMovesRadioGroup);
        usernameEditText = (EditText) findViewById(R.id.settingsUsernameEditText);
        backgroundSlider = (SeekBar) findViewById(R.id.settingsBackSoundSlider);
        effectsSlider = (SeekBar) findViewById(R.id.settingsEffectSoundSlider);
        backPerText = (TextView) findViewById(R.id.settingsBackSoundText);
        effectPerText = (TextView) findViewById(R.id.settingsEffectSoundText);

        //Set Controls with currently set preferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        showMovesRadio.check(showMoves ? R.id.settingsShowMoveOnButton : R.id.settingsShowMoveOffButton);
        String username = sharedPrefs.getString(MainActivity.PREF_USER_ID, "");
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

    /**
     * Updates the preferences.
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
        if (!newUsername.equals(sharedPrefs.getString(MainActivity.PREF_USER_ID, ""))) {
            //TODO: make server request to accept new username
            //TODO: save username or show Toast with error
        }

        //save the show possible moves setting
        editor.putBoolean(MainActivity.PREF_SHOW_MOVES, showMoves);

        editor.commit();
        BoomBoomMusic.pause();
    }

    protected void onResume() {
        super.onResume();
        BoomBoomMusic.start(this);
    }

}
