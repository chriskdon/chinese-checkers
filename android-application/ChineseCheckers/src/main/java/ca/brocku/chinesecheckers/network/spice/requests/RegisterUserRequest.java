package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.UserRegistrationReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * Register a new user.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class RegisterUserRequest extends ApiRequest<UserRegistrationReceivable> {

    private String username;

    public RegisterUserRequest(String username) {
        super(UserRegistrationReceivable.class);
        this.username = username;
    }

    @Override
    public UserRegistrationReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/users/register/%s", username);

        return getRestTemplate().postForObject(url, null, UserRegistrationReceivable.class);
    }
}
