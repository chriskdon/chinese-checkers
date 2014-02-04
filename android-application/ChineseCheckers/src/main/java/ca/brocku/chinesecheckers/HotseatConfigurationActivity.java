package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HotseatConfigurationActivity extends Activity {
    private Button twoPlayerButton;
    private Button threePlayerButton;
    private Button fourPlayerButton;
    private Button sixPlayerButton;
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
        twoPlayerButton = (Button) findViewById(R.id.hotseatTwoPlayerButton);
        threePlayerButton = (Button) findViewById(R.id.hotseatThreePlayerButton);
        fourPlayerButton = (Button) findViewById(R.id.hotseatFourPlayerButton);
        sixPlayerButton = (Button) findViewById(R.id.hotseatSixPlayerButton);
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
            Button numberOfPlayersButton = (Button)view;

        }
    }

    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
