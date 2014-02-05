package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class HotseatConfigurationActivity extends Activity {
    private ToggleButton twoPlayerButton, threePlayerButton, fourPlayerButton, sixPlayerButton;
    private EditText redPlayerEditText, orangePlayerEditText, yellowPlayerEditText,
            greenPlayerEditText, bluePlayerEditText, purplePlayerEditText;
    private LinearLayout redPlayerNameContainer, orangePlayerNameContainer, yellowPlayerNameContainer,
            greenPlayerNameContainer, bluePlayerNameContainer, purplePlayerNameContainer;
    private Button startHotseatGameButton;

    private ToggleButton currentSelection;

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

        redPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatRedPlayerNameContainer);
        orangePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatOrangePlayerNameContainer);
        yellowPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatYellowPlayerNameContainer);
        greenPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatGreenPlayerNameContainer);
        bluePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatBluePlayerNameContainer);
        purplePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatPurplePlayerNameContainer);

        startHotseatGameButton = (Button) findViewById(R.id.hotseatGameActivityButton);

        //Bind Handlers
        twoPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        threePlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        fourPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        sixPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        startHotseatGameButton.setOnClickListener(new StartGameHandler());

        currentSelection = twoPlayerButton;
    }


    private class NumberOfPlayerSelectionHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if(currentSelection.getId() != view.getId()) { //if the current button was not pressed

                redPlayerEditText.setText("");
                orangePlayerEditText.setText("");
                yellowPlayerEditText.setText("");
                greenPlayerEditText.setText("");
                bluePlayerEditText.setText("");
                purplePlayerEditText.setText("");

                switch(view.getId()) {
                    case R.id.hotseatTwoPlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 2");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerNameContainer.setVisibility(View.GONE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.hotseatThreePlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerEditText.setHint("Player 2");
                        yellowPlayerNameContainer.setVisibility(View.VISIBLE);
                        greenPlayerNameContainer.setVisibility(View.GONE);
                        bluePlayerEditText.setHint("Player 3");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.hotseatFourPlayerButton:
                        orangePlayerEditText.setHint("Player 2");
                        orangePlayerNameContainer.setVisibility(View.VISIBLE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 3");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerEditText.setHint("Player 4");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.hotseatSixPlayerButton:
                        orangePlayerEditText.setHint("Player 2");
                        orangePlayerNameContainer.setVisibility(View.VISIBLE);
                        yellowPlayerEditText.setHint("Player 3");
                        yellowPlayerNameContainer.setVisibility(View.VISIBLE);
                        greenPlayerEditText.setHint("Player 4");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerEditText.setHint("Player 5");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerEditText.setHint("Player 6");
                        purplePlayerNameContainer.setVisibility(View.VISIBLE);
                        break;
                }

                //un-checks previous option and updates the current selection
                currentSelection.setChecked(false);
                currentSelection = (ToggleButton)view;
            }
            ((ToggleButton)view).setChecked(true);
        }
    }

    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
