package Tests;
import Game.*;
import Pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anne on 9/12/2014.
 */
public class KingTests {

    @Test
    public void allMovement() throws Exception
    {
        Game game = new Game();
        for(int i = 0; i < 8; i++)
        {
            game.gameBoard.boardArray[i][7] = null;
            game.gameBoard.boardArray[i][6] = null;
        }

        game.gameBoard.blackPieces.removeAllElements();

        Piece king = new King(4, 4, game.player2);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 3, 3); //down-left

        game.gameBoard.movePiece(king, 4, 4); //up-right

        game.gameBoard.movePiece(king, 5, 3); //up-left

        game.gameBoard.movePiece(king, 4, 4); //down-right

        game.gameBoard.movePiece(king, 4, 5); //up

        game.gameBoard.movePiece(king, 4, 4); //down

        game.gameBoard.movePiece(king, 3, 4); //left

        game.gameBoard.movePiece(king, 4, 4); //right
    }

    @Test
    public void cantMoveTwo() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player2);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 2, 4); //down-left
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player2);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 40,21);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player2);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 4, 4);
    }

    @Test
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece king = game.gameBoard.boardArray[4][0];

        game.gameBoard.movePiece(king, 4, 1);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece king = game.gameBoard.boardArray[4][0];
        Piece pawn = new Pawn(4, 1, game.player2);
        game.gameBoard.boardArray[4][1] = pawn;

        game.gameBoard.movePiece(king, 4, 1);
        assertEquals(king, game.gameBoard.boardArray[4][1]);
    }


}
