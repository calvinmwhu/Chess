package org.bitbucket.calvinmwhu.chess.chessboard;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Square {
    private int rankPos;
    private int filePos;
    public Square(int rank, int file){
        rankPos=rank;
        filePos=file;
    }
    public int getRankPos(){
        return rankPos;
    }
    public int getFilePos(){
        return filePos;
    }
    public void setRankPos(int rank){
        rankPos=rank;
    }
    public void setFilePos(int file){
        filePos=file;
    }
}
