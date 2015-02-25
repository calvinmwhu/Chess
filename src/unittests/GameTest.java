package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testMove() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece currentPiece;
//        for(int i=board.getHeight()-1; i>=0; i--){
//            for(int j=0; j<board.getWidth(); j++){
//                System.out.print(board.getPieceAtLocation(i,j)+" ");
//            }
//            System.out.println();
//        }
        game.printConfiguration();
    }



    @Test
    public void testChecked_1() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.WHITE).get(PieceName.KING.getName());
        Piece blackQueen=game.getPlayers(Player.BLACK).get("Queen");
        Piece blackPawn = game.getPlayers(Player.BLACK).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(3,3));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(2,1));
        blackPawn.setTileUnderPiece(board.getTileAtLocation(4,4));
        game.updateConfiguration();

        assertTrue(game.checkKing(Player.WHITE));

    }

    @Test
    public void testChecked_2() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook=game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(3,0));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(4, 1));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(2, 1));
        game.updateConfiguration();

        assertTrue(game.checkKing(Player.BLACK));

    }

    @Test
    public void testChecked_3() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook=game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(6,3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(5,4));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(2,1));
        game.updateConfiguration();

        assertFalse(game.checkKing(Player.BLACK));

    }

    @Test
    public void testCheckMateTrue() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook=game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whiteKnight0 = game.getPlayers(Player.WHITE).get("Knight0");
        Piece whiteKnight1 = game.getPlayers(Player.WHITE).get("Knight1");
        Piece whiteQueen = game.getPlayers(Player.WHITE).get("Queen");

        king.setTileUnderPiece(board.getTileAtLocation(6,3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(7,7));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(4,3));
        whiteKnight0.setTileUnderPiece(board.getTileAtLocation(4,5));
        whiteKnight1.setTileUnderPiece(board.getTileAtLocation(4,1));
        whiteQueen.setTileUnderPiece(board.getTileAtLocation(6,4));

        game.updateConfiguration();

        assertTrue(game.checkMate(Player.BLACK));

    }


    @Test
    public void testCheckMateFalse() throws Exception{
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook=game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whiteKnight1 = game.getPlayers(Player.WHITE).get("Knight1");
        Piece whiteQueen = game.getPlayers(Player.WHITE).get("Queen");

        king.setTileUnderPiece(board.getTileAtLocation(6,3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(7,7));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(4,3));
        whiteKnight1.setTileUnderPiece(board.getTileAtLocation(4,1));
        whiteQueen.setTileUnderPiece(board.getTileAtLocation(6,4));

        game.updateConfiguration();

        assertFalse(game.checkMate(Player.BLACK));

    }

    @Test
    public void testBattle() throws  Exception{
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece active;
        game.printConfiguration();
        System.out.println();
        game.updateConfiguration();


        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertTrue(game.actionMoveTo(active, 3, 2));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Pawn1");
        assertTrue(game.actionMoveTo(active, 4, 1));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 4, 1));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Pawn0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertTrue(game.actionMoveTo(active, 5, 0));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 3));
        assertTrue(game.actionMoveTo(active, 2, 2));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 2));
        assertTrue(game.actionMoveTo(active, 3, 1));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Pawn0");
        assertTrue(game.actionMoveTo(active,2,0));
        assertFalse(game.checkKing(Player.BLACK));
        game.updateConfiguration();


        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertNotNull(game.actionMoveTo(active, 1, 2));
        game.updateConfiguration();
        assertTrue(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Queen");
        assertNotNull(game.actionKillPieceAtLocation(active, 1, 2));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Rook0");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn1");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

    }

}