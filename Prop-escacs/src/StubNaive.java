 class StubNaive {
    public int getTipus() {
        return 0;
    }

    public void setTauler(Taulell tauler) {
        System.out.println("Tauler especificat.");
    }

    public void setProfunditat(int mat) {
        System.out.println("Profunditat especificada.");
    }

    public void actualitzarProfunditat() {
        System.out.println("Profunditat actualitzada.");
    }

    public long moviment(Posicion origen, Posicion desti) {
        return 0;
    }
}
