package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.SuccessReceivable;
import com.ccapi.receivables.UserRegistrationReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * Change an existing user's username.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-29
 */
public class ChangeUsernameRequest extends ApiRequest<SuccessReceivable> {
    private long userId;
    private String username;

    public ChangeUsernameRequest(long userId, String username) {
        super(SuccessReceivable.class);

        this.userId = userId;
        this.username = username;
    }

    @Override
    public SuccessReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/users/change/%s/%s", userId, username);

        return getRestTemplate().postForObject(url, null, SuccessReceivable.class);
    }
}
