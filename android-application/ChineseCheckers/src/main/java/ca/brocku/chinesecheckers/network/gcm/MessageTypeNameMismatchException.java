package ca.brocku.chinesecheckers.network.gcm;

/**
 * Author: Chris Kellendonk
 * Student #: 4810800
 * Date: 3/11/2014
 */
public class MessageTypeNameMismatchException extends RuntimeException {
    public MessageTypeNameMismatchException() {
        super("The type of the message does not match the expected");
    }
}
