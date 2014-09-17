import org.junit.Test;

/**
 * Created by Anne on 9/12/2014.
 */
public class BishopTests {

    @Test
    public void diagonalMovement() throws Exception
    {
        Game game = new Game();
        Piece bishop = new Bishop(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = bishop;

        printData(bishop, 3, 3);
        game.gameBoard.movePiece(bishop, 3, 3); //down-left
        printArrivalData(bishop);

        printData(bishop, 4, 4);
        game.gameBoard.movePiece(bishop, 4, 4); //up-right
        printArrivalData(bishop);

        printData(bishop, 5, 3);
        game.gameBoard.movePiece(bishop, 5, 3); //up-left
        printArrivalData(bishop);

        printData(bishop, 4, 4);
        game.gameBoard.movePiece(bishop, 4, 4); //down-right
        printArrivalData(bishop);
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece bishop = new Bishop(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = bishop;

        game.gameBoard.movePiece(bishop, 40,21);
    }

    @Test
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 4, 2);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece bishop = new Bishop(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = bishop;

        game.gameBoard.movePiece(bishop, 4, 4);
    }

    @Test
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];

        game.gameBoard.movePiece(bishop, 3, 1);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece bishop = game.gameBoard.boardArray[2][0];
        game.gameBoard.boardArray[2][0].player = game.player2;

        game.gameBoard.movePiece(bishop, 3, 1);
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
