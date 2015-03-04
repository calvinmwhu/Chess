package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.controller.ChessBoardController;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.*;
import org.bitbucket.calvinmwhu.chess.view.ChessBoardView;
import org.junit.Test;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.io.File;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testMove() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece currentPiece;
        game.printConfiguration();
    }


    @Test
    public void testChecked_1() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.WHITE).get(PieceName.KING.getName());
        Piece blackQueen = game.getPlayers(Player.BLACK).get("Queen");
        Piece blackPawn = game.getPlayers(Player.BLACK).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(3, 3));
        blackQueen.setTileUnderPiece(board.getTileAtLocation(2, 1));
        blackPawn.setTileUnderPiece(board.getTileAtLocation(4, 4));
        game.updateReachableTilesForAll();

        assertTrue(game.checkKing(Player.WHITE));

    }

    @Test
    public void testChecked_2() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook = game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(3, 0));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(4, 1));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(2, 1));
        game.updateReachableTilesForAll();

        assertTrue(game.checkKing(Player.BLACK));

    }

    @Test
    public void testChecked_3() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook = game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");

        king.setTileUnderPiece(board.getTileAtLocation(6, 3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(5, 4));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(2, 1));
        game.updateReachableTilesForAll();

        assertFalse(game.checkKing(Player.BLACK));

    }

    @Test
    public void testCheckMateTrue() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook = game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whiteKnight0 = game.getPlayers(Player.WHITE).get("Knight0");
        Piece whiteKnight1 = game.getPlayers(Player.WHITE).get("Knight1");
        Piece whiteQueen = game.getPlayers(Player.WHITE).get("Queen");

        king.setTileUnderPiece(board.getTileAtLocation(6, 3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(7, 7));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(4, 3));
        whiteKnight0.setTileUnderPiece(board.getTileAtLocation(4, 5));
        whiteKnight1.setTileUnderPiece(board.getTileAtLocation(4, 1));
        whiteQueen.setTileUnderPiece(board.getTileAtLocation(6, 4));

        game.updateReachableTilesForAll();

        assertTrue(game.checkMate(Player.BLACK));

    }


    @Test
    public void testCheckMateFalse() throws Exception {
        Game game = new Game();
        game.initContent(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Piece king = game.getPlayers(Player.BLACK).get(PieceName.KING.getName());
        Piece whiteRook = game.getPlayers(Player.WHITE).get("Rook0");
        Piece whitePawn = game.getPlayers(Player.WHITE).get("Pawn0");
        Piece whiteKnight1 = game.getPlayers(Player.WHITE).get("Knight1");
        Piece whiteQueen = game.getPlayers(Player.WHITE).get("Queen");

        king.setTileUnderPiece(board.getTileAtLocation(6, 3));
        whiteRook.setTileUnderPiece(board.getTileAtLocation(7, 7));
        whitePawn.setTileUnderPiece(board.getTileAtLocation(4, 3));
        whiteKnight1.setTileUnderPiece(board.getTileAtLocation(4, 1));
        whiteQueen.setTileUnderPiece(board.getTileAtLocation(6, 4));

        game.updateReachableTilesForAll();

        assertFalse(game.checkMate(Player.BLACK));

    }

    @Test
    public void testBattle() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();
        Piece active;
        game.printConfiguration();
        System.out.println();
        game.updateReachableTilesForAll();


        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertTrue(game.actionMoveTo(active, 3, 2));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Pawn1");
        assertTrue(game.actionMoveTo(active, 4, 1));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 4, 1));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Pawn0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertTrue(game.actionMoveTo(active, 5, 0));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 3));
        assertTrue(game.actionMoveTo(active, 2, 2));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 2));
        assertTrue(game.actionMoveTo(active, 3, 1));
        game.updateReachableTilesForAll();
        game.printConfiguration();
        assertFalse(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Pawn0");
        assertTrue(game.actionMoveTo(active, 2, 0));
        assertFalse(game.checkKing(Player.BLACK));
        game.updateReachableTilesForAll();
        game.printConfiguration();


        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertNotNull(game.actionMoveTo(active, 1, 2));
        game.updateReachableTilesForAll();
        assertTrue(game.checkKing(Player.WHITE));
        game.printConfiguration();


        active = game.getPlayers(Player.WHITE).get("Queen");
        assertNotNull(game.actionKillPieceAtLocation(active, 1, 2));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.BLACK));
        game.printConfiguration();


        active = game.getPlayers(Player.BLACK).get("Rook0");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.WHITE));
        game.printConfiguration();


        active = game.getPlayers(Player.WHITE).get("Pawn1");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.BLACK));
        game.printConfiguration();


        active = game.getPlayers(Player.BLACK).get("Pawn4");
        assertFalse(game.actionMoveTo(active, 3, 4));
        assertTrue(game.actionMoveTo(active, 4, 4));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.WHITE));
        game.printConfiguration();


        active = game.getPlayers(Player.WHITE).get("Pawn4");
        assertTrue(game.actionMoveTo(active, 3, 4));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.BLACK));
        game.printConfiguration();

        active = game.getPlayers(Player.BLACK).get("Queen");
        assertFalse(game.actionMoveTo(active, 5, 2));
        assertTrue(game.actionMoveTo(active, 3, 7));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.WHITE));
        game.printConfiguration();


        active = game.getPlayers(Player.WHITE).get("Pawn5");
        assertTrue(game.actionMoveTo(active, 3, 5));
        game.updateReachableTilesForAll();
        assertFalse(game.checkKing(Player.BLACK));
        game.printConfiguration();

        active = game.getPlayers(Player.BLACK).get("Queen");
        assertNotNull(game.actionKillPieceAtLocation(active, 0, 4));
        game.updateReachableTilesForAll();
        game.printConfiguration();

//        assertFalse(game.checkKing(Player.WHITE));
    }

    @Test
    public void testBattle_withGUI() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        ChessBoardController controller = new ChessBoardController();

        Image blackTile = ImageIO.read(new File(System.getProperty("user.home") + "/temp/blackTile.jpeg"));
        Image whiteTile = ImageIO.read(new File(System.getProperty("user.home") + "/temp/whiteTile.jpg"));
        ChessBoardView chessBoardView = new ChessBoardView(BoardDimension.SQUARE.getHeight(), BoardDimension.SQUARE.getWidth());
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        Piece active;
        game.updateReachableTilesForAll();


        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertTrue(game.actionMoveTo(active, 3, 2));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Pawn1");
        assertTrue(game.actionMoveTo(active, 4, 1));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 4, 1));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Pawn0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertTrue(game.actionMoveTo(active, 5, 0));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn2");
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 5, 1));
        assertNotNull(game.actionKillPieceAtLocation(active, 5, 0));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 3));
        assertTrue(game.actionMoveTo(active, 2, 2));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));

        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertFalse(game.actionMoveTo(active, 2, 2));
        assertTrue(game.actionMoveTo(active, 3, 1));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Pawn0");
        assertTrue(game.actionMoveTo(active, 2, 0));
        assertFalse(game.checkKing(Player.BLACK));
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        game.updateReachableTilesForAll();


        active = game.getPlayers(Player.BLACK).get("Knight0");
        assertNotNull(game.actionMoveTo(active, 1, 2));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertTrue(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Queen");
        assertNotNull(game.actionKillPieceAtLocation(active, 1, 2));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Rook0");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));

        active = game.getPlayers(Player.WHITE).get("Pawn1");
        assertNotNull(game.actionKillPieceAtLocation(active, 2, 0));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Pawn4");
        assertFalse(game.actionMoveTo(active, 3, 4));
        assertTrue(game.actionMoveTo(active, 4, 4));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Pawn4");
        assertTrue(game.actionMoveTo(active, 3, 4));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Queen");
        assertFalse(game.actionMoveTo(active, 5, 2));
        assertTrue(game.actionMoveTo(active, 3, 7));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.WHITE));


        active = game.getPlayers(Player.WHITE).get("Pawn5");
        assertTrue(game.actionMoveTo(active, 3, 5));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);
        assertFalse(game.checkKing(Player.BLACK));


        active = game.getPlayers(Player.BLACK).get("Queen");
        assertNotNull(game.actionKillPieceAtLocation(active, 0, 4));
        game.updateReachableTilesForAll();
        chessBoardView.refreshBoard(game);
        Thread.sleep(5000);

//        assertFalse(game.checkKing(Player.WHITE));
    }


}