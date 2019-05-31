package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static gui.define.DARK_COLOR;
import static gui.define.LIGHT_COLOR;

public class AfegirPeca extends JPanel {
    private Posicion Pos;
    private boolean selected;
    private String tipusP;
    private int colorP;
    private static Dimension TILE_SIZE = new Dimension(77, 77);

    AfegirPeca(final Posicion id, String tipus, int color) {
        //super(new GridBagLayout());
        this.Pos = id;
        this.selected = false;
        this.tipusP = tipus;
        this.colorP = color;
        setPreferredSize(TILE_SIZE);
        assign_color();
        assign_icon();
        //this.addMouseListener(this);
        validate();
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean s) {
        selected = s;
    }

    public Posicion getPos() {
        return Pos;
    }
    private void assign_color() {
        Color background_color;
        if (this.Pos.x%2 == 0) { //columna parella
            background_color = LIGHT_COLOR;
        }
        else{
            //columna senar
            background_color = DARK_COLOR;
        }
        setBackground(background_color);
    }

    private void assign_icon() {
        this.removeAll();
        if (Pos.x != 6) {
            try {
                String file_path = define.icons_route + "miniicons/" + this.tipusP + this.colorP + ".png";

                final BufferedImage icon =
                        ImageIO.read(new File(file_path));
                this.add(new JLabel(new ImageIcon(icon)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            try {
                String file_path = define.icons_route + "miniicons/trash.png";

                final BufferedImage icon =
                        ImageIO.read(new File(file_path));
                this.add(new JLabel(new ImageIcon(icon)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
