package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

/** This is the Activity associated with the hotseat configuration screen.
 *
 * The user must choose how many people are to play and their names. After validation, this activity
 * starts the hotseat game activity.
 *
 */
public class HotseatConfigurationActivity extends Activity {
    private ToggleButton twoPlayerButton, threePlayerButton, fourPlayerButton, sixPlayerButton;
    private LinearLayout redPlayerNameContainer, orangePlayerNameContainer, yellowPlayerNameContainer,
            greenPlayerNameContainer, bluePlayerNameContainer, purplePlayerNameContainer;
    private ImageView redPlayerError, orangePlayerError, yellowPlayerError,
            greenPlayerError, bluePlayerError, purplePlayerError;
    private EditText redPlayerEditText, orangePlayerEditText, yellowPlayerEditText,
            greenPlayerEditText, bluePlayerEditText, purplePlayerEditText;
    private Button startHotseatGameButton;

    private ToggleButton currentSelection; //the current selection for number of players

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotseat_configuration);

        //Bind Controls
        twoPlayerButton = (ToggleButton) findViewById(R.id.hotseatTwoPlayerButton);
        threePlayerButton = (ToggleButton) findViewById(R.id.hotseatThreePlayerButton);
        fourPlayerButton = (ToggleButton) findViewById(R.id.hotseatFourPlayerButton);
        sixPlayerButton = (ToggleButton) findViewById(R.id.hotseatSixPlayerButton);

        redPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatRedPlayerNameContainer);
        orangePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatOrangePlayerNameContainer);
        yellowPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatYellowPlayerNameContainer);
        greenPlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatGreenPlayerNameContainer);
        bluePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatBluePlayerNameContainer);
        purplePlayerNameContainer = (LinearLayout)findViewById(R.id.hotseatPurplePlayerNameContainer);

        redPlayerError = (ImageView) findViewById(R.id.hotseatRedPlayerError);
        orangePlayerError = (ImageView) findViewById(R.id.hotseatOrangePlayerError);
        yellowPlayerError = (ImageView) findViewById(R.id.hotseatYellowPlayerError);
        greenPlayerError = (ImageView) findViewById(R.id.hotseatGreenPlayerError);
        bluePlayerError = (ImageView) findViewById(R.id.hotseatBluePlayerError);
        purplePlayerError = (ImageView) findViewById(R.id.hotseatPurplePlayerError);

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

        currentSelection = twoPlayerButton;
    }

    /** Hides all of the warning symbols associated with required input.
     *
     */
    private void hideWarnings() {
        redPlayerError.setVisibility(View.INVISIBLE);
        orangePlayerError.setVisibility(View.INVISIBLE);
        yellowPlayerError.setVisibility(View.INVISIBLE);
        greenPlayerError.setVisibility(View.INVISIBLE);
        bluePlayerError.setVisibility(View.INVISIBLE);
        purplePlayerError.setVisibility(View.INVISIBLE);
    }

    /** Handles clicking on any of the number of player buttons.
     *
     */
    private class NumberOfPlayerSelectionHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if(currentSelection.getId() != view.getId()) { //if the current button was not pressed

                hideWarnings();

                //clears the names if another option is selected for number of players
                redPlayerEditText.setText("");
                orangePlayerEditText.setText("");
                yellowPlayerEditText.setText("");
                greenPlayerEditText.setText("");
                bluePlayerEditText.setText("");
                purplePlayerEditText.setText("");

                //shows and labels the name fields based on the number of players option
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
            ((ToggleButton)view).setChecked(true); //ensures the selected option is checked
        }
    }

    /** Handles validation of inputted configuration and starting the hotseat game activity.
     *
     */
    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            hideWarnings();

            //if any of the visible input fields are empty, don't submit
            if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerEditText.getText().toString().equals("") ||
                    orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerEditText.getText().toString().equals("") ||
                    yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerEditText.getText().toString().equals("") ||
                    greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerEditText.getText().toString().equals("") ||
                    bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerEditText.getText().toString().equals("") ||
                    purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerEditText.getText().toString().equals("")) {

                //for each visible input field which is empty, display its warning symbol
                if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerEditText.getText().toString().equals(""))
                    redPlayerError.setVisibility(View.VISIBLE);
                if(orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerEditText.getText().toString().equals(""))
                    orangePlayerError.setVisibility(View.VISIBLE);
                if(yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerEditText.getText().toString().equals(""))
                    yellowPlayerError.setVisibility(View.VISIBLE);
                if(greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerEditText.getText().toString().equals(""))
                    greenPlayerError.setVisibility(View.VISIBLE);
                if(bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerEditText.getText().toString().equals(""))
                    bluePlayerError.setVisibility(View.VISIBLE);
                if(purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerEditText.getText().toString().equals(""))
                    purplePlayerError.setVisibility(View.VISIBLE);

            } else {
                String[] players; //to be passed to HotseatGameActivity
                Intent intent = new Intent(HotseatConfigurationActivity.this, HotseatGameActivity.class);

                switch(currentSelection.getId()) {
                    case R.id.hotseatTwoPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.hotseatThreePlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                yellowPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.hotseatFourPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                orangePlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.hotseatSixPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                orangePlayerEditText.getText().toString(),
                                yellowPlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString(),
                                purplePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                }
                HotseatConfigurationActivity.this.finish();
                startActivity(intent);
            }

        }
    }
}
