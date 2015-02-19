package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashSet;
import java.util.LinkedList;

public abstract class Piece {
    protected BoardTile tileUnderPiece;
    protected int index;
    protected Player player;
    protected PieceName name;
    protected HashSet<BoardTile> reachableTiles;

    public Piece(Player player) {
        this.player = player;
        this.tileUnderPiece = null;
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

    public int getRank(){
        return tileUnderPiece.getRankPos();
    }

    public int getFile(){
        return tileUnderPiece.getFilePos();
    }

    protected void setTileUnderPiece(BoardTile tile) {
        tileUnderPiece = tile;
    }



    public LinkedList<BoardTile> getNeighbours() {
        LinkedList<BoardTile> neighbours = new LinkedList<BoardTile>();
        neighbours.add(new BoardTile(rank, file));
        neighbours.add(new BoardTile(rank + 1, file));
        neighbours.add(new BoardTile(rank - 1, file));
        neighbours.add(new BoardTile(rank, file + 1));
        neighbours.add(new BoardTile(rank, file - 1));
        neighbours.add(new BoardTile(rank - 1, file - 1));
        neighbours.add(new BoardTile(rank + 1, file - 1));
        neighbours.add(new BoardTile(rank - 1, file + 1));
        neighbours.add(new BoardTile(rank + 1, file + 1));
        return neighbours;
    }

    /**
     * @param board
     * @param rankDes
     * @param fileDes
     * @return
     */
    public abstract boolean canMoveToLocation(Board board, int rankDes, int fileDes);
}
