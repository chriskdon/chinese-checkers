package ca.brocku.chinesecheckers.uiengine.visuals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ca.brocku.chinesecheckers.R;
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
    PiecePositionSystem piecePositionSystem;

    public GameBoardVisual(Context context, PiecePositionSystem piecePositionSystem,
                           float w, float h) {
        super(0, 0, w, h);

        this.piecePositionSystem = piecePositionSystem;

        final int color = context.getResources().getColor(R.color.gray);

        // Add Pieces
        for(PieceDrawingDetails pos : piecePositionSystem.getPositionDetails()) {
            addChild(new PieceVisual(pos, color));
        }
    }
}
