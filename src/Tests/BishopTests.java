package Tests;
import Exceptions.InvalidMovementException;
import Game.*;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Anne on 9/12/2014.
 */

public class BishopTests {

    @Test
    public void downLeftMovement() throws Exception {
        Game game = new Game();
        Piece bishop = new Bishop(4, 4, game.player2);

        game.gameBoard.movePiece(bishop, 3, 3); //down-left
        assertEquals(bishop, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void upRightMovement() throws Exception {
        Game game = new Game();
        Piece bishop = new Bishop(3, 3, game.player2);

        game.gameBoard.movePiece(bishop, 4, 4); //up-right
        assertEquals(bishop, game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void downRightMovement() throws Exception {
        Game game = new Game();
        Piece bishop = new Bishop(4, 4, game.player2);

        game.gameBoard.movePiece(bishop, 5, 3); //down-right
        assertEquals(bishop, game.gameBoard.boardArray[5][3]);
    }

    @Test
    public void upLeftMovement() throws Exception
    {
        Game game = new Game();
        Piece bishop = new Bishop(5, 3, game.player2);

        game.gameBoard.movePiece(bishop, 4, 4); //up-left
        assertEquals(bishop, game.gameBoard.boardArray[4][4]);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 4, 2);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];
        game.gameBoard.boardArray[3][1].player = game.player2;

        game.gameBoard.movePiece(bishop, 3, 1);
        assertEquals(bishop, game.gameBoard.boardArray[3][1]);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 3, 1);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 2, 0);
    }

    @Test(expected = InvalidMovementException.class)
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 40,21);
    }
}
