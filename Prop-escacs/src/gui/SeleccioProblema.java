package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SeleccioProblema implements ActionListener {
    private JFrame frameVista = new JFrame("Selecció de problema");
    private JMenuBar menuBar = new JMenuBar();
    private TProblemes llistaProblemes = new TProblemes();
    private JButton bcont = new JButton("Continua");


    public SeleccioProblema() {
        menuBar.add(crea_menu());
        frameVista.setJMenuBar(menuBar);
        frameVista.setLayout(new BorderLayout());
        //frameVista.setResizable(false);
        frameVista.add(llistaProblemes, BorderLayout.CENTER);
        bcont.setMnemonic(KeyEvent.VK_C); //Alt+C
        bcont.addActionListener(this);
        frameVista.add(bcont, BorderLayout.PAGE_END);

        //frameVista.getRootPane().setDefaultButton(bcont);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.pack();
        frameVista.setVisible(true);
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
        SeleccioProblema vista = new SeleccioProblema();
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
