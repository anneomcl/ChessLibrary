import java.util.Vector;

/**
 * Created by Anne on 9/11/2014.
 */
public class King extends Piece {

    int type;
    Vector<Piece> attackingEnemies = new Vector(1);

    /**
     * The constructor for a King.
     * @param location_X the King's X location
     * @param location_Y the King's Y location
     * @param player the King's associated player
     */
    public King(int location_X, int location_Y, Player player)
    {
        super(location_X, location_Y, player);
        type = KING;
    }

    /**
     * A function that gets the Piece type.
     * @return  an integer indicating the Piece type
     */
    public int getType()
    {
        return KING;
    }

    /**
     * A function that determines whether the King is moving one space in any direction, is not in check, and
     * is not in checkmate.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the path is valid
     */
    public boolean isValidPath(int end_X, int end_Y)
    {
        if(isValidPathHelper(end_X, end_Y) && notInCheckOrCheckmate(this, end_X, end_Y))
        {
            return true;
        }

        return false;
    }

    /**
     * A function indicating whether the king is moving only one space in any direction.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the king's path is valid, regardless of check/checkmate
     */
    public boolean isValidPathHelper(int end_X, int end_Y)
    {
        int location_X_diff = Math.abs(end_X - this.location_X);
        int location_Y_diff = Math.abs(end_Y - this.location_Y);

        if((location_X_diff < 2) && (location_Y_diff < 2))
            return true;

        else
            return false;
    }

    /**
     * A function indicating whether the king is in check or checkmate. It first determines if the king is
     * in checkmate, and sets the game's end condition to true. Then it determines if the king is in check
     * in its starting location AND the intended end location. If it is, then the user will be prompted to
     * enter a new move.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the king is in check or checkmate
     */
    protected boolean notInCheckOrCheckmate(Piece piece, int end_X, int end_Y)
    {
        if(isInCheck(piece, piece.location_X, piece.location_Y))
        {
            attackingEnemies.removeAllElements();
            if(!isInCheck(piece, end_X, end_Y))
            {
                attackingEnemies.removeAllElements();
                return true;
            }
        }

        else if(isInCheckmate(piece))
        {
            piece.player.isLoser = true;
            return false;
        }

        attackingEnemies.removeAllElements();
        return true;
    }

    /**
     * A function determining whether the king is in checkmate. It determines if the king can move in any direction
     * @param piece the piece to move
     * @return
     */
    protected boolean isInCheckmate(Piece piece)
    {
        if(isInCheck(piece, piece.location_X, piece.location_Y)
                && kingCantMove(piece, piece.location_X, piece.location_Y)
                && kingCantMove(piece, piece.location_X + 1, piece.location_Y)
                && kingCantMove(piece, piece.location_X + 1, piece.location_Y + 1)
                && kingCantMove(piece, piece.location_X + 1, piece.location_Y - 1)
                && kingCantMove(piece, piece.location_X - 1, piece.location_Y)
                && kingCantMove(piece, piece.location_X - 1, piece.location_Y + 1)
                && kingCantMove(piece, piece.location_X - 1, piece.location_Y - 1)
                && kingCantMove(piece, piece.location_X + 1, piece.location_Y + 1))
        {
            attackingEnemies.removeAllElements();
            if(allyCantDefend(piece))
                return true;
        }

        attackingEnemies.removeAllElements();
        return false;


    }

    /**
     * A function that indicates if an ally is unable to defend the king. An ally can only defend against one enemy
     * at a time. It checks every ally piece until one of them can move to a square on the enemy's path to the king.
     * If any piece can block the enemy from capturing the king, then the ally CAN defend, and the function returns
     * false.
     * @param piece the piece to move
     * @return a boolean indicating whether an ally is unable to defend the king
     */
    protected boolean allyCantDefend(Piece piece)
    {
        if(attackingEnemies.size() == 1)
        {
            Piece enemy = attackingEnemies.elementAt(0);
            int[][] enemyPath = enemy.drawPath(enemy.location_X, enemy.location_Y, piece.location_X, piece.location_Y);
            int enemyPathSize = enemyPath[0].length;

            Vector<Piece> allies = piece.player.getAllyPieces(piece.player.playerNumber);

            for(int i = 0; i < allies.size(); i++)
            {
                for(int j = 0; j < enemyPathSize; j++)
                {
                    if(piece.player.myGame.gameBoard.isValidMove(piece, enemyPath[0][j], enemyPath[1][j])
                            && piece.isValidPath(enemyPath[0][j], enemyPath[1][j]))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * A function that indicates whether the king is unable to move. It ensures the move is valid for any piece,
     * that the king will not be in check by moving to that space, and that it is only moving one space in any
     * direction.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the king is unable to move to a given space
     */
    protected boolean kingCantMove(Piece piece, int end_X, int end_Y)
    {
        Board board = piece.player.myGame.gameBoard;

        if(board.isValidMove(piece, end_X, end_Y)
                && !isInCheck(piece,end_X, end_Y)
                && isValidPathHelper(end_X, end_Y))
        {
            attackingEnemies.removeAllElements();
            return false;
        }

        attackingEnemies.removeAllElements();
        return true;
    }

    /**
     * A function determining whether a king is in check. It iterates through all enemies, and if one can
     * capture the king, then the king is in check.
     * @param piece
     * @param end_X
     * @param end_Y
     * @return a boolean indicating whether a king is in check
     */
    protected  boolean isInCheck(Piece piece, int end_X, int end_Y)
    {
        Vector<Piece> enemies = piece.player.getEnemyPieces(piece.player.playerNumber);

        for(int i = 0; i < enemies.size(); i++)
        {
            if(canKillKing(enemies.elementAt(i), piece))
            {
                if(attackingEnemies.size() < 2 //if there's more than one enemy that can kill the king in a given space,
                                                //an ally cannot defend against both
                        && enemies.elementAt(i).getType() != KNIGHT) //discard knights because they can leap
                                                                     //(thus allies cannot defend)
                {
                    attackingEnemies.add(enemies.elementAt(i)); //keep a list of attacking enemies to potentially
                                                                //require an ally to defend against
                }
            }
        }

        if(attackingEnemies.isEmpty())
            return false;

        return true;
    }

    /**
     * A function determining whether a given enemy can move to the king's coordinates and capture it.
     * @param enemy the enemy that could attack the king
     * @param king the king himself
     * @return a boolean indicating whether the enemy can capture the king
     */
    protected boolean canKillKing(Piece enemy, Piece king)
    {
        Board board = enemy.player.myGame.gameBoard;

        if(board.isValidMove(enemy, king.location_X, king.location_Y)
                && enemy.isValidPath(king.location_X, king.location_Y)
                && board.isCapture(enemy, king.location_X, king.location_Y))
        {
            return true;
        }

        else
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
        int pairs = 0; //kings will never have a path
        int [][] path = new int[2][pairs];

        return path;
    }

}
