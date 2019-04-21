/**
 * @author Marian Dumitru Danci
 */
public abstract class Maquina extends Jugador {
    private Taulell Tauler;
    private Peca pecesTau[][];
    private static final int tipus = define.MAQUINA;


    public int getTipusMaquina() {
        return this.tipus;
    }

    public abstract long moviment(Posicion inici, Posicion fi);

    public abstract void setTauler(Taulell t);
    public abstract void setProfunditat(int torn);
    /*
    evaluar()
     */

    public int puntuacioPeca(String tipus) {
        int puntuacio = 0;
        switch (tipus) {
            case define.PEO:
                puntuacio = 10;
                break;
            case define.CAVALL:
                puntuacio = 35;
                break;
            case define.ALFIL:
                puntuacio = 35;
                break;
            case define.TORRE:
                puntuacio = 52;
                break;
            case define.REINA:
                puntuacio = 100;
                break;
        }
        return puntuacio;
    }

    public void setTaulerMaquina(Taulell tauler) {
        this.Tauler = tauler;
        pecesTau = Tauler.getTauler();
    }

    public int getColorPeca(Posicion pos) {
        return pecesTau[pos.x][pos.y].getColor();
    }

    public String getTipusPeca(Posicion pos) {
        return pecesTau[pos.x][pos.y].getTipus();
    }

    public Posicion[] totsMovimentsPeca(Posicion pos) {
        return Tauler.todos_movimientos(pos);
    }
    public boolean mourePeca(Posicion ini, Posicion desti, int color) {
        int colorAdversari = color ^ 1;
        if(pecesTau[desti.x][desti.y].getTipus() == define.REI) {
            return false;
        }
        else {
            while (!Tauler.mover_pieza(ini, desti, color));
        }
        return true;
    }

    public int evaluar(int colorJugador) {
        int colorContrari = colorJugador ^ 1;
        int total = 0;

        int escac_mat1 = Tauler.escac_i_mat(colorContrari);
        if (escac_mat1 == 1) {
            total += 5000;
        }
        else if (escac_mat1 == 0) {
            total += 1000;
        }
        else if (escac_mat1 == 2){
            total -= 1000;
        }

        int escac_mat2 = Tauler.escac_i_mat(colorJugador);
        if (escac_mat2 == 1) {
            total -= 5000;
        }
        else if (escac_mat2 == 0) {
            total -= 500;
        }
        else if (escac_mat2 == 2){
            total += 1000;
        }

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    total += puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion ini = new Posicion(i, j);

                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    for (Posicion desti : movimentsPosibles) {
                        if (pecesTau[desti.x][desti.y].getColor() != colorJugador) {
                            total++;
                        }
                    }
                }
                else if (pecesTau[i][j].getColor() == colorContrari) {
                    total -= puntuacioPeca(pecesTau[i][j].getTipus());
                    Posicion ini = new Posicion(i, j);
                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    for (Posicion desti : movimentsPosibles) {
                        if (pecesTau[desti.x][desti.y].getColor() == colorJugador) {
                            total--;
                        }
                    }
                }
            }
        }
        /*
        if (pecesTau[3][2].getTipus() == define.REI && pecesTau[4][3].getTipus() == define.CAVALL) {
            System.out.println("*************************************************");
            System.out.println("PUNTS : " + total);
            System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
            System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));

            Tauler.printTauler();
        }
        */
        /*
        //if (pecesTau[4][3].getTipus() == define.PEO && pecesTau[7][5].getTipus() == define.CAVALL) {
        if (pecesTau[7][5].getTipus() == define.CAVALL) {
            System.out.println("*************************************************");
            System.out.println("PUNTS : " + total);
            System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
            System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));

            Tauler.printTauler();
        }
        */

        //System.out.println("PUNTS : " + total);
        //System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
        //System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));
        //Tauler.printTauler();

        return total;
    }

    public int estatMat(int color) {
        return Tauler.escac_i_mat(color);
    }

    public boolean desferMoviment(Posicion ini, Posicion desti, String peca, int color) {
        Peca aux = pecesTau[desti.x][desti.y];
        Tauler.destrueix_peça(desti);
        Tauler.crear_peça(ini,color,aux.getTipus());

        if(!peca.equals(define.PECA_NULA)) {
            Tauler.crear_peça(desti, color ^ 1, peca);
        }
        return true;
    }

    /*
    Donar bonus per profunditata!!!
     */
}