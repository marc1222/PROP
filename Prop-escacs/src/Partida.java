public class Partida  {

    private int torn;
    private int ronda;
    private Taulell Tauler;
    private Problema Prob;
    private Usuari W;
    private Usuari B;
    private Estadistica Stat;
    private int clock;


    //creadora

    //pre: true
    //post: instancia un problema, dos usuaris i un taulell;
    Partida(Problema P, Usuari w, Usuari b) {
        this.torn = P.getPrimer();
        this.ronda = 0;
        this.Tauler = new Taulell(P.getPeces());
        this.Prob = P;
        this.W = w;
        this.B = b;
        this.Stat = null;
        this.clock =  0;
    }

    //private



    //public

    //
    //pre: true
    //post:
    public void jugar_partida() {
            Posicion inici = new Posicion();
            Posicion fi = new Posicion();
            int ret;
            String res = (this.torn == define.WHITE) ? "WHITE" : "BLACK";
            System.out.println("INICI: El jugador que ha de guanyar es el "+res);
            while ( (ret = jugar_torn(inici,fi) ) == -1);
            res = (define.WHITE == ret) ? "WHITE" : "BLACK";
            System.out.println("FINAL: Ha guanyat el jugador: "+res);

    }

    //juga una ronda, tot indicant si aquesta serà la ultima de la partida i en cas que ho sigui el jugador guanyador
    //pre: sempre el jugador que acaba de tirar es el que suposadament ha de guanyar
    //post: retorna el jugador guanyador si es produeix escac i mat, o be si s'excedeix el numero de rondes permeses, altrament retorna -1
    public int jugar_torn(Posicion inici, Posicion fi)
    {
        if (this.ronda <= this.Prob.getJugades()) {

            if (this.torn == 'W') {
                W.tirar(inici, fi);
                System.out.println("MOV: El jugador BLANC vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
            } else {
                B.tirar(inici, fi);
                System.out.println("MOV: El jugador NEGRE vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
            }
            Tauler.mover_pieza(inici, fi, torn);

            if (Tauler.escac_i_mat() == true) return this.torn;

            if (this.torn == define.WHITE) this.torn = define.BLACK;
            else this.torn = define.WHITE;
            return -1;

        }
        return this.torn;
    }
}
