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
            SelectedBorder = new Contorn(5, 5, 5, 5, define.BoardBorderColor);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!board.simulacio) {
                        if ((e).getButton() == MouseEvent.BUTTON1) {
                            //LEFT CLICK HANDLER
                            String tipus = T.getPecaTipus(Pos.x, Pos.y);
                            if (tipus.equals(define.PECA_NULA)) { //PECA NULA SELECCIONADA
                                Posicion sel = board.getPosSelected();
                                if (sel != null) {
                                    //tenim una peca seleccionada
                                    if (is_move(T.getAll_movs(sel.x,sel.y), Pos, T)) {
                                        board.setPosDest(Pos);
                                        board.make_move();
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
                                            board.make_move();
                                        }
                                    }
                                    else {
                                        //No tenim peça seleccionada -> no fem res ja que no es nostre
                                    }
                                }
                            }
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
                    String file_path = define.icons_route + tipus + DC.getPecaColor(this.Pos.x, this.Pos.y) +".png";
                    final BufferedImage icon =
                            ImageIO.read(new File(file_path));
                    this.add(new JLabel(new ImageIcon(icon)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void Select(ControladorDomini T) {
            this.setBorder(this.SelectedBorder.getBorder());
//            assign_color();
//            assign_icon(T);
//            validate();
//            repaint();
        }
        private void UnSelect(ControladorDomini T) {
            this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 0));
//            assign_color();
//            assign_icon(T);
//            validate();
//            repaint();
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
            assign_icon(T);
            assign_color();
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
    private History historial;
    private boolean simulacio;
    private JugarPartidaView parent;
    public Thread compute_thread;
    public boolean finish_sim = false;


    GUITauler(ControladorDomini CDP, GUIOption option, boolean sim, JugarPartidaView parent) {
        super(new GridLayout(8,8));
        this.DomainController = CDP;
        this.option_panel = option;
        this.historial = new History();
        this.parent = parent;
        this.PosSelected = null;
        this.PosDest = null;
        this.cuadrants = new Tile[8][8];
        this.simulacio = sim;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                final Tile tile = new Tile(this, new gui.Posicion(j,7-i), this.DomainController);
                cuadrants[j][7-i] = tile;
                add(tile);
            }
        }
        setPreferredSize(BOARD_SIZE);
        this.Borde_tauler = new Contorn(20, 50, 20, 50, define.BoardBorderColor);
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
    public History.Movement[] getHistory() {
        return this.historial.getAll();
    }
    public void make_move() {
        Posicion from = this.PosSelected;
        Posicion to = this.PosDest;

        int ret = DomainController.juga_torn(from.x, from.y, to.x, to.y);
        System.out.println("FROM: ["+from.x+","+from.y+"] -> TO: ["+to.x+","+to.y+"] --> RESULT: "+ret);

        if (ret != -2) {
            //fer cosesupda
            cuadrants[from.x][from.y].UnSelect(DomainController);
            //MOVIMENT OK
            historial.add(DomainController.colorJugadorNoActual(), from, to);
            PosSelected = null;
            PosDest = null;

            if (ret == -1) {
                //ENCENEM / ATUREM EL CRONO SI SOM EL JUGADOR QUE TIRA
                option_panel.update_stats();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pinta_tauler();
                    }
                });

                if (DomainController.tipusJugadorActual() == define.MAQUINA) {
                    simulacio = true;
                    //EN AQUEST PUNT JA HEM TIRAT I EL USER ACTUAL DE PARTIDA JA HA CANVIAT
                    //LLAVORS VEIEM SI EL SEGUENT JUGADOR QUE TIRA ES LA MAQUINA
                    //PER TAL DE FER AQUEST SEGUIT DE COSES:
                    //      1 - en un nou thread, executem el seu moviment
                    //      2 - activem la simulacio, que desactivarem quan es faci el moviment
                    ;

                    compute_thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int ret;
                            try {
                                do {
                                    //fer cosesupda
                                    compute_thread.sleep(500);
                                    int x0=0,y0=0, x=0,y=0;
                                    int res[] = DomainController.juga_tornMaquina(x0, y0, x, y);
                                    if (parent.pause) {
                                        synchronized (compute_thread) {
                                            if (parent.pause) compute_thread.wait();
                                        }
                                    }                                ret = res[0];
                                    System.out.println("FROM: [" + res[1] + "," + res[2] + "] -> TO: [" + res[3] + "," + res[4] + "] --> RESULT: " + ret);

                                    // cuadrants[from.x][from.y].UnSelect(DomainController);
                                    //MOVIMENT OK

                                    if (ret == -1) {
                                        //ENCENEM / ATUREM EL CRONO SI SOM EL JUGADOR QUE TIRA
                                        option_panel.update_stats();
                                        historial.add(DomainController.colorJugadorNoActual(), new Posicion(res[1],res[2]), new Posicion(res[3],res[4]));

                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                simulacio = false;
                                                pinta_tauler();

                                            }
                                        });

                                    } else if (ret != -2) {
                                        //juga torn ha retornat un guanyador
                                        pinta_tauler();
                                        Object[] options = {"Continua"};
                                        JLabel msg1 = new JLabel("Han guanyat les "+((ret==define.WHITE)?"Blanques":"Negres"),JLabel.CENTER);
                                        msg1.setFont (msg1.getFont ().deriveFont (12.0f));

                                        JLabel msg2 = new JLabel("\nHas "+(ret==DomainController.getMasterColor()?":D Guanyat :D":"D: Perdut D:"), JLabel.CENTER);
                                        msg2.setFont (msg2.getFont ().deriveFont (16.0f));

                                        JPanel aux = new JPanel(new GridLayout(0,1));
                                        aux.add(msg1);
                                        aux.add(msg2);
                                        aux.setPreferredSize(new Dimension(90, 60));

                                        int input = JOptionPane.showOptionDialog(GUITauler.this, aux ,"Final de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                                        if (input == 0) {
                                            //SEGUNET CLICK TRIGGERED
                                            DomainController.save_stats(ret, option_panel.getclock());

                                            parent.tornaMenu();

                                        }
                                    }
                                } while(ret == -2);
                            } catch (InterruptedException ex) {
                               System.out.print("INTERRUPTED");
                            }
                        }
                    });
                    compute_thread.start();
                }


            } else {
                //juga torn ha retornat un guanyador
                option_panel.stop_timer();

                //TODO TORNAR AL MENU PRINCIPAL DESPRES DE FER HANDLE AL OK QUE IMPRIMIRA AQUEST POPUP
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pinta_tauler();
                        Object[] options = {"Continua"};
                        JLabel msg1 = new JLabel("Han guanyat les "+((ret==define.WHITE)?"Blanques":"Negres"),JLabel.CENTER);
                        msg1.setFont (msg1.getFont ().deriveFont (12.0f));

                        JLabel msg2 = new JLabel("\nHas "+(ret==DomainController.getMasterColor()?":D Guanyat :D":"D: Perdut D:"), JLabel.CENTER);
                        msg2.setFont (msg2.getFont ().deriveFont (16.0f));

                        JPanel aux = new JPanel(new GridLayout(0,1));
                        aux.add(msg1);
                        aux.add(msg2);
                        aux.setPreferredSize(new Dimension(90, 60));

                        int input = JOptionPane.showOptionDialog(GUITauler.this, aux ,"Final de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        if (input == 0) {
                            //SEGUNET CLICK TRIGGERED
                            DomainController.save_stats(ret, option_panel.getclock());
                            parent.tornaMenu();
                        }
                    }
                });
            }


        }
        else {
            //no parem rellotge, i pintem tauler i enseñem popup mov incorrecte
            Object[] options = {"D'acord"};
            //System.out.println("MOVIMENT INCORRECTE!!! - FIXATHI BE!!!");

            JLabel msg = new JLabel("<html>La situació acual del tauler, provoca que<br/>aquest moviment sigui invàlid <br/>------------------------------------------------------------- <br/>Fixat-hi bé!</html>", SwingConstants.CENTER);
            msg.setHorizontalAlignment(JLabel.CENTER);
            msg.setFont (msg.getFont ().deriveFont (13.0f));
            JPanel aux = new JPanel(new GridLayout(0,1));
            aux.setPreferredSize(new Dimension(320, 100 ));
            aux.add(msg);
            JOptionPane.showOptionDialog(GUITauler.this, aux ,"Moviment Incorrecte", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        }


    }

    public void make_moveMaquina() {
        simulacio = true;
        compute_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ret;
                try {
                    do {
                        //fer cosesupda
                        compute_thread.sleep(500);
                        int x0=0,y0=0, x=0,y=0;
                        int res[] = DomainController.juga_tornMaquina(x0, y0, x, y);
                        if (parent.pause) {
                            synchronized (compute_thread) {
                                if (parent.pause) compute_thread.wait();
                            }
                        }

                        ret = res[0];
                        System.out.println("FROM: [" + res[1] + "," + res[2] + "] -> TO: [" + res[3] + "," + res[4] + "] --> RESULT: " + ret);

                        // cuadrants[from.x][from.y].UnSelect(DomainController);
                        //MOVIMENT OK

                        if (ret == -1) {
                            //ENCENEM / ATUREM EL CRONO SI SOM EL JUGADOR QUE TIRA
                            option_panel.update_stats();
                            historial.add(DomainController.colorJugadorNoActual(), new Posicion(res[1],res[2]), new Posicion(res[3],res[4]));

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    simulacio = false;
                                    pinta_tauler();

                                }
                            });

                        } else if (ret != -2) {
                            //juga torn ha retornat un guanyador
                            pinta_tauler();
                            Object[] options = {"Continua"};
                            JLabel msg1 = new JLabel("Han guanyat les "+((ret==define.WHITE)?"Blanques":"Negres"),JLabel.CENTER);
                            msg1.setFont (msg1.getFont ().deriveFont (12.0f));

                            JLabel msg2 = new JLabel("\nHas "+(ret==DomainController.getMasterColor()?":D Guanyat :D":"D: Perdut D:"), JLabel.CENTER);
                            msg2.setFont (msg2.getFont ().deriveFont (16.0f));

                            JPanel aux = new JPanel(new GridLayout(0,1));
                            aux.add(msg1);
                            aux.add(msg2);
                            aux.setPreferredSize(new Dimension(90, 60));

                            int input = JOptionPane.showOptionDialog(GUITauler.this, aux ,"Final de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                            if (input == 0) {
                                DomainController.save_stats(ret, option_panel.getclock());
                                parent.tornaMenu();
                            }
                        }
                    } while(ret == -2);
                } catch (InterruptedException ex) {
                    System.out.print("INTERRUPTED");
                }
            }
        });
        compute_thread.start();

    }

    /**
     *
     */
    public void jugaSimulacio() {
        compute_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        int ret;
                        boolean finish = false;
                        while (!finish_sim && !finish) {
                            do {
                                //fer cosesupda
                                compute_thread.sleep(1000);
                                int x0=0,y0=0, x=0,y=0;
                                int res[] = DomainController.juga_tornMaquina(x0, y0, x, y);
                                if (parent.pause) {
                                    synchronized (compute_thread) {
                                        if (parent.pause) compute_thread.wait();
                                    }
                                }
                                if (compute_thread.isInterrupted()) {
                                    finish_sim = true;
                                    compute_thread = new Thread();
                                }
                                ret = res[0];
                                System.out.println("FROM: [" + res[1] + "," + res[2] + "] -> TO: [" + res[3] + "," + res[4] + "] --> RESULT: " + ret);

                                // cuadrants[from.x][from.y].UnSelect(DomainController);
                                //MOVIMENT OK

                                if (ret == -1) {
                                    //ENCENEM / ATUREM EL CRONO SI SOM EL JUGADOR QUE TIRA

                                    option_panel.update_stats();
                                    historial.add(DomainController.colorJugadorNoActual(), new Posicion(res[1],res[2]), new Posicion(res[3],res[4]));

                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            pinta_tauler();
                                        }
                                    });

                                } else if (ret != -2) {
                                    //juga torn ha retornat un guanyador
                                    pinta_tauler();
                                    Object[] options = {"Continua"};
                                    JLabel msg1 = new JLabel("Han guanyat les "+((ret==define.WHITE)?"Blanques":"Negres"),JLabel.CENTER);
                                    msg1.setFont (msg1.getFont ().deriveFont (14.0f));

                                    JPanel aux = new JPanel(new GridLayout(0,1));
                                    aux.add(msg1);
                                    aux.setMinimumSize(new Dimension(110, 40));

                                    int input = JOptionPane.showOptionDialog(GUITauler.this, aux ,"Final de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                                    if (input == 0) {
                                        finish = true;
                                        parent.juga_simulacio();
                                    }
                                }
                            } while(ret == -2);
                        }


                } catch (InterruptedException ex) {
                    System.out.print("EXXXXXXXEPTIONNNN");
                }

            }
        });
        compute_thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        compute_thread.start();

    }
}
