/**
 * Created by Anne on 9/11/2014.
 */
public class Queen extends Piece {

    int type;

    /**
     * The constructor for a Queen.
     * @param location_X the Queen's X location
     * @param location_Y the Queen's Y location
     * @param player the Queen's associated player
     */
    public Queen(int location_X, int location_Y, Player player)
    {
        super(location_X, location_Y, player);
        type = QUEEN;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType()
    {
        return QUEEN;
    }

    /**
     * A function that determines whether the Queen is moving horizontally, vertically, or diagonally.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        int location_X_diff = Math.abs(end_X - this.location_X);
        int location_Y_diff = Math.abs(end_Y - this.location_Y);

        if((location_X_diff == location_Y_diff) || (end_X == this.location_X) || (end_Y == this.location_Y))
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

        if(end_Y == start_Y) //if queen is travelling horizontally
        {
            pairs = Math.abs(end_X - start_X);
            if(end_X - start_X < 0)
                X_dir = -1;
            else
                X_dir = 1;
        }

        else if(end_X == start_X) //if queen is travelling vertically
        {
            pairs = Math.abs(end_Y - start_Y);
            if(end_Y - start_Y < 0)
                Y_dir = -1;
            else
                Y_dir = 1;
        }

        else //if queen is travelling diagonally
        {
            pairs = Math.abs(end_X - start_X);
            if(end_X - start_X < 0)
                X_dir = -1;
            if(end_Y - start_Y < 0)
                Y_dir = -1;
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
