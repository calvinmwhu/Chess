package unittests;

import org.bitbucket.calvinmwhu.chess.view.ChessBoardView;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.BoardDimension;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class ChessBoardUITest {
    @Test
    public void testChecked_2() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);

        Image blackTile = ImageIO.read(new File(System.getProperty("user.home") + "/temp/blackTile.jpeg"));
        Image whiteTile = ImageIO.read(new File(System.getProperty("user.home") + "/temp/whiteTile.jpg"));
        ChessBoardView chessBoardView = new ChessBoardView(BoardDimension.SQUARE.getHeight(), BoardDimension.SQUARE.getWidth());
        chessBoardView.updatePiecesConfiguration(game);
        while(true){

        }
    }
}