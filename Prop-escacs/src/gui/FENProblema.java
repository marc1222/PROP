package gui;

import domini.define;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class FENProblema extends JPanel implements ActionListener {
    //private JFrame frameVista = new JFrame("Crear Problema FEN");
    //private JMenuBar menuBar = new JMenuBar();
    private JTextField tFen = new JTextField(35);
    //NumberFormat nf = NumberFormat.getIntegerInstance();
    NumberFormat nf = NumberFormat.getInstance();
    NumberFormatter nfr = new NumberFormatter(nf);
    private JFormattedTextField tJug;
    private JRadioButton rbBlanc = new JRadioButton("Blanc");
    private JRadioButton rbNegre = new JRadioButton("Negre");
    private ButtonGroup rbg = new ButtonGroup();
    private JButton bConf = new JButton("Confirmar");
    private JPanel rbPanel = new JPanel(new GridLayout(0, 1));
    private JLabel lFen = new JLabel("Introdueix el FEN del problema", JLabel.CENTER);
    private JLabel lJug = new JLabel("Introdueix el número de jugades del problema", JLabel.CENTER);
    private JLabel lPrim = new JLabel("Selecciona quin color comença", JLabel.CENTER);

    public FENProblema() {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        //frameVista.setLayout(new GridLayout(0, 1));
        this.setLayout(new GridLayout(0,1));
        nfr.setValueClass(Integer.class);
        nfr.setMaximum(10);
        nfr.setAllowsInvalid(false);
        nfr.setCommitsOnValidEdit(true);

        tJug = new JFormattedTextField(nfr);
        tJug.setColumns(5);
        tJug.setPreferredSize(new Dimension(10, 50));

        JOptionPane.showMessageDialog(null, tJug);

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

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
        String fen = tFen.getText();
        String sjug = (String) tJug.getValue(); // int?
        int color;
        if (rbBlanc.isSelected()) color = define.WHITE;
        else if (rbNegre.isSelected()) color = define.BLACK;
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

    private static void iniciar() {
        FENProblema vista = new FENProblema();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                iniciar();
            }
        });
    }
}
