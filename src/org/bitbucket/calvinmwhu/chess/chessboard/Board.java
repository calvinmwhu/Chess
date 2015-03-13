package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

import java.util.*;

/**
 * chessBoard base class for creating boards of various shape, i.e. square, rectangle, or polygon.
 */
public abstract class Board {
    /**
     * 2D array of boardTiles. A board object can directly reference to any tile given its location on the board
     */
    protected BoardTile[][] boardTiles;

    protected boolean customized;

    /**
     * constructor
     */
    public Board() {
        boardTiles = null;
    }

    /**
     * public getter
     * @return width of the board
     */
    public abstract int getWidth();

    /**
     * public getter
     * @return height of the board
     */
    public abstract int getHeight();

    /**
     * checks if a given location is within a valid range
     * @param rank
     * @param file
     * @return true if the location is in valid range, false if it is out of range
     */
    public abstract boolean validRange(int rank, int file);

    /**
     * Given all the pieces, put them on the board according to their initial configuration
     * @param whitePlayer
     * @param blackPlayer
     */
    public abstract void putPiecesOnBoard(HashMap<String, Piece> whitePlayer, HashMap<String, Piece> blackPlayer);

    /**
     * check if a pawn piece is at its starting position, i.e. at rank 1 for white player
     * @param piece
     * @return true if the pawn is at starting position, false otherwise
     */
    public abstract boolean pawnStartPosition(Piece piece);

    /**
     * public getter
     * @param rank
     * @param file
     * @return a BoardTile object at location (rank,file)
     */
    public BoardTile getTileAtLocation(int rank, int file) {
        return null;
    }

    /**
     *
     * @param rank
     * @param file
     * @return
     */
    public Piece getPieceAtLocation(int rank, int file){return null;}

    public boolean canKillKingAtPosition(HashMap<String, Piece> attackerPieces, BoardTile tile){
        for(Piece piece : attackerPieces.values()){
            if(piece.getReachableTiles().contains(tile)){
                return true;
            }
        }
        return false;
    }

    public void removeAllPiecesOnBoard(HashMap<String, Piece> whitePlayer, HashMap<String, Piece> blackPlayer){
        for(Piece piece : whitePlayer.values()){
            piece.setTileUnderPiece(null);
        }
        for(Piece piece : blackPlayer.values()){
            piece.setTileUnderPiece(null);
        }
    }


    public boolean getCustomized(){
        return customized;
    }

    public void setCustomized(boolean b){
        customized=b;
    }


}
