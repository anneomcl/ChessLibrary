import org.junit.Test;
/**
 * Created by Anne on 9/12/2014.
 */
public class RookTests {

    @Test
     public void verticalMovement() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = rook;

        printData(rook, 4, 5);
        game.gameBoard.movePiece(rook, 4, 5); //down-left
        printArrivalData(rook);

        printData(rook, 4, 3);
        game.gameBoard.movePiece(rook, 4, 3); //up-right
        printArrivalData(rook);
    }

    @Test
    public void horizontalMovement() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = rook;

        printData(rook, 5, 4);
        game.gameBoard.movePiece(rook, 5, 4); //down-left
        printArrivalData(rook);

        printData(rook, 3, 4);
        game.gameBoard.movePiece(rook, 3, 4); //up-right
        printArrivalData(rook);
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = rook;

        game.gameBoard.movePiece(rook, -1, -1);
    }

    @Test
    public void invalidLeaping() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];

        game.gameBoard.movePiece(rook, 0, 3);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece rook = new Rook(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = rook;

        game.gameBoard.movePiece(rook, 4, 4);
    }

    @Test
    public void invalidEndPoints() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];

        game.gameBoard.movePiece(rook, 0, 1);
    }

    @Test
    public void canCapturePieces() throws Exception
    {
        Game game = new Game();
        Piece rook = game.gameBoard.boardArray[0][0];
        game.gameBoard.boardArray[0][1].player = game.player2;

        game.gameBoard.movePiece(rook, 0, 1);
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
