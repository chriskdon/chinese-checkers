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
import android.widget.Button;

/**
 * Created by kubasub on 2/21/2014.
 */
public class OfflineGameResumeDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offline_game_resume_dialog, container, false);

        // Watch for button clicks.
        Button resumeButton = (Button)v.findViewById(R.id.offlineAcceptContinuationButton);
        Button quitButton = (Button)v.findViewById(R.id.offlineDeclineContinuationButton);

        resumeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), OfflineConfigurationActivity.class));
            }
        });

        return v;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return new AlertDialog.Builder(
//                new ContextThemeWrapper(getActivity(), R.style.Dialog))
//                .setTitle("Game in Progress")
//                .setMessage("Would you like to resume the saved game?")
//                .setPositiveButton("Resume",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        }
//                )
//                .setNegativeButton("New Game",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                onClickNewGame();
//                            }
//                        }
//                )
//                .create();
//    }

    private void onClickNewGame() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), OfflineConfigurationActivity.class));
    }
}