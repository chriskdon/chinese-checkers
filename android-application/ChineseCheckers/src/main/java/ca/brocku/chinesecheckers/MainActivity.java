package ca.brocku.chinesecheckers;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/** This is the activity for the home screen of Chinese Checkers.
 *
 * It controls navigation to the different parts of the application including the different game
 * modes and user settings.
 *
 */
public class MainActivity extends Activity {
    private Button hotseatActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Controls
        hotseatActivityButton = (Button)findViewById(R.id.hotseatConfigurationActivityButton);

        //Bing Handlers
        hotseatActivityButton.setOnClickListener(new HotseatActivityButtonHandler());

    }

    /** Handles clicking on the "Hotseat" game button.
     *
     */
    private class HotseatActivityButtonHandler implements Button.OnClickListener {

        /** OnClick event which starts the HotseatConfigurationActivity activity.
         *
         * @param view the Hotseat button
         */
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, HotseatConfigurationActivity.class));
        }
    }
}
