package Game;
import Pieces.*;
import Exceptions.InvalidMovementException;

import java.util.Vector;

/**
 * Created by Anne on 9/11/2014.
 */
public class Board {

    public int height, width;
    public Piece[][] boardArray;
    public Game game; //Boards can access information about players, pieces and turns.
    public Vector<Piece> p1Pieces = new Vector<Piece>(16);
    public Vector<Piece> p2Pieces = new Vector<Piece>(16);

    /**
     * The constructor for a board.
     * @param height the height of the board
     * @param width the width of the board
     * @param game the game associated with this board
     */
    public Board(int height, int width, Game game)
    {
        this.height = height;
        this.width = width;
        boardArray = new Piece[height][width];
        this.game = game;
    }

    /**
     * Sets pieces on the board and initializes lists of pieces.
     */
    public void setPieces()
    {
        setPlayer1Pieces();
        setPlayer2Pieces();
        setPieceVectors();
    }

    /**
     * Places Player 1's pieces on the board.
     */
    public void setPlayer1Pieces()
    {
        Piece[][] board = this.boardArray;

        for(int i = 0; i < 8; i++)
        {
            Piece pawn = new Pawn(i, 1, this.game.player1);
        }

        Piece topRook = new Rook(0, 0, this.game.player1);
        Piece bottomRook = new Rook(7, 0, this.game.player1);

        Piece topKnight = new Knight(1, 0, this.game.player1);
        Piece bottomKnight = new Knight(6, 0, this.game.player1);

        Piece topBishop = new Bishop(2, 0, this.game.player1);
        Piece bottomBishop = new Bishop(5, 0, this.game.player1);

        Piece queen = new Queen(3, 0, this.game.player1);
        King king = new King(4, 0, this.game.player1);
        this.game.player1.playerKing =  king;
    }

    /**
     * Places PLayer 2's pieces on the board.
     */
    public void setPlayer2Pieces()
    {
        Piece[][] board = this.boardArray;

        for(int i = 0; i < 8; i++)
        {
            Piece pawn = new Pawn(i, 6, this.game.player2);
            board[i][6] = pawn;
        }

        Piece rook = new Rook(0, 7, this.game.player2);
        Piece rook2 = new Rook(7, 7, this.game.player2);

        Piece knight = new Knight(1, 7, this.game.player2);
        Piece knight2 = new Knight(6,7, this.game.player2);

        Piece bishop = new Bishop(2, 7, this.game.player2);
        Piece bishop2 = new Bishop(5, 7, this.game.player2);

        Piece queen = new Queen(3, 7, this.game.player2);
        King king = new King(4, 7, this.game.player2);
        this.game.player2.playerKing =  king;
    }

    /**
     * Initializes the game's vectors of Player 1 and Player 2's pieces.
     */
    public void setPieceVectors()
    {
        for(int i = 0; i < 8; i++)
        {
            p1Pieces.add(this.boardArray[i][0]);
            p1Pieces.add(this.boardArray[i][1]);
            p2Pieces.add(this.boardArray[i][6]);
            p2Pieces.add(this.boardArray[i][7]);
        }
    }


    /**
     * A function to move a piece. It checks to see if the move is valid for any piece, then it checks if
     * that move is valid based on the piece's specific type. It handles capturing, then sets the new location.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     */
    public void movePiece(Piece piece, int finalX, int finalY)
    {
        if(isValidMove(piece, finalX, finalY) && piece.isValidPath(finalX, finalY))
        {
            if(isCapture(piece, finalX, finalY))
            {
                boardArray[finalX][finalY] = null;
                if(piece.getType() == PieceTypes.Type.ZERGLING)
                {
                    boardArray[piece.x][piece.y] = null;
                    boardArray[piece.x][piece.y] = new Zergling(piece.x, piece.y, piece.player);
                    piece.x = finalX;
                    piece.y = finalY;
                    boardArray[finalX][finalY] = piece;
                }
            }

            setNewPieceLocation(piece, finalX, finalY);
        }


        else
        {
            try {
                throw new InvalidMovementException();
            } catch (InvalidMovementException e) {
                e.printStackTrace();
            }
        }

        return;
    }

    /**
     * A function that determines whether a move is valid for any piece based on the following: 1) the move is within
     * the board's bounds, 2) the move does not involve invalid leaping, 3) the move is not the same as the
     * start location, 4) the move coordinates do not land on an ally piece, and 5) that the player's king is
     * not in check (meaning the player has to move the king).
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the move is valid
     */
    public boolean isValidMove(Piece piece, int finalX, int finalY)
    {
        //TO-DO: Make it so that pieces can't move if the king is in check
        int [][] path = piece.drawPath(piece.x, piece.y, finalX, finalY);

        if(isWithinBounds(finalX,finalY)&& (validLeaping(piece, path))&& (isNotOrigin(piece, finalX, finalY))
                && (isValidEndPoint(piece, finalX, finalY)))
        {
            return true;
        }

        return false;
    }

    /**
     * A function determining if coordinates are in bounds of the board.
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return  a boolean indicating whether the coordinates are in bounds
     */
    protected boolean isWithinBounds(int finalX, int finalY)
    {
        if((0 <= finalX && finalX < width) && (0 <= finalY && finalY < height))
            return true;

        return false;
    }

    /**
     * A function determining if a given path involves invalid leaping for a specific piece.
     * @param piece the piece to move
     * @param movePath the path of the piece, not including origin or endpoint
     * @return  a boolean indicating if there is invalid leaping
     */
    protected boolean validLeaping(Piece piece, int[][] movePath)
    {
        if(piece.getType() == PieceTypes.Type.KNIGHT) //knights can leap
            return true;

        //pawns only have a path under special circumstances. kings will never have a path
        if(piece.getType() == PieceTypes.Type.PAWN || piece.getType() == PieceTypes.Type.KING)
            return true;

        int pairs = movePath[0].length;

        for(int i = 0; i < pairs - 1; i++)
        {
            if(boardArray[movePath[0][i]][movePath[1][i]] != null)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * A function determining if a given move lands on the origin.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether the end coordinates are the same as the start coordinates
     */
    protected boolean isNotOrigin(Piece piece, int finalX, int finalY)
    {
        if((piece.x != finalX) || (piece.y != finalY))
            return true;

        else
        {
            return false;
        }

    }

    /**
     * A function determining if a move lands on an allied piece.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return  a boolean indicating whether the end coordinates are valid
     */
    protected boolean isValidEndPoint(Piece piece, int finalX, int finalY)
    {
        if((boardArray[finalX][finalY] == null)||((boardArray[finalX][finalY] != null)
                && boardArray[finalX][finalY].player.playerNumber != piece.player.playerNumber))
        {
            return true;
        }

        return false;
    }

    /**
     * A function determining if the move results in a capture.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     * @return a boolean indicating whether a capture will occur
     */
    public boolean isCapture(Piece piece, int finalX, int finalY)
    {

        if(boardArray[finalX][finalY]!= null && boardArray[finalX][finalY].player != piece.player)
        {

            return true;
        }

        else
            return false;
    }

    /**
     * A function that transfers a piece to a new location, and clears the board space at
     * its previous location.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     */
    public void setNewPieceLocation(Piece piece, int finalX, int finalY)
    {
        int originX = piece.x;
        int originY = piece.y;

        piece.x = finalX; //set piece's new location
        piece.y = finalY;

        boardArray[finalX][finalY] = piece; //set array to new piece's position
        boardArray[originX][originY] = null; //set starting point to empty
    }


}
