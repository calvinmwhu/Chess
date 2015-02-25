package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.path.Path;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.ArrayList;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Bishop extends Piece {
    public Bishop(Board board, Player player, int index) {
        super(board, player);
        this.index = index;
        this.name = PieceName.BISHOP;
    }

    public String getIndex() {
        return String.valueOf(index);
    }

    public void updateReachableTiles() {
        if (removedFromBoard()) return;
        reachableTiles.clear();
        Path path = new Path(getRank(), getFile(), getPlayer(), board);
        ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();

        tiles.addAll(path.getPathInDirection(1, 1)); //go upright
        tiles.addAll(path.getPathInDirection(1, -1)); //go upleft
        tiles.addAll(path.getPathInDirection(-1, 1)); //go downright
        tiles.addAll(path.getPathInDirection(-1, -1)); //go downleft
        reachableTiles.addAll(tiles);
    }

    public boolean canKillKingAtTile(BoardTile toTile) {
        if (removedFromBoard() || toTile == tileUnderPiece) return false;
        int rank = getRank();
        int file = getFile();
        int rankDes = toTile.getRankPos();
        int fileDes = toTile.getFilePos();

        if (Math.abs(rank - rankDes) == Math.abs(file - fileDes)) {
            int rankOffset = (rank < rankDes ? 1 : -1);
            int fileOffset = (file < fileDes ? 1 : -1);
            do {
                rank += rankOffset;
                file += fileOffset;
            } while ((rank != rankDes || file != fileDes) && board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }
        return false;
    }
}
