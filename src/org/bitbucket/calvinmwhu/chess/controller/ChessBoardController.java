package org.bitbucket.calvinmwhu.chess.controller;


import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.values.BoardDimension;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.view.ChessBoardView;

import javax.swing.*;
import java.awt.*;

/**
 * The game controller for connecting the view and model.
 * All the buttons defined in the ChessBoardView class are registered with corresponding call-back functions here.
 */
public class ChessBoardController extends JApplet {
    private ChessBoardView boardView;
    private Game gameModel;
    boolean gameOver = false;
    static final int UPDATES_PER_SEC = 10;    // number of game update per second
    static final long UPDATE_PERIOD_NSEC = 1000000000L / UPDATES_PER_SEC;  // nanoseconds


    public ChessBoardView getBoardView() {
        return boardView;
    }


    public void init() {
        try {
            setupModelAndView();
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
        boardView.refreshBoard(gameModel);
        setVisible(true);
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
