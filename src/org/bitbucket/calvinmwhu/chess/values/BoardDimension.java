package org.bitbucket.calvinmwhu.chess.values;

/**
 * Created by calvinmwhu on 2/20/15.
 */
public enum BoardDimension {
    SQUARE(8,8);

    private final int height;
    private final int width;

    BoardDimension(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
}
