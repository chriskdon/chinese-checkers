package ca.brocku.chinesecheckers.gameboard;

import ca.brocku.chinesecheckers.gamestate.CcPlayer;

/**
 * Created by zz on 2/6/14.
 */
public class GameBoardTest {
    public GameBoardTest() {
        CcGameBoard bo = new CcGameBoard();
        CcPlayer p1 = new CcPlayer("Peter");
        bo.setPiece(new GridPosition(9,1), p1);
        bo.setPiece(new GridPosition(8,0), p1);
        bo.setPiece(new GridPosition(10,0), p1);
        bo.setPiece(new GridPosition(10,1), p1);
        bo.setPiece(new GridPosition(3,0), p1);
        Position testCenter = new GridPosition(9,0); // the position to be tested
        Position validMove = new GridPosition(3,2); // a position to be tested for a valid move
        Piece centerPiece = new GridPiece(testCenter, p1); // position to be tested converted into a Piece
        Position[] possibleMoves = bo.getPossibleMoves(centerPiece); // possible moves generated
        printArray(possibleMoves); // output possible moves
        System.out.println(bo.isValidMove(centerPiece, validMove)); // check if move is valid
    }
    public void printArray (Position[] thing) {
        System.out.println("Possible Moves:");
        for(int i=0; i<thing.length; i++) {
            if (thing[i]!=null) {
                System.out.println(thing[i].getRow()+ ", " + thing[i].getIndex());
            }
        }
    }

    public static void main(String[] args) {
        new GameBoardTest();
    }
}
