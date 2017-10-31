package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The implementation of GameBoard - This board being specifically for chinese checkers.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class CcGameBoard implements GameBoard {
    /**
     * Total number of spaces on the board
     */
    private GridPiece[][] board;
    private int numPlayers = 0;

    /**
     * Constructs an empty board, used for testing purposes
     */
    public CcGameBoard() {
        board = constructBoard();
    }

    /**
     * Add a piece to the board.
     *
     * @param p
     */
    @Override
    public void addPiece(Piece p) {
        setPiece(p.getPosition(), p.getPlayerNumber());
    }

    /**
     * Initialize a new game with the specified number of players.
     * @param numPlayers    The number of players in the game. {2,3,4,6}
     */
    public CcGameBoard(int numPlayers) {
        // Check to make sure the numPlayers argument is in range.
        if(!Arrays.asList(2, 3, 4, 6).contains(numPlayers)) {
            throw new IllegalArgumentException("The number of players must be {2,3,4,6}.");
        }

        this.numPlayers = numPlayers;

        board = constructBoard();
        populateNewGame(numPlayers);
    }

    /**
     * Load a game board from an initial set of pieces.
     * @param pieceList The list of pieces to initialize the board with.
     */
    public CcGameBoard(Piece[] pieceList) {
        if((pieceList.length%10) != 0 || pieceList.length > 60) {
            throw new IllegalArgumentException("The number of pieces on the board doesn't match the number of players playing.");
        }

        board = constructBoard();
        loadBoard(pieceList);
    }

    /**
     * Constructor for the parcel.
     * @param parcel    The parcel to build from.
     */
    private CcGameBoard(Parcel parcel) {
        Parcelable[] single = parcel.readParcelableArray(GridPiece[].class.getClassLoader());

        // Get board back
        if (single != null) {
            board = constructBoard();
            GridPiece[] singleBoard = Arrays.copyOf(single, single.length, GridPiece[].class);

            int i = 0;
            for(int row = 0; row < ROW_POSITION_COUNT.length; row++) {
                for(int col = 0; col < ROW_POSITION_COUNT[row]; col++) {
                    board[row][col] = singleBoard[i++];
                }
            }
        }
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Convert the piece board into a 1d array
        Piece[] single = new Piece[GameBoard.TOTAL_PIECE_COUNT];

        int i = 0;
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                single[i] = board[row][col];
                i++;
            }
        }

        dest.writeParcelableArray(single, 0);
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<CcGameBoard> CREATOR =
            new Parcelable.Creator<CcGameBoard>() {

                public CcGameBoard createFromParcel(Parcel in) {
                    return new CcGameBoard(in);
                }

                public CcGameBoard[] newArray(int size) {
                    return new CcGameBoard[size];
                }
            };

    /**
     * Populates the board with pieces in the starting location for each player.
     *
     * @param  playerNum Number of players playing.
     */
    private void populateNewGame(int playerNum) {
        int k, h, start;
        int[] playerList = generatePlayerList(playerNum);
        for(int x = 0; x<playerList.length; x++) {
            start = playerList[x];
            for(int i=0; i<4;i++) {
                for(int j=0; j<i+1; j++) {
                    k = getOffsetRow(start, i);
                    h = getOffsetIndex(start, j);
                    setPiece(new Position(k, h), playerList[x]);
                }
            }
        }
    }

    /**
     * Fill the board with the specified set of pieces.
     * @param pieceList The list of pieces to initialize the board with.
     */
    private void loadBoard(Piece[] pieceList) {
        if(checkEmpty()) {
            for(int i = 0; i<pieceList.length; i++) {
                setPiece(pieceList[i].getPosition(), pieceList[i].getPlayerNumber());
            }
        }
        else throw new BoardNotEmptyException("Board is not empty, game cannot be loaded.");

    }
    public Position[] getGoalPositions(int playerNumber) {
        int k, h, winArea;
        List<Position> goalPositions = new ArrayList<Position>();
        if(playerNumber < 4) {
            winArea = playerNumber + 3;
        }
        else {
            winArea = playerNumber + 3 - 6;
        }
        for(int i=0; i<4;i++) {
            for(int j=0; j<i+1; j++) {
                k = getOffsetRow(winArea, i);
                h = getOffsetIndex(winArea, j);
                goalPositions.add(new Position(k,h));
            }
        }
        return goalPositions.toArray(new Position[goalPositions.size()]);
    }
    public boolean isInGoal(Piece forPiece) {
        Position[] goalPositions = getGoalPositions(forPiece.getPlayerNumber());
        for(int i=0; i<goalPositions.length; i++) {
            if(forPiece.getPosition().getRow()==goalPositions[i].getRow() && forPiece.getPosition().getIndex()==goalPositions[i].getIndex()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks to see if a player satisfies the win condition by checking that all positions in their
     * goal area have one of their pieces in that position.
     *
     * @param  playerNumber The player for which checking of win condition is required.
     *
     */
    @Override
    public boolean hasPlayerWon(int playerNumber) {
        boolean winCheck = true;
        boolean playerCheck = false;
        int k, h;
        int winArea;
        if(playerNumber < 4) {
            winArea = playerNumber + 3;
        }
        else {
            winArea = playerNumber + 3 - 6;
        }
        for(int i=0; i<4;i++) {
            for(int j=0; j<i+1; j++) {
                k = getOffsetRow(winArea, i);
                h = getOffsetIndex(winArea, j);
                if(board[k][h]==null){
                    winCheck = false;
                    break;
                }
                if( !playerCheck && board[k][h].getPlayerNumber()==playerNumber) {
                    playerCheck=true;
                }

            }
        }

        return (winCheck && playerCheck);
    }

    /**
     * Returns a constructed chinese checks board in the form of a ragged two dimensional Piece array
     *
     * @return  A ragged two dimensional Piece array representing a chinese checkers board
     */
    private GridPiece[][] constructBoard() {
        GridPiece[][] board = new GridPiece[17][];
        for(int i=0; i<board.length;i++) {
            board[i] = new GridPiece[ROW_POSITION_COUNT[i]];
        }
        return board;
    }

    /**
     * Return all the pieces that are on the board in no specific order.
     *
     * @return  All the pieces on the board.
     */
    @Override
    public Piece[] getAllPieces() {
        List<Piece> allPieces = new ArrayList<Piece>();
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]!=null) {
                    allPieces.add(board[i][j]);
                }
            }
        }
        return allPieces.toArray(new Piece[allPieces.size()]);
    }
    /**
     * MovePath a piece from one position to another. Prints a statement if the move is invalid for any
     * reason.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    @Override
    public void movePiece(Piece piece, Position to) {
        if(isValidMove(piece, to)) {
            forceMove((GridPiece)getPiece(piece.getPosition()), to);
        }
        else{
            throw new IllegalMoveException("This piece cannot move to this position");
        }
    }

    /**
     * Moves a piece without validaing mvoe.
     *
     * @param piece Piece to be moved
     * @param to Location that the piece is moving to
     */
    public void forceMove(GridPiece piece, Position to) {
        int oldRow = piece.getPosition().getRow();
        int oldIndex = piece.getPosition().getIndex();
        piece.setPosition(to);
        board[oldRow][oldIndex] = null;
        board[piece.getPosition().getRow()][piece.getPosition().getIndex()] = piece;
    }

    /**
     * Get the piece that is at a position on the board.
     *
     * @param at    The position the piece is at.
     *
     * @return      The piece that was at the position specified, returns null if the position is
     *               empty or out of bounds.
     */
    @Override
    public GridPiece getPiece(Position at) {
        int row = at.getRow();
        int index = at.getIndex();

        try {
            return board[row][index];

        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Sets a piece at a given position for a given player. This method will mostly be used for
     * unit testing, and once unit testing is complete, for assistance in setting up the board.
     *
     * @param at    The Position that the player wishes to set the piece.
     * @param pl    The player that has ownership of the Piece.
     */
    public void setPiece(Position at, int pl) {
        int row = at.getRow();
        int index = at.getIndex();
        if(!isOccupied(at)) {
            board[row][index] = new GridPiece(at, pl);
        }
    }

    /**
     * Checks a given piece for any possible openings for that piece to move to. The process is
     * static and done almost on a case basis.
     *
     * @param forPiece  The piece to check positions for.
     *
     * @return          The list of positions the piece can move to. Or an empty array if there
     *                  is nowhere to move.
     */
    @Override
    public Position[] getPossibleMoves(Piece forPiece) {
        Position[] possibleMoves = new Position[14];
        int row = forPiece.getPosition().getRow();
        int index = forPiece.getPosition().getIndex();
        int posindex = 0;
        { // immediateNeighbours
            if (row>12 || (row>3 && row<8)) { // if y is between 4 and 8 and greater than 12
                if(row==4) {
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index-5));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index-5+1));
                    posindex=posindex+1;
                }
                else if(row==13) {
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index+4));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index+4+1));
                    posindex=posindex+1;
                }
                else {
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row-1, index+1));
                    posindex=posindex+1;
                }
                possibleMoves[posindex] = checkPosition(new Position(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row+1, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row+1, index));
                posindex=posindex+1;
            }
            else if (row==8) { // dead middle of the board
                possibleMoves[posindex] = checkPosition(new Position(row-1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row-1, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row+1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row+1, index+1));
                posindex=posindex+1;
            }
            else { // if y is between 9 and 11 or less than 4
                possibleMoves[posindex] = checkPosition(new Position(row-1, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row-1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new Position(row, index+1));
                posindex=posindex+1;
                if(row==3) {
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index+4));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index+4+1));
                    posindex=posindex+1;
                }
                else if(row==12) {
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index-5));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index-5+1));
                    posindex=posindex+1;
                }
                else {
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new Position(row+1, index+1));
                    posindex=posindex+1;
                }
            }
        }
        { //downLeft
            if (row>10 || (row>3 && row<8)) { // if y is between 4 and 8 and greater than 10
                if(row==11) {
                    if(isOccupied(new Position(row+1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-5));
                        posindex=posindex+1;
                    }
                }
                else if(row==12) {
                    if(isOccupied(new Position(row+1, index-5))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-6));
                        posindex=posindex+1;
                    }
                }
                else if(row==7) {
                    if(isOccupied(new Position(row+1, index-1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-1));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row+1, index-1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-2));
                        posindex=posindex+1;
                    }
                }
            }
            else { // if y is between 9 and 10 or less than 4
                if(row==3) {
                    if(isOccupied(new Position(row+1, index+4))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+3));
                        posindex=posindex+1;
                    }
                }
                else if(row==2) {
                    if(isOccupied(new Position(row+1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+4));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row+1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index));
                        posindex=posindex+1;
                    }
                }
            }
        }
        { // down right
            if (row>10 || (row>3 && row<8)) { // if row is between 4 and 8 and greater than 10
                if(row==11) {
                    if(isOccupied(new Position(row+1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-3));
                        posindex=posindex+1;
                    }
                }
                else if(row==12) {
                    if(isOccupied(new Position(row+1, index-4))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index-4));
                        posindex=posindex+1;
                    }
                }
                else if(row==7) {
                    if(isOccupied(new Position(row+1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+1));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row+1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index));
                        posindex=posindex+1;
                    }
                }
            }
            else { // if row is between 9 and 10 or less than 4
                if(row==2) {
                    if(isOccupied(new Position(row+1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+6));
                        posindex=posindex+1;
                    }
                }
                else if(row==3) {
                    if(isOccupied(new Position(row+1, index+5))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+5));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row+1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row+2, index+2));
                        posindex=posindex+1;
                    }
                }
            }
        } // end downRight
        { // upperLeft
            if (row>12 || (row>5 && row<9)) { // if row is between 4 and 8 and greater than 10
                if(row==14) {
                    if(isOccupied(new Position(row-1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+4));
                        posindex=posindex+1;
                    }
                }
                else if(row==13) {
                    if(isOccupied(new Position(row-1, index+4))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+3));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row-1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index));
                        posindex=posindex+1;
                    }
                }
            }
            else { // if row is between 9 and 10 or less than 4
                if(row==4) {
                    if(isOccupied(new Position(row-1, index-5))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-6));
                        posindex=posindex+1;
                    }
                }
                else if(row==9) {
                    if(isOccupied(new Position(row-1, index-1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-1));
                        posindex=posindex+1;
                    }
                }
                else if(row==5) {
                    if(isOccupied(new Position(row-1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-5));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row-1, index-1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-2));
                        posindex=posindex+1;
                    }
                }
            }
        } // end upperLeft
        { // upperRight
            if (row>12 || (row>5 && row<9)) { // if row is between 4 and 8 and greater than 10
                if(row==14) {
                    if(isOccupied(new Position(row-1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+6));
                        posindex=posindex+1;
                    }
                }
                else if(row==13) {
                    if(isOccupied(new Position(row-1, index+5))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+5));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row-1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+2));
                        posindex=posindex+1;
                    }
                }
            }
            else { // if row is between 9 and 10 or less than 4
                if(row==4) {
                    if(isOccupied(new Position(row-1, index-4))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-4));
                        posindex=posindex+1;
                    }
                }
                else if(row==9) {
                    if(isOccupied(new Position(row-1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index+1));
                        posindex=posindex+1;
                    }
                }
                else if(row==5) {
                    if(isOccupied(new Position(row-1, index+1))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index-3));
                        posindex=posindex+1;
                    }
                }
                else {
                    if(isOccupied(new Position(row-1, index))) {
                        possibleMoves[posindex] = checkPosition(new Position(row-2, index));
                        posindex=posindex+1;
                    }
                }
            }
        } // end upperRight
        { // leftAndRight
            if(isOccupied(new Position(row, index-1))) {
                possibleMoves[posindex] = checkPosition(new Position(row, index-2));
                posindex=posindex+1;
            }
            if(isOccupied(new Position(row, index+1))) {
                possibleMoves[posindex] = checkPosition(new Position(row, index+2));
            }
        } // end leftAndRight
        if(isInGoal(forPiece)) {
            return trimToGoal(forPiece, assistPossibleMoves(possibleMoves));
        }
        return assistPossibleMoves(possibleMoves);
    }

    /**
     * A list of valid positions that a piece can go to ONLY by hopping over another player.
     *
     * @param forPiece The piece to check positions for.
     * @return The list of positions the piece can move to.
     */
    @Override
    public Position[] getPossibleHops(Piece forPiece) {
        Position[] allPossible = getPossibleMoves(forPiece);
        ArrayList<Position> hops = new ArrayList<Position>();
        if(allPossible != null) {
            for(Position p : allPossible) {
                if(Math.abs(p.getRow() - forPiece.getPosition().getRow()) == 2 ||
                   Math.abs(p.getIndex() - forPiece.getPosition().getIndex()) == 2) {

                    hops.add(p);
                }
            }
        }

        return (hops.size() > 0 ? hops.toArray(new Position[hops.size()]) : null);
    }

    private Position[] trimToGoal (Piece forPiece, Position[] possibleMoves) {
        if(possibleMoves==null) {
            return null;
        }
        List<Position> trimmedPossibleMoves = new ArrayList<Position>();
        Position[] goalPositions = getGoalPositions(forPiece.getPlayerNumber());
        for(int i=0; i<goalPositions.length; i++) {
            for(int j=0; j<possibleMoves.length; j++) {
                if(goalPositions[i].getRow()==possibleMoves[j].getRow() && goalPositions[i].getIndex()==possibleMoves[j].getIndex()) {
                    trimmedPossibleMoves.add(possibleMoves[j]);
                }
            }
        }

        Position[] temp = trimmedPossibleMoves.toArray(new Position[trimmedPossibleMoves.size()]);

        return (temp.length <= 0 ? null : temp);
    }
    /*
        Assistance function, removes all nulls from the getPossibleMoves array, may seem unecessary but it's neater
        than the alternative.
         */
    private Position[] assistPossibleMoves (Position[] possibleMoves) {
        List<Position> fixedPossibleMoves = new ArrayList<Position>();
        for(int i=0; i<possibleMoves.length; i++) {
            if(possibleMoves[i]!=null) {
                fixedPossibleMoves.add(possibleMoves[i]);
            }
        }

        Position[] temp = fixedPossibleMoves.toArray(new Position[fixedPossibleMoves.size()]);

        return (temp.length <= 0 ? null : temp);
    }
    /**
     * Checks if a move is valid by verifying the possible moves for the given piece and evaluating
     * the given position against those positions. If the piece is within the possibleMoves array,
     * the move is valid. Otherwise it is invalid.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return      True if the move is valid, false otherwise.
     */
    @Override
    public boolean isValidMove(Piece piece, Position to) {
        Position[] possibleMoves = getPossibleMoves(piece);
        for(int i = 0; i < possibleMoves.length; i++) {
            if (possibleMoves[i] != null) {
                if(possibleMoves[i].equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a position on the game board is occupied or out of bounds.
     *
     * @param at    The position to be checked
     * @return      True if the position is out of bounds or occupied by another piece, false
     * otherwise
     */
    private boolean isOccupied (Position at) {
        int row = at.getRow();
        int index = at.getIndex();

        try {
            return board[row][index]!=null;

        }
        catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * Assistant method for getPossibleMoves(), implements an easier way of checking if a position
     * is valid and simply returning the position if it is.
     *
     * @param at    The position to be checked
     * @return      Returns null if the position is occupied, returns "at" if the position is open
     */
    private Position checkPosition (Position at) {
        if(isOccupied(at)) {
            return null;
        }
        return at;
    }

    /**
     * An assisting function for hasPlayerWon and populateBoard that returns an offset row value
     * based on the location of the targeted area and the current iteration of the loop.
     *
     * @param  location The targeted area, see supporting location documentation.
     * @param  i The iteration of the loop
     * @return returns the offset of the row.
     */
    private int getOffsetRow(int location, int i) {
        switch (location) {
            case 1: return 16-i;
            case 2: return 9+i;
            case 3: return 7-i;
            case 4: return i;
            case 5: return 7-i;
            case 6: return 9+i;
            default: return -1;
        }
    }

    /**
     * An assisting function for hasPlayerWon and populateBoard that returns an offset column or index value
     * based on the location of the targeted area and the current iteration of the loop.
     *
     * @param  location The targeted area, see supporting location documentation.
     * @param  j The iteration of the loop
     * @return returns the offset of the index.
     */
    private int getOffsetIndex(int location, int j) {
        if(location < 5) {
            return j;
        }
        else {
            return j+9;
        }
    }
    private boolean checkEmpty() {
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]!=null) {
                    return false;
                }
            }
        }
        return true;
    }
    private int[] generatePlayerList(int playerNum) {
        if(playerNum==2) {
            int[] playerList = {1,4};
            return playerList;
        }
        else if(playerNum==3) {
            int[] playerList = {1,3,5};
            return playerList;
        }
        else if(playerNum==4) {
            int[] playerList = {1,3,4,6};
            return playerList;
        }
        else if(playerNum==6) {
            int[] playerList = {1,2,3,4,5,6};
            return playerList;
        }
        else {
            throw new IllegalArgumentException("The number of players provided is not 2,3,4,or 6");
        }

    }

    /**
     * Create a deep copy of the game board that can be modified.
     *
     * @return
     */
    @Override
    public GameBoard getDeepCopy() {
        Piece[] pieces = getAllPieces();

        CcGameBoard board = new CcGameBoard(); // Empty Board

        // Construct board
        for(final Piece p : pieces) {
            board.addPiece(new Piece() {
                @Override
                public Position getPosition() {
                    return p.getPosition();
                }

                @Override
                public int getPlayerNumber() {
                    return p.getPlayerNumber();
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {

                }
            });
        }

        board.numPlayers = pieces.length/10;

        return board;
    }

    /**
     * Get the number of players
     *
     * @return
     */
    @Override
    public int getPlayerCount() {
        return numPlayers;
    }

    /**
     * Reset the board to start state
     */
    @Override
    public void reset() {
        board = constructBoard();
        populateNewGame(getPlayerCount());
    }

    /**
     * @param position The position being evaluated
     * @param playerNumber The player who owns the piece at said position
     * @return Whether that piece is at the edge of its goal zone
     */
    @Override
    public boolean atGoalEdge(Position position, int playerNumber){
        switch(playerNumber){
            case 1:
                if(position.getRow() == 4 && position.getIndex() > 3 && position.getIndex() < 9)
                    return true;
                break;
            case 2:
                if(position.getRow() > 3 && position.getRow() < 9 && position.getIndex() == 8)
                    return true;
                break;
            case 3:
                if(position.getRow() > 7 && position.getRow() < 13 && position.getIndex() == 8)
                    return true;
                break;
            case 4:
                if(position.getRow() == 12 && position.getIndex() > 3 && position.getIndex() < 9)
                    return true;
                break;
            case 5:
                if(position.getRow() > 7 && position.getRow() < 13 && (position.getRow() - position.getIndex()) == 8)
                    return true;
                break;
            case 6:
                if(position.getRow() < 9 && position.getRow() > 3 && (position.getRow() + position.getIndex()) == 8)
                    return true;
                break;
        }
        return false;
    }
}


