package ca.brocku.chinesecheckers.network.gcm;

import android.os.Bundle;

import ca.brocku.chinesecheckers.uiengine.BoardUiEngine;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class GcmMessage {
    private static final String KEY_MESSAGE_TYPE = "MESSAGE_TYPE";

    protected Bundle bundle;    // The raw message bundle data

    public GcmMessage(Bundle bundle) {
        this.bundle = bundle;
    }

    public GcmMessage(Bundle bundle, String expectedTypeName) {
        this(bundle);

        if(!isMessageTypeName(expectedTypeName)) {
            throw new MessageTypeNameMismatchException();
        }
    }

    /**
     * @return The bundle for this message
     */
    public Bundle getBundle() {
        return bundle;
    }

    /**
     * Is the unique type name of this message a match for the expected type.
     *
     * @param expectedTypeName The name of the type.
     *
     * @return True if they match, False otherwise.
     */
    public Boolean isMessageTypeName(String expectedTypeName) {
        return getBundle().getString(KEY_MESSAGE_TYPE).equals(expectedTypeName);
    }
}
