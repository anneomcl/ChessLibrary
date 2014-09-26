package Pieces;
import Game.*;
/**
 * Created by Anne on 9/11/2014.
 */
public abstract class Piece {

    public int x, y;
    public Player player;

    /**
     * Constructor for a Piece.
     * @param x    the x location of the Piece
     * @param y    the y location of the Piece
     * @param player    the Player object associated with the Piece
     */
    public Piece(int x, int y, Player player)
    {
        this.x = x;
        this.y = y;
        this.player = player;
        this.player.myGame.gameBoard.boardArray[x][y] = this;
    }

    /**
     * A function that determines if their path is valid based on Piece type.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return  a boolean indicating whether the path is valid
     */
    public abstract boolean isValidPath(int finalX, int finalY);

    /**
     * A function that draws a path from one point to another based on Piece type and stores the coordinates.
     * @param startX   the initial X location
     * @param startY   the initial Y location
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return an array of coordinates of the given path
     */
    public abstract int[][] drawPath(int startX, int startY, int finalX, int finalY);

    /**
     * A function that returns the type of a Piece.
     * @return  an integer indicating the Piece type (as defined above)
     */
    public abstract Type getType();
}
