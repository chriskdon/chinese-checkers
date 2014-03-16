package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

import ca.brocku.chinesecheckers.computerplayer.AIPlayer;
import ca.brocku.chinesecheckers.gameboard.CcGameBoard;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;
import ca.brocku.chinesecheckers.gamestate.HumanPlayer;
import ca.brocku.chinesecheckers.gamestate.Player;

import static android.view.View.OnFocusChangeListener;

/** This is the Activity associated with the offline configuration screen.
 *
 * The user must choose how many people are to play and their names. After validation, this activity
 * starts the offline game activity.
 *
 */
@SuppressLint("all")
public class OfflineConfigurationActivity extends Activity {
    private ToggleButton twoPlayerButton, threePlayerButton, fourPlayerButton, sixPlayerButton;
    private ToggleButton redPlayerEasyButton, redPlayerMediumButton, redPlayerHardButton,
            orangePlayerEasyButton, orangePlayerMediumButton, orangePlayerHardButton,
            yellowPlayerEasyButton, yellowPlayerMediumButton, yellowPlayerHardButton,
            greenPlayerEasyButton, greenPlayerMediumButton, greenPlayerHardButton,
            bluePlayerEasyButton, bluePlayerMediumButton, bluePlayerHardButton,
            purplePlayerEasyButton, purplePlayerMediumButton, purplePlayerHardButton;
    private LinearLayout redPlayerNameContainer, orangePlayerNameContainer, yellowPlayerNameContainer,
            greenPlayerNameContainer, bluePlayerNameContainer, purplePlayerNameContainer;
    private ImageButton redPlayerTypeButton, orangePlayerTypeButton, yellowPlayerTypeButton,
            greenPlayerTypeButton, bluePlayerTypeButton, purplePlayerTypeButton;
    private ImageView redPlayerError, orangePlayerError, yellowPlayerError,
            greenPlayerError, bluePlayerError, purplePlayerError;
    private EditText redPlayerEditText, orangePlayerEditText, yellowPlayerEditText,
            greenPlayerEditText, bluePlayerEditText, purplePlayerEditText;
    private Button startOfflineGameButton;

    private ToggleButton currentSelection; //stores the current selection for number of players

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_configuration);

        bindControls(); //bind UI controls

        bindHandlers(); //bind handlers

        currentSelection = twoPlayerButton;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_help) {
            startActivity(new Intent(OfflineConfigurationActivity.this, HelpActivity.class));
        } else if(id == R.id.action_settings) {
            startActivity(new Intent(OfflineConfigurationActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /** Handles clicking on a player-type toggle.
     *
     * It manually toggles the image for the player type and and input type. Example: Human & name
     * input field toggle to Robot and difficulty buttons.
     */
    private class PlayerTypeToggleHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ImageButton playerTypeButton = (ImageButton)view;
            ViewGroup playerContainer = (ViewGroup)playerTypeButton.getParent();

            if(playerTypeButton.getTag().equals("human")) { //toggle to AI
                playerTypeButton.setTag("robot");
                playerTypeButton.setImageResource(R.drawable.ic_player_ai);

                //show AI difficulty and hide the name input field
                playerContainer.getChildAt(1).setVisibility(View.GONE);
                playerContainer.getChildAt(2).setVisibility(View.VISIBLE);

            } else { //toggle to human
                playerTypeButton.setTag("human");
                playerTypeButton.setImageResource(R.drawable.ic_player_human);

                //show name input field and hide the AI difficulty
                playerContainer.getChildAt(1).setVisibility(View.VISIBLE);
                playerContainer.getChildAt(2).setVisibility(View.GONE);
            }
        }
    }

    /** Handles clicking on any of the number of player buttons.
     *
     */
    private class NumberOfPlayerSelectionHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if(currentSelection.getId() != view.getId()) { //if the current button was not pressed

                resetPlayOptions(); //resets all of the player's options

                //shows and labels the name fields based on the number of players option
                switch(view.getId()) {
                    case R.id.twoPlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 2");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerNameContainer.setVisibility(View.GONE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.threePlayerButton:
                        orangePlayerNameContainer.setVisibility(View.GONE);
                        yellowPlayerEditText.setHint("Player 2");
                        yellowPlayerNameContainer.setVisibility(View.VISIBLE);
                        greenPlayerNameContainer.setVisibility(View.GONE);
                        bluePlayerEditText.setHint("Player 3");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.fourPlayerButton:
                        orangePlayerEditText.setHint("Player 2");
                        orangePlayerNameContainer.setVisibility(View.VISIBLE);
                        yellowPlayerNameContainer.setVisibility(View.GONE);
                        greenPlayerEditText.setHint("Player 3");
                        greenPlayerNameContainer.setVisibility(View.VISIBLE);
                        bluePlayerEditText.setHint("Player 4");
                        bluePlayerNameContainer.setVisibility(View.VISIBLE);
                        purplePlayerNameContainer.setVisibility(View.GONE);
                        break;
                    case R.id.sixPlayerButton:
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

        /** This method resets the player options for each player
         *
         */
        private void resetPlayOptions() {
            hideWarnings();

            //clears the names if another option is selected for number of players
            redPlayerEditText.setText("");
            purplePlayerEditText.setText("");
            bluePlayerEditText.setText("");
            greenPlayerEditText.setText("");
            yellowPlayerEditText.setText("");
            orangePlayerEditText.setText("");

            //resets the difficulties to easy
            redPlayerEasyButton.performClick();
            purplePlayerEasyButton.performClick();
            bluePlayerEasyButton.performClick();
            greenPlayerEasyButton.performClick();
            yellowPlayerEasyButton.performClick();
            orangePlayerEasyButton.performClick();

            //display AI options for all players except for the first (red) player
            if(!redPlayerTypeButton.getTag().equals("human"))
                redPlayerTypeButton.performClick();
            if(!purplePlayerTypeButton.getTag().equals("robot"))
                purplePlayerTypeButton.performClick();
            if(!bluePlayerTypeButton.getTag().equals("robot"))
                bluePlayerTypeButton.performClick();
            if(!greenPlayerTypeButton.getTag().equals("robot"))
                greenPlayerTypeButton.performClick();
            if(!yellowPlayerTypeButton.getTag().equals("robot"))
                yellowPlayerTypeButton.performClick();
            if(!orangePlayerTypeButton.getTag().equals("robot"))
                orangePlayerTypeButton.performClick();
        }
    }

    /** Trims whitespace from the name input field as focus is lost to ensure a name with spaces is
     * not accepted.
     *
     */
    private class PlayerNameFocusHandler implements OnFocusChangeListener {
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

    /** Handles clicking between the three toggle buttons for each AI difficulty.
     *
     * Gives the effect of the toggle buttons being linked like radio buttons by ensuring that only
     * one is selected at a time and that one if always selected.
     *
     */
    private class AiDifficultyButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ToggleButton difficultyButton = (ToggleButton)view;
            ViewGroup difficultyContainer = (ViewGroup)difficultyButton.getParent();

            //deselect the options
            for(int i=0; i<3; i++) {
                ((ToggleButton)difficultyContainer.getChildAt(i)).setChecked(false);
            }
            difficultyButton.setChecked(true); //check the current option
        }
    }

    /** Handles validation of inputted configuration and starting the offline game activity.
     *
     */
    private class StartGameHandler implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            ArrayList<Player> players = new ArrayList<Player>(6);

            hideWarnings();

            //if any of the visible, human input fields are empty, don't submit
            if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerTypeButton.getTag().equals("human") && redPlayerEditText.getText().toString().trim().equals("") ||
                    orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerTypeButton.getTag().equals("human") && orangePlayerEditText.getText().toString().trim().equals("") ||
                    yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerTypeButton.getTag().equals("human") && yellowPlayerEditText.getText().toString().trim().equals("") ||
                    greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerTypeButton.getTag().equals("human") && greenPlayerEditText.getText().toString().trim().equals("") ||
                    bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerTypeButton.getTag().equals("human") && bluePlayerEditText.getText().toString().trim().equals("") ||
                    purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerTypeButton.getTag().equals("human") && purplePlayerEditText.getText().toString().trim().equals("")) {

                //for each visible input field which is empty, display its warning symbol
                if(redPlayerNameContainer.getVisibility() == View.VISIBLE && redPlayerTypeButton.getTag().equals("human") && redPlayerEditText.getText().toString().trim().equals(""))
                    redPlayerError.setVisibility(View.VISIBLE);
                if(orangePlayerNameContainer.getVisibility() == View.VISIBLE && orangePlayerTypeButton.getTag().equals("human") && orangePlayerEditText.getText().toString().trim().equals(""))
                    orangePlayerError.setVisibility(View.VISIBLE);
                if(yellowPlayerNameContainer.getVisibility() == View.VISIBLE && yellowPlayerTypeButton.getTag().equals("human") && yellowPlayerEditText.getText().toString().trim().equals(""))
                    yellowPlayerError.setVisibility(View.VISIBLE);
                if(greenPlayerNameContainer.getVisibility() == View.VISIBLE && greenPlayerTypeButton.getTag().equals("human") && greenPlayerEditText.getText().toString().trim().equals(""))
                    greenPlayerError.setVisibility(View.VISIBLE);
                if(bluePlayerNameContainer.getVisibility() == View.VISIBLE && bluePlayerTypeButton.getTag().equals("human") && bluePlayerEditText.getText().toString().trim().equals(""))
                    bluePlayerError.setVisibility(View.VISIBLE);
                if(purplePlayerNameContainer.getVisibility() == View.VISIBLE && purplePlayerTypeButton.getTag().equals("human") && purplePlayerEditText.getText().toString().trim().equals(""))
                    purplePlayerError.setVisibility(View.VISIBLE);

            } else {
                Intent intent = new Intent(OfflineConfigurationActivity.this, GameActivity.class);

                // TODO: Handle Robots
                //TODO: START TEMP CODE
                if(redPlayerTypeButton.getTag().equals("robot"))
                    redPlayerEditText.setText("AI Robbie");
                if(purplePlayerTypeButton.getTag().equals("robot"))
                    purplePlayerEditText.setText("AI Roboto");
                if(bluePlayerTypeButton.getTag().equals("robot"))
                    bluePlayerEditText.setText("AI Roomba");
                if(greenPlayerTypeButton.getTag().equals("robot"))
                    greenPlayerEditText.setText("AI Roberto");
                if(yellowPlayerTypeButton.getTag().equals("robot"))
                    yellowPlayerEditText.setText("AI Robeye");
                if(orangePlayerTypeButton.getTag().equals("robot"))
                    orangePlayerEditText.setText("AI Robber");
                //TODO: END TEMP CODE

                if(redPlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(redPlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(redPlayerEditText.getText().toString(), Player.PlayerColor.RED));
                    else if(redPlayerTypeButton.getTag().equals("robot") && redPlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.RED));
                    else if(redPlayerTypeButton.getTag().equals("robot") && redPlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.RED));
                    else if(redPlayerTypeButton.getTag().equals("robot") && redPlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.RED));
                }

                if(purplePlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(purplePlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(purplePlayerEditText.getText().toString(), Player.PlayerColor.PURPLE));
                    else if(purplePlayerTypeButton.getTag().equals("robot") && purplePlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.PURPLE));
                    else if(purplePlayerTypeButton.getTag().equals("robot") && purplePlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.PURPLE));
                    else if(purplePlayerTypeButton.getTag().equals("robot") && purplePlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.PURPLE));
                }

                if(bluePlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(bluePlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(bluePlayerEditText.getText().toString(), Player.PlayerColor.BLUE));
                    else if(bluePlayerTypeButton.getTag().equals("robot") && bluePlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.BLUE));
                    else if(bluePlayerTypeButton.getTag().equals("robot") && bluePlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.BLUE));
                    else if(bluePlayerTypeButton.getTag().equals("robot") && bluePlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.BLUE));
                }

                if(greenPlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(greenPlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(greenPlayerEditText.getText().toString(), Player.PlayerColor.GREEN));
                    else if(greenPlayerTypeButton.getTag().equals("robot") && greenPlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.GREEN));
                    else if(greenPlayerTypeButton.getTag().equals("robot") && greenPlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.GREEN));
                    else if(greenPlayerTypeButton.getTag().equals("robot") && greenPlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.GREEN));
                }

                if(yellowPlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(yellowPlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(yellowPlayerEditText.getText().toString(), Player.PlayerColor.YELLOW));
                    else if(yellowPlayerTypeButton.getTag().equals("robot") && yellowPlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.YELLOW));
                    else if(yellowPlayerTypeButton.getTag().equals("robot") && yellowPlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.YELLOW));
                    else if(yellowPlayerTypeButton.getTag().equals("robot") && yellowPlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.YELLOW));
                }

                if(orangePlayerNameContainer.getVisibility() == View.VISIBLE) {
                    if(orangePlayerTypeButton.getTag().equals("human"))
                        players.add(new HumanPlayer(orangePlayerEditText.getText().toString(), Player.PlayerColor.ORANGE));
                    else if(orangePlayerTypeButton.getTag().equals("robot") && orangePlayerEasyButton.isChecked())
                        players.add(new AIPlayer("EASY", Player.PlayerColor.ORANGE));
                    else if(orangePlayerTypeButton.getTag().equals("robot") && orangePlayerMediumButton.isChecked())
                        players.add(new AIPlayer("MEDIUM", Player.PlayerColor.ORANGE));
                    else if(orangePlayerTypeButton.getTag().equals("robot") && orangePlayerHardButton.isChecked())
                        players.add(new AIPlayer("HARD", Player.PlayerColor.ORANGE));
                }

                GameBoard board = new CcGameBoard(players.size());
                intent.putExtra("GAME_STATE_MANAGER", (Parcelable)new GameStateManager(board, players));
                OfflineConfigurationActivity.this.finish();
                startActivity(intent);
            }
        }
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

    /** This function is used to bind all of the controls in the UI to variables.
     *
     */
    private void bindControls() {
        //Number of player buttons
        twoPlayerButton = (ToggleButton) findViewById(R.id.twoPlayerButton);
        threePlayerButton = (ToggleButton) findViewById(R.id.threePlayerButton);
        fourPlayerButton = (ToggleButton) findViewById(R.id.fourPlayerButton);
        sixPlayerButton = (ToggleButton) findViewById(R.id.sixPlayerButton);

        //Player info containers
        redPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineRedPlayerNameContainer);
        orangePlayerNameContainer = (LinearLayout)findViewById(R.id.offlineOrangePlayerNameContainer);
        yellowPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineYellowPlayerNameContainer);
        greenPlayerNameContainer = (LinearLayout)findViewById(R.id.offlineGreenPlayerNameContainer);
        bluePlayerNameContainer = (LinearLayout)findViewById(R.id.offlineBluePlayerNameContainer);
        purplePlayerNameContainer = (LinearLayout)findViewById(R.id.offlinePurplePlayerNameContainer);

        //Player type buttons
        redPlayerTypeButton = (ImageButton)findViewById(R.id.offlineRedPlayerTypeButton);
        orangePlayerTypeButton = (ImageButton)findViewById(R.id.offlineOrangePlayerTypeButton);
        yellowPlayerTypeButton = (ImageButton)findViewById(R.id.offlineYellowPlayerTypeButton);
        greenPlayerTypeButton = (ImageButton)findViewById(R.id.offlineGreenPlayerTypeButton);
        bluePlayerTypeButton = (ImageButton)findViewById(R.id.offlineBluePlayerTypeButton);
        purplePlayerTypeButton = (ImageButton)findViewById(R.id.offlinePurplePlayerTypeButton);

        //Input error images
        redPlayerError = (ImageView) findViewById(R.id.offlineRedPlayerError);
        orangePlayerError = (ImageView) findViewById(R.id.offlineOrangePlayerError);
        yellowPlayerError = (ImageView) findViewById(R.id.offlineYellowPlayerError);
        greenPlayerError = (ImageView) findViewById(R.id.offlineGreenPlayerError);
        bluePlayerError = (ImageView) findViewById(R.id.offlineBluePlayerError);
        purplePlayerError = (ImageView) findViewById(R.id.offlinePurplePlayerError);

        //Input fields for human players
        redPlayerEditText = (EditText) findViewById(R.id.offlineRedPlayerNameEditText);
        orangePlayerEditText = (EditText) findViewById(R.id.offlineOrangePlayerNameEditText);
        yellowPlayerEditText = (EditText) findViewById(R.id.offlineYellowPlayerNameEditText);
        greenPlayerEditText = (EditText) findViewById(R.id.offlineGreenPlayerNameEditText);
        bluePlayerEditText = (EditText) findViewById(R.id.offlineBluePlayerNameEditText);
        purplePlayerEditText = (EditText) findViewById(R.id.offlinePurplePlayerNameEditText);

        //Difficulty buttons for AI
        redPlayerEasyButton = (ToggleButton)findViewById(R.id.OfflineRedPlayerEasyButton);
        redPlayerMediumButton = (ToggleButton)findViewById(R.id.OfflineRedPlayerMediumButton);
        redPlayerHardButton = (ToggleButton)findViewById(R.id.OfflineRedPlayerHardButton);
        orangePlayerEasyButton = (ToggleButton)findViewById(R.id.OfflineOrangePlayerEasyButton);
        orangePlayerMediumButton = (ToggleButton)findViewById(R.id.OfflineOrangePlayerMediumButton);
        orangePlayerHardButton = (ToggleButton)findViewById(R.id.OfflineOrangePlayerHardButton);
        yellowPlayerEasyButton = (ToggleButton)findViewById(R.id.OfflineYellowPlayerEasyButton);
        yellowPlayerMediumButton = (ToggleButton)findViewById(R.id.OfflineYellowPlayerMediumButton);
        yellowPlayerHardButton = (ToggleButton)findViewById(R.id.OfflineYellowPlayerHardButton);
        greenPlayerEasyButton = (ToggleButton)findViewById(R.id.OfflineGreenPlayerEasyButton);
        greenPlayerMediumButton = (ToggleButton)findViewById(R.id.OfflineGreenPlayerMediumButton);
        greenPlayerHardButton = (ToggleButton)findViewById(R.id.OfflineGreenPlayerHardButton);
        bluePlayerEasyButton = (ToggleButton)findViewById(R.id.OfflineBluePlayerEasyButton);
        bluePlayerMediumButton = (ToggleButton)findViewById(R.id.OfflineBluePlayerMediumButton);
        bluePlayerHardButton = (ToggleButton)findViewById(R.id.OfflineBluePlayerHardButton);
        purplePlayerEasyButton = (ToggleButton)findViewById(R.id.OfflinePurplePlayerEasyButton);
        purplePlayerMediumButton = (ToggleButton)findViewById(R.id.OfflinePurplePlayerMediumButton);
        purplePlayerHardButton = (ToggleButton)findViewById(R.id.OfflinePurplePlayerHardButton);

        //Start game button
        startOfflineGameButton = (Button) findViewById(R.id.offlineGameActivityButton);
    }

    /** This function is used to bind all of the controls in the UI with listeners.
     *
     */
    private void bindHandlers() {
        twoPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        threePlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        fourPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());
        sixPlayerButton.setOnClickListener(new NumberOfPlayerSelectionHandler());

        redPlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());
        orangePlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());
        yellowPlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());
        greenPlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());
        bluePlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());
        purplePlayerTypeButton.setOnClickListener(new PlayerTypeToggleHandler());

        redPlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());
        orangePlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());
        yellowPlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());
        greenPlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());
        bluePlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());
        purplePlayerEditText.setOnFocusChangeListener(new PlayerNameFocusHandler());

        redPlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        redPlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        redPlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());
        orangePlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        orangePlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        orangePlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());
        yellowPlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        yellowPlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        yellowPlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());
        greenPlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        greenPlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        greenPlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());
        bluePlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        bluePlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        bluePlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());
        purplePlayerEasyButton.setOnClickListener(new AiDifficultyButtonHandler());
        purplePlayerMediumButton.setOnClickListener(new AiDifficultyButtonHandler());
        purplePlayerHardButton.setOnClickListener(new AiDifficultyButtonHandler());

        startOfflineGameButton.setOnClickListener(new StartGameHandler());
    }
}
