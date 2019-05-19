package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccioProblema {
    private JFrame frameVista = new JFrame("Selecció de problema");
    private JMenuBar menuBar = new JMenuBar();
    private TProblemes llistaProblemes = new TProblemes();


    public SeleccioProblema() {
        menuBar.add(crea_menu());
        frameVista.setJMenuBar(menuBar);
        frameVista.setLayout(new BorderLayout());
        //frameVista.setResizable(false);
        frameVista.add(llistaProblemes, BorderLayout.CENTER);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.pack();
        frameVista.setVisible(true);
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
