package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class HotseatConfigurationActivityUnitTest extends ActivityInstrumentationTestCase2<HotseatConfigurationActivity> {

    private static final long sleepTime = 10000;
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public HotseatConfigurationActivityUnitTest() {
        super(HotseatConfigurationActivity.class);
    }

    public HotseatConfigurationActivityUnitTest(Activity curAct, Instrumentation curInstruments) {
        super(HotseatConfigurationActivity.class);
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
        assertNotNull("HotseatConfigurationActivity Not Started", curAct);
        twoPlayerConfigTests();
        threePlayerConfigTests();
        fourPlayerConfigTests();
        sixPlayerConfigTests();
    }

    public void twoPlayerConfigTests() throws Exception {
        final ToggleButton hotseatTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatTwoPlayerButton);
        assertNotNull("hotseatTwoPlayerButton Not Found", hotseatTwoPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatTwoPlayerButton.performClick();
                    }
                }
        );

        final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
        assertNotNull("hotseatRedPlayerNameEditText Not Found", hotseatRedPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatRedPlayerNameEditText.isEnabled());
        final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
        assertNotNull("hotseatGreenPlayerNameEditText Not Found", hotseatGreenPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatGreenPlayerNameEditText.isEnabled());

        final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
        final ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);

        final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
        assertNotNull("hotseatGameActivityButton Not Found", hotseatGameActivityButton);
        assertTrue("hotseatGameActivityButton Not Clickable", hotseatGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());

                    }
                }
        );

    }

    public void threePlayerConfigTests() throws Exception {
        final ToggleButton hotseatThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatThreePlayerButton);
        assertNotNull("hotseatThreePlayerButton Not Found", hotseatThreePlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatThreePlayerButton.performClick();
                    }
                }
        );

        final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
        assertNotNull("hotseatRedPlayerNameEditText Not Found", hotseatRedPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatRedPlayerNameEditText.isEnabled());
        final EditText hotseatYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatYellowPlayerNameEditText);
        assertNotNull("hotseatYellowPlayerNameEditText Not Found", hotseatYellowPlayerNameEditText);
        assertTrue("hotseatYellowPlayerNameEditText Not Enabled", hotseatYellowPlayerNameEditText.isEnabled());
        final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
        assertNotNull("hotseatBluePlayerNameEditText Not Found", hotseatBluePlayerNameEditText);
        assertTrue("hotseatBluePlayerNameEditText Not Enabled", hotseatBluePlayerNameEditText.isEnabled());

        final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
        final ImageView hotseatYellowPlayerError = (ImageView) curAct.findViewById(R.id.hotseatYellowPlayerError);
        final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

        final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
        assertNotNull("hotseatGameActivityButton Not Found", hotseatGameActivityButton);
        assertTrue("hotseatGameActivityButton Not Clickable", hotseatGameActivityButton.isClickable());


        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                    }
                }
        );
    }

    public void fourPlayerConfigTests() throws Exception {
        final ToggleButton hotseatFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatFourPlayerButton);
        assertNotNull("hotseatFourPlayerButton Not Found", hotseatFourPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatFourPlayerButton.performClick();
                    }
                }
        );

        final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
        assertNotNull("hotseatRedPlayerNameEditText Not Found", hotseatRedPlayerNameEditText);
        assertTrue("hotseatRedPlayerNameEditText Not Enabled", hotseatRedPlayerNameEditText.isEnabled());
        final EditText hotseatOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatOrangePlayerNameEditText);
        assertNotNull("hotseatOrangePlayerNameEditText Not Found", hotseatOrangePlayerNameEditText);
        assertTrue("hotseatOrangePlayerNameEditText Not Enabled", hotseatOrangePlayerNameEditText.isEnabled());
        final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
        assertNotNull("hotseatGreenPlayerNameEditText Not Found", hotseatGreenPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatGreenPlayerNameEditText.isEnabled());
        final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
        assertNotNull("hotseatBluePlayerNameEditText Not Found", hotseatBluePlayerNameEditText);
        assertTrue("hotseatBluePlayerNameEditText Not Enabled", hotseatBluePlayerNameEditText.isEnabled());

        final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
        final ImageView hotseatOrangePlayerError = (ImageView) curAct.findViewById(R.id.hotseatOrangePlayerError);
        final ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);
        final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

        final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
        assertNotNull("hotseatGameActivityButton Not Found", hotseatGameActivityButton);
        assertTrue("hotseatGameActivityButton Not Clickable", hotseatGameActivityButton.isClickable());

        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());
//
                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());
                    }
                }
        );
    }

    public void sixPlayerConfigTests() throws Exception {
        final ToggleButton hotseatSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatSixPlayerButton);
        assertNotNull("hotseatSixPlayerButton Not Found", hotseatSixPlayerButton);
        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatSixPlayerButton.performClick();
                    }
                }
        );

        synchronized (this) { Thread.sleep(sleepTime); }
        final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
        assertNotNull("hotseatRedPlayerNameEditText Not Found", hotseatRedPlayerNameEditText);
        assertTrue("hotseatRedPlayerNameEditText Not Enabled", hotseatRedPlayerNameEditText.isEnabled());
        assertEquals("hotseatRedPlayerNameEditText Initalized With Text","",hotseatRedPlayerNameEditText.getText().toString());
        assertTrue("hotseatRedPlayerNameEditText Not Visible", hotseatRedPlayerNameEditText.isShown());
        final EditText hotseatOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatOrangePlayerNameEditText);
        assertNotNull("hotseatOrangePlayerNameEditText Not Found", hotseatOrangePlayerNameEditText);
        assertTrue("hotseatOrangePlayerNameEditText Not Enabled", hotseatOrangePlayerNameEditText.isEnabled());
        assertEquals("hotseatOrangePlayerNameEditText Initalized With Text","",hotseatOrangePlayerNameEditText.getText().toString());
        assertTrue("hotseatOrangePlayerNameEditText Not Visible", hotseatOrangePlayerNameEditText.isShown());
        final EditText hotseatPurplePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatPurplePlayerNameEditText);
        assertNotNull("hotseatPurplePlayerNameEditText Not Found", hotseatPurplePlayerNameEditText);
        assertTrue("hotseatPurplePlayerNameEditText Not Enabled", hotseatPurplePlayerNameEditText.isEnabled());
        assertEquals("hotseatPurplePlayerNameEditText Initalized With Text","",hotseatPurplePlayerNameEditText.getText().toString());
        assertTrue("hotseatPurplePlayerNameEditText Not Visible", hotseatPurplePlayerNameEditText.isShown());
        final EditText hotseatYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatYellowPlayerNameEditText);
        assertNotNull("hotseatYellowPlayerNameEditText Not Found", hotseatYellowPlayerNameEditText);
        assertTrue("hotseatYellowPlayerNameEditText Not Enabled", hotseatYellowPlayerNameEditText.isEnabled());
        assertEquals("hotseatYellowPlayerNameEditText Initalized With Text","",hotseatYellowPlayerNameEditText.getText().toString());
        assertTrue("hotseatYellowPlayerNameEditText Not Visible", hotseatYellowPlayerNameEditText.isShown());
        final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
        assertNotNull("hotseatGreenPlayerNameEditText Not Found", hotseatGreenPlayerNameEditText);
        assertTrue("hotseatGreenPlayerNameEditText Not Enabled", hotseatGreenPlayerNameEditText.isEnabled());
        assertEquals("hotseatGreenPlayerNameEditText Initalized With Text","",hotseatGreenPlayerNameEditText.getText().toString());
        assertTrue("hotseatGreenPlayerNameEditText Not Visible", hotseatGreenPlayerNameEditText.isShown());
        final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
        assertNotNull("hotseatBluePlayerNameEditText Not Found", hotseatBluePlayerNameEditText);
        assertTrue("hotseatBluePlayerNameEditText Not Enabled", hotseatBluePlayerNameEditText.isEnabled());
        assertEquals("hotseatBluePlayerNameEditText Initalized With Text","",hotseatBluePlayerNameEditText.getText().toString());
        assertTrue("hotseatBluePlayerNameEditText Not Visible", hotseatBluePlayerNameEditText.isShown());

        final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
        final ImageView hotseatOrangePlayerError = (ImageView) curAct.findViewById(R.id.hotseatOrangePlayerError);
        final ImageView hotseatPurplePlayerError = (ImageView) curAct.findViewById(R.id.hotseatPurplePlayerError);
        final ImageView hotseatYellowPlayerError = (ImageView) curAct.findViewById(R.id.hotseatYellowPlayerError);
        final ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);
        final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

        final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
        assertNotNull("hotseatGameActivityButton Not Found", hotseatGameActivityButton);
        assertTrue("hotseatGameActivityButton Not Clickable", hotseatGameActivityButton.isClickable());

        curAct.runOnUiThread(
                new Runnable() {
                    public void run() {
                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("Red Bob");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertFalse("hotseatRedPlayerError Displayed Inappropriately", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately ", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately ", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately ", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("Orange Bob");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertFalse("hotseatOrangePlayerError Displayed Inappropriately", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately ", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Not Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("Purple Bob");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertFalse("hotseatPurplePlayerError Displayed Inappropriately", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertTrue("hotseatBluePlayerError Not Displayed", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertTrue("hotseatGreenPlayerError Not Displayed", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("Yellow Bob");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertFalse("hotseatYellowPlayerError Displayed Inappropriately", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                        hotseatRedPlayerNameEditText.setText("");
                        hotseatOrangePlayerNameEditText.setText("");
                        hotseatPurplePlayerNameEditText.setText("");
                        hotseatYellowPlayerNameEditText.setText("");
                        hotseatGreenPlayerNameEditText.setText("Green Bob");
                        hotseatBluePlayerNameEditText.setText("Blue Bob");
                        hotseatGameActivityButton.performClick();
                        assertTrue("hotseatRedPlayerError Not Displayed", hotseatRedPlayerError.isShown());
                        assertTrue("hotseatOrangePlayerError Not Displayed", hotseatOrangePlayerError.isShown());
                        assertTrue("hotseatPurplePlayerError Not Displayed", hotseatPurplePlayerError.isShown());
                        assertTrue("hotseatYellowPlayerError Not Displayed", hotseatYellowPlayerError.isShown());
                        assertFalse("hotseatGreenPlayerError Displayed Inappropriately", hotseatGreenPlayerError.isShown());
                        assertFalse("hotseatBluePlayerError Displayed Inappropriately", hotseatBluePlayerError.isShown());

                    }
                }
        );

    }
}

