package ca.brocku.chinesecheckers.tests;
//import org.junit.Test;
//import org.junit.Ignore;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.*;
import android.test.AndroidTestCase;

import ca.brocku.chinesecheckers.gameboard.*;

public class GridUnitTest extends AndroidTestCase  {

    //@Test
    public void testBoardCreate() {
    	CcGameBoard board = new CcGameBoard();
    	assertNotNull(board);
    }
//    @Test
    public void testGetPieceEmpty() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	assertNull(board.getPiece(temp));
    }
//    @Test
    public void testOutOfBoundsPiece() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(20,10);
    	assertNull(board.getPiece(temp));
    }
//    @Test
    public void testSetandGetPiece() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	board.setPiece(temp, 1);
    	//assertEquals(board.getPiece(temp).getPlayer().getName(), "TEST");
    	assertEquals(board.getPiece(temp).getPosition().getRow(), 5);
    	assertEquals(board.getPiece(temp).getPosition().getIndex(), 5);
    }
//    @Test
    public void testSetOutOfBounds() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(20,5);
    	board.setPiece(temp, 1);
    	Piece[] tt = board.getAllPieces();
    	for(int i=0; i<tt.length; i++) {
    		assertEquals(tt[i], null);
    	}
    }
//    @Test
    public void testDoubleSet() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
        board.setPiece(temp, 1);
        board.setPiece(temp, 2);
    	Piece t = board.getPiece(temp);
    	assertEquals(t.getPlayer(), 1);
    }
//    @Test
    public void testValidMoves() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	Position[] possibleMoves = board.getPossibleMoves(t);
    	for(int i=0; i<possibleMoves.length; i++) {
    		if(possibleMoves[i]==null){
    			continue;
    		}
    		assertEquals(board.isValidMove(t, possibleMoves[i]), true);
    	}
    }
//    @Test
    public void testInvalidMove() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	Position m = new GridPosition(9,9);
    	assertEquals(board.isValidMove(t, m), false);
    }
//    @Test
    public void testMoveonExistingPiece() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	Position temp2 = new GridPosition(5,6);
    	board.setPiece(temp2, 1);
    	Piece t = new GridPiece(temp, 1);
    	assertEquals(board.isValidMove(t, temp2), false);
    }
//    @Test
    public void testGetAllPiecesNotNull() {
    	CcGameBoard board = new CcGameBoard();
    	assertNotNull(board.getAllPieces());
    }
    public void testGetPiecesValid() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	Piece[] tt = board.getAllPieces();
    	for(int i=0;i<tt.length;i++) {
    		if(tt[i]!=null) {
    			assertEquals(tt[i].getPlayer(), t.getPlayer());
    		}
    	}
    }
//    @Test
    public void testMovePieceValid() {
    	CcGameBoard board = new CcGameBoard();
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	board.movePiece(t, new GridPosition(5,6));
    	Piece m = board.getPiece(new GridPosition(5,6));
    	assertEquals(m.getPosition().getRow(), 5);
    	assertEquals(m.getPosition().getIndex(), 6);
    	assertNull(board.getPiece(temp));
    }
//    @Test
    public void testWinCondition() {
    	CcGameBoard board = new CcGameBoard();
    	int p1 = 1;
    	int p2 = 2;
    	int p3 = 3;
    	int p4 = 4;
    	int p5 = 5;
    	int p6 = 6;
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(i, j), p1);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(7-i, j+9), p2);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(i+9, j+9), p3);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(16-i, j), p4);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(i+9, j), p5);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new GridPosition(7-i, j), p6);
    		}
    	}
    	assertEquals(board.checkWinCondition(p1), true);
    	assertEquals(board.checkWinCondition(p2), true);
    	assertEquals(board.checkWinCondition(p3), true);
    	assertEquals(board.checkWinCondition(p4), true);
    	assertEquals(board.checkWinCondition(p5), true);
    	assertEquals(board.checkWinCondition(p6), true);
    	Position temp = new GridPosition(3,0);
    	board.movePiece(board.getPiece(temp), new GridPosition(4,4));
    	assertEquals(board.checkWinCondition(p1), false);
    }
}
