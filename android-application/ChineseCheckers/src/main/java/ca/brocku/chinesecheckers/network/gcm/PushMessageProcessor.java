package ca.brocku.chinesecheckers.network.gcm;

import com.ccapi.receivables.Receivable;

import java.util.Objects;

import de.greenrobot.event.EventBus;

/**
 * Forward push messages to the correct locations.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class PushMessageProcessor {
    /**
     * Filter and forward a new message.
     *
     * @param message   The message to handle.
     */
    public void push(Object message) {
        postToEventBus(message);
    }


    private void postToEventBus(Object message) {
        EventBus.getDefault().post(message);
    }
}
