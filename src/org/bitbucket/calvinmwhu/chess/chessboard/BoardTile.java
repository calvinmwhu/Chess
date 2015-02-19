package org.bitbucket.calvinmwhu.chess.chessboard;

import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

/**
 * Created by calvinmwhu on 2/11/15.
 */
public class BoardTile {
    private int rankPos;
    private int filePos;
    private Piece occupyingPiece;

    public BoardTile(int rank, int file) {
        rankPos = rank;
        filePos = file;
        occupyingPiece = null;
    }

    public int getRankPos() {
        return rankPos;
    }

    public int getFilePos() {
        return filePos;
    }

    public void setRankPos(int rank) {
        rankPos = rank;
    }

    public void setFilePos(int file) {
        filePos = file;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setOccupyingPiece(Piece piece) {
        occupyingPiece = piece;
    }

    public Player getPlayerAtTile() {
        if (occupyingPiece != null) {
            return occupyingPiece.getPlayer();
        }
        return Player.UNOCCUPIED;
    }

    public PieceName getPieceNameAtTile(){
        if (occupyingPiece != null) {
            return occupyingPiece.getName();
        }
        return null;
    }

    @Override
    public String toString() {
        if (occupyingPiece != null)
            return "(" + rankPos + "," + filePos + "): " + occupyingPiece.getPlayer().getColor() + occupyingPiece.getName().getName();
        else
            return "(" + rankPos + "," + filePos + "): EMPTY";
    }


}
