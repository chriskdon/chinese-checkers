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
public class HardMove {
    private AiPlannedMove currentBestMove;
    private HeuristicCalculator cHeuristic;
    MovePath path, nextPath;
    ArrayList<Position> visited, visitedNextTurn;
    GridPiece[] AIPieces;
    int nextTurnHeuristic;

    /**
     * Calculates the move that a medium AI will make.
     *
     * @param player Player number of the AI.
     * @param board  The current state of the board.
     * @return The move the AI chooses to make.
     */
    public MovePath getHardMove(int player, GameBoard board) {
        visited = new ArrayList<Position>();
        visitedNextTurn = new ArrayList<Position>();
        currentBestMove = new AiPlannedMove();
        path = new MovePath();
        nextPath = new MovePath();
        cHeuristic = new HeuristicCalculator(player, board);
        GameBoard tempBoard = board.getDeepCopy();
        Piece piece;

        AIPieces = getPlayerPieces(player, tempBoard);

        for (int i = 0 ; i < AIPieces.length ; i++) {
            piece = AIPieces[i];
            if(piece != null){

                visited.clear();
                if(path.size() > 0)
                    for(int j = path.size() ; j > 0 ; j--)
                        path.removeEndPosition();

                Position[] initialPossible = board.getPossibleMoves(piece);

                if(initialPossible != null && initialPossible.length > 0){
                    path.addToPath(piece.getPosition());    //path = starting position
                    visited.add(piece.getPosition());       //visited = starting position
                    for(Position pos : initialPossible)
                        if(pos != null){
                            calculatePossibleMoves(piece, pos, tempBoard, true);
                        }
                }
            }
        }

        //Helps prevent pieces getting locked near the goal state
        if(board.atGoalEdge(currentBestMove.getPath().get(0), player) && board.atGoalEdge(currentBestMove.getPath().get(currentBestMove.getPath().size()-1), player)){
            Position thisMove = currentBestMove.getPath().get(currentBestMove.getPath().size()-1), initialPosition = currentBestMove.getPath().get(0);
            int tempHeuristic = 0;
            Position[] initialPossible = board.getPossibleMoves(currentBestMove.getPieceMoved());

            for(Position pos : initialPossible){
                if(board.atGoalEdge(pos, player)){
                    tempBoard.forceMove((GridPiece) currentBestMove.getPieceMoved(), pos);
                    Position[] newPossible = tempBoard.getPossibleMoves(currentBestMove.getPieceMoved());

                    for(Position checkNextTurn : newPossible)
                        if(cHeuristic.getDeltaDistanceHeuristic(new MovePath(currentBestMove.getPath().get((0)), checkNextTurn)) > tempHeuristic)
                            thisMove = pos;
                    tempBoard.forceMove((GridPiece) currentBestMove.getPieceMoved(), initialPosition);
                }
            }
            return new MovePath(initialPosition, thisMove);
        }
        return new MovePath(currentBestMove.getPath());
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

    private void calculatePossibleMoves(Piece current, Position movingTo, GameBoard tempBoard, boolean isThisTurn) {
        boolean alreadyVisited;
        if(isThisTurn){
            path.addToPath(movingTo);
            visited.add(movingTo);
        }
        else{
            nextPath.addToPath(movingTo);
            visitedNextTurn.add(movingTo);
        }

        tempBoard.forceMove((GridPiece)current, movingTo);
        Position[] availableMoves = tempBoard.getPossibleHops(tempBoard.getPiece(movingTo));

        if(isThisTurn){
            checkNextTurnMoves(tempBoard);
            if ((cHeuristic.getDeltaDistanceHeuristic(path) + nextTurnHeuristic) > currentBestMove.getHeuristic() || currentBestMove.getHeuristic() == 0) {
                Position[] toArrayList = path.getPath().toArray(new Position[path.size()]);
                ArrayList<Position>  newArrayList = new ArrayList<Position>();
                for(int i = 0 ; i < toArrayList.length ;  i++){
                    newArrayList.add(toArrayList[i]);
                }
                currentBestMove.setHeuristic(cHeuristic.getDeltaDistanceHeuristic(path) + nextTurnHeuristic);
                currentBestMove.setPath(newArrayList);
                currentBestMove.setPieceMoved(current);
            }
        }
        else {
            if(cHeuristic.getDeltaDistanceHeuristic(nextPath) > nextTurnHeuristic)
                nextTurnHeuristic = cHeuristic.getDeltaDistanceHeuristic(nextPath);
        }
        //Log.wtf("myApp", "Current best move is (" + currentBestMove.getPath().get(0).getRow() + "," + currentBestMove.getPath().get(0).getIndex() + ") to (" + currentBestMove.getPath().get(currentBestMove.getPath().size()-1).getRow() + "," + currentBestMove.getPath().get(currentBestMove.getPath().size()-1).getIndex() + ")");
        if(availableMoves != null){
            for(Position newHop : availableMoves){
                alreadyVisited = false;
                if(isThisTurn){
                    for(Position hasBeenVisited : visited){ //checks if the position has been visited by this piece in a previous move
                        if(newHop.equals(hasBeenVisited))
                            alreadyVisited = true;
                    }
                }
                else{
                    for(Position hasBeenVisited : visitedNextTurn){
                        if(newHop.equals(hasBeenVisited))
                            alreadyVisited = true;
                    }
                }
                if(alreadyVisited == false){
                    calculatePossibleMoves(tempBoard.getPiece(movingTo), newHop, tempBoard, isThisTurn);}
            }
        }
        if(isThisTurn){
            path.removeEndPosition();
            tempBoard.forceMove((GridPiece)tempBoard.getPiece(movingTo), path.getEndPosition());
        }
        else{
            nextPath.removeEndPosition();
            tempBoard.forceMove((GridPiece)tempBoard.getPiece(movingTo), nextPath.getEndPosition());
        }
    }

    private void checkNextTurnMoves(GameBoard board){
        Piece piece;
        for (int i = 0 ; i < AIPieces.length ; i++) {
            piece = AIPieces[i];
            if(piece != null){

                visitedNextTurn.clear();
                if(nextPath.size() > 0)
                    for(int j = nextPath.size() ; j > 0 ; j--)
                        nextPath.removeEndPosition();

                Position[] initialPossible = board.getPossibleMoves(piece);

                if(initialPossible != null && initialPossible.length > 0){
                    nextPath.addToPath(piece.getPosition());        //nextPath = starting position
                    visitedNextTurn.add(piece.getPosition());       //visitedNextTurn = starting position
                    for(Position pos : initialPossible)
                        if(pos != null){
                            calculatePossibleMoves(piece, pos, board, false);
                        }
                }
            }
        }
    }

}