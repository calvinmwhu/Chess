package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Knight extends Piece {
    public Knight(Player player, int index) {
        super(player);
        this.index = index;
        this.name = PieceName.KNIGHT
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
