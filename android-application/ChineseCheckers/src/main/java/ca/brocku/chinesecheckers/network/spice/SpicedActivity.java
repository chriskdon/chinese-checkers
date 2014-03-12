package ca.brocku.chinesecheckers.network.spice;

import android.app.Activity;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * An activity that has a {@code SpiceManager} in it.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class SpicedActivity extends Activity {
    protected SpiceManager spiceManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);

    /**
     * Start the spice manager.
     */
    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    /**
     * Stop the spice manager.
     */
    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
