public class Partida  {

    private int torn;
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
        this.Tauler = new Taulell(P.getPeces());
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
    public void empezar_partida() {
            Posicion inici = new Posicion();
            Posicion fi = new Posicion();
            jugar_torn(inici,fi);
    }

    //
    //pre:
    //post:
    public void jugar_torn(Posicion inici, Posicion fi)
    {

        if (this.torn == 'W') {
            W.tirar(inici,fi);
            System.out.println("El jugador BLANC vol moure la peça que es troba a ["+inici.x+"]["+inici.y+"] a la posició destí ["+fi.x+"]["+fi.y+"]");
        }
        else B.{
            tirar(inici,fi);
            System.out.println("El jugador NEGRE vol moure la peça que es troba a ["+inici.x+"]["+inici.y+"] a la posició destí ["+fi.x+"]["+fi.y+"]");
        }
        Tauler.mover_pieza(inici,fi,torn);

        if (this.torn == define.WHITE) this.torn = define.BLACK;
        else this.torn = define.WHITE;
    }
}
