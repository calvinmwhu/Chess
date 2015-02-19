package unittests;

import org.bitbucket.calvinmwhu.chess.pieces.King;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void testCanMoveToLocation() throws Exception {
        Piece king = new King("White", 0,4);
        assertTrue(king.canMoveToLocation(null,0,5));
        assertTrue(king.canMoveToLocation(null,0,3));
        assertTrue(king.canMoveToLocation(null,1,5));
        assertTrue(king.canMoveToLocation(null,1,3));

    }
}