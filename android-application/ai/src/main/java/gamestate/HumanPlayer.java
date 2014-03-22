package gamestate;


import gameboard.ReadOnlyGameBoard;

/**
 * The implementation of Player that is used to represent a chinese checkers player.
 *
 * Author: Peter Pobojewski
 * Student #: 4528311
 * Date: 2/13/2014
 */
public class HumanPlayer extends Player {
    private String name;

    // This needs to be small enough that a human can't detect the difference between tapping
    // the screen and seeing their piece move;
    private static final int THREAD_SLEEP_TIME = 100;

    /**
     * Create a new player.
     *
     * @param name          The name of the player.
     * @param playerColor   The color of the player.
     */
    public HumanPlayer(String name, PlayerColor playerColor) {
        super(playerColor);

        this.name = name;
    }

    /**
     * Returns the name of a player
     *
     * @return  Player's name
     */
    public String getName() {
        return name;
    }

    private MovePath m;

    /**
     * Executed when it is this players turn to act.
     *
     * @param board
     */
    @Override
    public MovePath onTurn(ReadOnlyGameBoard board) {
        while(m == null){
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex); // Rethrow at runtime
            }

        }

        MovePath temp = m;
        m = null;
        return temp;
    }

    public void signalMove(MovePath movePath) {
        m = movePath;
    }

}
