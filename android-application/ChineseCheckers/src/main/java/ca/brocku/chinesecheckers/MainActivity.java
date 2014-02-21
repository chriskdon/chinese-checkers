package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
            startActivity(new Intent(MainActivity.this, OfflineConfigurationActivity.class));
        }
    }
}
