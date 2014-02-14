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
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        //new MainActivityUnitTest(curAct,curInstruments).runTest();
        final Button hotseatConfigurationActivityButton = (Button) curAct.findViewById(R.id.hotseatConfigurationActivityButton);
        monitor = curInstruments.addMonitor(HotseatConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, hotseatConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to HotseatConfigurationActivity Failed", curAct);
        curInstruments = getInstrumentation();
        //new HotseatConfigurationActivityUnitTest(curAct,curInstruments).runTest();
    }

    public void tearDown() throws Exception {
        curAct.finish();
        super.tearDown();
    }

}