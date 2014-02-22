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
 * Created by Main on 2/5/14.
 */
public class HotseatGameActivityUnitTest extends ActivityInstrumentationTestCase2<HotseatGameActivity> {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;
    private String[] players;

    public HotseatGameActivityUnitTest() {
        super(HotseatGameActivity.class);
    }

    public HotseatGameActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(HotseatGameActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
    }

    public void setUp() throws Exception {
        super.setUp();
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
        assertNotNull("hotseatCurrentPlayerTextView Not Found", hotseatCurrentPlayerTextView);
        assertTrue("hotseatCurrentPlayerTextView Not Visible", hotseatCurrentPlayerTextView.isShown());
        assertEquals("hotseatCurrentPlayerTextView Label Is Incorrect", "Red Bob", hotseatCurrentPlayerTextView.getText().toString());

        Button hotseatMoveResetButton = (Button) curAct.findViewById(R.id.hotseatMoveResetButton);
        assertNotNull("hotseatMoveResetButton Not Found", hotseatMoveResetButton);
        assertTrue("hotseatMoveResetButton Not Visible", hotseatMoveResetButton.isShown());
        assertEquals("hotseatMoveResetButton Text Is Incorrect", "Reset", hotseatMoveResetButton.getText().toString());
        assertTrue("hotseatMoveResetButton Not Clickable",hotseatMoveResetButton.isClickable());

        Button hotseatMoveDoneButton = (Button) curAct.findViewById(R.id.hotseatMoveDoneButton);
        assertNotNull("hotseatMoveDoneButton Not Found", hotseatMoveDoneButton);
        assertTrue("hotseatMoveDoneButton Not Visible", hotseatMoveDoneButton.isShown());
        assertEquals("hotseatMoveDoneButton Text Is Incorrect", "Done", hotseatMoveDoneButton.getText().toString());
        assertTrue("hotseatMoveDoneButton Not Clickable",hotseatMoveDoneButton.isClickable());

    }

}
