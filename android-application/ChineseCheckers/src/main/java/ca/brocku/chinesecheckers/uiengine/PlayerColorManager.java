package ca.brocku.chinesecheckers.uiengine;

import android.content.res.Resources;

import ca.brocku.chinesecheckers.R;
import ca.brocku.chinesecheckers.gamestate.Player;

/**
 * Handle the colors associated with players.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/27/2014
 */
public class PlayerColorManager {
    /**
     * States that a piece color can be in.
     */
    public static enum ColorSate {
        NORMAL,
        DARK,
        LIGHT
    };

    /**
     * Get the color associated with a player.
     *
     * @param resources     Access to android resources.
     * @param playerColor   The color of the player.
     * @param state         The state color type to get.
     * @return  The color value.
     */
    public static int getPlayerColor(Resources resources, Player.PlayerColor playerColor, ColorSate state) {
        switch (state) {
            case NORMAL: {
                switch(playerColor) {
                    case RED: return resources.getColor(R.color.red);
                    case PURPLE: return resources.getColor(R.color.purple);
                    case BLUE: return resources.getColor(R.color.blue);
                    case GREEN: return resources.getColor(R.color.green);
                    case YELLOW: return resources.getColor(R.color.yellow);
                    case ORANGE: return resources.getColor(R.color.orange);
                }
            }

            case DARK: {
                switch(playerColor) {
                    case RED: return resources.getColor(R.color.dark_red);
                    case PURPLE: return resources.getColor(R.color.dark_purple);
                    case BLUE: return resources.getColor(R.color.dark_blue);
                    case GREEN: return resources.getColor(R.color.dark_green);
                    case YELLOW: return resources.getColor(R.color.dark_yellow);
                    case ORANGE: return resources.getColor(R.color.dark_orange);
                }
            }

            case LIGHT: {
                switch(playerColor) {
                    case RED: return resources.getColor(R.color.light_red);
                    case PURPLE: return resources.getColor(R.color.light_purple);
                    case BLUE: return resources.getColor(R.color.light_blue);
                    case GREEN: return resources.getColor(R.color.light_green);
                    case YELLOW: return resources.getColor(R.color.light_yellow);
                    case ORANGE: return resources.getColor(R.color.light_orange);
                }
            }
        }

        throw new IllegalArgumentException("Player Number must be between 1 and 6");
    }
}
