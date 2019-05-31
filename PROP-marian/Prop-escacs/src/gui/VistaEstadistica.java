package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaEstadistica {
    private GameFrame main;
    private JFrame master;
    private ControladorDomini ctrlDomini;
    private String usuari;
    private String idProblema;

    JPanel panelStats = new JPanel();
    JTabbedPane tp = new JTabbedPane();
    JScrollPane spUser;
    JTable taulaUser;
    JScrollPane spProblema;
    JTable taulaProblema;
    JPanel panelProblema = new JPanel();
    JPanel panelUsuari = new JPanel();
    JButton btnSelectProblema = new JButton("Seleccionar problema");

    public VistaEstadistica(GameFrame mainGame, ControladorDomini ctrlDomini) {
        this.master = mainGame.getGameFrame();
        this.main = mainGame;
        this.ctrlDomini = ctrlDomini;
        this.usuari = main.getUsuari();
        idProblema = "-99";

        initComponents();
        asignar_listenersComponentes();
        ferVisible();
    }

    public VistaEstadistica(GameFrame mainGame, ControladorDomini ctrlDomini, String id) {
        this.master = mainGame.getGameFrame();
        this.main = mainGame;
        this.ctrlDomini = ctrlDomini;
        this.usuari = main.getUsuari();
        this.idProblema = id;

        initComponents();
        asignar_listenersComponentes();
        ferVisible();
    }

    public GameFrame getMain() {
        return this.main;
    }

    public ControladorDomini getCtrlDomini() {
        return this.ctrlDomini;
    }

    public void ferVisible() {
        this.master.getContentPane().removeAll();

        main.getAbandonar().setEnabled(true);
        panelStats.add(tp, BorderLayout.CENTER);
        panelStats.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        this.master.add(panelStats);
        this.master.repaint();
        this.master.setVisible(true);
    }

    private void initComponents() {
        BorderLayout bl = new BorderLayout();
        bl.setVgap(10);
        panelProblema.setLayout(bl);
        panelUsuari.setLayout(new BorderLayout());
        /*
        panelStats = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        */
        panelStats = new JPanel(new BorderLayout());

        fillTaulaUser();
        fillTaulaProblema();
        tp.add("Problema",panelProblema);
        tp.add("Usuari",panelUsuari);
        setFont();
    }

    private void fillTaulaUser() {
        String column[]={"Problema","Mat", "Temps"};

        ArrayList<String> listUsuari = ctrlDomini.estadistiquesUsuari(usuari);
        int listSizeUsuari= listUsuari.size();
        if (listSizeUsuari != 0) {
            String[][] dataUsuari = new String[listSizeUsuari][3];
            //System.out.println(listSizeUsuari);

            for (int x = 0; x < listSizeUsuari; x++) {
                //System.out.println(list.get(x));

                String strUsuari = listUsuari.get(x);
                String[] splitedUsuari = strUsuari.split("\\s+");

                dataUsuari[x][0] = splitedUsuari[0];
                dataUsuari[x][1] = splitedUsuari[1];
                dataUsuari[x][2] = splitedUsuari[2] + " " + splitedUsuari[3];
            }
            taulaUser = new JTable(dataUsuari, column);
            //spUser.setViewportView(taulaUser);
            spUser = new JScrollPane(taulaUser);
            panelUsuari.add(spUser, BorderLayout.CENTER);
            panelUsuari.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }
        else {
            System.out.println("No hi han dades "+ usuari);
        }
    }

    private void fillTaulaProblema() {
        String column[]={"Usuari","Mat", "Temps"};

        ArrayList<String> listProblemes = ctrlDomini.estadistiquesProblema(idProblema);
        int listSizeProblemes = listProblemes.size();
        String[][] dataProblemes = new String[listSizeProblemes][3] ;
        //System.out.println(listSizeProblemes);

        for (int x = 0; x < listSizeProblemes; x++) {
            //System.out.println(listProblemes.get(x));

            String str = listProblemes.get(x);
            String[] splitedProblemes = str.split("\\s+");

            dataProblemes[x][0] = splitedProblemes[0];
            dataProblemes[x][1] = splitedProblemes[1];
            dataProblemes[x][2] = splitedProblemes[2] + " " + splitedProblemes[3];
        }
        taulaProblema = new JTable(dataProblemes,column);
        //spProblema.setViewportView(taulaProblema);
        spProblema = new JScrollPane(taulaProblema);

        panelProblema.add(btnSelectProblema, BorderLayout.NORTH);
        //btnProblema.setMargin(new Insets(0, 0, 50, 0));
        panelProblema.add(spProblema, BorderLayout.CENTER);
        panelProblema.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void asignar_listenersComponentes() {
        btnSelectProblema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SeleccioProblema sp = new SeleccioProblema(VistaEstadistica.this, define.ESTAD);

                master.getContentPane().removeAll();
                master.add(sp);
                master.repaint();
                master.setVisible(true);
            }
        });
    }

    private void setFont() {
        Font original = tp.getFont();
        tp.setFont( new Font( original.getName(), Font.PLAIN, 17 ) );
    }
}