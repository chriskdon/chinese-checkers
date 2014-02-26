package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.OfflineGameActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class OfflineGameActivityUnitTest extends ActivityInstrumentationTestCase2<OfflineGameActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;
    private String[] players;

    public OfflineGameActivityUnitTest() {
        super(OfflineGameActivity.class);
        testHelper = new TestHelpers();
    }

    public OfflineGameActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(OfflineGameActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
        testHelper = new TestHelpers();
    }

    public void setUp() throws Exception {
        super.setUp();
        testHelper = new TestHelpers();
        Intent intent = new Intent();
        String[] players = new String[]{
                "Red Bob",
                "Orange Bob",
                "Yellow Bob",
                "Green Bob",
                "Blue Bob",
                "Purple Bob"};
        intent.putExtra("PLAYER_NAMES", players);
        setActivityIntent(intent);
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
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
        monitor = curInstruments.addMonitor(OfflineGameActivity.class.getName(),null,false);

        new HelpActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,5000);
        assertNotNull("Transition Back to OfflineGameActivity Failed",curAct);
    }

    public void activityTestHelper(){
        TextView offlineCurrentPlayerTextView = (TextView) curAct.findViewById(R.id.offlineCurrentPlayerTextView);
        testHelper.TextViewTest(this,offlineCurrentPlayerTextView,true,"Red Bob");

        Button offlineMoveResetButton = (Button) curAct.findViewById(R.id.offlineMoveResetButton);
        testHelper.ButtonTest(this,offlineMoveResetButton,true);

        Button offlineMoveDoneButton = (Button) curAct.findViewById(R.id.offlineMoveDoneButton);
        testHelper.ButtonTest(this,offlineMoveDoneButton,true);
    }

}
