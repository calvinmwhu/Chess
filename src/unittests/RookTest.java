package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.pieces.Rook;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void testCanMoveToLocationEmptyBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece blackRook=game.getPlayers(Player.BLACK).get("Rook1");
        blackRook.setTileUnderPiece(board.getTileAtLocation(2, 5));
        blackRook.updateReachableTiles();
        System.out.println(blackRook.getReachableTiles());
    }

    @Test
    public void testCanMoveToLocationRandomBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteRook=game.getPlayers(Player.WHITE).get("Rook1");
        Piece whitePawn0=game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whitePawn1=game.getPlayers(Player.WHITE).get("Pawn1");
        Piece blackQueen=game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");


        whiteRook.setTileUnderPiece(board.getTileAtLocation(3,3));
        whitePawn0.setTileUnderPiece(board.getTileAtLocation(4,3));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(1,3));
        blackKing.setTileUnderPiece(board.getTileAtLocation(3,2));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(3,4));
        whiteRook.updateReachableTiles();
        System.out.println(whiteRook.getReachableTiles());

    }


}