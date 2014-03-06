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

    /**
     * Calculates the move that an easy AI would make
     *
     * @param player Player number of the AI.
     * @param board The current state of the board.
     * @return The move the AI chooses to make.
     */
    public Move getEasyMove(int player, GameBoard board) {
        Piece[] AIPieces = getPlayerPieces(player, board);
        GameBoard tempBoard;
        Move move = null;
        HeuristicCalculator cHeuristic = new HeuristicCalculator(player, board);

        /*
        Gets each possible move for each player-owned piece.
         */
        for (Piece piece : AIPieces) {
            //Easy AI is a greedy AI, so boardstate is irrelivent, only delta pieceHeuristic matters
            int currentHeuristic = cHeuristic.getDistanceHeuristic(piece), tempHeuristic;
            AIPlannedMove currentMove = null;

            Position[] initialPossible = board.getPossibleMoves(piece);
            ArrayList<Position> hops = new ArrayList<Position>();
            int j = 0;
            if (initialPossible != null) {
                for (Position p : initialPossible) {
                    if (Math.abs(p.getRow() - piece.getPosition().getRow()) == 2 ||
                            Math.abs(p.getIndex() - piece.getPosition().getIndex()) == 2) {

                        hops.add(p);
                    }
                }
            }
            //adds chain hops to the list of possible moves
            Position[] allPossible = getAllHops(piece, hops.toArray(new Position[hops.size()]), board);

            //Compares the preference of the move to the currently favoured move
            for (Position pos : allPossible) {
                tempBoard = board;
                tempBoard.forceMove(piece, pos);
                tempHeuristic = cHeuristic.getDistanceHeuristic(piece);
                if(currentMove == null){
                    currentMove = new AIPlannedMove(piece, pos, tempHeuristic);
                }
                if (tempHeuristic < currentMove.getHeuristic()) {
                    //replace current AIPlannedMove
                    currentMove.setHeuristic(tempHeuristic);
                    currentMove.setPieceMoved(piece);
                    currentMove.setNewPosition(pos);
                }
            }
        }
        move = new Move();
        return move;
    }

    /**
     * Returns all of the pieces on the gameboard owned by the player player.
     *
     * @param player is the owning player.
     * @param board is the gameboard containing pieces.
     * @return array of pieces belonging to the player.
     */
    public Piece[] getPlayerPieces(int player, GameBoard board){
        Piece[] allPieces = board.getAllPieces(), playerPieces = new Piece[10];
        int j = 0;
        for (int i=0 ; i < allPieces.length ; i++)
            if(allPieces[i].getPlayerNumber() == player){
                playerPieces[j] = allPieces[i];
                j++;
            }

        return playerPieces;
    }

    /**
     * Recursively calculates all hops of any distance for the provided piece
     *
     * @param current Piece to calculate hop paths for
     * @param listSoFar Current list of moves for the piece
     * @param tempBoard The gameboard
     * @return An array of positions that the piece can move to
     */
    private Position[] getAllHops(Piece current, Position[] listSoFar, GameBoard tempBoard){
        Boolean existingHop;
        Position currentPosition = current.getPosition();
        Position[] hops = tempBoard.getPossibleHops(current);
        ArrayList<Position> newHops = new ArrayList<Position>();

        //remove duplicate hops
        if(hops == null)
            return hops;
        else
            for(Position position: hops){
                existingHop = false;
                for(Position existingPosition: listSoFar){
                    if(existingPosition == position)
                        existingHop = true;
                }
                if(existingHop == false)
                    newHops.add(position);
            }

       //for each new, non-duplicate hop, this function calls itself
        if(newHops.size() > 0){
            Position[] allNewHops = newHops.toArray(new Position[newHops.size()]);
            int j = listSoFar.length;
            for(int i = 0 ; i < allNewHops.length ; i++)
            listSoFar[j] = allNewHops[i];
            j++;
            for(Position position : allNewHops)
                    tempBoard.forceMove(current, position);
                    listSoFar = getAllHops(current, listSoFar, tempBoard);
        }

        //sets the board back to its initial state. Not required, but neater.
        tempBoard.forceMove(current, currentPosition);

        return listSoFar;


    }
}
