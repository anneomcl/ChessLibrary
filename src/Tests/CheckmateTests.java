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

        game.gameBoard.p1Pieces.removeAllElements();
        game.gameBoard.p2Pieces.removeAllElements();

        King king2 = new King(4,7, game.player2);
        game.player2.playerKing = king2;
        Piece pawn1 = new Pawn(5,1,game.player1);
        Piece pawn2 = new Pawn(6,1,game.player1);
        Piece pawn3 = new Pawn(7,1,game.player1);
        King king1 = new King(6,0,game.player1);
        game.player1.playerKing = king1;
        Piece rook = new Rook(2,0, game.player2);
        Piece rook2 = new Rook(3,3, game.player1);

        game.gameBoard.p2Pieces.add(rook);
        game.gameBoard.p2Pieces.add(king2);
        game.gameBoard.p1Pieces.add(pawn2);
        game.gameBoard.p1Pieces.add(rook2);
        game.gameBoard.p1Pieces.add(pawn1);
        game.gameBoard.p1Pieces.add(pawn3);
        game.gameBoard.p1Pieces.add(king1);

        assertEquals(true, king1.isInCheckmate());
    }



}
