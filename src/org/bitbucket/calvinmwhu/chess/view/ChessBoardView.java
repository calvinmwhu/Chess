package org.bitbucket.calvinmwhu.chess.view;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by calvinmwhu on 2/20/15.
 */
public class ChessBoardView extends JFrame {
    private String imageDir;
    private final int height;
    private final int width;
    private TextField[] scores;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;

    private JButton start=new JButton("Start");
    private JButton restart=new JButton("Restart");
    private JButton forfeit=new JButton("Forfeit");
    private JButton undo=new JButton("Undo");
    private JButton redo=new JButton("Redo");

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

    public void addStartListener(ActionListener a){
        start.addActionListener(a);
    }

    public void addRestartListener(ActionListener a){
        restart.addActionListener(a);
    }

    public void addForfeitListener(ActionListener a){
        forfeit.addActionListener(a);
    }

    public void addUndoListener(ActionListener a){
        undo.addActionListener(a);
    }

    public void addRedoListener(ActionListener a){
        redo.addActionListener(a);
    }

    public ChessBoardView(Image whiteTile, Image blackTile, int height, int width) {

        this.whiteTile = whiteTile;
        this.blackTile = blackTile;
        this.height = height;
        this.width = width;
        this.scores = new TextField[2];
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
        setLayout(new BorderLayout());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        constructContentPane(getContentPane());
    }

    private void constructContentPane(Container contentPane) {

        constructCenterPanel();
        constructSouthPanel();
        constructWestPanel();
        constructNorthPanel();
        constructEastPanel();

        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        contentPane.add(westPanel, BorderLayout.WEST);
        contentPane.add(northPanel,BorderLayout.NORTH);
        contentPane.add(eastPanel, BorderLayout.EAST);
    }

    private void constructNorthPanel(){
        GridLayout gridLayout = new GridLayout(0,2);
        northPanel.setLayout(gridLayout);
        JPanel[] panels = new JPanel[2];
        for(int i=0; i<panels.length; i++){
            panels[i] = new JPanel(new FlowLayout());
            JLabel name = new JLabel("Player_"+i+"'s score");
            scores[i] = new TextField("");
            panels[i].add(name);
            panels[i].add(scores[i]);
            northPanel.add(panels[i]);
        }
    }

    private void constructEastPanel(){
        eastPanel.setLayout(new GridLayout(5,0));
        eastPanel.add(start);
        eastPanel.add(restart);
        eastPanel.add(forfeit);
        eastPanel.add(undo);
        eastPanel.add(redo);
    }

    private void constructWestPanel() {
        GridLayout gridLayout = new GridLayout(this.height, 0);

        westPanel.setLayout(gridLayout);
        JLabel[] labels = new JLabel[8];
        for (int i = 0; i < this.height; i++) {
            labels[i] = new JLabel(String.valueOf(this.height - i));
            labels[i].setVerticalAlignment(JLabel.CENTER);
            westPanel.add(labels[i]);
        }
    }


    private void constructSouthPanel() {
        GridLayout gridLayout = new GridLayout(0,this.width);
        southPanel.setLayout(gridLayout);

        JLabel[] labels = new JLabel[8];
        String[] values = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < this.width; i++) {
            labels[i] = new JLabel(values[i]);
            labels[i].setHorizontalAlignment(JLabel.CENTER);
            southPanel.add(labels[i]);
        }
    }

    private void constructCenterPanel() {
        GridLayout gridLayout = new GridLayout(height, width);
        centerPanel.setLayout(gridLayout);

        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int[] ranks = {8, 7, 6, 5, 4, 3, 2, 1};

        for (int rank = ranks.length-1; rank>=0; rank--) {
            for (int file = 0; file < files.length; file++) {
                imagePanels[rank][file] = (rank + file) % 2 == 1 ? new ImagePanel(whiteTile) : new ImagePanel(blackTile);
                imagePanels[rank][file].setName(files[file] + ranks[rank]);
                centerlabels[rank][file] = new JLabel();
                centerlabels[rank][file].setName(imagePanels[rank][file].getName());
                imagePanels[rank][file].add(centerlabels[rank][file]);
                centerPanel.add(imagePanels[rank][file]);
            }
        }
    }

    public void refreshBoard(Game game) {
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                centerlabels[rank][file].setIcon(null);
            }
        }
        updatePiecesConfiguration(game);
    }

    public void updatePiecesConfiguration(Game game) {
        for (Piece piece : game.getPlayers(Player.WHITE).values()) {
            addPieceToBoard(piece);
        }
        for (Piece piece : game.getPlayers(Player.BLACK).values()) {
            addPieceToBoard(piece);
        }
    }

    private void addPieceToBoard(Piece piece) {
        if (piece.removedFromBoard()) return;
        int rank = piece.getRank();
        int file = piece.getFile();
        String color = piece.getPlayer().getColor();
        String name = piece.getName().getName();
        String filename = System.getProperty("user.home") + "/temp/" + color + name + ".png";
        centerlabels[rank][file].setIcon(new ImageIcon(filename));
    }

    public void markPosition(){
        for(int rank = 0; rank<height; rank++){
            for(int file=0; file<width; file++){
                centerlabels[rank][file].setText("("+String.valueOf(rank)+","+String.valueOf(file)+")");
            }
        }

    }

}
