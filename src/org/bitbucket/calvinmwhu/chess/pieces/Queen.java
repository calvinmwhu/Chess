package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Queen extends Piece {
    private static int id = 0;

    public Queen(String color, int rank, int file) {
        super(rank, file);
        setName("Queen");
        setColor(color);
        setHashKey();
    }

    public boolean canMoveToLocation(Board board, int rankDes, int fileDes) {
        int rank = this.rank;
        int file = this.file;
        if (Math.abs(rank - rankDes) == Math.abs(file - fileDes) || rank - rankDes == 0 || file - fileDes == 0) {
            int rankStep = rank == rankDes ? 0 : (rankDes - rank) / (Math.abs(rankDes - rank));
            int fileStep = file == fileDes ? 0 : (fileDes - file) / (Math.abs(fileDes - file));

            do {
                rank = rank + rankStep;
                file = file + fileStep;
//                System.out.println(rank+" "+file);
            } while ((rank != rankDes || file != fileDes) && board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }
        return false;
    }
}
