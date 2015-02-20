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

    public HashSet<BoardTile> getReachableTiles() {
        return reachableTiles;
    }

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

    public abstract void updateReachableTiles();

    public void killPiece(Piece target) {
        target.setTileUnderPiece(null);
        System.out.println(target.getPlayer().getColor() + target.getName().getName() + " killed by " + player.getColor() + name.getName());
    }

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

    public HashSet<BoardTile> neighbours() {
        return null;
    }

    public boolean removedFromBoard() {
        return tileUnderPiece == null;
    }

    public abstract boolean canKillKingAtTile(BoardTile toTile);
//    public abstract tryMovingToPosition(int rank, int file);
}
