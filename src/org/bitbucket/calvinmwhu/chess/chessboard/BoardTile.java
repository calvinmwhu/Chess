package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class BoardTile {
    private int rankPos;
    private int filePos;
    private Piece occupyingPiece;

    public BoardTile(int rank, int file) {
        rankPos = rank;
        filePos = file;
        occupyingPiece = null;
    }

    public int getRankPos() {
        return rankPos;
    }

    public int getFilePos() {
        return filePos;
    }

    public void setRankPos(int rank) {
        rankPos = rank;
    }

    public void setFilePos(int file) {
        filePos = file;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }
}
