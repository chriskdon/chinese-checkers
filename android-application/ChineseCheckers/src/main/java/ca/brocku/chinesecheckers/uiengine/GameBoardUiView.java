package ca.brocku.chinesecheckers.uiengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.CcGameBoard;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gamestate.Move;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedMovingPieceHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedRotatingBoardHandler;
import ca.brocku.chinesecheckers.uiengine.visuals.GameBoardVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.HintVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.PieceVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.Visual;

/**
 * The ui for the game board components and all the actions you can do
 * when interacting with the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public class GameBoardUiView extends SurfaceView implements BoardUiEngine, SurfaceHolder.Callback {
    private GameBoardVisual gameBoard;                          // Root visual element
    private SurfaceHolder surfaceHolder;                        // Surface with canvas
    private PiecePositionSystem piecePositionSystem;            // Positioning of pieces
    private Map<Position, PieceVisual> pieces;                  // Pieces
    private BoardUiEventsHandler boardUiEventsHandler;          // Board events handler
    private Collection<Visual> hintPositions;                   // Positions of the currently
    private int hintColor;                                      // Displayed hint color.
    private float hintStrokeWidth;                              // Width of the hint stroke.
    private Piece[] initialPieces;                              // Initial setup of  board piece

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
     * Called when the surface has been created.
     * @param holder
     */
    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        GameBoardUiView.this.surfaceHolder = holder;

        // Get the width and height of the canvas
        int width, height; {
            Canvas canvas = holder.lockCanvas();
            width = canvas.getWidth();
            height = canvas.getHeight();
            holder.unlockCanvasAndPost(canvas);
        }

        piecePositionSystem = new PiecePositionSystem(width, height);
        gameBoard = new GameBoardVisual(getContext(),
                piecePositionSystem,
                width, height);

        gameBoard.setPositionTouchedHandler(new GameBoardVisual.PositionTouchedHandler() {
            @Override
            public void onPositionTouched(Position position) {
                if(boardUiEventsHandler != null) {
                    boardUiEventsHandler.positionTouched(position);
                }
            }
        });

        // Load initial board state
        if(initialPieces != null) {
            loadBoardPieces(initialPieces);
            initialPieces = null;   // No longer need these
        }

        redraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * Register the handler to start drawing the board.
     */
    {
        this.hintColor = getResources().getColor(R.color.hint_color);
        this.hintStrokeWidth = getResources().getInteger(R.integer.hint_stroke_width);

        this.pieces = new HashMap<Position, PieceVisual>();

        getHolder().addCallback(this);
    }

    /**
     * Occurs when a user touches the surface.
     * @param event The details of where the user touched.
     * @return True...the event was handled.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(gameBoard != null) {
                gameBoard.sendTouchEvent(event);
            }
        }

        return true;
    }

    /**
     * Animate moving a piece on the board.
     *
     * This does not guarantee a check to see if the user is trying to place pieces on top of
     * each other. All it is doing is moving pieces around. That should be checked elsewhere.
     *
     * @param from       The Piece to move.
     * @param to         The position to move to.
     * @param onFinished Callback to fire when the animation has completed.
     * @return           Returns true if a piece could be successfully moved.
     */
    @Override
    public boolean movePiece(Piece from, Position to, FinishedMovingPieceHandler onFinished) {
        // Make sure it is not modified before we get the start position
        final Position fromPosition;
        synchronized (from) {
            fromPosition = from.getPosition();
        }

        PieceVisual p = pieces.remove(fromPosition);
        if(p != null) {
            p.setPieceDrawingDetails(piecePositionSystem.get(to));
            redraw();

            if(onFinished != null) {
                onFinished.onPieceFinishedMovingAnimation(from, fromPosition);
            }

            if(pieces.get(to) == null) { // Make sure we aren't overwriting piece.
                pieces.put(to, p);
            } else {
                throw new IllegalStateException("There is already a piece at that position.");
            }

            return true;
        }

        // TODO: Handle this
        Log.d("MOVE", "Tried to move a piece that wasn't there.");

        return  false;
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
        // TODO
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
     * @param move       The piece and path taken.
     * @param onFinished Callback to fire when the animation has completed.
     */
    @Override
    public void cancelMove(Move move, FinishedMovingPieceHandler onFinished) {

    }

    /**
     * Show hints on the board for positions the player could potentially move to.
     *
     * @param positions The positions to draw the hints on.
     */
    @Override
    public void showHintPositions(Position[] positions) {
        if(this.hintPositions != null) {
            gameBoard.removeChildren(hintPositions);
        }

        if(positions != null) {
            this.hintPositions = new HashSet<Visual>(positions.length);

            // Add Hint
            for(int i = 0; i < positions.length; i++) {
                Position p = positions[i];
                if(p != null) { // TODO: Remove this should be fixed by @goddamnpete
                    Visual v = new HintVisual(piecePositionSystem.get(p), hintColor, hintStrokeWidth);

                    this.hintPositions.add(v);
                    gameBoard.addChild(v);
                }
            }

            redraw();
        }
    }

    /**
     * Initialize the board with the current piece positions.
     *
     * @param pieces The pieces that represent the initial state of the board.
     */
    @Override
    public void initializeBoard(Piece[] pieces) {
        initialPieces = pieces;
    }

    /**
     * Load all the pieces onto the board.
     * @param pieces
     */
    private void loadBoardPieces(Piece[] pieces) {
        if(piecePositionSystem == null) {
            throw new IllegalStateException("The Piece Position System is not ready.");
        }

        for(Piece p : pieces) {
            PieceVisual pv = new PieceVisual(piecePositionSystem.get(p.getPosition()),
                                             getPlayerColor(p.getPlayerNumber()));

            this.pieces.put(p.getPosition(), pv);

            gameBoard.addChild(pv);
        }
    }

    /**
     * Get the color associated with a player.
     * @param playerNumber  The number of the player.
     * @return  The color value.
     */
    private int getPlayerColor(int playerNumber) {
        switch(playerNumber) {
            case 1: return getResources().getColor(R.color.red);
            case 2: return getResources().getColor(R.color.purple);
            case 3: return getResources().getColor(R.color.blue);
            case 4: return getResources().getColor(R.color.green);
            case 5: return getResources().getColor(R.color.yellow);
            case 6: return getResources().getColor(R.color.orange);
        }

        throw new IllegalArgumentException("Player Number must be between 1 and 6");
    }

    /**
     * Add a handler to receive any events that occur on the board.
     *
     * @param handler The handler to register for the events.
     */
    @Override
    public void setBoardEventsHandler(BoardUiEventsHandler handler) {
        this.boardUiEventsHandler = handler;
    }

    /**
     * Redraw the game board.
     */
    private void redraw() {
        if(this.surfaceHolder == null) {
            throw new IllegalStateException("The surface is not ready.");
        }

        Canvas c = this.surfaceHolder.lockCanvas();

        c.drawColor(getResources().getColor(R.color.black)); // Clear canvas

        gameBoard.draw(c);

        this.surfaceHolder.unlockCanvasAndPost(c);
    }

    /**
     * Fires when the surface holder is ready
     */
    public interface SurfaceHolderReadyHandler {
        /**
         * Fired when the surface holder is ready.
         * @param holder
         */
        public void onSurfaceHolderReady(SurfaceHolder holder);
    }
}
