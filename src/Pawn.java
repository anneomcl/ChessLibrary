/**
 * Created by Anne on 9/11/2014.
 */
public class Pawn extends Piece {

    int type;

    /**
     * The constructor for a Pawn.
     * @param location_X the Pawn's X location
     * @param location_Y the Pawn's Y location
     * @param player the Pawn's associated player
     */
    public Pawn(int location_X, int location_Y, Player player)
    {
        super(location_X, location_Y, player);
        type = PAWN;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType()
    {
        return PAWN;
    }

    /**
     * A function that determines whether the Queen is moving horizontally, vertically, or diagonally.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        //checks if it is the player's first turn, and if there are no units along the path
        //if so, it will let the pawn move two spaces forward
        if(pawnCanMoveTwo(this, end_X, end_Y))
            return true;

            //checks if pawn is moving on a diagonal, if it is moving only one space, and if there is an enemy in that space
        else if(pawnCanCapture(this, end_X, end_Y))
            return true;

            //checks if pawn is moving one space forward, does not let it move forward unless space is empty
        else if(pawnCanMoveForward(this, end_X, end_Y))
            return true;

        else
        {
            return false;
        }
    }

    /**
     * A function determining whether a pawn can move forward two spaces.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether a pawn can move forward two spaces
     */
    protected boolean pawnCanMoveTwo(Piece piece, int end_X, int end_Y)
    {
        int abs_Y_diff = Math.abs(end_Y - piece.location_Y);
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if((abs_Y_diff == 2)
                &&(this.player.myGame.turn == 1 || this.player.myGame.turn == 2)
                &&(board[end_X][end_Y] == null)
                &&((piece.player.playerNumber == 1 && board[piece.location_X][piece.location_Y + 1] == null)
                    ||(piece.player.playerNumber == 2 && board[piece.location_X][piece.location_Y - 1] == null)))
        {
            return true;
        }

        else
            return false;
    }

    /**
     * A function determining whether a pawn can capture a piece and move diagonally.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the pawn can capture
     */
    protected boolean pawnCanCapture(Piece piece, int end_X, int end_Y)
    {
        int abs_X_diff = Math.abs(end_X - piece.location_X);
        int abs_Y_diff = Math.abs(end_Y - piece.location_Y);
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if((abs_X_diff == abs_Y_diff) && (abs_Y_diff == 1))
        {
            if(piece.player.playerNumber == 1 && ((board[end_X][end_Y] != null
                    && board[end_X][end_Y].player.playerNumber == 2)))
                return true;

            if(piece.player.playerNumber == 1 && ((board[end_X][end_Y] != null
                    && board[end_X][end_Y].player.playerNumber == 2)))
                return true;
        }

        return false;

    }

    /**
     * A function determining whether a pawn can move forward. Unlike other pieces, it cannot capture enemy
     * pieces that are in front of it.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the pawn can move
     */
    protected boolean pawnCanMoveForward(Piece piece, int end_X, int end_Y)
    {
        int abs_Y_diff = Math.abs(end_Y - piece.location_Y);
        int Y_diff = end_Y - piece.location_Y;
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if(((piece.player.playerNumber == 1 && Y_diff > 0 && abs_Y_diff == 1 ) ||
                (piece.player.playerNumber == 2 && Y_diff < 0 && abs_Y_diff == 1)) &&
                board[end_X][end_Y] == null && piece.location_X == end_X)
        {
            return true;
        }

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
    public int[][] drawPath(int start_X, int start_Y, int end_X, int end_Y)
    {
        int pairs = 0;
        int[][] path = new int[2][pairs];

        return path;
    }
}
