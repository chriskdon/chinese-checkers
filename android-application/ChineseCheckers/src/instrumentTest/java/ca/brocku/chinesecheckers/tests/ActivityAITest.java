package ca.brocku.chinesecheckers.tests;

import android.test.AndroidTestCase;

import ca.brocku.chinesecheckers.computerplayer.AIPlayer;
import ca.brocku.chinesecheckers.computerplayer.HeuristicCalculator;
import ca.brocku.chinesecheckers.gameboard.CcGameBoard;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.MovePath;
import ca.brocku.chinesecheckers.gamestate.Player;

/**
 * Created by Administrator on 10/03/14.
 */
public class ActivityAITest extends AndroidTestCase  {

/*    public ActivityAITest(){
        testCorrectDirection();

        testChainHopping();
        for(int i = 1 ; i <= 6 ; i++)
            testDistanceHeuristic(i);
    }*/
    public void testCorrectDirection(){
        directionEvaluation("EASY", 1, Player.PlayerColor.RED);
        directionEvaluation("EASY", 2, Player.PlayerColor.PURPLE);
        directionEvaluation("EASY", 3, Player.PlayerColor.BLUE);
        directionEvaluation("EASY", 4, Player.PlayerColor.GREEN);
        directionEvaluation("EASY", 5, Player.PlayerColor.YELLOW);
        directionEvaluation("EASY", 6, Player.PlayerColor.ORANGE);

        /*
        directionEvaluation("MEDIUM", 1, Player.PlayerColor.RED);
        directionEvaluation("MEDIUM", 2, Player.PlayerColor.PURPLE);
        directionEvaluation("MEDIUM", 3, Player.PlayerColor.BLUE);
        directionEvaluation("MEDIUM", 4, Player.PlayerColor.GREEN);
        directionEvaluation("MEDIUM", 5, Player.PlayerColor.YELLOW);
        directionEvaluation("MEDIUM", 6, Player.PlayerColor.ORANGE);

        directionEvaluation("HARD", 1, Player.PlayerColor.RED);
        directionEvaluation("HARD", 2, Player.PlayerColor.PURPLE);
        directionEvaluation("HARD", 3, Player.PlayerColor.BLUE);
        directionEvaluation("HARD", 4, Player.PlayerColor.GREEN);
        directionEvaluation("HARD", 5, Player.PlayerColor.YELLOW);
        directionEvaluation("HARD", 6, Player.PlayerColor.ORANGE);
        */
    }

    public void directionEvaluation(String AILevel, int playerNumber, Player.PlayerColor color) {
        CcGameBoard board = new CcGameBoard();
        int opponentNumber;
        AIPlayer computerPlayer = new AIPlayer(AILevel, color);

        board.setPiece(newPosition(8, 4), playerNumber);
        if(playerNumber<6) opponentNumber = 6;
        else opponentNumber = 1;
        board.setPiece(newPosition(5, 5), opponentNumber);
        board.setPiece(newPosition(7, 2), opponentNumber);
        board.setPiece(newPosition(7, 4), opponentNumber);
        board.setPiece(newPosition(7, 5), opponentNumber);
        board.setPiece(newPosition(7, 7), opponentNumber);
        board.setPiece(newPosition(8, 3), opponentNumber);
        board.setPiece(newPosition(8, 5), opponentNumber);
        board.setPiece(newPosition(9, 2), opponentNumber);
        board.setPiece(newPosition(9, 4), opponentNumber);
        board.setPiece(newPosition(9, 5), opponentNumber);
        board.setPiece(newPosition(9, 7), opponentNumber);
        board.setPiece(newPosition(11, 5), opponentNumber);

        MovePath move = computerPlayer.getMove(new ReadOnlyGameBoard(board));
        Position position = move.getEndPosition();

        switch(playerNumber){
            case 1:
                assertEquals("Final row should be 4", 4, position.getRow());//got 8
                assertEquals("Final index should be 6", 6, position.getIndex());
                break;
            case 2:
                //assertEquals("Final row should be 6", 6, position.getRow());//got 4
                assertEquals("Final index should be 8", 8, position.getIndex());//got 6
                break;
            case 3:
                assertEquals("Final row should be 10", 10, position.getRow());
                assertEquals("Final index should be 8", 8, position.getIndex());
                break;
            case 4:
                assertEquals("Final row should be 12", 12, position.getRow());
                assertEquals("Final index should be 6", 6, position.getIndex());
                break;
            case 5:
                assertEquals("Final row should be 10", 10, position.getRow());
                assertEquals("Final index should be 2", 2, position.getIndex());
                break;
            case 6:
                assertEquals("Final row should be 6", 6, position.getRow());
                assertEquals("Final index should be 2", 2, position.getIndex());
                break;
            default:
                assertTrue("Invalid player number.", false);
        }
    }

    public void testChainHopping(){
        hoppingEvaluation("EASY", Player.PlayerColor.RED);
        /*
        hoppingEvaluation("MEDIUM", Player.PlayerColor.RED);
        hoppingEvaluation("HARD", Player.PlayerColor.RED);
        */
    }

    public void hoppingEvaluation(String AILevel, Player.PlayerColor color){
        CcGameBoard board = new CcGameBoard();
        AIPlayer computerPlayer = new AIPlayer(AILevel, color);

        board.setPiece(newPosition(5, 5), 1);
        board.setPiece(newPosition(7, 5), 1);
        board.setPiece(newPosition(7, 4), 1);
        board.setPiece(newPosition(9, 4), 1);
        board.setPiece(newPosition(11, 4), 1);
        board.setPiece(newPosition(12, 4), 1);

        MovePath move = computerPlayer.getMove(new ReadOnlyGameBoard(board));
        Position position = move.getEndPosition();
        assertEquals("Final row should be 4", 4, position.getRow());    //got 3
        assertEquals("Final index should be 6", 6, position.getIndex());//got 0
    }

    public void testHeuristics(){
        testDistanceHeuristic(1);
        testDistanceHeuristic(2);
        testDistanceHeuristic(3);
        testDistanceHeuristic(4);
        testDistanceHeuristic(5);
        testDistanceHeuristic(6);
    }

    public void testDistanceHeuristic(int playerNumber){
        CcGameBoard board = new CcGameBoard();
        HeuristicCalculator cHeuristic = new HeuristicCalculator(playerNumber, board);

        //board.setPiece(newPosition(0,0),playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (0,0) should be 32.", 32, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
            case 2:
                assertEquals("Distance heuristic at (0,0) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
            case 3:
                assertEquals("Distance heuristic at (0,0) should be 240.", 240, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
            case 4:
                assertEquals("Distance heuristic at (0,0) should be 352.", 352, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
            case 5:
                assertEquals("Distance heuristic at (0,0) should be 240.", 240, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
            case 6:
                assertEquals("Distance heuristic at (0,0) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(0,0)));
                break;
        }

        //board.setPiece(newPosition(16,0), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (16,0) should be 352.", 352, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
            case 2:
                assertEquals("Distance heuristic at (16,0) should be 240.", 240, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
            case 3:
                assertEquals("Distance heuristic at (16,0) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
            case 4:
                assertEquals("Distance heuristic at (16,0) should be 32.", 32, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
            case 5:
                assertEquals("Distance heuristic at (16,0) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
            case 6:
                assertEquals("Distance heuristic at (16,0) should be 240.", 240, cHeuristic.getDistanceHeuristic(newPosition(16,0)));
                break;
        }

        //board.setPiece(newPosition(4,0), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (4,0) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
            case 2:
                assertEquals("Distance heuristic at (4,0) should be 216.", 216, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
            case 3:
                assertEquals("Distance heuristic at (4,0) should be 344.", 344, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
            case 4:
                assertEquals("Distance heuristic at (4,0) should be 288.", 288, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
            case 5:
                assertEquals("Distance heuristic at (4,0) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
            case 6:
                assertEquals("Distance heuristic at (4,0) should be 0.", 0, cHeuristic.getDistanceHeuristic(newPosition(4,0)));
                break;
        }

        //board.setPiece(newPosition(12,0), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (12,0) should be 288.", 288, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
            case 2:
                assertEquals("Distance heuristic at (12,0) should be 344.", 344, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
            case 3:
                assertEquals("Distance heuristic at (12,0) should be 216.", 216, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
            case 4:
                assertEquals("Distance heuristic at (12,0) should be 128.",128, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
            case 5:
                assertEquals("Distance heuristic at (12,0) should be 0.", 0, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
            case 6:
                assertEquals("Distance heuristic at (12,0) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(12,0)));
                break;
        }

        //board.setPiece(newPosition(12,12), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (12,12) should be 288.", 288, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break; //check to see if this should be 240
            case 2:
                assertEquals("Distance heuristic at (12,12) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break;
            case 3:
                assertEquals("Distance heuristic at (12,12) should be 0.", 0, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break;
            case 4:
                assertEquals("Distance heuristic at (12,12) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break;
            case 5:
                assertEquals("Distance heuristic at (12,12) should be 144.", 144, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break;
            case 6:
                assertEquals("Distance heuristic at (12,12) should be 272.", 272, cHeuristic.getDistanceHeuristic(newPosition(12,12)));
                break;
        }

        //board.setPiece(newPosition(4,12), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (4,12) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
            case 2:
                assertEquals("Distance heuristic at (4,12) should be 0.", 0, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
            case 3:
                assertEquals("Distance heuristic at (4,12) should be 128.", 128, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
            case 4:
                assertEquals("Distance heuristic at (4,12) should be 288.", 288, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
            case 5:
                assertEquals("Distance heuristic at (4,12) should be 272.", 272, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
            case 6:
                assertEquals("Distance heuristic at (4,12) should be 144.", 144, cHeuristic.getDistanceHeuristic(newPosition(4,12)));
                break;
        }

        //board.setPiece(newPosition(8,4), playerNumber);
        switch(playerNumber){
            case 1:
                assertEquals("Distance heuristic at (8,4) should be 160.", 160, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
            case 2:
                assertEquals("Distance heuristic at (8,4) should be 208.", 208, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
            case 3:
                assertEquals("Distance heuristic at (8,4) should be 208.", 208, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
            case 4:
                assertEquals("Distance heuristic at (8,4) should be 160.", 160, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
            case 5:
                assertEquals("Distance heuristic at (8,4) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
            case 6:
                assertEquals("Distance heuristic at (8,4) should be 112.", 112, cHeuristic.getDistanceHeuristic(newPosition(8,4)));
                break;
        }
    }

    private Position newPosition(int row, int index) {
        return new Position(row, index);
    }
}