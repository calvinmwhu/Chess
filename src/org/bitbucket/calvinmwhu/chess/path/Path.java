package org.bitbucket.calvinmwhu.chess.path;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.ArrayList;

/**
 * Created by calvinmwhu on 2/18/15.
 */
public class Path {
    private final int rank;
    private final int file;
    private final Player player;
    private Board board;

    public Path(int rank, int file, Player player, Board board) {
        this.rank = rank;
        this.file = file;
        this.player = player;
        this.board = board;
    }


    public ArrayList<BoardTile> getPathInDirection(int rankOffset, int fileOffset) {
        ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();
        int rankPos = rank;
        int filePos = file;

        while (board.validRange(rankPos, filePos)) {
            BoardTile tile = board.getTileAtLocation(rankPos, filePos);
            if (tile.getPlayerAtTile() != player) {
                tiles.add(tile);
                if (tile.getPlayerAtTile() != Player.UNOCCUPIED) {
                    break;
                }
            } else if (tile.getRankPos() != rank || tile.getFilePos() != file) {
                break;
            }
            rankPos += rankOffset;
            filePos += fileOffset;
        }
        return tiles;
    }



}
