package ca.brocku.chinesecheckers.gameboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

import javajar.gameboard.Piece;
import javajar.gameboard.Position;
import javajar.gamestate.Player;

/**
 * The implementation of AndroidGameBoard - This board being specifically for chinese checkers.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class AndroidCcGameBoard extends javajar.gameboard.CcGameBoard implements AndroidGameBoard,Parcelable{
    /**
     * Total number of spaces on the board
     */
    //private AndroidGridPiece[][] board;
    //private int numPlayers = 0;

    /**
     * Constructs an empty board, used for testing purposes
     */
    public AndroidCcGameBoard() {
        super();
    }

    /**
     * Initialize a new game with the specified number of players.
     * @param numPlayers    The number of players in the game. {2,3,4,6}
     */
    public AndroidCcGameBoard(int numPlayers) {
        super(numPlayers);
    }

    /**
     * Load a game board from an initial set of pieces.
     * @param pieceList The list of pieces to initialize the board with.
     */
    public AndroidCcGameBoard(AndroidPiece[] pieceList) {
        super(pieceList);
    }

    /**
     * Returns a constructed chinese checks board in the form of a ragged two dimensional AndroidPiece array
     *
     * @return  A ragged two dimensional AndroidPiece array representing a chinese checkers board
     */
    /*public AndroidGridPiece[][] constructBoard() {
        AndroidGridPiece[][] board = new AndroidGridPiece[17][];
        for(int i=0; i<board.length;i++) {
            board[i] = new AndroidGridPiece[ROW_POSITION_COUNT[i]];
        }
        return board;
    }*/

    /**
     * Constructor for the parcel.
     * @param parcel    The parcel to build from.
     */
    private AndroidCcGameBoard(Parcel parcel) {
        Parcelable[] single = parcel.readParcelableArray(AndroidGridPiece[].class.getClassLoader());

        // Get board back
        if (single != null) {
            board = constructBoard();
            AndroidGridPiece[] singleBoard = Arrays.copyOf(single, single.length, AndroidGridPiece[].class);

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
        AndroidGridPiece[] single = new AndroidGridPiece[AndroidGameBoard.TOTAL_PIECE_COUNT];

        int i = 0;
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                Piece p = board[row][col];
                if(p!=null){
                    Position pos = p.getPosition();
                    int pl = p.getPlayerNumber();
                    single[i] = new AndroidGridPiece(pos,pl);
                }
                else single[i]=null;
                i++;
            }
        }

        dest.writeParcelableArray(single, 0);
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<AndroidCcGameBoard> CREATOR =
            new Parcelable.Creator<AndroidCcGameBoard>() {

                public AndroidCcGameBoard createFromParcel(Parcel in) {
                    return new AndroidCcGameBoard(in);
                }

                public AndroidCcGameBoard[] newArray(int size) {
                    return new AndroidCcGameBoard[size];
                }
            };

}

