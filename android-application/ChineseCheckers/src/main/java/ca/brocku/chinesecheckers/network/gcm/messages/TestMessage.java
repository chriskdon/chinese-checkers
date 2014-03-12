package ca.brocku.chinesecheckers.network.gcm.messages;

import android.os.Bundle;

import ca.brocku.chinesecheckers.network.gcm.GcmMessage;

/**
 * TODO: THIS IS A PLACEHOLDER MESSAGE UNTIL THE API IS CREATED
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class TestMessage extends GcmMessage {
    public static final String TYPE_NAME = "TEST_MESSAGE";

    public TestMessage(Bundle bundle) {
        super(bundle, TYPE_NAME);
    }

    public TestMessage(GcmMessage message) {
        this(message.getBundle());
    }

    public String getTestData() {
        return getBundle().getString("TestData");
    }
}
