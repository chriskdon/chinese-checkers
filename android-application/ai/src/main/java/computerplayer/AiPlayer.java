package computerplayer;


import gameboard.ReadOnlyGameBoard;
import gamestate.MovePath;
import gamestate.Player;


/**
 * A potential move to be made by a computer player.
 *
 * Author: James Kostiuk
 * Student #: 4366340
 * Date: 2/24/2014
 */
public class AiPlayer extends gamestate.Player {
    private String AILevel;
    private int difficulty;

    public AiPlayer(String AILevel, PlayerColor playerColor){
        super(playerColor);
        this.AILevel = AILevel;
        this.difficulty = getDifficulty(AILevel);
    }

    public static final int getDifficulty(String level) {
        if(level.equals("EASY")) return 1;
        if(level.equals("MEDIUM")) return 2;
        if(level.equals("HARD")) return 3;

        throw new IllegalArgumentException("Difficulty must be EASY, MEDIUM, or HARD.");
    }

    public int getDifficulty(){ return difficulty; }

    /**
     * Executed when it is this players turn to act.
     *
     * @param gameBoard The current game board.
     * @return The move that the AI chooses to make.
     */
    public MovePath getMove(ReadOnlyGameBoard gameBoard) {
        MovePath move;
        switch(this.difficulty){
            //case 3: hardMove myHardMove = new HardAIMove();
            //  move = myHardMove.getHardMove(getPlayerNumber(), gameBoard.getDeepCopy()
            case 2:
                MediumMove myMediumMove = new MediumMove();
                move = myMediumMove.getMediumMove(getPlayerNumber(), gameBoard.getDeepCopy());
                break;
            default:
                EasyMove myEasyMove = new EasyMove();
                move = myEasyMove.getEasyMove(getPlayerNumber(), gameBoard.getDeepCopy());
                break;
        }
        return move;
    }

    /**
     * Executed when it is this players turn to act.
     *
     * @param board
     */
    @Override
    public MovePath onTurn(ReadOnlyGameBoard board) {
        return getMove(board);
    }

    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        switch(getPlayerColor()){
            case RED: return "AI Robbie";
            case PURPLE: return  "AI Roboto";
            case BLUE: return "AI Roomba";
            case GREEN: return "AI Roberto";
            case YELLOW: return "AI Robeye";
            case ORANGE: return "AI Robber";
        }
        throw new IllegalStateException("Invalid Player Color");
    }

}
