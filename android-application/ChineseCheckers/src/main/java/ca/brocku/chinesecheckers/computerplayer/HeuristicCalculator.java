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
    int[] goalState;

    public HeuristicCalculator(int playerNumber, GameBoard board){
        this.goalState = getGoalState(playerNumber, board);
    }
    /**
     * Calculates the distance from a piece to the farthest corner of the goal state.
     * Gives distance to travel 2.5 times more weight than distance from center
     * of the board.
     *
     * @param piece The piece to be checked.
     * @return The pathing heuristic of the piece.
     */
    public int getDistanceHeuristic(Piece piece){
        int distanceFromGoal = 0, rowCase = 0, indexCase = 0;

        //vertical displacement from the goal state
        if(goalState[0] == 16 || goalState[0] == 0)
            rowCase = 1;//up/down center
        if(goalState[0] == 9 || goalState[0] == 9)
            rowCase = 2;//up diagonal
        if(goalState[0] == 7 || goalState[0] == 7)
            rowCase = 3;//down diagonal
        switch(rowCase){
            case 1:
                distanceFromGoal += Math.abs(piece.getPosition().getRow() - goalState[0])*20;
                break;
            case 2:
                distanceFromGoal += Math.abs(piece.getPosition().getRow() - (goalState[0]+3))*10;
                break;
            case 3:
                distanceFromGoal += Math.abs(piece.getPosition().getRow() - (goalState[0]-3))*10;
                break;
            default:
                break;
        }

        //horizontal displacement from the goal state
        if(goalState[1] == 1 || goalState[1] == 4)
            indexCase = 1;//up/down center
        if(goalState[1] == 3 || goalState[1] == 5)
            indexCase = 2;//leftward diagonal
        if(goalState[1] == 5 || goalState[1] == 6)
            indexCase = 3;//rightward diagonal
        switch(indexCase){
            case 1:
                //first three rows into one of the vertical goal states
                if(piece.getPosition().getRow() < 4 && piece.getPosition().getRow() > 0)
                    distanceFromGoal += Math.abs(piece.getPosition().getIndex() - 1) * 8;
                if(piece.getPosition().getRow() > 12 && piece.getPosition().getRow() < 16)
                    distanceFromGoal += Math.abs(piece.getPosition().getIndex() - 1) * 8;
                //deepest row in one of the vertical goal states
                if(piece.getPosition().getRow() == 0 || piece.getPosition().getRow() == 16)
                    distanceFromGoal += 0;
                //rows between the vertical goal states in the grid
                if(piece.getPosition().getRow() >= 4 && piece.getPosition().getRow() <= 12)
                    distanceFromGoal += Math.abs((piece.getPosition().getIndex()) - (Math.abs(piece.getPosition().getIndex() - 8) / 2) + 4) * 8;
                break;
            case 2:
                distanceFromGoal += Math.abs(piece.getPosition().getIndex() - goalState[1]) * 18;
                break;
            case 3:
                distanceFromGoal += Math.abs(piece.getPosition().getIndex() - (goalState[1]+3)) * 18;
                break;
            default:
                break;
        }

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
