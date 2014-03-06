package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;

import ca.brocku.chinesecheckers.gamestate.GameStateManager;

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
            File savedOfflineGame = getFileStreamPath(GameStateManager.SERIALIZED_FILENAME); //get the serialized file

            if(savedOfflineGame.exists()) { //if there is a saved game file
                try {
                    //Load the GameStateManager from storage
                    FileInputStream fis = openFileInput(GameStateManager.SERIALIZED_FILENAME);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    GameStateManager gameStateManager = (GameStateManager)ois.readObject();
                    ois.close();
                    fis.close();

                    //Bundle information and start the OfflineGameActivity
                    Intent intent = new Intent(MainActivity.this, OfflineGameActivity.class);
                    intent.putExtra("GAME_STATE_MANAGER", (Parcelable)gameStateManager); //Store GameStateManager
                    intent.putExtra("SAVED_GAME", true); //Store flag that this is a saved game
                    startActivity(intent);

                } catch (Exception e) {
                    savedOfflineGame.delete(); //delete the saved game as it couldn't be loaded
                    e.printStackTrace();
                }
            } else { //there is no saved game, go to configuration for the offline game
                startActivity(new Intent(MainActivity.this, OfflineConfigurationActivity.class));
            }
        }
    }
}
