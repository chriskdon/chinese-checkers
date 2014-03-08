package ca.brocku.chinesecheckers.computerplayer;

import android.os.Parcel;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.Move;
import ca.brocku.chinesecheckers.gamestate.Player;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class AIPlayer extends Player {
    private String name;
    private int difficulty, playerNumber;

    public AIPlayer(String AILevel, PlayerColor playerColor, int playerNumber){
        super(playerColor);

        this.difficulty = getDifficulty(AILevel);
        //this.name = getName(playerNumber);
        this.playerNumber = playerNumber;
    }

    public static final int getDifficulty(String level) {
        if(level.equals("EASY")) return 1;
        if(level.equals("MEDIUM")) return 2;
        if(level.equals("HARD")) return 3;

        throw new IllegalArgumentException("Difficulty must be EASY, MEDIUM, or HARD.");
    }

    public int getDifficulty(){ return difficulty; }

    public int getPlayerNumber(){ return playerNumber; }

    /**
     * Executed when it is this players turn to act.
     *
     * @param gameBoard The current game board.
     * @return The move that the AI chooses to make.
     */
    @Override
    public Move onTurn(ReadOnlyGameBoard gameBoard) {
        Move move;

        switch(this.difficulty){
            //case 3: move = HardAIMove();
            //case 2: move = MediumAIMove();
            default: EasyMove myEasyMove = new EasyMove();
                move = myEasyMove.getEasyMove(playerNumber, gameBoard);
                break;
        }
        return move;
    }

    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


    /**
     * Recreate this instance
     */
    /*public static final Parcelable.Creator<AIPlayer> CREATOR =
        new Parcelable.Creator<AIPlayer>() {

        public AIPlayer createFromParcel(Parcel in) {
            return new AIPlayer(in);
        }

        public AIPlayer[] newArray(int size) {
            return new AIPlayer[size];
        }
    };*/
}
