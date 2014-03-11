package ca.brocku.chinesecheckers.network.gcm;

import ca.brocku.chinesecheckers.network.gcm.messages.TestMessage;
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
    public void push(GcmMessage message) {
        postToEventBus(filter(message));
    }

    /**
     * Filter GCM Messages so that they are typed correctly.
     * @param message   The message to filter.
     * @return          The real typed version of the message.
     */
    protected GcmMessage filter(GcmMessage message) {
        // TODO: Come up with a better filtering mechanism
        if(message.isMessageTypeName(TestMessage.TYPE_NAME)) {
            return new TestMessage(message);
        }

        throw new NoMessageTypeException();
    }

    private void postToEventBus(GcmMessage message) {
        EventBus.getDefault().post(message);
    }
}
