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
 * Created by Main on 2/23/14.
 */
public class MainAndConfigAndGameIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public MainAndConfigAndGameIntegrationTest() {
        super(MainActivity.class);
        testHelper = new TestHelpers();
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        new MainActivityUnitTest(curAct, curInstruments).testActivity();
        final Button hotseatConfigurationActivityButton = (Button) curAct.findViewById(R.id.hotseatConfigurationActivityButton);
        monitor = curInstruments.addMonitor(HotseatConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, hotseatConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 30);
        assertNotNull("Transition to HotseatConfigurationActivity Failed", curAct);
        curInstruments = getInstrumentation();
        new OfflineConfigurationActivityUnitTest(curAct, curInstruments).testActivity();
        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(HotseatGameActivity.class.getName(), null, false);

        MCGIntegrationRunnable mCGIR = new MCGIntegrationRunnable(this);
        synchronized (mCGIR){
            curAct.runOnUiThread(mCGIR);
            mCGIR.wait();
        }

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, 1000);
        assertNotNull("Transition to HotseatGameActivity Failed", curAct);
        try{Thread.sleep(1000);}catch (Exception e){}
        curInstruments = getInstrumentation();
        new OfflineGameActivityUnitTest(curAct, curInstruments).testActivity();
    }

    public void tearDown() throws Exception {
        curAct.finish();
        super.tearDown();
    }

    class MCGIntegrationRunnable implements Runnable{

        ActivityInstrumentationTestCase2 actInsTest;
        public MCGIntegrationRunnable(ActivityInstrumentationTestCase2 actInsTest){
            this.actInsTest = actInsTest;
        }
        public void run() {
            synchronized (this){
                final ToggleButton hotseatTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatTwoPlayerButton);
                testHelper.ButtonTest(actInsTest, hotseatTwoPlayerButton, true);
                hotseatTwoPlayerButton.performClick();

                final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
                testHelper.EditTextTest(actInsTest,hotseatRedPlayerNameEditText,true);
                final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
                testHelper.EditTextTest(actInsTest, hotseatGreenPlayerNameEditText, true);

                final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
                testHelper.ButtonTest(actInsTest, hotseatGameActivityButton, true);

                hotseatRedPlayerNameEditText.setText("Red Bob");
                hotseatGreenPlayerNameEditText.setText("Green Bob");
                hotseatGameActivityButton.performClick();

                notify();
            }
        }



    }

}
