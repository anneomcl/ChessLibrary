import org.junit.Test;

/**
 * Created by Anne on 9/12/2014.
 */
public class PawnTests {

    @Test
    public void canMoveTwo() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];

        printData(pawn, 1, 3);
        game.gameBoard.movePiece(pawn, 1, 3); //move two spaces forward
        printArrivalData(pawn);
    }

    @Test
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
        Piece pawn2 = new Pawn(2, 2, game.player2);
        game.gameBoard.boardArray[2][2] = pawn2;

        game.gameBoard.movePiece(pawn, 2, 2);
    }

    @Test
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
    }

    @Test
    public void canNotMoveForward() throws Exception
    {
        Game game = new Game();
        Piece pawn = game.gameBoard.boardArray[1][1];
        Piece pawn2 = new Pawn(1, 2, game.player2);
        game.gameBoard.boardArray[1][2] = pawn2;

        game.gameBoard.movePiece(pawn, 1, 2);
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = pawn;

        game.gameBoard.movePiece(pawn, 40,21);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece pawn = new Pawn(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = pawn;

        game.gameBoard.movePiece(pawn, 4, 4);
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
