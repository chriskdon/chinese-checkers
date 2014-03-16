package ca.brocku.chinesecheckers.computerplayer;

import android.os.Parcel;
import android.os.Parcelable;

import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.MovePath;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.gamestate.PlayerTurnState;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class AIPlayer extends Player {
    private String AILevel;
    private int difficulty;

    public AIPlayer(String AILevel, PlayerColor playerColor){
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
    public MovePath getMove(ReadOnlyGameBoard gameBoard) {
        MovePath move;
        switch(this.difficulty){
            //case 3: move = HardAIMove();
            //case 2: move = MediumAIMove();
            default: EasyMove myEasyMove = new EasyMove();
                move = myEasyMove.getEasyMove(getPlayerNumber(), gameBoard.getDeepCopy());
                break;
        }
        return move;
    }

    /**
     * Executed when it is this players turn to act.
     *
     * @param state The current state.
     */
    @Override
    public void onTurn(PlayerTurnState state) {
        state.signalMove(this, getMove(state.getGameBoard()));
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
    private AIPlayer(Parcel parcel) {
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
    public static final Parcelable.Creator<AIPlayer> CREATOR =
            new Parcelable.Creator<AIPlayer>() {

                public AIPlayer createFromParcel(Parcel in) { return new AIPlayer(in); }

                public AIPlayer[] newArray(int size) { return new AIPlayer[size]; }
            };
}