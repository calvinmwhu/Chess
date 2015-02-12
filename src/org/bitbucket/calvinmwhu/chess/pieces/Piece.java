package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
import java.util.ArrayList;

public abstract class Piece {
    protected Location location;
    protected String color;
    protected String name;
    protected String hashKey;

    public Piece(int rank, int file){
        location = new Location(rank, file);
    }

    public void setColor(String color){
        this.color=color;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setLocation(int rank, int file){
        location.setRankPos(file);
        location.setFilePos(file);
    }

    public void setHashKey(){
        hashKey = color+" "+name;
    }

    public String getColor(){
        return color;
    }
    public String getName(){
        return name;
    }

    public Location getLocation(){
        return location;
    }
    public String getHashKey(){
        return hashKey;
    }

    public String getId(){
        return "";
    }

    public ArrayList<Location> getNeighbours(){
        ArrayList<Location> neighbours = new ArrayList<Location>();
        int rank = location.getRankPos();
        int file = location.getFilePos();
        neighbours.add(new Location(rank+1,file));
        neighbours.add(new Location(rank-1,file));
        neighbours.add(new Location(rank,file+1));
        neighbours.add(new Location(rank,file-1));
        return neighbours;
    }

    public abstract boolean canMoveToLocation(Board board, Location location);

}
