package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.CcPlayer;

/**
 * Created by zz on 2/6/14.
 */
public class GameBoardTest {
    public GameBoardTest() {
        CcGameBoard board = new CcGameBoard();
        CcPlayer p1 = new CcPlayer("Peter");
        GridPosition test = new GridPosition(5,6);
        GridPosition[] testr = board.getPossibleMoves(new GridPiece(test, p1));
        printArray(testr);
    }
    public void printArray (GridPosition[] thing) {
        System.out.println("Possible Moves:");
        for(int i=0; i<thing.length; i++) {
            if (thing[i]==null) {
                continue;
            }
            System.out.println(thing[i].getRow()+ ", " + thing[i].getIndex());
        }
    }
    public static void main(String[] args) {
        new GameBoardTest();
    }
}
