package org.bitbucket.calvinmwhu.chess.values;

/**
 * Created by calvinmwhu on 2/18/15.
 */
public enum PieceName {
    KING("King"),QUEEN("Queen"),BISHOP("Bishop"),ROOK("Rook"),PAWN("Pawn"),KNIGHT("Knight");

    private final String name;
    PieceName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
