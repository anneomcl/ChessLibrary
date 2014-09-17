import java.util.Vector;

/**
 * Created by Anne on 9/11/2014.
 */
public class Player {

    int playerNumber;
    boolean goesFirst;
    boolean isLoser = false;
    Game myGame; //Players can access the board, the turn number, and the opponent's pieces.

    /**
     * Constructor for a Player.
     * @param playerNumber
     * @param goesFirst
     */
    public Player(int playerNumber, boolean goesFirst)
    {
        this.playerNumber = playerNumber;
        this.goesFirst = goesFirst;
    }

    /**
     * Gets a list of the player's pieces.
     * @param playerNumber  the Player's number
     * @return allyPieces  a vector of the player's pieces
     */
    public Vector<Piece> getAllyPieces(int playerNumber)
    {
        Vector<Piece> allyPieces;

        if(playerNumber == 1)
            allyPieces = myGame.p1Pieces;

        else
            allyPieces = myGame.p2Pieces;

        return allyPieces;
    }

    /**
     * Gets a list of the enemy's pieces.
     * @param playerNumber  the Player's number
     * @return enemyPieces  a vector of the enemy's pieces
     */
    public Vector<Piece> getEnemyPieces(int playerNumber)
    {
        Vector<Piece> enemyPieces;

        if(playerNumber == 1)
            enemyPieces = myGame.p2Pieces;

        else
            enemyPieces = myGame.p1Pieces;

        return enemyPieces;

    }
}
