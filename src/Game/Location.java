package Game;

/**
 * Created by Anne on 9/17/2014.
 * A class to store coordinates as Locations. Will be implemented later to allow extendability to other forms of chess.
 */
public class Location {

    int x, y;

    /**
     * Constructor for a Location
     * @param x    the x coordinate
     * @param y    the y coordinate
     */
    public Location(int x, int y, Player player)
    {
        this.x = x;
        this.y = y;
    }
}
