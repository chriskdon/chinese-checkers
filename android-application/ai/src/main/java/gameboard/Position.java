package gameboard;

import java.io.Serializable;

/**
 * The common interface that defines a position
 * on the board.
 *
 * Returns the row a piece a occurs on and the index of the piece
 * within the row.
 *
 * See the architecture diagrams for a better description.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public class Position implements Serializable {
    int row, index;

    public Position(int row, int index) {
        this.row = row;
        this.index = index;
    }

    public int getRow() {
        return  row;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Equality test. Two positions are equal if they have the same row and index
     *
     * @param o The object to test for equality.
     * @return  True if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) { return false; }
        if(o == this) { return true; }
        if(o instanceof Position) {
            final Position x = (Position)o;

            return (getIndex() == x.getIndex() && getRow() == x.getRow());
        }

        return  false;
    }

    /**
     * Generate a hash code. Two positions with the same row/index will
     * generate the same hash code.
     *
     * @return  The hash code.
     */
    @Override
    public int hashCode() {
        return ("R" + getRow() + "I" + getIndex()).hashCode();
    }

}
