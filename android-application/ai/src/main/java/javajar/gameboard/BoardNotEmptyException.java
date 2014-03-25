package javajar.gameboard;

/**
 * Created by zz on 2/23/14.
 */
class BoardNotEmptyException extends RuntimeException {
        //Parameterless Constructor
        public BoardNotEmptyException() {}

        //Constructor that accepts a message
        public BoardNotEmptyException(String message)
        {
            super(message);
        }

}
