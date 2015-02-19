package unittests;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Queen;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareBoardTest {

    @Test
    public void testSetupBoard() throws Exception {
        Game game = new Game();
        game.setUpBoardAndPieces(BoardShape.SQUARE);
        Board board = game.getChessBoard();

        //test configuration:
        for(int i=0; i<board.getHeight(); i++){
            for(int j=0; j<board.getWidth(); j++){
                if(i==0 || i==1){
                    assertEquals(board.getTileAtLocation(i,j).getPlayerAtTile(), Player.WHITE);
                }else if(i==7 || i==6){
                    assertEquals(board.getTileAtLocation(i,j).getPlayerAtTile(), Player.BLACK);
                }else{
                    assertEquals(board.getTileAtLocation(i,j).getPlayerAtTile(), Player.UNOCCUPIED);
                }
            }
        }

        //test pawn
        for(int i=0; i<board.getWidth(); i++){
            assertEquals(board.getTileAtLocation(1,i).getPieceNameAtTile(), PieceName.PAWN);
            assertEquals(board.getTileAtLocation(6,i).getPieceNameAtTile(), PieceName.PAWN);
        }

        //test king:
        assertEquals(board.getTileAtLocation(0,4).getPieceNameAtTile(), PieceName.KING);
        assertEquals(board.getTileAtLocation(7,4).getPieceNameAtTile(), PieceName.KING);

        //test queen
        assertEquals(board.getTileAtLocation(0,3).getPieceNameAtTile(), PieceName.QUEEN);
        assertEquals(board.getTileAtLocation(7,3).getPieceNameAtTile(), PieceName.QUEEN);

        //test rook
        assertEquals(board.getTileAtLocation(0,0).getPieceNameAtTile(), PieceName.ROOK);
        assertEquals(board.getTileAtLocation(0,7).getPieceNameAtTile(), PieceName.ROOK);
        assertEquals(board.getTileAtLocation(7,0).getPieceNameAtTile(), PieceName.ROOK);
        assertEquals(board.getTileAtLocation(7,7).getPieceNameAtTile(), PieceName.ROOK);

        //test Knight:
        assertEquals(board.getTileAtLocation(0,1).getPieceNameAtTile(), PieceName.KNIGHT);
        assertEquals(board.getTileAtLocation(0,6).getPieceNameAtTile(), PieceName.KNIGHT);
        assertEquals(board.getTileAtLocation(7,1).getPieceNameAtTile(), PieceName.KNIGHT);
        assertEquals(board.getTileAtLocation(7,6).getPieceNameAtTile(), PieceName.KNIGHT);


        //test Bishop:
        assertEquals(board.getTileAtLocation(0,2).getPieceNameAtTile(), PieceName.BISHOP);
        assertEquals(board.getTileAtLocation(0,5).getPieceNameAtTile(), PieceName.BISHOP);
        assertEquals(board.getTileAtLocation(7,2).getPieceNameAtTile(), PieceName.BISHOP);
        assertEquals(board.getTileAtLocation(7,5).getPieceNameAtTile(), PieceName.BISHOP);
    }

}