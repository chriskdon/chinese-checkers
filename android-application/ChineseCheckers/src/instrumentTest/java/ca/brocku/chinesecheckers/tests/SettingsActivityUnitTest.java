package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.SettingsActivity;

/**
 * Created by Main on 3/18/14.
 */
public class SettingsActivityUnitTest extends ActivityInstrumentationTestCase2<SettingsActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public SettingsActivityUnitTest() {
        super(SettingsActivity.class);
        testHelper = new TestHelpers();
    }

    public SettingsActivityUnitTest(Activity curAct, Instrumentation curInstruments) {
        super(SettingsActivity.class);
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

    public void activityTest() {
        final ActivityInstrumentationTestCase2 actInsTest = this;
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(curAct);
        assertNotNull("SettingsActivity Not Started", curAct);

        synchronized (curAct) {
            curAct.runOnUiThread(new Runnable() {

                public void run() {
                    RadioButton showMoveOn = (RadioButton) curAct.findViewById(R.id.settingsShowMoveOnButton);
                    RadioButton showMoveOff = (RadioButton) curAct.findViewById(R.id.settingsShowMoveOffButton);
                    RadioGroup showMoveGroup = (RadioGroup) curAct.findViewById(R.id.settingsShowMovesRadioGroup);

                    showMoveGroup.clearCheck();
                    showMoveGroup.check(R.id.settingsShowMoveOnButton);
                    try{Thread.sleep(1000);}catch (Exception e){}
                    testHelper.RadioButtonTest(actInsTest, showMoveOn, true, true);
                    testHelper.RadioButtonTest(actInsTest, showMoveOff, true, false);

                    showMoveGroup.check(R.id.settingsShowMoveOffButton);
                    try{Thread.sleep(1000);}catch (Exception e){}
                    testHelper.RadioButtonTest(actInsTest, showMoveOn, true, false);
                    testHelper.RadioButtonTest(actInsTest, showMoveOff, true, true);

                    showMoveGroup.check(R.id.settingsShowMoveOnButton);
                    try{Thread.sleep(1000);}catch (Exception e){}
                    testHelper.RadioButtonTest(actInsTest, showMoveOn, true, true);
                    testHelper.RadioButtonTest(actInsTest, showMoveOff, true, false);

                    String username = sharedPrefs.getString(MainActivity.PREF_USER_ID, "");
                    testHelper.EditTextTest(actInsTest, (EditText) curAct.findViewById(R.id.settingsUsernameEditText), true,username);
                }
            });
        }

        SettingsRunnable sR = new SettingsRunnable();
        synchronized (sR) {
            try {
                curAct.runOnUiThread(sR);
                ((Object) sR).wait();
            } catch (Exception e) {
            }
        }
    }

    class SettingsRunnable implements Runnable {
        public void run() {
            synchronized (this) {
                curAct.onBackPressed();
                ((Object) this).notify();
            }
        }
    }

}
