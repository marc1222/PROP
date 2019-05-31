package gui;

import domini.ControladorDomini;
import domini.Problema;
import domini.define;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.text.NumberFormat;
import java.text.ParseException;

public class FENProblema extends JPanel implements ActionListener {
    private JTextField tFen = new JTextField(35);
    private JFrame master;
    private VistaMenuPrincipal menuPrincipal;
    /*NumberFormat nf = NumberFormat.getInstance();
    NumberFormatter nfr = new NumberFormatter(nf);
    private JFormattedTextField tJug;*/
    private JTextField tJug = new JTextField();
    private JRadioButton rbBlanc = new JRadioButton("Blanc");
    private JRadioButton rbNegre = new JRadioButton("Negre");
    private ButtonGroup rbg = new ButtonGroup();
    private JButton bConf = new JButton("Confirmar");
    private JPanel rbPanel = new JPanel(new GridLayout(0, 1));
    private JLabel lFen = new JLabel("Introdueix el FEN del problema", JLabel.CENTER);
    private JLabel lJug = new JLabel("Introdueix el número de jugades del problema", JLabel.CENTER);
    private JLabel lPrim = new JLabel("Selecciona quin color comença", JLabel.CENTER);

    public FENProblema(JFrame master, VistaMenuPrincipal vmp) {
        this.master = master;
        this.menuPrincipal = vmp;
        rbBlanc.setFont (rbBlanc.getFont ().deriveFont (14.0f));
        rbNegre.setFont (rbNegre.getFont ().deriveFont (14.0f));
        lFen.setFont (lFen.getFont ().deriveFont (16.0f));
        lJug.setFont (lJug.getFont ().deriveFont (16.0f));
        lPrim.setFont (lPrim.getFont ().deriveFont (16.0f));
        bConf.setFont (bConf.getFont ().deriveFont (25.0f));
        rbBlanc.setHorizontalAlignment(SwingConstants.CENTER);
        rbNegre.setHorizontalAlignment(SwingConstants.CENTER);

        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        //frameVista.setLayout(new GridLayout(0, 1));
        this.setLayout(new GridLayout(0,1));

        /*nfr.setValueClass(Integer.class);
        nfr.setMaximum(10);
        nfr.setAllowsInvalid(false);
        nfr.setCommitsOnValidEdit(true);*/

        //tJug = new JFormattedTextField(nfr);
        tJug.setColumns(5);
        tJug.setPreferredSize(new Dimension(10, 50));
        tJug.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        //JOptionPane.showMessageDialog(null, tJug);

        rbBlanc.setSelected(true);
        rbg.add(rbBlanc);
        rbg.add(rbNegre);
        rbPanel.add(rbBlanc);
        rbPanel.add(rbNegre);
        bConf.addActionListener(this);

        /*frameVista.add(tFen, BorderLayout.PAGE_START);
        frameVista.add(tJug, BorderLayout.CENTER);
        frameVista.add(rbPanel, BorderLayout.LINE_END);
        frameVista.add(bConf, BorderLayout.PAGE_END);*/

        //frameVista.add(lFen);
        //frameVista.add(tFen);
        //frameVista.add(lJug);
        //frameVista.add(tJug);
        //frameVista.add(lPrim);
        //frameVista.add(rbPanel);
        //frameVista.add(bConf);

        this.add(lFen);
        this.add(tFen);
        this.add(lJug);
        this.add(tJug);
        this.add(lPrim);
        this.add(rbPanel);
        this.add(bConf);

        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);
    }

    public FENProblema(JFrame master, VistaMenuPrincipal vmp, String fen, int njug, int prim) {
        this.master = master;
        this.menuPrincipal = vmp;
        rbBlanc.setFont (rbBlanc.getFont ().deriveFont (14.0f));
        rbNegre.setFont (rbNegre.getFont ().deriveFont (14.0f));
        lFen.setFont (lFen.getFont ().deriveFont (16.0f));
        lJug.setFont (lJug.getFont ().deriveFont (16.0f));
        lPrim.setFont (lPrim.getFont ().deriveFont (16.0f));
        bConf.setFont (bConf.getFont ().deriveFont (25.0f));
        rbBlanc.setHorizontalAlignment(SwingConstants.CENTER);
        rbNegre.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new GridLayout(0,1));
        tJug.setColumns(5);
        tJug.setPreferredSize(new Dimension(10, 50));
        tJug.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        rbg.add(rbBlanc);
        rbg.add(rbNegre);
        rbPanel.add(rbBlanc);
        rbPanel.add(rbNegre);
        bConf.addActionListener(this);

        tJug.setText(String.valueOf(njug));
        tFen.setText(fen);
        if (prim == define.WHITE) rbBlanc.setSelected(true);
        else rbNegre.setSelected(true);

        this.add(lFen);
        this.add(tFen);
        this.add(lJug);
        this.add(tJug);
        this.add(lPrim);
        this.add(rbPanel);
        this.add(bConf);
    }

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
        String fen = tFen.getText();
        if (!fen.isEmpty()) {
            String jug = tJug.getText();
            if (!jug.isEmpty()) {
                int njug = Integer.parseInt(jug); // int?
                int color;
                final String ffen;
                if (rbBlanc.isSelected()) ffen = fen + " w";
                else ffen = fen + " b";
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Problema p = new Problema();
                        p.crear_problema(njug, ffen);
                    }
                }).start();*/
                int res = ControladorPresentacio.creaProblema(njug, ffen, menuPrincipal.getUsuari());
                if (res < 0) {
                    if (res == -2) JOptionPane.showMessageDialog(this, "FEN invàlid",
                            "Error en el FEN", JOptionPane.ERROR_MESSAGE);
                    else if (res == -3) JOptionPane.showMessageDialog(this, "El problema ja existeix",
                            "Problema ja creat", JOptionPane.ERROR_MESSAGE);
                    else JOptionPane.showMessageDialog(this, "Problema sense solució",
                                "Problema invàlid", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "S'ha validat i creat el problema correctament",
                            "Problema creat", JOptionPane.INFORMATION_MESSAGE);
                    MenuProblema mp = new MenuProblema(master, menuPrincipal);
                    master.setContentPane(mp);
                    //master.pack();
                    master.setVisible(true);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Escriu el número de jugades del problema",
                        "Error en el número de jugades", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Escriu el FEN del problema", "Error en el FEN",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
