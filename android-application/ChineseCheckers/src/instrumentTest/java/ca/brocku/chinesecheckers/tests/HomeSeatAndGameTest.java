package ca.brocku.chinesecheckers.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import ca.brocku.chinesecheckers.HotseatConfigurationActivity;
import ca.brocku.chinesecheckers.MainActivity;
import ca.brocku.chinesecheckers.R;

/**
 * Created by Main on 2/4/14.
 */
public class HomeSeatAndGameTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Activity curAct;
    private Instrumentation curInstruments;
    private Instrumentation.ActivityMonitor monitor;

    public HomeSeatAndGameTest() {
        super(MainActivity.class);
    }

}
