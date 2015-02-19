package org.bitbucket.calvinmwhu.chess.values;

/**
 * Created by calvinmwhu on 2/18/15.
 */
public enum Player {
    WHITE("White"), BLACK("Black");

    private final String color;
    Player(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

}
