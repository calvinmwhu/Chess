package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashSet;

public abstract class Piece {
    protected Board board;
    protected BoardTile tileUnderPiece;
    protected int index;
    protected Player player;
    protected PieceName name;
    protected HashSet<BoardTile> reachableTiles;


    public Piece(Board board, Player player) {
        this.player = player;
        this.tileUnderPiece = null;
        this.board = board;
        reachableTiles = new HashSet<BoardTile>();
    }

    public PieceName getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Player getPlayer() {
        return player;
    }

    public BoardTile getTileUnderPiece() {
        return tileUnderPiece;
    }

    public int getRank() {
        if (tileUnderPiece == null) {
            return -1;
        }
        return tileUnderPiece.getRankPos();
    }

    public int getFile() {
        if (tileUnderPiece == null) {
            return -1;
        }
        return tileUnderPiece.getFilePos();
    }

    /**
     * get the tiles the piece can move to
     * @return
     */
    public HashSet<BoardTile> getReachableTiles() {
        return reachableTiles;
    }

    /**
     * put a piece at tile
     * @param tile
     */
    public void setTileUnderPiece(BoardTile tile) {
//        if (tile != tileUnderPiece) {
        if (tileUnderPiece != null) {
            tileUnderPiece.setOccupyingPiece(null);
        }
        if (tile != tileUnderPiece) {
            tileUnderPiece = tile;
        }
        if (tile != null) {
            tile.setOccupyingPiece(this);
        }
//        }
    }

    /**
     * run each game iteration, updating the possible tiles the piece can move to
     */
    public abstract void updateReachableTiles();

    /**
     * replace the piece target
     * @param target
     */
    public void killPiece(Piece target) {
        target.setTileUnderPiece(null);
        System.out.println(target.getPlayer().getColor() + target.getName().getName() + " killed by " + player.getColor() + name.getName());
    }

    /**
     * move a piece to tile at location (rank,file)
     * @param rank
     * @param file
     * @return true if the move if successful
     */
    public boolean moveToPosition(int rank, int file) {
        BoardTile toTile = board.getTileAtLocation(rank, file);
        if (reachableTiles.contains(toTile)) {
            if (toTile.getPlayerAtTile() != Player.UNOCCUPIED) {
                //kill piece
                killPiece(toTile.getOccupyingPiece());
            } else {
                System.out.println(player.getColor() + name.getName() + "moves to " + toTile);
            }
            setTileUnderPiece(toTile);
            return true;
        }
        return false;
    }

    /**
     * useful for checking if the king is checkmated
     * @return set of neighbours tiles
     */
    public HashSet<BoardTile> neighbours() {
        return null;
    }

    /**
     * check if a piece has been killed
     * @return true if it is killed
     */
    public boolean removedFromBoard() {
        return tileUnderPiece == null;
    }

    /**
     * check if the piece can move to a given tile
     * @param toTile
     * @return true if the tile is reachable from the piece
     */
    public abstract boolean canKillKingAtTile(BoardTile toTile);
}
