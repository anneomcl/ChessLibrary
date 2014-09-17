/**
 * Created by Anne on 9/11/2014.
 */
public class Knight extends Piece {

    int type;

    /**
     * The constructor for a Knight.
     * @param location_X the Knight's X location
     * @param location_Y the Knight's Y location
     * @param player the Knight's associated player
     */
    public Knight(int location_X, int location_Y, Player player)
    {
        super(location_X, location_Y, player);
        type = KNIGHT;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType()
    {
        return KNIGHT;
    }

    /**
     * A function that determines whether the Knight is moving in an "L" path.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        int location_X_diff = Math.abs(end_X - this.location_X);
        int location_Y_diff = Math.abs(end_Y - this.location_Y);

        if((location_X_diff == 1 && location_Y_diff == 2) || (location_X_diff == 2 && location_Y_diff == 1))
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
        int X_diff = end_X - start_X, Y_diff = end_Y - start_Y;

        int pairs = 2; //knights will always have 2 squares on their path
        int [][] path = new int[2][pairs];

        if(pairs > 0)
        {
            for(int i = 0; i < pairs; i++)
            {
                path[0][i] = start_X + X_diff;
                path[1][i] = start_Y + Y_diff;
            }
        }

        return path;
    }
}
