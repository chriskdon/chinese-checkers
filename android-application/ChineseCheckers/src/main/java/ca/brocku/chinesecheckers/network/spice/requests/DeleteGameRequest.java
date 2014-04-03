package ca.brocku.chinesecheckers.network.spice.requests;

import com.ccapi.receivables.SuccessReceivable;

import ca.brocku.chinesecheckers.network.spice.ApiRequest;

/**
 * @author kubasub
 * @date April 02, 2014
 */
public class DeleteGameRequest extends ApiRequest<SuccessReceivable> {
    private long userId;
    private long gameId;

    public DeleteGameRequest(long userId, long gameId) {
        super(DeleteGameRequest.class);

        this.userId = userId;
        this.gameId = gameId;
    }

    @Override
    public SuccessReceivable loadDataFromNetwork() throws Exception {
        String url = apiUrl("/games/delete/%s/%s", gameId, userId);

        return getRestTemplate().postForObject(url, null, SuccessReceivable.class);
    }
}
