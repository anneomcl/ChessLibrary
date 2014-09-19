package Tests;
import Game.*;
import Pieces.*;
import org.junit.Test;
/**
 * Created by Anne on 9/12/2014.
 */
public class BoardTests {

    @Test
    public void confirmBoardSetUp() throws Exception
    {
        Game game = new Game();

        System.out.println("Row 0 types should be: ROOK KNIGHT BISHOP QUEEN KING BISHOP KNIGHT ROOK");
        createGameHelper(0, 1, game.gameBoard);

        System.out.println("");
        System.out.println("Row 1 types should be: PAWN PAWN PAWN PAWN PAWN PAWN PAWN PAWN");
        createGameHelper(1, 1, game.gameBoard);

        System.out.println("");
        System.out.println("Row 7 types should be: ROOK KNIGHT BISHOP QUEEN KING BISHOP KNIGHT ROOK");
        createGameHelper(7, 2, game.gameBoard);

        System.out.println("");
        System.out.println("Row 6 types should be: PAWN PAWN PAWN PAWN PAWN PAWN PAWN PAWN");
        createGameHelper(6, 2, game.gameBoard);
    }

    public void createGameHelper(int row, int playerNumber, Board board)
    {
        boolean belongsToPlayer = true;

        System.out.print("Row " + row + " types actually are: ");

        for(int i = 0; i < 8; i++)
        {
            System.out.print(board.boardArray[i][row].getType() + " ");
            if(board.boardArray[i][row].player.playerNumber != playerNumber)
                belongsToPlayer = false;

        }
        if(belongsToPlayer)
            System.out.println("\nAll belong to Player " + playerNumber + "!");
    }

}
