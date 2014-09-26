package Pieces;
import Game.*;

//The Princess is a Knight and a Bishop combined.

/**
 * Created by Anne on 9/18/2014.
 */
public class Princess extends Piece {

    Type type;

    public Princess(int x, int y, Player player) {
        super(x, y, player);
        type = Type.PRINCESS;
    }

    public Type getType()
    {
        return Type.PRINCESS;
    }

    public boolean isValidPath(int finalX, int finalY)
    {
        int x_diff = Math.abs(finalX - this.x);
        int y_diff = Math.abs(finalY - this.y);

        if((x_diff == 1 && y_diff == 2) || (x_diff == 2 && y_diff == 1) || x_diff == y_diff)
            return true;

        return false;
    }

    public int[][] drawPath(int startX, int startY, int finalX, int finalY)
    {
        if((finalX - startX) != (finalY - startY)) //if moving like a knight, it can leap
        {
            int [][] path = new int[2][0];
            return path;
        }

        int pairs = Math.abs(finalX - startX); //length of path
        int [][] path = new int[2][pairs]; //populating array of pairs

        int x_dir = 1, y_dir = 1; //determining direction
        if(finalX - startX < 0)
            x_dir = -1;
        if(finalY - startY < 0)
            y_dir = -1;


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
