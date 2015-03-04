package org.bitbucket.calvinmwhu.chess.controller;


import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.values.BoardDimension;
import org.bitbucket.calvinmwhu.chess.values.BoardShape;
import org.bitbucket.calvinmwhu.chess.view.ChessBoardView;

import javax.swing.*;

/**
 * The game controller for connecting the view and model.
 * All the buttons defined in the ChessBoardView class are registered with corresponding call-back functions here.
 */
public class ChessBoardController extends JApplet{
    private ChessBoardView boardView;
    private Game gameModel;
    boolean gameOver = false;

    public ChessBoardView getBoardView(){
        return boardView;
    }

    public void init(){
        try {
            boardView = new ChessBoardView(BoardDimension.SQUARE.getHeight(),BoardDimension.SQUARE.getWidth());
            gameModel = new Game();
            gameModel.setUpBoardAndPieces(BoardShape.SQUARE);
            boardView.refreshBoard(gameModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        ChessBoardView view = new ChessBoardView();
        ChessBoardController controller = new ChessBoardController();
        controller.init();
        controller.getContentPane().add(controller.getBoardView());
        while(true){

        }
    }

}
