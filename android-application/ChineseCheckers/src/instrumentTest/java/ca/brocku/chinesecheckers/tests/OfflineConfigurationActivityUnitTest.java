package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class OfflineConfigurationActivityUnitTest extends ActivityInstrumentationTestCase2<HotseatConfigurationActivity> {

    private TestHelpers testHelper;
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public OfflineConfigurationActivityUnitTest() {
        super(HotseatConfigurationActivity.class);
        testHelper = new TestHelpers();
    }

    public OfflineConfigurationActivityUnitTest(Activity curAct, Instrumentation curInstruments) {
        super(HotseatConfigurationActivity.class);
        this.curAct = curAct;
        this.curInstruments = curInstruments;
        testHelper = new TestHelpers();
    }

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        curAct = getActivity();
        curInstruments = getInstrumentation();
    }

    public void testActivity() throws Exception {
        assertNotNull("HotseatConfigurationActivity Not Started", curAct);
        PlayerConfigRunnable pCR = new PlayerConfigRunnable(this);
        synchronized (pCR){
            curAct.runOnUiThread(pCR);
            pCR.wait();
        }
    }


    private class PlayerConfigRunnable implements Runnable{
        ActivityInstrumentationTestCase2 actInsTest;
        PlayerConfigRunnable(ActivityInstrumentationTestCase2 actInsTest){
            this.actInsTest = actInsTest;
        }

        public void run(){
            synchronized (this){
                twoPlayerConfigTests();
                threePlayerConfigTests();
                fourPlayerConfigTests();
                sixPlayerConfigTests();
                notify();
            }
        }

        public void twoPlayerConfigTests() {

            ToggleButton hotseatTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatTwoPlayerButton);
            testHelper.ButtonTest(actInsTest,hotseatTwoPlayerButton, true);
            hotseatTwoPlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatRedPlayerNameEditText,true);

            EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest, hotseatGreenPlayerNameEditText, true);

            ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
            ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);

            Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
            testHelper.ButtonTest(actInsTest,hotseatGameActivityButton,true);

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

        public void threePlayerConfigTests(){

            final ToggleButton hotseatThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatThreePlayerButton);
            testHelper.ButtonTest(actInsTest,hotseatThreePlayerButton,true);
            hotseatThreePlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatRedPlayerNameEditText,true);
            final EditText hotseatYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatYellowPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatYellowPlayerNameEditText,true);
            final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatBluePlayerNameEditText,true);

            final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
            final ImageView hotseatYellowPlayerError = (ImageView) curAct.findViewById(R.id.hotseatYellowPlayerError);
            final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

            final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
            testHelper.ButtonTest(actInsTest,hotseatGameActivityButton,true);

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

        public void fourPlayerConfigTests(){
            final ToggleButton hotseatFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatFourPlayerButton);
            testHelper.ButtonTest(actInsTest,hotseatFourPlayerButton,true);
            hotseatFourPlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatRedPlayerNameEditText,true);
            final EditText hotseatOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatOrangePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatOrangePlayerNameEditText,true);
            final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatGreenPlayerNameEditText,true);
            final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatBluePlayerNameEditText,true);

            final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
            final ImageView hotseatOrangePlayerError = (ImageView) curAct.findViewById(R.id.hotseatOrangePlayerError);
            final ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);
            final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

            final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
            testHelper.ButtonTest(actInsTest,hotseatFourPlayerButton,true);

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

        public void sixPlayerConfigTests(){
            final ToggleButton hotseatSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatSixPlayerButton);
            testHelper.ButtonTest(actInsTest,hotseatSixPlayerButton,true);
            hotseatSixPlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText hotseatRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatRedPlayerNameEditText,true);
            final EditText hotseatOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatOrangePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatOrangePlayerNameEditText,true);
            final EditText hotseatPurplePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatPurplePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatPurplePlayerNameEditText,true);
            final EditText hotseatYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatYellowPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatYellowPlayerNameEditText,true);
            final EditText hotseatGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatGreenPlayerNameEditText,true);
            final EditText hotseatBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.hotseatBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,hotseatBluePlayerNameEditText,true);

            final ImageView hotseatRedPlayerError = (ImageView) curAct.findViewById(R.id.hotseatRedPlayerError);
            final ImageView hotseatOrangePlayerError = (ImageView) curAct.findViewById(R.id.hotseatOrangePlayerError);
            final ImageView hotseatPurplePlayerError = (ImageView) curAct.findViewById(R.id.hotseatPurplePlayerError);
            final ImageView hotseatYellowPlayerError = (ImageView) curAct.findViewById(R.id.hotseatYellowPlayerError);
            final ImageView hotseatGreenPlayerError = (ImageView) curAct.findViewById(R.id.hotseatGreenPlayerError);
            final ImageView hotseatBluePlayerError = (ImageView) curAct.findViewById(R.id.hotseatBluePlayerError);

            final Button hotseatGameActivityButton = (Button) curAct.findViewById(R.id.hotseatGameActivityButton);
            testHelper.ButtonTest(actInsTest,hotseatGameActivityButton,true);

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

}
