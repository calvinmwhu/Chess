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

    public boolean canMoveToLocation(Board board, Location location) {
        int rank = this.location.getRankPos();
        int file = this.location.getFilePos();
        int rankdes = location.getRankPos();
        int filedes = location.getFilePos();

        if (rank+1 == rankdes && file==filedes || rank-1==rankdes && file==filedes || rank==rankdes && file - 1==filedes || rank==rankdes && file+1==filedes){
            return true;
        }

        return false;
    }


}
