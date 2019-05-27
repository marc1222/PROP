package gui;

import domini.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GUITauler extends JPanel {

    private class Tile extends JPanel {
        private gui.Posicion Pos;
        private Dimension TILE_SIZE = new Dimension(77, 77);
        private Contorn SelectedBorder;
        private boolean valid_mov;
        private Color MainColor;

        Tile(GUITauler board, gui.Posicion id, ControladorDomini T) {
            super(new GridBagLayout());
            this.Pos = id;
            this.valid_mov = false;
            setPreferredSize(TILE_SIZE);
            assign_color();
            assign_icon(T);
            SelectedBorder = new Contorn(5, define.BoardBorderColor);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    boolean flag = false;
                    if ((e).getButton() == MouseEvent.BUTTON1) {
                        //LEFT CLICK HANDLER
                        String tipus = T.getPecaTipus(Pos.x, Pos.y);
                        if (tipus.equals(define.PECA_NULA)) { //PECA NULA SELECCIONADA
                            Posicion sel = board.getPosSelected();
                            if (sel != null) {
                              //tenim una peca seleccionada
                                if (is_move(T.getAll_movs(sel.x,sel.y), Pos, T)) {
                                    board.setPosDest(Pos);
                                    flag = true;
                                }
                            }
                            else {
                              //no tenim peca seleccionada -> no fem re
                            }
                        }
                        else {
                            //HA SELECCIONAT UNA PECA NO NULA
                            int color = T.getPecaColor(Pos.x, Pos.y);
                            if (color == T.colorJugadorActual()) {
                                //PEÇA DEL JUGADOR QUE TIRA
                                //1 - DESCELECCIONEM LA PEÇA ACTUAL
                                if (board.getPosSelected() != null) {
                                    gui.Posicion act = board.getPosSelected();
                                    board.cuadrants[act.x][act.y].UnSelect(T);
                                }
                                //2 - SELECCIONEM AQUEST GRAFICAMENT
                                Select(T);
                                //3 - ACTUALITZEM ACTUAL
                                board.setPosSelected(Pos);
                                //4 - SELECCIONEM LES PECES QUE CAL SELECCIONAR DONCS SON MOVS VALIDS
                                marcar_movs_valids(board, T.getAll_movs(Pos.x,Pos.y));

                            }
                            else {
                                //PEÇA DEL OPENENT
                                Posicion sel = board.getPosSelected();
                                if (board.getPosSelected() != null) {
                                    //tenim una peca seleccionada
                                    if (is_move(T.getAll_movs(sel.x,sel.y), Pos, T)) {
                                        board.setPosDest(Pos);
                                        flag = true;
                                    }
                                }
                                else {
                                    //No tenim peça seleccionada -> no fem res ja que no es nostre
                                }
                            }
                        }
                    }

                    if (flag) {
                        Posicion from = board.getPosSelected();
                        Posicion to = board.getPosDest();

                        int ret = T.juga_torn(from.x, from.y, to.x, to.y);
                        System.out.println("FROM: ["+from.x+","+from.y+"] -> TO: ["+to.x+","+to.y+"] --> RESULT: "+ret);

                        if (ret != -2) {
                            //fer cosesupda
                            board.cuadrants[from.x][from.y].UnSelect(T);
                            //MOVIMENT OK
                            board.PosSelected = null;
                            board.PosDest = null;

                            if (ret == -1) {
                                //ENCENEM / ATUREM EL CRONO SI SOM EL JUGADOR QUE TIRA
                                option_panel.update_stats();

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.pinta_tauler();
                                    }
                                });

                            } else {
                                //TODO TORNAR AL MENU PRINCIPAL DESPRES DE FER HANDLE AL OK QUE IMPRIMIRA AQUEST POPUP
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        board.pinta_tauler();
                                        System.out.println("Han gunayat les "+((ret==define.WHITE)?"BLANQUES":"NEGRES"));
                                        System.out.println("HAS "+((ret==T.getMasterColor())?"GUAYNAT :D :D :D :D":"PERDUT D: D: D: D:"));

                                    }
                                });
                            }


                        }
                        else {
                            //no parem rellotge, i pintem tauler de nou loggegant
                            System.out.println("MOVIMENT INCORRECTE!!! - FIXATHI BE!!!");
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            validate();
        }

        public boolean is_move(Integer[][] moves, Posicion check, ControladorDomini T) {
            for (Integer[] move : moves ) {
                Posicion act = new Posicion(move[0],move[1]);
                if (act.x == check.x && act.y == check.y) return true;
            }
            return false;
        }
        public boolean isValid_mov() {
            return valid_mov;
        }

        public void setValid_mov(boolean valid_mov) {
            this.valid_mov = valid_mov;
        }
        private void assign_color() {
            Color background_color;
            if (this.Pos.y%2 == 1) { //fila senar
                if (this.Pos.x%2 == 0) { //columna parella
                    background_color = define.LIGHT_COLOR;
                }
                else{
                    //columna senar
                    background_color = define.DARK_COLOR;
                }

            } else { //fila parella
                if (this.Pos.x%2 == 0) { //columna parella
                    background_color = define.DARK_COLOR;
                } else {
                    background_color = define.LIGHT_COLOR;
                }
            }
            this.MainColor = background_color;
            setBackground(background_color);
        }

        private void assign_icon(ControladorDomini DC) {
            this.removeAll();
            String tipus = DC.getPecaTipus(this.Pos.x, this.Pos.y);
            if (!tipus.equals(define.PECA_NULA)) {
                try {
                    String file_path = define.icons_route + tipus + DC.getPecaColor(this.Pos.x, this.Pos.y) +".gif";
                    final BufferedImage icon =
                            ImageIO.read(new File(file_path));
                    this.add(new JLabel(new ImageIcon(icon)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void Select(ControladorDomini T) {
            removeAll();
            this.setBorder(this.SelectedBorder.getBorder());
            assign_color();
            assign_icon(T);
            validate();
            repaint();
        }
        private void UnSelect(ControladorDomini T) {
            removeAll();
            this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 0));
            assign_color();
            assign_icon(T);
            validate();
            repaint();
        }
        private void marcar_movs_valids(GUITauler board, Integer[][] marca) {
            Color default_color;
            //desmarquem totes les pos
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    default_color = board.cuadrants[i][j].getMainColor();
                    if (default_color != board.cuadrants[i][j].getBackground()) {
                        board.cuadrants[i][j].setTileBackgroundColor(default_color);
                    }
                }
            }
            //marquem les pertinents
            for (int i = 0; i < marca.length; ++i) {
                board.cuadrants[marca[i][0]][marca[i][1]].setTileBackgroundColor(define.TileSelectedColor);
            }
        }
        private void setTileBackgroundColor(Color color) {
            setBackground(color);
        }

        public Color getMainColor() {
            return this.MainColor;
        }

        public void pinta_tile(ControladorDomini T) {
            this.valid_mov = false;
            assign_color();
            assign_icon(T);
            validate();
            repaint();
        }
    }

    private static Dimension BOARD_SIZE = new Dimension(600,600);
    final Tile[][] cuadrants;
    private ControladorDomini DomainController;
    private gui.Posicion PosSelected,PosDest;
    private Contorn Borde_tauler;
    private GUIOption option_panel;

    GUITauler(ControladorDomini CDP, GUIOption option) {
        super(new GridLayout(8,8));
        this.DomainController = CDP;
        this.option_panel = option;
        this.PosSelected = null;
        this.PosDest = null;
        this.cuadrants = new Tile[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                final Tile tile = new Tile(this, new gui.Posicion(j,7-i), this.DomainController);
                cuadrants[j][7-i] = tile;
                add(tile);
            }
        }
        setPreferredSize(BOARD_SIZE);
        this.Borde_tauler = new Contorn(20, define.BoardBorderColor);
        this.setBorder(this.Borde_tauler.getBorder());
        validate();
    }


    public void pinta_tauler() {
        removeAll();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                this.cuadrants[j][7-i].pinta_tile(this.DomainController);
                add(this.cuadrants[j][7-i]);
            }
        }
        validate();
        repaint();
    }

    public Posicion getPosDest() {
        return PosDest;
    }

    public void setPosDest(Posicion posDest) {
        PosDest = posDest;
    }

    public gui.Posicion getPosSelected() {
        return PosSelected;
    }

    public void setPosSelected(gui.Posicion posSelected) {
        PosSelected = posSelected;
    }
    public void marca_pos(int x, int y) {
        this.cuadrants[x][y].setTileBackgroundColor(define.TileSelectedColor);
    }
    public void desmarca_pos(int x, int y) {
        this.cuadrants[x][y].setTileBackgroundColor(this.cuadrants[x][y].getMainColor());
    }
}
