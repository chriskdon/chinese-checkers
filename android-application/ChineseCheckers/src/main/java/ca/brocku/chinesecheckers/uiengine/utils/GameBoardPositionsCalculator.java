package ca.brocku.chinesecheckers.uiengine.utils;

import ca.brocku.chinesecheckers.gameboard.GameBoard;
import ca.brocku.chinesecheckers.uiengine.PieceInformation;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/3/2014
 */
public class GameBoardPositionsCalculator {
    public PieceInformation[] calculatePiecePositions(int width) {
        int numPositions = 0;
        for(int count : GameBoard.ROW_POSITION_COUNT) {
            numPositions += count;
        }

        int spacing = 2;
        int scale = 5;

        PieceInformation[] positions = new PieceInformation[numPositions];

        int radius = (width/(13+scale))/2;
        int topOffset = (width - (radius*GameBoard.ROW_POSITION_COUNT.length))/3;

        int pos = 0;
        for(int row = 0; row < GameBoard.ROW_POSITION_COUNT.length; row++) {
            int indexCount = GameBoard.ROW_POSITION_COUNT[row];

            int x = radius + (width/2) - (radius*indexCount);
            int y = topOffset + (radius*row*2) + radius;
            for(int i = 0; i < indexCount; i++) {
                positions[pos++] = new PieceInformation(x, y, row, i, radius - spacing);

                x += radius*2;
            }
        }

        return  positions;
    }
}
