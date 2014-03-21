package ca.brocku.chinesecheckers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.brocku.chinesecheckers.gamestate.Player;

/** This dialog is used system-wide as the default dialog.
 *
 */
public class Popup extends Dialog {
    private Context context;

    private FrameLayout root;
    private LinearLayout dialogContainer;
    private LinearLayout titleContainer;
    private TextView title;
    private LinearLayout bodyContainer;
    private TextView message;
    private LinearLayout buttonContainer;
    private Button acceptButton;
    private Button declineButton;
    private boolean isBackButtonDisabled;

    public Popup(final Context context) {
        super(context, R.style.CustomDialogTheme);

        setContentView(R.layout.fragment_dialog); //dialog layout

        this.context = context;

        //Bind Controls
        root = (FrameLayout)findViewById(android.R.id.content);
        dialogContainer = (LinearLayout)findViewById(R.id.dialogContainer);
        titleContainer = (LinearLayout)findViewById(R.id.dialogTitleContainer);
        title = (TextView)findViewById(R.id.dialogTitle);
        bodyContainer = (LinearLayout)findViewById(R.id.dialogBodyContainer);
        message = (TextView)findViewById(R.id.dialogMessage);
        buttonContainer = (LinearLayout)findViewById(R.id.dialogButtonContainer);
        acceptButton = (Button)findViewById(R.id.dialogAcceptButton);
        declineButton = (Button)findViewById(R.id.dialogCancelButton);

        isBackButtonDisabled = false;

        //Bind Handlers
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        root.setClickable(false);
        dialogContainer.setOnClickListener(new View.OnClickListener() {
            //Needed so that clicking the translucent background doesn't percolate up
            @Override
            public void onClick(View view) { }
        });
    }

    @Override
    public void onBackPressed() {
        if(!isBackButtonDisabled) { //don't disable the back key
            super.onBackPressed();
        }
    }

    public Popup hideTitle(boolean b) {
        if(b) {
            titleContainer.setVisibility(View.GONE);
        } else {
            titleContainer.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /** Hides buttons and enables clicking on the translucent background to dismiss the dialog.
     *
     * @param b
     * @return
     */
    public Popup hideButtons(boolean b) {
        if(b) {
            buttonContainer.setVisibility(View.GONE);
            root.setClickable(true);
        } else {
            buttonContainer.setVisibility(View.VISIBLE);
            root.setClickable(false);
        }
        return this;
    }

    public Popup setTitleText(int text) {
        title.setText(text);
        return this;
    }

    public Popup setTitleText(String text) {
        title.setText(text);
        return this;
    }

    public Popup setMessageText(int text) {
        message.setText(text);
        return this;
    }

    public Popup setMessageText(String text) {
        message.setText(text);
        return this;
    }

    public Popup setAcceptButtonText(int text) {
        acceptButton.setText(text);
        return this;
    }

    public Popup setAcceptButtonText(String text) {
        acceptButton.setText(text);
        return this;
    }

    public Popup setDeclineButtonText(int text) {
        declineButton.setText(text);
        return this;
    }

    public Popup setDeclineButtonText(String text) {
        declineButton.setText(text);
        return this;
    }

    public Popup setAcceptClickListener(Button.OnClickListener listener) {
        acceptButton.setOnClickListener(listener);
        return this;
    }

    public Popup setCancelClickListener(Button.OnClickListener listener) {
        declineButton.setOnClickListener(listener);
        return this;
    }

    public Popup enableNewGameOptions() {
        message.setVisibility(View.GONE);

        View newGameOptionsView = View.inflate(context, R.layout.fragment_online_new_game, bodyContainer);

        return this;
    }

    public int getNumberOfPlayers() {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.numberOfPlayersRadioGroup);
        int numberOfPlayers = -1;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.twoPlayerButton:
                numberOfPlayers = 2;
                break;
            case R.id.threePlayerButton:
                numberOfPlayers = 3;
                break;
            case R.id.fourPlayerButton:
                numberOfPlayers = 4;
                break;
            case R.id.sixPlayerButton:
                numberOfPlayers = 6;
                break;
        }
        return numberOfPlayers;
    }

    public Popup enablePlayerList(Player[] players) {
        message.setVisibility(View.GONE);

        View playerListView = View.inflate(context, R.layout.fragment_player_list, bodyContainer);

        for(Player player : players) {
            switch (player.getPlayerColor()) {
                case RED:
                    playerListView.findViewById(R.id.playerListRedContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListRedTextView)).setText(player.getName());
                    break;
                case PURPLE:
                    playerListView.findViewById(R.id.playerListPurpleContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListPurpleTextView)).setText(player.getName());
                    break;
                case BLUE:
                    playerListView.findViewById(R.id.playerListBlueContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListBlueTextView)).setText(player.getName());
                    break;
                case GREEN:
                    playerListView.findViewById(R.id.playerListGreenContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListGreenTextView)).setText(player.getName());
                    break;
                case YELLOW:
                    playerListView.findViewById(R.id.playerListYellowContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListYellowTextView)).setText(player.getName());
                    break;
                case ORANGE:
                    playerListView.findViewById(R.id.playerListOrangeContainer).setVisibility(View.VISIBLE);
                    ((TextView)playerListView.findViewById(R.id.playerListOrangeTextView)).setText(player.getName());
                    break;
            }
        }

        return this;
    }

    public Popup disableBackButton(boolean isBackButtonDisabled) {
        this.isBackButtonDisabled = isBackButtonDisabled;
        return this;
    }

    public LinearLayout getBodyContainer() {
        return bodyContainer;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public Button getDeclineButton() {
        return declineButton;
    }
}
