package org.bitbucket.calvinmwhu.chess.game;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.chessboard.SquareBoard;
import org.bitbucket.calvinmwhu.chess.pieces.*;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Game backend model, for storing chessBoard, pieces, and players data.
 */
public class Game {
    private Board chessBoard;
    private HashMap<String, Piece> whitePlayer;
    private HashMap<String, Piece> blackPlayer;
    private Stack<GameAction> undoStack;
    private Stack<GameAction> redoStack;
    private Player turn;
    private Piece activePiece;
    private int whiteScore;
    private int blackScore;
    private String gameNews;
    private boolean customized;
    /**
     * constructor, creates an empty game, gives the turn to white player
     */
    public Game() {
        turn = Player.WHITE;
        activePiece = null;
        undoStack = new Stack<GameAction>();
        redoStack = new Stack<GameAction>();
        whiteScore = 0;
        blackScore = 0;
        customized=false;
    }

    /**
     * sets up a chessboard and pieces according the specified shape. But not connecting pieces to chessboard
     *
     * @param shape
     */
    public void initContent(BoardShape shape) {
        if (shape == BoardShape.SQUARE) {
            chessBoard = new SquareBoard();
            chessBoard.setCustomized(customized);
        } else {
            System.out.println("other board shape not implemented yet!");
        }
        if(!customized){
            setUpPieces();
        }
        else{
            setUpCustomizedPieces();
        }
    }

    /**
     * sets up pieces and the chessboard, then puts pieces on board
     *
     * @param shape
     */
    public void setUpBoardAndPieces(BoardShape shape) {
        initContent(shape);
        chessBoard.putPiecesOnBoard(whitePlayer, blackPlayer);
    }

    /**
     * creates 16 normal pieces and stores them into the player hashmaps
     */
    void setUpPieces() {
        whitePlayer = new HashMap<String, Piece>();
        blackPlayer = new HashMap<String, Piece>();
        for (PieceName pieceName : PieceName.values()) {
            if (pieceName == PieceName.PAWN) {
                for (int i = 0; i < 8; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.BISHOP) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Bishop(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.ROOK) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Rook(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Rook(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KNIGHT) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KING) {
                whitePlayer.put(pieceName.getName(), new King(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new King(chessBoard, Player.BLACK));
            } else {
                whitePlayer.put(pieceName.getName(), new Queen(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new Queen(chessBoard, Player.BLACK));
            }
        }
    }

    void setUpCustomizedPieces() {
        whitePlayer = new HashMap<String, Piece>();
        blackPlayer = new HashMap<String, Piece>();
        for (PieceName pieceName : PieceName.values()) {
            if (pieceName == PieceName.PAWN) {
                for (int i = 0; i < 8; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Pawn(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.EMPRESS) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Empress(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Empress(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.PRINCESS) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Princess(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Princess(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KNIGHT) {
                for (int i = 0; i < 2; i++) {
                    whitePlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.WHITE, i));
                    blackPlayer.put(pieceName.getName() + String.valueOf(i), new Knight(chessBoard, Player.BLACK, i));
                }
            } else if (pieceName == PieceName.KING) {
                whitePlayer.put(pieceName.getName(), new King(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new King(chessBoard, Player.BLACK));
            } else {
                whitePlayer.put(pieceName.getName(), new Queen(chessBoard, Player.WHITE));
                blackPlayer.put(pieceName.getName(), new Queen(chessBoard, Player.BLACK));
            }
        }
    }


    /**
     * return the game's chessBoard
     *
     * @return a reference to a ChessBoard object
     */
    public Board getChessBoard() {
        return chessBoard;
    }

    public Piece getPieceAtLocation(int rank, int file) {
        return chessBoard.getPieceAtLocation(rank, file);
    }

    public BoardTile getTileAtLocation(int rank, int file) {
        return chessBoard.getTileAtLocation(rank, file);
    }


    public Piece getActivePiece() {
        return activePiece;
    }

    public void setActivePiece(int rank, int file) {
        activePiece = getPieceAtLocation(rank, file);
    }

    public void setActivePiece(Piece piece) {
        activePiece = piece;
    }

    public Player getTurn() {
        return turn;
    }

    public void flipTurn() {
        if (turn == Player.BLACK) {
            turn = Player.WHITE;
        } else {
            turn = Player.BLACK;
        }
    }

    public String getGameNews() {
        return gameNews;
    }

    public void setGameNews(String news) {
        gameNews = news;
    }

    public Stack<GameAction> getUndoStack() {
        return undoStack;
    }

    public Stack<GameAction> getRedoStack() {
        return redoStack;
    }

    public int getWhiteScore(){
        return whiteScore;
    }

    public int getBlackScore(){
        return blackScore;
    }

    public boolean getCustomized(){
        return customized;
    }

    public void setCustomized(boolean b){
        customized=b;
    }

    /**
     * @param player
     * @return player of either white or black color
     */
    public HashMap<String, Piece> getPlayers(Player player) {
        return player == Player.WHITE ? whitePlayer : blackPlayer;
    }


    public boolean actionMoveTo(int toRank, int toFile) {
        BoardTile toTile = getTileAtLocation(toRank, toFile);
        if (activePiece.moveToTile(toTile)) {
            return true;
        }

        return false;
    }

    public Piece actionKillPieceAtLocation(int toRank, int toFile) {
        Piece target = getPieceAtLocation(toRank, toFile);
        if (activePiece.killTargetPiece(target)) {
            return target;
        }
        return null;
    }

    public boolean pieceActionToPerform(int toRank, int toFile) {
        if (getPieceAtLocation(toRank, toFile) == null) {
            boolean moveSuccess = actionMoveTo(toRank, toFile);
            if (moveSuccess) {
//                gameNews = activePiece + " moves to [" + toRank + "," + toFile + "]";
//                System.out.println(gameNews);
                return true;
            } else {
//                gameNews = activePiece + " cannot move to [" + toRank + "," + toFile + "]";
//                System.out.println(gameNews);
                return false;
            }
        } else {
            Piece pieceKilled = actionKillPieceAtLocation(toRank, toFile);
            if (pieceKilled != null) {
//                gameNews = pieceKilled + " is killed by " + activePiece;
//                System.out.println(gameNews);
                return true;
            } else {
//                gameNews = activePiece + " attempts an invalid kill!";
//                System.out.println(gameNews);
                return false;
            }
        }
    }


    public boolean performAction(int toRank, int toFile) {
        GameAction currAction = new GameAction(toRank, toFile);
        boolean success = currAction.performAction();
        if (success) {
            undoStack.push(currAction);
        }
        return success;
    }
//
//    public void performUndoAction(){
//        Game.GameAction undoAction = undoStack.peek();
//        undoAction.undoAction();
//
//    }

    public class GameAction {
        int toRank;
        int toFile;
        BoardTile currentTile = null;
        BoardTile toTile = null;

        Piece currPiece = null;
        Piece pieceToKill = null;
        String preGameNews;

        int preWhiteScore;
        int preBlackScore;

        Player preTurn;
        String[] fileLetters = {"A", "B", "C", "D", "E", "F", "G", "H"};

        HashSet<BoardTile> reachableTiles = new HashSet<BoardTile>();

        public GameAction(int toRank, int toFile) {
            preBlackScore=blackScore;
            preWhiteScore=whiteScore;
            preTurn=turn;
            this.toRank = toRank;
            this.toFile = toFile;
            toTile = getTileAtLocation(toRank, toFile);
            pieceToKill = getPieceAtLocation(toRank, toFile);
            preGameNews=new String(gameNews);
            if (activePiece != null) {
                currPiece = activePiece;
                reachableTiles = new HashSet<BoardTile>(activePiece.getReachableTiles());
                currentTile = activePiece.getTileUnderPiece();
            } else {
                throw new NullPointerException();
            }
        }

        public HashSet<BoardTile> getCurrReachableTiles() {
            return reachableTiles;
        }

        public Piece getCurrPiece() {
            return currPiece;
        }

        public Piece getPieceToKill() {
            return pieceToKill;
        }

        public BoardTile getCurrentTile() {
            return currentTile;
        }

        public BoardTile getDestinationTile() {
            return toTile;
        }

        public boolean actionMove() {
            if(activePiece.getName()==PieceName.KING){
                HashMap<String, Piece> attackerPieces = (activePiece.getPlayer() == Player.WHITE) ? blackPlayer : whitePlayer;
                if(playerCanKillPieceAtTile(attackerPieces, toTile)!=null){
                    gameNews=activePiece+"will be checked at "+toTile;
                    return false;
                }
            }
            if (activePiece.moveToTile(toTile)) {
                return true;
            }
            return false;
        }

        public Piece actionKill() {
            if (activePiece.killTargetPiece(pieceToKill)) {
                return pieceToKill;
            }
            return null;
        }

        public boolean performAction() {
            if (pieceToKill == null) {
                boolean moveSuccess = actionMove();
                if (moveSuccess) {
                    gameNews = activePiece + " moves to " + fileLetters[toFile] + (toRank+1) ;
                    System.out.println(gameNews);
                    return true;
                } else {
                    gameNews = activePiece + " cannot move to " +fileLetters[toFile] + (toRank+1) ;
                    System.out.println(gameNews);
                    return false;
                }
            } else {
                Piece pieceKilled = actionKill();
                if (pieceKilled != null) {
                    gameNews = pieceKilled + " is killed by " + activePiece;
                    System.out.println(gameNews);
                    if(activePiece.getPlayer()==Player.WHITE){
                        whiteScore++;
                    }else{
                        blackScore++;
                    }
                    return true;
                } else {
                    gameNews = activePiece + " attempts an invalid kill!";
                    System.out.println(gameNews);
                    return false;
                }
            }
        }

        public void undoAction() {
            activePiece=null;
            gameNews = new String(preGameNews);
            whiteScore=preWhiteScore;
            blackScore=preBlackScore;
            turn=preTurn;

            currPiece.setTileUnderPiece(currentTile);
            System.out.println(currPiece+": "+currPiece.getTileUnderPiece());
            if(pieceToKill!=null){
                pieceToKill.setTileUnderPiece(toTile);
                System.out.println(pieceToKill + ": " + pieceToKill.getTileUnderPiece());
            }
        }

        public void redoAction() {

        }

    }


    /**
     * for each enemy piece, it checks if at least one of them can kill the target player's king
     *
     * @param player
     * @return true if the targetKing is in check
     */
    public boolean checkKing(Player player) {
        HashMap<String, Piece> attackerPieces = (player == Player.WHITE) ? blackPlayer : whitePlayer;
        HashMap<String, Piece> targetPieces = (player == Player.BLACK) ? blackPlayer : whitePlayer;
        Piece targetKing = targetPieces.get(PieceName.KING.getName());
        BoardTile tile = targetKing.getTileUnderPiece();
        for (Piece piece : attackerPieces.values()) {
            if (!piece.removedFromBoard() && piece.getReachableTiles().contains(tile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper function for checking if king is checkable if it moves to targetTile
     *
     * @param playerPieces
     * @param targetTile
     * @return true if the king can be checked at the given tile
     */
    public Piece playerCanKillPieceAtTile(HashMap<String, Piece> playerPieces, BoardTile targetTile) {
        for (Piece piece : playerPieces.values()) {
            if (piece.canKillPieceAtTile(targetTile)) {
                return piece;
            }
        }
        return null;
    }

    /**
     * for a given player checkMate checks if its king can be killed no matter which tile it moves to
     *
     * @param player
     * @return true if targetKing is checkmated
     */
    public boolean checkMate(Player player) {
        HashMap<String, Piece> attackerPieces = (player == Player.WHITE) ? blackPlayer : whitePlayer;
        HashMap<String, Piece> targetPieces = (player == Player.BLACK) ? blackPlayer : whitePlayer;
        Piece targetKing = targetPieces.get(PieceName.KING.getName());
        if(targetKing.removedFromBoard()){
            return true;
        }
        HashSet<BoardTile> checkMatePositions = targetKing.neighbours();
        if(checkMatePositions.size()==0){
            return false;
        }
        for (Iterator<BoardTile> it = checkMatePositions.iterator(); it.hasNext(); ) {
            BoardTile tile = it.next();
            Piece killerPiece = playerCanKillPieceAtTile(attackerPieces, tile);
            if (killerPiece!=null && playerCanKillPieceAtTile(targetPieces,killerPiece.getTileUnderPiece())==null) {
                it.remove();
            }
        }
        return checkMatePositions.isEmpty();
    }

    /**
     * update the reachable tiles of every piece on the board
     */
    public void updateReachableTilesForAll() {
        updateReachableTilesForPlayer(whitePlayer);
        updateReachableTilesForPlayer(blackPlayer);
    }

    /**
     * Helper function
     *
     * @param playerPieces
     */
    public void updateReachableTilesForPlayer(HashMap<String, Piece> playerPieces) {
        for (Piece piece : playerPieces.values()) {
            piece.updateReachableTiles();
        }
    }

    public void printConfiguration() {
        for (int i = chessBoard.getHeight() - 1; i >= 0; i--) {
            for (int j = 0; j < chessBoard.getWidth(); j++) {
                System.out.print(chessBoard.getPieceAtLocation(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void reStart(){
        undoStack.clear();
        redoStack.clear();
        turn=Player.WHITE;
        activePiece=null;
        whiteScore=0;
        blackScore=0;
        gameNews="Game Restarts";
        chessBoard.removeAllPiecesOnBoard(whitePlayer,blackPlayer);
        chessBoard.putPiecesOnBoard(whitePlayer,blackPlayer);
    }

}
