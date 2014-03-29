package ca.brocku.chinesecheckers.network.spice;

import com.ccapp.Result;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-27
 */
public abstract class ApiRequestListener<T extends Result> implements RequestListener<T> {
    /**
     * Called when the intended task that was requested could not be completed by the
     * server.
     *
     * @param code      The error code of the result.
     * @param message   The error message.
     */
    public abstract void onTaskFailure(int code, String message);

    /**
     * Called when the intended task that was requested was completed without error.
     * @param result    The resulting object of the request.
     */
    public abstract void onTaskSuccess(T result);

    @Override
    public void onRequestSuccess(T t) {
        if(t.code != Result.CODE_SUCCESS) {
            onTaskFailure(t.code, t.message);
        } else {
            onTaskSuccess(t);
        }
    }
}
