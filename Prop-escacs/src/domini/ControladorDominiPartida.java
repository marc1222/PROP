package domini;

public class ControladorDominiPartida {
    private Partida partida;
    ControladorDominiJugador JugadorController;
    ControladorDominiProblema ProblemaController;
    boolean simulacio;
    int num_prob;

    //CREADORES
    ControladorDominiPartida() {
    }

    public ControladorDominiPartida(ControladorDominiProblema PC, ControladorDominiJugador JC, boolean simulacio, int num_prob) {
        this.ProblemaController = PC;
        this. JugadorController = JC;
        this.simulacio = simulacio;
        this.num_prob = num_prob;

        boolean save_stats;
        //MIREM SI GUARDEM LES ESTADISTIQUES
        if (simulacio) {
            if (this.num_prob == 1) save_stats = true;
            else save_stats = false;
        }
        else {
            int primer = this.ProblemaController.getPrimer();
            if (primer == define.WHITE) save_stats = (this.JugadorController.getJugadorTipus(define.WHITE) == define.USER);
            else save_stats = (this.JugadorController.getJugadorTipus(define.BLACK) == define.USER);
        }
        this.partida = new Partida(ProblemaController.getProblema(), JugadorController.getWhite(),
                JugadorController.getBlack(),save_stats);
    }

}
