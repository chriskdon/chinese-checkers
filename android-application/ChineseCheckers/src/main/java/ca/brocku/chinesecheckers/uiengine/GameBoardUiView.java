package ca.brocku.chinesecheckers.uiengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.elements.EmptyGameBoardElement;
import ca.brocku.chinesecheckers.uiengine.handlers.BoardUiEventsHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedMovingPieceHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedRotatingBoardHandler;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public class GameBoardUiView extends SurfaceView implements BoardUiDrawingEngine {
    EmptyGameBoardElement emptyBoard = new EmptyGameBoardElement(this.getContext());


    /**
     * Instance Block -> Added to all constructors
     */
    {
        setWillNotDraw(false); // Allow invalidate() to work on surface for onDraw().
    }

    public GameBoardUiView(Context context) {
        super(context);
    }

    public GameBoardUiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameBoardUiView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int x= 0;
    long start = 0;
    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        emptyBoard.draw(canvas);
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
