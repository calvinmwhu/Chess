package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Bishop;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.pieces.Rook;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class BishopTest {


    @Test
    public void testCanMoveToLocationEmptyBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece blackBishop = game.getPlayers(Player.BLACK).get("Bishop0");
        blackBishop.setTileUnderPiece(board.getTileAtLocation(2, 5));
        blackBishop.updateReachableTiles();
        System.out.println(blackBishop.getReachableTiles());
    }

    @Test
    public void testCanMoveToLocationRandomBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteBishop = game.getPlayers(Player.WHITE).get("Bishop0");
        Piece whitePawn0 = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whitePawn1 = game.getPlayers(Player.WHITE).get("Pawn1");
        Piece blackQueen = game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");

        whiteBishop.setTileUnderPiece(board.getTileAtLocation(1, 4));
        whitePawn0.setTileUnderPiece(board.getTileAtLocation(3, 6));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(3, 2));
        blackKing.setTileUnderPiece(board.getTileAtLocation(0, 5));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(0, 3));
        whiteBishop.updateReachableTiles();
        System.out.println(whiteBishop.getReachableTiles());
    }
}