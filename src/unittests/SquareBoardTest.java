package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareBoardTest {

    @Test
    public void testConstructor() throws Exception {
        SquareBoard squareBoard = new SquareBoard();
        assertEquals(squareBoard.getHeight(), 8);
        assertEquals(squareBoard.getWidth(), 8);
    }

    @Test
    public void testSetupBoard() throws Exception {
        Board squareBoard = new SquareBoard();
        squareBoard.setupBoard();
        assertEquals(squareBoard.getPieceAtLocation(0, 0).getRank(), 0);
        assertEquals(squareBoard.getPieceAtLocation(0, 0).getFile(), 0);
        assertEquals(squareBoard.getPieceAtLocation(0, 0).getColor(), "White");

        assertEquals(squareBoard.getPieceAtLocation(7, 0).getRank(), 7);
        assertEquals(squareBoard.getPieceAtLocation(7, 0).getFile(), 0);
        assertEquals(squareBoard.getPieceAtLocation(7, 0).getColor(), "Black");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < squareBoard.getWidth(); j++) {
                assertEquals(squareBoard.getPieceAtLocation(i, j).getRank(), i);
                assertEquals(squareBoard.getPieceAtLocation(i, j).getFile(), j);
                assertEquals(squareBoard.getPieceAtLocation(i, j).getColor(), "White");
            }
        }

        for (int i = squareBoard.getHeight() - 1; i >= squareBoard.getHeight() - 2; i--) {
            for (int j = 0; j < squareBoard.getWidth(); j++) {
                assertEquals(squareBoard.getPieceAtLocation(i, j).getRank(), i);
                assertEquals(squareBoard.getPieceAtLocation(i, j).getFile(), j);
                assertEquals(squareBoard.getPieceAtLocation(i, j).getColor(), "Black");
            }
        }

        for (int i = 0; i < squareBoard.getHeight(); i++) {
            for (int j = 0; j < squareBoard.getWidth(); j++) {
                if (squareBoard.getPieceAtLocation(i, j) != null) {
                    if (i == 0 && j == 0 || i == 0 && j == 7 || i == 7 && j == 0 || i == 7 && j == 7) {
                        assertEquals(squareBoard.getPieceAtLocation(i, j).getHashKey().substring(0, 4), "Rook");
                    } else if (i == 0 && j == 1 || i == 0 && j == 6 || i == 7 && j == 1 || i == 7 && j == 6) {
                        assertEquals(squareBoard.getPieceAtLocation(i, j).getHashKey().substring(0, 6), "Knight");
                    } else if (i == 0 && j == 2 || i == 0 && j == 5 || i == 7 && j == 2 || i == 7 && j == 5) {
                        assertEquals(squareBoard.getPieceAtLocation(i, j).getHashKey().substring(0, 6), "Bishop");
                    } else if (i == 0 && j == 3 || i == 7 && j == 3) {
                        assertEquals(squareBoard.getPieceAtLocation(i, j).getHashKey().substring(0, 5), "Queen");
                    } else if (i == 0 && j == 4 || i == 7 && j == 4) {
                        assertEquals(squareBoard.getPieceAtLocation(i, j).getHashKey().substring(0, 4), "King");
                    }

                }
            }
        }

    }

    @Test
    public void testValidRange() throws Exception {
        Board squareBoard = new SquareBoard();
        assertTrue(squareBoard.validRange(7, 9));
        assertTrue(squareBoard.validRange(8, 8));
        assertTrue(squareBoard.validRange(1, 9));
    }

    @Test
    public void testAtPawnStartPosition() throws Exception {
        Board squareBoard = new SquareBoard();
        assertTrue(squareBoard.atPawnStartPosition(1));
        assertTrue(squareBoard.atPawnStartPosition(6));
        assertFalse(squareBoard.atPawnStartPosition(5));
        assertFalse(squareBoard.atPawnStartPosition(2));
    }
}