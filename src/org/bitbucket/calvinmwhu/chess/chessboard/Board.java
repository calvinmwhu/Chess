package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

import java.util.*;
/**
 * Created by calvinmwhu on 2/10/15.
 */
public abstract class Board {
    protected Piece[][] pieces;
    protected String black = "Black";
    protected String white = "White";
    protected HashMap<String, Piece> blackPlayer;
    protected HashMap<String, Piece> whitePlayer;
    protected boolean gameEnded;

    public Board(){
        pieces=null;
        blackPlayer=null;
        whitePlayer=null;
    }

    public abstract boolean validRange(int rank, int file);
    public abstract boolean atPawnStartPosition(int rank);
    public abstract void setupBoard();

    public void movePiece(int fromRank, int fromFile, int toRank, int toFile){
        if(!validRange(fromRank, fromFile) || !validRange(toRank,toFile) || (fromRank==toRank && fromFile==toFile)){
            return;
        }
        Piece fromPiece=pieces[fromRank][fromFile];
        Piece toPiece=pieces[toRank][toFile];
        if(fromPiece!=null && (toPiece==null || (!fromPiece.getColor().equals(toPiece.getColor())) && fromPiece.canMoveToLocation(this,toRank, toFile)) ) {
            //move the piece and capture/kill the destination.
        }
    }

    public Piece getPieceAtLocation(int rank, int file){
        if(validRange(rank, file)){
            return pieces[rank][file];
        }
        return null;
    }

    public void setPiecesAtLocation(Piece piece, int rank, int file){
        if(validRange(rank, file)){
            pieces[rank][file]=piece;
        }
    }

    public boolean checkmated(){
        return false;
    }

}
