package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

import java.util.*;
/**
 * Created by calvinmwhu on 2/10/15.
 */
public abstract class Board {
    protected Piece[][] pieces;
    protected HashMap<String, Piece> blackPlayer;
    protected HashMap<String, Piece> whitePlayer;
    protected boolean gameEnded;

    public Board(){
        pieces=null;
        blackPlayer=null;
        whitePlayer=null;
    }
    public boolean validRange(int rank, int file){
        return false;
    }
    public void movePiece(int fromRank, int fromFile, int toRank, int toFile){
        Piece piece;
        if(validRange(fromRank, fromFile) && validRange(toRank, toFile) && ((piece = getPieceAtLocation(fromRank, fromFile)) != null)){
            Piece toPiece = getPieceAtLocation(toRank,toFile);
            if(piece.getColor()!=toPiece.getColor()){
                
            }
        }
    }

    public Piece getPieceAtLocation(int rank, int file){
        if(validRange(rank,file)){
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
