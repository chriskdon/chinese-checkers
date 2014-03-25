package javajar.gameboard;

/**
 * Created by zz on 2/23/14.
 */
public class IllegalMoveException extends RuntimeException
{
    //Parameterless Constructor
    public IllegalMoveException() {}

    //Constructor that accepts a message
    public IllegalMoveException(String message)
    {
        super(message);
    }
}
