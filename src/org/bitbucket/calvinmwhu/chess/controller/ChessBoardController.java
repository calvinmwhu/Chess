package org.bitbucket.calvinmwhu.chess.controller;


import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.BoardDimension;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
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
    static final int UPDATES_PER_SEC = 30;    // number of game update per second
    static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds

    public ChessBoardController() {
        gameOver = false;
        gameStarted = false;
        updateSeq=0;
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
                    gameStart();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGameModel(){
        return gameModel;
    }

    private void setupModelAndView() {
        setName("CS242 Chess Game");
        setLayout(new BorderLayout());
        setSize(800, 800);

        gameModel = new Game();
        gameModel.setUpBoardAndPieces(BoardShape.SQUARE);
//        gameModel.updateReachableTilesForAll();
        boardView = new ChessBoardView(this, BoardDimension.SQUARE.getHeight(), BoardDimension.SQUARE.getWidth());

//        boardView.updatePiecesConfiguration();
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
                    gameModel.updateReachableTilesForAll();
                    boardView.updatePiecesConfiguration();
                }
            }
        });
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

            if (gameStarted) {
                if (gameModel.getActivePiece() == null) {
                    gameModel.setActivePiece(toRank, toFile);
                    updateSeq = updateSeq% Long.MAX_VALUE + 1;
                }else{
                    if(gameModel.performAction(toRank,toFile)){
                        gameModel.updateReachableTilesForAll();
                        updateSeq = updateSeq% Long.MAX_VALUE + 1;
                        gameModel.setActivePiece(null);
                    }
                }
            }
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
        while (!gameOver) {
            beginTime = System.nanoTime();
            // Refresh the display

            if(boardView.getUpdateSeq()<updateSeq%Long.MAX_VALUE){
//                System.out.println("update");
                boardView.refreshBoard();
                boardView.repaint();
                boardView.setUpdateSeq(updateSeq%Long.MAX_VALUE);
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
