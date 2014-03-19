package ca.brocku.chinesecheckers.network.spice.pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * TODO: DELETE THIS CLASS - ONLY FOR EXAMPLE PLACEHOLDER UNTIL API IS CREATED
 *
 * Author: Chris Kellendonk
 * Student #: 4810800ˆ
 * Date: 3/11/2014
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Follower {

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
