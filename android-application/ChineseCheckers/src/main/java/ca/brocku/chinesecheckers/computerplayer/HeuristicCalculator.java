package ca.brocku.chinesecheckers.computerplayer;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/22/2014
 */

public class HeuristicCalculator {


    /**
     * Calculates the distance from a piece to the farthest corner of the goal state.
     * Gives distance to travel 2.5 times more weight than distance from center
     * of the board.
     *
     * @param piece The piece to be checked.
     * @param board The current state of the board.
     * @return The pathing heuristic of the piece.
     */
    public int getDistanceHeuristic(Piece piece, GameBoard board){
        int distanceFromGoal = 0;

        int[] goalState = getGoalState(piece.getPlayerNumber(), board);







        /* must be modified greatly
        distanceFromGoal += Math.abs(piece.getPosition().getRow() - goalState[0])*20;
        distanceFromGoal += Math.abs(piece.getPosition().getIndex() - goalState[1]) * 8;
            */
        return distanceFromGoal;
    }

    /**
     * Finds the farthest goal state position from a player's starting location.
     *
     *
     * @param playerNumber The player number of the AI.
     * @return The farthest position in the goal state.
     */
    public int[] getGoalState(int playerNumber, GameBoard board){
        int winLocation;
        int[] winPos = new int[2];

        if(playerNumber < 4) {
            winLocation = playerNumber + 3;
        }
        else {
            winLocation = playerNumber + 3 - 6;
        }

        winPos[0] = getOffsetRow(winLocation);
        winPos[1] = getOffsetIndex(winLocation);
        return winPos;
    }



    /**
     * An assisting function for getGoalState that returns an offset row value
     * based on the location of the targeted area.
     *
     * @param  location The targeted area, see supporting location documentation.
     * @return returns the offset of the row.
     */
    private int getOffsetRow(int location) {
        switch (location) {
            case 1: return 16;
            case 2: return 9;
            case 3: return 7;
            case 4: return 0;
            case 5: return 7;
            case 6: return 9;
            default: return -1;
        }
    }

    /**
     * An assisting function for getGoalState that returns an offset column or index value
     * based on the location of the targeted area.
     *
     * @param  location The targeted area, see supporting location documentation.
     * @return returns the offset of the index.
     */
    private int getOffsetIndex(int location) {
        if(location < 5) {
            return 0;
        }
        else {
            return 9;
        }
    }
}
