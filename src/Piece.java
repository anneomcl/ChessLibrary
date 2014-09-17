/**
 * Created by Anne on 9/11/2014.
 */
public abstract class Piece {

    int location_X, location_Y;
    Player player;

    final static int KING = 1, ROOK = 2, BISHOP = 3, QUEEN = 4, KNIGHT = 5, PAWN = 6;

    /**
     * Constructor for a Piece.
     * @param location_X    the x location of the Piece
     * @param location_Y    the y location of the Piece
     * @param player    the Player object associated with the Piece
     */
    public Piece(int location_X, int location_Y, Player player)
    {
        this.location_X = location_X;
        this.location_Y = location_Y;
        this.player = player;
    }

    /**
     * A function that determines if their path is valid based on Piece type.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return  a boolean indicating whether the path is valid
     */
    abstract boolean isValidPath(int end_X, int end_Y);

    /**
     * A function that draws a path from one point to another based on Piece type and stores the coordinates.
     * @param start_X   the initial X location
     * @param start_Y   the initial Y location
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return an array of coordinates of the given path
     */
    abstract int[][] drawPath(int start_X, int start_Y, int end_X, int end_Y);

    /**
     * A function that returns the type of a Piece.
     * @return  an integer indicating the Piece type (as defined above)
     */
    public abstract int getType();
}
