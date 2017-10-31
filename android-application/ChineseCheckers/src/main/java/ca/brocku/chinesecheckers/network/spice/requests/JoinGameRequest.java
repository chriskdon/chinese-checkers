package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.JoinGameReceivable;
import com.ccapi.receivables.UserRegistrationReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * Try and join a game with the number of players you want.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-29
 */
public class JoinGameRequest extends ApiRequest<JoinGameReceivable> {
    private int playerCount;
    private long userId;

    public JoinGameRequest(long userId, int playerCount) {
        super(JoinGameRequest.class);

        this.userId = userId;
        this.playerCount = playerCount;
    }

    @Override
    public JoinGameReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/games/join/%s/%s", playerCount, userId);

        return getRestTemplate().postForObject(url, null, JoinGameReceivable.class);
    }
}