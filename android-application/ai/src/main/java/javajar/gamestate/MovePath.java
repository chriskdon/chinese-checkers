package javajar.gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javajar.gameboard.Position;

/**
 * Represents a pieces total move. This could be a single peg move. Or multiple
 * hops.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class MovePath {
    private List<Position> path;    // The path from start to end

    /**
     * Constructor for the piece that moves and the path taken.
     *
     * @param path          The path the piece took to move.
     */
    public MovePath(List<Position> path) {
        if(path == null) {
            throw new IllegalArgumentException("Path cannot be null.");
        }

        this.path = path;
    }

    public MovePath(Position from, Position to) {
        if(from == null || to == null) {
            throw new IllegalArgumentException("From and To must be specified.");
        }

        this.path = new ArrayList<Position>(2);

        addToPath(from);
        addToPath(to);
    }

    public MovePath(Position start) {
        this(new ArrayList<Position>());

        addToPath(start);
    }

    public MovePath() {
        this(new ArrayList<Position>());
    }

    /**
     * Add a position to the path.
     *
     * @param pos   The position to add.
     */
    public void addToPath(Position pos) {
        this.path.add(pos);
    }

    /**
     * Removes the last position in the path.
     */
    public void removeEndPosition(){
        path.remove(path.size()-1);
    }

    /**
     * @return The position of the piece's starting location before it was moved.
     */
    public Position getStartPosition() {
        if(path.size() > 0) {
            return path.get(0);
        }

        throw new IllegalStateException("The path has not been added yet");
    }

    /**
     * @return The final resting position of the move that was made.
     */
    public Position getEndPosition() {
        if(path.size() > 0) {
            return path.get(path.size() - 1);
        }

        throw new IllegalStateException("The path has not been added yet");
    }

    public Position getPosition(int position){
        if(path.size() >= position)
            return path.get(position);

        throw new IllegalStateException("The path has not been added yet");
    }

    /**
     * Returns an unmodifiable path that the piece took.
     *
     * @return The path.
     */
    public List<Position> getPath() {
        return Collections.unmodifiableList(path);
    }

    /**
     * Is this position already in the path.
     * @param p
     * @return
     */
    public boolean contains(Position p) {
        for(Position pos : path) {
            if(pos.equals(p)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return How many positions are in the path.
     */
    public int size() {
        return path.size();
    }
}
