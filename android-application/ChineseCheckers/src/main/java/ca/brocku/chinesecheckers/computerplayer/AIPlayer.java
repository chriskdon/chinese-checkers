package ca.brocku.chinesecheckers.computerplayer;

import android.os.Parcel;

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

    public AIPlayer(int difficulty, int playerNumber, PlayerColor playerColor){
        super(playerColor);
        this.difficulty = difficulty;
        this.playerNumber = playerNumber;
    }

    /**
     * Calls the appropriate AI method in accordance to the AI's difficulty
     *
     * @param board The current state of the board.
     * @return The move that the AI chooses to make.
     */
    public Move getMove(GameBoard board){
        Move move;

        switch(this.difficulty){
            //case 3: move = HardAIMove();
            //case 2: move = MediumAIMove();

            default: EasyMove myEasyMove = new EasyMove();
                move = myEasyMove.getEasyMove(playerNumber, board);
                break;
        }
        return move;
    }

    public int getDifficulty(){ return difficulty; }

    public int getPlayerNumber(){ return playerNumber; }

    @Override
    public Move onTurn(ReadOnlyGameBoard gameBoard) {
        return null;
    }

    public String getName() { return name; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
