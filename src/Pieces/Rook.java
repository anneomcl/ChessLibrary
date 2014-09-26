package Pieces;

import Game.*;
/**
 * Created by Anne on 9/11/2014.
 */
public class Rook extends Piece {

    Type type;

    /**
     * The constructor for a Rook.
     * @param x the Rook's X location
     * @param y the Rook's Y location
     * @param player the Rook's associated player
     */
    public Rook(int x, int y, Player player)
    {
        super(x, y, player);
        type = Type.ROOK;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public Type getType()
    {
        return Type.ROOK;
    }

    /**
     * A function that determines whether the Rook is moving horizontally or vertically.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int finalX, int finalY)
    {
        if((finalX == this.x) || (finalY == this.y))
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
        int pairs;
        int x_dir = 0, y_dir = 0;
        if(finalX - startX !=0 && startY == finalY) //if the rook is travelling horizontally
        {
            pairs = Math.abs(finalX - startX);
            if(finalX - startX < 0)
                x_dir = -1;
            else
                x_dir = 1;
        }

        else //if the rook is travelling vertically
        {
            pairs = Math.abs(finalY - startY);
            if(finalY - startY < 0)
                y_dir = -1;
            else
                y_dir = 1;
        }

        int [][] path = new int[2][pairs];
        if(pairs - 1 > 0)
        {
            for(int i = 0; i < pairs - 1; i++)
            {
                path[0][i] = startX + x_dir*1;
                path[1][i] = startY + y_dir*1;
            }
        }

        return path;
    }
}
