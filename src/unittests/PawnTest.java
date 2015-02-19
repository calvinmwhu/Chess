//package unittests;
//
//import org.bitbucket.calvinmwhu.chess.chessboard.Board;
//import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PawnTest {
//
//    @Test
//    public void testCanMoveToLocation() throws Exception {
//        Board board = new SquareBoard();
//        board.setupBoard();
//        assertTrue(board.getPieceAtLocation(1,1).canMoveToLocation(board,2,1));
//        assertTrue(board.getPieceAtLocation(1,1).canMoveToLocation(board,3,1));
//
//        assertTrue(board.getPieceAtLocation(6,1).canMoveToLocation(board,5,1));
//        assertTrue(board.getPieceAtLocation(6,1).canMoveToLocation(board,4,1));
//    }
//}