public abstract class StubMaquina {
    public abstract long moviment(Posicion origen, Posicion desti);

    public abstract void setTauler(Taulell t);

    public abstract void setProfunditat(int torn);

    public int getTipusMaquina() {
        return 0;
    }

    public int puntuacioPeca(String tipus) {
        return 0;
    }

    public void setTaulerMaquina(Taulell tauler) {
        System.out.println("Tauler especificat.");
    }

    public int getColorPeca(Posicion pos) {
        return 0;
    }

    public String getTipusPeca(Posicion pos) {
        return define.PECA_NULA;
    }

    public Posicion[] totsMovimentsPeca(Posicion pos) {
        Posicion pos1 = new Posicion(0, 0);
        Posicion[] tots = {pos1};
        return tots;
    }

    public boolean mourePeca(Posicion origen, Posicion desti, int color) {
        return false;
    }

    public int evaluar(int colorJugador) {
        return 0;
    }

    public int estatMat(int color) {
        return 0;
    }

    public void desferMoviment(Posicion origen, Posicion desti, String peca, int color) {
        System.out.println("Moviment desfet.");
    }
}