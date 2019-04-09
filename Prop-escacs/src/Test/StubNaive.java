//class StubNaive extends Maquina {
class StubNaive extends Maquina {
    private Taulell Tauler;
    private static int profunditat = 4;
    private int colorJugador;

    public StubNaive(Taulell Tauler, int color) {
        this.Tauler = Tauler;
        colorJugador = color;
    }

    StubNaive() {
        System.out.println("Instancia Maquina Naive");
    }

    public long moviment(Posicion ini, Posicion fi) {
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        Peca pecesTau[][] = Tauler.getTauler();

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if ((pecesTau[i][j].getColor() == colorJugador) && !pecesTau[i][j].getTipus().equals("Peca_nula")) {
                    mejorIni = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        mejorDesti = desti;
                    }
                }
            }
        }
        return 0;
    }
}