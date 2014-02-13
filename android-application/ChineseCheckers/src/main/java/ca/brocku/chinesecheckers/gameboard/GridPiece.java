package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.CcPlayer;
import ca.brocku.chinesecheckers.gamestate.Player;
/**
 * Created by zz on 2/5/14.
 */
public class GridPiece implements Piece {
    Position position;
    Player player;
    public GridPiece (Position pos, Player pl) {
        position = pos;
        player = pl;
    }
    public Position getPosition() {
        return position;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPosition(Position at) {
        position=at;
    }
    public void setPlayer(Player np) {
        player = np;
    }
}
