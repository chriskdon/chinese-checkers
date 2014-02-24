package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

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

        TextView offlineCurrentPlayerTextView = (TextView) curAct.findViewById(R.id.offlineCurrentPlayerTextView);
        testHelper.TextViewTest(this,offlineCurrentPlayerTextView,true,"Red Bob");

        Button offlineMoveResetButton = (Button) curAct.findViewById(R.id.offlineMoveResetButton);
        testHelper.ButtonTest(this,offlineMoveResetButton,true);

        Button offlineMoveDoneButton = (Button) curAct.findViewById(R.id.offlineMoveDoneButton);
        testHelper.ButtonTest(this,offlineMoveDoneButton,true);

    }

}
