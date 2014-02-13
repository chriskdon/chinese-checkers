package ca.brocku.chinesecheckers.gameboard;

/**
 * The implementation of Position used to represent a position on the chinese checkers game grid.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class GridPosition implements Position{
    int row, index;
    public GridPosition(int r, int i) {
        row = r;
        index = i;
    }
    /**
     * Returns the row value of a position.
     *
     * @return  Row value
     */
    public int getRow() {
        return row;
    }
    /**
     * Returns the index or column value of a positon.
     *
     * @return  Index value
     */
    public int getIndex() {
        return index;
    }
}
