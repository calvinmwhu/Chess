package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;

import java.util.*;

/**
 * Created by calvinmwhu on 2/10/15.
 */
public abstract class Board {
    protected BoardTile[][] boardTiles;

    public Board() {
        boardTiles = null;
    }


    public abstract int getWidth();
    public abstract int getHeight();
    public abstract boolean validRange(int rank, int file);
    public abstract boolean atPawnStartPosition(int rank);
    public abstract void setupBoard();

    public abstract void putPiecesOnBoard(HashMap<String, Piece> whitePlayer, HashMap<String, Piece> blackPlayer);


//
//    /**
//     *
//     * @param fromRank
//     * @param fromFile
//     * @param toRank
//     * @param toFile
//     * @return
//     */
//    public String movePiece(int fromRank, int fromFile, int toRank, int toFile) {
//        String result = "cannot move to location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
//        if (!validRange(fromRank, fromFile) || !validRange(toRank, toFile) || (fromRank == toRank && fromFile == toFile)) {
//            result = "Invalid range selected";
//        } else {
//            Piece fromPiece = pieces[fromRank][fromFile];
//            Piece toPiece = pieces[toRank][toFile];
//            if (fromPiece != null && (toPiece == null || (!fromPiece.getColor().equals(toPiece.getColor())) &&
//                    fromPiece.canMoveToLocation(this, toRank, toFile))) {
//                //move the piece to empty location or capture/kill the destination.
//                if (toPiece != null) {
//                    result = "capturing piece at location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
//                    if (toPiece.getColor().equals(white)) {
//                        whitePlayer.remove(toPiece.getHashKey());
//                    } else {
//                        blackPlayer.remove(toPiece.getHashKey());
//                    }
//                    setPiecesAtLocation(null, toRank, toFile);
//                } else {
//                    result = "move to empty location (" + String.valueOf(toRank) + "," + String.valueOf(toFile) + ")";
//                }
//                fromPiece.setRank(toRank);
//                fromPiece.setFile(toFile);
//                setPiecesAtLocation(fromPiece, toRank, toFile);
//                setPiecesAtLocation(null, fromRank, fromFile);
//            }
//        }
//        return result;
//    }
//
//    /**
//     *
//     * @param rank
//     * @param file
//     * @return
//     */
//    public Piece getPieceAtLocation(int rank, int file) {
//        if (validRange(rank, file)) {
//            return pieces[rank][file];
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param piece
//     * @param rank
//     * @param file
//     */
//    public void setPiecesAtLocation(Piece piece, int rank, int file) {
//        if (validRange(rank, file)) {
//            pieces[rank][file] = piece;
//        }
//    }
//
//    /**
//     *
//     * @param targetColor
//     * @param rank
//     * @param file
//     * @return
//     */
//    public boolean attackerCanKillPieceAtLocation(String targetColor, int rank, int file) {
//        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
//        for (String key : attacker.keySet()) {
//            Piece piece = attacker.get(key);
//            if (piece.canMoveToLocation(this, rank, file)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     *
//     * @param targetColor
//     * @return
//     */
//    public boolean checked(String targetColor){
//        HashMap<String, Piece> target = targetColor.equals(white) ? whitePlayer : blackPlayer;
//        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
//        Piece king = target.get("King");
//        for(String key : attacker.keySet()){
//            Piece piece = attacker.get(key);
//            if(piece.canMoveToLocation(this,king.getRank(),king.getFile())){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     *
//     * @param targetColor
//     * @return
//     */
//    public boolean checkmated(String targetColor) {
//        HashMap<String, Piece> target = targetColor.equals(white) ? whitePlayer : blackPlayer;
//        HashMap<String, Piece> attacker = targetColor.equals(black) ? whitePlayer : blackPlayer;
//        LinkedList<BoardTile> neighbours = target.get("King").getNeighbours();
//
//        //to do: check if target king is checkmated!!
//        ListIterator<BoardTile> it = neighbours.listIterator();
//        while (it.hasNext()) {
//            BoardTile item = it.next();
//            int itemRank = item.getRankPos();
//            int itemFile = item.getFilePos();
//            if (!validRange(itemRank, itemFile) || attackerCanKillPieceAtLocation(targetColor, itemRank, itemFile)) {
//                it.remove();
//            }
//        }
//        if (neighbours.isEmpty()) {
//            gameEnded = true;
//            return true;
//        }
//        return false;
//    }

}
