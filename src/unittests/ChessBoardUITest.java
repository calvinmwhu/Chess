package unittests;

import org.bitbucket.calvinmwhu.chess.View.ChessBoardUI;
import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.values.*;
import org.bitbucket.calvinmwhu.chess.values.Dimension;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

import static org.junit.Assert.*;

public class ChessBoardUITest {
    @Test
    public void testChecked_2() throws Exception{
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        Image blackTile = ImageIO.read(new File(System.getProperty("user.home")+"/temp/blackTile.jpeg"));
        Image whiteTile = ImageIO.read(new File(System.getProperty("user.home")+"/temp/whiteTile.jpg"));
        ChessBoardUI chessBoardUI = new ChessBoardUI(whiteTile,blackTile,Dimension.SQUARE.getHeight(), Dimension.SQUARE.getWidth());
        chessBoardUI.updatePiecesConfiguration(game);


        while(true){

        }
    }
}