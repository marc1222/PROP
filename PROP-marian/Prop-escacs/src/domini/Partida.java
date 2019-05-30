package domini;
/**
 * @author Marc Guinovart
 */

public class Partida  {

    //------------------------------------------------------------------------
    //ATRIBUTS DE LA CLASSE
    //------------------------------------------------------------------------

    private int torn;
    private int ronda;
    private Taulell Tauler;
    private Problema Prob;
    private Jugador W;
    private Jugador B;
    private boolean save_stat;
    private long clock;
    private int max_rondes;


    //------------------------------------------------------------------------
    //CREADORES
    //------------------------------------------------------------------------

    //creadora per defecte
    Partida() {

    }

    /**
     * creadora d'una partida amb tots els paràmetres necessaris
     * @param P
     * @param w
     * @param b
     * @param save
     */
    Partida(Problema P, Jugador w, Jugador b,boolean save) {
        this.torn = P.getPrimer();
        this.ronda = 0;
        this.Tauler = new Taulell(new Taulell(P.getPeces()));
        this.Prob = P;
        this.W = w;
        this.B = b;
        this.clock = 0;
        this.save_stat = save;
        this.max_rondes = (P.getNumJugades()*2)-1;
    }

    //------------------------------------------------------------------------
    //OPERACIONS PRIVADES
    //------------------------------------------------------------------------

    /**
     *juga una ronda, tot indicant si aquesta serà la ultima de la partida i en cas que ho sigui el jugador guanyador
     *pre: true
     *post: retorna el jugador guanyador si es produeix escac i mat, o be si s'excedeix el numero de rondes permeses, altrament retorna -1
     * @param inici
     * @param fi
     * @return
     */

    private int jugar_torn(Posicion inici, Posicion fi)
    {
        this.ronda++;
        long aux;
        if (this.ronda <= this.max_rondes) {
            boolean jugada = false;
            do {
                if (this.torn == define.WHITE) {
                    aux = W.moviment(inici, fi);
                    if (Prob.getPrimer() == define.WHITE) this.clock += aux;
                    System.out.println("MOV: El jugador BLANC vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
                } else {
                    //tirem
                    aux = B.moviment(inici, fi);
                    //actualitzar rellotge
                    if (Prob.getPrimer() == define.BLACK) this.clock += aux;
                    System.out.println("MOV: El jugador NEGRE vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
                }
                jugada = Tauler.mover_pieza(inici, fi, this.torn);
                if (!jugada) System.out.println("¡¡¡¡MOVIMENT INCORRECTE!!!!");
            } while (!jugada); //mover pieza retorna true si s'ha pogut executar el moviment o fals altrament

            aux = Tauler.escac_i_mat((this.torn == define.WHITE) ? define.BLACK : define.WHITE);
            if (aux == 1) { //jaque mate del jugador que acaba de tirar
                System.out.println("--- FI DE LA PARTIDA --- ESCAC I MAT ---");
                return this.torn;
            }
            else if (aux == 0) { //jaque jugador que acaba de tirar fa escac
                System.out.println("--- ATENCIÓ --- ESCAC ---");

            }
            else if (aux == 2) { //ofegat, automaticament el jugador que acaba de tirar perd
                System.out.println("--- FI DE LA PARTIDA --- REI OFEGAT ---");
                return (this.torn == define.WHITE)? define.BLACK: define.WHITE;

            }
            if (this.torn == define.WHITE) this.torn = define.BLACK;
            else this.torn = define.WHITE;
            Tauler.printTauler();
            return -1;

        }

        return this.torn;
    }


    //------------------------------------------------------------------------
    //OPERACIONS PÚBLIQUES
    //------------------------------------------------------------------------
    public Problema getProb() {
        return Prob;
    }
    public int getRonda() {
        return ronda;
    }
    public int getMaxRondes() {
        return max_rondes;
    }
    public int tipusJugActual() {
        if (this.torn == define.WHITE) return W.getTipus();
        else return B.getTipus();
    }
    /**
     *juga una ronda, tot indicant si aquesta serà la ultima de la partida i en cas que ho sigui el jugador guanyador
     *pre: true
     *post: retorna el jugador guanyador si es produeix escac i mat, o be si s'excedeix el numero de rondes permeses,
     * altrament retorna -1, retorna -2 si el moviment ha estat incorrecte i no sha fet
     * @param inici
     * @param fi
     * @return
     */

    public int jugar_tornGUI(Posicion inici, Posicion fi)
    {
        this.ronda++;
        long aux;
        if (this.ronda <= this.max_rondes) {

            if (this.torn == define.WHITE && W.getTipus() == define.MAQUINA)
                W.moviment(inici,fi);
            else if (this.torn == define.BLACK && B.getTipus() == define.MAQUINA)
                B.moviment(inici, fi);
            boolean jugada = Tauler.mover_pieza(inici, fi, this.torn);
            if (jugada) {
                aux = Tauler.escac_i_mat((this.torn == define.WHITE) ? define.BLACK : define.WHITE);
                if (aux == 1) { //jaque mate del jugador que acaba de tirar
                    System.out.println("--- FI DE LA PARTIDA --- ESCAC I MAT ---");
                    return this.torn;
                } else if (aux == 0) { //jaque jugador que acaba de tirar fa escac
                    System.out.println("--- ATENCIÓ --- ESCAC ---");

                } else if (aux == 2) { //ofegat, automaticament el jugador que acaba de tirar perd
                    System.out.println("--- FI DE LA PARTIDA --- REI OFEGAT ---");
                    return (this.torn == define.WHITE) ? define.BLACK : define.WHITE;

                }
                if (this.torn == define.WHITE) this.torn = define.BLACK;
                else this.torn = define.WHITE;
                Tauler.printTauler();
                if (this.ronda == this.max_rondes) return this.torn;
                return -1;
            } else {
                this.ronda--;
                return -2; //ERROR en el moviment
            }
        }
        return this.torn;
    }


    int getTorn() {
        return this.torn;
    }
    /**
     * Retorna el tipus de la peca a la posicio pos
     * pre: 0 <= {pos.x && pos.y} <= 7
     * @param pos - posicio de la peca a buscar
     * @return tipus de la peca en la posicio pos
     */
    String getPecaTipusTauler(Posicion pos) {
        return (this.Tauler.getPecaPosicio(pos).getTipus());
    }
    /**
     * Retorna el color de la peca a la posicio pos
     * pre: 0 <= {pos.x && pos.y} <= 7
     * @param pos - posicio de la peca a buscar
     * @return color de la peca en la posicio pos
     */
    int getPecaColorTauler(Posicion pos) {
        return (this.Tauler.getPecaPosicio(pos).getColor());
    }
    /**
     * getter de taulell d'una partida
     */
    public Taulell getTauler() {
        return this.Tauler;
    }
    /**
     * getter de las jugadas del problema
     */
    public int getMat() {
        return Prob.getNumJugades();
    }

    public Jugador getWhite() {
        return this.W;
    }
    public Jugador getBlack() {
        return this.B;
    }

    /**
     *     encarregada de jugar una partida al complet (mitjançant la funció jugar_torn)
     *     i cridar a estadística per guardar-ne les estadísitques, si s'ha de guardar
     *     pre: partida vàlida inicialitzada correctament
     *     post: juga la partida
     */

    public void jugar_partida() {
        Posicion inici = new Posicion();
        Posicion fi = new Posicion();
        int ret;
        String res = (this.torn == define.WHITE) ? "WHITE" : "BLACK";
        System.out.println("INICI: El jugador que ha de guanyar es el "+res);
        Tauler.printTauler();
        while ( (ret = jugar_torn(inici,fi) ) == -1);

        res = (define.WHITE == ret) ? "WHITE" : "BLACK";
        System.out.println("FINAL: Ha guanyat el jugador: "+res);
        if (this.save_stat && ret==Prob.getPrimer()) {
            //guardar stat
            Usuari Wu,Bu;
            String name = "";
            if (W.getTipus() == define.USER && Prob.getPrimer()== define.WHITE) {
                Wu = (Usuari) W;
                name = Wu.getNom();
            }
            else if (B.getTipus() == define.USER && Prob.getPrimer()== define.BLACK) {
                Bu = (Usuari) B;
                name = Bu.getNom();
            }
            Estadistica.guardarTemps(String.valueOf(Prob.getId()), name, String.valueOf(this.ronda/2 + 1),String.valueOf(this.clock));
            System.out.println("Se han guardado las estadísticas");
        }
    }
    public void save_stats(int winner, int time) {
        if (this.save_stat && winner==Prob.getPrimer()) {
            //guardar stat
            Usuari Wu,Bu;
            String name = "";
            if (W.getTipus() == define.USER && Prob.getPrimer()== define.WHITE) {
                Wu = (Usuari) W;
                name = Wu.getNom();
            }
            else if (B.getTipus() == define.USER && Prob.getPrimer()== define.BLACK) {
                Bu = (Usuari) B;
                name = Bu.getNom();
            }
            Estadistica.guardarTemps(String.valueOf(Prob.getId()), name, String.valueOf(this.ronda/2 + 1),String.valueOf(time));
            System.out.println("Se han guardado las estadísticas");
        }
    }

   }
