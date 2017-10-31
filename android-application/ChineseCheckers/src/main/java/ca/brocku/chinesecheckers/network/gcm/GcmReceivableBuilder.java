package ca.brocku.chinesecheckers.network.gcm;

import android.os.Bundle;

import com.ccapi.receivables.GameReadyNotificationReceivable;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Objects;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-04-03
 */
public class GcmReceivableBuilder {
    private ObjectMapper mapper;
    private Bundle bundle;

    public GcmReceivableBuilder(Bundle bundle) {
        this.mapper = new ObjectMapper();
        this.bundle = bundle;
    }

    public Object build() {
        try {
            Class clazz = Class.forName(bundle.getString("classname"));

            return mapper.readValue(bundle.getString("receivable"), clazz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
