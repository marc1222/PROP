package gui;

import domini.ControladorDomini;

public class ControladorPresentacio {
    private ControladorDomini cDomini;

    public ControladorPresentacio() {
        GameFrame view = new GameFrame();
    }

    public ControladorPresentacio(ControladorDomini cd) {
        this.cDomini = cd;
    }

    public static int creaProblema(int njug, String fen, String user) {
        return ControladorDomini.creaProblema(njug, fen, user);
    }

    public static String graficToFEN(String tipus[][], int colors[][], int primer) {
        return ControladorDomini.graficToFEN(tipus, colors, primer);
    }

    public static int borraProblema(int id, String user) {
        return ControladorDomini.borraProblema(id, user);
    }
}
