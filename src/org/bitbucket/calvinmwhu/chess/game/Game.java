package org.bitbucket.calvinmwhu.chess.game;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.pieces.*;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Game backend model, for storing chessBoard, pieces, and players data.
 */
public class Game {
    private Board chessBoard;
    private HashMap<String, Piece> whitePlayer;
    private HashMap<String, Piece> blackPlayer;
    private Player turn;

    /**
     * constructor, creates an empty game, gives the turn to white player
     */
    public Game() {
        turn = Player.WHITE;
    }

    /**
     * sets up a chessboard and pieces according the specified shape. But not connecting pieces to chessboard
     *
     * @param shape
     */
    public void initContent(BoardShape shape) {
        if (shape == BoardShape.SQUARE) {
            chessBoard = new SquareBoard();
        } else {
            System.out.println("other board shape not implemented yet!");
        }
        setUpPieces();
    }

    /**
     * sets up pieces and the chessboard, then puts pieces on board
     *
     * @param shape
     */
    public void setUpBoardAndPieces(BoardShape shape) {
        initContent(shape);
        chessBoard.putPiecesOnBoard(whitePlayer, blackPlayer);
    }

    /**
     * creates 16 normal pieces and stores them into the player hashmaps
     */
    void setUpPieces() {
        whitePlayer = new HashMap<String, Piece>();
        blackPlayer = new HashMap<String, Piece>();
        for (PieceName pieceName : PieceName.values()) {
            if (pieceName == PieceName.PAWN) {
                for (int i = 0; i < 8; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.BISHOP) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.ROOK) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Rook(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Rook(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KNIGHT) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KING) {
                whitePlayer.put(pieceName.getName(), new King(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new King(chessBoard, Player.BLACK));
            } else {
                whitePlayer.put(pieceName.getName(), new Queen(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new Queen(chessBoard, Player.BLACK));
            }
        }
    }

    /**
     * return the game's chessBoard
     *
     * @return a reference to a ChessBoard object
     */
    public Board getChessBoard() {
        return chessBoard;
    }

    /**
     * @param player
     * @return player of either white or black color
     */
    public HashMap<String, Piece> getPlayers(Player player) {
        return player == Player.WHITE ? whitePlayer : blackPlayer;
    }


    public boolean actionMoveTo(Piece activePiece, int toRank, int toFile) {
        BoardTile toTile = chessBoard.getTileAtLocation(toRank, toFile);
        if (activePiece.moveToTile(toTile)) {
            printConfiguration();

            return true;
        }
        printConfiguration();

        return false;
    }

    public Piece actionKillPieceAtLocation(Piece activePiece, int toRank, int toFile) {
        Piece target = chessBoard.getPieceAtLocation(toRank, toFile);
        if (activePiece.killTargetPiece(target)) {
            printConfiguration();
            return target;
        }

        printConfiguration();
        return null;
    }


    /**
     * for each enemy piece, it checks if at least one of them can kill the target player's king
     *
     * @param player
     * @return true if the targetKing is in check
     */
    public boolean checkKing(Player player) {
        HashMap<String, Piece> attackerPieces = (player == Player.WHITE) ? blackPlayer : whitePlayer;
        HashMap<String, Piece> targetPieces = (player == Player.BLACK) ? blackPlayer : whitePlayer;
        Piece targetKing = targetPieces.get(PieceName.KING.getName());
        BoardTile tile = targetKing.getTileUnderPiece();
        for (Piece piece : attackerPieces.values()) {
//            System.out.println(piece.getPlayer().getColor() + piece.getName().getName() + " can move to " + piece.getReachableTiles());
            if (!piece.removedFromBoard() && piece.getReachableTiles().contains(tile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper function for checking if king is checkable if it moves to targetTile
     *
     * @param playerPieces
     * @param targetTile
     * @return true if the king can be checked at the given tile
     */
    public boolean playerCanKillKingAtTile(HashMap<String, Piece> playerPieces, BoardTile targetTile) {
        for (Piece piece : playerPieces.values()) {
            if (piece.canKillKingAtTile(targetTile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * for a given player checkMate checks if its king can be killed no matter which tile it moves to
     *
     * @param player
     * @return true if targetKing is checkmated
     */
    public boolean checkMate(Player player) {
        HashMap<String, Piece> attackerPieces = (player == Player.WHITE) ? blackPlayer : whitePlayer;
        HashMap<String, Piece> targetPieces = (player == Player.BLACK) ? blackPlayer : whitePlayer;
        Piece targetKing = targetPieces.get(PieceName.KING.getName());
        HashSet<BoardTile> checkMatePositions = targetKing.neighbours();
        for (Iterator<BoardTile> it = checkMatePositions.iterator(); it.hasNext(); ) {
            BoardTile tile = it.next();
            if (playerCanKillKingAtTile(attackerPieces, tile)) {
                it.remove();
            }
        }
        return checkMatePositions.isEmpty();
    }

    /**
     * update the reachable tiles of every piece on the board
     */
    public void updateReachableTilesForAll() {
        updateReachableTilesForPlayer(whitePlayer);
        updateReachableTilesForPlayer(blackPlayer);
    }

    /**
     * Helper function
     *
     * @param playerPieces
     */
    public void updateReachableTilesForPlayer(HashMap<String, Piece> playerPieces) {
        for (Piece piece : playerPieces.values()) {
            piece.updateReachableTiles();
        }
    }

    public void printConfiguration() {
        for (int i = chessBoard.getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < chessBoard.getWidth(); j++) {
                System.out.print(chessBoard.getPieceAtLocation(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
