public class Partida  {

    private int torn;
    private int ronda;
    private Taulell Tauler;
    private Problema Prob;
    private Usuari W;
    private Usuari B;
    private boolean save_stat;
    private int clock;


    //creadora

    //pre: true
    //post: instancia un problema, dos usuaris i un taulell;
    Partida(Problema P, Usuari w, Usuari b,boolean save) {
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
        if (this.save_stat) {
            //guardar stat
            String name = (Prob.getPrimer()==define.WHITE)?W.getId():B.getId();
            //se tendria que grabar estadistica si se pierde????->o deberia ser otro campo que guardar mas????
            guardarTiempo(String.valueOf(Prob.getId()), name, String.valueOf(this.ronda),String.valueOf(this.clock));
            System.out.println("Se han guardado las estadísticas");
        }
    }

    //juga una ronda, tot indicant si aquesta serà la ultima de la partida i en cas que ho sigui el jugador guanyador
    //pre: sempre el jugador que acaba de tirar es el que suposadament ha de guanyar
    //post: retorna el jugador guanyador si es produeix escac i mat, o be si s'excedeix el numero de rondes permeses, altrament retorna -1
    public int jugar_torn(Posicion inici, Posicion fi)
    {
        this.ronda++;
        int aux;
        if (this.ronda <= this.Prob.getJugades()) {
            do {
                if (this.torn == define.WHITE) {
                    aux = W.tirar(inici, fi);
                    if (Prob.getPrimer() == define.WHITE) this.clock += aux;
                    System.out.println("MOV: El jugador BLANC vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
                } else {
                    //tirem
                    aux = B.tirar(inici, fi);
                    //actualitzar rellotge
                    if (Prob.getPrimer() == define.BLACK) this.clock += aux;
                    System.out.println("MOV: El jugador NEGRE vol moure la peça que es troba a [" + inici.x + "][" + inici.y + "] a la posició destí [" + fi.x + "][" + fi.y + "]");
                }
            } while (!Tauler.mover_pieza(inici, fi, this.torn)); //mover pieza retorna true si s'ha pogut executar el moviment o fals altrament

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
            return -1;

        }
        return this.torn;
    }
}
