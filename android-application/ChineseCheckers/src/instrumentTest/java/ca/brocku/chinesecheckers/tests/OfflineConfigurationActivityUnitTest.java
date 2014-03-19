package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.OfflineConfigurationActivity;
import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.SettingsActivity;

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

//        MenuItem item = new MenuItem() {
//            @Override
//            public int getItemId() {
//                return R.id.action_help;
//            }
//
//            @Override
//            public int getGroupId() {
//                return 0;
//            }
//
//            @Override
//            public int getOrder() {
//                return 0;
//            }
//
//            @Override
//            public MenuItem setTitle(CharSequence charSequence) {
//                return null;
//            }
//
//            @Override
//            public MenuItem setTitle(int i) {
//                return null;
//            }
//
//            @Override
//            public CharSequence getTitle() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setTitleCondensed(CharSequence charSequence) {
//                return null;
//            }
//
//            @Override
//            public CharSequence getTitleCondensed() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setIcon(Drawable drawable) {
//                return null;
//            }
//
//            @Override
//            public MenuItem setIcon(int i) {
//                return null;
//            }
//
//            @Override
//            public Drawable getIcon() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setIntent(Intent intent) {
//                return null;
//            }
//
//            @Override
//            public Intent getIntent() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setShortcut(char c, char c2) {
//                return null;
//            }
//
//            @Override
//            public MenuItem setNumericShortcut(char c) {
//                return null;
//            }
//
//            @Override
//            public char getNumericShortcut() {
//                return 0;
//            }
//
//            @Override
//            public MenuItem setAlphabeticShortcut(char c) {
//                return null;
//            }
//
//            @Override
//            public char getAlphabeticShortcut() {
//                return 0;
//            }
//
//            @Override
//            public MenuItem setCheckable(boolean b) {
//                return null;
//            }
//
//            @Override
//            public boolean isCheckable() {
//                return false;
//            }
//
//            @Override
//            public MenuItem setChecked(boolean b) {
//                return null;
//            }
//
//            @Override
//            public boolean isChecked() {
//                return false;
//            }
//
//            @Override
//            public MenuItem setVisible(boolean b) {
//                return null;
//            }
//
//            @Override
//            public boolean isVisible() {
//                return false;
//            }
//
//            @Override
//            public MenuItem setEnabled(boolean b) {
//                return null;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return false;
//            }
//
//            @Override
//            public boolean hasSubMenu() {
//                return false;
//            }
//
//            @Override
//            public SubMenu getSubMenu() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
//                return null;
//            }
//
//            @Override
//            public ContextMenu.ContextMenuInfo getMenuInfo() {
//                return null;
//            }
//
//            @Override
//            public void setShowAsAction(int i) {
//
//            }
//
//            @Override
//            public MenuItem setShowAsActionFlags(int i) {
//                return null;
//            }
//
//            @Override
//            public MenuItem setActionView(View view) {
//                return null;
//            }
//
//            @Override
//            public MenuItem setActionView(int i) {
//                return null;
//            }
//
//            @Override
//            public View getActionView() {
//                return null;
//            }
//
//            @Override
//            public MenuItem setActionProvider(ActionProvider actionProvider) {
//                return null;
//            }
//
//            @Override
//            public ActionProvider getActionProvider() {
//                return null;
//            }
//
//            @Override
//            public boolean expandActionView() {
//                return false;
//            }
//
//            @Override
//            public boolean collapseActionView() {
//                return false;
//            }
//
//            @Override
//            public boolean isActionViewExpanded() {
//                return false;
//            }
//
//            @Override
//            public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
//                return null;
//            }
//        };
//        curAct.onOptionsItemSelected(item);

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition to HelpActivity Failed", curAct);

        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(),null,false);

        new HelpActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition Back to OfflineConfigurationActivity Failed",curAct);




        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(SettingsActivity.class.getName(),null,false);

        curInstruments.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        curInstruments.invokeMenuActionSync(curAct, R.id.action_settings,0);

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition to SettingsActivity Failed", curAct);

        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(OfflineConfigurationActivity.class.getName(),null,false);

        new SettingsActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition Back to MainActivity Failed",curAct);
    }

    public void activityTestHelper() throws Exception{
        assertNotNull("OfflineConfigurationActivity Not Started", curAct);
        PlayerConfigRunnable pCR = new PlayerConfigRunnable(this);
        synchronized (pCR){
            curAct.runOnUiThread(pCR);
            ((Object) pCR).wait();
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
                ((Object) this).notify();
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
            ToggleButton offlineTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.twoPlayerButton);
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
            final ToggleButton offlineThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.threePlayerButton);

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
            final ToggleButton offlineFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.fourPlayerButton);

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
            final ToggleButton offlineSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.sixPlayerButton);

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
