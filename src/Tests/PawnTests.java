package Tests;

import Exceptions.InvalidMovementException;
import Game.*;
import Pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Anne on 9/12/2014.
 */
public class PawnTests {

    @Test
    public void canMoveTwo() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];

        game.gameBoard.movePiece(pawn, 1, 3); //move two spaces forward
        assertEquals(pawn, game.gameBoard.boardArray[1][3]);
    }

    @Test(expected = InvalidMovementException.class)
    public void canNotMoveTwo() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];

        game.turn = 3;
        game.gameBoard.movePiece(pawn, 1, 5); //move two spaces forward
    }

    @Test
    public void canCapture() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];
        Piece enemyPawn = new Pawn(2, 2, game.player2);

        game.gameBoard.movePiece(pawn, 2, 2);
        assertEquals(pawn, game.gameBoard.boardArray[2][2]);
    }

    @Test(expected = InvalidMovementException.class)
    public void canNotCapture() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];

        game.gameBoard.movePiece(pawn, 2, 2);
    }

    @Test
    public void canMoveForward() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];

        game.gameBoard.movePiece(pawn, 1, 2);
        assertEquals(pawn, game.gameBoard.boardArray[1][2]);
    }

    @Test(expected = InvalidMovementException.class)
    public void canNotMoveForward() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];
        Piece pawn2 = new Pawn(1, 2, game.player2);
        game.gameBoard.boardArray[1][2] = pawn2;

        game.gameBoard.movePiece(pawn, 1, 2);
    }

    @Test(expected = InvalidMovementException.class)
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.player1);

        game.gameBoard.movePiece(pawn, 40,21);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.player1);

        game.gameBoard.movePiece(pawn, 4, 4);
    }
}
