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
 * Created by Main on 2/5/14.
 */
public class OfflineGameActivityUnitTest extends ActivityInstrumentationTestCase2<OfflineGameActivity> {

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;
    private String[] players;

    public OfflineGameActivityUnitTest() {
        super(OfflineGameActivity.class);
    }

    public OfflineGameActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(OfflineGameActivity.class);
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

        TextView offlineCurrentPlayerTextView = (TextView) curAct.findViewById(R.id.offlineCurrentPlayerTextView);
        assertNotNull("offlineCurrentPlayerTextView Not Found", offlineCurrentPlayerTextView);
        assertTrue("offlineCurrentPlayerTextView Not Visible", offlineCurrentPlayerTextView.isShown());
        assertEquals("offlineCurrentPlayerTextView Label Is Incorrect", "Red Bob", offlineCurrentPlayerTextView.getText().toString());

        Button offlineMoveResetButton = (Button) curAct.findViewById(R.id.offlineMoveResetButton);
        assertNotNull("offlineMoveResetButton Not Found", offlineMoveResetButton);
        assertTrue("offlineMoveResetButton Not Visible", offlineMoveResetButton.isShown());
        assertEquals("offlineMoveResetButton Text Is Incorrect", "Reset", offlineMoveResetButton.getText().toString());
        assertTrue("offlineMoveResetButton Not Clickable",offlineMoveResetButton.isClickable());

        Button offlineMoveDoneButton = (Button) curAct.findViewById(R.id.offlineMoveDoneButton);
        assertNotNull("offlineMoveDoneButton Not Found", offlineMoveDoneButton);
        assertTrue("offlineMoveDoneButton Not Visible", offlineMoveDoneButton.isShown());
        assertEquals("offlineMoveDoneButton Text Is Incorrect", "Done", offlineMoveDoneButton.getText().toString());
        assertTrue("offlineMoveDoneButton Not Clickable",offlineMoveDoneButton.isClickable());

    }

}
