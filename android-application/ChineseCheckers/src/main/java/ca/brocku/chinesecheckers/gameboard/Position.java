package ca.brocku.chinesecheckers.gameboard;

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
public interface Position {
    public int getRow();
    public int getIndex();
}
