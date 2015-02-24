package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

public class KnightTest {


    @Test
    public void testMoveToLocationBarrier() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece whiteKnight=game.getPlayers(Player.WHITE).get("Knight1");
        Piece whitePawn1=game.getPlayers(Player.WHITE).get("Pawn0");
        Piece blackQueen=game.getPlayers(Player.BLACK).get("Queen");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");
        Piece whitePawn2=game.getPlayers(Player.WHITE).get("Pawn1");

        whiteKnight.setTileUnderPiece(board.getTileAtLocation(0,3));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(4,1));
        whitePawn2.setTileUnderPiece(board.getTileAtLocation(2,5));
        blackKing.setTileUnderPiece(board.getTileAtLocation(1,2));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(5,4));

        whiteKnight.updateReachableTiles();
        whitePawn1.updateReachableTiles();
        whitePawn2.updateReachableTiles();
        blackKing.updateReachableTiles();
        blackQueen.updateReachableTiles();

        System.out.println("testing move: whiteKing: " + whiteKnight.getReachableTiles());
    }
}