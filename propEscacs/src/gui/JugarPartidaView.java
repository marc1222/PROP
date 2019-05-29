package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;

public class JugarPartidaView {

    //TODO TEMPSSSSS
//    long iniCrono = System.currentTimeMillis();
//    long tempsMoviment = System.currentTimeMillis() - iniCrono;;

    //private SeleccioProblema SeleccioProblema
    private SeleccioJugarPartida SeleccioPartida;
    private SeleccioProblema SeleccioProblema;
    private GameFrame parent;
    private GUITauler BoardView;
    private GUIOption OptionBarView;
    private ControladorDomini DomainController;
    private JFrame Master;
    boolean simulacio;
    int problemaID;
    int sim_prob[];
    int id_sim;

    JugarPartidaView(GameFrame master, boolean sim, ControladorDomini DC) {
        this.parent = master;
        this.DomainController = DC;
        this.Master = master.getGameFrame();
        // TODO: LA VISTA DE PROBLEMA N'HA dE FER un getter -> this.ProblemaController = PC;
        this.simulacio = sim;
        //TODO: INSTANCIAR LA VISTA DE SELECCIO DE PROBLEMES PASSANT SIMULACIO==true?0:1
        //this.Master.getContentPane().removeAll();

        this.SeleccioProblema = new SeleccioProblema(this, simulacio?define.simulacio:define.normal, Master);
//        Master.setContentPane(SeleccioProblema);
//        Master.pack();
//
//        this.Master.add(this.SeleccioProblema);
//        this.Master.repaint();
       // this.Master.setVisible(true);
        this.SeleccioPartida = new SeleccioJugarPartida(this);

    }

    public void setProblemView(int problemaID) {
        this.problemaID = problemaID;
        this.Master.getContentPane().removeAll();
        this.Master.add(this.SeleccioPartida);
        this.Master.repaint();
        this.Master.setVisible(true);
    }
    public GUIOption getOptionPanel() {
        return this.OptionBarView;
    }
    public void change_to_play_view(boolean ataca, int oponent) {
        (this.DomainController).init_partida(ataca, oponent, problemaID);//
        this.OptionBarView = new GUIOption(this.simulacio);
        OptionBarView.initCrono(ataca);
        OptionBarView.update_stats();
        String op_name = (oponent==define.USER)?"Usuari 2":(oponent==define.NAIVE)?"Naive (M)":"Smart (M)";
        OptionBarView.setInfo(ataca?"GetNameUser":op_name, (!ataca)?"GetNameUser":op_name, problemaID, DomainController.getMaxRondes());
        this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
        this.Master.getContentPane().removeAll();
        this.Master.add(this.OptionBarView, BorderLayout.NORTH);
        this.Master.add(this.BoardView, BorderLayout.CENTER);
        this.Master.repaint();
        this.Master.setVisible(true);
        parent.enterGame();
        parent.setMenuPartidaNormal();

        if (DomainController.tipusJugadorActual() == define.MAQUINA) BoardView.make_moveMaquina();

    }
    public void startSimulacio(int probIDS[], int white, int black) {
            this.sim_prob = probIDS;
            this.id_sim = 0;

            this.problemaID = probIDS[id_sim];
            (this.DomainController).init_partida(white, black, problemaID);
            this.OptionBarView = new GUIOption(this.simulacio);
            this.OptionBarView.update_stats();
            this.OptionBarView.setInfo((white==define.NAIVE?"Naive":"Smart")+" (M)", (black==define.NAIVE?"Naive":"Smart")+" (M)" , problemaID, DomainController.getMaxRondes());
            this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
            this.Master.getContentPane().removeAll();
            this.Master.add(this.OptionBarView, BorderLayout.NORTH);
            this.Master.add(this.BoardView, BorderLayout.CENTER);
            this.Master.repaint();
            this.Master.setVisible(true);

            parent.enterGame();
            parent.setMenuPartidaSimulacio();

            this.BoardView.jugaSimulacio();

    }
    public void juga_simulacio() {
        id_sim++;
        if (id_sim < sim_prob.length) {
            this.problemaID = sim_prob[id_sim];
            (this.DomainController).juga_simulacio(problemaID);
            this.OptionBarView.resetSim();
            this.OptionBarView.update_stats();
            this.OptionBarView.setInfo("null", "null" , problemaID, DomainController.getMaxRondes());
            this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
            this.Master.getContentPane().removeAll();
            this.Master.add(this.OptionBarView, BorderLayout.NORTH);
            this.Master.add(this.BoardView, BorderLayout.CENTER);
            this.Master.repaint();
            this.Master.setVisible(true);
            this.BoardView.jugaSimulacio();
        }
    }


    public History.Movement[] getHistory() {
        return this.BoardView.getHistory();
    }


}
