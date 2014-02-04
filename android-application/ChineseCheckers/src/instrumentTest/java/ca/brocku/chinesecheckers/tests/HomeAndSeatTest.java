package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
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
        final Button hotseatConfigurationActivityButton = (Button) curAct.findViewById(R.id.hotseatConfigurationActivityButton);
        monitor = curInstruments.addMonitor(HotseatConfigurationActivity.class.getName(),null,false);
        assertTrue("hotseatConfigurationActivityButton Did Not Respond To Click",hotseatConfigurationActivityButton.isClickable());
        TouchUtils.clickView(this, hotseatConfigurationActivityButton);
        tTransitionToConfigAct();
//        curAct.runOnUiThread(
//                new Runnable() {
//                    public void run() {
//                        assertNotNull("hotseatConfigurationActivityButton Not Available", hotseatConfigurationActivityButton);
//                        assertTrue("hotseatConfigurationActivityButton Did Not Respond To Click ",hotseatConfigurationActivityButton.performClick());
//                    }
//                }
//        );
    }

    public void tTransitionToConfigAct(){
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor,1);
        assertNotNull("Transition to HotseatConfigurationActivity Failed",curAct);
//        new HotseatConfigurationActivityUnitTest();
    }

}