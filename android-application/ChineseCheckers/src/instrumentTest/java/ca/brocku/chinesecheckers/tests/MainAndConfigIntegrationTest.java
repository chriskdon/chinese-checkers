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
 * Created by Main on 2/20/14.
 */
public class MainAndConfigIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public MainAndConfigIntegrationTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        new MainActivityUnitTest(curAct,curInstruments).testActivity();
        final Button offlineConfigurationActivityButton = (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, offlineConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull("Transition to OfflineConfigurationActivity Failed", curAct);

        curInstruments = getInstrumentation();
        new OfflineConfigurationActivityUnitTest(curAct,curInstruments).testActivity();
    }

    public void tearDown() throws Exception {
        curAct.finish();
        super.tearDown();
    }

}
