package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;


/**
 * Created by calvinmwhu on 2/11/15.
 */
public class King extends Piece {

    public King(Player player) {
        super(player);
        this.name = PieceName.KING;
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
