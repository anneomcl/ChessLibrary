package Pieces;
import Game.*;
import java.util.Vector;

/**
 * Created by Anne on 9/11/2014.
 */
public class King extends Piece {

    Type type;
    public boolean kingInCheck = false;

    /**
     * The constructor for a King.
     * @param x the King's X location
     * @param y the King's Y location
     * @param player the King's associated player
     */
    public King(int x, int y, Player player)
    {
        super(x, y, player);
        type = Type.KING;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public Type getType()
    {
        return Type.KING;
    }

    /**
     * A function that determines whether the King is moving one space in any direction, is not in check, and
     * is not in checkmate.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int finalX, int finalY)
    {
       /* if(isInCheckmate())
        {
            this.player.isLoser = true;
            return false;
        }*/

        if(isValidPathHelper(finalX, finalY) /*&& !kingInCheck
                && !isInCheck(finalX, finalY)*/)
        {
            return true;
        }

        return false;
    }

    /**
     * A function indicating whether the king is moving only one space in any direction.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the king's path is valid, regardless of check/checkmate
     */
    public boolean isValidPathHelper(int finalX, int finalY)
    {
        int x_diff = Math.abs(finalX - this.x);
        int y_diff = Math.abs(finalY - this.y);

        if((x_diff < 2) && (y_diff < 2))
            return true;

        else
            return false;
    }

    /**
     * A function determining whether the king is in checkmate. It determines if the king can move in any direction
     * @return
     */
    public boolean isInCheckmate()
    {
        Vector<Piece> enemies = this.player.getEnemyPieces(this.player.playerColor);
        Vector<Piece> attackingEnemies = new Vector<Piece>(1);

        for(int i = 0; i < enemies.size(); i++)
        {
            if(enemies.elementAt(i).getType() != Type.KING && canKillKing(enemies.elementAt(i), this.x, this.y))
            {
                kingInCheck = true;
                /*
                 * if there's more than one enemy that can kill the king in a given space,
                 * an ally cannot defend against both, and it cannot defend against knights.
                 */
                if(attackingEnemies.size() < 2 && enemies.elementAt(i).getType() != Type.KNIGHT)
                {
                    //keep a list of attacking enemies to potentially require an ally to defend against
                    attackingEnemies.add(enemies.elementAt(i));
                }
            }
        }

        if(!kingCanMove(this.x, this.y)
                && !kingCanMove(this.x + 1, this.y)
                && !kingCanMove(this.x + 1, this.y + 1)
                && !kingCanMove(this.x + 1, this.y - 1)
                && !kingCanMove(this.x - 1, this.y)
                && !kingCanMove(this.x - 1, this.y + 1)
                && !kingCanMove(this.x - 1, this.y - 1)
                && !kingCanMove(this.x + 1, this.y + 1))
        {
            //if(allyCanDefend(attackingEnemies))
                //return false;


        return true;

        }
        return false;
    }

    /**
     * A function that indicates if an ally is unable to defend the king. An ally can only defend against one enemy
     * at a time. It checks every ally piece until one of them can move to a square on the enemy's path to the king.
     * If any piece can block the enemy from capturing the king, then the ally CAN defend, and the function returns
     * false. **** MAKE SURE TO CHECK FOR KNIGHTS WITH PATH BLOCKING... CAN STILL CAPTURE
     * @return a boolean indicating whether an ally is unable to defend the king
     *
     * TO-DO: Corner case where, if ally is blocking a potential attacker and moves to block another one,
     * could cause infinite loop. Need to check if king is in check even after ally moves. If so, then ally
     * cannot defend.
     */
    protected boolean allyCanDefend(Vector<Piece> attackingEnemies)
    {
        if(attackingEnemies.size() == 1)
        {
            Piece enemy = attackingEnemies.elementAt(0);
            int[][] enemyPath = enemy.drawPath(enemy.x, enemy.y, this.x, this.y);
            int enemyPathSize = enemyPath[0].length;

            Vector<Piece> allies = this.player.getAllyPieces(this.player.playerColor);

            for(int i = 0; i < allies.size(); i++)
            {
                for(int j = 0; j < enemyPathSize; j++)
                {
                    if(allies.get(i).getType() != Type.KING &&
                            (this.player.myGame.gameBoard.isValidMove(allies.get(i), enemyPath[0][j], enemyPath[1][j]) &&
                            allies.get(i).isValidPath(enemyPath[0][j], enemyPath[1][j])) ||
                            (this.player.myGame.gameBoard.isValidMove(allies.get(i), enemy.x, enemy.y) &&
                            allies.get(i).isValidPath(enemy.y, enemy.x)))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * A function that indicates whether the king is unable to move. It ensures the move is valid for any piece,
     * that the king will not be in check by moving to that space, and that it is only moving one space in any
     * direction.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the king is unable to move to a given space
     */
    protected boolean kingCanMove(int finalX, int finalY)
    {
        Board board = this.player.myGame.gameBoard;

        if(board.isValidMove(this, finalX, finalY)
                && isValidPathHelper(finalX, finalY)
                && !isInCheck(finalX, finalY))
        {
            return true;
        }
        return false;
    }

    /**
     * A function determining whether a king is in check. It iterates through all enemies, and if one can
     * capture the king, then the king is in check.
     * @param x the x coord of the king
     * @param y the y coord of the king
     * @return a boolean indicating whether a king is in check
     */
    public  boolean isInCheck(int x, int y)
    {
        Vector<Piece> enemies = this.player.getEnemyPieces(this.player.playerColor);

        for(int i = 0; i < enemies.size(); i++)
        {
            if(canKillKing(enemies.elementAt(i), x, y))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * A function determining whether a given enemy can move to the king's coordinates and capture it.
     * @param enemy the enemy that could attack the king
     * @param x
     * @param y
     * @return a boolean indicating whether the enemy can capture the king
     */
    protected boolean canKillKing(Piece enemy, int x, int y)
    {
        Board board = enemy.player.myGame.gameBoard;

        //pawns can only kill king if there is a piece to capture, so must consider it while king is in place
        if(enemy.getType() == Type.PAWN && (board.isValidMove(enemy, x, y) && enemy.isValidPath(x, y)))
        {
            return true;
        }

        //must delete the king in its current state temporarily to see if the enemy's path to the king's
        //final position is possible. there are some cases in which leaving the king in its original position
        //will "block" the enemy piece that could capture the king if the king was in its final location
        Player player = this.player;
        int kingX = this.x;
        int kingY = this.y;
        board.boardArray[kingX][kingY] = null;

        if(board.isValidMove(enemy, x, y)
                && enemy.isValidPath(x, y))
        {
            King new_king = new King(kingX, kingY, player);
            return true;
        }

        else
        {
            King new_king = new King(kingX, kingY, player);
            return false;
        }
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
        int pairs = 0; //kings will never have a path
        int [][] path = new int[2][pairs];

        return path;
    }

}
