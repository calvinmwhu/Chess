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
     * constructor, creates an empty game
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
     * creates 16 normal pieces and stored them into the hashmaps
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

    /**
     * return the piece at location (rank,file), null if no piece exists at that location
     *
     * @param rank
     * @param file
     * @return a reference to the piece
     */
    public Piece getPieceAt(int rank, int file) {
        if (chessBoard.validRange(rank, file)) {
            return chessBoard.getTileAtLocation(rank, file).getOccupyingPiece();
        }
        return null;
    }


    /**
     * move the piece to tile at location (toRank, toFile)
     *
     * @param piece
     * @param toRank
     * @param toFile
     * @return true if the move is allowed (legal move), false otherwise
     */
    public boolean movePieceTo(Piece piece, int toRank, int toFile) {
        if (!piece.moveToPosition(toRank, toFile)) {
            System.out.println("Cannot move to (" + toRank + "," + toFile + ")");
            return false;
        }
        return true;
    }

    /**
     * for each enemy piece, it checks if at least one of them can kill the king
     *
     * @param targetKing
     * @return true if the targetKing is in check
     */
    public boolean checkKing(Piece targetKing) {
        HashMap<String, Piece> attackerPieces = (targetKing.getPlayer() == Player.WHITE) ? blackPlayer : whitePlayer;
        BoardTile tile = targetKing.getTileUnderPiece();
        for (Piece piece : attackerPieces.values()) {
//            System.out.println(piece.getPlayer().getColor() + piece.getName().getName() + " can move to " + piece.getReachableTiles());
            if (piece.getReachableTiles().contains(tile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper function for checking if king is checked if it moves to targetTile
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
     * for a given targetKing checkMate checks if the king can be killed no matter which tile it moves to
     *
     * @param targetKing
     * @return true if targetKing is checkmated
     */
    public boolean checkMate(Piece targetKing) {
        HashMap<String, Piece> attackerPieces = (targetKing.getPlayer() == Player.WHITE) ? blackPlayer : whitePlayer;
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
     * update the reachables tiles for every piece on the board
     */
    public void updateConfiguration() {
        updateConfigurationForPlayer(whitePlayer);
        updateConfigurationForPlayer(blackPlayer);
    }

    /**
     * Helper function
     *
     * @param playerPieces
     */
    public void updateConfigurationForPlayer(HashMap<String, Piece> playerPieces) {
        for (Piece piece : playerPieces.values()) {
            piece.updateReachableTiles();
        }
    }
    
}
