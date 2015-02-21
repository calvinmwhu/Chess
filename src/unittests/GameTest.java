package unittests;

import org.bitbucket.calvinmwhu.chess.View.ChessBoardUI;
import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.*;
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
//        for(int i=board.getHeight()-1; i>=0; i--){
//            for(int j=0; j<board.getWidth(); j++){
//                System.out.print(board.getTileAtLocation(i,j)+" ");
//            }
//            System.out.println();
//        }
        Image blackTile = ImageIO.read(new File(System.getProperty("user.home") + "/temp/blackTile.jpeg"));
        Image whiteTile = ImageIO.read(new File(System.getProperty("user.home")+"/temp/whiteTile.jpg"));
        ChessBoardUI chessBoardUI = new ChessBoardUI(whiteTile,blackTile, org.bitbucket.calvinmwhu.chess.values.Dimension.SQUARE.getHeight(), org.bitbucket.calvinmwhu.chess.values.Dimension.SQUARE.getWidth());
        chessBoardUI.updatePiecesConfiguration(game);
        Thread.sleep(10000);


        currentPiece = game.getPieceAt(1, 2);
        game.updateConfiguration();
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(3, 2));
        chessBoardUI.refreshBoard(game);

        Thread.sleep(10000);
        currentPiece = game.getPieceAt(6,3);
        game.updateConfiguration();
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(4, 3));
        chessBoardUI.refreshBoard(game);

        Thread.sleep(10000);
        currentPiece = game.getPieceAt(3,2);
        game.updateConfiguration();
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(4, 3));
        chessBoardUI.refreshBoard(game);

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

        assertTrue(game.checkKing(king));

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

        assertTrue(game.checkKing(king));

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

        assertFalse(game.checkKing(king));

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

        assertTrue(game.checkMate(king));

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

        assertFalse(game.checkMate(king));

    }

}