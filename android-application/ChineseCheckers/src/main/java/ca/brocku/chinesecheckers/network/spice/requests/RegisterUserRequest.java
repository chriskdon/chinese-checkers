package ca.brocku.chinesecheckers.network.spice.requests;



import com.ccapi.receivables.UserRegistrationReceivable;
import com.ccapi.postdata.RegisterUserPostData;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * Register a new user.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class RegisterUserRequest extends ApiRequest<UserRegistrationReceivable> {
    private RegisterUserPostData postData = null;

    public RegisterUserRequest(RegisterUserPostData postData) {
        super(UserRegistrationReceivable.class);
        this.postData = postData;
    }

    @Override
    public UserRegistrationReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/users/register");

        return getRestTemplate().postForObject(url, postData, UserRegistrationReceivable.class);
    }
}
