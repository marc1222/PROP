package gui;

import domini.Peca;
import domini.Posicion;
import domini.Taulell;
import domini.define;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static domini.define.DARK_COLOR;
import static domini.define.LIGHT_COLOR;

public class Tile extends JPanel {
    private Posicion Pos;
    private static Dimension TILE_SIZE = new Dimension(77, 77);

    Tile(final GUITauler board, final Posicion id, Taulell T) {
        super(new GridBagLayout());
        this.Pos = id;
        setPreferredSize(TILE_SIZE);
        assign_color();
        assign_icon(T);
        validate();
    }

    private void assign_color() {
        Color background_color;
        if (this.Pos.y%2 == 1) { //fila senar
            if (this.Pos.x%2 == 0) { //columna parella
                background_color = LIGHT_COLOR;
            }
            else{
                //columna senar
                background_color = DARK_COLOR;
            }

        } else { //fila parella
            if (this.Pos.x%2 == 0) { //columna parella
                background_color = DARK_COLOR;
            } else {
                background_color = LIGHT_COLOR;
            }
        }
        setBackground(background_color);
    }
    private void assign_icon(final Taulell board) {
        this.removeAll();
        Peca act = board.getPecaPosicio(this.Pos);
        if (act.getTipus() != define.PECA_NULA) {
            try {
                String file_path = define.icons_route + act.getTipus() + act.getColor()+".gif";

                final BufferedImage icon =
                        ImageIO.read(new File(file_path));
                this.add(new JLabel(new ImageIcon(icon)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
