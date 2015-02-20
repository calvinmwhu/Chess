package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;


public class PawnTest {

    @Test
    public void testCanMoveToLocationEmptyBoard() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece pawn=game.getPlayers(Player.WHITE).get("Pawn0");
        pawn.setTileUnderPiece(board.getTileAtLocation(1,2));
        pawn.updateReachableTiles();
        System.out.println(pawn.getReachableTiles());

        Piece pawn1=game.getPlayers(Player.WHITE).get("Pawn1");
        pawn1.setTileUnderPiece(board.getTileAtLocation(2,4));
        pawn1.updateReachableTiles();
        System.out.println(pawn1.getReachableTiles());

        Piece pawn2=game.getPlayers(Player.BLACK).get("Pawn0");
        pawn2.setTileUnderPiece(board.getTileAtLocation(1,5));
        pawn2.updateReachableTiles();
        System.out.println(pawn2.getReachableTiles());
    }

    @Test
    public void testCanMoveToLocationRandomBoardWhite() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece pawn=game.getPlayers(Player.WHITE).get("Pawn0");
        pawn.setTileUnderPiece(board.getTileAtLocation(1,2));
        Piece bishop = game.getPlayers(Player.BLACK).get("Bishop1");
        bishop.setTileUnderPiece(board.getTileAtLocation(2,1));
        Piece bishop0 = game.getPlayers(Player.WHITE).get("Bishop1");
        bishop0.setTileUnderPiece(board.getTileAtLocation(2,3));

        pawn.updateReachableTiles();
        System.out.println(pawn.getReachableTiles());
    }


    @Test
    public void testCanMoveToLocationRandomBoardBlack() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece pawn=game.getPlayers(Player.BLACK).get("Pawn0");
        pawn.setTileUnderPiece(board.getTileAtLocation(6,2));
        Piece bishop = game.getPlayers(Player.WHITE).get("Bishop1");
        bishop.setTileUnderPiece(board.getTileAtLocation(5,1));
        Piece bishop0 = game.getPlayers(Player.BLACK).get("Bishop1");
        bishop0.setTileUnderPiece(board.getTileAtLocation(5,3));

        pawn.updateReachableTiles();
        System.out.println(pawn.getReachableTiles());
    }
}