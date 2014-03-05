package ca.brocku.chinesecheckers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

/**
 * Created by kubasub on 2/22/2014.
 */
@SuppressLint("all")
public class OfflineGameEndDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(
                new ContextThemeWrapper(getActivity(), R.style.Dialog))
                .setTitle("Kuba Wins!")
                .setMessage("Would you like to start a new game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onNewGameClick();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onNoNewGameClick();
                    }
                })
                .create();
    }

    private void onNewGameClick() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), OfflineConfigurationActivity.class));
    }

    private void onNoNewGameClick() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
