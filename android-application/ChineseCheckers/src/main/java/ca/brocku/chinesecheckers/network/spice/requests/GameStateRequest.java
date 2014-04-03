package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.GameStateReceivable;
import com.ccapi.receivables.JoinGameReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * @author Jakub Subczynski
 * @date April 02, 2014
 */
public class GameStateRequest extends ApiRequest<GameStateReceivable> {
    private long gameId;

    public GameStateRequest(long gameId) {
        super(GameStateRequest.class);

        this.gameId = gameId;
    }

    @Override
    public GameStateReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/games/gamestate/%s", gameId);

        return getRestTemplate().getForObject(url, GameStateReceivable.class);
    }
}
