package ca.brocku.chinesecheckers.network.spice;

import android.app.Activity;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-27
 */
public abstract class ApiRequest<T> extends SpringAndroidSpiceRequest<T> {
    public ApiRequest(Class c) {
        super(c);
    }

    /**
     * Builds the API url.
     *
     * @param apiPath   The path of the request with param placeholders (i.e. %s).
     * @param params    The params for the url.
     * @return          The URL String.
     */
    public String apiUrl(String apiPath, Object... params) {
        if(apiPath.length() > 0 && !apiPath.startsWith("/")) {
            apiPath = "/" + apiPath;
        }

        // Change to chinesecheckers.codecanister.com
        return String.format("http://192.168.1.198:9000" + apiPath, params);
    }
}
