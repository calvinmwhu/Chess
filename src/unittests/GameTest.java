package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import java.awt.*;

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
        currentPiece = game.getPieceAt(1, 2);
        game.readyToMove(currentPiece);
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(3,2));

        currentPiece = game.getPieceAt(6,3);
        game.readyToMove(currentPiece);
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(4, 3));

        currentPiece = game.getPieceAt(3,2);
        game.readyToMove(currentPiece);
        System.out.println(currentPiece.getReachableTiles());
        assertTrue(currentPiece.moveToPosition(4, 3));


    }




}