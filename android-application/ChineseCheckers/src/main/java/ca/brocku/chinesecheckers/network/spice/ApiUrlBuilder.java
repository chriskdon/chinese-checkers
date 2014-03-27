package ca.brocku.chinesecheckers.network.spice;

/**
 * Creates a url for the server request
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2014-03-27
 */
public class ApiUrlBuilder {
    public String build(String apiPath, Object... params) {
        if(apiPath.length() > 0 && !apiPath.startsWith("/")) {
            apiPath = "/" + apiPath;
        }

        return String.format("http://10.100.186.192:9000" + apiPath, params);
    }
}
