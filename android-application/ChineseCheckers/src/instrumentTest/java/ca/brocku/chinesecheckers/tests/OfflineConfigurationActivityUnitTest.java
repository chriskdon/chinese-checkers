package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/18/14.
 */
public class OfflineConfigurationActivityUnitTest extends ActivityInstrumentationTestCase2<OfflineConfigurationActivity> {

    private TestHelpers testHelper;
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public OfflineConfigurationActivityUnitTest() {
        super(OfflineConfigurationActivity.class);
        testHelper = new TestHelpers();
    }

    public OfflineConfigurationActivityUnitTest(Activity curAct, Instrumentation curInstruments) {
        super(OfflineConfigurationActivity.class);
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
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(),null,false);

        new HelpActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,5000);
        assertNotNull("Transition Back to OfflineConfigurationActivity Failed",curAct);
    }

    public void activityTestHelper() throws Exception{
        assertNotNull("OfflineConfigurationActivity Not Started", curAct);
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

        public void AIToggleButtonTest(ToggleButton easyButton,ToggleButton mediumButton, ToggleButton hardButton, ImageButton typeButton){
            testHelper.AIToggleButtonTest(actInsTest,
                    easyButton,
                    mediumButton,
                    hardButton,
                    1);

            mediumButton.performClick();

            testHelper.AIToggleButtonTest(actInsTest,
                    easyButton,
                    mediumButton,
                    hardButton,
                    2);

            hardButton.performClick();

            testHelper.AIToggleButtonTest(actInsTest,
                    easyButton,
                    mediumButton,
                    hardButton,
                    3);

            testHelper.ImageButtonTest(actInsTest,typeButton,true);
            typeButton.performClick();
        }

        public void twoPlayerConfigTests() {

            ToggleButton offlineTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineTwoPlayerButton);
            testHelper.ButtonTest(actInsTest,offlineTwoPlayerButton, true);
            offlineTwoPlayerButton.performClick();
            try{Thread.sleep(1000);}catch (Exception e){}

            EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineRedPlayerNameEditText,true);

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineGreenPlayerTypeButton)
            );

            EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest, offlineGreenPlayerNameEditText, true);

            ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
            ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);

            Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
            testHelper.ButtonTest(actInsTest,offlineGameActivityButton,true);

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

        public void threePlayerConfigTests(){

            final ToggleButton offlineThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineThreePlayerButton);
            testHelper.ButtonTest(actInsTest,offlineThreePlayerButton,true);
            offlineThreePlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineRedPlayerNameEditText,true);

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineYellowPlayerTypeButton)
            );

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineBluePlayerTypeButton)
            );

            final EditText offlineYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineYellowPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineYellowPlayerNameEditText,true);
            final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineBluePlayerNameEditText,true);

            final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
            final ImageView offlineYellowPlayerError = (ImageView) curAct.findViewById(R.id.offlineYellowPlayerError);
            final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

            final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
            testHelper.ButtonTest(actInsTest,offlineGameActivityButton,true);

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

        public void fourPlayerConfigTests(){
            final ToggleButton offlineFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineFourPlayerButton);
            testHelper.ButtonTest(actInsTest,offlineFourPlayerButton,true);
            offlineFourPlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineRedPlayerNameEditText,true);

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineOrangePlayerTypeButton)
            );


            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineGreenPlayerTypeButton)
            );


            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineBluePlayerTypeButton)
            );


            final EditText offlineOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineOrangePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineOrangePlayerNameEditText,true);
            final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineGreenPlayerNameEditText,true);
            final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineBluePlayerNameEditText,true);

            final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
            final ImageView offlineOrangePlayerError = (ImageView) curAct.findViewById(R.id.offlineOrangePlayerError);
            final ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);
            final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

            final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
            testHelper.ButtonTest(actInsTest,offlineFourPlayerButton,true);

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

        public void sixPlayerConfigTests(){
            final ToggleButton offlineSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.offlineSixPlayerButton);
            testHelper.ButtonTest(actInsTest,offlineSixPlayerButton,true);
            offlineSixPlayerButton.performClick();
//            try{Thread.sleep(1000);}catch (Exception e){}

            final EditText offlineRedPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineRedPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineRedPlayerNameEditText,true);

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineOrangePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineOrangePlayerTypeButton)
            );

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflinePurplePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflinePurplePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflinePurplePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlinePurplePlayerTypeButton)
            );

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineYellowPlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineYellowPlayerTypeButton)
            );

            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineGreenPlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineGreenPlayerTypeButton)
            );


            this.AIToggleButtonTest((ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerEasyButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerMediumButton),
                    (ToggleButton)curAct.findViewById(R.id.OfflineBluePlayerHardButton),
                    (ImageButton)curAct.findViewById(R.id.offlineBluePlayerTypeButton)
            );

            final EditText offlineOrangePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineOrangePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineOrangePlayerNameEditText,true);
            final EditText offlinePurplePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlinePurplePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlinePurplePlayerNameEditText,true);
            final EditText offlineYellowPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineYellowPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineYellowPlayerNameEditText,true);
            final EditText offlineGreenPlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineGreenPlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineGreenPlayerNameEditText,true);
            final EditText offlineBluePlayerNameEditText = (EditText) curAct.findViewById(R.id.offlineBluePlayerNameEditText);
            testHelper.EditTextTest(actInsTest,offlineBluePlayerNameEditText,true);

            final ImageView offlineRedPlayerError = (ImageView) curAct.findViewById(R.id.offlineRedPlayerError);
            final ImageView offlineOrangePlayerError = (ImageView) curAct.findViewById(R.id.offlineOrangePlayerError);
            final ImageView offlinePurplePlayerError = (ImageView) curAct.findViewById(R.id.offlinePurplePlayerError);
            final ImageView offlineYellowPlayerError = (ImageView) curAct.findViewById(R.id.offlineYellowPlayerError);
            final ImageView offlineGreenPlayerError = (ImageView) curAct.findViewById(R.id.offlineGreenPlayerError);
            final ImageView offlineBluePlayerError = (ImageView) curAct.findViewById(R.id.offlineBluePlayerError);

            final Button offlineGameActivityButton = (Button) curAct.findViewById(R.id.offlineGameActivityButton);
            testHelper.ButtonTest(actInsTest,offlineGameActivityButton,true);

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

}
