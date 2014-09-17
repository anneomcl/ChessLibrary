/**
 * Created by Anne on 9/11/2014.
 */
public class Board {

    int boardHeight, boardWidth;
    Piece[][] boardArray;
    Game game; //Boards can access information about players, pieces and turns.

    final static int KING = 1, ROOK = 2, BISHOP = 3, QUEEN = 4, KNIGHT = 5, PAWN = 6;

    /**
     * The constructor for a board.
     * @param boardHeight the height of the board
     * @param boardWidth the width of the board
     * @param game the game associated with this board
     */
    public Board(int boardHeight, int boardWidth, Game game)
    {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        boardArray = new Piece[boardHeight][boardWidth];
        this.game = game;
    }

    /**
     * A function to move a piece. It checks to see if the move is valid for any piece, then it checks if
     * that move is valid based on the piece's specific type. It handles capturing, then sets the new location.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     */
    public void movePiece(Piece piece, int end_X, int end_Y)
    {
        if(isValidMove(piece, end_X, end_Y) && piece.isValidPath(end_X, end_Y))
        {
            if(isCapture(piece, end_X, end_Y))
                boardArray[end_X][end_Y] = null;

            setNewPieceLocation(piece, end_X, end_Y);
        }

        else
        {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return;
    }

    /**
     * A function that determines whether a move is valid for any piece based on the following: 1) the move is within
     * the board's bounds, 2) the move does not involve invalid leaping, 3) the move is not the same as the
     * start location, and 4) the move coordinates do not land on an ally piece.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the move is valid
     */
    public boolean isValidMove(Piece piece, int end_X, int end_Y)
    {
        int [][] path = piece.drawPath(piece.location_X, piece.location_Y, end_X, end_Y);

        if(isWithinBounds(end_X,end_Y)
                && (validLeaping(piece, path)
                && (isNotOrigin(piece, end_X, end_Y))
                && (isValidEndPoint(piece, end_X, end_Y))))
        {
            return true;
        }

        return false;
    }

    /**
     * A function determining if coordinates are in bounds of the board.
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return  a boolean indicating whether the coordinates are in bounds
     */
    protected boolean isWithinBounds(int end_X, int end_Y)
    {
        if((0 <= end_X && end_X < boardWidth) && (0 <= end_Y && end_Y < boardHeight))
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
        if(piece.getType() == KNIGHT) //knights can leap
            return true;

        if(piece.getType() == PAWN //pawns only have a path under special circumstances
                || piece.getType() == KING) //kings will never have a path
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
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether the end coordinates are the same as the start coordinates
     */
    protected boolean isNotOrigin(Piece piece, int end_X, int end_Y)
    {
        if((piece.location_X != end_X) || (piece.location_Y != end_Y))
            return true;

        else
        {
            return false;
        }

    }

    /**
     * A function determining if a move lands on an allied piece.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return  a boolean indicating whether the end coordinates are valid
     */
    protected boolean isValidEndPoint(Piece piece, int end_X, int end_Y)
    {
        if((boardArray[end_X][end_Y] == null)||((boardArray[end_X][end_Y] != null)
                && boardArray[end_X][end_Y].player.playerNumber != piece.player.playerNumber))
        {
            return true;
        }

        return false;
    }

    /**
     * A function determining if the move results in a capture.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     * @return a boolean indicating whether a capture will occur
     */
    protected boolean isCapture(Piece piece, int end_X, int end_Y)
    {

        if(boardArray[end_X][end_Y]!= null && boardArray[end_X][end_Y].player != piece.player)
        {
            System.out.println("Piece captured!");
            return true;
        }

        else
            return false;
    }

    /**
     * A function that transfers a piece to a new location, and clears the board space at
     * its previous location.
     * @param piece the piece to move
     * @param end_X the final X location
     * @param end_Y the final Y location
     */
    public void setNewPieceLocation(Piece piece, int end_X, int end_Y)
    {
        int origin_X = piece.location_X;
        int origin_Y = piece.location_Y;

        piece.location_X = end_X; //set piece's new location
        piece.location_Y = end_Y;

        boardArray[end_X][end_Y] = piece; //set array to new piece's position
        boardArray[origin_X][origin_Y] = null; //set starting point to empty
    }


}
