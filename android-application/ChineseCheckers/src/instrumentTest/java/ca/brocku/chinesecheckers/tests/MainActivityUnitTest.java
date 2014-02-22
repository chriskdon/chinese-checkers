package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;

import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class MainActivityUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public MainActivityUnitTest() {
        super(MainActivity.class);
    }

    public MainActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(MainActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() {
        assertNotNull("MainActivity Not Started", curAct);
        final Button offlineConfigurationActivityButton = (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton);
        assertTrue("offlineConfigurationActivityButton Did Not Respond To Click", offlineConfigurationActivityButton.isClickable());
    }

}
