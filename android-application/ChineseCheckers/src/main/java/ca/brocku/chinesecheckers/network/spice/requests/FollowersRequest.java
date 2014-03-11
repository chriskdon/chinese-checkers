package ca.brocku.chinesecheckers.network.spice.requests;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import ca.brocku.chinesecheckers.network.spice.pojos.FollowerList;

/**
 * TODO: DELETE THIS CLASS - ONLY FOR EXAMPLE PLACEHOLDER UNTIL API IS CREATED
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
//Create a request in its own Java file, it should not an inner class of a Context
public class FollowersRequest extends SpringAndroidSpiceRequest<FollowerList> {

    private String user;

    public FollowersRequest(String user) {
        super(FollowerList.class);
        this.user = user;
    }

    @Override
    public FollowerList loadDataFromNetwork() throws Exception {

        String url = String.format("https://api.github.com/users/%s/followers", user);

        return getRestTemplate().getForObject(url, FollowerList.class);
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     * @return
     */
    public String createCacheKey() {
        return "followers." + user;
    }
}
