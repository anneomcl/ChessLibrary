import org.junit.Test;
/**
 * Created by Anne on 9/12/2014.
 */
public class QueenTests {

    @Test
    public void verticalMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = queen;

        printData(queen, 4, 5);
        game.gameBoard.movePiece(queen, 4, 5); //down-left
        printArrivalData(queen);

        printData(queen, 4, 3);
        game.gameBoard.movePiece(queen, 4, 3); //up-right
        printArrivalData(queen);
    }

    @Test
    public void horizontalMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = queen;

        printData(queen, 5, 4);
        game.gameBoard.movePiece(queen, 5, 4); //down-left
        printArrivalData(queen);

        printData(queen, 3, 4);
        game.gameBoard.movePiece(queen, 3, 4); //up-right
        printArrivalData(queen);
    }

    @Test
    public void diagonalMovement() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = queen;

        printData(queen, 3, 3);
        game.gameBoard.movePiece(queen, 3, 3); //down-left
        printArrivalData(queen);

        printData(queen, 4, 4);
        game.gameBoard.movePiece(queen, 4, 4); //up-right
        printArrivalData(queen);

        printData(queen, 5, 3);
        game.gameBoard.movePiece(queen, 5, 3); //up-left
        printArrivalData(queen);

        printData(queen, 4, 4);
        game.gameBoard.movePiece(queen, 4, 4); //down-right
        printArrivalData(queen);
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = queen;

        game.gameBoard.movePiece(queen, -1, -1);
    }

    @Test
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece queen = game.gameBoard.boardArray[3][0];

        game.gameBoard.movePiece(queen, 3, 2);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece queen = new Queen(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = queen;

        game.gameBoard.movePiece(queen, 4, 4);
    }

    @Test
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
