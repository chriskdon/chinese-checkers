package ca.brocku.chinesecheckers.network;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

import ca.brocku.chinesecheckers.network.gcm.GcmActivity;

/**
 * An activity that needs to have both RoboSpice and GCM services.
 *
 * TODO: There may be a cleaner way of doing this. Currently this code is repeated in SpicedActivity.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class SpicedGcmActivity extends GcmActivity {
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
