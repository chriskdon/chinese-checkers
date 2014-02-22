package ca.brocku.chinesecheckers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kubasub on 2/21/2014.
 */
public class OfflineGameResumeDialog extends DialogFragment {


    public static OfflineGameResumeDialog newInstance(int title) {
        OfflineGameResumeDialog dialog = new OfflineGameResumeDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(
                new ContextThemeWrapper(getActivity(), R.style.Dialog))
                .setTitle("Game in Progress")
                .setMessage("Would you like to resume the saved game?")
                .setPositiveButton("Resume",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .setNegativeButton("New Game",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                onClickNewGame();
                            }
                        }
                )
                .create();
    }

    private void onClickNewGame() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), OfflineConfigurationActivity.class));
    }
}
