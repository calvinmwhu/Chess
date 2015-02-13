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
    protected boolean gameEnded = false;

    public Board() {
        pieces = null;
        blackPlayer = null;
        whitePlayer = null;
    }

    public abstract boolean validRange(int rank, int file);

    public abstract boolean atPawnStartPosition(int rank);

    public abstract void setupBoard();

    public String movePiece(int fromRank, int fromFile, int toRank, int toFile) {
        String result = "cannot move to location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
        if (!validRange(fromRank, fromFile) || !validRange(toRank, toFile) || (fromRank == toRank && fromFile == toFile)) {
            result = "Invalid range selected";
        } else {
            Piece fromPiece = pieces[fromRank][fromFile];
            Piece toPiece = pieces[toRank][toFile];
            if (fromPiece != null && (toPiece == null || (!fromPiece.getColor().equals(toPiece.getColor())) &&
                    fromPiece.canMoveToLocation(this, toRank, toFile))) {
                //move the piece to empty location or capture/kill the destination.
                if (toPiece != null) {
                    result = "capturing piece at location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
                    if (toPiece.getColor().equals(white)) {
                        whitePlayer.remove(toPiece.getHashKey());
                    } else {
                        blackPlayer.remove(toPiece.getHashKey());
                    }
                    setPiecesAtLocation(null, toRank, toFile);
                } else {
                    result = "move to empty location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
                }
                fromPiece.setRank(toRank);
                fromPiece.setFile(toFile);
                setPiecesAtLocation(fromPiece, toRank, toFile);
                setPiecesAtLocation(null, fromRank, fromFile);
            }
        }
        return result;
    }

    public Piece getPieceAtLocation(int rank, int file) {
        if (validRange(rank, file)) {
            return pieces[rank][file];
        }
        return null;
    }

    public void setPiecesAtLocation(Piece piece, int rank, int file) {
        if (validRange(rank, file)) {
            pieces[rank][file] = piece;
        }
    }

    public boolean attackerCanKillPieceAtLocation(String targetColor, int rank, int file) {
        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
        for (String key : attacker.keySet()) {
            Piece piece = attacker.get(key);
            if (piece.canMoveToLocation(this, rank, file)) {
                return true;
            }
        }
        return false;
    }

    public boolean checked(String targetColor){
        HashMap<String, Piece> target = targetColor.equals(white) ? whitePlayer : blackPlayer;
        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
        Piece king = target.get("King");
        for(String key : attacker.keySet()){
            Piece piece = attacker.get(key);
            if(piece.canMoveToLocation(this,king.getRank(),king.getFile())){
                return true;
            }
        }
        return false;
    }

    public boolean checkmated(String targetColor) {
        HashMap<String, Piece> target = targetColor.equals(white) ? whitePlayer : blackPlayer;
        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
        LinkedList<Square> neighbours = target.get("King").getNeighbours();

        //to do: check if target king is checkmated!!
        ListIterator<Square> it = neighbours.listIterator();
        while (it.hasNext()) {
            Square item = it.next();
            int itemRank = item.getRankPos();
            int itemFile = item.getFilePos();
            if (!validRange(itemRank, itemFile) || attackerCanKillPieceAtLocation(targetColor, itemRank, itemFile)) {
                it.remove();
            }
        }
        if (neighbours.isEmpty()) {
            gameEnded = true;
            return true;
        }
        return false;
    }

}
