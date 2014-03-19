package ca.brocku.chinesecheckers.gamestate;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ca.brocku.chinesecheckers.computerplayer.AiPlayer;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gameboard.IllegalMoveException;

/**
 * Handles coordinating the game between multiple players and keeping the game
 * board up to date.
 * <p/>
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/22/2014
 */
public class GameStateManager implements Parcelable, Serializable {
    public static final String SERIALIZED_FILENAME = "OfflineGame.ser";
    private static final int MIN_AI_MOVE_SPEED = 500; // The minimum speed an AI can move at

    private GameBoard gameBoard;
    private Map<Player.PlayerColor, Player> players;    // Players in the game
    private Player.PlayerColor currentPlayer;           // The current players turn

    private transient GameStateEvents gameStateEventsHandler;
    private transient boolean isRunning = false;

    /**
     * Constructor
     *
     * @param gameBoard The game board to use to manage the rules and state of the game.
     * @param players   The players in the game.
     */
    public GameStateManager(GameBoard gameBoard, ArrayList<Player> players) {
        this(gameBoard, players, null);
    }

    /**
     * Constructor
     *
     * @param gameBoard     The game board to use to manage the rules and state of the game.
     * @param players       The players in the game.
     * @param currentPlayer The current player's turn.
     */
    public GameStateManager(GameBoard gameBoard, ArrayList<Player> players, Player.PlayerColor currentPlayer) {
        if (gameBoard == null) {
            throw new IllegalArgumentException("Board must be defined.");
        }
        if (players == null || players.size() <= 0) {
            throw new IllegalArgumentException("Players must be defined.");
        }

        this.gameBoard = gameBoard;

        this.players = new HashMap<Player.PlayerColor, Player>(players.size());
        for (Player p : players) {
            this.players.put(p.getPlayerColor(), p);
        }

        if (currentPlayer == null) {
            this.currentPlayer = Player.FIRST_PLAYER;
        } else if (!this.players.containsKey(currentPlayer)) {
            throw new IllegalArgumentException("Current Player must be in Players list");
        } else {
            this.currentPlayer = currentPlayer;
        }
    }

    /**
     * Called after all the non-transient objects are ready. This should be called after all
     * constructor code. And after deserialization.
     */
    private void onStateReady(Activity activity) {
        if (this.gameBoard == null) {
            throw new IllegalStateException("GameBoard must be setup.");
        }
    }

    /**
     * Find the color that comes after a set color.
     *
     * @param currentPlayer Get the color after this.
     * @return Color after currentPlayer
     */
    private Player.PlayerColor getNextPlayer(Player.PlayerColor currentPlayer) {
        switch (players.size()) {
            case 2: {
                switch (currentPlayer) {
                    case RED:
                        return Player.PlayerColor.GREEN;
                    case GREEN:
                        return Player.PlayerColor.RED;
                }
            }

            case 3: {
                switch (currentPlayer) {
                    case RED:
                        return Player.PlayerColor.BLUE;
                    case BLUE:
                        return Player.PlayerColor.YELLOW;
                    case YELLOW:
                        return Player.PlayerColor.RED;
                }
            }

            case 4: {
                switch (currentPlayer) {
                    case RED:
                        return Player.PlayerColor.BLUE;
                    case BLUE:
                        return Player.PlayerColor.GREEN;
                    case GREEN:
                        return Player.PlayerColor.ORANGE;
                    case ORANGE:
                        return Player.PlayerColor.RED;
                }
            }

            case 6: {
                switch (currentPlayer) {
                    case RED:
                        return Player.PlayerColor.PURPLE;
                    case PURPLE:
                        return Player.PlayerColor.BLUE;
                    case BLUE:
                        return Player.PlayerColor.GREEN;
                    case GREEN:
                        return Player.PlayerColor.YELLOW;
                    case YELLOW:
                        return Player.PlayerColor.ORANGE;
                    case ORANGE:
                        return Player.PlayerColor.RED;
                }
            }
        }

        throw new IllegalArgumentException("Invalid Player Color");
    }

    /**
     * Start the game
     */
    public synchronized void startGame(final Activity activity) {
        this.onStateReady(activity);

        isRunning = true;

        // Game Loop
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    final Player p = getCurrentPlayer();

                    // Pre Move
                    if (GameStateManager.this.gameStateEventsHandler != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GameStateManager.this.gameStateEventsHandler.onPlayerTurn(p);
                            }
                        });
                    }

                    // Get Move
                    long start = System.currentTimeMillis();

                    final MovePath m = p.onTurn(getGameBoard());
                    final GameBoard originalBoard = gameBoard.getDeepCopy();

                    // Make AIs draw slower
                    long diff = System.currentTimeMillis() - start;
                    if (p instanceof AiPlayer && diff < MIN_AI_MOVE_SPEED && gameStateEventsHandler != null) {
                        try {
                            Thread.sleep(MIN_AI_MOVE_SPEED - diff);
                        } catch (InterruptedException ex) {
                            // Skip sleeping
                        }
                    }

                    // Save Move
                    writePathToBoard(p, m);

                    final boolean hasPlayerWon = gameBoard.hasPlayerWon(p.getPlayerNumber());

                    // Tell Activity
                    if (gameStateEventsHandler != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gameStateEventsHandler.onBoardModified(p, originalBoard, getGameBoard(), m);

                                // Notify player winning
                                if (hasPlayerWon) {
                                    gameStateEventsHandler.onPlayerWon(p, 1);
                                }

                                if (m.getPath().size() >= 9) {
                                    checkKonamiCode(p, m);
                                }
                            }
                        });
                    }

                    // Check if somebody won
                    if (hasPlayerWon) {
                        stopGame();
                    } else {
                        // Next Player
                        currentPlayer = getNextPlayer(getCurrentPlayer().getPlayerColor());
                    }
                }
            }
        }).start();
    }

    /**
     * Stop the game. Don't forget to call this or thread will keep running.
     */
    public synchronized void stopGame() {
        this.isRunning = false;
    }

    /**
     * Save the path to the GameBoard.
     *
     * @param player   The player that made the move.
     * @param movePath The path the move took.
     */
    private void writePathToBoard(Player player, MovePath movePath) {
        // Move the sequence of pieces
        Iterator<Position> it = movePath.getPath().iterator();
        Position last = null;
        while (it.hasNext()) {
            if (last == null) {
                last = it.next();
            }

            Position current = it.next();

            Piece piece = gameBoard.getPiece(last);

            // Check for illegal moves
            if (piece == null) {
                throw new IllegalMoveException("There is no piece at that position.");
            } else if (piece.getPlayerNumber() != player.getPlayerNumber()) {
                throw new IllegalMoveException("Player<" + player.getName() + "> cannot move that piece.");
            }

            gameBoard.movePiece(piece, current);

            last = current;
        }
    }

    /*
    * checkKonamiCode
    * Sees if the player's name and path adds up to the Konami Code
    */
    private void checkKonamiCode(Player p, MovePath m) {
//        Log.e("Holla", "Entered");
        if ((m.getPosition(0).getRow() > m.getPosition(1).getRow())) { // && //Up
//            Log.e("Holla","Up");
            if (m.getPosition(1).getRow() > m.getPosition(2).getRow()) { // && //Up
//                Log.e("Holla","Up");
                if (m.getPosition(2).getRow() < m.getPosition(3).getRow()) { //&& //Down
//                    Log.e("Holla","Down");
                    if (m.getPosition(3).getRow() < m.getPosition(4).getRow()) { //&& //Down
//                        Log.e("Holla","Down");
                        if (m.getPosition(4).getIndex() > m.getPosition(5).getIndex()) { // && //Left
//                            Log.e("Holla","Left");
                            if (m.getPosition(5).getIndex() < m.getPosition(6).getIndex()) { // && //Right
//                                Log.e("Holla","Right");
                                if (m.getPosition(6).getIndex() > m.getPosition(7).getIndex()) { // && //Left
//                                    Log.e("Holla","Left");
                                    if (m.getPosition(7).getIndex() < m.getPosition(8).getIndex()) { // && //Right
//                                        Log.e("Holla","Right");
                                        if (p.getName().contains("ba")) {//){
//                                          Log.e("Holla", "Winn");
                                            gameStateEventsHandler.onPlayerWon(p, p.getPlayerNumber());
                                            stopGame();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Player[] getPlayers() {
        Player[] playerArray = new Player[getNumberOfPlayers()];
        Iterator it = players.entrySet().iterator();

        int counter = 0;
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            playerArray[counter++] = (Player) pairs.getValue();
            //it.remove();
        }

        return playerArray;
    }

    /**
     * Get the number of players in the game.
     *
     * @return
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Return the player who's turn it currently is.
     *
     * @return The player.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Return a gameboard that can only be looked at.
     *
     * @return The gameboard.
     */
    public ReadOnlyGameBoard getGameBoard() {
        return new ReadOnlyGameBoard(gameBoard);
    }

    /**
     * Parcelable constructor
     *
     * @param parcel The parcel instance to generate the instance from.
     */
    private GameStateManager(Parcel parcel) {
        this((GameBoard) parcel.readParcelable(GameBoard.class.getClassLoader()),
                parcel.readArrayList(Player.class.getClassLoader()),
                Player.PlayerColor.valueOf(parcel.readString()));
    }

    /**
     * Set the event handler for the various game state events.
     *
     * @param handler The handler to register
     */
    public void setGameStateEventsHandler(GameStateEvents handler) {
        this.gameStateEventsHandler = handler;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        this.stopGame();
        dest.writeParcelable(gameBoard, 0);
        dest.writeList(new ArrayList<Player>(players.values()));
        dest.writeString(currentPlayer.toString());
    }

    /**
     * Recreate this instance
     */
    public static final Parcelable.Creator<GameStateManager> CREATOR =
            new Parcelable.Creator<GameStateManager>() {

                public GameStateManager createFromParcel(Parcel in) {
                    return new GameStateManager(in);
                }

                public GameStateManager[] newArray(int size) {
                    return new GameStateManager[size];
                }
            };

    /**
     * Events that the GameStateManager will throw throughout
     * the lifecycle of the game.
     */
    public static interface GameStateEvents {
        /**
         * Fired when it is a new players turn.
         *
         * @param player The player who's turn it is.
         */
        public void onPlayerTurn(Player player);

        /**
         * Fired when a piece on the board is moved from one position to another.
         *
         * @param player        The player who modified the board
         * @param originalBoard The original copied state of the board.
         * @param currentBoard  The current game board.
         * @param movePath      The path describing the movePath.
         */
        public void onBoardModified(Player player, GameBoard originalBoard, ReadOnlyGameBoard currentBoard, MovePath movePath);

        /**
         * Occurs when a player forfeit the game.
         * <p/>
         * TODO: Is this just because they quit, or could this be a result of them taking to long to make a move and being kicked out.
         *
         * @param player The player who forfeited.
         */
        public void onForfeit(Player player);

        /**
         * Occurs when a player wins the game.
         *
         * @param player   The player that won.
         * @param position The position they finished in (1st, 2nd, 3rd, etc.).
         */
        public void onPlayerWon(Player player, int position);
    }


    /**
     * Thi is a private method that the virtual machine will call when this object is deserialized.
     * <p/>
     * This method is used in order to re-set the events for this object in addition to
     * deserializing it. This mechanism is available from implementing Serializable.
     *
     * @param in the ObjectInputStream which will deserialize this object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); //Deserializes the GameStateManager object
    }
}
