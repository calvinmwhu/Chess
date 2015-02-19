package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Bishop extends Piece {
    public Bishop(Player player, int index) {
        super(player);
        this.index = index;
        this.name = PieceName.BISHOP;
    }

    public void setHashKey() {
        hashKey = name + "_" + id;
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;
        if (Math.abs(rank - rankDes) == Math.abs(file - fileDes)) {
            do {
                rank = rank + (rank < rankDes ? 1 : -1);
                file = file + (file < fileDes ? 1 : -1);
            } while ((rank != rankDes || file != fileDes) && board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }

        return false;
    }

}
