package org.bitbucket.calvinmwhu.chess.controller;


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

/**
 * The game controller for connecting the view and model.
 * All the buttons defined in the ChessBoardView class are registered with corresponding call-back functions here.
 */
public class ChessBoardController extends JApplet {
    private ChessBoardView boardView;
    private Game gameModel;
    private boolean gameOver;
    private boolean gameStarted;

    public ChessBoardController() {
        gameOver = false;
        gameStarted = false;
    }

    public ChessBoardView getBoardView() {
        return boardView;
    }

    public void init() {
        try {
            setupModelAndView();
            addMouseListenerToTiles();
            addActionListenerToStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupModelAndView() throws Exception {
        setName("CS242 Chess Game");
        setLayout(new BorderLayout());
        setSize(800, 800);
        boardView = new ChessBoardView(this, BoardDimension.SQUARE.getHeight(), BoardDimension.SQUARE.getWidth());
        gameModel = new Game();
        gameModel.setUpBoardAndPieces(BoardShape.SQUARE);
        gameModel.updateReachableTilesForAll();
        boardView.refreshBoard(gameModel);
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
            int rank = imagePanel.getRank();
            int file = imagePanel.getFile();
//            System.out.println(imagePanel);
            if (gameStarted) {
                if (gameModel.getActivePiece() == null) {
                    gameModel.setActivePiece(rank, file);
                    Piece activePiece = gameModel.getActivePiece();
                    System.out.println(activePiece);
//                    gameModel.setActivePiece(null);
                    //to do: highlight reachable squares:
                    if (activePiece != null) {

                        HashSet<BoardTile> reachableTiles = activePiece.getReachableTiles();
                        System.out.println(reachableTiles);

                    }

//                    gameModel.setActivePiece(null);
                } else {
                    gameModel.setActivePiece(null);
                }
            }
            gameModel.updateReachableTilesForAll();
            boardView.refreshBoard(gameModel);
        }
    }


//
//    public void startChessGame() {
//        Thread gameThread = new Thread() {
//            @Override
//            public void run() {
//                gameLoop();
//            }
//        };
//        gameThread.start();
//    }
//
//    public void gameLoop() {
//        long beginTime, timeTaken, timeLeft;
//        while (!gameOver) {
//            beginTime = System.nanoTime();
//            System.out.println("hi");
//
//            // Refresh the display
//            // Delay timer to provide the necessary delay to meet the target rate
//            timeTaken = System.nanoTime() - beginTime;
//            timeLeft = (UPDATE_PERIOD_NSEC - timeTaken) / 1000000;  // in milliseconds
//            if (timeLeft < 10) timeLeft = 10;   // set a minimum
//            try {
//                // Provides the necessary delay and also yields control so that other thread can do work.
//                Thread.sleep(timeLeft);
//            } catch (InterruptedException ex) {
//            }
//
//        }
//    }


}
