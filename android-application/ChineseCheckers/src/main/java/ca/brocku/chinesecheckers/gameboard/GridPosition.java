package ca.brocku.chinesecheckers.gameboard;

/**
 * Created by zz on 2/5/14.
 */
public class GridPosition implements Position{
    int row, index;
    public GridPosition(int r, int i) {
        row = r;
        index = i;
    }
    public int getRow() {
        return row;
    }
    public int getIndex() {
        return index;
    }
}
