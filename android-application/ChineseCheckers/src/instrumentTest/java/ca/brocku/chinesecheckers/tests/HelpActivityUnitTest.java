package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import ca.brocku.chinesecheckers.HelpActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/20/14.
 */
public class HelpActivityUnitTest extends ActivityInstrumentationTestCase2<HelpActivity>{

    private TestHelpers testHelper;

    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public HelpActivityUnitTest() {
        super(HelpActivity.class);
        testHelper = new TestHelpers();
    }

    public HelpActivityUnitTest(Activity curAct,Instrumentation curInstruments){
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
        assertNotNull("HelpActivity Not Started",curAct);
        HelpRunnable hR = new HelpRunnable();
        synchronized (hR){
            try{
                curAct.runOnUiThread(hR);
                hR.wait();
            }
            catch(Exception e){}
        }
    }

    class HelpRunnable implements Runnable{
        public void run(){
            synchronized (this){
                curAct.onBackPressed();
                notify();
            }
        }
    }

}