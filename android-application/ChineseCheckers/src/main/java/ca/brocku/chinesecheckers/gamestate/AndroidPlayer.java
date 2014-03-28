package ca.brocku.chinesecheckers.gamestate;

import java.io.Serializable;
import android.os.Parcelable;

import javajar.gamestate.MovePath;
import javajar.gamestate.Player;

import ca.brocku.chinesecheckers.gameboard.AndroidReadOnlyGameBoard;

/**
 * Represent a player on the board.
 *
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 2/1/2014
 */
public abstract class AndroidPlayer extends Player implements Parcelable {


    public AndroidPlayer(PlayerColor playerColor) {
        super(playerColor);
    }

    public void signalMove(MovePath movePath) {
    }

}
