package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashSet;
import java.util.LinkedList;

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
        return tileUnderPiece.getRankPos();
    }

    public int getFile() {
        return tileUnderPiece.getFilePos();
    }

    public HashSet<BoardTile> getReachableTiles() {
        return reachableTiles;
    }

    public void setTileUnderPiece(BoardTile tile) {
        tileUnderPiece = tile;
        if (tile != null) {
            tile.setOccupyingPiece(this);
        }
    }

//
//    public LinkedList<BoardTile> getNeighbours() {
//        LinkedList<BoardTile> neighbours = new LinkedList<BoardTile>();
//        neighbours.add(new BoardTile(rank, file));
//        neighbours.add(new BoardTile(rank + 1, file));
//        neighbours.add(new BoardTile(rank - 1, file));
//        neighbours.add(new BoardTile(rank, file + 1));
//        neighbours.add(new BoardTile(rank, file - 1));
//        neighbours.add(new BoardTile(rank - 1, file - 1));
//        neighbours.add(new BoardTile(rank + 1, file - 1));
//        neighbours.add(new BoardTile(rank - 1, file + 1));
//        neighbours.add(new BoardTile(rank + 1, file + 1));
//        return neighbours;
//    }

    //    @Override
//    public String toString() {
//        return player.getColor()+name.getName()+
//    }
//
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
            }else{
                System.out.println(player.getColor() + name.getName()+"moves to "+ toTile);
            }
            tileUnderPiece.setOccupyingPiece(null);
            setTileUnderPiece(toTile);
            return true;
        }
        return false;
    }

}
