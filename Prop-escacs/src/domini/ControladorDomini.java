package domini;

import gui.JugarPartidaView;
import javafx.geometry.Pos;

import java.util.ArrayList;

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
        problema = p;
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
            System.out.println("Naive defensa");
            this.SecondUser  = new Naive((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
            (this.User).setColor(p.getPrimer());
        } else if (!user_ataca && user_oponent == define.NAIVE) {
            //defender      &&  //naive
            System.out.println("Naive ataca");
            this.SecondUser  = new Naive(p.getPrimer());
            (this.User).setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
        } else if (user_ataca && user_oponent == define.SMART) {
            //atacar   &&    //smart
            //TODO CANVIAR NAIVE A SMART
            this.SecondUser  = new Smart((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
            (this.User).setColor(p.getPrimer());
        } else {//if (!user_ataca && user_oponent == define.SMART) {
            //defender      &&  //smart
            //TODO CANVIAR NAIVE A SMART
            this.SecondUser  = new Smart(p.getPrimer());
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
        problema = p;

        if (white == define.NAIVE && black == define.NAIVE) {
            SecondUser = new Naive(define.WHITE);
            AuxUser = new Naive(define.BLACK);
        } else if (white == define.NAIVE && black == define.SMART) {
            SecondUser = new Naive(define.WHITE);
            AuxUser = new Smart(define.BLACK);
        } else if (white == define.SMART && black == define.NAIVE) {
            SecondUser = new Smart(define.WHITE);
            AuxUser = new Naive(define.BLACK);
        } else {
            // BOTH SMARTS
            SecondUser = new Smart(define.WHITE);
            AuxUser = new Smart(define.BLACK);
        }

        this.partida = new Partida(p, SecondUser, AuxUser, true);

        Maquina m1 = (Maquina) SecondUser;
        m1.setTauler((this.partida).getTauler());
        m1.setProfunditat((this.partida).getMat());

        Maquina m2 = (Maquina) AuxUser;
        m2.setTauler((this.partida).getTauler());
        m2.setProfunditat((this.partida).getMat());

    }
    public int getMaxRondes() {
        return partida.getMaxRondes();
    }
    public void juga_simulacio(int problemaID) {

        Problema p = new Problema();
        int res = Problema.getProblemaId(problemaID, p);
        if (res != 0) System.out.println("¡¡¡¡¡¡¡¡ERRROROROROROROR PROBLEMA INCORRECTEEE!!!!!!!");
        problema = p;
        this.partida = new Partida(p, SecondUser, AuxUser, true);

        Maquina m1 = (Maquina) SecondUser;
        m1.setTauler((this.partida).getTauler());
        m1.setProfunditat((this.partida).getMat());

        Maquina m2 = (Maquina) AuxUser;
        m2.setTauler((this.partida).getTauler());
        m2.setProfunditat((this.partida).getMat());

    }

    public int tipusJugadorActual() {
        return partida.tipusJugActual();
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
    public int colorJugadorPrimer() {
        int ret = partida.getProb().getPrimer();
        System.out.print(ret);
        return ret;
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

    public int[] juga_tornMaquina(int x0, int y0, int x, int y) {
        Posicion ini = new Posicion(x0,y0);
        Posicion fi = new Posicion(x,y);
        int ret[] = new int[5];
        ret[0] = partida.jugar_tornGUI(ini, fi);
        ret[1] = ini.x;
        ret[2] = ini.y;
        ret[3] = fi.x;
        ret[4] = fi.y;
        return ret;
    }

    public int getRondaPartida() {
        return partida.getRonda();
    }
    //PROBLEMA
    public int getPrimer() {
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
    public boolean eliminarUsuari(String usuari) {
        return User.eliminarUsuari(usuari);
    }

    public boolean iniciarSessio(String nomUsuari, String contrasenya) {
        return User.iniciarSessio(nomUsuari, contrasenya);
    }

    public boolean registrar(String nomUsuari, String contrasenya1, String contrasenya2) {
        return User.registrar(nomUsuari, contrasenya1, contrasenya2);
    }

    public ArrayList<String> estadistiquesProblema(String problema) {
        return Estadistica.estadistiquesProblema(problema);
    }

    public ArrayList<String> estadistiquesUsuari(String usuari) {
        return Estadistica.estadistiquesUsuari(usuari);
    }

    public void eliminarStatsUsuari(String usuari) {
        Estadistica.eliminarStatsUsuari(usuari);
    }

    public void eliminarStatsProblema(String problema) {
        Estadistica.eliminarStatsProblema(problema);
    }

    public String getMainUserName() {
        String nom = "Convidat";
        Jugador white = partida.getWhite();
        if (white.getTipus() == define.USER) {
            Usuari w = (Usuari)white;
            nom = w.getNom();

        }
        if (!nom.equals("Convidat")) return nom;
        else {
            Jugador black = partida.getBlack();
            if (black.getTipus() == define.USER) {
                Usuari b = (Usuari)black;
                nom = b.getNom();
            }
        }
        return nom;
    }

    public static int creaProblema(int njug, String fen, String user) {
        Problema p = new Problema();
        p.setIdCreador(user);
        return p.crear_problema(njug, fen);
    }

    public static String graficToFEN(String tipus[][], int colors[][], int primer) {
        return Problema.graficToFEN(tipus, colors, primer);
    }

    public static int borraProblema(int id, String user) {
        Problema p = new Problema();
        int res = Problema.getProblemaId(id, p);
        if (!user.equals(p.getIdCreador())) {
            System.out.println("No ets el creador d'aquest problema");
            return -3;
        }
        p.eliminar_problema();
        Estadistica.eliminarStatsProblema(String.valueOf(id));
        return 0;
    }
    public void save_stats(int winner, int time) {
        partida.save_stats(winner,time);
    }
}
