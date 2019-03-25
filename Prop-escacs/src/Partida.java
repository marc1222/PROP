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
        try {
            //iniciar el taulell -> crear totes les peces
            int Peces[][] = Prob.getPeces();
            for (int i = 0; i < Peces.lenght; ++i) {
                int aux[] = Peces[i];
                if (aux.lenght != 4)
                    throw new ChessException("Error al crear la pieza");
                Tauler.crear_peça(aux[0], aux[1], aux[2], aux[3]);
            }
            int x0,y0,x,y;
            if (this.torn == 'W') W.tirar(x0,y0,x,y);
            else B.tirar(x0,y0,x,y);
            jugar_turno(x0,y0,x,y);
        } catch( ChessException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //
    //pre:
    //post:
    public void jugar_turno(int x0, int y0, int x, int y)
    {
        Tauler.mover_pieza(x0,y0,x,y,torn);
        Tauler.torn = !Tauler.torn;
    }
}
