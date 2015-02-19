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
public class Queen extends Piece {

    public Queen(Board board, Player player) {
        super(board, player);
        this.name = PieceName.QUEEN;
    }

    public void updateReachableTiles() {
        reachableTiles.clear();
        Path path = new Path(getRank(), getFile(), getPlayer(), board);
        ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();


        tiles.addAll(path.getPathInDirection(1, 0)); //go up
        tiles.addAll(path.getPathInDirection(-1, 0)); //go down
        tiles.addAll(path.getPathInDirection(0, 1)); //go right
        tiles.addAll(path.getPathInDirection(0, -1)); //go left

        tiles.addAll(path.getPathInDirection(1, 1)); //go upright
        tiles.addAll(path.getPathInDirection(1, -1)); //go upleft
        tiles.addAll(path.getPathInDirection(-1, 1)); //go downright
        tiles.addAll(path.getPathInDirection(-1, -1)); //go downleft
        reachableTiles.addAll(tiles);
    }

}
