package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import java.io.File;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.SettingsActivity;
import ca.brocku.chinesecheckers.gamestate.GameStateManager;

/**
 * Created by Main on 2/18/14.
 */
public class MainActivityUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public MainActivityUnitTest() {
        super(MainActivity.class);
        testHelper = new TestHelpers();
    }

    public MainActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(MainActivity.class);
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

    public void testActivity() {
        File savedOfflineGame = curAct.getFileStreamPath(GameStateManager.SERIALIZED_FILENAME);
        savedOfflineGame.delete();
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
        monitor = curInstruments.addMonitor(MainActivity.class.getName(),null,false);

        new HelpActivityUnitTest(curAct,curInstruments).activityTest();

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition Back to MainActivity Failed",curAct);


        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(SettingsActivity.class.getName(),null,false);

        curInstruments.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        curInstruments.invokeMenuActionSync(curAct, R.id.action_settings,0);

        curAct = curInstruments.waitForMonitorWithTimeout(monitor, testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition to SettingsActivity Failed", curAct);
        new SettingsActivityUnitTest(curAct,curInstruments).activityTest();

        curInstruments.removeMonitor(monitor);
        monitor = curInstruments.addMonitor(MainActivity.class.getName(),null,false);

        curAct = curInstruments.waitForMonitorWithTimeout(monitor,testHelper.timeoutForActivityTransition);
        try{Thread.sleep(1000);}catch(Exception e){}
        assertNotNull("Transition Back to MainActivity Failed",curAct);

    }

    public void activityTestHelper(){
        assertNotNull("MainActivity Not Started", curAct);
        testHelper.ButtonTest(this, (Button) curAct.findViewById(R.id.offlineConfigurationActivityButton), true);
        testHelper.ButtonTest(this, (Button) curAct.findViewById(R.id.onlineListActivityButton), true);
        testHelper.ButtonTest(this, (Button) curAct.findViewById(R.id.helpActivityButton), true);
        testHelper.ButtonTest(this, (Button) curAct.findViewById(R.id.settingsActivityButton), true);
    }

}
