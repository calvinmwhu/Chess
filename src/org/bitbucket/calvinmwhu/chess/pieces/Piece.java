package org.bitbucket.calvinmwhu.chess.pieces;

import org.bitbucket.calvinmwhu.chess.chessboard.*;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashSet;

public abstract class Piece {
    protected Board board;
    protected BoardTile tileUnderPiece;
    protected int index;
    protected Player player;
    protected PieceName name;
    protected HashSet<BoardTile> reachableTiles;


    public Piece(Board board, Player player) {
        this.player = player;
        this.tileUnderPiece = null;
        this.board = board;
        reachableTiles = new HashSet<BoardTile>();
    }

    public PieceName getName() {
        return name;
    }

    public String getIndex() {
        return "";
    }

    public Player getPlayer() {
        return player;
    }

    public BoardTile getTileUnderPiece() {
        return tileUnderPiece;
    }

    public boolean isFriendPiece(Piece piece){
        if(piece!=null){
            return piece.getPlayer()==player;
        }
        return false;
    }

    public BoardTile getTileAtLocation(int rank, int file){
        if(board!=null){
            return board.getTileAtLocation(rank,file);
        }
        return null;
    }

    public int getRank() {
        if (tileUnderPiece == null) {
            return -1;
        }
        return tileUnderPiece.getRankPos();
    }

    public int getFile() {
        if (tileUnderPiece == null) {
            return -1;
        }
        return tileUnderPiece.getFilePos();
    }

    /**
     * get the tiles the piece can move to
     * @return
     */
    public HashSet<BoardTile> getReachableTiles() {
        return reachableTiles;
    }

    /**
     * puts a piece at tile; also calls tile's setOccupyingPiece function to connect the tile to piece
     * @param tile
     */
    public void setTileUnderPiece(BoardTile tile) {
        if (tileUnderPiece != null) {
            tileUnderPiece.setOccupyingPiece(null);
        }
        if (tile != tileUnderPiece) {
            tileUnderPiece = tile;
        }
        if (tile != null) {
            tile.setOccupyingPiece(this);
        }
    }

    /**
     * runs during each game iteration, updates the possible tiles the piece can move to
     */
    public abstract void updateReachableTiles();


    /**
     * replace the piece target
     * @param target
     */
    public void killPiece(Piece target) {
        target.setTileUnderPiece(null);
        System.out.println(target.getPlayer().getColor() + target.getName().getName() + " killed by " + player.getColor() + name.getName());
    }

    /**
     * move a piece to tile at location (rank,file)
     * @param rank
     * @param file
     * @return true if the move if successful
     */
    public boolean moveToPosition(int rank, int file) {
        BoardTile toTile = getTileAtLocation(rank,file);
        if (reachableTiles.contains(toTile)) {
            if (toTile.getPlayerAtTile() != Player.UNOCCUPIED) {
                //kill piece
                killPiece(toTile.getOccupyingPiece());
            } else {
                System.out.println(player.getColor() + name.getName() + "moves to " + toTile);
            }
            setTileUnderPiece(toTile);
            return true;
        }
        return false;
    }











    /**
     * called by a game object on a piece trying to move to a BoardTile toTile
     * @param toTile a BoardTile object that the current piece tries to move to
     * @return true if the move if valid, false otherwise
     */
    public boolean moveToTile(BoardTile toTile){
        if(toTile==null || toTile.getOccupyingPiece()!=null){
            return false;
        }
        setTileUnderPiece(toTile);
        return true;
    }

    /**
     * called by a game object on an attacking piece that tries to kill another piece target
     * @param target a piece object that the current piece tries to kill
     * @return true if the target is successfully killed, false otherwise
     */
    public boolean killTargetPiece(Piece target){
        /**
         * checks if the target is invalid, i.e. a null or a friend
         */
        if(target==null || isFriendPiece(target)){
            return false;
        }
        /**
         * update the tile
         */
        BoardTile toTile = target.getTileUnderPiece();
        target.setTileUnderPiece(null);
        setTileUnderPiece(toTile);
        return true;
    }


    /**
     * useful for checking if the king is checkmated. returns all the neighbour tiles around the king
     * @return set of neighbours tiles
     */
    public HashSet<BoardTile> neighbours() {
        return null;
    }

    /**
     * check if a piece has been killed
     * @return true if it is killed
     */
    public boolean removedFromBoard() {
        return tileUnderPiece == null;
    }

    /**
     * check if the piece can move to a given tile
     * @param toTile
     * @return true if the toTile is reachable by the piece
     */
    public abstract boolean canKillKingAtTile(BoardTile toTile);


    @Override
    public String toString() {
        return getPlayer().getColor()+getName()+getIndex();
    }

}
