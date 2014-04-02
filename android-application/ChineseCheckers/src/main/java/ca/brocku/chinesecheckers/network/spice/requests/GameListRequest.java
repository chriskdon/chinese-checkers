package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.GameListReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * @author Jakub Subczynski
 * @date April 02, 2014
 */
public class GameListRequest extends ApiRequest<GameListReceivable> {
    private long userId;


    public GameListRequest(long userId) {
        super(GameListRequest.class);

        this.userId = userId;
    }

    @Override
    public GameListReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/games/list/%s", userId);

        return getRestTemplate().getForObject(url, GameListReceivable.class);
    }
}
