package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProblema extends JPanel implements ActionListener {
    //private JFrame frameVista = new JFrame("Crear Problema FEN");
    //private JMenuBar menuBar = new JMenuBar();
    private JButton bAFEN = new JButton("Crear problema");
    private JButton bMFEN = new JButton("Modificar problema");
    private JLabel lFEN = new JLabel("Afegir problema FEN", JLabel.CENTER);
    private JButton bAG = new JButton("Crear problema");
    private JButton mAG = new JButton("Modificar problema");
    private JLabel lG = new JLabel("Afegir problema gràficament", JLabel.CENTER);
    private JButton bBorrar = new JButton("Borrar problema");
    private JPanel lrow = new JPanel(new GridLayout(1,2));
    private JPanel crow = new JPanel(new GridLayout(1,2));
    private JPanel mrow = new JPanel(new GridLayout(1,2));


    public MenuProblema() {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);

        //frameVista.setLayout(new GridLayout(4, 1));
        this.setLayout(new GridLayout(4, 1));
        lrow.add(lFEN);
        lrow.add(lG);
        //frameVista.add(lrow);
        this.add(lrow);
        bAFEN.addActionListener(this);
        crow.add(bAFEN);
        bAG.addActionListener(this);
        crow.add(bAG);
        //frameVista.add(crow);
        this.add(crow);
        bMFEN.addActionListener(this);
        mrow.add(bMFEN);
        mAG.addActionListener(this);
        mrow.add(mAG);
        //frameVista.add(mrow);
        this.add(mrow);
        //frameVista.add(bBorrar);
        this.add(bBorrar);

        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
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
        MenuProblema vista = new MenuProblema();
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
