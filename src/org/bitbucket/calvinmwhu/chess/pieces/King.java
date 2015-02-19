package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;


/**
 * Created by calvinmwhu on 2/11/15.
 */
public class King extends Piece {

    public King(Board board, Player player) {
        super(board, player);
        this.name = PieceName.KING;
    }

    public void updateReachableTiles(){
        reachableTiles.clear();
        int rank = getRank();
        int file = getFile();

        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(i!=0 && j!=0 && board.validRange(rank+i, file+j)){
                    BoardTile tile = board.getTileAtLocation(rank+i, file+j);
                    if(tile.getPlayerAtTile()!=this.player){
                        reachableTiles.add(tile);
                    }
                }
            }
        }

    }


}
