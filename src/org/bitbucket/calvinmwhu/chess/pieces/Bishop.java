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

    public void updateReachableTiles() {
        reachableTiles.clear();
        Path path = new Path(getRank(), getFile(), getPlayer(), board);
        ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();

        tiles.addAll(path.getPathInDirection(1, 1)); //go upright
        tiles.addAll(path.getPathInDirection(1, -1)); //go upleft
        tiles.addAll(path.getPathInDirection(-1, 1)); //go downright
        tiles.addAll(path.getPathInDirection(-1, -1)); //go downleft
        reachableTiles.addAll(tiles);
    }

}
