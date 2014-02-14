package ca.brocku.chinesecheckers.uiengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.handlers.BoardUiEventsHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedMovingPieceHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedRotatingBoardHandler;
import ca.brocku.chinesecheckers.uiengine.visuals.GameBoardVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.PieceVisual;

/**
 * The ui for the game board components and all the actions you can do
 * when interacting with the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public class GameBoardUiView extends SurfaceView implements BoardUiEngine {
    private Compositor compositor = new Compositor();
    private int playerCount;

    public GameBoardUiView(Context context) {
        super(context);
    }

    public GameBoardUiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardUiView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Register the handler to start drawing the board.
     */
    {
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();

                // TODO: Clean up
                PiecePositionSystem piecePositionSystem = new PiecePositionSystem(canvas);
                GameBoardVisual emptyBoard = new GameBoardVisual(getContext(),
                        piecePositionSystem, canvas.getWidth(), canvas.getHeight());

                compositor.pushVisual(emptyBoard);
                drawPlayer(piecePositionSystem);

                compositor.composite(canvas);

                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    /**
     * TODO: Remove and replace with board initialization
     * <p/>
     * Set the number of players to draw initial board state.
     *
     * @param playerCount The number of player playing.
     */
    @Override
    public void setPlayerCount(int playerCount) {
       this.playerCount = playerCount;
    }

    // TODO: Remove --> For Testing
    private void drawPlayer(PiecePositionSystem pos) {
        if(playerCount == 2 || playerCount == 3 || playerCount == 4 || playerCount == 6) {
            // Draw Red Pieces
            int color = getResources().getColor(R.color.red);
            compositor.pushVisual(drawPiece(pos, 0, 0, color));
            compositor.pushVisual(drawPiece(pos, 1, 0, color));
            compositor.pushVisual(drawPiece(pos, 1, 1, color));
            compositor.pushVisual(drawPiece(pos, 2, 0, color));
            compositor.pushVisual(drawPiece(pos, 2, 1, color));
            compositor.pushVisual(drawPiece(pos, 2, 2, color));
            compositor.pushVisual(drawPiece(pos, 3, 0, color));
            compositor.pushVisual(drawPiece(pos, 3, 1, color));
            compositor.pushVisual(drawPiece(pos, 3, 2, color));
            compositor.pushVisual(drawPiece(pos, 3, 3, color));
        }

        if(playerCount == 2 || playerCount == 4 || playerCount == 6) {
            // Draw Green Pieces
            int color = getResources().getColor(R.color.green);
            compositor.pushVisual(drawPiece(pos, 13, 3, color));
            compositor.pushVisual(drawPiece(pos, 13, 2, color));
            compositor.pushVisual(drawPiece(pos, 13, 1, color));
            compositor.pushVisual(drawPiece(pos, 13, 0, color));
            compositor.pushVisual(drawPiece(pos, 14, 2, color));
            compositor.pushVisual(drawPiece(pos, 14, 1, color));
            compositor.pushVisual(drawPiece(pos, 14, 0, color));
            compositor.pushVisual(drawPiece(pos, 15, 1, color));
            compositor.pushVisual(drawPiece(pos, 15, 0, color));
            compositor.pushVisual(drawPiece(pos, 16, 0, color));
        }

        if(playerCount == 3 || playerCount == 6) {
            // Draw Yellow Pieces
            int color = getResources().getColor(R.color.yellow);
            compositor.pushVisual(drawPiece(pos, 12, 12, color));
            compositor.pushVisual(drawPiece(pos, 12, 11, color));
            compositor.pushVisual(drawPiece(pos, 12, 10, color));
            compositor.pushVisual(drawPiece(pos, 12, 9, color));
            compositor.pushVisual(drawPiece(pos, 11, 11, color));
            compositor.pushVisual(drawPiece(pos, 11, 10, color));
            compositor.pushVisual(drawPiece(pos, 11, 9, color));
            compositor.pushVisual(drawPiece(pos, 10, 10, color));
            compositor.pushVisual(drawPiece(pos, 10, 9, color));
            compositor.pushVisual(drawPiece(pos, 9, 9, color));
        }

        if(playerCount == 3 || playerCount == 4 || playerCount == 6) {
            // Draw Blue Pieces
            int color = getResources().getColor(R.color.blue);
            compositor.pushVisual(drawPiece(pos, 12, 3, color));
            compositor.pushVisual(drawPiece(pos, 12, 2, color));
            compositor.pushVisual(drawPiece(pos, 12, 1, color));
            compositor.pushVisual(drawPiece(pos, 12, 0, color));
            compositor.pushVisual(drawPiece(pos, 11, 2, color));
            compositor.pushVisual(drawPiece(pos, 11, 1, color));
            compositor.pushVisual(drawPiece(pos, 11, 0, color));
            compositor.pushVisual(drawPiece(pos, 10, 1, color));
            compositor.pushVisual(drawPiece(pos, 10, 0, color));
            compositor.pushVisual(drawPiece(pos, 9, 0, color));
        }

        if(playerCount == 6) {
            // Draw Purple Pieces
            int color = getResources().getColor(R.color.purple);
            compositor.pushVisual(drawPiece(pos, 4, 0, color));
            compositor.pushVisual(drawPiece(pos, 4, 1, color));
            compositor.pushVisual(drawPiece(pos, 4, 2, color));
            compositor.pushVisual(drawPiece(pos, 4, 3, color));
            compositor.pushVisual(drawPiece(pos, 5, 0, color));
            compositor.pushVisual(drawPiece(pos, 5, 1, color));
            compositor.pushVisual(drawPiece(pos, 5, 2, color));
            compositor.pushVisual(drawPiece(pos, 6, 0, color));
            compositor.pushVisual(drawPiece(pos, 6, 1, color));
            compositor.pushVisual(drawPiece(pos, 7, 0, color));
        }

        if(playerCount == 6 || playerCount == 4) {
            // Draw Orange Pieces
            int color = getResources().getColor(R.color.orange);
            compositor.pushVisual(drawPiece(pos, 4, 9, color));
            compositor.pushVisual(drawPiece(pos, 4, 10, color));
            compositor.pushVisual(drawPiece(pos, 4, 11, color));
            compositor.pushVisual(drawPiece(pos, 4, 12, color));
            compositor.pushVisual(drawPiece(pos, 5, 9, color));
            compositor.pushVisual(drawPiece(pos, 5, 10, color));
            compositor.pushVisual(drawPiece(pos, 5, 11, color));
            compositor.pushVisual(drawPiece(pos, 6, 9, color));
            compositor.pushVisual(drawPiece(pos, 6, 10, color));
            compositor.pushVisual(drawPiece(pos, 7, 9, color));
        }
    }

    // TODO: Utility function for display testing --> REMOVE
    private PieceVisual drawPiece(PiecePositionSystem pos, final int row, final int index, int color) {
        Position p = new Position() {
            @Override
            public int getRow() {
                return row;
            }

            @Override
            public int getIndex() {
                return index;
            }
        };

        return new PieceVisual(pos.get(p), color);
    }

    /**
     * Animate moving a piece on the board.
     *
     * @param piece      The piece to be moved.
     * @param to         The position to move to.
     * @param jumps      @Nullable, The piece that is jumped in this move if any.
     * @param onFinished Callback to fire when the animation has completed.
     */
    @Override
    public void movePiece(Piece piece, Position to, Piece jumps, FinishedMovingPieceHandler onFinished) {

    }

    /**
     * Highlight a position on the board so that the player
     * can see something important is occurring there.
     *
     * @param position The position to highlight.
     */
    @Override
    public void highlightPosition(Position position) {

    }

    /**
     * Rotate the board a certain number of <code>degrees</code>.
     *
     * @param degrees    The number of degrees to rotate the board.
     * @param onFinished Called when the rotation animation has completed.
     */
    @Override
    public void rotateBoard(int degrees, FinishedRotatingBoardHandler onFinished) {

    }

    /**
     * Do the animation for when a player wins.
     *
     * @param player The player that won.
     */
    @Override
    public void playerWon(Player player) {

    }

    /**
     * TODO: Possibly unneeded could be done with movePiece.
     * <p/>
     * Return a piece back to it's original position.
     *
     * @param piece            The piece to move.
     * @param originalPosition The original position to move the piece back to.
     * @param onFinished       Callback to fire when the animation has completed.
     */
    @Override
    public void cancelMove(Piece piece, Position originalPosition, FinishedMovingPieceHandler onFinished) {

    }

    /**
     * Show hints on the board for positions the player could potentially move to.
     *
     * @param positions The positions to draw the hints on.
     */
    @Override
    public void showHintPositions(Position[] positions) {

    }

    /**
     * TODO: Possibly put this in constructor
     * <p/>
     * Initialize the board with the current piece positions.
     *
     * @param pieces The pieces that represent the initial state of the board.
     */
    @Override
    public void initializeBoard(Piece[] pieces) {

    }

    /**
     * Add a handler to receive any events that occur on the board.
     *
     * @param handler The handler to register for the events.
     */
    public void addBoardEventsHandler(BoardUiEventsHandler handler) {
        // TODO
    }

    /**
     * Returns the largest possible side for a square that can be created on this device.
     *
     * @return The largest side length.
     */
    private int getLargestSquareSide() {
        return (this.getWidth() < this.getHeight() ? this.getWidth() : this.getHeight());
    }


}
