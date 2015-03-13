package org.bitbucket.calvinmwhu.chess.controller;


import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardDimension;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.values.Player;
import org.bitbucket.calvinmwhu.chess.view.ChessBoardView;
import org.bitbucket.calvinmwhu.chess.view.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The game controller for connecting the view and model.
 * All the buttons defined in the ChessBoardView class are registered with corresponding call-back functions here.
 */
public class ChessBoardController extends JApplet {
    private ChessBoardView boardView;
    private Game gameModel;
    private boolean gameOver;
    private boolean gameStarted;
    private long updateSeq;
    private long updateNews;
    static final int UPDATES_PER_SEC = 45;    // number of game update per second
    static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds
    private boolean staleMate = false;
    private Player winner = null;
    private boolean guiRunning;


    public ChessBoardController() {
        gameOver = false;
        gameStarted = false;
        guiRunning=true;
        updateSeq = 0;
        updateNews = 0;
    }

    public ChessBoardView getBoardView() {
        return boardView;
    }

    public void init() {
        try {
            // Use invokeAndWait() to ensure that init() exits after GUI construction
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    setupModelAndView();
                    addMouseListenerToTiles();
                    addActionListenerToStart();
                    addActionListenerToCustomized();
                    addActionListenerToRestart();
                    gameStart();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGameModel() {
        return gameModel;
    }

    private void incUpdateNews(){
        updateNews = updateNews % Long.MAX_VALUE + 1;
    }

    private void incUpdateSeq(){
        updateSeq = updateSeq % Long.MAX_VALUE + 1;
    }

    private void setupModelAndView() {
        setName("CS242 Chess Game");
        setLayout(new BorderLayout());
        setSize(800, 800);

        gameModel = new Game();
        boardView = new ChessBoardView(this, BoardDimension.SQUARE.getHeight(), BoardDimension.SQUARE.getWidth());

        setVisible(true);
    }

    private void addMouseListenerToTiles() {
        int height = BoardDimension.SQUARE.getHeight();
        int width = BoardDimension.SQUARE.getWidth();


        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                ImagePanel imagePanel = boardView.getImagePanelAtPosition(rank, file);
                imagePanel.addMouseListener(new ImagePanelListener(imagePanel));
            }
        }
    }

    private void addActionListenerToStart() {
        boardView.addStartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameStarted) {
                    gameStarted = true;
                    gameModel.setGameNews("Game Starts!");
                    gameModel.setUpBoardAndPieces(BoardShape.SQUARE);

                    gameModel.updateReachableTilesForAll();
                    boardView.updatePiecesConfiguration();
                    incUpdateNews();
                }
            }
        });
    }

    private void addActionListenerToCustomized(){
        boardView.addCustomizedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameStarted){
                    gameModel.setCustomized(true);
                }
            }
        });
    }

    private void addActionListenerToRestart(){
        boardView.addRestartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameStarted){
                    gameModel.reStart();
                    gameModel.updateReachableTilesForAll();
                    boardView.updatePiecesConfiguration();
                    incUpdateNews();

                }
            }
        });
    }

    public boolean checkMated(Player player){
        return gameModel.checkMate(player);
    }

    class ImagePanelListener extends MouseAdapter {
        ImagePanel imagePanel;

        public ImagePanelListener(ImagePanel imagePanel) {
            this.imagePanel = imagePanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int toRank = imagePanel.getRank();
            int toFile = imagePanel.getFile();

            Player currTurn = gameModel.getTurn();
            if(checkMated(currTurn)){
                gameOver = true;
                winner=(currTurn==Player.BLACK)? Player.WHITE:Player.BLACK;
            }

            if (gameOver) {
                gameModel.setGameNews("Game over, "+winner.getColor()+" wins");
                incUpdateNews();
                return;
            }


            if (gameStarted) {
                Piece activePiece = gameModel.getActivePiece();
                if (activePiece == null) {
                    Piece pieceToGet = gameModel.getPieceAtLocation(toRank, toFile);
                    if (pieceToGet!=null && pieceToGet.getPlayer() != gameModel.getTurn()) {
                        gameModel.setGameNews("It's " + gameModel.getTurn().getColor() + "'s turn!");
                        incUpdateNews();
                        return;
                    }
                    gameModel.setActivePiece(toRank, toFile);
                    incUpdateSeq();
                } else {
                    if (gameModel.performAction(toRank, toFile)) {
                        gameModel.updateReachableTilesForAll();

                        //check king:
                        Player target = activePiece.getPlayer()==Player.WHITE? Player.BLACK:Player.WHITE;
                        if(gameModel.checkKing(target)){
                            gameModel.setGameNews(" checks " + target.getColor()+"'s king!");
                            System.out.println(gameModel.getGameNews());
                        }

                        gameModel.setActivePiece(null);
                        gameModel.flipTurn();
                        incUpdateSeq();
                    } else {
                        Piece desPiece = gameModel.getPieceAtLocation(toRank, toFile);
                        if (activePiece.isFriendPiece(desPiece)) {
                            incUpdateSeq();
                            gameModel.setActivePiece(desPiece);
                        }
                    }
                }
            }
            incUpdateNews();
        }
    }


    public void gameStart() {
        Thread gameThread = new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        // Start the thread. start() calls run(), which in turn calls gameLoop().
        gameThread.start();
    }


    private void gameLoop() {

        long beginTime, timeTaken, timeLeft;   // in msec
        while (guiRunning) {
            beginTime = System.nanoTime();
            // Refresh the display

            if (boardView.getUpdateSeq() < updateSeq % Long.MAX_VALUE) {
//                System.out.println("update");
                boardView.refreshBoard();
                boardView.setUpdateSeq(updateSeq % Long.MAX_VALUE);
                boardView.setWhiteScore(gameModel.getWhiteScore());
                boardView.setBlackScore(gameModel.getBlackScore());
                boardView.repaint();
            }

            if (boardView.getUpdateNews() < updateNews % Long.MAX_VALUE) {
                boardView.setGameFeedback(gameModel.getGameNews());
                boardView.setUpdateNews(updateNews % Long.MAX_VALUE);
                boardView.repaint();
            }

            // Delay timer to provide the necessary delay to meet the target rate
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (UPDATE_PERIOD_NSEC - timeTaken) / 1000000;  // in milliseconds

            if (timeLeft < 10) timeLeft = 10;  // set a minimum
            try {
                // Provides the necessary delay and also yields control so that other thread can do work.
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) {
            }
        }
    }


}
