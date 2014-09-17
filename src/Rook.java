/**
 * Created by Anne on 9/11/2014.
 */
public class Rook extends Piece{

    int type;

    /**
     * The constructor for a Rook.
     * @param location_X the Rook's X location
     * @param location_Y the Rook's Y location
     * @param player the Rook's associated player
     */
    public Rook(int location_X, int location_Y, Player player)
    {
        super(location_X, location_Y, player);
        type = ROOK;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType()
    {
        return ROOK;
    }

    /**
     * A function that determines whether the Rook is moving horizontally or vertically.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        if((end_X == this.location_X) || (end_Y == this.location_Y))
            return true;

        return false;
    }

    /**
     * A function that draws a path from one point to another based on Piece type and stores the coordinates.
     * @param start_X   the initial X location
     * @param start_Y   the initial Y location
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return an array of coordinates of the given path
     */
    public int [][] drawPath(int start_X, int start_Y, int end_X, int end_Y)
    {
        int pairs;
        int X_dir = 0, Y_dir = 0;
        if(end_X - start_X !=0 && start_Y == end_Y) //if the rook is travelling horizontally
        {
            pairs = Math.abs(end_X - start_X);
            if(end_X - start_X < 0)
                X_dir = -1;
            else
                X_dir = 1;
        }

        else //if the rook is travelling vertically
        {
            pairs = Math.abs(end_Y - start_Y);
            if(end_Y - start_Y < 0)
                Y_dir = -1;
            else
                Y_dir = 1;
        }

        int [][] path = new int[2][pairs];
        if(pairs - 1 > 0)
        {
            for(int i = 0; i < pairs - 1; i++)
            {
                path[0][i] = start_X + X_dir*1;
                path[1][i] = start_Y + Y_dir*1;
            }
        }

        return path;
    }
}
