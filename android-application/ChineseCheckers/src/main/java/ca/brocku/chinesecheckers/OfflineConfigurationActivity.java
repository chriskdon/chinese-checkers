package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import static android.view.View.OnFocusChangeListener;

/** This is the Activity associated with the offline configuration screen.
 *
 * The user must choose how many people are to play and their names. After validation, this activity
 * starts the offline game activity.
 *
 */
public class OfflineConfigurationActivity extends Activity {
    private ToggleButton twoPlayerButton, threePlayerButton, fourPlayerButton, sixPlayerButton;
    private LinearLayout redPlayerNameContainer, orangePlayerNameContainer, yellowPlayerNameContainer,
            greenPlayerNameContainer, bluePlayerNameContainer, purplePlayerNameContainer;
    private ImageView redPlayerError, orangePlayerError, yellowPlayerError,
            greenPlayerError, bluePlayerError, purplePlayerError;
    private EditText redPlayerEditText, orangePlayerEditText, yellowPlayerEditText,
            greenPlayerEditText, bluePlayerEditText, purplePlayerEditText;
    private Button startOfflineGameButton;

    private ToggleButton currentSelection; //the current selection for number of players

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_configuration);

        //Bind Controls
        twoPlayerButton = (ToggleButton) findViewById(R.id.offlineTwoPlayerButton);
        threePlayerButton = (ToggleButton) findViewById(R.id.offlineThreePlayerButton);
        fourPlayerButton = (ToggleButton) findViewById(R.id.offlineFourPlayerButton);
        sixPlayerButton = (ToggleButton) findViewById(R.id.offlineSixPlayerButton);

        redPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineRedPlayerNameContainer);
        orangePlayerNameContainer = (LinearLayout)findViewById(R.id.offlineOrangePlayerNameContainer);
        yellowPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineYellowPlayerNameContainer);
        greenPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineGreenPlayerNameContainer);
        bluePlayerNameContainer = (LinearLayout)findViewById(R.id.offlineBluePlayerNameContainer);
        purplePlayerNameContainer = (LinearLayout)findViewById(R.id.offlinePurplePlayerNameContainer);

        redPlayerError = (ImageView) findViewById(R.id.offlineRedPlayerError);
        orangePlayerError = (ImageView) findViewById(R.id.offlineOrangePlayerError);
        yellowPlayerError = (ImageView) findViewById(R.id.offlineYellowPlayerError);
        greenPlayerError = (ImageView) findViewById(R.id.offlineGreenPlayerError);
        bluePlayerError = (ImageView) findViewById(R.id.offlineBluePlayerError);
        purplePlayerError = (ImageView) findViewById(R.id.offlinePurplePlayerError);

        redPlayerEditText = (EditText) findViewById(R.id.offlineRedPlayerNameEditText);
        orangePlayerEditText = (EditText) findViewById(R.id.offlineOrangePlayerNameEditText);
        yellowPlayerEditText = (EditText) findViewById(R.id.offlineYellowPlayerNameEditText);
        greenPlayerEditText = (EditText) findViewById(R.id.offlineGreenPlayerNameEditText);
        bluePlayerEditText = (EditText) findViewById(R.id.offlineBluePlayerNameEditText);
        purplePlayerEditText = (EditText) findViewById(R.id.offlinePurplePlayerNameEditText);

        startOfflineGameButton = (Button) findViewById(R.id.offlineGameActivityButton);

        //Bind Handlers
        twoPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        threePlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        fourPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        sixPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        startOfflineGameButton.setOnClickListener(new StartGameHandler());

        redPlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());
        orangePlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());
        yellowPlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());
        greenPlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());
        bluePlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());
        purplePlayerEditText.setOnFocusChangeListener(new PlayerNameHandler());


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
                    case R.id.offlineTwoPlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 2");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerNameContainer.setVisibility(View.GONE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.offlineThreePlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerEditText.setHint("Player 2");
                        yellowPlayerNameContainer.setVisibility(View.VISIBLE);
                        greenPlayerNameContainer.setVisibility(View.GONE);
                        bluePlayerEditText.setHint("Player 3");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.offlineFourPlayerButton:
                        orangePlayerEditText.setHint("Player 2");
                        orangePlayerNameContainer.setVisibility(View.VISIBLE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 3");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerEditText.setHint("Player 4");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.offlineSixPlayerButton:
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

    /** Handles validation of inputted configuration and starting the offline game activity.
     *
     */
    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            hideWarnings();

            //if any of the visible input fields are empty, don't submit
            if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerEditText.getText().toString().trim().equals("") ||
                    orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerEditText.getText().toString().trim().equals("") ||
                    yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerEditText.getText().toString().trim().equals("") ||
                    greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerEditText.getText().toString().trim().equals("") ||
                    bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerEditText.getText().toString().trim().equals("") ||
                    purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerEditText.getText().toString().trim().equals("")) {

                //for each visible input field which is empty, display its warning symbol
                if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerEditText.getText().toString().trim().equals(""))
                    redPlayerError.setVisibility(View.VISIBLE);
                if(orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerEditText.getText().toString().trim().equals(""))
                    orangePlayerError.setVisibility(View.VISIBLE);
                if(yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerEditText.getText().toString().trim().equals(""))
                    yellowPlayerError.setVisibility(View.VISIBLE);
                if(greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerEditText.getText().toString().trim().equals(""))
                    greenPlayerError.setVisibility(View.VISIBLE);
                if(bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerEditText.getText().toString().trim().equals(""))
                    bluePlayerError.setVisibility(View.VISIBLE);
                if(purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerEditText.getText().toString().trim().equals(""))
                    purplePlayerError.setVisibility(View.VISIBLE);

            } else {
                String[] players; //to be passed to OfflineGameActivity
                Intent intent = new Intent(OfflineConfigurationActivity.this, OfflineGameActivity.class);

                switch(currentSelection.getId()) {
                    case R.id.offlineTwoPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.offlineThreePlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                yellowPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.offlineFourPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                orangePlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                    case R.id.offlineSixPlayerButton:
                        players = new String[]{redPlayerEditText.getText().toString(),
                                orangePlayerEditText.getText().toString(),
                                yellowPlayerEditText.getText().toString(),
                                greenPlayerEditText.getText().toString(),
                                bluePlayerEditText.getText().toString(),
                                purplePlayerEditText.getText().toString()};
                        intent.putExtra("PLAYER_NAMES", players);
                        break;
                }
                OfflineConfigurationActivity.this.finish();
                startActivity(intent);
            }

        }
    }

    /** Trims whitespace from the name input field as focus is lost to ensure a name with spaces is
     * not accepted.
     *
     */
    private class PlayerNameHandler implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {

            if(!b) { //If focus was lost

                //Trim whitespace from player's name input field
                EditText nameInput = (EditText)view;
                Editable inputText = nameInput.getText();
                if(inputText != null) {
                    nameInput.setText(inputText.toString().trim());
                }
            }
        }
    }
}
