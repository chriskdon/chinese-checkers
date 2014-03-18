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

/**
 * Created by Main on 3/18/14.
 */
public class SettingsActivityUnitTest extends ActivityInstrumentationTestCase2<HelpActivity> {

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public SettingsActivityUnitTest() {
        super(HelpActivity.class);
        testHelper = new TestHelpers();
    }

    public SettingsActivityUnitTest(Activity curAct,Instrumentation curInstruments){
        super(HelpActivity.class);
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
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(curAct);
        assertNotNull("SettingsActivity Not Started",curAct);
        SettingsRunnable sR = new SettingsRunnable();
        RadioButton showMoveOn = (RadioButton)curAct.findViewById(R.id.settingsShowMoveOnButton);
        RadioButton showMoveOff = (RadioButton)curAct.findViewById(R.id.settingsShowMoveOnButton);

        TouchUtils.clickView(this, showMoveOn);
        testHelper.RadioButtonTest(this, showMoveOn, true, true);
        testHelper.RadioButtonTest(this,showMoveOn,true,false);
        Boolean showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        assertTrue("Show Moves saved False When Should Be True",showMoves);

        TouchUtils.clickView(this, showMoveOff);
        testHelper.RadioButtonTest(this,showMoveOn,true,false);
        testHelper.RadioButtonTest(this,showMoveOn,true,true);
        showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        assertFalse("Show Moves saved True When Should Be False", showMoves);

        TouchUtils.clickView(this, showMoveOn);
        testHelper.RadioButtonTest(this,showMoveOn,true,true);
        testHelper.RadioButtonTest(this,showMoveOn,true,false);
        showMoves = sharedPrefs.getBoolean(MainActivity.PREF_SHOW_MOVES, true);
        assertTrue("Show Moves saved False When Should Be True",showMoves);

        testHelper.EditTextTest(this,(EditText)curAct.findViewById(R.id.settingsUsernameEditText),true);

        synchronized (sR){
            try{
                curAct.runOnUiThread(sR);
                ((Object) sR).wait();
            }
            catch(Exception e){}
        }
    }

    class SettingsRunnable implements Runnable{
        public void run(){
            synchronized (this){
                curAct.onBackPressed();
                ((Object) this).notify();
            }
        }
    }

}
