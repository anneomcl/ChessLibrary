package Pieces;

import Game.*;
/**
 * Created by Anne on 9/11/2014.
 */
public class Knight extends Piece {

   Type type;

    /**
     * The constructor for a Knight.
     * @param x the Knight's X location
     * @param y the Knight's Y location
     * @param player the Knight's associated player
     */
    public Knight(int x, int y, Player player)
    {
        super(x, y, player);
        type = Type.KNIGHT;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public Type getType()
    {
        return Type.KNIGHT;
    }

    /**
     * A function that determines whether the Knight is moving in an "L" path.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int finalX, int finalY)
    {
        int x_diff = Math.abs(finalX - this.x);
        int y_diff = Math.abs(finalY - this.y);

        if((x_diff == 1 && y_diff == 2) || (x_diff == 2 && y_diff == 1))
            return true;

        return false;
    }

    /**
     * A function that draws a path from one point to another based on Piece type and stores the coordinates.
     * @param startX   the initial X location
     * @param startY   the initial Y location
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return an array of coordinates of the given path
     */
    public int [][] drawPath(int startX, int startY, int finalX, int finalY)
    {
        int pairs = 0; //knights can leap, so a path is not necessary
        int [][] path = new int[2][pairs];

        return path;
    }
}
