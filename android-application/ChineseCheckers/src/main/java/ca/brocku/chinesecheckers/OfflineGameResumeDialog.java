package ca.brocku.chinesecheckers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offline_game_resume, container, false);

        // Watch for button clicks.
        //Button button = (Button)v.findViewById(R.id.show);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // When button is clicked, call up to owning activity.
//                //((FragmentDialog)getActivity()).showDialog();
//            }
//        });

        return v;
    }
}
