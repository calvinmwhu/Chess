//package unittests;
//
//import org.bitbucket.calvinmwhu.chess.chessboard.Board;
//import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
//import org.bitbucket.calvinmwhu.chess.pieces.Bishop;
//import org.bitbucket.calvinmwhu.chess.pieces.Piece;
//import org.bitbucket.calvinmwhu.chess.pieces.Rook;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class BishopTest {
//
//    @Test
//    public void testSetHashKey() throws Exception {
//        Piece bishop0 = new Bishop("white", 3, 3);
//        Piece bishop1 = new Bishop("white", 3, 3);
////        assertArrayEquals(bishop0.getHashKey().getBytes(),"Bishop_0".getBytes());
//    }
//
//    @Test
//    public void testCanMoveToLocationEmptyBoard() throws Exception {
//        Piece bishop = new Bishop("white",3,3);
//        Board board = new SquareBoard();
//
//        assertTrue(bishop.canMoveToLocation(board, 4, 4));
//        assertTrue(bishop.canMoveToLocation(board, 2, 2));
//        assertTrue(bishop.canMoveToLocation(board, 4, 2));
//        assertTrue(bishop.canMoveToLocation(board, 2, 4));
//
//
//        assertTrue(bishop.canMoveToLocation(board, 5, 5));
//        assertTrue(bishop.canMoveToLocation(board, 1, 1));
//        assertTrue(bishop.canMoveToLocation(board, 5, 1));
//        assertTrue(bishop.canMoveToLocation(board, 1, 5));
//
//        assertFalse(bishop.canMoveToLocation(board,3,0));
//        assertFalse(bishop.canMoveToLocation(board,3,1));
//        assertFalse(bishop.canMoveToLocation(board,4,3));
//        assertFalse(bishop.canMoveToLocation(board,2,3));
//
//
//        assertFalse(bishop.canMoveToLocation(board,5,6));
//        assertFalse(bishop.canMoveToLocation(board,6,5));
//        assertFalse(bishop.canMoveToLocation(board,4,5));
//        assertFalse(bishop.canMoveToLocation(board,2,5));
//
//    }
//
//    @Test
//    public void testCanMoveToLocationRandomBoard() throws  Exception{
//        Board board = new SquareBoard();
//        Piece bishop = new Bishop("white", 3,3);
//        board.setPiecesAtLocation(new Rook("white", 4, 4), 4, 4);
//        board.setPiecesAtLocation(new Rook("white", 4, 2), 4, 2);
//
//        assertFalse(bishop.canMoveToLocation(board,5,5));
//        assertFalse(bishop.canMoveToLocation(board,5,1));
//
//
//
//    }
//}