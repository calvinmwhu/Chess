package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Knight extends Piece {
    private static int id = 0;

    public Knight(String color, int rank, int file) {
        super(rank, file);
        setName("Knight");
        setColor(color);
        setHashKey();
        id++;
    }

    public void setHashKey() {
        hashKey = name + "_" + id;
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;

        if (rank + 1 == rankDes && file + 2 == fileDes ||
                rank - 1 == rankDes && file + 2 == fileDes ||
                rank + 2 == rankDes && file + 1 == fileDes ||
                rank - 2 == rankDes && file + 1 == fileDes ||
                rank + 2 == rankDes && file - 1 == fileDes ||
                rank - 2 == rankDes && file - 1 == fileDes ||
                rank + 1 == rankDes && file - 2 == fileDes ||
                rank - 1 == rankDes && file - 2 == fileDes) {
            return true;
        }

        return false;
    }
}
