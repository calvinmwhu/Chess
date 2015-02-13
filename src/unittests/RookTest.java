package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.pieces.Rook;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void testSetHashKey() throws Exception {
        Piece rook0 = new Rook("white",3,3);
        Piece rook1 = new Rook("white",3,3);
//
//        assertArrayEquals(rook0.getHashKey().getBytes(),"Rook_0".getBytes());
//        assertArrayEquals(rook1.getHashKey().getBytes(),"Rook_1".getBytes());
    }

    @Test
    public void testCanMoveToLocationEmptyBoard() throws Exception {
        Piece rook = new Rook("white",3,3);
        Board board = new SquareBoard();

        assertTrue(rook.canMoveToLocation(board,3,5));
        assertTrue(rook.canMoveToLocation(board,3,1));
        assertTrue(rook.canMoveToLocation(board, 5, 3));
        assertTrue(rook.canMoveToLocation(board, 1, 3));
    }

    @Test
    public void testCanMoveToLocationRandomBoard() throws Exception {
        Piece rook = new Rook("white",3,3);
        Board board = new SquareBoard();
        board.setPiecesAtLocation(new Rook("white",3,4),3,4);
        board.setPiecesAtLocation(new Rook("white",4,3),4,3);

        assertFalse(rook.canMoveToLocation(board, 3, 5));
        assertFalse(rook.canMoveToLocation(board, 5, 3));

    }


}