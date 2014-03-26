package ca.brocku.chinesecheckers.gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.brocku.chinesecheckers.gameboard.AndroidPosition;

/**
 * Represents a pieces total move. This could be a single peg move. Or multiple
 * hops.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class AndroidMovePath extends javajar.gamestate.MovePath {
    private List<AndroidPosition> path;    // The path from start to end

    /**
     * Constructor for the piece that moves and the path taken.
     *
     * @param path          The path the piece took to move.
     */
    public AndroidMovePath(List<AndroidPosition> path) {
        if(path == null) {
            throw new IllegalArgumentException("Path cannot be null.");
        }

        this.path = path;
    }

    public AndroidMovePath(AndroidPosition from, AndroidPosition to) {
        super(from,to);
    }

    public AndroidMovePath(AndroidPosition start) {
        super(start);
    }

    public AndroidMovePath() {
        super();
    }

}
