package org.bitbucket.calvinmwhu.chess.chessboard;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class Location {
    private int rankPos;
    private int filePos;
    public Location(int rank, int file){
        rankPos=rank;
        filePos=file;
    }
    int getRankPos(){
        return rankPos;
    }
    int getFilePos(){
        return filePos;
    }
    void setRankPos(int rank){
        rankPos=rank;
    }
    void setFilePos(int file){
        filePos=file;
    }
}
