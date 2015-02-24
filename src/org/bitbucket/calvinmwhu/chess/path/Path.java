package org.bitbucket.calvinmwhu.chess.path;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.ArrayList;

/**
 * This class is used for collecting the reachable tiles along a given direction for a piece whose initial position is at (rank,file)
 */
public class Path {
    private final int rank;
    private final int file;
    private final Player player;
    private Board board;

    /**
     *
     * @param rank the piece's current rank position
     * @param file the piece's current file position
     * @param player the player the piece belongs to
     * @param board a reference to the chess board
     */
    public Path(int rank, int file, Player player, Board board) {
        this.rank = rank;
        this.file = file;
        this.player = player;
        this.board = board;
    }

    /**
     *  Given a direction defined by (rankOffSet, fileOffset), each time the location is updated to (rank+rankOffset, file+fileOffset)
     * @param rankOffset specifies how the rank changes in direction, takes values -1, 0, or 1
     * @param fileOffset specifies how the file changes in direction, takes values -1, 0, or 1
     * @return
     */
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
