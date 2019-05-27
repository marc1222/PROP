package domini;

import gui.JugarPartidaView;
import javafx.geometry.Pos;

/**
 * @authors Marc Guinovart, Narcís Rodas
 *
 *      Aquest controlador de domini, s'encarregara de gestionar totes les funcionalitats oferides per
 *      el propi domini. S'usara (s'instanciarà) en les vistes de la capa de presentació per dur-les a terme.
 *
 *      El controlador en concret es comunnicarà amb les següents classes per tal d'oferir-ne comunicació entre capes:
 *
 *      Comunicació amb partida, per la funcionalitat de jugar partides
 */
public class ControladorDomini {

    //PARTIDA
    private Partida partida;
//    boolean simulacio;
//    int num_prob;

    //JUGADOR
    private Usuari User;
    private Jugador SecondUser;
    private Jugador AuxUser;

    //PROBLEMA
    private Problema problema;

    //ESTADISTICA
    private Estadistica stats;

    //CREADORES
    public ControladorDomini() {
        this.User = new Usuari();
    }

    /**
     *
     * @param user_ataca true si user loggegat ataca, si defensa false
     * @param user_oponent define.USER - define.NAIVE - define.SMART
     * @param problemaID - identificador del problema a jugar
     */
    public void init_partida(boolean user_ataca, int user_oponent, int problemaID) {
        if (user_oponent != define.USER && user_oponent != define.SMART && user_oponent != define.NAIVE)
            System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR USER OPONENT INCORRECTEEE!!!!!!!");
        Problema p = new Problema();
        int res = Problema.getProblemaId(problemaID, p);
        if (res != 0) System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR PROBLEMA INCORRECTEEE!!!!!!!");

        if (user_ataca && user_oponent == define.USER) {
            //atacar  && //invitado
            this.SecondUser = new Usuari((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
            (this.User).setColor(p.getPrimer());
        } else if (!user_ataca && user_oponent == define.USER) {
            //defender      &&  //Invitado
            this.SecondUser  = new Usuari(p.getPrimer());
            (this.User).setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
        } else if (user_ataca && user_oponent == define.NAIVE) {
            //atacar   &&    //naive
            this.SecondUser  = new Naive((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
            (this.User).setColor(p.getPrimer());
        } else if (!user_ataca && user_oponent == define.NAIVE) {
            //defender      &&  //naive
            this.SecondUser  = new Naive(p.getPrimer());
            (this.User).setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
        } else if (user_ataca && user_oponent == define.SMART) {
            //atacar   &&    //smart
            //TODO CANVIAR NAIVE A SMART
            this.SecondUser  = new Naive((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
            (this.User).setColor(p.getPrimer());
        } else {//if (!user_ataca && user_oponent == define.SMART) {
            //defender      &&  //smart
            //TODO CANVIAR NAIVE A SMART
            this.SecondUser  = new Naive(p.getPrimer());
            (this.User).setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
        }
        this.partida = new Partida(p, (User.getColor() == define.WHITE) ? User : SecondUser, (User.getColor() == define.WHITE) ? SecondUser : User, user_ataca);

        if (SecondUser.getTipus() == define.MAQUINA) {
            Maquina n = (Maquina) SecondUser;
            n.setTauler(partida.getTauler());
            n.setProfunditat(partida.getMat());
        }
    }

    /**
     *
     * @param  - define.NAIVE - define.SMART
     * @param black - define.NAIVE - define.SMART
     * @param problemaID - identificador del problema a jugar
     */
    public void init_partida(int white, int black, int problemaID) {
        if (white != define.SMART &&  white != define.NAIVE)
            System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR MAQUINA1 INCORRECTEEE!!!!!!!");
        if (black != define.SMART && black != define.NAIVE)
            System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR MAQUINA2 INCORRECTEEE!!!!!!!");
        Problema p = new Problema();
        int res = Problema.getProblemaId(problemaID, p);
        if (res != 0) System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR PROBLEMA INCORRECTEEE!!!!!!!");

        if (white == define.NAIVE && black == define.NAIVE) {
            SecondUser = new Naive(define.WHITE);
            AuxUser = new Naive(define.BLACK);
        } else if (white == define.NAIVE && black == define.SMART) {
            SecondUser = new Naive(define.WHITE);
            //TODO NEW SMART AUXUSER
            AuxUser = new Naive(define.BLACK);
        } else if (white == define.SMART && black == define.NAIVE) {
            //TODO NEW SMART SECONDUSER
            SecondUser = new Naive(define.WHITE);
            AuxUser = new Naive(define.BLACK);
        } else {
            // BOTH SMARTS
            //TODO NEW BOTH SMARTS: AUXUSER & SECONDUSER
            SecondUser = new Naive(define.WHITE);
            AuxUser = new Naive(define.BLACK);
        }

        this.partida = new Partida(p, SecondUser, AuxUser, true);

        Maquina m1 = (Maquina) SecondUser;
        m1.setTauler((this.partida).getTauler());
        m1.setProfunditat((this.partida).getMat());

        Maquina m2 = (Maquina) AuxUser;
        m2.setTauler((this.partida).getTauler());
        m2.setProfunditat((this.partida).getMat());

    }
    public void juga_simulacio(int problemaID) {

        Problema p = new Problema();
        int res = Problema.getProblemaId(problemaID, p);
        if (res != 0) System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR PROBLEMA INCORRECTEEE!!!!!!!");

        this.partida = new Partida(p, SecondUser, AuxUser, true);

        Maquina m1 = (Maquina) SecondUser;
        m1.setTauler((this.partida).getTauler());
        m1.setProfunditat((this.partida).getMat());

        Maquina m2 = (Maquina) AuxUser;
        m2.setTauler((this.partida).getTauler());
        m2.setProfunditat((this.partida).getMat());

    }

//    public ControladorDomini() {
//
//        boolean save_stats;
//        //MIREM SI GUARDEM LES ESTADISTIQUES
//        if (simulacio) {
//            if (this.num_prob == 1) save_stats = true;
//            else save_stats = false;
//        }
//        else {
//            int primer = this.ProblemaController.getPrimer();
//            if (primer == define.WHITE) save_stats = (this.JugadorController.getJugadorTipus(define.WHITE) == define.USER);
//            else save_stats = (this.JugadorController.getJugadorTipus(define.BLACK) == define.USER);
//        }
//        this.partida = new Partida(ProblemaController.getProblema(), JugadorController.getWhite(),
//                JugadorController.getBlack(),save_stats);
//    }
//


    //FUNCIONS USADES PER LA CAPA DE PRESENTACIO AL INSTANCIARLO


    public String getPecaTipus(int x, int y) {
        return partida.getPecaTipusTauler(new Posicion(x,y));
    }
    public int getPecaColor(int x, int y) {
        return partida.getPecaColorTauler(new Posicion(x,y));
    }

    public int colorJugadorActual() {
        return partida.getTorn();
    }
    public int colorJugadorNoActual() {
        if (partida.getTorn() == define.WHITE) return define.BLACK;
        return define.WHITE;
    }

    public Integer[][] getAll_movs(int x, int y) {
        Posicion[] pos = partida.getTauler().todos_movimientos(new Posicion(x,y));
        //convertim les posicions a tipus simples
        Integer[][] all_pos = new Integer[pos.length][2];
        for (int i = 0; i < all_pos.length; ++i ) {
            all_pos[i][0] = pos[i].x;
            all_pos[i][1] = pos[i].y;
        }
        return all_pos;
    }
    public int juga_torn(int x0, int y0, int x, int y) {
        return partida.jugar_tornGUI(new Posicion(x0,y0), new Posicion(x,y));
    }

    public int getRondaPartida() {
        return partida.getRonda();
    }
    //PROBLEMA
    int getPrimer() {
        return this.problema.getPrimer();
    }

    Problema getProblema() {
        return this.problema;
    }

    //JUGADOR
    public int getMasterColor() {
        return User.getColor();
    }
//    Jugador getWhite() {
//        return this.White;
//    }
//
//    Jugador getBlack() {
//        return this.Black;
//    }

    /**
     * pre: color == define.WHITE || color == define.BALCK
     * @param color - color del jugador
     * @return tipus del jugador en concret
     */
//    int getJugadorTipus(int color) {
//        if (color == define.WHITE) {
//            return this.White.getTipus();
//        }
//        else { // (color == define.BLACK)
//            return this.Black.getTipus();
//        }
//    }

    //public
}
