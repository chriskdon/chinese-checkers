package ca.brocku.chinesecheckers.network.gcm;

import java.io.IOException;

/**
 * Fired when GCM could not be registered for this device.
 */
public interface FailedToRegisterGcm {
    public void onCouldNotRegisterGcm(IOException ex);
}
