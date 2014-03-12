package ca.brocku.chinesecheckers.network.gcm;

import ca.brocku.chinesecheckers.uiengine.exceptions.NoPieceAtPositionException;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class NoMessageTypeException extends RuntimeException {
    public NoMessageTypeException() {
        super("No message matches the type name specified.");
    }
}
