package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

import java.util.*;

/**
 * Created by calvinmwhu on 2/10/15.
 */
public abstract class Board {
    protected BoardTile[][] boardTiles;

    public Board() {
        boardTiles = null;
    }


    public abstract int getWidth();

    public abstract int getHeight();

    public abstract boolean validRange(int rank, int file);

    public abstract void putPiecesOnBoard(HashMap<String, Piece> whitePlayer, HashMap<String, Piece> blackPlayer);


    public abstract boolean pawnStartPosition(Piece piece);

    public BoardTile getTileAtLocation(int rank, int file) {
        return null;
    }
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


}
