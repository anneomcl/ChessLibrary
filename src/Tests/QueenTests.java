package Tests;

import Exceptions.InvalidMovementException;
import Game.*;
import Pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anne on 9/12/2014.
 */
public class QueenTests {

    @Test
    public void verticalMovementUp() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 4, 5); //up
        assertEquals(queen, game.gameBoard.boardArray[4][5]);
    }

    @Test
    public void verticalMovementDown() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 4, 3); //down
        assertEquals(queen, game.gameBoard.boardArray[4][3]);
    }

    @Test
    public void horizontalMovementLeft() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 3, 4); //left
        assertEquals(queen, game.gameBoard.boardArray[3][4]);
    }

    @Test
    public void horizontalMovementRight() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 5, 4); //right
        assertEquals(queen, game.gameBoard.boardArray[5][4]);
    }

    @Test
    public void downLeftMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 3, 3); //down-left
        assertEquals(queen, game.gameBoard.boardArray[3][3]);
    }

    @Test
    public void upRightMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(3, 3, game.player2);

        game.gameBoard.movePiece(queen, 4, 4); //up-right
        assertEquals(queen, game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void downRightMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 5, 3); //down-right
        assertEquals(queen, game.gameBoard.boardArray[5][3]);
    }

    @Test
    public void upLeftMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(5, 3, game.player2);

        game.gameBoard.movePiece(queen, 4, 4); //up-left
        assertEquals(queen, game.gameBoard.boardArray[4][4]);
    }

    @Test(expected = InvalidMovementException.class)
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, -1, -1);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece queen = game.gameBoard.boardArray[3][0];

        game.gameBoard.movePiece(queen, 3, 2);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player2);

        game.gameBoard.movePiece(queen, 4, 4);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece queen = game.gameBoard.boardArray[3][0];

        game.gameBoard.movePiece(queen, 3, 1);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece queen = game.gameBoard.boardArray[3][0];
        game.gameBoard.boardArray[3][1].player = game.player2;

        game.gameBoard.movePiece(queen, 3, 1);
    }
}
