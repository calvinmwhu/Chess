package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Pawn extends Piece {

    public Pawn(Player player, int index) {
        super(player);
        this.index = index;
        this.name = PieceName.PAWN;
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
