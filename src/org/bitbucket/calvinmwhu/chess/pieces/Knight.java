package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.Map;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Knight extends Piece {
    public Knight(Board board, Player player, int index) {
        super(board, player);
        this.index = index;
        this.name = PieceName.KNIGHT;
    }

    public void updateReachableTiles() {
        reachableTiles.clear();
        int rank = getRank();
        int file = getFile();

        int[] rankJump = {-2, -1, 1, 2};
        int[] fileJump = {-2, -1, 1, 2};

        for (int i = 0; i < rankJump.length; i++) {
            for (int j = 0; j < fileJump.length; j++) {
                if (Math.abs(rankJump[i]) != Math.abs(fileJump[j])) {
                    BoardTile tile = board.getTileAtLocation(rankJump[i], fileJump[j]);
                    if (tile.getPlayerAtTile() != player) {
                        reachableTiles.add(tile);
                    }
                }
            }
        }

    }

}
