package javajar.computerplayer;

import javajar.gameboard.GameBoard;
import javajar.gameboard.Position;
import javajar.gamestate.MovePath;


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
     * Calculates the difference in Distance Heuristic between two points.
     *
     * @param path The path that the piece will move on
     * @return The difference in distance to the goal the piece attains from this move
     */
    public int getDeltaDistanceHeuristic(MovePath path){
        int initialPosition = getDistanceHeuristic(path.getStartPosition());
        int finalPosition = getDistanceHeuristic(path.getEndPosition());

        return (initialPosition - finalPosition);
    }
    /**
     * Calculates the distance from a piece to the farthest corner of the goal state.
     *
     * @param piece The piece to be checked.
     * @return The pathing heuristic of the piece.
     */
    public int getDistanceHeuristic(Position piece){
        int distanceFromGoal = 0, rowCase = 0, indexCase = 0;
        //vertical displacement from the goal state
        if(goalState[0] == 16 || goalState[0] == 0)
            rowCase = 1;//up/down center
        if(goalState[0] == 7)
            rowCase = 2;//up diagonal
        if(goalState[0] == 9)
            rowCase = 3;//down diagonal

        switch(rowCase){
            case 1:
                distanceFromGoal += Math.abs(piece.getRow() - goalState[0])*20;
                if(distanceFromGoal <= 60) distanceFromGoal = distanceFromGoal / 2;
                break;
            case 2:
                distanceFromGoal += Math.abs(piece.getRow() - ((goalState[0]-3)))*16;
                if(piece.getRow() > 12 || piece.getRow() < 4) distanceFromGoal += 48;
                if(piece.getRow() > 8) distanceFromGoal = distanceFromGoal*2;
                break;
            case 3:
                distanceFromGoal += Math.abs(piece.getRow() - (goalState[0]+3))*16;
                if(piece.getRow() > 12 || piece.getRow() < 4) distanceFromGoal += 48;
                if(piece.getRow() < 8) distanceFromGoal = distanceFromGoal*2;
                break;
            default:
                break;
        }

        //horizontal displacement from the goal state
        if(goalState[1] == 0 && goalState[0] == 0)
            indexCase = 1;//up center
        else if(goalState[1] == 0 && goalState[0] == 16)
            indexCase = 1;//down center
        else if(goalState[1] == 0)
            indexCase = 2;//leftward diagonal
        else if(goalState[1] == 9)
            indexCase = 3;//rightward diagonal
        switch(indexCase){
            case 1:
                //first three rows into one of the vertical goal states
                if(piece.getRow() < 4 && piece.getRow() >= 0 && goalState[0] != 0)
                    distanceFromGoal += Math.abs(piece.getIndex() + 4) * 8;
                else if(piece.getRow() > 12 && piece.getRow() <= 16 && goalState[0] != 16)
                    distanceFromGoal += Math.abs(piece.getIndex() + 4) * 8;
                    //rows between the vertical goal states in the grid
                else if(piece.getRow() >= 4 && piece.getRow() <= 12)
                    distanceFromGoal += Math.abs(piece.getIndex() - (Math.abs(piece.getRow() - 8) / 2 + 4)) * 8;
                break;
            case 2:
                //first three rows into one of the vertical goal states
                if(piece.getRow() < 4 && piece.getRow() >= 0)
                    distanceFromGoal += Math.abs(piece.getIndex() + 4) * 16;
                else if(piece.getRow() > 12 && piece.getRow() <= 16)
                    distanceFromGoal += Math.abs(piece.getIndex() + 4) * 16;
                //rows between the vertical goal states in the grid
                else if (piece.getRow() >= 4 && piece.getRow() <= 12)
                    distanceFromGoal += Math.abs(piece.getIndex()) * 16;
                if(piece.getIndex() > (7 - piece.getRow())) distanceFromGoal += piece.getIndex()*8;
                break;
            case 3:
                //first three rows into one of the vertical goal states
                if(piece.getRow() < 4 && piece.getRow() >= 0)
                    distanceFromGoal += Math.abs((goalState[1]+3) - (piece.getIndex())) * 16;
                else if(piece.getRow() > 12 && piece.getRow() <= 16)
                    distanceFromGoal += Math.abs((goalState[1]+3) - (piece.getIndex())) * 16;
                    //rows between the vertical goal states in the grid
                else if (piece.getRow() >= 4 && piece.getRow() <= 12){
                    int indexDist = Math.abs(piece.getIndex() - (goalState[1]+3));
                    if (indexDist > 3) distanceFromGoal += 2*indexDist*16;
                    else distanceFromGoal += indexDist * 16;
                }
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
            winLocation = playerNumber - 3;
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