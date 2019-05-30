package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;

public class JugarPartidaView {

    //TODO TEMPSSSSS

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
    public boolean pause;
    private VistaMenuPrincipal menuPrincipal;

    JugarPartidaView(GameFrame master, boolean sim, ControladorDomini DC, VistaMenuPrincipal menuview) {
        this.pause = false;
        this.parent = master;
        this.menuPrincipal = menuview;
        this.DomainController = DC;
        this.Master = master.getGameFrame();
        // TODO: LA VISTA DE PROBLEMA N'HA dE FER un getter -> this.ProblemaController = PC;
        this.simulacio = sim;
        //TODO: INSTANCIAR LA VISTA DE SELECCIO DE PROBLEMES PASSANT SIMULACIO==true?0:1

        this.SeleccioProblema = new SeleccioProblema(this, simulacio?define.simulacio:define.normal);
//        Master.setContentPane(SeleccioProblema);
//        Master.pack();
        this.SeleccioPartida = new SeleccioJugarPartida(this);

        this.Master.getContentPane().removeAll();
        this.Master.add(this.SeleccioProblema);
        this.Master.repaint();
        this.Master.setVisible(true);

    }
    public GUITauler getBoardView() {
        return BoardView;
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
        this.OptionBarView = new GUIOption(this.simulacio, DomainController.colorJugadorPrimer());
        OptionBarView.initCrono(ataca);
        OptionBarView.update_stats();
        String op_name = (oponent==define.USER)?"Usuari 2":(oponent==define.NAIVE)?"Naive (M)":"Smart (M)";
        OptionBarView.setInfo(ataca?DomainController.getMainUserName():op_name, (!ataca)?DomainController.getMainUserName():op_name, problemaID, DomainController.getMaxRondes());
        this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
        this.Master.getContentPane().removeAll();
        this.Master.add(this.OptionBarView, BorderLayout.NORTH);
        this.Master.add(this.BoardView, BorderLayout.CENTER);
        this.Master.repaint();
        this.Master.setVisible(true);
        parent.enterGame();

        if (DomainController.tipusJugadorActual() == define.MAQUINA) BoardView.make_moveMaquina();

    }
    public void startSimulacio(int probIDS[], int white, int black) {
            this.sim_prob = probIDS;
            this.id_sim = 0;

            this.problemaID = probIDS[id_sim];
            (this.DomainController).init_partida(white, black, problemaID);
            this.OptionBarView = new GUIOption(this.simulacio, DomainController.colorJugadorPrimer());
            this.OptionBarView.update_stats();
            this.OptionBarView.setInfo((white==define.NAIVE?"Naive":"Smart")+" (M)", (black==define.NAIVE?"Naive":"Smart")+" (M)" , problemaID, DomainController.getMaxRondes());
            this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
            this.Master.getContentPane().removeAll();
            this.Master.add(this.OptionBarView, BorderLayout.NORTH);
            this.Master.add(this.BoardView, BorderLayout.CENTER);
            this.Master.repaint();
            this.Master.setVisible(true);

            parent.enterGame();
            BoardView.finish_sim = false;

            this.BoardView.jugaSimulacio();

    }
    public void juga_simulacio() {
        id_sim++;
        if (id_sim < sim_prob.length && !BoardView.finish_sim) {
            this.problemaID = sim_prob[id_sim];
            (this.DomainController).juga_simulacio(problemaID);
            this.OptionBarView.resetSim(DomainController.colorJugadorPrimer());
            this.OptionBarView.update_stats();
            this.OptionBarView.setInfo("null", "null" , problemaID, DomainController.getMaxRondes());
            this.BoardView = new GUITauler(this.DomainController, this.OptionBarView, this.simulacio, this);
            this.Master.getContentPane().removeAll();
            this.Master.add(this.OptionBarView, BorderLayout.NORTH);
            this.Master.add(this.BoardView, BorderLayout.CENTER);
            this.Master.repaint();
            this.Master.setVisible(true);
            this.BoardView.jugaSimulacio();
        } else if (!BoardView.finish_sim) {
            returnmenu();
        }
    }


    public History.Movement[] getHistory() {
        return this.BoardView.getHistory();
    }

    public void tornaMenu() {
        this.menuPrincipal.setMenu();
    }
    public void returnmenu() {
        parent.exitGame();
        menuPrincipal.setMenu();
        BoardView.compute_thread.interrupt();
    }

}
