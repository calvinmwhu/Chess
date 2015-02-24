package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

public class QueenTest {

    @Test
    public void testCanMoveToLocationEmptyBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece queen=game.getPlayers(Player.BLACK).get("Queen");
        queen.setTileUnderPiece(board.getTileAtLocation(2, 5));
        queen.updateReachableTiles();
        System.out.println(queen.getReachableTiles());
    }


    @Test
    public void testCanMoveToLocationRandom() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece queen=game.getPlayers(Player.BLACK).get("Queen");
        Piece whitePawn0=game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whitePawn1=game.getPlayers(Player.WHITE).get("Pawn1");
        Piece blackPawn=game.getPlayers(Player.BLACK).get("Pawn0");
        Piece blackKing = game.getPlayers(Player.BLACK).get("King");

        queen.setTileUnderPiece(board.getTileAtLocation(3, 5));
        blackPawn.setTileUnderPiece(board.getTileAtLocation(2, 4));
        blackKing.setTileUnderPiece(board.getTileAtLocation(5,7));
        whitePawn0.setTileUnderPiece(board.getTileAtLocation(5,5));
        whitePawn1.setTileUnderPiece(board.getTileAtLocation(3,2));

        queen.updateReachableTiles();
        System.out.println(queen.getReachableTiles());




    }




}