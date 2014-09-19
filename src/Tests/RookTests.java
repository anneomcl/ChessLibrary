package Tests;

import Exceptions.InvalidMovementException;
import Game.*;
import Pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Anne on 9/12/2014.
 */
public class RookTests {

    @Test
     public void verticalMovementUp() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, 4, 5); //up
        assertEquals(rook, game.gameBoard.boardArray[4][5]);
    }


    @Test
    public void verticalMovementDown() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, 4, 3); //down
        assertEquals(rook, game.gameBoard.boardArray[4][3]);
    }

    @Test
    public void horizontalMovementLeft() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, 3, 4); //left
        assertEquals(rook, game.gameBoard.boardArray[3][4]);
    }

    @Test
    public void horizontalMovementRight() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, 5, 4); //right
        assertEquals(rook, game.gameBoard.boardArray[5][4]);
    }


    @Test(expected = InvalidMovementException.class)
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, -1, -1);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];

        game.gameBoard.movePiece(rook, 0, 3);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);

        game.gameBoard.movePiece(rook, 4, 4);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];

        game.gameBoard.movePiece(rook, 0, 1);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];
        game.gameBoard.boardArray[0][1].player = game.player2;

        game.gameBoard.movePiece(rook, 0, 1);
        assertEquals(rook, game.gameBoard.boardArray[0][1]);
    }
}
