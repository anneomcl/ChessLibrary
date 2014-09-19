package Tests;
import Exceptions.InvalidMovementException;
import Game.*;
import Pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anne on 9/12/2014.
 */
public class KnightTests {

    @Test
    public void knightMovementUp2Left1() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.boardArray[3][6] = null;
        game.gameBoard.movePiece(knight, 3, 6); //up 2, left 1
        assertEquals(knight, game.gameBoard.boardArray[3][6]);
    }

    @Test
    public void knightMovementDown2Right1() throws Exception
    {
        Game game = new Game();
        game.gameBoard.boardArray[3][6] = null;
        Piece knight = new Knight(3,6, game.player1);

        game.gameBoard.movePiece(knight, 4, 4); //down 2, right 1
        assertEquals(knight, game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void knightMovementUp2Right1() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.boardArray[5][6] = null;
        game.gameBoard.movePiece(knight, 5, 6); //up 2, right 1
        assertEquals(knight, game.gameBoard.boardArray[5][6]);
    }

    @Test
    public void knightMovementDown2Left1() throws Exception
    {
        Game game = new Game();
        game.gameBoard.boardArray[5][6] = null;
        Piece knight = new Knight(5, 6, game.player1);

        game.gameBoard.movePiece(knight, 4, 4); //down 2, left 1
        assertEquals(knight, game.gameBoard.boardArray[4][4]);
    }

    @Test
    public void knightMovementDown1Right2() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 6, 3); //down 1, right2
        assertEquals(knight, game.gameBoard.boardArray[6][3]);
    }

    @Test
    public void knightMovementDown1Left2() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 2, 3); //down 1, left 2
        assertEquals(knight, game.gameBoard.boardArray[2][3]);
    }

    @Test
    public void knightMovementUp1Left2() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 2, 5); //up 1, left 2
        assertEquals(knight, game.gameBoard.boardArray[2][5]);
    }

    @Test
    public void knightMovementUp1Right2() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 6, 5); //up 1, right 2
        assertEquals(knight, game.gameBoard.boardArray[6][5]);
    }

    @Test(expected = InvalidMovementException.class)
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 40,21);
    }

    @Test
    public void validLeaping() throws Exception
    {
        Game game = new Game();
        Piece knight = game.gameBoard.boardArray[1][0];

        game.gameBoard.movePiece(knight, 2, 2);
        assertEquals(knight, game.gameBoard.boardArray[2][2]);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);

        game.gameBoard.movePiece(knight, 4, 4);
    }

    @Test(expected = InvalidMovementException.class)
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece knight = game.gameBoard.boardArray[1][0];
        Piece pawn = game.gameBoard.boardArray[2][1];
        game.gameBoard.movePiece(pawn, 2, 2);
        game.gameBoard.movePiece(knight, 2, 2);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece knight = game.gameBoard.boardArray[1][0];
        Piece pawn = game.gameBoard.boardArray[3][1];
        pawn.player = game.player2;
        game.gameBoard.movePiece(knight, 3, 1);

        assertEquals(knight, game.gameBoard.boardArray[3][1]);
    }
}
