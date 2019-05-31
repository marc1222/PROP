/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author MDD
 */
public class VistaMenuPrincipal {
    private GameFrame main;
    private JFrame master;
    private ControladorDomini ctrlDomini;
    private String usuari;

    private JugarPartidaView partida = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnSimular;
    private JButton btnJugar;
    private JButton btnGestioProblemes;
    private JButton btnEstadistiques;
    private JButton btnBaixa;
    private JButton btnTancarSessio;
    private JLabel labelTitol;
    private JPanel panelMenu;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form VistaMenuPrincipal
     */
    public VistaMenuPrincipal(GameFrame mainGame, ControladorDomini ctrlDomini) {
        this.main = mainGame;
        this.master = mainGame.getGameFrame();
        this.ctrlDomini = ctrlDomini;
        this.usuari = main.getUsuari();
        initComponents();

//        this.master.getContentPane().removeAll();
//        this.master.add(jPanel1);
//        this.master.repaint();
//        this.master.setVisible(true);
        (main.getPausa_partida()).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    partida.pause = true;
                    if (!partida.getOptionPanel().simulacio) partida.getOptionPanel().stop_timer();
                    Object[] options = {"Continuar partida"};
                    JLabel msg = new JLabel("Partida pausada", SwingConstants.CENTER);
                    msg.setHorizontalAlignment(JLabel.CENTER);
                    msg.setFont(msg.getFont().deriveFont(15.0f));
                    int input = JOptionPane.showOptionDialog(master, msg, "Pausa", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (input == 0) {
                        partida.pause = false;
                        if (!partida.getOptionPanel().simulacio) partida.getOptionPanel().start_timer();
                        if (partida.getBoardView().compute_thread != null) {
                            synchronized (partida.getBoardView().compute_thread) {
                                partida.getBoardView().compute_thread.notify();
                            }
                        }
                    }
            }
        });
        (main.getHistory_partida()).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Object[] options = {"Continuar partida"};

                        JPanel aux = new JPanel(new GridLayout(0,1));

                        JLabel msg1 = new JLabel("Historial", JLabel.CENTER);
                        msg1.setHorizontalAlignment(JLabel.CENTER);
                        msg1.setFont (msg1.getFont ().deriveFont (19.0f));
                        aux.add(msg1);
                        JLabel msg2 = new JLabel("---------------------------------------", JLabel.CENTER);
                        aux.add(msg2);
                        JLabel msg3 = new JLabel("   #ronda  -  torn  -  Ini  -  Dest    ", JLabel.CENTER);
                        msg1.setFont (msg1.getFont ().deriveFont (14.0f));
                        aux.add(msg3);
                        JLabel msg4 = new JLabel("---------------------------------------", JLabel.CENTER);
                        aux.add(msg4);
                        History.Movement[] history = partida.getHistory();
                        JLabel msgi;
                        for (int i = 0; i < history.length; ++i) {
                            History.Movement act = history[i];
                            msgi = new JLabel("       "+(i+1)+"        "+((act.color==define.WHITE)?"B":"N")+"       "+translate(act.ini)+" -> "+translate(act.fi)+"  ",JLabel.CENTER);
                            msgi.setFont (msgi.getFont ().deriveFont (14.0f));
                            aux.add(msgi);
                        }


                        JOptionPane.showOptionDialog(master, aux,"Historial de la partida", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    }
                });

            }
        });
    }

    public String getUsuari() {
        return usuari;
    }

    private void initComponents() {

        GridBagConstraints gbc = new GridBagConstraints();
        panelMenu = new JPanel(new GridBagLayout());

        labelTitol = new JLabel("Escacs");
        btnJugar = new JButton();
        btnGestioProblemes = new JButton();
        btnEstadistiques = new JButton();
        btnBaixa = new JButton();
        btnTancarSessio = new JButton();
        btnSimular = new JButton();


        (main.getAbandonar()).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel msg = new JLabel("<html>Estàs segur que vols tornar al menú?<br/>(No es guardarà el progrés actual)</html>");
                Object[] options = {"Sí", "No"};
                int input = JOptionPane.showOptionDialog(master, msg,
                        "Tornar al menú",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[1]);
                if (input == 0) {
                    setMenu();
                    if (partida != null) {
                        if (partida.getBoardView().compute_thread != null) partida.getBoardView().compute_thread.interrupt();
                    }
                }
            }
        });

        btnJugar.setText("Jugar partida");
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        btnGestioProblemes.setText("Gestió problemes");
        btnGestioProblemes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGestioProblemesActionPerformed(evt);
                main.getAbandonar().setEnabled(true);

            }
        });

        btnEstadistiques.setText("Mirar estadístiques");
        btnEstadistiques.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEstadistiquesActionPerformed(evt);
            }
        });

        btnBaixa.setText("Donar-se de baixa");
        btnBaixa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBaixaActionPerformed(evt);
            }
        });

        btnTancarSessio.setText("Tancar sessió");
        btnTancarSessio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTancarSessioActionPerformed(evt);
            }
        });

        btnSimular.setText("Simular partides");
        btnSimular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });
        //btnSimular.setToolTipText("FGRS");

        master.getContentPane().removeAll();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,0,15,0);
        panelMenu.add(labelTitol, gbc);

        JSeparator sep = new JSeparator();
        gbc.gridy = 1;
        //gbc.ipadx = 500;
        //gbc.gridwidth = 2;
        panelMenu.add(sep, gbc);


        gbc.insets = new Insets(0,0,20,0);
        gbc.ipady = 40;      //altura
        gbc.ipadx = 150;      //anchura
        gbc.gridy = 2;
        panelMenu.add(btnJugar, gbc);

        gbc.gridy = 3;
        panelMenu.add(btnSimular, gbc);

        gbc.gridy = 4;
        panelMenu.add(btnGestioProblemes, gbc);

        gbc.gridy = 5;
        panelMenu.add(btnEstadistiques, gbc);

        gbc.gridy = 6;
        panelMenu.add(btnBaixa, gbc);

        gbc.gridy = 7;
        panelMenu.add(btnTancarSessio, gbc);


        setFont();

        master.add(panelMenu);
        master.repaint();
        master.setVisible(true);
    }

    private void btnJugarActionPerformed(ActionEvent evt) {
        // TODO --- JUGAR PARTIDA ---
        JugarPartidaView partida = new JugarPartidaView(main, false, ctrlDomini, this);
        this.partida = partida;
    }

    private void btnSimularActionPerformed(ActionEvent evt) {
        // TODO --- SIMULAR PARTIDA ---
        JugarPartidaView partida = new JugarPartidaView(main, true, ctrlDomini,this);
        this.partida = partida;
    }

    private void btnGestioProblemesActionPerformed(ActionEvent evt) {
        // TODO --- GESTIO PROBLEMES ---
        // TODO --- GESTIO PROBLEMES ---
        MenuProblema mp = new MenuProblema(master, this);
        master.setContentPane(mp);
        //master.pack();
        master.setVisible(true);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                MenuProblema mp = new MenuProblema(master);
//            }
//        });
    }

    private void btnEstadistiquesActionPerformed(ActionEvent evt) {
        VistaEstadistica ve = new VistaEstadistica(main, ctrlDomini);
    }

    private void btnBaixaActionPerformed(ActionEvent evt) {
        int a = JOptionPane.showConfirmDialog(master,"Estàs segur de que vols donar-te de baixa?",
                "Donar-se de baixa", JOptionPane.YES_NO_OPTION );
        if(a == JOptionPane.YES_OPTION){
            ctrlDomini.eliminarUsuari(usuari);
            ctrlDomini.eliminarStatsUsuari(usuari);
            VistaInici startview = new VistaInici(main, ctrlDomini);
        }

    }

    private void btnTancarSessioActionPerformed(ActionEvent evt) {
        int a = JOptionPane.showConfirmDialog(master,"Estàs segur de que vols tancar sessió?",
                "Tancar sessió", JOptionPane.YES_NO_OPTION );
        if(a == JOptionPane.YES_OPTION){
            VistaInici startview = new VistaInici(main, ctrlDomini);
        }
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

    public void setMenu() {
        master.getContentPane().removeAll();
        master.add(this.panelMenu);
        master.repaint();
        master.setVisible(true);
        main.exitGame();
    }

    private void setFont() {
        Font fntLabel = btnJugar.getFont();
        Font fntBold = new Font(fntLabel.getName(), Font.BOLD, 18);
        Font fntPlain = new Font(fntLabel.getName(), Font.PLAIN, 18);

        labelTitol.setFont(new Font(fntLabel.getName(), Font.BOLD, 40)); // NOI18N
        labelTitol.setHorizontalAlignment(SwingConstants.CENTER);

        btnJugar.setFont(fntPlain);
        btnSimular.setFont(fntPlain);
        btnGestioProblemes.setFont(fntPlain);
        btnEstadistiques.setFont(fntPlain);
        btnBaixa.setFont(fntPlain);
        btnTancarSessio.setFont(fntPlain);
    }

}
