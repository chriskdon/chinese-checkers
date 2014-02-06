package ca.brocku.chinesecheckers.uiengine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import ca.brocku.chinesecheckers.gameboard.GameBoard;

/**
 * Handles all the location of items drawn for the game board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class BoardPositionSystem {
    public PieceInformation[] calculatePiecePositions(int width, int height) {
        PieceInformation[] positions = new PieceInformation[GameBoard.TOTAL_PIECE_COUNT];
        final int rows = GameBoard.ROW_POSITION_COUNT.length;

        // Settings
        int shrink = 5;     // In Display Pixels
        int scaleDown = 1;  // In empty space units (how many EXTRA spaces per row)

        // Radius without shrink
        int squareSide = (width < height ? width : height);
        int radiusArea = (squareSide/(GameBoard.MAXIMUM_PIECES_PER_ROW+scaleDown))/2;

        // Calculate Gap
        double length = radiusArea*2;
        double a = - ((length/2)*(length/2));
        double h = length*length;
        double gap = Math.sqrt(a + h);

        // Center the board
        int topOffset = (int)((height - (gap*rows))/2) + radiusArea - shrink;

        // Calculate positions
        int pos = 0;
        for(int row = 0; row < rows; row++) {
            int indexCount = GameBoard.ROW_POSITION_COUNT[row];

            int x = radiusArea + (squareSide/2) - (radiusArea*indexCount);
            int y = topOffset + (int)(gap*row);
            for(int i = 0; i < indexCount; i++) {
                positions[pos++] = new PieceInformation(x, y, row, i, radiusArea - shrink);

                x += radiusArea*2;
            }
        }

        return  positions;
    }
}
