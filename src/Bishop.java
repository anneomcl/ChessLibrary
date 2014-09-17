/**
 * Created by Anne on 9/11/2014.
 */
public class Bishop extends Piece {

    int type;

    /**
     * The constructor for a Bishop.
     * @param location_X the Bishop's x coordinate
     * @param location_Y the Bishop's y coordinate
     * @param player the bishop's associated player
     */
    public Bishop(int location_X, int location_Y, Player player) {
        super(location_X, location_Y, player);
        type = BISHOP;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType() {
        return BISHOP;
    }

    /**
     * A function that determines whether the Bishop is moving diagonally.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        int location_X_diff = Math.abs(end_X - this.location_X);
        int location_Y_diff = Math.abs(end_Y - this.location_Y);

        if(location_X_diff == location_Y_diff)
            return true;

        else
        {
            return false;
        }
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
        int pairs = Math.abs(end_X - start_X); //length of path

        int X_dir = 1, Y_dir = 1; //determining direction
        if(end_X - start_X < 0)
            X_dir = -1;
        if(end_Y - start_Y < 0)
            Y_dir = -1;

        int [][] path = new int[2][pairs]; //populating array of pairs

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