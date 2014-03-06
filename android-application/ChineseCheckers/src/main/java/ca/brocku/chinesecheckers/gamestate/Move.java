package ca.brocku.chinesecheckers.gamestate;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;

/**
 * Represents a pieces total move. This could be a single peg move. Or multiple
 * hops.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class Move {
    private Piece pieceMoved;   // The piece that was moved.
    private List<Position> path;    // The path it took to get to its final position.

    /**
     * Constructor for the piece that moves and the path taken.
     *
     */
    public Move() {
        this.pieceMoved = pieceMoved;
        this.path = path;
    }

    /**
     * Constructor for the piece being moved with an unknown path at time of creation.
     *
     * @param pieceMoved    The piece that was moved.
     */
    public Move(Piece pieceMoved) {
        this(pieceMoved, new ArrayList<Position>());
    }

    /**
     * Add a position to the path.
     *
     * @param pos   The position to add.
     */
    public void addToPath(Position pos) {
        this.path.add(pos);
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    /**
     * Returns an unmodifiable path that the piece took.
     *
     * @return The path.
     */
    public List<Position> getPath() {
        return Collections.unmodifiableList(path);
    }
}
