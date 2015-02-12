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

    public boolean validRange(Location location){
        return false;
    }

    public void movePiece(Location origin, Location destination){
        Piece fromPiece=getPieceAtLocation(origin);
        Piece toPiece=getPieceAtLocation(destination);
        if(fromPiece!=null && fromPiece.canMoveToLocation(this,destination)) {
            if(toPiece!=null){
                //topiece got captured.. print sth here, and remove it
            }
        }
    }

    public Piece getPieceAtLocation(Location location){
        if(validRange(location)){
            return pieces[location.getRankPos()][location.getFilePos()];
        }
        return null;
    }

    public void setPiecesAtLocation(Piece piece, Location location){
        if(validRange(location)){
            pieces[location.getRankPos()][location.getFilePos()]=piece;
        }
    }

    public boolean checkmated(){
        return false;
    }
}
