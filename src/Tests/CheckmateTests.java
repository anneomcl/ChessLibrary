package Tests;
import Game.*;
import Pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anne on 9/12/2014.
 */
public class CheckmateTests {

    @Test
    public void checkmateTest() throws Exception
    {
        Game game = new Game();
        for(int i = 0; i < 8; i++)
        {
            game.gameBoard.boardArray[i][0] = null;
            game.gameBoard.boardArray[i][1] = null;
            game.gameBoard.boardArray[i][6] = null;
            game.gameBoard.boardArray[i][7] = null;
        }

        game.gameBoard.whitePieces.removeAllElements();
        game.gameBoard.blackPieces.removeAllElements();

        King king2 = new King(4,7, game.player2);
        Piece pawn1 = new Pawn(5,1,game.player2);
        Piece pawn2 = new Pawn(6,1,game.player2);
        Piece pawn3 = new Pawn(7,1,game.player2);
        King king1 = new King(6,0,game.player2);
        Piece rook = new Rook(2,0, game.player2);
        Piece rook2 = new Rook(3,3, game.player2);

        game.gameBoard.blackPieces.add(rook);
        game.gameBoard.blackPieces.add(king2);
        game.gameBoard.whitePieces.add(pawn2);
        game.gameBoard.whitePieces.add(rook2);
        game.gameBoard.whitePieces.add(pawn1);
        game.gameBoard.whitePieces.add(pawn3);
        game.gameBoard.whitePieces.add(king1);

        assertEquals(true, king1.isInCheckmate());
    }



}
