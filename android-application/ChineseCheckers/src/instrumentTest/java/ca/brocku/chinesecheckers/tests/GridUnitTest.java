package ca.brocku.chinesecheckers.tests;

import android.test.AndroidTestCase;

import ca.brocku.chinesecheckers.gameboard.*;
/**
 * Grid Unit Test for CcGameBoard.java
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/22/2014
 */
public class GridUnitTest extends AndroidTestCase  {
    /**
     * Create a new board.
     * @param numPlayers
     * @return
     */
    private GameBoard newBoard(int numPlayers) {
        GameBoard board = new CcGameBoard(numPlayers);

        return board;
    }

    private Position newPosition(int row, int index) {
        return new GridPosition(row, index);
    }

    //Testing that board creation does not result in a null object
    public void testBoardCreate() {
    	CcGameBoard board = new CcGameBoard(2);
    	assertNotNull(board);
    }

    /**
     * Automate the testing of checking the possible moves for a position.
     * @param row                   The row of the piece.
     * @param index                 The index of the piece.
     * @param numPossibleMoves      The positions it should have.
     */
    private void checkPossibleStartMoves(int row, int index, int numPossibleMoves) {
        GameBoard board = newBoard(6);

        try {
            Position[] possible = board.getPossibleMoves(board.getPiece(newPosition(row, index)));

            if(numPossibleMoves == 0) {
                assertNull("There should be no possible moves for (" + row + ", " + index + ")", possible);
            } else {
                assertEquals("There should be " + numPossibleMoves + " possible moves for (" + row + ", " + index + ")", numPossibleMoves, possible.length);
            }
        } catch(ArrayIndexOutOfBoundsException ex) {
            assertTrue("Index out of bounds for (" + row + ", " + index + ")", false);
        }
    }

    /**
     * Make sure the correct number of possible moves is returned for the starting positions.
     */
    public void testPossibleMovesCountCorners() {
        // Player 4
        checkPossibleStartMoves(0, 0, 0);
        checkPossibleStartMoves(1, 0, 0);
        checkPossibleStartMoves(1, 1, 0);
        checkPossibleStartMoves(2, 0, 2);
        checkPossibleStartMoves(2, 1, 2);
        checkPossibleStartMoves(2, 2, 2);
        checkPossibleStartMoves(3, 0, 2);
        checkPossibleStartMoves(3, 1, 2);
        checkPossibleStartMoves(3, 2, 2);
        checkPossibleStartMoves(3, 3, 2);

        // Player 3
        checkPossibleStartMoves(4, 0, 0);
        checkPossibleStartMoves(4, 1, 0);
        checkPossibleStartMoves(4, 2, 2);
        checkPossibleStartMoves(4, 3, 2);
        checkPossibleStartMoves(5, 0, 0);
        checkPossibleStartMoves(5, 1, 2);
        checkPossibleStartMoves(5, 2, 2);
        checkPossibleStartMoves(6, 0, 2);
        checkPossibleStartMoves(6, 1, 2);
        checkPossibleStartMoves(7, 0, 2);

        // Player 2
        checkPossibleStartMoves(9, 0, 2);
        checkPossibleStartMoves(10, 0, 2);
        checkPossibleStartMoves(10, 1, 2);
        checkPossibleStartMoves(11, 0, 0);
        checkPossibleStartMoves(11, 1, 2);
        checkPossibleStartMoves(11, 2, 2);
        checkPossibleStartMoves(12, 0, 0);
        checkPossibleStartMoves(12, 1, 0);
        checkPossibleStartMoves(12, 2, 2);
        checkPossibleStartMoves(12, 3, 2);

        // Player 1
        checkPossibleStartMoves(13, 0, 2);
        checkPossibleStartMoves(13, 1, 2);
        checkPossibleStartMoves(13, 2, 2);
        checkPossibleStartMoves(13, 3, 2);
        checkPossibleStartMoves(14, 0, 2);
        checkPossibleStartMoves(14, 1, 2);
        checkPossibleStartMoves(14, 2, 2);
        checkPossibleStartMoves(15, 0, 0);
        checkPossibleStartMoves(15, 1, 0);
        checkPossibleStartMoves(16, 0, 0);

        // Player 5
        checkPossibleStartMoves(4, 9, 2);
        checkPossibleStartMoves(4, 10, 2);
        checkPossibleStartMoves(4, 11, 0);
        checkPossibleStartMoves(4, 12, 0);
        checkPossibleStartMoves(5, 9, 2);
        checkPossibleStartMoves(5, 10, 2);
        checkPossibleStartMoves(5, 11, 0);
        checkPossibleStartMoves(6, 9, 2);
        checkPossibleStartMoves(6, 10, 2);
        checkPossibleStartMoves(7, 9, 2);

        // Player 6
        checkPossibleStartMoves(9, 9, 2);
        checkPossibleStartMoves(10, 9, 2);
        checkPossibleStartMoves(10, 10, 2);
        checkPossibleStartMoves(11, 9, 2);
        checkPossibleStartMoves(11, 10, 2);
        checkPossibleStartMoves(11, 11, 0);
        checkPossibleStartMoves(12, 9, 2);
        checkPossibleStartMoves(12, 10, 2);
        checkPossibleStartMoves(12, 11, 0);
        checkPossibleStartMoves(12, 12, 0);
    }

    /**
     * Check that the correct number of possible moves are returned.
     */
    public void testPossibleMovesForCenterPiece() {
        CcGameBoard board = new CcGameBoard(2);
        board.setPiece(newPosition(8, 4), 4);

        Position[] possible = board.getPossibleMoves(board.getPiece(newPosition(8,4)));

        assertEquals("Possible moves should be 6", 6, possible.length);
    }

//   Testing that retrieving a nonexistant piece results in a null
    public void testGetPieceEmpty() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	assertNull(board.getPiece(temp));
    }
//  Testing placement of a piece out of bounds and getting said piece results in a null
    public void testOutOfBoundsPiece() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(20,10);
    	assertNull(board.getPiece(temp));
    }
//  Testing the setting and getting of a valid piece
    public void testSetandGetPiece() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	board.setPiece(temp, 1);
    	//assertEquals(board.getPiece(temp).getPlayer().getName(), "TEST");
    	assertEquals(board.getPiece(temp).getPosition().getRow(), 5);
    	assertEquals(board.getPiece(temp).getPosition().getIndex(), 5);
    }
//  Testing to see if setting a piece out of bounds is not retreived by getAllPieces
    public void testSetOutOfBounds() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(20,5);
    	board.setPiece(temp, 1);
    	Piece[] tt = board.getAllPieces();

        int pieceCount = 0;
    	for(int i=0; i<tt.length; i++) {
            if(tt[i] != null) {
                pieceCount++;
            }
    	}
        assertEquals(20, pieceCount);
    }
// Testing to see if a piece is placed where another piece already exists, the piece is not overwritten
    public void testDoubleSet() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
        board.setPiece(temp, 1);
        board.setPiece(temp, 2);
    	Piece t = board.getPiece(temp);
    	assertEquals(t.getPlayerNumber(), 1);
    }
//  Testing to see that all moves retrieved by getPossibleMoves is valid
    public void testValidMoves() {
    	CcGameBoard board = new CcGameBoard(2);
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

    public void testCorrectNumberOfPiecesReturend() {
        assertEquals(20, new CcGameBoard(2).getAllPieces().length);
        assertEquals(30, new CcGameBoard(3).getAllPieces().length);
        assertEquals(40, new CcGameBoard(4).getAllPieces().length);
        assertEquals(60, new CcGameBoard(6).getAllPieces().length);
    }

//  Testing to see that a move outside of possibleMoves is invalid
    public void testInvalidMove() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	Position m = new GridPosition(9,9);
    	assertEquals(board.isValidMove(t, m), false);
    }
//  Testing to see if moving a piece on a position that already has a piece is not valid
    public void testMoveOnExistingPiece() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	Position temp2 = new GridPosition(5,6);
    	board.setPiece(temp2, 1);
    	Piece t = new GridPiece(temp, 1);
    	assertEquals(board.isValidMove(t, temp2), false);
    }
//  Testing to see if the array retrieved by getAllPieces is not a null object
    public void testGetAllPiecesNotNull() {
    	CcGameBoard board = new CcGameBoard(2);
    	assertNotNull(board.getAllPieces());
    }
//  Testing to see that a piece retrieved by getAllPieces is valid
    public void testGetPiecesValid() {
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	Piece[] tt = board.getAllPieces();
    	for(int i=0;i<tt.length;i++) {
    		if(tt[i] != null && tt[i].getPosition().getRow() == 5 &&
                    tt[i].getPosition().getIndex() == 5) {

    			assertEquals(tt[i].getPlayerNumber(), t.getPlayerNumber());
    		}
    	}
    }

//  Testing to see if movePiece changes the Position correctly
    public void testMovePieceValid() { // TODO: FIX THIS
    	CcGameBoard board = new CcGameBoard(2);
    	Position temp = new GridPosition(5,5);
    	Piece t = new GridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	board.movePiece(t, new GridPosition(5,6));
    	Piece m = board.getPiece(new GridPosition(5,6));
    	assertEquals(m.getPosition().getRow(), 5);
    	assertEquals(m.getPosition().getIndex(), 6);
    	assertNull(board.getPiece(temp));
    }

    public void testCreateBoardWithValidNumberOfPlayers() {
        try {
            new CcGameBoard(1);
            new CcGameBoard(5);
            assertTrue("Invalid player number board created.", false);
        } catch (IllegalArgumentException ex) {}

        try {
            new CcGameBoard(2);
            new CcGameBoard(3);
            new CcGameBoard(4);
            new CcGameBoard(6);
        } catch (IllegalArgumentException ex) {
            assertTrue("Valid player number board couldn't be created.", false);
        }
    }
    public void testAccuracyOfGetPossibleMoves() {
        CcGameBoard board = new CcGameBoard(2);
        Position[] possibleMoves;
        Position[] testPositions = {
                new GridPosition(2,1),
                new GridPosition(3,1),
                new GridPosition(4,6),
                new GridPosition(5,5),
                new GridPosition(7,4),
                new GridPosition(8,4),
                new GridPosition(9,4),
                new GridPosition(11,5),
                new GridPosition(12,6),
                new GridPosition(13,1),
                new GridPosition(14,0)
        };
        board.setPiece(new GridPosition(4,6), 1);
        board.setPiece(new GridPosition(5,5), 1);
        board.setPiece(new GridPosition(7,4), 1);
        board.setPiece(new GridPosition(8,4), 1);
        board.setPiece(new GridPosition(9,4), 1);
        board.setPiece(new GridPosition(11,5), 1);
        board.setPiece(new GridPosition(12,6), 1);
        Position[][] verifyMoves ={
                { // verify 2,1
                        new GridPosition(4,5),
                        new GridPosition(4,7)
                },
                { //verify 3,1
                        new GridPosition(4,5),
                        new GridPosition(5,6)
                },
                { //verify 4,6
                        new GridPosition(4,5),
                        new GridPosition(4,7),
                        new GridPosition(5,6),
                        new GridPosition(6,4)
                },
                { //verify 5,5
                        new GridPosition(4,5),
                        new GridPosition(5,4),
                        new GridPosition(5,6),
                        new GridPosition(6,4),
                        new GridPosition(6,5)
                },
                { //verify 7,4
                        new GridPosition(6,4),
                        new GridPosition(6,5),
                        new GridPosition(7,3),
                        new GridPosition(7,5),
                        new GridPosition(8,3),
                        new GridPosition(9,5)
                },
                { //verify 8,4
                        new GridPosition(7,5),
                        new GridPosition(8,3),
                        new GridPosition(8,5),
                        new GridPosition(9,5),
                        new GridPosition(10,4),
                        new GridPosition(6,4)
                },
                { //verify 9,4
                        new GridPosition(8,3),
                        new GridPosition(9,3),
                        new GridPosition(9,5),
                        new GridPosition(10,4),
                        new GridPosition(10,5),
                        new GridPosition(7,5),
                },
                { //verify 11,5
                        new GridPosition(10,4),
                        new GridPosition(10,5),
                        new GridPosition(11,4),
                        new GridPosition(11,6),
                        new GridPosition(12,5),
                },
                { //verify 12,6
                        new GridPosition(11,6),
                        new GridPosition(12,5),
                        new GridPosition(12,7),
                        new GridPosition(10,4),
                },
                { //verify 13,1
                        new GridPosition(12,5),
                        new GridPosition(11,6)
                },
                { //verify 14,0
                        new GridPosition(12,4)
                }
        };

        for(int i=0; i<testPositions.length; i++) {
            possibleMoves = board.getPossibleMoves(board.getPiece(testPositions[i]));
            for(int j=0; j<possibleMoves.length; j++) {
                assertEquals(possibleMoves[j].getRow(), verifyMoves[i][j].getRow());
                assertEquals(possibleMoves[j].getIndex(), verifyMoves[i][j].getIndex());
            }
        }

    }
//   Currently disabled. Tests to see if the win condition is triggered appropriately.
    // Call back should be tested by GameState??
/*    public void testWinCondition() {
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
    }*/
}
