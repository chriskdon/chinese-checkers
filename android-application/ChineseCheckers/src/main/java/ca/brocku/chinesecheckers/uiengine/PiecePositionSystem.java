package ca.brocku.chinesecheckers.uiengine;

import android.graphics.Canvas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ca.brocku.chinesecheckers.gameboard.AndroidGameBoard;
import ca.brocku.chinesecheckers.gameboard.AndroidPosition;

/**
 * Handles all the location of items drawn for the game board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class PiecePositionSystem implements Serializable {
    private transient Map<AndroidPosition, PieceDrawingDetails> positionDetails; // AndroidPosition mapping

    /**
     * Create the instance, and calculate all the board positions.
     *
     * @param c The canvas that this system is intended to be drawn with.
     */
    public PiecePositionSystem(Canvas c) {
        this(c.getWidth(), c.getHeight());
    }

    /**
     * Create the instance, and calculate all the board positions.
     *
     * @param width     The width of the canvas that this system is for.
     * @param height    The height of the canvas that this system is for.
     */
    public PiecePositionSystem(int width, int height) {
        positionDetails = new HashMap<AndroidPosition, PieceDrawingDetails>(AndroidGameBoard.TOTAL_PIECE_COUNT);

        mapIntoPositionDetails(positionDetails, width, height);
    }

    /**
     * @return  Return an unmodifiable position system mapping.
     */
    public Map<AndroidPosition, PieceDrawingDetails> getPositionDetailsMapping() {
        return Collections.unmodifiableMap(positionDetails);
    }

    /**
     * @return  Return an unmodifiable collection of just the drawing position details.
     */
    public Collection<PieceDrawingDetails> getPositionDetails() {
        return Collections.unmodifiableCollection(positionDetails.values());
    }

    /**
     * Find the drawing details that match a board position.
     *
     * @param position  The row-index position on the board.
     * @return          The details for that position or null if there was no match.
     */
    public PieceDrawingDetails get(AndroidPosition position) {
        return positionDetails.get(position);
    }

    /**
     * Calculate how <code>AndroidPosition</code> (row-index) coordinates relate to actual
     * pixel positions on the screen.
     *
     * @param dest      The destination map to fill with positions.
     * @param width     The width of the drawing area.
     * @param height    The height tof the drawing area.
     * @return          The position information
     */
    private void mapIntoPositionDetails(Map<AndroidPosition, PieceDrawingDetails> dest, int width, int height) {
        final int rows = AndroidGameBoard.ROW_POSITION_COUNT.length;

        // Settings
        int shrink = 5;     // In Display Pixels
        int scaleDown = 1;  // In empty space units (how many EXTRA spaces per row)

        // Radius without shrink
        int squareSide = (width < height ? width : height);
        int radiusArea = (squareSide/(AndroidGameBoard.MAXIMUM_PIECES_PER_ROW+scaleDown))/2;

        // Calculate Gap
        double length = radiusArea*2;
        double a = - ((length/2)*(length/2));
        double h = length*length;
        double gap = Math.sqrt(a + h);

        // Center the board
        int topOffset = (int)((height - (gap*rows))/2) + radiusArea - shrink;

        // Calculate positions
        for(int row = 0; row < rows; row++) {
            int indexCount = AndroidGameBoard.ROW_POSITION_COUNT[row];

            int x = radiusArea + (squareSide/2) - (radiusArea*indexCount);
            int y = topOffset + (int)(gap*row);
            for(int i = 0; i < indexCount; i++) {
                // These need to be final for our anonymous class
                final int posRow = row;
                final int posIndex = i;

                final AndroidPosition position = new AndroidPosition(posRow, posIndex);

                // Store in the hash map
                dest.put(position, new PieceDrawingDetails(position, x, y, radiusArea - shrink));

                x += radiusArea*2;
            }
        }
    }
}
