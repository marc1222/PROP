package gui;

import domini.Peca;
import domini.Problema;
import domini.Taulell;
import domini.define;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Taula {

    private JFrame gameFrame;
    private static Dimension SCREEN_SIZE = new Dimension(730,730);

    private JMenuBar MenuBar;

    private GUITauler Board;
    private GUIOption OptionBar;

    private Mainmenu mainmenu;
    private Taulell master_tauler;

    public Taula() {
        this.gameFrame = new JFrame("Escacs");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setResizable(false);
        fill_menu_bar();
        ((JFrame) this.gameFrame).setJMenuBar(this.MenuBar);
        this.gameFrame.setSize(SCREEN_SIZE);


//        Taulell T = new Taulell(init_test());
//        this.master_tauler = T;
//        this.Board = new GUITauler(this.master_tauler);
//        this.gameFrame.add(this.Board, BorderLayout.CENTER);
//
//        this.OptionBar = new GUIOption();
//        this.gameFrame.add(this.OptionBar, BorderLayout.NORTH);

        this.gameFrame.setVisible(true);
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

    public set_to_user_menu() {
        this.gameFrame.remove(this.mainmenu);
        this.gameFrame.add(this.usermenu)
    }
    public jugar_parti

}

