package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Pawn extends Piece {
    private static int id = 0;

    public Pawn(String color, int rank, int file) {
        super(rank, file);
        setName("Pawn");
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

        if (board.getPieceAtLocation(rankDes, fileDes) == null) {
            if (fileDes != file) {
                return false;
            }
            if (Math.abs(rankDes - rank) == 1 || (Math.abs(rankDes - rank) == 2 && board.atPawnStartPosition(rank))) {
                return true;
            }
        } else {
            if (Math.abs(fileDes - file) == 1 && Math.abs(rankDes - rank) == 1) {
                return true;
            }
        }
        return false;
    }
}
