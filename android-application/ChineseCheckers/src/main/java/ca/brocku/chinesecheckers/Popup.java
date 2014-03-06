package ca.brocku.chinesecheckers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** This dialog is used system-wide as the default dialog.
 *
 */
public class Popup extends Dialog {
    private TextView title;
    private TextView message;
    private Button acceptButton;
    private Button declineButton;
    private boolean isBackButtonDisabled;

    public Popup(Context context) {
        super(context, R.style.CustomDialogTheme);

        setContentView(R.layout.fragment_dialog); //dialog layout

        title = (TextView)findViewById(R.id.dialogTitle);
        message = (TextView)findViewById(R.id.dialogMessage);
        acceptButton = (Button)findViewById(R.id.dialogAcceptButton);
        declineButton = (Button)findViewById(R.id.dialogCancelButton);

        isBackButtonDisabled = false;
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

    public Popup setAcceptButtonText(int text) {
        acceptButton.setText(text);
        return this;
    }

    public Popup setDeclineButtonText(int text) {
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

    public Popup disableBackButton(boolean isBackButtonDisabled) {
        this.isBackButtonDisabled = isBackButtonDisabled;
        return this;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public Button getDeclineButton() {
        return declineButton;
    }

    @Override
    public void onBackPressed() {
        if(!isBackButtonDisabled) { //don't disable the back key
            super.onBackPressed();
        }
    }

}
