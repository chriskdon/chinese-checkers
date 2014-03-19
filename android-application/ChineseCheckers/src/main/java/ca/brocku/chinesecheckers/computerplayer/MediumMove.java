package ca.brocku.chinesecheckers.computerplayer;

import android.util.Log;

import java.util.ArrayList;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.GridPiece;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.MovePath;


/**
 * Calculates the move for an artificial player of medium intelligence.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 19/03/2014
 */
public class MediumMove {
    private AiPlannedMove currentMove;
    private HeuristicCalculator cHeuristic;
    MovePath path;
    ArrayList<Position> visited;

    /**
     * Calculates the move that a medium AI will make.
     *
     * @param player Player number of the AI.
     * @param board  The current state of the board.
     * @return The move the AI chooses to make.
     */
    public MovePath getMediumMove(int player, GameBoard board) {
        visited = new ArrayList<Position>();
        currentMove = new AiPlannedMove();
        path = new MovePath();
        cHeuristic = new HeuristicCalculator(player, board);
        currentMove.setHeuristic(0);
        GameBoard tempBoard = board.getDeepCopy();
        int[] startingHeuristic = new int[10];
        int tempHeur;
        GridPiece tempPiece;
        Piece piece;

        GridPiece[] AIPieces = getPlayerPieces(player, tempBoard);

        for(int i = 0 ; i < 10 ; i++)//get starting heuristics of all player pieces
            startingHeuristic[i] = cHeuristic.getDistanceHeuristic(AIPieces[i].getPosition());

        for(int i = 0 ; i < startingHeuristic.length - 1 ; i++) //sort the arrays from closest to goal to farthest.
            for(int j = i ; j < startingHeuristic.length-1 ; j++)
                if(startingHeuristic[j] > startingHeuristic[j+1]){
                    tempHeur = startingHeuristic[j];
                    tempPiece = AIPieces[j];
                    startingHeuristic[j] = startingHeuristic[j+1];
                    AIPieces[j] = AIPieces[j+1];
                    startingHeuristic[j+1] = tempHeur;
                    AIPieces[j+1] = tempPiece;
                }

        //split
        for (int i = 5; i < AIPieces.length ; i++)
            if(startingHeuristic[i] - startingHeuristic[i-1] > 48) //play around with this number
              for(int j=i ; j < AIPieces.length ; j++)
                  startingHeuristic[j] += (startingHeuristic[i]-startingHeuristic[i-1]/(10-i));

        //back piece weight
        startingHeuristic[9] += 48; //Add extra weight so pieces aren't left behind(play around with these numbers)

        for (int i = 0 ; i < AIPieces.length ; i++) {
            piece = AIPieces[i];
            if(piece != null){

                visited.clear();
                if(path.size() > 0)
                    for(int j = path.size() ; j > 0 ; j--)
                        path.removeEndPosition();

                Position[] initialPossible = board.getPossibleMoves(piece);

                if(initialPossible != null && initialPossible.length > 0){
                    path.addToPath(piece.getPosition());//path = starting position
                    visited.add(piece.getPosition());   //visited = starting position
                    for(Position pos : initialPossible)
                        if(pos != null){
                            visited.add(pos);
                            checkHops(piece, pos, tempBoard, startingHeuristic[i]);
                        }
                }
            }
        }
        return new MovePath(currentMove.getPath());
    }

    /**
     * Returns all of the pieces on the gameboard owned by the player player.
     *
     * @param player is the owning player.
     * @param board  is the gameboard containing pieces.
     * @return array of pieces belonging to the player.
     */
    public GridPiece[] getPlayerPieces(int player, GameBoard board) {
        Piece[] allPieces = board.getAllPieces();
        GridPiece[] playerPieces = new GridPiece[10];
        int j = 0;
        for (Piece piece : allPieces)
            if (piece.getPlayerNumber() == player) {
                playerPieces[j] = new GridPiece(piece.getPosition(), player);
                j++;
            }
        return playerPieces;
    }

    /**
     * Recursively calculates the heuristic and path of all hops of any distance for the provided piece
     *
     * @param current   Piece to calculate hop paths for
     * @param movingTo The new position for the piece on the gameboard
     * @param tempBoard The gameboard
     *
     */
    private void checkHops(Piece current, Position movingTo, GameBoard tempBoard, int startHeur) {
        boolean alreadyVisited;
        path.addToPath(movingTo);
        visited.add(movingTo);

        tempBoard.movePiece(current, movingTo);

        //if(cHeuristic.getDeltaDistanceHeuristic(path) < 0 && currentMove.getHeuristic > 0){

        Position[] availableMoves = tempBoard.getPossibleHops(tempBoard.getPiece(movingTo));

        if ((startHeur - cHeuristic.getDistanceHeuristic(path.getEndPosition())) > currentMove.getHeuristic() || currentMove.getHeuristic() == 0) {
            Position[] toArrayList = path.getPath().toArray(new Position[path.size()]);
            ArrayList<Position>  newArrayList = new ArrayList<Position>();
            for(int i = 0 ; i < toArrayList.length ;  i++){
                newArrayList.add(toArrayList[i]);
            }
            currentMove.setHeuristic(startHeur - cHeuristic.getDistanceHeuristic(path.getEndPosition()));
            currentMove.setPath(newArrayList);
            currentMove.setPieceMoved(current);
        }
        if(availableMoves != null){
            for(Position newHop : availableMoves){
                alreadyVisited = false;
                for(Position hasBeenVisited : visited){ //checks if the position has been visited by this piece in a previous move
                    if(newHop.equals(hasBeenVisited))
                        alreadyVisited = true;
                }
                if(alreadyVisited == false){
                    checkHops(tempBoard.getPiece(movingTo), newHop, tempBoard, startHeur);}
            }
        }
        //} //skip this recursive check if the piece moves backwards
        path.removeEndPosition();
        tempBoard.movePiece(tempBoard.getPiece(movingTo), path.getEndPosition());
    }
}