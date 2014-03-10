package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kubasub on 2014-03-06.
 */
public class OnlineListActivity extends Activity {
    private Button newGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_list);

        //Bind Controls
        newGameButton = (Button)findViewById(R.id.onlineNewGameButton);

        //Bind Handlers
        newGameButton.setOnClickListener(new NewGameHandler());
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
            startActivity(new Intent(OnlineListActivity.this, HelpActivity.class));
        } else if(id == R.id.action_settings) {
            startActivity(new Intent(OnlineListActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /** This handler creates and manages a new-online-game dialog. The dialog contains radio buttons
     * for the number of players.
     *
     * When join is clicked, this class makes a request to the server to be added to a game with the
     * number of players which was selected.
     *
     */
    private class NewGameHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final Popup newGamePopup = new Popup(OnlineListActivity.this);

            newGamePopup
                    .setTitleText("New Game")
                    .enableNewGameOptions()
                    .setAcceptButtonText("Join")
                    .setDeclineButtonText("Cancel")
                    .setCancelClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            newGamePopup.dismiss();
                        }
                    })
                    .setAcceptClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int numberOfPlayers = newGamePopup.getNumberOfPlayers();

                            //TODO: make server call to join game, remove Toast
                            Toast.makeText(OnlineListActivity.this, Integer.toString(numberOfPlayers), Toast.LENGTH_LONG).show();

                            newGamePopup.dismiss();
                        }
                    })
                    .show();
        }
    }
}
