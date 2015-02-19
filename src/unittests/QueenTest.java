//package unittests;
//
//import org.bitbucket.calvinmwhu.chess.chessboard.Board;
//import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
//import org.bitbucket.calvinmwhu.chess.pieces.Piece;
//import org.bitbucket.calvinmwhu.chess.pieces.Queen;
//import org.bitbucket.calvinmwhu.chess.pieces.Rook;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class QueenTest {
//
//    @Test
//    public void testCanMoveToLocationEmptyBoard() throws Exception {
//        Piece queen = new Queen("white", 3, 3);
//        Board board = new SquareBoard();
//
//        assertTrue(queen.canMoveToLocation(board, 4, 4));
//        assertTrue(queen.canMoveToLocation(board, 2, 2));
//        assertTrue(queen.canMoveToLocation(board, 4, 2));
//        assertTrue(queen.canMoveToLocation(board, 2, 4));
//
//        assertTrue(queen.canMoveToLocation(board, 3, 4));
//        assertTrue(queen.canMoveToLocation(board, 4, 3));
//
//        assertTrue(queen.canMoveToLocation(board, 3, 5));
//        assertTrue(queen.canMoveToLocation(board, 5, 3));
//        assertTrue(queen.canMoveToLocation(board, 5, 5));
//        assertTrue(queen.canMoveToLocation(board, 1, 1));
//
//        assertFalse(queen.canMoveToLocation(board, 5, 4));
//        assertFalse(queen.canMoveToLocation(board, 4, 5));
//        assertFalse(queen.canMoveToLocation(board, 1, 2));
//        assertFalse(queen.canMoveToLocation(board, 2, 1));
//    }
//
//    @Test
//    public void testCanMoveToLocationFilledBoard() throws Exception {
//        Board board = new SquareBoard();
//        board.setupBoard();
//        Piece queen = board.getPieceAtLocation(0, 3);
//
//        assertFalse(queen.canMoveToLocation(board, 2, 5));
//        assertFalse(queen.canMoveToLocation(board, 2, 1));
//        assertFalse(queen.canMoveToLocation(board, 0, 1));
//        assertFalse(queen.canMoveToLocation(board, 0, 5));
//        assertFalse(queen.canMoveToLocation(board, 2, 3));
//
//    }
//
//
//    @Test
//    public void testCanMoveToLocationRandomBoard() throws Exception {
//        Board board = new SquareBoard();
//        Piece queen = new Queen("white", 3, 3);
//        board.setPiecesAtLocation(new Rook("white", 3, 2), 3, 2);
//        board.setPiecesAtLocation(new Rook("white", 4, 3), 4, 3);
//
//        assertTrue(queen.canMoveToLocation(board, 2, 3));
//        assertFalse(queen.canMoveToLocation(board, 5, 3));
//        assertFalse(queen.canMoveToLocation(board, 3, 1));
//        assertTrue(queen.canMoveToLocation(board, 5, 5));
//        assertTrue(queen.canMoveToLocation(board, 3, 5));
//
//
//    }
//
//
//}