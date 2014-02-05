package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.widget.Button;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/3/14.
 */

public class HomeAndSeatTest extends InstrumentationTestCase {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;


    protected void setUp() throws Exception {
        curInstruments = getInstrumentation();
        monitor = curInstruments.addMonitor(MainActivity.class.getName(),null,false);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(curInstruments.getTargetContext(),MainActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        curInstruments.startActivitySync(intent);
        curInstruments = getInstrumentation();
        curAct = curInstruments.waitForMonitorWithTimeout(monitor,3);
    }

    public void testPreConditions() {
        assertNotNull("MainActivity Not Started", curAct);
//        new MainActivityUnitTest();
    }

    public void testButton() {
        final Button hotseatConfigurationActivityButton = (Button) curAct.findViewById(R.id.hotseatConfigurationActivityButton);
        monitor = curInstruments.addMonitor(HotseatConfigurationActivity.class.getName(),null,false);
        assertTrue("hotseatConfigurationActivityButton Did Not Respond To Click", hotseatConfigurationActivityButton.isClickable());
        TouchUtils.clickView(this, hotseatConfigurationActivityButton);
//        transitionTestingToConfigActivity();
//        curAct.runOnUiThread(
//                new Runnable() {
//                    public void run() {
//                        assertNotNull("hotseatConfigurationActivityButton Not Available", hotseatConfigurationActivityButton);
//                        assertTrue("hotseatConfigurationActivityButton Did Not Respond To Click ",hotseatConfigurationActivityButton.performClick());
//                    }
//                }
//        );
    }

    public void transitionTestingToConfigActivity(){
        curAct = getInstrumentation().waitForMonitorWithTimeout(monitor,3); //
        assertNotNull("Transition to HotseatConfigurationActivity Failed",curAct);
//        new HotseatConfigurationActivityUnitTest();
    }

}