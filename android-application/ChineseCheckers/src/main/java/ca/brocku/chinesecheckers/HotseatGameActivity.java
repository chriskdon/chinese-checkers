package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import ca.brocku.chinesecheckers.uiengine.BoardUiEngine;

public class HotseatGameActivity extends Activity {
    private String[] players; //an array of the players' names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotseat_game);

        // Make sure variables are setup before creating fragment
        players = getIntent().getExtras().getStringArray("PLAYER_NAMES");


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(players))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotseat_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private TextView currentPlayerName;
        private Button resetMove;
        private Button doneMove;
        private String[] playerNames;

        public PlaceholderFragment() {

        }

        public PlaceholderFragment(String[] playerNames) {
            this.playerNames = playerNames;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hotseat_game, container, false);

            // Setup Game Board
            BoardUiEngine gameBoardUi = (BoardUiEngine)
                    rootView.findViewById(R.id.gameBoardSurface);

            gameBoardUi.setPlayerCount(playerNames.length);

            //Bind Controls
            currentPlayerName = (TextView)rootView.findViewById(R.id.hotseatCurrentPlayerTextView);
            resetMove = (Button)rootView.findViewById(R.id.hotseatMoveResetButton);
            doneMove = (Button)rootView.findViewById(R.id.hotseatMoveDoneButton);

            //Bind Handlers
            resetMove.setOnClickListener(new ResetMoveHanlder());
            doneMove.setOnClickListener(new DoneMoveHandler());

            currentPlayerName.setText(playerNames[0]);

            return rootView;
        }

        private class ResetMoveHanlder implements View.OnClickListener {
            @Override
            public void onClick(View view) {

            }
        }

        private class DoneMoveHandler implements View.OnClickListener {

            @Override
            public void onClick(View view) {

            }
        }
    }

}
