package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.CcPlayer;
/**
 * Created by zz on 2/5/14.
 */
public abstract class GridPiece implements Piece {
    GridPosition position;
    CcPlayer player;
    public GridPiece (GridPosition pos, CcPlayer pl) {
        position = pos;
        player = pl;
    }
    public GridPosition getPosition() {
        return position;
    }
    public CcPlayer getPlayer() {
        return player;
    }
}
