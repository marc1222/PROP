package gui;

import domini.*;
import domini.define;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameFrame {

    private JFrame gameFrame;
    private static Dimension SCREEN_SIZE = new Dimension(730,730);
    private ControladorDomini DomainController;
    String usuari;

    private JMenuBar MenuBar;

   // private ControladorDominiPartida PartidaController;
//    private ControladorDominiJugador JugadorController;
//    private ControladorDominiEstadistica StatController;
//    private ControladorDominiProblema ProblemController;

    final JMenu aux_menu = new JMenu("Options");
    final JMenuItem exit = new JMenuItem("Exit app");
    final JMenuItem abandonar = new JMenuItem("Tornar al menú");

    final JMenu game_options = new JMenu("Opcions Partida");
    final JMenuItem pausa_partida = new JMenuItem("Pausar partida");
    final JMenuItem historial = new JMenuItem("Mira historial");

    public GameFrame() {
        this.gameFrame = new JFrame("Escacs");
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setResizable(false);
        fill_menu_bar();
        (this.gameFrame).setJMenuBar(this.MenuBar);
        this.gameFrame.setSize(SCREEN_SIZE);

        usuari = "Convidat";

        init_domain_controller();
        VistaInici startview = new VistaInici(this, DomainController);

        //VistaEstadistica ve = new VistaEstadistica(this, DomainController, "usr1");
        gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                JLabel msg = new JLabel("<html>Estàs segur que vols sortir?<br/>(No es guardarà el progrés actual)</html>");
                Object[] options = {"Sí", "No"};
                int input = JOptionPane.showOptionDialog(gameFrame, msg,
                        "Sortir de la app",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
                if (input == 0) {
                    System.exit(0);
                }
            }
        });
        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public JFrame getGameFrame() {
        return this.gameFrame;
    }

    public void setUsuari(String usr) {
        this.usuari = usr;
    }

    public String getUsuari() {
        return usuari;
    }

    private void fill_menu_bar() {
        this.MenuBar = new JMenuBar();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel msg = new JLabel("<html>Estàs segur que vols sortir?<br/>(No es guardarà el progrés actual)</html>");
                Object[] options = {"Sí", "No"};
                int input = JOptionPane.showOptionDialog(gameFrame, msg,
                        "Tancar app",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
                if (input == 0) System.exit(0);
            }
        });
        aux_menu.add(exit);

        abandonar.setEnabled(false);

        aux_menu.add(abandonar);
        this.MenuBar.add(aux_menu);

        this.MenuBar.add(createMenuPartida());
    }

    private JMenu createMenuPartida() {

        game_options.setEnabled(false);

        game_options.add(pausa_partida);
        game_options.add(historial);

        return game_options;
    }


    private void init_domain_controller() {
        this.DomainController = new ControladorDomini();
    }

    public JMenuItem getPausa_partida() {
        return pausa_partida;
    }

    public JMenuItem getHistory_partida() {
        return historial;
    }

    public JMenuItem getAbandonar() {
        return abandonar;
    }

    public JMenu getpartidaoptions() {
        return game_options;
    }
    public void enterGame() {
        abandonar.setEnabled(true);
        game_options.setEnabled(true);
    }

    public void exitGame() {
        abandonar.setEnabled(false);
        game_options.setEnabled(false);
    }
}

