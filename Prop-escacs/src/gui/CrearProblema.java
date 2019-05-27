package gui;

import domini.ControladorDomini;

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

    private BarraPeces barraPeces = new BarraPeces();
    private ControladorDomini cd;
    private TaulerCrearProblema tauler;
    private JButton bCont = new JButton("Confirmar i validar problema");
    private JPanel pJug = new JPanel(); // = new JPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
    private JTextField tJug = new JTextField();
    private JLabel lJug = new JLabel("<html>Escriu el número de<br/>jugades del problema</html>");

    private class BarraPeces extends JPanel implements MouseListener {
        private AfegirPeca[] peces = new AfegirPeca[13];

        public BarraPeces() {
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 6; ++j) {
                    AfegirPeca casella = new AfegirPeca(new Posicion(j, i));
                    casella.addMouseListener(this);
                    casella.setBorder(BorderFactory.createEmptyBorder());
                    this.add(casella);
                    peces[i*6 + j] = casella;

                }
            }
            AfegirPeca casella = new AfegirPeca(new Posicion(6, 1));
            casella.addMouseListener(this);
            casella.setBackground(Color.white);
            casella.setBorder(BorderFactory.createEmptyBorder());
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
                assign_icon(cd);
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

            private void assign_icon(ControladorDomini cd) {
                this.removeAll();
                //String tipus = cd.getPecaTipus(this.Pos.x, this.Pos.y);
                if (!tipusP.equals(define.PECA_NULA)) {
                    try {
                        String file_path = define.icons_route + tipusP + colorP +".gif";
                        final BufferedImage icon =
                                ImageIO.read(new File(file_path));
                        this.add(new JLabel(new ImageIcon(icon)));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            public void pinta_tile(ControladorDomini cd) {
                assign_color();
                assign_icon(cd);
                validate();
                repaint();
            }

        }

        //////////////////////////////////////////////////////

        public TaulerCrearProblema(ControladorDomini cd) {
            super(new GridLayout(8,8));
            this.controladorDomini = cd;
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
                    this.caselles[j][7-i].pinta_tile(this.controladorDomini);
                    add(this.caselles[j][7-i]);
                }
            }
            validate();
            repaint();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public CrearProblema(ControladorDomini cd) {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        //frameVista.add(barraPeces, BorderLayout.NORTH);
        this.add(barraPeces, BorderLayout.NORTH); // new?
        //frameVista.setResizable(false);
        //frameVista.add(gTauler, BorderLayout.CENTER);
        this.cd = cd;
        tauler = new TaulerCrearProblema(cd);
        this.add(tauler, BorderLayout.CENTER);

        tJug.setMaximumSize(new Dimension(100, 50));
        tJug.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        pJug.setLayout(new BoxLayout(pJug, BoxLayout.Y_AXIS));
        tJug.setAlignmentX(CENTER_ALIGNMENT);
        lJug.setAlignmentX(CENTER_ALIGNMENT);
        pJug.add(lJug);
        pJug.add(tJug);
        this.add(pJug, BorderLayout.LINE_END);

        bCont.addActionListener(this);
        this.add(bCont, BorderLayout.PAGE_END);
        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);
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
        Object[] atacant = {"Blanques", "Negres"};
        int primer = JOptionPane.showOptionDialog(this, "Quin color començarà jugant (atacant)?",
                "Escull color atacant", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, atacant, atacant[1]);
        int njug = Integer.parseInt(tJug.getText());

    }

    private JMenu crea_menu() {
        final JMenu aux = new JMenu("Options");
        final JMenuItem exit = new JMenuItem("Exit app");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        aux.add(exit);
        return aux;
    }
    /*
    private void init_barraPeces() {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 6; ++j) {
                AfegirPeca casella = new AfegirPeca(new Posicion(j, i));
                casella.addMouseListener(this);
                casella.setBorder(BorderFactory.createEmptyBorder());
                barraPeces.add(casella);
            }
        }
    }

    public void mouseClicked(MouseEvent me) {
        AfegirPeca ap = (AfegirPeca) me.getComponent();
        if (ap.getSelected()) {
            ap.setSelected(false);
            ap.setBorder(BorderFactory.createEmptyBorder());
        }
        else {
            ap.setSelected(true);
            ap.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.blue));

        }
    }

    public void mousePressed(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {}

    public void mouseExited(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}*/

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CrearProblema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        CrearProblema newContentPane = new CrearProblema(new ControladorDomini());
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
