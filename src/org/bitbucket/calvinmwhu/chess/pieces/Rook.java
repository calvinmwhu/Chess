package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Rook extends Piece {
    private static int id = 0;

    public Rook(String color, int rank, int file) {
        super(rank, file);
        setName("Rook");
        setColor(color);
        setHashKey();
        id++;
    }

    public void setHashKey() {
        hashKey = color + "_" + name + "_" + id;
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;
        if (rank - rankDes == 0 || file - fileDes == 0) {
            int rankStep = rank == rankDes ? 0 : (rankDes - rank) / (Math.abs(rankDes - rank));
            int fileStep = rank == fileDes ? 0 : (fileDes - file) / (Math.abs(fileDes - file));

            do {
                rank = rank + rankStep;
                file = rank + fileStep;
            } while ((rank != rankDes && file != fileDes) || board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }
        return false;
    }
}
