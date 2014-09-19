package Game;

import java.util.Random;
/**
 * Created by Anne on 9/11/2014.
 */
public class Game {

    public Board gameBoard;
    public Player player1, player2;
    public int turn;
    final static int standardHeight = 8, standardWidth = 8;

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
        gameBoard = new Board(standardHeight,standardWidth, this);
        setPlayers();
        gameBoard.setPieces();
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

}
