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
//Create a request in its own Java file, it should not an inner class of a Context
public class RegisterUserRequest extends ApiRequest<UserRegistrationReceivable> {

    private String username;

    public RegisterUserRequest(String username) {
        super(UserRegistrationReceivable.class);
        this.username = username;
    }

    @Override
    public UserRegistrationReceivable loadDataFromNetwork() throws Exception {
        String url = getApiUrlBuilder().build("/users/register/%s", username);

        return getRestTemplate().postForObject(url, null, UserRegistrationReceivable.class);
    }
}
