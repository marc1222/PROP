import java.time.temporal.JulianFields;

public class Partida  {

    private int torn;
    private int ronda;
    private Taulell Tauler;
    private Problema Prob;
<<<<<<< HEAD
    private Jugador W;
    private Jugador B;
    private boolean save_stat;
    private long clock;
=======
    private Usuari W;
    private Usuari B;
    private boolean save_stat;
    private int clock;
>>>>>>> 1a1a648fe941a0ad481f227bbb1789229b419869


    //creadora

    //pre: true
    //post: instancia un problema, dos usuaris i un taulell;
<<<<<<< HEAD
    Partida(Problema P, Jugador w, Jugador b,boolean save) {
=======
    Partida(Problema P, Usuari w, Usuari b,boolean save) {
>>>>>>> 1a1a648fe941a0ad481f227bbb1789229b419869
        this.torn = P.getPrimer();
        this.ronda = 0;
        this.Tauler = new Taulell(P.getPeces());
        this.Prob = P;
        this.W = w;
        this.B = b;
        this.clock =  0;
        this.save_stat = save;
    }

    //private



    //public
    public Taulell getTauler() {
        return this.Tauler;
    }
    //
    //pre: true
    private Estadistica Stat;
    private Estadistica Stat;
    //post:
    public void jugar_partida() {
        Posicion inici = new Posicion();
        Posicion fi = new Posicion();
        int ret;
        String res = (this.torn == define.WHITE) ? "WHITE" : "BLACK";
        System.out.println("INICI: El jugador que ha de guanyar es el "+res);
<<<<<<< HEAD
        Tauler.printTauler();
        while ( (ret = jugar_torn(inici,fi) ) == -1);

        res = (define.WHITE == ret) ? "WHITE" : "BLACK";
        System.out.println("FINAL: Ha guanyat el jugador: "+res);
        if (this.save_stat && ret==Prob.getPrimer()) {
            //guardar stat
            Usuari Wu = (Usuari) W;
            Usuari Bu = (Usuari) B;
            String name = (Prob.getPrimer()==define.WHITE)?Wu.getNom():Bu.getNom();
            //se tendria que grabar estadistica si se pierde????->o deberia ser otro campo que guardar mas????
            Estadistica.guardarTemps(String.valueOf(Prob.getId()), name, String.valueOf(this.ronda),String.valueOf(this.clock));
=======
        while ( (ret = jugar_torn(inici,fi) ) == -1);
        res = (define.WHITE == ret) ? "WHITE" : "BLACK";
        System.out.println("FINAL: Ha guanyat el jugador: "+res);
        if (this.save_stat) {
            //guardar stat
            String name = (Prob.getPrimer()==define.WHITE)?W.getId():B.getId();
            guardarTiempo(String.valueOf(Prob.getId()), name, String.valueOf(this.ronda),String.valueOf(this.clock));
>>>>>>> 1a1a648fe941a0ad481f227bbb1789229b419869
            System.out.println("Se han guardado las estadísticas");
        }
    }

    //juga una ronda, tot indicant si aquesta serà la ultima de la partida i en cas que ho sigui el jugador guanyador
    //pre: sempre el jugador que acaba de tirar es el que suposadament ha de guanyar
    //post: retorna el jugador guanyador si es produeix escac i mat, o be si s'excedeix el numero de rondes permeses, altrament retorna -1
    public int jugar_torn(Posicion inici, Posicion fi)
    {
        if (this.torn == Prob.getPrimer()) this.ronda++;
        long aux;
        if (this.ronda <= this.Prob.getNumJugades()) {
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
            if (aux == 1) { //jaque mate
                System.out.println("--- FI DE LA PARTIDA --- ESCAC I MAT ---");
                return this.torn;
            }
            else if (aux == 0) { //jaque
                System.out.println("--- ATENCIÓ --- ESCAC ---");

            }

            if (this.torn == define.WHITE) this.torn = define.BLACK;
            else this.torn = define.WHITE;
            Tauler.printTauler();
            return -1;

        }

        return this.torn;
    }
}