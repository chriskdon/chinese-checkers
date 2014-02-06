package ca.brocku.chinesecheckers.uiengine.utils;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.uiengine.PieceInformation;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class GameBoardPositionsCalculator {
    public PieceInformation[] calculatePiecePositions(int width, int height) {
        PieceInformation[] positions = new PieceInformation[GameBoard.TOTAL_PIECE_COUNT];
        final int rows = GameBoard.ROW_POSITION_COUNT.length;

        // Settings
        int shrink = 5;
        int scale = 0;

        // Radius without shrink
        int squareSide = (width < height ? width : height);
        int radiusArea = (squareSide/(13+scale))/2;

        // Calculate Gap
        double length = radiusArea*2;
        double a = - ((length/2)*(length/2));
        double h = length*length;
        double gap = Math.sqrt(a + h);

        // Center the board
        int topOffset = (int)((height - (int)(gap * rows))/1.5);

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
