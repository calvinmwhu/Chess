package org.bitbucket.calvinmwhu.chess.view;

import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by calvinmwhu on 2/20/15.
 */
public class ChessBoardView extends JFrame {
    private final int height;
    private final int width;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;

    private Image whiteTile;
    private Image blackTile;

    private JLabel[][] centerlabels;
    private ImagePanel[][] imagePanels;

    class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image img) {
            image = img;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            graphics.drawImage(image, 0, 0, null);
        }
    }

    public ChessBoardView(Image whiteTile, Image blackTile, int height, int width) {
        this.whiteTile = whiteTile;
        this.blackTile = blackTile;
        this.height = height;
        this.width = width;
        centerPanel = new JPanel();
        southPanel = new JPanel();
        northPanel = new JPanel();
        westPanel = new JPanel();
        eastPanel = new JPanel();
        centerlabels = new JLabel[this.height][this.width];
        imagePanels = new ImagePanel[this.height][this.width];
        setUpChessBoardUI();
    }

    private void setUpChessBoardUI() {
        setTitle("CS242 Chess Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        constructContentPane(getContentPane());

        centerPanel.setSize(720,720);
        setSize(800,800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void constructContentPane(Container contentPane) {
        GridLayout gridLayout = new GridLayout(height, width);
        centerPanel.setLayout(gridLayout);

        constructSouthPanel();
        constructWestPanel();
        constructCenterPanel();

        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        contentPane.add(westPanel, BorderLayout.WEST);
    }


    private void constructWestPanel() {
        GridLayout gridLayout = new GridLayout(this.height, 0);

        westPanel.setLayout(gridLayout);
        JLabel[] labels = new JLabel[8];
        for (int i = 0; i < this.height; i++) {
            labels[i] = new JLabel(String.valueOf(this.height - i));
            westPanel.add(labels[i]);
        }
    }


    private void constructSouthPanel() {
        GridLayout gridLayout = new GridLayout(this.height, 0);

        southPanel.setLayout(gridLayout);
        JLabel[] labels = new JLabel[8];
        String[] values = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < this.width; i++) {
            labels[i] = new JLabel(values[i]);
            southPanel.add(labels[i]);
        }
    }

    private void constructCenterPanel() {
        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
//        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] ranks = {8,7,6,5,4,3,2,1};

        for (int rank = 0; rank < ranks.length; rank++) {
            for (int file = 0; file < files.length; file++) {
                imagePanels[rank][file] = (rank + file) % 2 == 0 ? new ImagePanel(whiteTile) : new ImagePanel(blackTile);
                imagePanels[rank][file].setName(files[file] + ranks[rank]);
                centerlabels[rank][file] = new JLabel();
                centerlabels[rank][file].setName(imagePanels[rank][file].getName());
                imagePanels[rank][file].add(centerlabels[rank][file]);
                imagePanels[rank][file].setSize(90,90);
                centerPanel.add(imagePanels[rank][file]);
            }
        }

    }

    public void refreshBoard(Game game){
        for(int rank = 0; rank<height; rank++){
            for(int file = 0; file<width; file++){
                centerlabels[rank][file].setIcon(null);
            }
        }
        updatePiecesConfiguration(game);
    }

    public void updatePiecesConfiguration(Game game){
        for(Piece piece : game.getPlayers(Player.WHITE).values()){
            addPieceToBoard(piece);
        }
        for(Piece piece : game.getPlayers(Player.BLACK).values()){
            addPieceToBoard(piece);
        }
    }

    private void addPieceToBoard(Piece piece){
        if(piece.removedFromBoard()) return;
        int rank = piece.getRank();
        int file = piece.getFile();
        String color = piece.getPlayer().getColor();
        String name = piece.getName().getName();
        String filename = System.getProperty("user.home")+"/temp/"+color+name+".png";
        centerlabels[rank][file].setIcon(new ImageIcon(filename));
    }

}
