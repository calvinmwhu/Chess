package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Empress;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import java.util.HashSet;

public class EmpressTest {
    @Test
    public void testMoveToLocationBarrier() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteEmpress = new Empress(board, Player.WHITE, 0);
        Piece whiteKnight = game.getPlayers(Player.WHITE).get("Knight1");
        Piece whitePawn1 = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece blackQueen = game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");
        Piece whitePawn2 = game.getPlayers(Player.WHITE).get("Pawn1");

        whiteEmpress.setTileUnderPiece(board.getTileAtLocation(3, 3));
        whiteKnight.setTileUnderPiece(board.getTileAtLocation(0, 3));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(6, 3));
        whitePawn2.setTileUnderPiece(board.getTileAtLocation(2, 5));
        blackKing.setTileUnderPiece(board.getTileAtLocation(1, 2));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(5, 4));

        whiteEmpress.updateReachableTiles();
        HashSet<BoardTile> reachableTiles = whiteEmpress.getReachableTiles();
        System.out.println("testing move: whiteEmpress: " + whiteEmpress.getReachableTiles());
    }
}