package org.bitbucket.calvinmwhu.chess.view;

import org.bitbucket.calvinmwhu.chess.chessboard.Board;
import org.bitbucket.calvinmwhu.chess.chessboard.BoardTile;
import org.bitbucket.calvinmwhu.chess.controller.ChessBoardController;
import org.bitbucket.calvinmwhu.chess.game.Game;
import org.bitbucket.calvinmwhu.chess.pieces.Piece;
import org.bitbucket.calvinmwhu.chess.values.PieceName;
import org.bitbucket.calvinmwhu.chess.values.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by calvinmwhu on 2/20/15.
 */
public class ChessBoardView extends JPanel {
    private final int height;
    private final int width;
    private JLabel gameFeedback;
    private TextField whiteScore;
    private TextField blackScore;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private HashMap<String, ImageIcon> pieceImages = new HashMap<String, ImageIcon>();

    private JButton start = new JButton("Start");
    private JButton restart = new JButton("Restart");
    private JButton forfeit = new JButton("Forfeit");
    private JButton undo = new JButton("Undo");
    private JButton redo = new JButton("Redo");
    private JButton customized = new JButton("Customized");

    private Image whiteTile;
    private Image blackTile;

    private JLabel[][] centerLabels;
    private ImagePanel[][] imagePanels;
    private Game game;
    long updateSeq;
    long updateNews;

    public ChessBoardView(ChessBoardController controller, int height, int width) {
        this.height = height;
        this.width = width;
        whiteScore = new TextField();
        blackScore = new TextField();
        gameFeedback = new JLabel();
        centerPanel = new JPanel();
        southPanel = new JPanel();
        northPanel = new JPanel();
        westPanel = new JPanel();
        eastPanel = new JPanel();
        centerLabels = new JLabel[this.height][this.width];
        imagePanels = new ImagePanel[this.height][this.width];
        game = controller.getGameModel();
        updateSeq = 0;
        updateNews = 0;

        loadPieceImages();
        setUpChessBoardUI(controller);
    }

    public long getUpdateSeq() {
        return updateSeq;
    }

    public long getUpdateNews() {
        return updateNews;
    }


    public void setGameFeedback(String feedback) {
        gameFeedback.setText(feedback);
    }

    public void setUpdateSeq(long seq) {
        updateSeq = seq;
    }

    public void setUpdateNews(long seq) {
        updateNews = seq;
    }

    public void setWhiteScore(int score) {
        whiteScore.setText(String.valueOf(score));
    }

    public void setBlackScore(int score) {
        blackScore.setText(String.valueOf(score));
    }


    private void loadPieceImages() {
        for (Player player : Player.values()) {
            if (player != Player.UNOCCUPIED) {
                String color = player.getColor();
                for (PieceName pieceName : PieceName.values()) {
                    String name = color + pieceName.getName();
                    String filename = name + ".png";
                    pieceImages.put(name, new ImageIcon(this.getClass().getResource(filename)));
                }
            }
        }
        try {
            whiteTile = ImageIO.read(this.getClass().getResource("whiteTile.jpg"));
            blackTile = ImageIO.read(this.getClass().getResource("blackTile.jpeg"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private void setUpChessBoardUI(ChessBoardController controller) {
        constructContentPane(controller.getContentPane());
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
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(eastPanel, BorderLayout.EAST);
    }

    private void constructNorthPanel() {
        GridLayout gridLayout = new GridLayout(0, 2);
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel[] panels = new JPanel[2];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel(new FlowLayout());
            JLabel name;
            TextField temp;
            if (i == 0) {
                name = new JLabel("White" + "'s score");
                temp = whiteScore;
            } else {
                name = new JLabel("Black" + "'s score");
                temp = blackScore;
            }
            panels[i].add(name);
            panels[i].add(temp);
            northPanel.add(panels[i]);
        }
        gameFeedback.setText("Game not started");
        northPanel.add(Box.createHorizontalStrut(60));
        northPanel.add(gameFeedback);
    }

    private void constructEastPanel() {
        eastPanel.setLayout(new GridLayout(6, 0));
        eastPanel.add(start);
        eastPanel.add(restart);
        eastPanel.add(forfeit);
        eastPanel.add(undo);
        eastPanel.add(redo);
        eastPanel.add(customized);
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
        GridLayout gridLayout = new GridLayout(0, this.width);
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
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8};

        for (int rank = ranks.length - 1; rank >= 0; rank--) {
            for (int file = 0; file < files.length; file++) {
                centerLabels[rank][file] = new JLabel();
                imagePanels[rank][file] = (rank + file) % 2 == 1 ? new ImagePanel(whiteTile, centerLabels[rank][file], rank, file) : new ImagePanel(blackTile, centerLabels[rank][file], rank, file);
                imagePanels[rank][file].setName(files[file] + ranks[rank]);
                centerLabels[rank][file].setName(imagePanels[rank][file].getName());
                imagePanels[rank][file].add(centerLabels[rank][file]);

                centerPanel.add(imagePanels[rank][file]);
            }
        }
    }

    public void removePiece(int rank, int file) {
        System.out.println(centerLabels[rank][file].getIcon());
        centerLabels[rank][file].setIcon(null);
        System.out.println(centerLabels[rank][file].getIcon());
    }

    public void highLightPanel(int rank, int file) {
        ImagePanel imagePanel;
        Border redBorder = BorderFactory.createLineBorder(Color.YELLOW, 5);
        try {
            imagePanel = imagePanels[rank][file];
            imagePanel.setBorder(redBorder);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deHighLightPanel(int rank, int file) {
        ImagePanel imagePanel;
        try {
            imagePanel = imagePanels[rank][file];
            imagePanel.setBorder(null);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void highlightReachableTiles(Piece activePiece) {
        HashSet<BoardTile> reachableTiles = activePiece.getReachableTiles();
        System.out.println(reachableTiles);
        Iterator<BoardTile> it = reachableTiles.iterator();
        while (it.hasNext()) {
            BoardTile tile = it.next();
            highLightPanel(tile.getRankPos(), tile.getFilePos());
        }
    }

    public void deHighlightReachableTiles(HashSet<BoardTile> reachableTiles) {
        Iterator<BoardTile> it = reachableTiles.iterator();
        while (it.hasNext()) {
            BoardTile tile = it.next();
            deHighLightPanel(tile.getRankPos(), tile.getFilePos());
        }
    }


    public void updateViewUIAfterChangeInGameModel() {
        Game.GameAction preAction = null;
        Piece previousPiece = null;
        Piece killedPiece = null;
        BoardTile originTile = null;
        BoardTile desTile = null;
        HashSet<BoardTile> preReachableTiles = null;

        try {
            preAction = game.getUndoStack().peek();

        } catch (EmptyStackException e) {
            System.err.println(e.getMessage());
        }

        try {
            previousPiece = preAction.getCurrPiece();
            killedPiece = preAction.getPieceToKill();
            originTile = preAction.getCurrentTile();
            desTile = preAction.getDestinationTile();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        String imageName = previousPiece.getPieceNameWithoutIndex();
        ImageIcon img = pieceImages.get(imageName);
        if (killedPiece == null) {
            //the operation performed is a move
            centerLabels[originTile.getRankPos()][originTile.getFilePos()].setIcon(null);
            centerLabels[desTile.getRankPos()][desTile.getFilePos()].setIcon(img);
        } else {
            //the operation performed is a kill
            centerLabels[originTile.getRankPos()][originTile.getFilePos()].setIcon(null);
            centerLabels[desTile.getRankPos()][desTile.getFilePos()].setIcon(img);
        }
    }

    public void refreshBoard() {
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                imagePanels[rank][file].setBorder(null);
            }
        }
        Piece activePiece = game.getActivePiece();
        if (activePiece != null) {
            highlightReachableTiles(activePiece);
        } else {
            updateViewUIAfterChangeInGameModel();
        }
    }




    public void updateViewForUndo() {
        Game.GameAction preAction = null;
        Piece currPiece = null;
        Piece killedPiece = null;
        BoardTile originTile = null;
        BoardTile currTile = null;

        try {
            preAction = game.getUndoStack().peek();

        } catch (EmptyStackException e) {
            System.err.println(e.getMessage());
        }

        try {
            currPiece = preAction.getCurrPiece();
            killedPiece = preAction.getPieceToKill();
            originTile = preAction.getCurrentTile();
            currTile = preAction.getDestinationTile();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        String imageName = currPiece.getPieceNameWithoutIndex();
        ImageIcon img = pieceImages.get(imageName);
        centerLabels[currTile.getRankPos()][currTile.getFilePos()].setIcon(null);
        centerLabels[originTile.getRankPos()][originTile.getFilePos()].setIcon(img);

        if(killedPiece!=null){    //the operation performed is a kill
            String imageNameKilled = killedPiece.getPieceNameWithoutIndex();
            ImageIcon imageKilled = pieceImages.get(imageNameKilled);
            centerLabels[currTile.getRankPos()][currTile.getFilePos()].setIcon(imageKilled);
        }
    }

    public void undoView() {
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                imagePanels[rank][file].setBorder(null);
            }
        }
        updateViewForUndo();
    }





    public void updatePiecesConfiguration() {
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                centerLabels[rank][file].setIcon(null);
            }
        }
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
        centerLabels[rank][file].setIcon(pieceImages.get(color + name));
    }

    public void markPosition() {
        for (int rank = 0; rank < height; rank++) {
            for (int file = 0; file < width; file++) {
                centerLabels[rank][file].setText("(" + String.valueOf(rank) + "," + String.valueOf(file) + ")");
            }
        }
    }


    public int getDimensionHeight() {
        return height;
    }

    public int getDimensionWidth() {
        return width;
    }

    public ImagePanel getImagePanelAtPosition(int rank, int file) {
        return imagePanels[rank][file];
    }


    public void addStartListener(ActionListener a) {
        try {
            start.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addRestartListener(ActionListener a) {
        try {
            restart.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addForfeitListener(ActionListener a) {
        try {
            forfeit.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addUndoListener(ActionListener a) {
        try {
            undo.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addRedoListener(ActionListener a) {
        try {
            redo.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addCustomizedListener(ActionListener a) {
        try {
            customized.addActionListener(a);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }


    public void addMouthActionListener(int rank, int file, MouseAdapter m) {
        try {
            imagePanels[rank][file].addMouseListener(m);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }


}
