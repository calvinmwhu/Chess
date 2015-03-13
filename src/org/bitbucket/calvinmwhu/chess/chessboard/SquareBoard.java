package org.bitbucket.calvinmwhu.chess.chessboard;


import org.bitbucket.calvinmwhu.chess.pieces.*;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashMap;

/**
 * Created by calvinmwhu on 2/10/15.
 */
public class SquareBoard extends Board {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SquareBoard() {
        this.width = WIDTH;
        this.height = HEIGHT;
        boardTiles = new BoardTile[this.height][this.width];
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                boardTiles[rank][file] = new BoardTile(rank, file, this);
            }
        }
    }

    public void putPiecesOnBoard(HashMap<String, Piece> whitePlayer, HashMap<String, Piece> blackPlayer) {
        //put kings:
        whitePlayer.get(PieceName.KING.getName()).setTileUnderPiece(boardTiles[0][4]);
        blackPlayer.get(PieceName.KING.getName()).setTileUnderPiece(boardTiles[7][4]);
        //put queens
        whitePlayer.get(PieceName.QUEEN.getName()).setTileUnderPiece(boardTiles[0][3]);
        blackPlayer.get(PieceName.QUEEN.getName()).setTileUnderPiece(boardTiles[7][3]);

        //put Knight
        whitePlayer.get(PieceName.KNIGHT.getName() + "0").setTileUnderPiece(boardTiles[0][1]);
        whitePlayer.get(PieceName.KNIGHT.getName() + "1").setTileUnderPiece(boardTiles[0][6]);
        blackPlayer.get(PieceName.KNIGHT.getName() + "0").setTileUnderPiece(boardTiles[7][1]);
        blackPlayer.get(PieceName.KNIGHT.getName() + "1").setTileUnderPiece(boardTiles[7][6]);


        if (!customized) {
            //put Bishop
            whitePlayer.get(PieceName.BISHOP.getName() + "0").setTileUnderPiece(boardTiles[0][2]);
            whitePlayer.get(PieceName.BISHOP.getName() + "1").setTileUnderPiece(boardTiles[0][5]);
            blackPlayer.get(PieceName.BISHOP.getName() + "0").setTileUnderPiece(boardTiles[7][2]);
            blackPlayer.get(PieceName.BISHOP.getName() + "1").setTileUnderPiece(boardTiles[7][5]);


            //put rook
            whitePlayer.get(PieceName.ROOK.getName() + "0").setTileUnderPiece(boardTiles[0][0]);
            whitePlayer.get(PieceName.ROOK.getName() + "1").setTileUnderPiece(boardTiles[0][7]);
            blackPlayer.get(PieceName.ROOK.getName() + "0").setTileUnderPiece(boardTiles[7][0]);
            blackPlayer.get(PieceName.ROOK.getName() + "1").setTileUnderPiece(boardTiles[7][7]);

        } else {
            //put empress
            whitePlayer.get(PieceName.EMPRESS.getName() + "0").setTileUnderPiece(boardTiles[0][0]);
            whitePlayer.get(PieceName.EMPRESS.getName() + "1").setTileUnderPiece(boardTiles[0][7]);
            blackPlayer.get(PieceName.EMPRESS.getName() + "0").setTileUnderPiece(boardTiles[7][0]);
            blackPlayer.get(PieceName.EMPRESS.getName() + "1").setTileUnderPiece(boardTiles[7][7]);

            //put princess
            whitePlayer.get(PieceName.PRINCESS.getName() + "0").setTileUnderPiece(boardTiles[0][2]);
            whitePlayer.get(PieceName.PRINCESS.getName() + "1").setTileUnderPiece(boardTiles[0][5]);
            blackPlayer.get(PieceName.PRINCESS.getName() + "0").setTileUnderPiece(boardTiles[7][2]);
            blackPlayer.get(PieceName.PRINCESS.getName() + "1").setTileUnderPiece(boardTiles[7][5]);
        }

        //put pawn
        for (int i = 0; i < 8; i++) {
            whitePlayer.get(PieceName.PAWN.getName() + String.valueOf(i)).setTileUnderPiece(boardTiles[1][i]);
            blackPlayer.get(PieceName.PAWN.getName() + String.valueOf(i)).setTileUnderPiece(boardTiles[6][i]);
        }

    }

    public BoardTile getTileAtLocation(int rank, int file) {
        if (validRange(rank, file)) {
            return boardTiles[rank][file];
        }
        return null;
    }

    public Piece getPieceAtLocation(int rank, int file) {
        BoardTile tile = getTileAtLocation(rank, file);
        return (tile == null) ? null : tile.getOccupyingPiece();
    }


    public boolean validRange(int rank, int file) {
        return rank >= 0 && rank < height && file >= 0 && file < width;
    }

    public boolean pawnStartPosition(Piece piece) {
        return (piece.getPlayer() == Player.WHITE && piece.getRank() == 1) || (piece.getPlayer() == Player.BLACK && piece.getRank() == 6);
    }
}
