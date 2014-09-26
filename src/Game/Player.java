package Game;
import Pieces.*;

import java.util.Vector;

/**
 * Created by Anne on 9/11/2014.
 */
public class Player {

    public Color playerColor;
    public boolean goesFirst;
    public boolean isLoser = false;
    public Game myGame; //Players can access the board, the turn number, and the opponent's pieces.
    public boolean isTurn = false;
    public int score = 0;

    /**
     * Constructor for a Player.
     * @param playerColor
     * @param goesFirst
     */
    public Player(Color playerColor, boolean goesFirst)
    {
        this.playerColor = playerColor;
        this.goesFirst = goesFirst;
    }

    /**
     * Gets a list of the player's pieces.
     * @param playerColor  the Player's number
     * @return allyPieces  a vector of the player's pieces
     */
    public Vector<Piece> getAllyPieces(Color playerColor)
    {
        Vector<Piece> allyPieces;

        if(playerColor == Color.WHITE)
            allyPieces = myGame.gameBoard.whitePieces;

        else
            allyPieces = myGame.gameBoard.blackPieces;

        return allyPieces;
    }

    /**
     * Gets a list of the enemy's pieces.
     * @param playerColor  the Player's number
     * @return enemyPieces  a vector of the enemy's pieces
     */
    public Vector<Piece> getEnemyPieces(Color playerColor)
    {
        Vector<Piece> enemyPieces;

        if(playerColor == Color.WHITE)
            enemyPieces = myGame.gameBoard.blackPieces;

        else
            enemyPieces = myGame.gameBoard.whitePieces;

        return enemyPieces;

    }
}
