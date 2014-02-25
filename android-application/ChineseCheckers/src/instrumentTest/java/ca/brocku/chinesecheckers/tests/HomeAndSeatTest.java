package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/3/14.
 */

public class HomeAndSeatTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;
    public HomeAndSeatTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = (MainActivity)getActivity();
        curInstruments = getInstrumentation();
    }

    public void testPreConditions() {
        assertNotNull("MainActivity Not Started", curAct);
//        new MainActivityUnitTest();
    }

    public void testButton() {
        final Button offlineConfigurationActivityButton = (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(),null,false);
        assertTrue("offlineConfigurationActivityButton Did Not Respond To Click",offlineConfigurationActivityButton.isClickable());
        TouchUtils.clickView(this, offlineConfigurationActivityButton);
        tTransitionToConfigAct();
//        curAct.runOnUiThread(
//                new Runnable() {
//                    public void run() {
//                        assertNotNull("offlineConfigurationActivityButton Not Available", offlineConfigurationActivityButton);
//                        assertTrue("offlineConfigurationActivityButton Did Not Respond To Click ",offlineConfigurationActivityButton.performClick());
//                    }
//                }
//        );
    }

    public void tTransitionToConfigAct(){
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor,1);
        assertNotNull("Transition to OfflineConfigurationActivity Failed",curAct);
//        new OfflineConfigurationActivityUnitTest();
    }

}