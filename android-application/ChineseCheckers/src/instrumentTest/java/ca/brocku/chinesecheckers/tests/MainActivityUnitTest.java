package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ClipData;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Button;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class MainActivityUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public MainActivityUnitTest() {
        super(MainActivity.class);
        testHelper = new TestHelpers();
    }

    public MainActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(MainActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
        testHelper = new TestHelpers();
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() {
        activityTestHelper();
        helpActivityTransitionTest();
        activityTestHelper();
    }

    public void helpActivityTransitionTest(){
        monitor = curInstruments.addMonitor(HelpActivity.class.getName(), null, false);

        curInstruments.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        curInstruments.invokeMenuActionSync(curAct, R.id.action_help, 0);

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull("Transition to HelpActivity Failed", curAct);

        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(MainActivity.class.getName(),null,false);

        new HelpActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,5000);
        assertNotNull("Transition Back to MainActivity Failed",curAct);
    }

    public void activityTestHelper(){
        assertNotNull("MainActivity Not Started", curAct);
        testHelper.ButtonTest(this, (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton), true);
    }

}
