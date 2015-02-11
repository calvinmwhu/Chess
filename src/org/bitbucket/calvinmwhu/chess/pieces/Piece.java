package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
/**
 * Created by calvinmwhu on 2/10/15.
 */
public abstract class Piece {
    protected Location location;
    protected String color;
    public Piece(int rank, int file){
        location = new Location(rank, file);
    }
    public String getColor(){
        return color;
    }
}
