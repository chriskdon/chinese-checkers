package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.postdata.PlayerMovePostData;
import com.ccapi.postdata.RegisterUserPostData;
import com.ccapi.receivables.SuccessReceivable;
import com.ccapi.receivables.UserRegistrationReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * When a player makes a move send it to the server.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-04-03
 */
public class SendPlayerMoveRequest extends ApiRequest<SuccessReceivable> {
    private PlayerMovePostData postData = null;

    public SendPlayerMoveRequest(PlayerMovePostData postData) {
        super(UserRegistrationReceivable.class);
        this.postData = postData;
    }

    @Override
    public SuccessReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/gameplay/move");

        return getRestTemplate().postForObject(url, postData, SuccessReceivable.class);
    }
}
