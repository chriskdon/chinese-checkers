package ca.brocku.chinesecheckers.computerplayer;

import android.os.Parcel;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gamestate.AndroidPlayer;
import ca.brocku.chinesecheckers.gameboard.AndroidReadOnlyGameBoard;

import javajar.computerplayer.*;
import javajar.gameboard.ReadOnlyGameBoard;
import javajar.gamestate.MovePath;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class AndroidAiPlayer extends AndroidPlayer implements Parcelable{

    public String AILevel;
    private int difficulty;

    public AndroidAiPlayer(String AILevel, PlayerColor playerColor){
        super(playerColor);
        this.AILevel = AILevel;
        this.difficulty = getDifficulty(AILevel);
    }

    public static final int getDifficulty(String level) {
        if(level.equals("EASY")) return 1;
        if(level.equals("MEDIUM")) return 2;
        if(level.equals("HARD")) return 3;

        throw new IllegalArgumentException("Difficulty must be EASY, MEDIUM, or HARD.");
    }

    public int getDifficulty(){ return difficulty; }

    /**
     * Executed when it is this players turn to act.
     *
     * @param gameBoard The current game board.
     * @return The move that the AI chooses to make.
     */
    public MovePath getMove(AndroidReadOnlyGameBoard gameBoard) {
        MovePath move;
        switch(this.difficulty){
            //case 3: hardMove myHardMove = new HardAIMove();
            //  move = myHardMove.getHardMove(getPlayerNumber(), gameBoard.getDeepCopy()
            case 2:
                MediumMove myMediumMove = new MediumMove();
                move = myMediumMove.getMediumMove(getPlayerNumber(), gameBoard.getDeepCopy());
                break;
            default:
                EasyMove myEasyMove = new EasyMove();
                move = myEasyMove.getEasyMove(getPlayerNumber(), gameBoard.getDeepCopy());
                break;
        }
        return move;
    }

    /**
     * Executed when it is this players turn to act.
     *
     * @param board
     */
    @Override
    public MovePath onTurn(ReadOnlyGameBoard board) {
        return getMove((AndroidReadOnlyGameBoard)board);
    }

    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        switch(getPlayerColor()){
            case RED: return "AI Robbie";
            case PURPLE: return  "AI Roboto";
            case BLUE: return "AI Roomba";
            case GREEN: return "AI Roberto";
            case YELLOW: return "AI Robeye";
            case ORANGE: return "AI Robber";
        }
        throw new IllegalStateException("Invalid Player Color");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Revive
     *
     * @param parcel
     */
    private AndroidAiPlayer(Parcel parcel) {
        this(parcel.readString(), PlayerColor.valueOf(parcel.readString()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AILevel);
        dest.writeString(getPlayerColor().toString());
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<AndroidAiPlayer> CREATOR =
            new Parcelable.Creator<AndroidAiPlayer>() {

                public AndroidAiPlayer createFromParcel(Parcel in) { return new AndroidAiPlayer(in); }

                public AndroidAiPlayer[] newArray(int size) { return new AndroidAiPlayer[size]; }
            };
}
