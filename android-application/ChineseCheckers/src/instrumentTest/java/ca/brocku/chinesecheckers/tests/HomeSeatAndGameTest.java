package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.HotseatGameActivity;
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
        new MainActivityUnitTest(curAct, curInstruments).runTest();
        final Button hotseatConfigurationActivityButton = (Button) curAct.findViewById(R.id.hotseatConfigurationActivityButton);
        monitor = curInstruments.addMonitor(HotseatConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, hotseatConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to HotseatConfigurationActivity Failed", curAct);
        curInstruments = getInstrumentation();
        new HotseatConfigurationActivityUnitTest(curAct, curInstruments).runTest();
        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(HotseatGameActivity.class.getName(), null, false);
        final ToggleButton hotseatTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatTwoPlayerButton);
        assertNotNull("hotseatTwoPlayerButton Not Found", hotseatTwoPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatTwoPlayerButton.performClick();
                    }
                }
        );
        synchronized (this) {
            Thread.sleep(500);
        }

        final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
        assertNotNull("hotseatRedPlayerNameEditText Not Found", hotseatRedPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatRedPlayerNameEditText.isEnabled());
        final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
        assertNotNull("hotseatGreenPlayerNameEditText Not Found", hotseatGreenPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatGreenPlayerNameEditText.isEnabled());

        final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
        assertNotNull("hotseatGameActivityButton Not Found", hotseatGameActivityButton);
        assertTrue("hotseatGameActivityButton Not Clickable", hotseatGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatGameActivityButton.performClick();
                    }
                }
        );

        Thread.sleep(20000);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to HotseatGameActivity Failed", curAct);
        curInstruments = getInstrumentation();
        new HotseatGameActivityUnitTest(curAct, curInstruments).runTest();
    }

    public void tearDown() throws Exception {
        curAct.finish();
        super.tearDown();
    }

}
