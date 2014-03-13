package ca.brocku.chinesecheckers.computerplayer;

import java.util.ArrayList;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.GridPiece;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.MovePath;


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
    MovePath path;
    ArrayList<Position> visited;

    //Position[] visited;
    /**
     * Calculates the move that an easy AI would make
     *
     * @param player Player number of the AI.
     * @param board  The current state of the board.
     * @return The move the AI chooses to make.
     */
    public MovePath getEasyMove(int player, GameBoard board) {
        visited = new ArrayList<Position>();
        currentMove = new AIPlannedMove();
        path = new MovePath();
        cHeuristic = new HeuristicCalculator(player, board);
        GameBoard tempBoard = board.getDeepCopy();

        GridPiece[] AIPieces = getPlayerPieces(player, tempBoard);

        for (Piece piece : AIPieces) {
            if(piece != null){
                visited.clear();

                if(path.size() > 0)
                    for(int i = path.size() ; i > 0 ; i--)
                        path.removeEndPosition();

                path.addToPath(piece.getPosition());//path = starting position
                visited.add(piece.getPosition());   //visited = starting position

                Position[] initialPossible = board.getPossibleMoves(piece);

                if(initialPossible != null && initialPossible.length > 0)
                    for(Position pos : initialPossible)
                        if(pos != null){
                            visited.add(pos);
                            checkHops(piece, pos, tempBoard);
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
    private void checkHops(Piece current, Position movingTo, GameBoard tempBoard) {
        boolean alreadyVisited;
        path.addToPath(movingTo);
        visited.add(movingTo);

        tempBoard.movePiece(current, movingTo);
        Position[] availableMoves = tempBoard.getPossibleHops(tempBoard.getPiece(movingTo));

        if (cHeuristic.getDeltaDistanceHeuristic(path) > currentMove.getHeuristic()) {
                    Position[] toArrayList = path.getPath().toArray(new Position[path.size()]);
            ArrayList<Position>  newArrayList = new ArrayList<Position>();
            for(int i = 0 ; i < toArrayList.length ;  i++){
                newArrayList.add(toArrayList[i]);
            }
            currentMove.setHeuristic(cHeuristic.getDeltaDistanceHeuristic(path));
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
                    checkHops(tempBoard.getPiece(movingTo), newHop, tempBoard);}
            }
        }
        path.removeEndPosition();
        tempBoard.movePiece(tempBoard.getPiece(movingTo), path.getEndPosition());
    }
}