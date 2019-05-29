package gui;

import domini.*;
import domini.define;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameFrame {

    private JFrame gameFrame;
    private static Dimension SCREEN_SIZE = new Dimension(730,730);
    private ControladorDomini DomainController;

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
    final JMenuItem demana_pista = new JMenuItem("Demanar pista");
    final JMenuItem historial = new JMenuItem("Mira historial");
    final JMenuItem desfer_jugada = new JMenuItem("Desfér jugada");

    public GameFrame() {
        this.gameFrame = new JFrame("Escacs");
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setResizable(false);
        fill_menu_bar();
        (this.gameFrame).setJMenuBar(this.MenuBar);
        this.gameFrame.setSize(SCREEN_SIZE);

        init_domain_controller();

       VistaInici startview = new VistaInici(this, DomainController);

//        JugarPartidaView partida = new JugarPartidaView(this, true, this.DomainController);
//
//        pausa_partida.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                partida.getOptionPanel().stop_timer();
//                Object[] options = {"Continuar partida"};
//                JLabel msg = new JLabel("Partida pausada", SwingConstants.CENTER);
//                msg.setHorizontalAlignment(JLabel.CENTER);
//                msg.setFont (msg.getFont ().deriveFont (15.0f));
//                int input = JOptionPane.showOptionDialog(gameFrame, msg ,"Pausa", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//                if (input == 0) {
//                    partida.getOptionPanel().start_timer();
//                }
//            }
//        });
//        historial.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        Object[] options = {"Continuar partida"};
//
//                        JPanel aux = new JPanel(new GridLayout(0,1));
//
//                        JLabel msg1 = new JLabel("Historial", JLabel.CENTER);
//                        msg1.setHorizontalAlignment(JLabel.CENTER);
//                        msg1.setFont (msg1.getFont ().deriveFont (19.0f));
//                        aux.add(msg1);
//                        JLabel msg2 = new JLabel("---------------------------------------", JLabel.CENTER);
//                        aux.add(msg2);
//                        JLabel msg3 = new JLabel("   #ronda  -  torn  -  Ini  -  Dest    ", JLabel.CENTER);
//                        msg1.setFont (msg1.getFont ().deriveFont (14.0f));
//                        aux.add(msg3);
//                        JLabel msg4 = new JLabel("---------------------------------------", JLabel.CENTER);
//                        aux.add(msg4);
//                        History.Movement[] history = partida.getHistory();
//                        JLabel msgi;
//                        for (int i = 0; i < history.length; ++i) {
//                            History.Movement act = history[i];
//                            msgi = new JLabel("       "+(i+1)+"        "+((act.color==define.WHITE)?"B":"N")+"       "+translate(act.ini)+" -> "+translate(act.fi)+"  ",JLabel.CENTER);
//                            msgi.setFont (msgi.getFont ().deriveFont (14.0f));
//                            aux.add(msgi);
//                        }
//
//
//                        JOptionPane.showOptionDialog(gameFrame, aux,"Historial de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//                    }
//                });
//
//            }
//        });

    }
    public JFrame getGameFrame() {
        return this.gameFrame;
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
        abandonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel msg = new JLabel("<html>Estàs segur que vols tornar al menú?<br/>(No es guardarà el progrés actual)</html>");
                Object[] options = {"Sí", "No"};
                int input = JOptionPane.showOptionDialog(gameFrame, msg,
                        "Tornar al menú",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
                if (input == 0) {
                    //TODO TORNAR AL MENU -> SET MENU VIEW
                }
            }
        });
        aux_menu.add(abandonar);
        this.MenuBar.add(aux_menu);

        this.MenuBar.add(createMenuPartida());
    }
    private JMenu createMenuPartida() {

        game_options.setEnabled(false);

        game_options.add(pausa_partida);
        game_options.add(demana_pista);
        game_options.add(desfer_jugada);
        game_options.add(historial);

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
    public void setMenuPartidaSimulacio() {
        demana_pista.setEnabled(false);
        desfer_jugada.setEnabled(false);
    }
    public void setMenuPartidaNormal() {
        demana_pista.setEnabled(true);
        desfer_jugada.setEnabled(true);
    }


    private void init_domain_controller() {
        this.DomainController = new ControladorDomini();
    }
    private String translate(Posicion p) {
        char X = '0';
        switch (p.x) {
            case 0:
                X = 'a';
                break;
            case 1:
                X = 'b';
                break;
            case 2:
                X = 'c';
                break;
            case 3:
                X = 'd';
                break;
            case 4:
                X = 'e';
                break;
            case 5:
                X = 'f';
                break;
            case 6:
                X = 'g';
                break;
            case 7:
                X = 'h';
                break;
        }
        return (X+String.valueOf(p.y));

    }
}

