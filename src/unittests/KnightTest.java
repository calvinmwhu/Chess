//package unittests;
//
//import org.bitbucket.calvinmwhu.chess.pieces.Knight;
//import org.bitbucket.calvinmwhu.chess.pieces.Piece;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class KnightTest {
//
//    @Test
//    public void testSetHashKey() throws Exception {
//        Piece knight0 = new Knight("white", 3, 3);
//        assertArrayEquals(knight0.getHashKey().getBytes(), "Knight_0".getBytes());
//        Piece knight1 = new Knight("white", 3, 3);
//        assertArrayEquals(knight1.getHashKey().getBytes(), "Knight_1".getBytes());
//    }
//
//    @Test
//    public void testCanMoveToLocation() throws Exception {
//        Piece knight = new Knight("white", 3, 3);
//        assertTrue(knight.canMoveToLocation(null, 4, 5));
//        assertTrue(knight.canMoveToLocation(null, 2, 5));
//        assertTrue(knight.canMoveToLocation(null, 5, 4));
//        assertTrue(knight.canMoveToLocation(null, 1, 4));
//
//        assertTrue(knight.canMoveToLocation(null, 5, 2));
//        assertTrue(knight.canMoveToLocation(null, 1, 2));
//        assertTrue(knight.canMoveToLocation(null, 4, 1));
//        assertTrue(knight.canMoveToLocation(null, 2, 1));
//
//        assertFalse(knight.canMoveToLocation(null, 5, 3));
//        assertFalse(knight.canMoveToLocation(null, 1, 3));
//        assertFalse(knight.canMoveToLocation(null, 3, 1));
//        assertFalse(knight.canMoveToLocation(null, 2, 3));
//    }
//}