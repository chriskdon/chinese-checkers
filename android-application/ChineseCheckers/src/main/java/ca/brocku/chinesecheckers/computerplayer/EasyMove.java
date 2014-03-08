package ca.brocku.chinesecheckers.computerplayer;

import java.util.ArrayList;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Move;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class EasyMove {
    private AIPlannedMove currentMove;
    private HeuristicCalculator cHeuristic;

    /**
     * Calculates the move that an easy AI would make
     *
     * @param player Player number of the AI.
     * @param board  The current state of the board.
     * @return The move the AI chooses to make.
     */
    public Move getEasyMove(int player, GameBoard board) {
        Piece[] AIPieces = getPlayerPieces(player, board);
        GameBoard tempBoard = board;
        int tempHeuristic;
        Move move;
        cHeuristic = new HeuristicCalculator(player, board);
        ArrayList<Position> path;

        /*
        Gets each single step move for each player-owned piece.
         */
        for (Piece piece : AIPieces) {
            path = null;

            Position[] initialPossible = board.getPossibleMoves(piece);
            ArrayList<Position> hops = new ArrayList<Position>();
            if (initialPossible != null) {
                for (Position p : initialPossible) {
                    if (Math.abs(p.getRow() - piece.getPosition().getRow()) == 2 ||
                            Math.abs(p.getIndex() - piece.getPosition().getIndex()) == 2) {

                        hops.add(p);
                    }
                }
            }

            //Compares the preference of the move to the currently favoured move
            for (Position pos : initialPossible) {
                tempBoard = board;
                path.add(pos);
                tempBoard.forceMove(piece, pos);
                tempHeuristic = cHeuristic.getDistanceHeuristic(piece);
                if (currentMove.getHeuristic() == 0) {
                    currentMove.setPieceMoved(piece);
                    currentMove.setPath(path);
                    currentMove.setHeuristic(tempHeuristic);
                } else if (tempHeuristic < currentMove.getHeuristic()) {
                    currentMove.setHeuristic(tempHeuristic);
                    currentMove.setPieceMoved(piece);
                    currentMove.setPath(path);
                }
                path.remove(path.size() - 1); //remove the completed move
            }
            checkAllHops(piece, initialPossible, tempBoard, path);
        }
        move = new Move(currentMove.getPieceMoved(), currentMove.getPath());
        return move;
    }

    /**
     * Returns all of the pieces on the gameboard owned by the player player.
     *
     * @param player is the owning player.
     * @param board  is the gameboard containing pieces.
     * @return array of pieces belonging to the player.
     */
    public Piece[] getPlayerPieces(int player, GameBoard board) {
        Piece[] allPieces = board.getAllPieces(), playerPieces = new Piece[10];
        int j = 0;
        for (int i = 0; i < allPieces.length; i++)
            if (allPieces[i].getPlayerNumber() == player) {
                playerPieces[j] = allPieces[i];
                j++;
            }

        return playerPieces;
    }

    /**
     * Recursively calculates the heuristic and path of all hops of any distance for the provided piece
     *
     * @param current   Piece to calculate hop paths for
     * @param listSoFar Current list of moves for the piece
     * @param tempBoard The gameboard
     * @param path      The path taken to the current position for the piece
     * @return An array of positions that the piece can move to
     */
    private void checkAllHops(Piece current, Position[] listSoFar, GameBoard tempBoard, ArrayList<Position> path) {
        Boolean existingHop;
        Position currentPosition = current.getPosition();
        Position[] hops = tempBoard.getPossibleHops(current);
        //ArrayList<Position> newHops = new ArrayList<Position>();
        int thisHeuristic;

        //remove duplicate hops
        if (hops != null) {
            for (Position position : hops) {                    //for each possible hop
                existingHop = false;
                for (Position existingPosition : listSoFar) {   //remove previously visitted positions
                    if (existingPosition == position)
                        existingHop = true;
                }
                if (!existingHop) {
                    path.add(position);                         //check if this is the new best move
                    thisHeuristic = cHeuristic.getDistanceHeuristic(current);
                    if (thisHeuristic <= currentMove.getHeuristic()) {
                        currentMove.setHeuristic(thisHeuristic);
                        currentMove.setPath(path);
                        currentMove.setPieceMoved(current);
                    }
                    listSoFar[listSoFar.length] = position;     //add to visitted positions
                    checkAllHops(current, listSoFar, tempBoard, path);  //recursive call
                    path.remove(path.size() - 1);                 //remove latest hop
                    tempBoard.forceMove(current, path.get(path.size() - 1));


                }
            }
        }

    }
}
