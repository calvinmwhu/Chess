package org.bitbucket.calvinmwhu.chess.values;

/**
 * Created by calvinmwhu on 2/18/15.
 */
public enum BoardShape {
    SQUARE("Square"), RECTANGLE("Rectangle");

    private final String shape;
    BoardShape(String shape){
        this.shape=shape;
    }

    public String getShape(){
        return this.shape;
    }

}
