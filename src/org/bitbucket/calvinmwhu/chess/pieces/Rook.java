package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Rook extends Piece {
    public Rook(Player player, int index) {
        super(player);
        this.index = index;
        this.name = PieceName.ROOK;
    }

    public void setHashKey() {
        hashKey = name + "_" + id;
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;
        if (rank - rankDes == 0 || file - fileDes == 0) {
            int rankStep = rank == rankDes ? 0 : (rankDes - rank) / (Math.abs(rankDes - rank));
            int fileStep = file == fileDes ? 0 : (fileDes - file) / (Math.abs(fileDes - file));

            do {
                rank = rank + rankStep;
                file = file + fileStep;
            } while ((rank != rankDes || file != fileDes) && board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }
        return false;
    }
}
