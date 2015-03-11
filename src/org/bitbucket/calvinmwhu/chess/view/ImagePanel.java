package org.bitbucket.calvinmwhu.chess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by calvinmwhu on 3/11/15.
 */
public class ImagePanel extends JPanel {
    private Image image;
    private JLabel pieceLabel;
    private final int rank;
    private final int file;

    public ImagePanel(Image img, JLabel pl, int rank, int file) {
        this.image = img;
        this.pieceLabel = pl;
        this.rank = rank;
        this.file = file;
    }

    public int getRank() {
        return this.rank;
    }

    public int getFile() {
        return this.file;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
    }

    @Override
    public String toString() {
        if (pieceLabel != null) {
            return getName()+"(" + rank + "," + file + "): " + this.pieceLabel.getIcon();
        } else {
            return getName()+"(" + rank + "," + file + "): " + this.pieceLabel;
        }
    }
}
