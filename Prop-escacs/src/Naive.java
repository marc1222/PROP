import javafx.geometry.Pos;

/**
 * Esta clase da movientos en la partida de ajedrez, etc
 * @author Marian Dumitru Danci
 */
public class Naive extends Maquina {
    private Taulell Tauler;
    private Peca pecesTau[][];
    private static int profunditat = 2;
    private int colorJugador;
    private int colorContrari;
    private int tipus;

    public Naive(int color) {
        this.Tauler = null;
        colorJugador = color;
        colorContrari = color ^ 1;
        tipus = define.MAQUINA;
    }

    public int getTipus() {
        return this.tipus;
    }

    public void setTauler(Taulell tauler) {
        this.Tauler = tauler;
        pecesTau = Tauler.getTauler();
    }

    public long moviment(Posicion inici, Posicion fi) {
        System.out.println("Loading...");
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF;
        maxF = -9999;

        outerloop:
        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    Posicion ini = new Posicion(i, j);
                    Posicion[] tots =  Tauler.todos_movimientos(ini);
                    for (Posicion desti : tots) {
                        //desti.mostrar();
                        String auxPeca = define.PECA_NULA;
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                        }
                        //while (!Tauler.mover_pieza(ini, desti, colorJugador));
                        if (!mover(ini, desti, colorJugador)) {
                            System.out.println("MATA AL REI");
                        }
                        /*
                        if (Tauler.escac_i_mat(colorContrari) == 1) {
                            inici.x = ini.x;
                            inici.y = ini.y;
                            fi.x = desti.x;
                            fi.y = desti.y;
                            break outerloop;
                        }
                        */
                        int aux = minimax(profunditat, false);
                        while (!desferMoviment(ini, desti, auxPeca, colorJugador));
                        if (aux > maxF) {
                            mejorIni.x = ini.x;
                            mejorIni.y = ini.y;
                            mejorDesti.x = desti.x;
                            mejorDesti.y = desti.y;
                            maxF = aux;
                        }
                    }
                }
            }
        }
        //System.out.println("\n\nRESULTAT : " + maxF);

        inici.x = mejorIni.x;
        inici.y = mejorIni.y;
        fi.x = mejorDesti.x;
        fi.y = mejorDesti.y;
        return 0;
    }

    private int minimax(int depth, boolean maximitzar) {
        /*
        if (pecesTau[6][3].getTipus() == define.CAVALL && pecesTau[4][3].getTipus() == define.PEO) {
        //if (pecesTau[7][5].getTipus() == define.CAVALL) {
            System.out.println("*************************************************");
            //System.out.println("PUNTS : " + total);
            System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
            System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));

            Tauler.printTauler();
        }
        */

        int estatPropi = Tauler.escac_i_mat(colorContrari);
        int estatOponent = Tauler.escac_i_mat(colorJugador);
        if (depth == 0 ||
                (estatPropi == 1) || (estatOponent == 1) ||
                (estatPropi == 2 && !maximitzar) || (estatOponent == 2 && maximitzar)) {
            //System.out.println("1- " + Tauler.escac_i_mat(colorContrari) + " 2- " + Tauler.escac_i_mat(colorJugador));
            return evaluar();
        }
        int min, max;
        max = -9999;
        min = 9999;

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {

                if (maximitzar && (pecesTau[i][j].getColor() == colorJugador)) {
                    Posicion ini = new Posicion(i, j);

                    //System.out.println("+++ MAX +++");
                    //System.out.println("POS: " + i + "-" + j);
                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                        }
                        if (!mover(ini, desti, colorJugador)) {
                            return evaluar();
                        }
                        int aux = minimax(depth - 1, false);
                        if (aux > max) {
                            max = aux;
                        }
                        while (!desferMoviment(ini, desti, auxPeca, colorJugador));
                    }
                }
                else if (!maximitzar && (pecesTau[i][j].getColor() == (colorContrari))) {
                    Posicion ini = new Posicion(i, j);

                    //System.out.println("--- MIN ---");
                    //System.out.println("POS: " + i + "-" + j);
                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                        }
                        //while (!Tauler.mover_pieza(ini, desti, colorContrari));
                        if (!mover(ini, desti, colorContrari)) {
                            return evaluar();
                        }
                        int aux = minimax(depth - 1, true);
                        if (aux < min) {
                            min = aux;
                        }
                        while (!desferMoviment(ini, desti, auxPeca, colorContrari));
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }

    private boolean desferMoviment(Posicion ini, Posicion desti, String peca, int color) {
        Peca aux = pecesTau[desti.x][desti.y];
        Tauler.destrueix_peça(desti);
        Tauler.crear_peça(ini,color,aux.getTipus());

        if(!peca.equals(define.PECA_NULA)) {
            Tauler.crear_peça(desti, color ^ 1, peca);
        }
        return true;
    }

    private int evaluar() {
        int total = 0;

        //System.out.println("\n*** 1.1 ***");
        int escac_mat1 = Tauler.escac_i_mat(colorContrari);
        //System.out.println("*** 1.2 ***");
        if (escac_mat1 == 1) {
            total += 3000;
        }
        else if (escac_mat1 == 0) {
            total += 1000;
        }
        else if (escac_mat1 == 2){
            total -= 1000;
        }

        int escac_mat2 = Tauler.escac_i_mat(colorJugador);
        if (escac_mat2 == 1) {
            total -= 3000;
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

    private int puntuacioPeca(String tipus) {
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

    private boolean mover(Posicion ini, Posicion desti, int color) {
        int colorAdversari = color ^ 1;
        if(pecesTau[desti.x][desti.y].getTipus() == define.REI) {
            return false;
        }
        else {
            while (!Tauler.mover_pieza(ini, desti, color));
        }
        return true;
    }
}