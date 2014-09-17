import java.util.Random;
import java.util.Vector;
/**
 * Created by Anne on 9/11/2014.
 */
public class Game {

    Board gameBoard;
    Player player1, player2;
    int turn;
    Vector<Piece> p1Pieces = new Vector<Piece>(16);
    Vector<Piece> p2Pieces = new Vector<Piece>(16);

    /**
     * Default constructor for a game.
     */
    public Game()
    {
        setStandardGame();
    }

    /**
     * Sets up a standard game with two players and an 8 x 8 board.
     */
    public void setStandardGame()
    {
        gameBoard = new Board(8, 8, this);
        setPlayers();
        setPieces();
        turn = 1;
    }

    /**
     * Initializes both players for a game.
     */
    public void setPlayers()
    {
        player1 = new Player(1, true);
        player2 = new Player(2, true);
        isFirst();

        player1.myGame = this;
        player2.myGame = this;
    }

    /**
     * Randomly determines which player goes first.
     */
    public void isFirst()
    {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(2) + 1;
        int randomNum2 = rand.nextInt(2) + 1;

        if(randomNum1 >= randomNum2)
            player2.goesFirst = false;
        else
            player1.goesFirst = false;
    }

    /**
     * Sets pieces on the board and initializes lists of pieces.
     */
    public void setPieces()
    {
        setPlayer1Pieces();
        setPlayer2Pieces();
        setPieceVectors();
    }

    /**
     * Places Player 1's pieces on the board.
     */
    public void setPlayer1Pieces()
    {
        Piece[][] board = this.gameBoard.boardArray;

        for(int i = 0; i < 8; i++)
        {
            Piece pawn = new Pawn(i, 1, this.player1);
            board[i][1] = pawn;
        }

        Piece rook = new Rook(0, 0, this.player1);
        Piece rook2 = new Rook(7, 0, this.player1);
        board[0][0] = rook;
        board[7][0] = rook2;

        Piece knight = new Knight(1, 0, this.player1);
        Piece knight2 = new Knight(6, 0, this.player1);
        board[1][0] = knight;
        board[6][0] = knight2;

        Piece bishop = new Bishop(2, 0, this.player1);
        Piece bishop2 = new Bishop(5, 0, this.player1);
        board[2][0] = bishop;
        board[5][0] = bishop2;

        Piece queen = new Queen(3, 0, this.player1);
        Piece king = new King(4, 0, this.player1);
        board[3][0] = queen;
        board[4][0] = king;
    }

    /**
     * Places PLayer 2's pieces on the board.
     */
    public void setPlayer2Pieces()
    {
        Piece[][] board = this.gameBoard.boardArray;

        for(int i = 0; i < 8; i++)
        {
            Piece pawn = new Pawn(i, 6, this.player2);
            board[i][6] = pawn;
        }

        Piece rook = new Rook(0, 7, this.player2);
        Piece rook2 = new Rook(7, 7, this.player2);
        board[0][7] = rook;
        board[7][7] = rook2;

        Piece knight = new Knight(1, 7, this.player2);
        Piece knight2 = new Knight(6,7, this.player2);
        board[1][7] = knight;
        board[6][7] = knight2;

        Piece bishop = new Bishop(2, 7, this.player2);
        Piece bishop2 = new Bishop(5, 7, this.player2);
        board[2][7] = bishop;
        board[5][7] = bishop2;

        Piece queen = new Queen(3, 7, this.player2);
        Piece king = new King(4, 7, this.player2);
        board[3][7] = queen;
        board[4][7] = king;
    }

    /**
     * Initializes the game's vectors of Player 1 and Player 2's pieces.
     */
    public void setPieceVectors()
    {
        for(int i = 0; i < 8; i++)
        {
            p1Pieces.add(gameBoard.boardArray[i][0]);
            p1Pieces.add(gameBoard.boardArray[i][1]);
            p2Pieces.add(gameBoard.boardArray[i][6]);
            p2Pieces.add(gameBoard.boardArray[i][7]);
        }
    }
}
