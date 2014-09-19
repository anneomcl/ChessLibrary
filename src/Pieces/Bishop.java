package Pieces;
import Game.*;


/**
 * Created by Anne on 9/11/2014.
 */
public class Bishop extends Piece {

    PieceTypes.Type type;

    /**
     * The constructor for a Bishop.
     * @param x the Bishop's x coordinate
     * @param y the Bishop's y coordinate
     * @param player the bishop's associated player
     */
    public Bishop(int x, int y, Player player) {
        super(x, y, player);
        type = PieceTypes.Type.BISHOP;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public PieceTypes.Type getType() {return PieceTypes.Type.BISHOP;}

    /**
     * A function that determines whether the Bishop is moving diagonally.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int finalX, int finalY)
    {
        int x_diff = Math.abs(finalX - this.x);
        int y_diff = Math.abs(finalY - this.y);

        return x_diff == y_diff;
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
        int pairs = Math.abs(finalX - startX); //length of path

        int x_dir = 1, y_dir = 1; //determining direction
        if(finalX - startX < 0)
            x_dir = -1;
        if(finalY - startY < 0)
            y_dir = -1;

        int [][] path = new int[2][pairs]; //populating array of pairs

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