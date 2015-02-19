package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.King;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void testInvalidRange() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteKing=game.getPlayers(Player.WHITE).get("King");
        Piece whitePawn1=game.getPlayers(Player.WHITE).get("Pawn0");
        Piece blackQueen=game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");
        Piece whitePawn2=game.getPlayers(Player.WHITE).get("Pawn1");

        whiteKing.setTileUnderPiece(board.getTileAtLocation(3,0));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(3,2));
        whitePawn2.setTileUnderPiece(board.getTileAtLocation(4,2));
        blackKing.setTileUnderPiece(board.getTileAtLocation(2,3));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(4,4));

        whiteKing.updateReachableTiles();
        whitePawn1.updateReachableTiles();
        whitePawn2.updateReachableTiles();
        blackKing.updateReachableTiles();
        blackQueen.updateReachableTiles();

        System.out.println("testing invalidRange: whiteKing: " + whiteKing.getReachableTiles());

    }

    @Test
    public void testCanMoveToLocation() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteKing=game.getPlayers(Player.WHITE).get("King");
        Piece whitePawn1=game.getPlayers(Player.WHITE).get("Pawn0");
        Piece blackQueen=game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");
        Piece whitePawn2=game.getPlayers(Player.WHITE).get("Pawn1");

        whiteKing.setTileUnderPiece(board.getTileAtLocation(3,3));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(3,2));
        whitePawn2.setTileUnderPiece(board.getTileAtLocation(4,2));
        blackKing.setTileUnderPiece(board.getTileAtLocation(2,3));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(4,4));

        whiteKing.updateReachableTiles();
        whitePawn1.updateReachableTiles();
        whitePawn2.updateReachableTiles();
        blackKing.updateReachableTiles();
        blackQueen.updateReachableTiles();

        System.out.println("testing move: whiteKing: " + whiteKing.getReachableTiles());

    }
}