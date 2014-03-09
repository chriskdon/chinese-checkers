package ca.brocku.chinesecheckers.uiengine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.CcGameBoard;
import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.gameboard.Piece;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.gameboard.ReadOnlyGameBoard;
import ca.brocku.chinesecheckers.gamestate.MovePath;
import ca.brocku.chinesecheckers.gamestate.Player;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedMovingPieceHandler;
import ca.brocku.chinesecheckers.uiengine.handlers.FinishedRotatingBoardHandler;
import ca.brocku.chinesecheckers.uiengine.visuals.GameBoardVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.HintVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.PieceVisual;
import ca.brocku.chinesecheckers.uiengine.visuals.Visual;
import static ca.brocku.chinesecheckers.uiengine.PlayerColorManager.*;

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
    private PiecePositionSystem piecePositionSystem;            // Positioning of pieces
    private Map<Position, Visual> pieces;                       // Pieces
    private Collection<Visual> hintPositions;                   // Positions of the currently
    private int hintColor;                                      // Displayed hint color.
    private Piece currentHighlightedPiece;                      // Currently highlighted piece.
    private float canvasWidth, canvasHeight;                      // Width/Height of the canvas
    private float currentRotation;                              // Degrees canvas has rotated

    private transient BoardUiEventsHandler boardUiEventsHandler;          // Board events handler
    private transient SurfaceHolder surfaceHolder;                        // Surface with canvas

    private ReadOnlyGameBoard init;

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
            canvasWidth = ((float)canvas.getWidth());//-0.0f;
            canvasHeight = ((float)canvas.getHeight());//-1.75f;
            holder.unlockCanvasAndPost(canvas);
        }
        currentRotation = 0;

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

        if(init != null) {
            // Draw light home pieces
            GameBoard temp = init.getDeepCopy();
            temp.reset();
            for(Piece p : temp.getAllPieces()) {
                gameBoard.addChild(new PieceVisual(piecePositionSystem.get(p.getPosition()),
                        getPlayerColor(getResources(),
                                Player.getPlayerColor(p.getPlayerNumber()), ColorSate.VERY_DARK)));
            }

            drawBoard(init);
            init = null;
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

        this.pieces = new HashMap<Position, Visual>();

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
     * @param board@return Returns true if a piece could be successfully moved.
     */
    @Override
    public void drawBoard(ReadOnlyGameBoard board) {
        gameBoard.removeChildren(pieces.values());

        for(Piece p : board.getAllPieces()) {
            PieceVisual pv = new PieceVisual(piecePositionSystem.get(p.getPosition()), getPlayerColor(getResources(), Player.getPlayerColor(p.getPlayerNumber()), ColorSate.NORMAL));
            this.pieces.put(p.getPosition(), pv);
            gameBoard.addChild(pv);
        }
    }

    /**
     * Highlight a piece on the board.
     *
     * @param piece The position to highlight. Or NULL to clear any highlighted position
     */
    @Override
    public void highlightPiece(Piece piece) {
        if(currentHighlightedPiece != null) {
            ((PieceVisual)pieces.get(currentHighlightedPiece.getPosition()))
                    .setColor(getPlayerColor(getResources(),Player.getPlayerColor(currentHighlightedPiece.getPlayerNumber()), ColorSate.NORMAL));
        }

        if(piece != null) {
            PieceVisual pv = (PieceVisual)pieces.get(piece.getPosition());
            pv.setColor(getPlayerColor(getResources(), Player.getPlayerColor(piece.getPlayerNumber()),
                                       ColorSate.LIGHT));
        }

        currentHighlightedPiece = piece;
        redraw();
    }

    /**
     * Rotate the board a certain number of <code>degrees</code>.
     *
     * @param degrees    The number of degrees to rotate the board.
     * @param onFinished Called when the rotation animation has completed.
     */
    @Override
    public void rotateBoard(float degrees, FinishedRotatingBoardHandler onFinished) {
        redraw();

        currentRotation = (currentRotation + degrees)%360;

        // Log.e("ROTATION", String.valueOf(currentRotation));

        Canvas c = this.surfaceHolder.lockCanvas();

        //TODO: make rotation more precise; remove clear canvas below to see issue
        //TODO Cont'd: ISSUE IS PROB WITH THE PIECES NOT BEING DRAWN IN THE CENTER OF THE CANVAS
        c.drawColor(getResources().getColor(R.color.black)); // Clear canvas

        c.rotate(currentRotation, canvasWidth/2.0f, canvasHeight /2.0f); // Clear canvas

        gameBoard.draw(c);

        this.surfaceHolder.unlockCanvasAndPost(c);
    }

 /**
     * TODO: Possibly put this in constructor
     * <p/>
     * Initialize the board with the current piece positions.
     *
     * @param board
     */
    @Override
    public void initializeBoard(ReadOnlyGameBoard board) {
        init = board;
    }

    /**
     * Do the animation for when a player wins.
     *
     * @param player The player that won.
     */
    @Override
    public void playerWon(Player player) {
        // TODO
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
            for(Position p : positions) {
                Visual v = new HintVisual(piecePositionSystem.get(p), this.hintColor);

                this.hintPositions.add(v);
                gameBoard.addChild(v);
            }
        }

        redraw();
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
                                             getPlayerColor(getResources(), Player.getPlayerColor(p.getPlayerNumber()), ColorSate.NORMAL));

            this.pieces.put(p.getPosition(), pv);

            gameBoard.addChild(pv);
        }
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
