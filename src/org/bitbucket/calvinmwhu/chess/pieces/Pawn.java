package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Pawn extends Piece {

    public Pawn(Board board, Player player, int index) {
        super(board, player);
        this.index = index;
        this.name = PieceName.PAWN;
    }

    public String getIndex(){
        return String.valueOf(index);
    }

    public void updateReachableTiles() {
        if (removedFromBoard()) return;
        reachableTiles.clear();
        int rank = getRank();
        int file = getFile();
        int offset = player == Player.WHITE ? 1 : -1;

        //start position
        BoardTile tile1 = board.getTileAtLocation(rank + 1 * offset, file);
        BoardTile tile2 = board.getTileAtLocation(rank + 2 * offset, file);
        BoardTile tile3 = board.getTileAtLocation(rank + 1 * offset, file + 1 * offset);
        BoardTile tile4 = board.getTileAtLocation(rank + 1 * offset, file - 1 * offset);
        if (tile1 != null && tile1.getPlayerAtTile() == Player.UNOCCUPIED) {
            reachableTiles.add(tile1);
        }
        if (board.pawnStartPosition(this) && tile2 != null && tile2.getPlayerAtTile() == Player.UNOCCUPIED && tile1!=null && tile1.getPlayerAtTile() == Player.UNOCCUPIED) {
            reachableTiles.add(tile2);
        }
        if (tile3 != null && tile3.getPlayerAtTile() != player && tile3.getPlayerAtTile() != Player.UNOCCUPIED) {
            reachableTiles.add(tile3);
        }
        if (tile4 != null && tile4.getPlayerAtTile() != player && tile4.getPlayerAtTile() != Player.UNOCCUPIED) {
            reachableTiles.add(tile4);
        }

    }


    public boolean canKillKingAtTile(BoardTile toTile) {
        if (removedFromBoard()) return false;
        int rank = getRank();
        int file = getFile();
        int rankDes = toTile.getRankPos();
        int fileDes = toTile.getFilePos();
        int offset = player == Player.WHITE ? 1 : -1;

        BoardTile tile1 = board.getTileAtLocation(rank + 1 * offset, file + 1 * offset);
        BoardTile tile2 = board.getTileAtLocation(rank + 1 * offset, file - 1 * offset);

        return tile1 == toTile || tile2 == toTile;

    }

}
