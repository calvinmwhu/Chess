package org.bitbucket.calvinmwhu.chess.chessboard;


import org.bitbucket.calvinmwhu.chess.pieces.*;

import java.util.HashMap;

/**
 * Created by calvinmwhu on 2/10/15.
 */
public class SquareBoard extends Board {
    private static final int WIDTH=8;
    private static final int HEIGHT=8;

    private int width;
    private int height;
    public SquareBoard(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void movePiece(int fromRank, int fromFile, int toRank, int toFile){
        return;
    }

}
