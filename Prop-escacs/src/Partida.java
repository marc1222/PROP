public class Partida  {

    private char torn;
    private Taulell Tauler;
    private Problema Prob;
    private Usuari W;
    private Usuari B;
    private Estadistica Stat;


    //creadora

    //pre: true
    //post: instancia un problema, dos usuaris i un taulell;
    Partida(Problema P, Usuari w, Usuari b) {
        this.torn = P.torn;
        this.Tauler = new Taulell();
        this.Prob = P;
        this.W = w;
        this.B = b;
        this.Stat = null;
    }

    //private



    //public

    //
    //pre: true
    //post:
    public void empezar_partida()
    {
        //iniciar el taulell -> crear totes les peces

    }

    //
    //pre:
    //post:
    public void jugar_turno(int x0, int y0, int x, int y)
    {
        Tauler.mover_pieza(x0,y0,x,y,torn);
        //
    }
}
