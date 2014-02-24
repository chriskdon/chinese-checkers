package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import ca.brocku.chinesecheckers.MainActivity;
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
        assertNotNull("MainActivity Not Started",curAct);
        testHelper.ButtonTest(this,(Button)curAct.findViewById(R.id.offlineConfigurationActivityButton),true);
    }

}
