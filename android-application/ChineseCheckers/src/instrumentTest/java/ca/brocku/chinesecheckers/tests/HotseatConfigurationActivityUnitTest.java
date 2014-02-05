package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.ToggleButton;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class HotseatConfigurationActivityUnitTest extends ActivityInstrumentationTestCase2<HotseatConfigurationActivity> {

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
        twoPlayerButtonTesting();
    }

    public void twoPlayerButtonTesting() throws Exception {
        ToggleButton hotseatTwoPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatTwoPlayerButton);
        ToggleButton hotseatThreePlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatThreePlayerButton);
        ToggleButton hotseatFourPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatFourPlayerButton);
        ToggleButton hotseatSixPlayerButton = (ToggleButton) curAct.findViewById(R.id.hotseatSixPlayerButton);
        TouchUtils.clickView(this, hotseatTwoPlayerButton);
        synchronized (this) {
            this.wait(5000);
        }
        TouchUtils.clickView(this, hotseatThreePlayerButton);
        synchronized (this) {
            this.wait(5000);
        }
        TouchUtils.clickView(this, hotseatFourPlayerButton);
        synchronized (this) {
            this.wait(5000);
        }
        TouchUtils.clickView(this, hotseatSixPlayerButton);
        synchronized (this) {
            this.wait(5000);
        }
    }

}
