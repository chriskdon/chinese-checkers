package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class OfflineConfigurationActivityUnitTest extends ActivityInstrumentationTestCase2<OfflineConfigurationActivity> {

    private static final long sleepTime = 10000;
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public OfflineConfigurationActivityUnitTest() {
        super(OfflineConfigurationActivity.class);
    }

    public OfflineConfigurationActivityUnitTest(Activity curAct, Instrumentation curInstruments) {
        super(OfflineConfigurationActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        assertNotNull("OfflineConfigurationActivity Not Started", curAct);
        twoPlayerConfigTests();
        threePlayerConfigTests();
        fourPlayerConfigTests();
        sixPlayerConfigTests();
    }

    public void twoPlayerConfigTests() throws Exception {
        final ToggleButton offlineTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineTwoPlayerButton);
        assertNotNull("offlineTwoPlayerButton Not Found", offlineTwoPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineTwoPlayerButton.performClick();
                    }
                }
        );

        final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
        assertNotNull("offlineRedPlayerNameEditText Not Found", offlineRedPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineRedPlayerNameEditText.isEnabled());
        final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
        assertNotNull("offlineGreenPlayerNameEditText Not Found", offlineGreenPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineGreenPlayerNameEditText.isEnabled());

        final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
        final ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);

        final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
        assertNotNull("offlineGameActivityButton Not Found", offlineGameActivityButton);
        assertTrue("offlineGameActivityButton Not Clickable", offlineGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {

                        offlineRedPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());

                    }
                }
        );

    }

    public void threePlayerConfigTests() throws Exception {
        final ToggleButton offlineThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineThreePlayerButton);
        assertNotNull("offlineThreePlayerButton Not Found", offlineThreePlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineThreePlayerButton.performClick();
                    }
                }
        );

        final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
        assertNotNull("offlineRedPlayerNameEditText Not Found", offlineRedPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineRedPlayerNameEditText.isEnabled());
        final EditText offlineYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineYellowPlayerNameEditText);
        assertNotNull("offlineYellowPlayerNameEditText Not Found", offlineYellowPlayerNameEditText);
        assertTrue("offlineYellowPlayerNameEditText Not Enabled", offlineYellowPlayerNameEditText.isEnabled());
        final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
        assertNotNull("offlineBluePlayerNameEditText Not Found", offlineBluePlayerNameEditText);
        assertTrue("offlineBluePlayerNameEditText Not Enabled", offlineBluePlayerNameEditText.isEnabled());

        final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
        final ImageView offlineYellowPlayerError = (ImageView) curAct.findViewById(R.id.offlineYellowPlayerError);
        final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

        final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
        assertNotNull("offlineGameActivityButton Not Found", offlineGameActivityButton);
        assertTrue("offlineGameActivityButton Not Clickable", offlineGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {

                        offlineRedPlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineYellowPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                    }
                }
        );
    }

    public void fourPlayerConfigTests() throws Exception {
        final ToggleButton offlineFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineFourPlayerButton);
        assertNotNull("offlineFourPlayerButton Not Found", offlineFourPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineFourPlayerButton.performClick();
                    }
                }
        );

        final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
        assertNotNull("offlineRedPlayerNameEditText Not Found", offlineRedPlayerNameEditText);
        assertTrue("offlineRedPlayerNameEditText Not Enabled", offlineRedPlayerNameEditText.isEnabled());
        final EditText offlineOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineOrangePlayerNameEditText);
        assertNotNull("offlineOrangePlayerNameEditText Not Found", offlineOrangePlayerNameEditText);
        assertTrue("offlineOrangePlayerNameEditText Not Enabled", offlineOrangePlayerNameEditText.isEnabled());
        final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
        assertNotNull("offlineGreenPlayerNameEditText Not Found", offlineGreenPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineGreenPlayerNameEditText.isEnabled());
        final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
        assertNotNull("offlineBluePlayerNameEditText Not Found", offlineBluePlayerNameEditText);
        assertTrue("offlineBluePlayerNameEditText Not Enabled", offlineBluePlayerNameEditText.isEnabled());

        final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
        final ImageView offlineOrangePlayerError = (ImageView) curAct.findViewById(R.id.offlineOrangePlayerError);
        final ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);
        final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

        final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
        assertNotNull("offlineGameActivityButton Not Found", offlineGameActivityButton);
        assertTrue("offlineGameActivityButton Not Clickable", offlineGameActivityButton.isClickable());

        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());
//
                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());
                    }
                }
        );
    }

    public void sixPlayerConfigTests() throws Exception {
        final ToggleButton offlineSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineSixPlayerButton);
        assertNotNull("offlineSixPlayerButton Not Found", offlineSixPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineSixPlayerButton.performClick();
                    }
                }
        );

        synchronized (this) { Thread.sleep(sleepTime); }
        final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
        assertNotNull("offlineRedPlayerNameEditText Not Found", offlineRedPlayerNameEditText);
        assertTrue("offlineRedPlayerNameEditText Not Enabled", offlineRedPlayerNameEditText.isEnabled());
        assertEquals("offlineRedPlayerNameEditText Initalized With Text","",offlineRedPlayerNameEditText.getText().toString());
        assertTrue("offlineRedPlayerNameEditText Not Visible", offlineRedPlayerNameEditText.isShown());
        final EditText offlineOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineOrangePlayerNameEditText);
        assertNotNull("offlineOrangePlayerNameEditText Not Found", offlineOrangePlayerNameEditText);
        assertTrue("offlineOrangePlayerNameEditText Not Enabled", offlineOrangePlayerNameEditText.isEnabled());
        assertEquals("offlineOrangePlayerNameEditText Initalized With Text","",offlineOrangePlayerNameEditText.getText().toString());
        assertTrue("offlineOrangePlayerNameEditText Not Visible", offlineOrangePlayerNameEditText.isShown());
        final EditText offlinePurplePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlinePurplePlayerNameEditText);
        assertNotNull("offlinePurplePlayerNameEditText Not Found", offlinePurplePlayerNameEditText);
        assertTrue("offlinePurplePlayerNameEditText Not Enabled", offlinePurplePlayerNameEditText.isEnabled());
        assertEquals("offlinePurplePlayerNameEditText Initalized With Text","",offlinePurplePlayerNameEditText.getText().toString());
        assertTrue("offlinePurplePlayerNameEditText Not Visible", offlinePurplePlayerNameEditText.isShown());
        final EditText offlineYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineYellowPlayerNameEditText);
        assertNotNull("offlineYellowPlayerNameEditText Not Found", offlineYellowPlayerNameEditText);
        assertTrue("offlineYellowPlayerNameEditText Not Enabled", offlineYellowPlayerNameEditText.isEnabled());
        assertEquals("offlineYellowPlayerNameEditText Initalized With Text","",offlineYellowPlayerNameEditText.getText().toString());
        assertTrue("offlineYellowPlayerNameEditText Not Visible", offlineYellowPlayerNameEditText.isShown());
        final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
        assertNotNull("offlineGreenPlayerNameEditText Not Found", offlineGreenPlayerNameEditText);
        assertTrue("offlineGreenPlayerNameEditText Not Enabled", offlineGreenPlayerNameEditText.isEnabled());
        assertEquals("offlineGreenPlayerNameEditText Initalized With Text","",offlineGreenPlayerNameEditText.getText().toString());
        assertTrue("offlineGreenPlayerNameEditText Not Visible", offlineGreenPlayerNameEditText.isShown());
        final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
        assertNotNull("offlineBluePlayerNameEditText Not Found", offlineBluePlayerNameEditText);
        assertTrue("offlineBluePlayerNameEditText Not Enabled", offlineBluePlayerNameEditText.isEnabled());
        assertEquals("offlineBluePlayerNameEditText Initalized With Text","",offlineBluePlayerNameEditText.getText().toString());
        assertTrue("offlineBluePlayerNameEditText Not Visible", offlineBluePlayerNameEditText.isShown());

        final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
        final ImageView offlineOrangePlayerError = (ImageView) curAct.findViewById(R.id.offlineOrangePlayerError);
        final ImageView offlinePurplePlayerError = (ImageView) curAct.findViewById(R.id.offlinePurplePlayerError);
        final ImageView offlineYellowPlayerError = (ImageView) curAct.findViewById(R.id.offlineYellowPlayerError);
        final ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);
        final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

        final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
        assertNotNull("offlineGameActivityButton Not Found", offlineGameActivityButton);
        assertTrue("offlineGameActivityButton Not Clickable", offlineGameActivityButton.isClickable());

        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("Red Bob");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertFalse("offlineRedPlayerError Displayed Inappropriately", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately ", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately ", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately ", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("Orange Bob");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertFalse("offlineOrangePlayerError Displayed Inappropriately", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately ", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Not Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("Purple Bob");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertFalse("offlinePurplePlayerError Displayed Inappropriately", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertTrue("offlineBluePlayerError Not Displayed", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertTrue("offlineGreenPlayerError Not Displayed", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("Yellow Bob");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertFalse("offlineYellowPlayerError Displayed Inappropriately", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                        offlineRedPlayerNameEditText.setText("");
                        offlineOrangePlayerNameEditText.setText("");
                        offlinePurplePlayerNameEditText.setText("");
                        offlineYellowPlayerNameEditText.setText("");
                        offlineGreenPlayerNameEditText.setText("Green Bob");
                        offlineBluePlayerNameEditText.setText("Blue Bob");
                        offlineGameActivityButton.performClick();
                        assertTrue("offlineRedPlayerError Not Displayed", offlineRedPlayerError.isShown());
                        assertTrue("offlineOrangePlayerError Not Displayed", offlineOrangePlayerError.isShown());
                        assertTrue("offlinePurplePlayerError Not Displayed", offlinePurplePlayerError.isShown());
                        assertTrue("offlineYellowPlayerError Not Displayed", offlineYellowPlayerError.isShown());
                        assertFalse("offlineGreenPlayerError Displayed Inappropriately", offlineGreenPlayerError.isShown());
                        assertFalse("offlineBluePlayerError Displayed Inappropriately", offlineBluePlayerError.isShown());

                    }
                }
        );

    }
}

