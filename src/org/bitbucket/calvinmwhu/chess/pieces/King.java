package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;

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

    public boolean canMoveToLocation(Board board, int rankdes, int filedes) {
        int rank = this.rank;
        int file = this.file;

        if (rank + 1 == rankdes && file == filedes ||
                rank - 1 == rankdes && file == filedes ||
                rank == rankdes && file - 1 == filedes ||
                rank == rankdes && file + 1 == filedes) {
            return true;
        }

        return false;
    }


}
