package ca.brocku.chinesecheckers.tests;

import android.test.AndroidTestCase;

import ca.brocku.chinesecheckers.gameboard.*;

import javajar.gameboard.Position;
/**
 * Grid Unit Test for AndroidCcGameBoard.java
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
    private AndroidGameBoard newBoard(int numPlayers) {
        AndroidGameBoard board = new AndroidCcGameBoard(numPlayers);

        return board;
    }

    private AndroidPosition newPosition(int row, int index) {
        return new AndroidPosition(row, index);
    }

    //Testing that board creation does not result in a null object
    public void testBoardCreate() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	assertNotNull(board);
    }

    /**
     * Automate the testing of checking the possible moves for a position.
     * @param row                   The row of the piece.
     * @param index                 The index of the piece.
     * @param numPossibleMoves      The positions it should have.
     */
    private void checkPossibleStartMoves(int row, int index, int numPossibleMoves) {
        AndroidGameBoard board = newBoard(6);

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
        AndroidCcGameBoard board = new AndroidCcGameBoard(2);
        board.setPiece(newPosition(8, 4), 4);

        Position[] possible = board.getPossibleMoves(board.getPiece(newPosition(8,4)));

        assertEquals("Possible moves should be 6", 6, possible.length);
    }

//   Testing that retrieving a nonexistant piece results in a null
    public void testGetPieceEmpty() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	assertNull(board.getPiece(temp));
    }
//  Testing placement of a piece out of bounds and getting said piece results in a null
    public void testOutOfBoundsPiece() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(20,10);
    	assertNull(board.getPiece(temp));
    }
//  Testing the setting and getting of a valid piece
    public void testSetandGetPiece() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	board.setPiece(temp, 1);
    	//assertEquals(board.getPiece(temp).getPlayer().getName(), "TEST");
    	assertEquals(board.getPiece(temp).getPosition().getRow(), 5);
    	assertEquals(board.getPiece(temp).getPosition().getIndex(), 5);
    }
//  Testing to see if setting a piece out of bounds is not retreived by getAllPieces
    public void testSetOutOfBounds() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(20,5);
    	board.setPiece(temp, 1);
    	AndroidPiece[] tt = board.getAllPieces();

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
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
        board.setPiece(temp, 1);
        board.setPiece(temp, 2);
    	AndroidPiece t = (AndroidPiece)board.getPiece(temp);
    	assertEquals(t.getPlayerNumber(), 1);
    }
//  Testing to see that all moves retrieved by getPossibleMoves is valid
    public void testValidMoves() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	AndroidPiece t = new AndroidGridPiece(temp, 1);
    	Position[] possibleMoves = board.getPossibleMoves(t);
    	for(int i=0; i<possibleMoves.length; i++) {
    		if(possibleMoves[i]==null){
    			continue;
    		}
    		assertEquals(board.isValidMove(t, possibleMoves[i]), true);
    	}
    }

    public void testCorrectNumberOfPiecesReturend() {
        assertEquals(20, new AndroidCcGameBoard(2).getAllPieces().length);
        assertEquals(30, new AndroidCcGameBoard(3).getAllPieces().length);
        assertEquals(40, new AndroidCcGameBoard(4).getAllPieces().length);
        assertEquals(60, new AndroidCcGameBoard(6).getAllPieces().length);
    }

//  Testing to see that a move outside of possibleMoves is invalid
    public void testInvalidMove() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	AndroidPiece t = new AndroidGridPiece(temp, 1);
    	AndroidPosition m = new AndroidPosition(9,9);
    	assertEquals(board.isValidMove(t, m), false);
    }
//  Testing to see if moving a piece on a position that already has a piece is not valid
    public void testMoveOnExistingPiece() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	AndroidPosition temp2 = new AndroidPosition(5,6);
    	board.setPiece(temp2, 1);
    	AndroidPiece t = new AndroidGridPiece(temp, 1);
    	assertEquals(board.isValidMove(t, temp2), false);
    }
//  Testing to see if the array retrieved by getAllPieces is not a null object
    public void testGetAllPiecesNotNull() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	assertNotNull(board.getAllPieces());
    }
//  Testing to see that a piece retrieved by getAllPieces is valid
    public void testGetPiecesValid() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	AndroidPiece t = new AndroidGridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	AndroidPiece[] tt = board.getAllPieces();
    	for(int i=0;i<tt.length;i++) {
    		if(tt[i] != null && tt[i].getPosition().getRow() == 5 &&
                    tt[i].getPosition().getIndex() == 5) {

    			assertEquals(tt[i].getPlayerNumber(), t.getPlayerNumber());
    		}
    	}
    }

//  Testing to see if movePiece changes the AndroidPosition correctly
    public void testMovePieceValid() { // TODO: FIX THIS
    	AndroidCcGameBoard board = new AndroidCcGameBoard(2);
    	AndroidPosition temp = new AndroidPosition(5,5);
    	AndroidPiece t = new AndroidGridPiece(temp, 1);
    	board.setPiece(temp, 1);
    	board.movePiece(t, new AndroidPosition(5,6));
    	AndroidPiece m = (AndroidPiece)board.getPiece(new AndroidPosition(5,6));
    	assertEquals(m.getPosition().getRow(), 5);
    	assertEquals(m.getPosition().getIndex(), 6);
    	assertNull(board.getPiece(temp));
    }

    public void testCreateBoardWithValidNumberOfPlayers() {
        try {
            new AndroidCcGameBoard(1);
            new AndroidCcGameBoard(5);
            assertTrue("Invalid player number board created.", false);
        } catch (IllegalArgumentException ex) {}

        try {
            new AndroidCcGameBoard(2);
            new AndroidCcGameBoard(3);
            new AndroidCcGameBoard(4);
            new AndroidCcGameBoard(6);
        } catch (IllegalArgumentException ex) {
            assertTrue("Valid player number board couldn't be created.", false);
        }
    }
    public void testAccuracyOfGetPossibleMoves() {
        AndroidPosition[] possibleMoves;
        AndroidPosition[] testPositions = {
                new AndroidPosition(0,0),
                new AndroidPosition(1,0),
                new AndroidPosition(1,1),
                new AndroidPosition(2,0),
                new AndroidPosition(2,1),
                new AndroidPosition(2,2),
                new AndroidPosition(3,0),
                new AndroidPosition(3,1),
                new AndroidPosition(3,2),
                new AndroidPosition(3,3),
                new AndroidPosition(4,0),
                new AndroidPosition(4,1),
                new AndroidPosition(4,2),
                new AndroidPosition(4,3),
                new AndroidPosition(4,4),
                new AndroidPosition(4,5),
                new AndroidPosition(4,6),
                new AndroidPosition(4,7),
                new AndroidPosition(4,8),
                new AndroidPosition(4,9),
                new AndroidPosition(4,10),
                new AndroidPosition(4,11),
                new AndroidPosition(4,12),
                new AndroidPosition(5,0),
                new AndroidPosition(5,1),
                new AndroidPosition(5,2),
                new AndroidPosition(5,3),
                new AndroidPosition(5,4),
                new AndroidPosition(5,5),
                new AndroidPosition(5,6),
                new AndroidPosition(5,7),
                new AndroidPosition(5,8),
                new AndroidPosition(5,9),
                new AndroidPosition(5,10),
                new AndroidPosition(5,11),
                new AndroidPosition(6,0),
                new AndroidPosition(6,1),
                new AndroidPosition(6,2),
                new AndroidPosition(6,3),
                new AndroidPosition(6,4),
                new AndroidPosition(6,5),
                new AndroidPosition(6,6),
                new AndroidPosition(6,7),
                new AndroidPosition(6,8),
                new AndroidPosition(6,9),
                new AndroidPosition(6,10),
                new AndroidPosition(7,0),
                new AndroidPosition(7,1),
                new AndroidPosition(7,2),
                new AndroidPosition(7,3),
                new AndroidPosition(7,4),
                new AndroidPosition(7,5),
                new AndroidPosition(7,6),
                new AndroidPosition(7,7),
                new AndroidPosition(7,8),
                new AndroidPosition(7,9),
                new AndroidPosition(8,0),
                new AndroidPosition(8,1),
                new AndroidPosition(8,2),
                new AndroidPosition(8,3),
                new AndroidPosition(8,4),
                new AndroidPosition(8,5),
                new AndroidPosition(8,6),
                new AndroidPosition(8,7),
                new AndroidPosition(8,8),
                new AndroidPosition(9,0),
                new AndroidPosition(9,1),
                new AndroidPosition(9,2),
                new AndroidPosition(9,3),
                new AndroidPosition(9,4),
                new AndroidPosition(9,5),
                new AndroidPosition(9,6),
                new AndroidPosition(9,7),
                new AndroidPosition(9,8),
                new AndroidPosition(9,9),
                new AndroidPosition(10,0),
                new AndroidPosition(10,1),
                new AndroidPosition(10,2),
                new AndroidPosition(10,3),
                new AndroidPosition(10,4),
                new AndroidPosition(10,5),
                new AndroidPosition(10,6),
                new AndroidPosition(10,7),
                new AndroidPosition(10,8),
                new AndroidPosition(10,9),
                new AndroidPosition(10,10),
                new AndroidPosition(11,0),
                new AndroidPosition(11,1),
                new AndroidPosition(11,2),
                new AndroidPosition(11,3),
                new AndroidPosition(11,4),
                new AndroidPosition(11,5),
                new AndroidPosition(11,6),
                new AndroidPosition(11,7),
                new AndroidPosition(11,8),
                new AndroidPosition(11,9),
                new AndroidPosition(11,10),
                new AndroidPosition(11,11),
                new AndroidPosition(12,0),
                new AndroidPosition(12,1),
                new AndroidPosition(12,2),
                new AndroidPosition(12,3),
                new AndroidPosition(12,4),
                new AndroidPosition(12,5),
                new AndroidPosition(12,6),
                new AndroidPosition(12,7),
                new AndroidPosition(12,8),
                new AndroidPosition(12,9),
                new AndroidPosition(12,10),
                new AndroidPosition(12,11),
                new AndroidPosition(12,12),
                new AndroidPosition(13,0),
                new AndroidPosition(13,1),
                new AndroidPosition(13,2),
                new AndroidPosition(13,3),
                new AndroidPosition(14,0),
                new AndroidPosition(14,1),
                new AndroidPosition(14,2),
                new AndroidPosition(15,0),
                new AndroidPosition(15,1),
                new AndroidPosition(16,0)
        };
        AndroidPosition[][] verifyMoves ={
                { // verify 0,0
                        new AndroidPosition(1,0),
                        new AndroidPosition(1,1),
                        new AndroidPosition(2,0),
                        new AndroidPosition(2,2)
                },
                { // verify 1,0
                        new AndroidPosition(0,0),
                        new AndroidPosition(1,1),
                        new AndroidPosition(2,0),
                        new AndroidPosition(2,1),
                        new AndroidPosition(3,0),
                        new AndroidPosition(3,2)
                },
                { // verify 1,1
                        new AndroidPosition(0,0),
                        new AndroidPosition(1,0),
                        new AndroidPosition(2,1),
                        new AndroidPosition(2,2),
                        new AndroidPosition(3,1),
                        new AndroidPosition(3,3)
                },
                { // verify 2,0
                        new AndroidPosition(1,0),
                        new AndroidPosition(2,1),
                        new AndroidPosition(3,0),
                        new AndroidPosition(3,1),
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,6),
                        new AndroidPosition(0,0),
                        new AndroidPosition(2,2)
                },
                { // verify 2,1
                        new AndroidPosition(1,0),
                        new AndroidPosition(1,1),
                        new AndroidPosition(2,0),
                        new AndroidPosition(2,2),
                        new AndroidPosition(3,1),
                        new AndroidPosition(3,2),
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,7)
                },
                { // verify 2,2
                        new AndroidPosition(1,1),
                        new AndroidPosition(2,1),
                        new AndroidPosition(3,2),
                        new AndroidPosition(3,3),
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,8),
                        new AndroidPosition(0,0),
                        new AndroidPosition(2,0)
                },
                { // verify 3,0
                        new AndroidPosition(2,0),
                        new AndroidPosition(3,1),
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,5),
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,5),
                        new AndroidPosition(1,0),
                        new AndroidPosition(3,2)
                },
                { // verify 3,1
                        new AndroidPosition(2,0),
                        new AndroidPosition(2,1),
                        new AndroidPosition(3,0),
                        new AndroidPosition(3,2),
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,6),
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,6),
                        new AndroidPosition(1,1),
                        new AndroidPosition(3,3)
                },
                { // verify 3,2
                        new AndroidPosition(2,1),
                        new AndroidPosition(2,2),
                        new AndroidPosition(3,1),
                        new AndroidPosition(3,3),
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,7),
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,7),
                        new AndroidPosition(1,0),
                        new AndroidPosition(3,0)
                },
                { // verify 3,3
                        new AndroidPosition(2,2),
                        new AndroidPosition(3,2),
                        new AndroidPosition(4,7),
                        new AndroidPosition(4,8),
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,8),
                        new AndroidPosition(1,1),
                        new AndroidPosition(3,1)
                },
                { // verify 4,0
                        new AndroidPosition(4,1),
                        new AndroidPosition(5,0),
                        new AndroidPosition(6,0),
                        new AndroidPosition(4,2)
                },
                { // verify 4,1
                        new AndroidPosition(4,0),
                        new AndroidPosition(4,2),
                        new AndroidPosition(5,0),
                        new AndroidPosition(5,1),
                        new AndroidPosition(6,1),
                        new AndroidPosition(4,3)
                },
                { // verify 4,2
                        new AndroidPosition(4,1),
                        new AndroidPosition(4,3),
                        new AndroidPosition(5,1),
                        new AndroidPosition(5,2),
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,2),
                        new AndroidPosition(4,0),
                        new AndroidPosition(4,4)
                },
                { // verify 4,3
                        new AndroidPosition(4,2),
                        new AndroidPosition(4,4),
                        new AndroidPosition(5,2),
                        new AndroidPosition(5,3),
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,3),
                        new AndroidPosition(4,1),
                        new AndroidPosition(4,5)
                },
                { // verify 4,4
                        new AndroidPosition(3,0),
                        new AndroidPosition(4,3),
                        new AndroidPosition(4,5),
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,4),
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,4),
                        new AndroidPosition(2,0),
                        new AndroidPosition(4,2),
                        new AndroidPosition(4,6)
                },
                { // verify 4,5
                        new AndroidPosition(3,0),
                        new AndroidPosition(3,1),
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,6),
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,5),
                        new AndroidPosition(6,3),
                        new AndroidPosition(6,5),
                        new AndroidPosition(2,1),
                        new AndroidPosition(4,3),
                        new AndroidPosition(4,7)
                },
                { // verify 4,6
                        new AndroidPosition(3,1),
                        new AndroidPosition(3,2),
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,7),
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,6),
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,6),
                        new AndroidPosition(2,0),
                        new AndroidPosition(2,2),
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,8)
                },
                { // verify 4,7
                        new AndroidPosition(3,2),
                        new AndroidPosition(3,3),
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,8),
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,7),
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,7),
                        new AndroidPosition(2,1),
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,9)
                },
                { // verify 4,8
                        new AndroidPosition(3,3),
                        new AndroidPosition(4,7),
                        new AndroidPosition(4,9),
                        new AndroidPosition(5,7),
                        new AndroidPosition(5,8),
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,8),
                        new AndroidPosition(2,2),
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,10)
                },
                { // verify 4,9
                        new AndroidPosition(4,8),
                        new AndroidPosition(4,10),
                        new AndroidPosition(5,8),
                        new AndroidPosition(5,9),
                        new AndroidPosition(6,7),
                        new AndroidPosition(6,9),
                        new AndroidPosition(4,7),
                        new AndroidPosition(4,11)
                },
                { // verify 4,10
                        new AndroidPosition(4,9),
                        new AndroidPosition(4,11),
                        new AndroidPosition(5,9),
                        new AndroidPosition(5,10),
                        new AndroidPosition(6,8),
                        new AndroidPosition(6,10),
                        new AndroidPosition(4,8),
                        new AndroidPosition(4,12)
                },
                { // verify 4,11
                        new AndroidPosition(4,10),
                        new AndroidPosition(4,12),
                        new AndroidPosition(5,10),
                        new AndroidPosition(5,11),
                        new AndroidPosition(6,9),
                        new AndroidPosition(4,9)
                },
                { // verify 4,12
                        new AndroidPosition(4,11),
                        new AndroidPosition(5,11),
                        new AndroidPosition(6,10),
                        new AndroidPosition(4,10)
                },
                { // verify 5,0
                        new AndroidPosition(4,0),
                        new AndroidPosition(4,1),
                        new AndroidPosition(5,1),
                        new AndroidPosition(6,0),
                        new AndroidPosition(7,0),
                        new AndroidPosition(5,2)
                },
                { // verify 5,1
                        new AndroidPosition(4,1),
                        new AndroidPosition(4,2),
                        new AndroidPosition(5,0),
                        new AndroidPosition(5,2),
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,1),
                        new AndroidPosition(7,1),
                        new AndroidPosition(5,3)
                },
                { // verify 5,2
                        new AndroidPosition(4,2),
                        new AndroidPosition(4,3),
                        new AndroidPosition(5,1),
                        new AndroidPosition(5,3),
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,2),
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,2),
                        new AndroidPosition(5,0),
                        new AndroidPosition(5,4)
                },
                { // verify 5,3
                        new AndroidPosition(4,3),
                        new AndroidPosition(4,4),
                        new AndroidPosition(5,2),
                        new AndroidPosition(5,4),
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,3),
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,3),
                        new AndroidPosition(3,0),
                        new AndroidPosition(5,1),
                        new AndroidPosition(5,5)
                },
                { // verify 5,4
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,5),
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,5),
                        new AndroidPosition(6,3),
                        new AndroidPosition(6,4),
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,4),
                        new AndroidPosition(3,1),
                        new AndroidPosition(5,2),
                        new AndroidPosition(5,6)
                },
                { // verify 5,5
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,6),
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,6),
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,5),
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,5),
                        new AndroidPosition(3,0),
                        new AndroidPosition(3,2),
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,7)
                },
                { // verify 5,6
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,7),
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,7),
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,6),
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,6),
                        new AndroidPosition(3,1),
                        new AndroidPosition(3,3),
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,8)
                },
                { // verify 5,7
                        new AndroidPosition(4,7),
                        new AndroidPosition(4,8),
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,8),
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,7),
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,7),
                        new AndroidPosition(3,2),
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,9)
                },
                { // verify 5,8
                        new AndroidPosition(4,8),
                        new AndroidPosition(4,9),
                        new AndroidPosition(5,7),
                        new AndroidPosition(5,9),
                        new AndroidPosition(6,7),
                        new AndroidPosition(6,8),
                        new AndroidPosition(7,6),
                        new AndroidPosition(7,8),
                        new AndroidPosition(3,3),
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,10)
                },
                { // verify 5,9
                        new AndroidPosition(4,9),
                        new AndroidPosition(4,10),
                        new AndroidPosition(5,8),
                        new AndroidPosition(5,10),
                        new AndroidPosition(6,8),
                        new AndroidPosition(6,9),
                        new AndroidPosition(7,7),
                        new AndroidPosition(7,9),
                        new AndroidPosition(5,7),
                        new AndroidPosition(5,11)
                },
                { // verify 5,10
                        new AndroidPosition(4,10),
                        new AndroidPosition(4,11),
                        new AndroidPosition(5,9),
                        new AndroidPosition(5,11),
                        new AndroidPosition(6,9),
                        new AndroidPosition(6,10),
                        new AndroidPosition(7,8),
                        new AndroidPosition(5,8)
                },
                { // verify 5,11
                        new AndroidPosition(4,11),
                        new AndroidPosition(4,12),
                        new AndroidPosition(5,10),
                        new AndroidPosition(6,10),
                        new AndroidPosition(7,9),
                        new AndroidPosition(5,9)
                },
                { // verify 6,0
                        new AndroidPosition(5,0),
                        new AndroidPosition(5,1),
                        new AndroidPosition(6,1),
                        new AndroidPosition(7,0),
                        new AndroidPosition(8,0),
                        new AndroidPosition(4,0),
                        new AndroidPosition(4,2),
                        new AndroidPosition(6,2)
                },
                { // verify 6,1
                        new AndroidPosition(5,1),
                        new AndroidPosition(5,2),
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,2),
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,1),
                        new AndroidPosition(8,1),
                        new AndroidPosition(4,1),
                        new AndroidPosition(4,3),
                        new AndroidPosition(6,3)
                },
                { // verify 6,2
                        new AndroidPosition(5,2),
                        new AndroidPosition(5,3),
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,3),
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,2),
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,2),
                        new AndroidPosition(4,2),
                        new AndroidPosition(4,4),
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,4)
                },
                { // verify 6,3
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,4),
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,4),
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,3),
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,3),
                        new AndroidPosition(4,3),
                        new AndroidPosition(4,5),
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,5)
                },
                { // verify 6,4
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,5),
                        new AndroidPosition(6,3),
                        new AndroidPosition(6,5),
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,4),
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,4),
                        new AndroidPosition(4,4),
                        new AndroidPosition(4,6),
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,6)
                },
                { // verify 6,5
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,6),
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,6),
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,5),
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,5),
                        new AndroidPosition(4,5),
                        new AndroidPosition(4,7),
                        new AndroidPosition(6,3),
                        new AndroidPosition(6,7)
                },
                { // verify 6,6
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,7),
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,7),
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,6),
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,6),
                        new AndroidPosition(4,6),
                        new AndroidPosition(4,8),
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,8)
                },
                { // verify 6,7
                        new AndroidPosition(5,7),
                        new AndroidPosition(5,8),
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,8),
                        new AndroidPosition(7,6),
                        new AndroidPosition(7,7),
                        new AndroidPosition(8,5),
                        new AndroidPosition(8,7),
                        new AndroidPosition(4,7),
                        new AndroidPosition(4,9),
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,9)
                },
                { // verify 6,8
                        new AndroidPosition(5,8),
                        new AndroidPosition(5,9),
                        new AndroidPosition(6,7),
                        new AndroidPosition(6,9),
                        new AndroidPosition(7,7),
                        new AndroidPosition(7,8),
                        new AndroidPosition(8,6),
                        new AndroidPosition(8,8),
                        new AndroidPosition(4,8),
                        new AndroidPosition(4,10),
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,10)
                },
                { // verify 6,9
                        new AndroidPosition(5,9),
                        new AndroidPosition(5,10),
                        new AndroidPosition(6,8),
                        new AndroidPosition(6,10),
                        new AndroidPosition(7,8),
                        new AndroidPosition(7,9),
                        new AndroidPosition(8,7),
                        new AndroidPosition(4,9),
                        new AndroidPosition(4,11),
                        new AndroidPosition(6,7)
                },
                { // verify 6,10
                        new AndroidPosition(5,10),
                        new AndroidPosition(5,11),
                        new AndroidPosition(6,9),
                        new AndroidPosition(7,9),
                        new AndroidPosition(8,8),
                        new AndroidPosition(4,10),
                        new AndroidPosition(4,12),
                        new AndroidPosition(6,8)
                },
                { // verify 7,0
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,1),
                        new AndroidPosition(7,1),
                        new AndroidPosition(8,0),
                        new AndroidPosition(9,1),
                        new AndroidPosition(5,0),
                        new AndroidPosition(5,2),
                        new AndroidPosition(7,2)
                },
                { // verify 7,1
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,2),
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,2),
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,1),
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,2),
                        new AndroidPosition(5,1),
                        new AndroidPosition(5,3),
                        new AndroidPosition(7,3)
                },
                { // verify 7,2
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,3),
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,3),
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,2),
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,3),
                        new AndroidPosition(5,2),
                        new AndroidPosition(5,4),
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,4)
                },
                { // verify 7,3
                        new AndroidPosition(6,3),

                        new AndroidPosition(6,4),
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,4),
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,3),
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,4),
                        new AndroidPosition(5,3),
                        new AndroidPosition(5,5),
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,5)
                },
                { // verify 7,4
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,5),
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,5),
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,4),
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,5),
                        new AndroidPosition(5,4),
                        new AndroidPosition(5,6),
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,6)
                },
                { // verify 7,5
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,6),
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,6),
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,5),
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,6),
                        new AndroidPosition(5,5),
                        new AndroidPosition(5,7),
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,7)
                },
                { // verify 7,6
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,7),
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,7),
                        new AndroidPosition(8,5),
                        new AndroidPosition(8,6),
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,7),
                        new AndroidPosition(5,6),
                        new AndroidPosition(5,8),
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,8)
                },
                { // verify 7,7
                        new AndroidPosition(6,7),
                        new AndroidPosition(6,8),
                        new AndroidPosition(7,6),
                        new AndroidPosition(7,8),
                        new AndroidPosition(8,6),
                        new AndroidPosition(8,7),
                        new AndroidPosition(9,6),
                        new AndroidPosition(9,8),
                        new AndroidPosition(5,7),
                        new AndroidPosition(5,9),
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,9)
                },
                { // verify 7,8
                        new AndroidPosition(6,8),
                        new AndroidPosition(6,9),
                        new AndroidPosition(7,7),
                        new AndroidPosition(7,9),
                        new AndroidPosition(8,7),
                        new AndroidPosition(8,8),
                        new AndroidPosition(9,7),
                        new AndroidPosition(9,9),
                        new AndroidPosition(5,8),
                        new AndroidPosition(5,10),
                        new AndroidPosition(7,6)
                },
                { // verify 7,9
                        new AndroidPosition(6,9),
                        new AndroidPosition(6,10),
                        new AndroidPosition(7,8),
                        new AndroidPosition(8,8),
                        new AndroidPosition(9,8),
                        new AndroidPosition(5,9),
                        new AndroidPosition(5,11),

                        new AndroidPosition(7,7)
                },
                { // verify 8,0
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,1),
                        new AndroidPosition(8,1),
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,1),
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,2),
                        new AndroidPosition(6,0),
                        new AndroidPosition(6,2),
                        new AndroidPosition(8,2)
                },
                { // verify 8,1
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,2),
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,2),
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,2),
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,3),
                        new AndroidPosition(6,1),
                        new AndroidPosition(6,3),
                        new AndroidPosition(8,3)
                },
                { // verify 8,2
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,3),
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,3),
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,3),
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,4),
                        new AndroidPosition(6,2),
                        new AndroidPosition(6,4),
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,4)
                },
                { // verify 8,3
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,4),
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,4),
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,4),
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,5),
                        new AndroidPosition(6,3),
                        new AndroidPosition(6,5),
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,5)
                },
                { // verify 8,4
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,5),
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,5),
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,5),
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,6),
                        new AndroidPosition(6,4),
                        new AndroidPosition(6,6),
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,6)
                },
                { // verify 8,5
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,6),
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,6),
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,6),
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,7),
                        new AndroidPosition(6,5),
                        new AndroidPosition(6,7),
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,7)
                },
                { // verify 8,6
                        new AndroidPosition(7,6),
                        new AndroidPosition(7,7),
                        new AndroidPosition(8,5),
                        new AndroidPosition(8,7),
                        new AndroidPosition(9,6),
                        new AndroidPosition(9,7),
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,8),
                        new AndroidPosition(6,6),
                        new AndroidPosition(6,8),
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,8)
                },
                { // verify 8,7
                        new AndroidPosition(7,7),
                        new AndroidPosition(7,8),
                        new AndroidPosition(8,6),
                        new AndroidPosition(8,8),
                        new AndroidPosition(9,7),
                        new AndroidPosition(9,8),
                        new AndroidPosition(10,7),
                        new AndroidPosition(10,9),
                        new AndroidPosition(6,7),
                        new AndroidPosition(6,9),
                        new AndroidPosition(8,5)
                },
                { // verify 8,8
                        new AndroidPosition(7,8),
                        new AndroidPosition(7,9),
                        new AndroidPosition(8,7),
                        new AndroidPosition(9,8),
                        new AndroidPosition(9,9),
                        new AndroidPosition(10,8),
                        new AndroidPosition(10,10),
                        new AndroidPosition(6,8),
                        new AndroidPosition(6,10),
                        new AndroidPosition(8,6)
                },
                { // verify 9,0
                        new AndroidPosition(8,0),
                        new AndroidPosition(9,1),
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,1),
                        new AndroidPosition(11,0),
                        new AndroidPosition(11,2),
                        new AndroidPosition(7,1),
                        new AndroidPosition(9,2)
                },
                { // verify 9,1
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,1),
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,2),
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,2),
                        new AndroidPosition(11,1),
                        new AndroidPosition(11,3),
                        new AndroidPosition(7,0),
                        new AndroidPosition(7,2),
                        new AndroidPosition(9,3)
                },
                { // verify 9,2
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,2),
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,3),
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,3),
                        new AndroidPosition(11,2),
                        new AndroidPosition(11,4),
                        new AndroidPosition(7,1),
                        new AndroidPosition(7,3),
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,4)
                },
                { // verify 9,3
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,3),
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,4),
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,4),
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,5),
                        new AndroidPosition(7,2),
                        new AndroidPosition(7,4),
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,5)
                },
                { // verify 9,4
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,4),
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,5),
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,5),
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,6),
                        new AndroidPosition(7,3),
                        new AndroidPosition(7,5),
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,6)
                },
                { // verify 9,5
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,5),
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,6),
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,6),
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,7),
                        new AndroidPosition(7,4),
                        new AndroidPosition(7,6),
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,7)
                },
                { // verify 9,6
                        new AndroidPosition(8,5),
                        new AndroidPosition(8,6),
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,7),
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,7),
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,8),
                        new AndroidPosition(7,5),
                        new AndroidPosition(7,7),
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,8)
                },
                { // verify 9,7
                        new AndroidPosition(8,6),
                        new AndroidPosition(8,7),
                        new AndroidPosition(9,6),
                        new AndroidPosition(9,8),
                        new AndroidPosition(10,7),
                        new AndroidPosition(10,8),
                        new AndroidPosition(11,7),
                        new AndroidPosition(11,9),
                        new AndroidPosition(7,6),
                        new AndroidPosition(7,8),
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,9)
                },
                { // verify 9,8
                        new AndroidPosition(8,7),
                        new AndroidPosition(8,8),
                        new AndroidPosition(9,7),
                        new AndroidPosition(9,9),
                        new AndroidPosition(10,8),
                        new AndroidPosition(10,9),
                        new AndroidPosition(11,8),
                        new AndroidPosition(11,10),
                        new AndroidPosition(7,7),
                        new AndroidPosition(7,9),
                        new AndroidPosition(9,6)
                },
                { // verify 9,9
                        new AndroidPosition(8,8),
                        new AndroidPosition(9,8),
                        new AndroidPosition(10,9),
                        new AndroidPosition(10,10),
                        new AndroidPosition(11,9),
                        new AndroidPosition(11,11),
                        new AndroidPosition(7,8),
                        new AndroidPosition(9,7)
                },
                { // verify 10,0
                        new AndroidPosition(9,0),
                        new AndroidPosition(10,1),
                        new AndroidPosition(11,0),
                        new AndroidPosition(11,1),
                        new AndroidPosition(12,0),
                        new AndroidPosition(12,2),
                        new AndroidPosition(8,0),
                        new AndroidPosition(10,2)
                },
                { // verify 10,1
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,1),
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,2),
                        new AndroidPosition(11,1),

                        new AndroidPosition(11,2),
                        new AndroidPosition(12,1),
                        new AndroidPosition(12,3),
                        new AndroidPosition(8,1),
                        new AndroidPosition(10,3)
                },
                { // verify 10,2
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,2),
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,3),
                        new AndroidPosition(11,2),
                        new AndroidPosition(11,3),
                        new AndroidPosition(12,2),
                        new AndroidPosition(12,4),
                        new AndroidPosition(8,0),
                        new AndroidPosition(8,2),
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,4)
                },
                { // verify 10,3
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,3),
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,4),
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,4),
                        new AndroidPosition(12,3),
                        new AndroidPosition(12,5),
                        new AndroidPosition(8,1),
                        new AndroidPosition(8,3),
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,5)
                },
                { // verify 10,4
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,4),
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,5),
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,5),
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,6),
                        new AndroidPosition(8,2),
                        new AndroidPosition(8,4),
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,6)
                },
                { // verify 10,5
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,5),
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,6),
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,6),
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,7),
                        new AndroidPosition(8,3),
                        new AndroidPosition(8,5),
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,7)
                },
                { // verify 10,6
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,6),
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,7),
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,7),
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,8),
                        new AndroidPosition(8,4),
                        new AndroidPosition(8,6),
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,8)
                },
                { // verify 10,7
                        new AndroidPosition(9,6),
                        new AndroidPosition(9,7),
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,8),
                        new AndroidPosition(11,7),
                        new AndroidPosition(11,8),
                        new AndroidPosition(12,7),
                        new AndroidPosition(12,9),
                        new AndroidPosition(8,5),
                        new AndroidPosition(8,7),
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,9)
                },
                { // verify 10,8
                        new AndroidPosition(9,7),
                        new AndroidPosition(9,8),
                        new AndroidPosition(10,7),
                        new AndroidPosition(10,9),
                        new AndroidPosition(11,8),
                        new AndroidPosition(11,9),
                        new AndroidPosition(12,8),
                        new AndroidPosition(12,10),
                        new AndroidPosition(8,6),
                        new AndroidPosition(8,8),
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,10)
                },
                { // verify 10,9
                        new AndroidPosition(9,8),
                        new AndroidPosition(9,9),
                        new AndroidPosition(10,8),
                        new AndroidPosition(10,10),
                        new AndroidPosition(11,9),
                        new AndroidPosition(11,10),
                        new AndroidPosition(12,9),
                        new AndroidPosition(12,11),
                        new AndroidPosition(8,7),
                        new AndroidPosition(10,7)
                },
                { // verify 10,10
                        new AndroidPosition(9,9),
                        new AndroidPosition(10,9),
                        new AndroidPosition(11,10),
                        new AndroidPosition(11,11),
                        new AndroidPosition(12,10),
                        new AndroidPosition(12,12),
                        new AndroidPosition(8,8),
                        new AndroidPosition(10,8)
                },
                { // verify 11,0
                        new AndroidPosition(10,0),
                        new AndroidPosition(11,1),
                        new AndroidPosition(12,0),
                        new AndroidPosition(12,1),

                        new AndroidPosition(9,0),
                        new AndroidPosition(11,2)
                },
                { // verify 11,1
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,1),
                        new AndroidPosition(11,0),
                        new AndroidPosition(11,2),
                        new AndroidPosition(12,1),
                        new AndroidPosition(12,2),
                        new AndroidPosition(9,1),
                        new AndroidPosition(11,3)
                },
                { // verify 11,2
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,2),
                        new AndroidPosition(11,1),
                        new AndroidPosition(11,3),
                        new AndroidPosition(12,2),
                        new AndroidPosition(12,3),
                        new AndroidPosition(9,0),
                        new AndroidPosition(9,2),
                        new AndroidPosition(11,0),
                        new AndroidPosition(11,4)
                },
                { // verify 11,3
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,3),
                        new AndroidPosition(11,2),
                        new AndroidPosition(11,4),
                        new AndroidPosition(12,3),
                        new AndroidPosition(12,4),
                        new AndroidPosition(13,0),
                        new AndroidPosition(9,1),
                        new AndroidPosition(9,3),
                        new AndroidPosition(11,1),
                        new AndroidPosition(11,5)
                },
                { // verify 11,4
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,4),
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,5),
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,5),
                        new AndroidPosition(13,1),
                        new AndroidPosition(9,2),
                        new AndroidPosition(9,4),
                        new AndroidPosition(11,2),
                        new AndroidPosition(11,6)
                },
                { // verify 11,5
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,5),
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,6),
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,6),
                        new AndroidPosition(13,0),
                        new AndroidPosition(13,2),
                        new AndroidPosition(9,3),
                        new AndroidPosition(9,5),
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,7)
                },
                { // verify 11,6
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,6),
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,7),
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,7),
                        new AndroidPosition(13,1),
                        new AndroidPosition(13,3),
                        new AndroidPosition(9,4),
                        new AndroidPosition(9,6),
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,8)
                },
                { // verify 11,7
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,7),
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,8),
                        new AndroidPosition(12,7),
                        new AndroidPosition(12,8),
                        new AndroidPosition(13,2),
                        new AndroidPosition(9,5),
                        new AndroidPosition(9,7),
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,9)
                },
                { // verify 11,8
                        new AndroidPosition(10,7),
                        new AndroidPosition(10,8),
                        new AndroidPosition(11,7),
                        new AndroidPosition(11,9),
                        new AndroidPosition(12,8),
                        new AndroidPosition(12,9),
                        new AndroidPosition(13,3),
                        new AndroidPosition(9,6),
                        new AndroidPosition(9,8),
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,10)
                },
                { // verify 11,9
                        new AndroidPosition(10,8),
                        new AndroidPosition(10,9),
                        new AndroidPosition(11,8),
                        new AndroidPosition(11,10),
                        new AndroidPosition(12,9),
                        new AndroidPosition(12,10),
                        new AndroidPosition(9,7),
                        new AndroidPosition(9,9),
                        new AndroidPosition(11,7),
                        new AndroidPosition(11,11)
                },
                { // verify 11,10
                        new AndroidPosition(10,9),
                        new AndroidPosition(10,10),
                        new AndroidPosition(11,9),
                        new AndroidPosition(11,11),
                        new AndroidPosition(12,10),
                        new AndroidPosition(12,11),
                        new AndroidPosition(9,8),
                        new AndroidPosition(11,8)
                },
                { // verify 11,11
                        new AndroidPosition(10,10),
                        new AndroidPosition(11,10),
                        new AndroidPosition(12,11),
                        new AndroidPosition(12,12),
                        new AndroidPosition(9,9),
                        new AndroidPosition(11,9)
                },
                { // verify 12,0
                        new AndroidPosition(11,0),
                        new AndroidPosition(12,1),
                        new AndroidPosition(10,0),
                        new AndroidPosition(12,2)
                },
                { // verify 12,1
                        new AndroidPosition(11,0),
                        new AndroidPosition(11,1),
                        new AndroidPosition(12,0),
                        new AndroidPosition(12,2),
                        new AndroidPosition(10,1),
                        new AndroidPosition(12,3)
                },
                { // verify 12,2
                        new AndroidPosition(11,1),
                        new AndroidPosition(11,2),
                        new AndroidPosition(12,1),
                        new AndroidPosition(12,3),
                        new AndroidPosition(10,0),
                        new AndroidPosition(10,2),
                        new AndroidPosition(12,0),
                        new AndroidPosition(12,4)
                },
                { // verify 12,3
                        new AndroidPosition(11,2),
                        new AndroidPosition(11,3),
                        new AndroidPosition(12,2),
                        new AndroidPosition(12,4),
                        new AndroidPosition(10,1),
                        new AndroidPosition(10,3),
                        new AndroidPosition(12,1),
                        new AndroidPosition(12,5)
                },
                { // verify 12,4
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,4),
                        new AndroidPosition(12,3),
                        new AndroidPosition(12,5),
                        new AndroidPosition(13,0),
                        new AndroidPosition(14,0),
                        new AndroidPosition(10,2),
                        new AndroidPosition(10,4),
                        new AndroidPosition(12,2),
                        new AndroidPosition(12,6)
                },
                { // verify 12,5
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,5),
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,6),
                        new AndroidPosition(13,0),
                        new AndroidPosition(13,1),
                        new AndroidPosition(14,1),
                        new AndroidPosition(10,3),
                        new AndroidPosition(10,5),
                        new AndroidPosition(12,3),
                        new AndroidPosition(12,7)
                },
                { // verify 12,6
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,6),
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,7),
                        new AndroidPosition(13,1),
                        new AndroidPosition(13,2),
                        new AndroidPosition(14,0),
                        new AndroidPosition(14,2),
                        new AndroidPosition(10,4),
                        new AndroidPosition(10,6),
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,8)
                },
                { // verify 12,7
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,7),
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,8),
                        new AndroidPosition(13,2),
                        new AndroidPosition(13,3),
                        new AndroidPosition(14,1),
                        new AndroidPosition(10,5),
                        new AndroidPosition(10,7),
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,9)
                },
                { // verify 12,8
                        new AndroidPosition(11,7),
                        new AndroidPosition(11,8),
                        new AndroidPosition(12,7),
                        new AndroidPosition(12,9),
                        new AndroidPosition(13,3),
                        new AndroidPosition(14,2),
                        new AndroidPosition(10,6),
                        new AndroidPosition(10,8),
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,10)
                },
                { // verify 12,9
                        new AndroidPosition(11,8),
                        new AndroidPosition(11,9),
                        new AndroidPosition(12,8),
                        new AndroidPosition(12,10),
                        new AndroidPosition(10,7),
                        new AndroidPosition(10,9),
                        new AndroidPosition(12,7),
                        new AndroidPosition(12,11)
                },
                { // verify 12,10
                        new AndroidPosition(11,9),
                        new AndroidPosition(11,10),
                        new AndroidPosition(12,9),
                        new AndroidPosition(12,11),
                        new AndroidPosition(10,8),
                        new AndroidPosition(10,10),
                        new AndroidPosition(12,8),
                        new AndroidPosition(12,12)
                },
                { // verify 12,11
                        new AndroidPosition(11,10),
                        new AndroidPosition(11,11),
                        new AndroidPosition(12,10),
                        new AndroidPosition(12,12),
                        new AndroidPosition(10,9),
                        new AndroidPosition(12,9)
                },
                { // verify 12,12
                        new AndroidPosition(11,11),
                        new AndroidPosition(12,11),
                        new AndroidPosition(10,10),
                        new AndroidPosition(12,10)
                },
                { // verify 13,0
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,5),
                        new AndroidPosition(13,1),
                        new AndroidPosition(14,0),
                        new AndroidPosition(15,0),
                        new AndroidPosition(11,3),
                        new AndroidPosition(11,5),
                        new AndroidPosition(13,2)
                },
                { // verify 13,1
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,6),
                        new AndroidPosition(13,0),
                        new AndroidPosition(13,2),
                        new AndroidPosition(14,0),
                        new AndroidPosition(14,1),
                        new AndroidPosition(15,1),
                        new AndroidPosition(11,4),
                        new AndroidPosition(11,6),
                        new AndroidPosition(13,3)
                },
                { // verify 13,2
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,7),
                        new AndroidPosition(13,1),
                        new AndroidPosition(13,3),
                        new AndroidPosition(14,1),
                        new AndroidPosition(14,2),
                        new AndroidPosition(15,0),
                        new AndroidPosition(11,5),
                        new AndroidPosition(11,7),
                        new AndroidPosition(13,0)
                },
                { // verify 13,3
                        new AndroidPosition(12,7),
                        new AndroidPosition(12,8),
                        new AndroidPosition(13,2),
                        new AndroidPosition(14,2),
                        new AndroidPosition(15,1),
                        new AndroidPosition(11,6),
                        new AndroidPosition(11,8),
                        new AndroidPosition(13,1)
                },
                { // verify 14,0
                        new AndroidPosition(13,0),
                        new AndroidPosition(13,1),
                        new AndroidPosition(14,1),
                        new AndroidPosition(15,0),
                        new AndroidPosition(16,0),
                        new AndroidPosition(12,4),
                        new AndroidPosition(12,6),
                        new AndroidPosition(14,2)
                },
                { // verify 14,1
                        new AndroidPosition(13,1),
                        new AndroidPosition(13,2),
                        new AndroidPosition(14,0),
                        new AndroidPosition(14,2),
                        new AndroidPosition(15,0),
                        new AndroidPosition(15,1),
                        new AndroidPosition(12,5),
                        new AndroidPosition(12,7)
                },
                { // verify 14,2
                        new AndroidPosition(13,2),
                        new AndroidPosition(13,3),
                        new AndroidPosition(14,1),
                        new AndroidPosition(15,1),
                        new AndroidPosition(16,0),
                        new AndroidPosition(12,6),
                        new AndroidPosition(12,8),
                        new AndroidPosition(14,0)
                },
                { // verify 15,0
                        new AndroidPosition(14,0),
                        new AndroidPosition(14,1),
                        new AndroidPosition(15,1),
                        new AndroidPosition(16,0),
                        new AndroidPosition(13,0),
                        new AndroidPosition(13,2)
                },
                { // verify 15,1
                        new AndroidPosition(14,1),
                        new AndroidPosition(14,2),
                        new AndroidPosition(15,0),
                        new AndroidPosition(16,0),
                        new AndroidPosition(13,1),
                        new AndroidPosition(13,3)
                },
                { // verify 16,0
                        new AndroidPosition(15,0),
                        new AndroidPosition(15,1),
                        new AndroidPosition(14,0),
                        new AndroidPosition(14,2)
                }
        };

        int check;
        // Tests immediate neighbours
        for(int i=0; i<testPositions.length; i++) {
            AndroidCcGameBoard board = new AndroidCcGameBoard();
            board.setPiece(testPositions[i], 1);
            possibleMoves = board.getPossibleMoves(board.getPiece(testPositions[i]));
            for(int j=0; j<possibleMoves.length; j++) {
                check=0;
                for(int k=0; k<verifyMoves[i].length;k++){
                    if(possibleMoves[j].getRow() == verifyMoves[i][j].getRow() && possibleMoves[j].getIndex() == verifyMoves[i][j].getIndex()) {
                        check=1;
                    }
                }
                assertEquals(check, 1);
            }
        }
        //Tests hopping positions
        for(int i=0; i<testPositions.length; i++) {
            AndroidCcGameBoard board = new AndroidCcGameBoard();
            board.setPiece(testPositions[i], 1);
            possibleMoves = board.getPossibleMoves(board.getPiece(testPositions[i]));
            for(int w=0; w<possibleMoves.length; w++) {
                board.setPiece(possibleMoves[w], 1);
            }
            for(int j=0; j<possibleMoves.length; j++) {
                check=0;
                for(int k=0; k<verifyMoves[i].length;k++){
                    if(possibleMoves[j].getRow() == verifyMoves[i][j].getRow() && possibleMoves[j].getIndex() == verifyMoves[i][j].getIndex()) {
                        check=1;
                    }
                }
                assertEquals(check, 1);
            }
        }
    }

//   Currently disabled. Tests to see if the win condition is triggered appropriately.
    // Call back should be tested by GameState??
/*    public void testWinCondition() {
    	AndroidCcGameBoard board = new AndroidCcGameBoard();
    	int p1 = 1;
    	int p2 = 2;
    	int p3 = 3;
    	int p4 = 4;
    	int p5 = 5;
    	int p6 = 6;
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(i, j), p1);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(7-i, j+9), p2);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(i+9, j+9), p3);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(16-i, j), p4);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(i+9, j), p5);
    		}
    	}
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<i+1; j++) {
    			board.setPiece(new AndroidPosition(7-i, j), p6);
    		}
    	}
    	assertEquals(board.checkWinCondition(p1), true);
    	assertEquals(board.checkWinCondition(p2), true);
    	assertEquals(board.checkWinCondition(p3), true);
    	assertEquals(board.checkWinCondition(p4), true);
    	assertEquals(board.checkWinCondition(p5), true);
    	assertEquals(board.checkWinCondition(p6), true);
    	AndroidPosition temp = new AndroidPosition(3,0);
    	board.movePiece(board.getPiece(temp), new AndroidPosition(4,4));
    	assertEquals(board.checkWinCondition(p1), false);
    }*/
}
