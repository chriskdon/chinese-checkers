package ca.brocku.chinesecheckers.network.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Handle a GCM message
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class GcmIntentService extends IntentService {
    private PushMessageProcessor processor;

    public GcmIntentService() {
        super("GcmIntentService");
        this.processor = new PushMessageProcessor();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            // Filter messages
            if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                // Message collapsed/deleted
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // Received message - get data from extras
                processor.push(new GcmMessage(intent.getExtras()));
            }
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
