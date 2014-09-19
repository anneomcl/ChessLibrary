package Exceptions;

/**
 * Created by Anne on 9/17/2014.
 */
public class InvalidMovementException extends Exception {

    /**
     * This is an exception for an invalid piece movement. Called when movePiece() fails.
     */
    public InvalidMovementException(){
        System.out.println("Invalid movement!");
    }
}
