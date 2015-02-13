package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;

import java.util.LinkedList;

public abstract class Piece {
    protected int rank;
    protected int file;
    protected String color;
    protected String name;
    protected String hashKey;

    public Piece(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashKey() {
        hashKey = getName();
    }


    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setFile(int file) {
        this.file = file;
    }


    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public String getHashKey() {
        return hashKey;
    }

    public String getId() {
        return "";
    }

    public LinkedList<Square> getNeighbours() {
        LinkedList<Square> neighbours = new LinkedList<Square>();
        neighbours.add(new Square(rank, file));
        neighbours.add(new Square(rank + 1, file));
        neighbours.add(new Square(rank - 1, file));
        neighbours.add(new Square(rank, file + 1));
        neighbours.add(new Square(rank, file - 1));
        neighbours.add(new Square(rank - 1, file - 1));
        neighbours.add(new Square(rank + 1, file - 1));
        neighbours.add(new Square(rank - 1, file + 1));
        neighbours.add(new Square(rank + 1, file + 1));
        return neighbours;
    }

    public abstract boolean canMoveToLocation(Board board, int rankDes, int fileDes);
}
