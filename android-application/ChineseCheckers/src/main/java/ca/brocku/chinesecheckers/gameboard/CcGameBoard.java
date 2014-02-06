package ca.brocku.chinesecheckers.gameboard;

/**
 * Created by zz on 2/5/14.
 */
public abstract class CcGameBoard implements GameBoard{
    /**
     * The number of available positions in each row.
     */
    public static final int[] ROW_POSITION_COUNT = {1, 2, 3, 4, 13, 12, 11, 10, 9, 10, 11, 12, 13, 4, 3, 2, 1};
    /**
     * Total number of spaces on the board
     */
    public static final int TOTAL_PIECE_COUNT = 121;
    GridPiece[][] board;

    public CcGameBoard() {
        GridPiece[][] board = new GridPiece[17][];
        for(int i=0; i<board.length;i++) {
            board[i] = new GridPiece[ROW_POSITION_COUNT[i]];
        }
    }

    /**
     * Return all the pieces that are on the board in no specific order.
     *
     * @return  All the pieces on the board.
     */
    public GridPiece[] getAllPiece() {
        GridPiece[] allPieces = new GridPiece[60];
        int allPiecesIndex = 0;
        for(int i=0; i<board.length;i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j]!=null) {
                    allPieces[allPiecesIndex]=board[i][j];
                    allPiecesIndex=allPiecesIndex+1;
                }
            }
        }
        return allPieces;
    }

    /**
     * Move a piece from one position to another.
     *
     * @param piece The piece to move.
     * @param to    The new position of the piece.
     */
    public void movePiece(GridPiece piece, GridPosition to) {
        if(isValidMove(piece, to)) {
            int oldRow = piece.getPosition().getRow();
            int oldIndex = piece.getPosition().getIndex();
            int newRow = to.getRow();
            int newIndex = to.getIndex();
            board[newRow][newIndex] = piece;
            board[oldRow][oldIndex] = null;
        }
        else {
            System.out.println("invalid move command");
        }
    }

    /**
     * Get the piece that is at a position on the board.
     *
     * @param at    The position the piece is at.
     *
     * @return      The piece that was at the position specified.
     */
    public GridPiece getPiece(GridPosition at) {
        int row = at.getRow();
        int index = at.getIndex();
        if(isOccupied(at)) {
            return board[row][index];
        }
        else return null;
    }

    /**
     * A list of valid positions that the specified piece could move to.
     *
     * @param forPiece  The piece to check positions for.
     *
     * @return          The list of positions the piece can move to. Or <code>null</code> if there
     *                  is nowhere to move.
     */
    public GridPosition[] getPossibleMoves(GridPiece forPiece) {
        GridPosition[] possibleMoves = new GridPosition[12];
        int row = forPiece.getPosition().getRow();
        int index = forPiece.getPosition().getIndex();
        int posindex = 0;
        { // immediateNeighbours
            if (row>12 || (row>3 && row<8)) { // if y is between 4 and 8 and greater than 12
                if(row==4) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index-5));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index-5+1));
                    posindex=posindex+1;
                }
                else if(row==13) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index+4));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index+4+1));
                    posindex=posindex+1;
                }
                else {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index+1));
                    posindex=posindex+1;
                }
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index));
                posindex=posindex+1;
            }
            else if (row==8) { // dead middle of the board
                possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index+1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index+1));
                posindex=posindex+1;
            }
            else { // if y is between 9 and 11 or less than 4
                //System.out.println("Case3");
                possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row-1, index));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index-1));
                posindex=posindex+1;
                possibleMoves[posindex] = checkPosition(new GridPosition(row, index+1));
                posindex=posindex+1;
                if(row==3) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index+4));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index+4+1));
                    posindex=posindex+1;
                }
                else if(row==12) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index-5));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index-5+1));
                    posindex=posindex+1;
                }
                else {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index));
                    posindex=posindex+1;
                    possibleMoves[posindex] = checkPosition(new GridPosition(row+1, index+1));
                    posindex=posindex+1;
                }
            }
        }
            {
                if (row>10 || (row>3 && row<8)) { // if y is between 4 and 8 and greater than 10
                    if(row==11) {
                        if(isOccupied(new GridPosition(row+1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-5));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==12) {
                        if(isOccupied(new GridPosition(row+1, index-4))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-6));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==7) {
                        if(isOccupied(new GridPosition(row+1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-1));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row+1, index-1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-2));
                            posindex=posindex+1;
                        }
                    }
                }
                else { // if y is between 9 and 10 or less than 4
                    if(row==3) {
                        if(isOccupied(new GridPosition(row+1, index+4))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+3));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==2) {
                        if(isOccupied(new GridPosition(row+1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+4));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row+1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index));
                            posindex=posindex+1;
                        }
                    }
                }
            }
            { // down right
                if (row>10 || (row>3 && row<8)) { // if row is between 4 and 8 and greater than 10
                    if(row==11) {
                        if(isOccupied(new GridPosition(row+1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-3));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==12) {
                        if(isOccupied(new GridPosition(row+1, index-4))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index-4));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==7) {
                        if(isOccupied(new GridPosition(row+1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+1));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row+1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index));
                            posindex=posindex+1;
                        }
                    }
                }
                else { // if row is between 9 and 10 or less than 4
                    if(row==2) {
                        if(isOccupied(new GridPosition(row+1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+6));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==3) {
                        if(isOccupied(new GridPosition(row+1, index+5))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+5));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row+1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row+2, index+2));
                            posindex=posindex+1;
                        }
                    }
                }
            } // end downRight
            { // upperLeft
                if (row>12 || (row>5 && row<9)) { // if row is between 4 and 8 and greater than 10
                    if(row==14) {
                        if(isOccupied(new GridPosition(row-1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+4));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==13) {
                        if(isOccupied(new GridPosition(row-1, index+4))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+3));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row-1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index));
                            posindex=posindex+1;
                        }
                    }
                }
                else { // if row is between 9 and 10 or less than 4
                    if(row==4) {
                        if(isOccupied(new GridPosition(row-1, index-5))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-6));
                            posindex=posindex+1;
                        }
                    }
                    if(row==9) {
                        if(isOccupied(new GridPosition(row-1, index-1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-1));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==5) {
                        if(isOccupied(new GridPosition(row-1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-5));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row-1, index-1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-2));
                            posindex=posindex+1;
                        }
                    }
                }
            } // end upperLeft
            { // upperRight
                if (row>12 || (row>5 && row<9)) { // if row is between 4 and 8 and greater than 10
                    if(row==14) {
                        if(isOccupied(new GridPosition(row-1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+6));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==13) {
                        if(isOccupied(new GridPosition(row-1, index+5))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+5));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row-1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+2));
                            posindex=posindex+1;
                        }
                    }
                }
                else { // if row is between 9 and 10 or less than 4
                    //Srowstem.out.println("Case3");
                    if(row==4) {
                        if(isOccupied(new GridPosition(row-1, index-4))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-4));
                            posindex=posindex+1;
                        }
                    }
                    if(row==9) {
                        if(isOccupied(new GridPosition(row-1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index+1));
                            posindex=posindex+1;
                        }
                    }
                    else if(row==5) {
                        if(isOccupied(new GridPosition(row-1, index+1))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index-3));
                            posindex=posindex+1;
                        }
                    }
                    else {
                        if(isOccupied(new GridPosition(row-1, index))) {
                            possibleMoves[posindex] = checkPosition(new GridPosition(row-2, index));
                            posindex=posindex+1;
                        }
                    }
                }
            } // end upperRight
            { // leftAndRight
                if(isOccupied(new GridPosition(row, index-1))) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row, index-2));
                    posindex=posindex+1;
                }
                if(isOccupied(new GridPosition(row, index+1))) {
                    possibleMoves[posindex] = checkPosition(new GridPosition(row, index+1));
                }
            } // end leftAndRight
        return possibleMoves;
    }

    /**
     * Check if a move is valid for a specified piece.
     *
     * @param piece The piece making the move.
     * @param to    The position the piece is trying to move to.
     * @return      True if the move is valid, false otherwise.
     */
    public boolean isValidMove(GridPiece piece, GridPosition to) {
        GridPosition[] possibleMoves = getPossibleMoves(piece);
        for(int i=0; i<possibleMoves.length; i++) {
            if (possibleMoves[i]==null) {
                continue;
            }
            if(possibleMoves[i].getRow() == to.getRow() && possibleMoves[i].getIndex() == to.getIndex()) {
                return true;
            }
        }
        return false;
    }
    private boolean isOccupied (GridPosition at) {
        int row = at.getRow();
        int index = at.getIndex();

        try {
            return board[row][index]!=null;

        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private GridPosition checkPosition (GridPosition at) {
        if(isOccupied(at)) {
            return null;
        }
        return at;
    }
}
