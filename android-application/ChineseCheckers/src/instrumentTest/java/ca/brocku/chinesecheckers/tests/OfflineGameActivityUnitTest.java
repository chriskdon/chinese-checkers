package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import ca.brocku.chinesecheckers.HotseatGameActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class OfflineGameActivityUnitTest extends ActivityInstrumentationTestCase2<HotseatGameActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;
    private String[] players;

    public OfflineGameActivityUnitTest() {
        super(HotseatGameActivity.class);
        testHelper = new TestHelpers();
    }

    public OfflineGameActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(HotseatGameActivity.class);
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

        TextView hotseatCurrentPlayerTextView = (TextView) curAct.findViewById(R.id.hotseatCurrentPlayerTextView);
        testHelper.TextViewTest(this,hotseatCurrentPlayerTextView,true,"Red Bob");

        Button hotseatMoveResetButton = (Button) curAct.findViewById(R.id.hotseatMoveResetButton);
        testHelper.ButtonTest(this,hotseatMoveResetButton,true);

        Button hotseatMoveDoneButton = (Button) curAct.findViewById(R.id.hotseatMoveDoneButton);
        testHelper.ButtonTest(this,hotseatMoveDoneButton,true);

    }

}
