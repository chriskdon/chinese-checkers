package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/** This is the activity for the home screen of Chinese Checkers.
 *
 * It controls navigation to the different parts of the application including the different game
 * modes and user settings.
 *
 */
public class MainActivity extends Activity {
    private Button offlineActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Controls
        offlineActivityButton = (Button)findViewById(R.id.offlineConfigurationActivityButton);

        //Bind Handlers
        offlineActivityButton.setOnClickListener(new OfflineActivityButtonHandler());

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
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /** Handles clicking on the "Offline" game button.
     *
     */
    private class OfflineActivityButtonHandler implements Button.OnClickListener {

        /** OnClick event which starts the OfflineConfigurationActivity activity.
         *
         * @param view the Offline button
         */
        @Override
        public void onClick(View view) {

            if(true) { //TODO change to: if there is a saved game
                String[] players = new String[]{"Bobby", "Robby", "Tobby", "Sobby", "Cobby", "Wobby"};
                Intent intent = new Intent(MainActivity.this, OfflineGameActivity.class);
                intent.putExtra("PLAYER_NAMES", players);

                startActivity(intent);

            } else { //configure a new game
                startActivity(new Intent(MainActivity.this, OfflineConfigurationActivity.class));
            }
        }
    }
}
