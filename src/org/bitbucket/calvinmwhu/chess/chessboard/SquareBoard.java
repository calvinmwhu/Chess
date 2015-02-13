package org.bitbucket.calvinmwhu.chess.chessboard;


import org.bitbucket.calvinmwhu.chess.pieces.*;

import java.util.HashMap;

/**
 * Created by calvinmwhu on 2/10/15.
 */
public class SquareBoard extends Board {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    private int width;
    private int height;

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public SquareBoard() {
        this.width = WIDTH;
        this.height = WIDTH;
        pieces = new Piece[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                pieces[i][j] = null;
            }
        }
        whitePlayer = new HashMap<String, Piece>();
        blackPlayer = new HashMap<String, Piece>();
    }

    public void setupBoard() {
        pieces[0][0] = new Rook(white, 0, 0);
        pieces[0][7] = new Rook(white, 0, 7);
        pieces[7][0] = new Rook(black, 7, 0);
        pieces[7][7] = new Rook(black, 7, 7);

        pieces[0][1] = new Knight(white, 0, 1);
        pieces[0][6] = new Knight(white, 0, 6);
        pieces[7][1] = new Knight(black, 7, 1);
        pieces[7][6] = new Knight(black, 7, 6);

        pieces[0][2] = new Bishop(white, 0, 2);
        pieces[0][5] = new Bishop(white, 0, 5);
        pieces[7][2] = new Bishop(black, 7, 2);
        pieces[7][5] = new Bishop(black, 7, 5);

        pieces[0][3] = new Queen(white, 0, 3);
        pieces[7][3] = new Queen(black, 7, 3);

        pieces[0][4] = new King(white, 0, 4);
        pieces[7][4] = new King(black, 7, 4);

        for (int i = 0; i < this.width; i++) {
            pieces[1][i] = new Pawn(white, 1, i);
            pieces[6][i] = new Pawn(black, 6, i);
        }

        for (int i = 0; i < this.width; i++) {
            whitePlayer.put(pieces[0][i].getHashKey(), pieces[0][i]);
            whitePlayer.put(pieces[1][i].getHashKey(), pieces[1][i]);

            blackPlayer.put(pieces[7][i].getHashKey(), pieces[7][i]);
            blackPlayer.put(pieces[6][i].getHashKey(), pieces[6][i]);
        }

//        for(String key : whitePlayer.keySet()){
//            System.out.println(whitePlayer.get(key).getColor()+"_"+whitePlayer.get(key).getHashKey());
//        }
//
//        for(String key : blackPlayer.keySet()){
//            System.out.println(blackPlayer.get(key).getColor()+"_"+blackPlayer.get(key).getHashKey());
//        }

    }

    public boolean validRange(int rank, int file) {
        return rank >= 0 && rank < height && file >= 0 && file < width;
    }

    public boolean atPawnStartPosition(int rank) {
        return rank == 1 || rank == 6;
    }


}
