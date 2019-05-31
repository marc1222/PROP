package gui;

import domini.ControladorDomini;
import javafx.scene.control.ComboBox;
import sun.misc.JavaLangAccess;

import javax.imageio.ImageIO;
import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static gui.define.DARK_COLOR;
import static gui.define.LIGHT_COLOR;

public class CrearProblema extends JPanel implements ActionListener {
    //private JFrame frameVista = new JFrame("Creació del problema");
    //private JMenuBar menuBar = new JMenuBar();

    //private Taulell taulell = new Taulell(tauler_buit());
    private JFrame master;
    private VistaMenuPrincipal menuPrincipal;
    private BarraPeces barraPeces = new BarraPeces();
    //private ControladorDomini cd;
    private TaulerCrearProblema tauler;
    private JButton bCont = new JButton("Continuar");

    private class BarraPeces extends JPanel implements MouseListener {
        private AfegirPeca[] peces = new AfegirPeca[13];

        public BarraPeces() {
            String tipus = "";
            int color = 0;
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 6; ++j) {
                    switch (j) {
                        case 0:
                            tipus = define.PEO;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 1:
                            tipus = define.TORRE;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 2:
                            tipus = define.CAVALL;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 3:
                            tipus = define.ALFIL;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 4:
                            tipus = define.REINA;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        default:
                            tipus = define.REI;
                            if (i == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                    }
                    AfegirPeca casella = new AfegirPeca(new Posicion(j, i), tipus, color);
                    casella.addMouseListener(this);
                    casella.setBorder(BorderFactory.createEmptyBorder());
                    casella.setPreferredSize(new Dimension(50,50));
                    this.add(casella);
                    peces[i*6 + j] = casella;

                }
            }
            AfegirPeca casella = new AfegirPeca(new Posicion(6, 1), tipus, color);
            casella.addMouseListener(this);
            casella.setBackground(Color.white);
            casella.setBorder(BorderFactory.createEmptyBorder());
            casella.setPreferredSize(new Dimension(50,50));
            this.add(casella);
            peces[12] = casella;
        }

        public void mouseClicked(MouseEvent me) {
            AfegirPeca ap = (AfegirPeca) me.getComponent();
            if (ap.getSelected()) {
                ap.setSelected(false);
                ap.setBorder(BorderFactory.createEmptyBorder());
            }
            else {
                for (AfegirPeca peca : peces) {
                    if (peca.getSelected()) {
                        peca.setSelected(false);
                        peca.setBorder(BorderFactory.createEmptyBorder());
                    }
                }
                ap.setSelected(true);
                ap.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.blue));
            }
        }

        public void mousePressed(MouseEvent me) {}

        public void mouseEntered(MouseEvent me) {}

        public void mouseExited(MouseEvent me) {}

        public void mouseReleased(MouseEvent me) {}

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class TaulerCrearProblema extends JPanel implements MouseListener {
        private Casella[][] caselles = new Casella[8][8];
        private ControladorDomini controladorDomini;

        private class Casella extends JPanel {
            private Posicion Pos;
            private String tipusP;
            private int colorP;
            private Dimension TILE_SIZE = new Dimension(77, 77);

            Casella (final Posicion id, String tipus, int color) {
                this.Pos = id;
                this.tipusP = tipus;
                this.colorP = color;
                setPreferredSize(TILE_SIZE);
                assign_color();
                //assign_icon(cd);
                assign_icon();
                validate();
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
                setBackground(background_color);
            }

            //private void assign_icon(ControladorDomini cd) {
            private void assign_icon() {
                this.removeAll();
                if (!tipusP.equals(define.PECA_NULA)) {
                    try {
                        String file_path = define.icons_route + tipusP + colorP +".png";
                        final BufferedImage icon =
                                ImageIO.read(new File(file_path));
                        this.add(new JLabel(new ImageIcon(icon)));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            //public void pinta_tile(ControladorDomini cd) {
            public void pinta_tile() {
                assign_color();
                //assign_icon(cd);
                assign_icon();
                validate();
                repaint();
            }

        }

        //////////////////////////////////////////////////////

        //public TaulerCrearProblema(ControladorDomini cd) {
        public TaulerCrearProblema() {
            super(new GridLayout(8,8));
            //this.controladorDomini = cd;
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    final Casella c = new Casella(new gui.Posicion(j,7-i), define.PECA_NULA, define.NULL_COLOR);
                    caselles[j][7-i] = c;
                    c.addMouseListener(this);
                    add(c);
                }
            }
            setPreferredSize(new Dimension(600,600));
            validate();
        }

        public TaulerCrearProblema(String tipus[][], int colors[][]) {
            super(new GridLayout(8,8));
            //this.controladorDomini = cd;
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    final Casella c = new Casella(new gui.Posicion(j,7-i), tipus[j][7-i], colors[j][7-i]);
                    caselles[j][7-i] = c;
                    c.addMouseListener(this);
                    add(c);
                }
            }
            setPreferredSize(new Dimension(600,600));
            validate();
        }

        public void mouseClicked(MouseEvent me) {
            Casella c = (Casella) me.getComponent();
            String tipus = "cap";
            int color = -1;
            for (AfegirPeca peca : barraPeces.peces) {
                if (peca.getSelected()) {
                    Posicion p = peca.getPos();
                    switch (p.x) {
                        case 0:
                            tipus = define.PEO;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 1:
                            tipus = define.TORRE;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 2:
                            tipus = define.CAVALL;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 3:
                            tipus = define.ALFIL;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 4:
                            tipus = define.REINA;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        case 5:
                            tipus = define.REI;
                            if (p.y == 0) color = define.WHITE;
                            else color = define.BLACK;
                            break;
                        default:
                            tipus = define.PECA_NULA;
                            break;
                    }
                }
            }
            if (!tipus.equals("cap")) { //?
                c.tipusP = tipus;
                c.colorP = color;
                this.pinta_tauler();
            }
        }

        public void mousePressed(MouseEvent me) {}

        public void mouseEntered(MouseEvent me) {}

        public void mouseExited(MouseEvent me) {}

        public void mouseReleased(MouseEvent me) {}

        public void pinta_tauler() {
            removeAll();
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    //this.caselles[j][7-i].pinta_tile(this.controladorDomini);
                    this.caselles[j][7-i].pinta_tile();
                    add(this.caselles[j][7-i]);
                }
            }
            validate();
            repaint();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    //public CrearProblema(ControladorDomini cd) {
    public CrearProblema(JFrame master, VistaMenuPrincipal vmp) {
        this.master = master;
        this.menuPrincipal = vmp;
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        //frameVista.add(barraPeces, BorderLayout.NORTH);
        this.add(barraPeces, BorderLayout.NORTH); // new?
        //frameVista.setResizable(false);
        //frameVista.add(gTauler, BorderLayout.CENTER);

        //this.cd = cd;
        //tauler = new TaulerCrearProblema(cd);
        tauler = new TaulerCrearProblema();
        this.add(tauler, BorderLayout.CENTER);
        bCont.setMinimumSize(new Dimension(500, 70));

        bCont.setFont (bCont.getFont ().deriveFont (25.0f));

        bCont.addActionListener(this);
        this.add(bCont, BorderLayout.PAGE_END);
        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);
    }

    public CrearProblema(JFrame master, VistaMenuPrincipal vmp, String fen, int njug) {
        this.master = master;
        this.menuPrincipal = vmp;

        String tipus[][] = new String[8][8];
        int colors[][] = new int[8][8];
        FENToGrafic(fen, tipus, colors);
        this.setLayout(new BorderLayout());
        this.add(barraPeces, BorderLayout.NORTH); // new?
        //this.cd = cd;
        //tauler = new TaulerCrearProblema(cd);
        tauler = new TaulerCrearProblema(tipus, colors);
        this.add(tauler, BorderLayout.CENTER);

        bCont.addActionListener(this);
        this.add(bCont, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
        String tipusPeces[][] = new String[8][8];
        int colors[][] = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                tipusPeces[i][j] = tauler.caselles[i][j].tipusP;
                colors[i][j] = tauler.caselles[i][j].colorP;
            }
        }

        JPanel aux = new JPanel(new GridLayout(0,1));
        JLabel latac = new JLabel("Quin color començarà jugant (atacant)?", JLabel.CENTER);
        String[] opatacs = {"Blanques", "Negres"};
        JComboBox cbatac = new JComboBox(opatacs);
        cbatac.setSelectedIndex(0);
        aux.add(latac);
        aux.add(cbatac);
        JLabel ljug = new JLabel("Selecciona el número de jugades del problema", JLabel.CENTER);
        String[] opjug = {"1", "2","3","4","5","6","7","8","9"};
        JComboBox cbjug = new JComboBox(opjug);
        cbjug.setSelectedIndex(0);
        aux.add(ljug);
        aux.add(cbjug);
        Object[] op = {"Valida"};
        int primer = JOptionPane.showOptionDialog(this, aux,
                "Atacant i número de jugades", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
        if (primer == 0) {
            String fen = ControladorPresentacio.graficToFEN(tipusPeces, colors, cbatac.getSelectedIndex());
            int res = ControladorPresentacio.creaProblema(cbjug.getSelectedIndex() + 1, fen, menuPrincipal.getUsuari());
            if (res < 0) {
                if (res == -2) JOptionPane.showMessageDialog(this, "FEN invàlid",
                        "Error en el FEN", JOptionPane.ERROR_MESSAGE);
                else if (res == -3) JOptionPane.showMessageDialog(this, "El problema ja existeix",
                        "Problema ja creat", JOptionPane.ERROR_MESSAGE);
                else JOptionPane.showMessageDialog(this, "Problema sense solució",
                            "Problema invàlid", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "S'ha validat i creat el problema correctament",
                        "Problema creat", JOptionPane.INFORMATION_MESSAGE);
                MenuProblema mp = new MenuProblema(master, menuPrincipal);
                master.setContentPane(mp);
                //master.pack();
                master.setVisible(true);
            }
        }
    }

    public void FENToGrafic(String fen, String[][] tipus, int[][] colors) {
        int k = 0;
        for (int i = 7; i >= 0; --i) {
            for (int j = 0; j < 8; ++j) {
                char act = fen.charAt(k);
                if ((act >= 'a' && act <= 'z') || (act >= 'A' && act <= 'Z')) { //class character method
                    switch(act) {
                        case 'r':
                            tipus[j][i] = define.TORRE;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'n':
                            tipus[j][i] = define.CAVALL;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'b':
                            tipus[j][i] = define.ALFIL;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'q':
                            tipus[j][i] = define.REINA;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'k':
                            tipus[j][i] = define.REI;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'p':
                            tipus[j][i] = define.PEO;
                            colors[j][i] = define.BLACK;
                            break;
                        case 'R':
                            tipus[j][i] = define.TORRE;
                            colors[j][i] = define.WHITE;
                            break;
                        case 'N':
                            tipus[j][i] = define.CAVALL;
                            colors[j][i] = define.WHITE;
                            break;
                        case 'B':
                            tipus[j][i] = define.ALFIL;
                            colors[j][i] = define.WHITE;
                            break;
                        case 'Q':
                            tipus[j][i] = define.REINA;
                            colors[j][i] = define.WHITE;
                            break;
                        case 'K':
                            tipus[j][i] = define.REI;
                            colors[j][i] = define.WHITE;
                            break;
                        case 'P':
                            tipus[j][i] = define.PEO;
                            colors[j][i] = define.WHITE;
                            break;
                        default:
                            //
                            break;
                    }
                }
                else if (act >= '0' && act <= '8'){                             //class character method
                    for (int z = 0; z < Character.getNumericValue(act); ++z) {
                        tipus[j][i] = define.PECA_NULA;
                        colors[j][i] = define.NULL_COLOR;
                        ++j;
                    }
                    --j;
                }
                ++k;
            }
            ++k;
        }
    }
}
