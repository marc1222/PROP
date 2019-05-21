package gui;

import domini.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CrearProblema implements MouseListener {
    private JFrame frameVista = new JFrame("Creació del problema");
    private JMenuBar menuBar = new JMenuBar();
    private Taulell taulell = new Taulell(tauler_buit());
    private GUITauler gTauler = new GUITauler(taulell);
    private JPanel barraPeces = new JPanel();

    private Peca[][] tauler_buit() {
        Peca[][] tau = new Peca[8][8];
        for (int i = 0; i<8; ++i) {
            for (int j = 0; j < 8; ++j) {
                tau[i][j] = new Peca_Nula();
            }
        }
        return tau;
    }

    public CrearProblema() {
        menuBar.add(crea_menu());
        frameVista.setJMenuBar(menuBar);
        frameVista.setLayout(new BorderLayout());
        init_barraPeces();
        frameVista.add(barraPeces, BorderLayout.NORTH);
        //frameVista.setResizable(false);
        frameVista.add(gTauler, BorderLayout.CENTER);
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

    private void init_barraPeces() {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 6; ++j) {
                AfegirPeca casella = new AfegirPeca(new Posicion(j, i));
                casella.addMouseListener(this);
                barraPeces.add(casella);
            }
        }
    }

    public void mouseClicked(MouseEvent me) {
        JPanel p = (JPanel) me.getComponent();
        //p.setBackground(Color.blue);
    }

    public void mousePressed(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {}

    public void mouseExited(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}

    private static void iniciar() {
        CrearProblema vista = new CrearProblema();
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
