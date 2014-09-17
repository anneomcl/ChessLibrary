import org.junit.Test;
/**
 * Created by Anne on 9/12/2014.
 */
public class KnightTests {

    @Test
    public void knightMovement() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = knight;

        printData(knight, 3, 6);
        game.gameBoard.boardArray[3][6] = null;
        game.gameBoard.movePiece(knight, 3, 6); //up 2, left 1
        printArrivalData(knight);

        printData(knight, 4, 4);
        game.gameBoard.movePiece(knight, 4, 4); //down 2, right 1
        printArrivalData(knight);

        printData(knight, 5, 6);
        game.gameBoard.boardArray[5][6] = null;
        game.gameBoard.movePiece(knight, 5, 6); //up 2, right 1
        printArrivalData(knight);

        printData(knight, 4, 4);
        game.gameBoard.movePiece(knight, 4, 4); //down 2, left 1
        printArrivalData(knight);

        printData(knight, 6, 3);
        game.gameBoard.movePiece(knight, 6, 3); //down 1, right 2
        printArrivalData(knight);

        printData(knight, 4, 4);
        game.gameBoard.movePiece(knight, 4, 4); //up 1, left 2
        printArrivalData(knight);

        printData(knight, 6, 5);
        game.gameBoard.movePiece(knight, 6, 5); //up 1, right 2
        printArrivalData(knight);

        printData(knight, 4, 4);
        game.gameBoard.movePiece(knight, 4, 4); //down 1, left 2
        printArrivalData(knight);

    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = knight;

        game.gameBoard.movePiece(knight, 40,21);
    }

    @Test
    public void validLeaping() throws Exception
    {
        Game game = new Game();
        Piece knight = game.gameBoard.boardArray[1][0];

        game.gameBoard.movePiece(knight, 2, 2);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece knight = new Knight(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = knight;

        game.gameBoard.movePiece(knight, 4, 4);
    }

    @Test
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
    }

    public void printData(Piece piece, int end_X, int end_Y)
    {
        System.out.println("Piece is at: " + piece.location_X + ", " + piece.location_Y + " and is travelling" +
                "to " + end_X + ", " + end_Y);
    }

    public void printArrivalData(Piece piece)
    {
        System.out.println("Piece arrived at: " + piece.location_X + ", " + piece.location_Y);
    }

}
