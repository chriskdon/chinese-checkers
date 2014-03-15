package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.GameActivity;
import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/20/14.
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
        final Button offlineConfigurationActivityButton = (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(), null, false);
        TouchUtils.clickView(this, offlineConfigurationActivityButton);
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor, testHelper.timeoutForActivityTransition);
        assertNotNull("Transition to OfflineConfigurationActivity Failed", curAct);
        curInstruments = getInstrumentation();
        new OfflineConfigurationActivityUnitTest(curAct, curInstruments).testActivity();
        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(GameActivity.class.getName(), null, false);

        MCGIntegrationRunnable mCGIR = new MCGIntegrationRunnable(this);
        synchronized (mCGIR){
            curAct.runOnUiThread(mCGIR);
            ((Object) mCGIR).wait();
        }

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch (Exception e){}
        assertNotNull("Transition to GameActivity Failed", curAct);
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
                final ToggleButton offlineTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.twoPlayerButton);


                testHelper.ButtonTest(actInsTest, offlineTwoPlayerButton, true);
                offlineTwoPlayerButton.performClick();

                final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
                testHelper.EditTextTest(actInsTest,offlineRedPlayerNameEditText,true);

                testHelper.ImageButtonTest(actInsTest,(ImageButton)curAct.findViewById(R.id.offlineGreenPlayerTypeButton),true);
                ((ImageButton)curAct.findViewById(R.id.offlineGreenPlayerTypeButton)).performClick();

                final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
                testHelper.EditTextTest(actInsTest, offlineGreenPlayerNameEditText, true);

                final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
                testHelper.ButtonTest(actInsTest, offlineGameActivityButton, true);

                offlineRedPlayerNameEditText.setText("Red Bob");
                offlineGreenPlayerNameEditText.setText("Green Bob");
                offlineGameActivityButton.performClick();

                ((Object) this).notify();
            }
        }



    }

}
