import org.junit.Test;

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

        game.p2Pieces.removeAllElements();

        Piece king = new King(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = king;

        printData(king, 3, 3);
        game.gameBoard.movePiece(king, 3, 3); //down-left
        printArrivalData(king);

        printData(king, 4, 4);
        game.gameBoard.movePiece(king, 4, 4); //up-right
        printArrivalData(king);

        printData(king, 5, 3);
        game.gameBoard.movePiece(king, 5, 3); //up-left
        printArrivalData(king);

        printData(king, 4, 4);
        game.gameBoard.movePiece(king, 4, 4); //down-right
        printArrivalData(king);

        printData(king, 4, 5);
        game.gameBoard.movePiece(king, 4, 5); //up
        printArrivalData(king);

        printData(king, 4, 4);
        game.gameBoard.movePiece(king, 4, 4); //down
        printArrivalData(king);

        printData(king, 3, 4);
        game.gameBoard.movePiece(king, 3, 4); //left
        printArrivalData(king);

        printData(king, 4, 4);
        game.gameBoard.movePiece(king, 4, 4); //right
        printArrivalData(king);
    }

    @Test
    public void cantMoveTwo() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 2, 4); //down-left
    }

    @Test
    public void outOfBounds() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player1);
        game.gameBoard.boardArray[4][4] = king;

        game.gameBoard.movePiece(king, 40,21);
    }

    @Test
    public void invalidOrigin() throws Exception
    {
        Game game = new Game();
        Piece king = new King(4, 4, game.player1);
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
        Piece pawn = new Pawn (4, 1, game.player2);
        game.gameBoard.boardArray[4][1] = pawn;

        game.gameBoard.movePiece(king, 4, 1);
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

    @Test
    public void checkmateCantMove() throws Exception
    {
        Game game = new Game();
        Piece king = game.gameBoard.boardArray[4][0];

        Piece pawn = game.gameBoard.boardArray[5][1];
        game.gameBoard.movePiece(pawn, 5, 2);

        Piece pawn2 = game.gameBoard.boardArray[6][1];
        game.gameBoard.movePiece(pawn2, 6, 3);

        Piece queen = game.gameBoard.boardArray[3][7];
        game.gameBoard.movePiece(queen, 7, 3);

        Piece pawn3 = game.gameBoard.boardArray[4][6];
        game.gameBoard.movePiece(pawn3,4, 4);

        game.gameBoard.movePiece(king, 5, 1);

    }

}
