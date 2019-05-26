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

    private GUITauler BoardView;
    private GUIOption OptionBarView;
    private ControladorDomini DomainController;
    private JFrame Master;
    boolean simulacio;
    int problemaID;
    int sim_prob[];
    int id_sim;

    JugarPartidaView(JFrame master, boolean sim, ControladorDomini DC) {
        this.DomainController = DC;
        this.Master = master;
        // TODO: LA VISTA DE PROBLEMA N'HA dE FER un getter -> this.ProblemaController = PC;
        this.simulacio = sim;
        //TODO: INSTANCIAR LA VISTA DE SELECCIO DE PROBLEMES PASSANT SIMULACIO==true?0:1
        this.SeleccioProblema = new SeleccioProblema(this, simulacio?define.simulacio:define.normal);
        this.Master.add(this.SeleccioProblema);
        this.Master.setVisible(true);
        this.SeleccioPartida = new SeleccioJugarPartida(this);


//        problemaID = 13;
//        setProblemView();
       // this.PartidaController = new ControladorDominiPartida(this.ProblemaController,this.JugadorController, this.simulacio, num_prob);
       // this.BoardView = new GUITauler(this.PartidaController);
       //

       // this.OptionBarView = new GUIOption();
       //

    }

    public void setProblemView(int problemaID) {
        this.problemaID = problemaID;
        this.Master.getContentPane().removeAll();
        this.Master.add(this.SeleccioPartida);
        this.Master.repaint();
        this.Master.setVisible(true);
    }
    public void change_to_play_view(boolean ataca, int oponent) {
        (this.DomainController).init_partida(ataca, oponent, problemaID);
        this.OptionBarView = new GUIOption();
        OptionBarView.initCrono(ataca);
        OptionBarView.update_stats();

        this.BoardView = new GUITauler(this.DomainController, this.OptionBarView);
        this.Master.getContentPane().removeAll();
        this.Master.add(this.OptionBarView, BorderLayout.NORTH);
        this.Master.add(this.BoardView, BorderLayout.CENTER);
        this.Master.repaint();
        this.Master.setVisible(true);
    }
    public void startSimulacio(int probIDS[], int white, int black) {
            this.sim_prob = probIDS;
            this.id_sim = 0;

            this.problemaID = probIDS[id_sim];
            (this.DomainController).init_partida(white, black, problemaID);
            this.OptionBarView = new GUIOption();
            this.BoardView = new GUITauler(this.DomainController, this.OptionBarView);
            this.Master.getContentPane().removeAll();
            this.Master.add(this.OptionBarView, BorderLayout.NORTH);
            this.Master.add(this.BoardView, BorderLayout.CENTER);
            this.Master.repaint();
            this.Master.setVisible(true);
    }
    private void juga_simulacio() {
        id_sim++;
        this.problemaID = sim_prob[id_sim];
        (this.DomainController).juga_simulacio(problemaID);
        this.OptionBarView = new GUIOption();
        this.BoardView = new GUITauler(this.DomainController, this.OptionBarView);
        this.Master.getContentPane().removeAll();
        this.Master.add(this.OptionBarView, BorderLayout.NORTH);
        this.Master.add(this.BoardView, BorderLayout.CENTER);
        this.Master.repaint();
        this.Master.setVisible(true);
    }


}
