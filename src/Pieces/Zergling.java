package Pieces;
import Game.*;

/* The Zergling can move forward as many spaces as it wants, but it cannot leap. If it devours an enemy after making
 * a move, it provides food for the swarm, causing a new Zergling to spawn where the old Zergling was
 * originally located.
 */

/**
 * Created by Anne on 9/18/2014.
 */
public class Zergling extends Piece {

    Type type;

    public Zergling(int x, int y, Player player) {
        super(x, y, player);
        type = Type.ZERGLING;
    }

    public Type getType()
    {
        return Type.ZERGLING;
    }

    public boolean isValidPath(int finalX, int finalY)
    {
        if((this.player.playerColor == Color.WHITE && finalY - this.y > 0) ||
                (this.player.playerColor == Color.BLACK && finalY - this.y < 0))
        {
            return true;
        }

        return false;
    }

    public int[][] drawPath(int startX, int startY, int finalX, int finalY)
    {
        int pairs = Math.abs(finalY - startY);

        int [][] path = new int[2][pairs];
        if(pairs - 1 > 0)
        {
            for(int i = 0; i < pairs - 1; i++)
            {
                path[0][i] = startX;
                if(this.player == this.player.myGame.player1)
                    path[1][i] = startY - 1;
                if(this.player == this.player.myGame.player2)
                    path[1][i] = startY + 1;
            }
        }

        return path;
    }
}
