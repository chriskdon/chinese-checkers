package ca.brocku.chinesecheckers.network.spice;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import ca.brocku.chinesecheckers.network.spice.ApiUrlBuilder;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-27
 */
public abstract class ApiRequest<T> extends SpringAndroidSpiceRequest<T> {
    public ApiRequest(Class c) {
        super(c);
    }

    private ApiUrlBuilder builder;

    protected ApiUrlBuilder getApiUrlBuilder() {
        if(builder == null) {
            builder = new ApiUrlBuilder();
        }

        return builder;
    }
}
