package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class HotseatConfigurationActivity extends Activity {
    private ToggleButton twoPlayerButton;
    private ToggleButton threePlayerButton;
    private ToggleButton fourPlayerButton;
    private ToggleButton sixPlayerButton;
    private EditText redPlayerEditText;
    private EditText orangePlayerEditText;
    private EditText yellowPlayerEditText;
    private EditText greenPlayerEditText;
    private EditText bluePlayerEditText;
    private EditText purplePlayerEditText;
    private Button startHotseatGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotseat_configuration);

        //Bind Controls
        twoPlayerButton = (ToggleButton) findViewById(R.id.hotseatTwoPlayerButton);
        threePlayerButton = (ToggleButton) findViewById(R.id.hotseatThreePlayerButton);
        fourPlayerButton = (ToggleButton) findViewById(R.id.hotseatFourPlayerButton);
        sixPlayerButton = (ToggleButton) findViewById(R.id.hotseatSixPlayerButton);
        redPlayerEditText = (EditText) findViewById(R.id.hotseatRedPlayerNameEditText);
        orangePlayerEditText = (EditText) findViewById(R.id.hotseatOrangePlayerNameEditText);
        yellowPlayerEditText = (EditText) findViewById(R.id.hotseatYellowPlayerNameEditText);
        greenPlayerEditText = (EditText) findViewById(R.id.hotseatGreenPlayerNameEditText);
        bluePlayerEditText = (EditText) findViewById(R.id.hotseatBluePlayerNameEditText);
        purplePlayerEditText = (EditText) findViewById(R.id.hotseatPurplePlayerNameEditText);
        startHotseatGameButton = (Button) findViewById(R.id.hotseatGameActivityButton);

        //Bind Handlers
        twoPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        threePlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        fourPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        sixPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        startHotseatGameButton.setOnClickListener(new StartGameHandler());
    }


    private class NumberOfPlayerSelectionHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            twoPlayerButton.setChecked(false);
            threePlayerButton.setChecked(false);
            fourPlayerButton.setChecked(false);
            sixPlayerButton.setChecked(false);
            ((ToggleButton)view).setChecked(true);

            switch(view.getId()) {
                case R.id.hotseatTwoPlayerButton:
                    break;
                case R.id.hotseatThreePlayerButton:
                    break;
                case R.id.hotseatFourPlayerButton:
                    break;
                case R.id.hotseatSixPlayerButton:
                    break;
            }
        }
    }

    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
