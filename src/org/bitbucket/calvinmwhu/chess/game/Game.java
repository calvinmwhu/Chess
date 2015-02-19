package org.bitbucket.calvinmwhu.chess.game;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.pieces.*;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashMap;

/**
 * Created by calvinmwhu on 2/18/15.
 */
public class Game {
    private Board chessBoard;
    private HashMap<String, Piece> whitePlayer;
    private HashMap<String, Piece> blackPlayer;
    private Player turn;

    public Game(BoardShape shape) {
        turn = Player.WHITE;
    }

    public setUpBoardAndPieces(BoardShape shape) {
        if (shape == BoardShape.SQUARE) {
            chessBoard = new SquareBoard();
        } else {
            System.out.println("other board shape not implemented yet!");
        }
        setUpPieces();
        chessBoard.putPiecesOnBoard(whitePlayer, blackPlayer);
    }

    void setUpPieces() {
        whitePlayer = new HashMap<String, Piece>();
        blackPlayer = new HashMap<String, Piece>();
        for (PieceName pieceName : PieceName.values()) {
            if (pieceName == PieceName.PAWN) {
                for (int i = 0; i < 8; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(Player.BLACK, i));
                }
            } else if (pieceName == PieceName.BISHOP) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(Player.BLACK, i));
                }
            } else if (pieceName == PieceName.ROOK) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Rook(Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Rook(Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KNIGHT) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Knight(Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Knight(Player.BLACK, i));
                }
            } else {
                whitePlayer.put(pieceName.getName(), new King(Player.WHITE));
                blackPlayer.put(pieceName.getName(), new King(Player.BLACK));
            }
        }
    }


    public void movePiece(int fromRank, int fromFile, int toRank, int toFile) {

    }


}
