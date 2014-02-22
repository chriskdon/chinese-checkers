package ca.brocku.chinesecheckers.uiengine.visuals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gameboard.Position;
import ca.brocku.chinesecheckers.uiengine.PieceDrawingDetails;
import ca.brocku.chinesecheckers.uiengine.PiecePositionSystem;
import ca.brocku.chinesecheckers.uiengine.PixelPosition;

/**
 * Game board with blank positions
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/2/2014
 */
public class GameBoardVisual extends Visual {
    protected PiecePositionSystem piecePositionSystem;
    private TouchEventHandler pieceTouchEventHandler;
    private PositionTouchedHandler positionTouchedHandler;

    public GameBoardVisual(Context context, PiecePositionSystem piecePositionSystem,
                           float w, float h) {
        super(0, 0, w, h);

        this.piecePositionSystem = piecePositionSystem;

        this.pieceTouchEventHandler = new TouchEventHandler() {
            @Override
            public boolean onTouch(Visual v, MotionEvent e) {
                if(GameBoardVisual.this.positionTouchedHandler != null) {
                    Position pos = ((PieceVisual) v).getPositionOnBoard();
                    GameBoardVisual.this.positionTouchedHandler.onPositionTouched(pos);
                    return false;
                }

                return false;
            }
        };

        final int color = context.getResources().getColor(R.color.gray);

        // Add Pieces
        for(PieceDrawingDetails pos : piecePositionSystem.getPositionDetails()) {
            PieceVisual pv = new PieceVisual("BLANK", pos, color);
            pv.setTouchEventHandler(this.pieceTouchEventHandler);
            addChild(pv);
        }
    }

    public void setPositionTouchedHandler(PositionTouchedHandler handler) {
        this.positionTouchedHandler = handler;
    }

    /**
     * Occurs when a position on the board is touched.
     */
    public static interface PositionTouchedHandler {
        public void onPositionTouched(Position position);
    }
}
