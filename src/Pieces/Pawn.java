package Pieces;

import Game.*;
/**
 * Created by Anne on 9/11/2014.
 */
public class Pawn extends Piece {

    Type type;

    /**
     * The constructor for a Pawn.
     * @param x the Pawn's X location
     * @param y the Pawn's Y location
     * @param player the Pawn's associated player
     */
    public Pawn(int x, int y, Player player)
    {
        super(x, y, player);
        type = Type.PAWN;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public Type getType()
    {
        return Type.PAWN;
    }

    /**
     * A function that determines whether the Queen is moving horizontally, vertically, or diagonally.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int finalX, int finalY)
    {
        //checks if it is the player's first turn, and if there are no units along the path
        //if so, it will let the pawn move two spaces forward
        if(pawnCanMoveTwo(finalX, finalY))
            return true;

            //checks if pawn is moving on a diagonal, if it is moving only one space, and if there is an enemy in that space
        if(pawnCanCapture(finalX, finalY))
            return true;

            //checks if pawn is moving one space forward, does not let it move forward unless space is empty
        if(pawnCanMoveForward(finalX, finalY))
            return true;

        else
        {
            return false;
        }
    }

    /**
     * A function determining whether a pawn can move forward two spaces.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether a pawn can move forward two spaces
     */
    protected boolean pawnCanMoveTwo(int finalX, int finalY)
    {
        int abs_Y_diff = Math.abs(finalY - this.y);
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if((abs_Y_diff == 2)
                &&(this.player.myGame.turn == 1 || this.player.myGame.turn == 2)
                &&(board[finalX][finalY] == null)
                &&((this.player.playerColor == Color.WHITE && board[this.x][this.y - 1] == null)
                    ||(this.player.playerColor == Color.BLACK && board[this.x][this.y + 1] == null)))
        {
            return true;
        }

        else
            return false;
    }

    /**
     * A function determining whether a pawn can capture a piece and move diagonally.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the pawn can capture
     */
    protected boolean pawnCanCapture(int finalX, int finalY)
    {
        int abs_X_diff = Math.abs(finalX - this.x);
        int abs_Y_diff = Math.abs(finalY - this.y);
        int Y_diff = (finalY - this.y);
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if((abs_X_diff == abs_Y_diff) && (abs_Y_diff == 1))
        {
            if(this.player.playerColor == Color.WHITE && ((board[finalX][finalY] != null
                    && board[finalX][finalY].player.playerColor == Color.BLACK)) && Y_diff < 0)
                return true;

            if(this.player.playerColor == Color.BLACK && ((board[finalX][finalY] != null
                    && board[finalX][finalY].player.playerColor == Color.WHITE)) && Y_diff > 0)
                return true;
        }

        return false;

    }

    /**
     * A function determining whether a pawn can move forward. Unlike other pieces, it cannot capture enemy
     * pieces that are in front of it.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the pawn can move
     */
    protected boolean pawnCanMoveForward(int finalX, int finalY)
    {
        int abs_Y_diff = Math.abs(finalY - this.y);
        int Y_diff = finalY - this.y;
        Piece[][] board = this.player.myGame.gameBoard.boardArray;

        if(((this.player.playerColor == Color.WHITE && Y_diff < 0 && abs_Y_diff == 1 ) ||
                (this.player.playerColor == Color.BLACK && Y_diff > 0 && abs_Y_diff == 1)) &&
                board[finalX][finalY] == null && this.x == finalX)
        {
            return true;
        }

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
    public int[][] drawPath(int startX, int startY, int finalX, int finalY)
    {
        int pairs = 0;
        int[][] path = new int[2][pairs];

        return path;
    }
}
