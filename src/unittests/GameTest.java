package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testMove() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        for(int i=board.getHeight()-1; i>=0; i--){
            for(int j=0; j<board.getWidth(); j++){
                System.out.print(board.getTileAtLocation(i,j)+" ");
            }
            System.out.println();
        }
    }


}