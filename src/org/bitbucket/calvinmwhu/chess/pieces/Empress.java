package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.path.Path;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.ArrayList;

/**
 * Created by calvinmwhu on 2/20/15.
 */
public class Empress extends Piece {
    public Empress(Board board, Player player, int index) {
        super(board, player);
        this.index = index;
        this.name = PieceName.EMPRESS;
    }

    public void updateReachableTiles() {
        if (removedFromBoard()) return;
        reachableTiles.clear();
        int rank = getRank();
        int file = getFile();

        int[] rankJump = {-2, -1, 1, 2};
        int[] fileJump = {-2, -1, 1, 2};

        //move like a Knight
        for (int i = 0; i < rankJump.length; i++) {
            for (int j = 0; j < fileJump.length; j++) {
                if (Math.abs(rankJump[i]) != Math.abs(fileJump[j])) {
                    if (board.validRange(rank + rankJump[i], file + fileJump[j])) {
                        BoardTile tile = board.getTileAtLocation(rank + rankJump[i], file + fileJump[j]);
                        if (tile.getPlayerAtTile() != player) {
                            reachableTiles.add(tile);
                        }
                    }
                }
            }
        }

        //move like a Rook
        Path path = new Path(getRank(), getFile(), getPlayer(), board);
        ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();

        tiles.addAll(path.getPathInDirection(1, 0)); //go up
        tiles.addAll(path.getPathInDirection(-1, 0)); //go down
        tiles.addAll(path.getPathInDirection(0, 1)); //go right
        tiles.addAll(path.getPathInDirection(0, -1)); //go left
        reachableTiles.addAll(tiles);
    }

    public boolean canKillKingAtTile(BoardTile toTile) {
        if (removedFromBoard() || toTile == tileUnderPiece) return false;
        int rank = getRank();
        int file = getFile();
        int rankDes = toTile.getRankPos();
        int fileDes = toTile.getFilePos();

        int[] rankJump = {-2, -1, 1, 2};
        int[] fileJump = {-2, -1, 1, 2};

        for (int i = 0; i < rankJump.length; i++) {
            for (int j = 0; j < fileJump.length; j++) {
                if (Math.abs(rankJump[i]) != Math.abs(fileJump[j]) &&
                        rank + rankJump[i] == rankDes &&
                        file + fileJump[j] == fileDes) {
                    return true;
                }
            }
        }

        if (rank - rankDes == 0 || file - fileDes == 0) {
            int rankOffset = rank == rankDes ? 0 : (rankDes - rank) / (Math.abs(rankDes - rank));
            int fileOffset = file == fileDes ? 0 : (fileDes - file) / (Math.abs(fileDes - file));

            do {
                rank = rank + rankOffset;
                file = file + fileOffset;
            } while ((rank != rankDes || file != fileDes) && board.getPieceAtLocation(rank, file) == null);

            if (rank == rankDes && file == fileDes) {
                return true;
            }
        }

        return false;
    }
}
