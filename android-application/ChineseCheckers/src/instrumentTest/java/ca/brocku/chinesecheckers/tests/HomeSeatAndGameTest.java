package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.GameActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class HomeSeatAndGameTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public HomeSeatAndGameTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        //new MainActivityUnitTest(curAct, curInstruments).runTest();
        final Button offlineConfigurationActivityButton = (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, offlineConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to OfflineConfigurationActivity Failed", curAct);
        curInstruments = getInstrumentation();
//        new OfflineConfigurationActivityUnitTest(curAct, curInstruments).runTest();
        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(GameActivity.class.getName(), null, false);
        final ToggleButton offlineTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.twoPlayerButton);
        assertNotNull("offlineTwoPlayerButton Not Found", offlineTwoPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineTwoPlayerButton.performClick();
                    }
                }
        );
        synchronized (this) {
            Thread.sleep(500);
        }

        final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
        assertNotNull("offlineRedPlayerNameEditText Not Found", offlineRedPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineRedPlayerNameEditText.isEnabled());
        final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
        assertNotNull("offlineGreenPlayerNameEditText Not Found", offlineGreenPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineGreenPlayerNameEditText.isEnabled());

        final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
        assertNotNull("offlineGameActivityButton Not Found", offlineGameActivityButton);
        assertTrue("offlineGameActivityButton Not Clickable", offlineGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineGameActivityButton.performClick();
                    }
                }
        );

        Thread.sleep(20000);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to GameActivity Failed", curAct);
        curInstruments = getInstrumentation();
       // new OfflineGameActivityUnitTest(curAct, curInstruments).runTest();
    }

    public void tearDown() throws Exception {
        curAct.finish();
        super.tearDown();
    }

}
