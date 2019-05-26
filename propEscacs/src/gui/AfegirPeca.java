package gui;

import domini.*;

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
    private static Dimension TILE_SIZE = new Dimension(77, 77);

    AfegirPeca(final Posicion id) {
        //super(new GridBagLayout());
        this.Pos = id;
        this.selected = false;
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
        Peca peca;
        switch (Pos.x) {
            case 0:
                if (Pos.y == 0) peca = new Peo(define.WHITE);
                else peca = new Peo(define.BLACK);
                break;
            case 1:
                if (Pos.y == 0) peca = new Torre(define.WHITE);
                else peca = new Torre(define.BLACK);
                break;
            case 2:
                if (Pos.y == 0) peca = new Cavall(define.WHITE);
                else peca = new Cavall(define.BLACK);
                break;
            case 3:
                if (Pos.y == 0) peca = new Alfil(define.WHITE);
                else peca = new Alfil(define.BLACK);
                break;
            case 4:
                if (Pos.y == 0) peca = new Reina(define.WHITE);
                else peca = new Reina(define.BLACK);
                break;
            default:
                if (Pos.y == 0) peca = new Rei(define.WHITE);
                else peca = new Rei(define.BLACK);
                break;

        }
        try {
            String file_path = define.icons_route + peca.getTipus() + peca.getColor()+".gif";

            final BufferedImage icon =
                    ImageIO.read(new File(file_path));
            this.add(new JLabel(new ImageIcon(icon)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
