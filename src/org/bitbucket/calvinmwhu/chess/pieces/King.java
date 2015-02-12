package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class King extends Piece {
    private static int id;

    public King(String color, int rank, int file) {
        super(rank, file);
        setName("King");
        setColor(color);
        setHashKey();
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;

        if (rank + 1 == rankDes && file == fileDes ||
                rank - 1 == rankDes && file == fileDes ||
                rank == rankDes && file - 1 == fileDes ||
                rank == rankDes && file + 1 == fileDes ||
                rank + 1 == rankDes && file + 1 == fileDes ||
                rank - 1 == rankDes && file - 1 == fileDes ||
                rank + 1 == rankDes && file - 1 == fileDes ||
                rank - 1 == rankDes && file + 1 == fileDes) {
            return true;
        }

        return false;
    }

}
