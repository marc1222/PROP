package gui;

import domini.Posicion;
import domini.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUITauler extends JPanel {

    private static Dimension BOARD_SIZE = new Dimension(600,600);
    final java.util.List<Tile> cuadrants;
    private Taulell master_tauler;

    GUITauler(Taulell T) {
        super(new GridLayout(8,8));
        this.master_tauler = T;
        this.cuadrants = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                final Tile tile = new Tile(this, new Posicion(i,7-j), this.master_tauler);
                this.cuadrants.add(tile);
                add(tile);
            }
        }
        setPreferredSize(BOARD_SIZE);
        validate();

    }
}
