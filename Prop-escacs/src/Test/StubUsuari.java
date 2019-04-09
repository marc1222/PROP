class StubUsuari extends Jugador {

    StubUsuari() {
        System.out.println("Instancia usuari");
    }

    public String getNom() {
        return "Nom de l'usuari";
    }

    public void setNom() {
        System.out.println("Nou nom usuari");
    }

    public boolean getAtaca() {
        return true;
    }

    public void setAtaca(boolean ataca) {
        System.out.println("Posa ataca a " + ataca);
    }

    public boolean iniciarSessio() {
        System.out.println("Sessio iniciada");
        return true;
    }

    public boolean resigstrar() {
        System.out.println("Usuari registrat");
        return true;
    }

    public boolean baixa() {
        System.out.println("Usuari donat de baixa");
        return true;
    }

    public static String[] totsUsuaris() {
        String[] usrs = {"Usuari1","Usuari2"};
        return usrs;
    }

    /*
    public long moviment(Posicion ini, Posicion fi, Taulell Tauler, int colorJugador) {
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
    */

    public long moviment(Posicion ini, Posicion fi) {
        return 0;
    }
}