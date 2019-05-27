package gui;

import domini.*;
import domini.define;

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


    public GameFrame() {
        this.gameFrame = new JFrame("Escacs");
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setResizable(false);
        fill_menu_bar();
        ((JFrame) this.gameFrame).setJMenuBar(this.MenuBar);
        this.gameFrame.setSize(SCREEN_SIZE);

        init_domain_controller();

        JugarPartidaView partida = new JugarPartidaView(this.gameFrame, false, this.DomainController);
//        Taulell T = new Taulell(init_test());
//        this.master_tauler = T;




        //VistaInici vi = new VistaInici(this.gameFrame, this.DomainController);
        //VistaMenuPrincipal vmp = new VistaMenuPrincipal(this.gameFrame, this.DomainController, "usr123");
        //VistaEstadistica ve = new VistaEstadistica(this.gameFrame, this.DomainController, "usr1");
    }

    private void fill_menu_bar() {
        this.MenuBar = new JMenuBar();
        this.MenuBar.add(createMenu());
    }
    private JMenu createMenu() {
        final JMenu aux_menu = new JMenu("Options");
        final JMenuItem exit = new JMenuItem("Exit app");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        aux_menu.add(exit);
        return aux_menu;
    }
    private Peca[][] init_test() {
        Peca[][] Prova = new Peca[8][8];
        for (int i = 0; i<8; ++i) {
            for (int j = 0; j < 8; ++j) {
                String name = "";
                switch (j % 7) {
                    case 0:
                        name = "Alfil";
                        break;
                    case 1:
                        name = "Peo";
                        break;
                    case 2:
                        name = "Rei";
                        break;
                    case 3:
                        name = "Reina";
                        break;
                    case 4:
                        name = "Torre";
                        break;
                    case 5:
                        name = "Cavall";
                        break;
                    case 6:
                        name = "Peca_Nula";
                        break;
                }

                try {
                    Prova[i][j] = (Peca) Class.forName("domini." + name).getConstructor(int.class, ArrayList.class, ArrayList.class).newInstance(define.WHITE, null, null);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        return Prova;
    }

    private void init_domain_controller() {
        this.DomainController = new ControladorDomini();
    }

}
