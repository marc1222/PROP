import javafx.geometry.Pos;

/**
 * Esta clase da movientos en la partida de ajedrez, etc
 * @author Marian Dumitru Danci
 */
public class Naive extends Maquina {
    private Taulell Tauler;
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
    }

    public long moviment(Posicion inici, Posicion fi) {
        System.out.println("Loading...");
        Peca pecesTau[][] = Tauler.getTauler();
        //hay que hacer deep copy de Taullel, solo se ha de copiar matriz
        // se da por hecho que se quiere maximizar colorJugador, colorJugador = colorActual
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF;
        maxF = -9999;


        /*
        Posicion x2 = new Posicion(2, 2);
        Posicion x3 = new Posicion(3, 3);
        Posicion x4 = new Posicion(4, 4);

        Tauler.crear_peça(x2, colorContrari, define.PEO);
        Tauler.crear_peça(x3, colorJugador, define.PECA_NULA);
        Tauler.crear_peça(x4, colorContrari, define.TORRE);
        */

        /*
        System.out.println("jugador "+ colorJugador + " contrari " + colorContrari);
        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                System.out.println("POS: " + i + " " + j + " TIPUS: " + pecesTau[i][j].getTipus() +
                        " COLOR: " + pecesTau[i][j].getColor());

                if (pecesTau[i][j].getColor() == colorJugador) {
                    System.out.println("0");
                }
                else if (pecesTau[i][j].getColor() == colorContrari) {
                    System.out.println("1");
                }
            }
        }
        */


        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                //System.out.println("POS: " + i + " " + j + " TIPUS: " + pecesTau[i][j].getTipus() +
                        //" COLOR: " + pecesTau[i][j].getColor());
                //if ((pecesTau[i][j].getColor() == colorJugador) && !pecesTau[i][j].getTipus().equals(define.PECA_NULA)) {


                if (pecesTau[i][j].getColor() == colorJugador) {
                    //System.out.println("TIPUS " + i + " "+ j + + pecesTau[i][j].getColor() + " " + pecesTau[i][j].getTipus());
                    Posicion ini = new Posicion(i, j);
                    Posicion[] tots =  Tauler.todos_movimientos(ini);
                    //System.out.println("TOTS MOVS: " + tots.length);
                    for (Posicion desti : tots) {
                        //System.out.println("MOVIMENT : " + ini.x + "-" + ini.y + "  " + desti.x + "-" + desti.y);
                        String auxPeca = define.PECA_NULA;
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                        }
                        while (!Tauler.mover_pieza(ini, desti, colorJugador));
                        //Tauler.mover_pieza(ini, desti, colorJugador);
                        int aux = minimax(profunditat, false);
                        /*
                        System.out.println("-------------------------------------");
                        System.out.println("ACTUAL: " + actual + "    " +
                                ini.x + "-" + ini.y + "  " + desti.x + "-" + desti.y);
                        Tauler.printTauler();
                        */
                        desferMoviment(ini, desti, auxPeca, colorJugador);
                        //Tauler.printTauler();
                        if (aux > maxF) {
                            System.out.println("**********************");
                            System.out.println("RESULTAT : " + aux + " MAX " + maxF);
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
        System.out.println("\n\nRESULTAT : " + maxF);
        //caso de que se pierde seguro hacer movimiento random
        inici.x = mejorIni.x;
        inici.y = mejorIni.y;
        fi.x = mejorDesti.x;
        fi.y = mejorDesti.y;
        //System.out.println("FI NAIVE POS: " + inici.x + " " + inici.y + "-" + fi.x + " " + fi.y);
        return 0;
    }

    private void desferMoviment(Posicion ini, Posicion desti, String peca, int color) {
        //System.out.println("DESFER " + ini.x + "-" + ini.y + "  " + desti.x + "-" + desti.y);
        //System.out.println("--------------------------------------------");
        //Tauler.printTauler();
        //while (!Tauler.mover_pieza(desti, ini, color));

        Peca pecesTau[][] = Tauler.getTauler();
        Peca aux = pecesTau[desti.x][desti.y];
        Tauler.destrueix_peça(desti);
        Tauler.crear_peça(ini,color,aux.getTipus());

        //Tauler.mover_pieza(desti, ini, color);
        if(!peca.equals(define.PECA_NULA)) {
            //System.out.println("CREA PECA " + peca);
            Tauler.crear_peça(desti, color ^ 1, peca);
        }
        //Tauler.printTauler();
    }

    private int minimax(int depth, boolean maximitzar) {
        //System.out.println("-----------------------------------------------");
        //Tauler.printTauler();
        //System.out.println("MINIMAX  "+ depth);
        //or si escac_mat del algun jugador
        if (depth == 0 || (Tauler.escac_i_mat(colorContrari) == 0) || (Tauler.escac_i_mat(colorJugador) == 0)) {
            return evaluar();
        }
        int min, max;
        max = -9999;
        min = 9999;
        Peca pecesTau[][] = Tauler.getTauler();

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {

                /*
                if(pecesTau[i][j].getTipus().equals(define.TORRE)) {
                    Posicion torre1 = new Posicion(i, j);
                    Posicion[] torre2 =  Tauler.todos_movimientos(torre1);
                    System.out.println("**************************************************");
                    System.out.println("INI : " + torre1.x + "-" + torre1.y);
                    for (Posicion torre3 : torre2) {
                        System.out.println("DESTI : " + torre3.x + "-" + torre3.y);
                    }
                }
                */
                //if (maximitzar && (pecesTau[i][j].getColor() == colorJugador) && !pecesTau[i][j].getTipus().equals(define.PECA_NULA)) {
                if (maximitzar && (pecesTau[i][j].getColor() == colorJugador)) {
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        //System.out.println("- MAX: " + ini.x + "-" + ini.y + "  " + desti.x + "-" + desti.y);
                        String auxPeca = define.PECA_NULA;
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                            //System.out.println("- JO MATO PECA -" + auxPeca + " DESTI: " + desti.x + "-" + desti.y);
                        }
                        //Tauler.mover_pieza(ini, desti, colorJugador);
                        while (!Tauler.mover_pieza(ini, desti, colorJugador));
                        int aux = minimax(depth - 1, false);
                        if (aux > max) {
                            System.out.println("ACTUAL " + aux + " MAX " + max);
                            max = aux;
                        }
                        //System.out.println("- PUNT MAX " + max + "  ACTUAL " + actual);
                        desferMoviment(ini, desti, auxPeca, colorJugador);
                    }
                }
                else if (!maximitzar && (pecesTau[i][j].getColor() == (colorContrari))) {
                    //System.out.println("MIN: " + depth);
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        //System.out.println("- MIN: " + ini.x + "-" + ini.y + "  " + desti.x + "-" + desti.y);
                        String auxPeca = define.PECA_NULA;
                        //if(pecesTau[desti.x][desti.y].getColor() != 2 && !(pecesTau[desti.x][desti.y].getTipus().equals(define.PECA_NULA))) {
                        if(pecesTau[desti.x][desti.y].getColor() != 2) {
                            auxPeca = pecesTau[desti.x][desti.y].getTipus();
                            //System.out.println("-ENEMIC MATA PECA -" + auxPeca + " DESTI: " + desti.x + "-" + desti.y);
                        }
                        //Tauler.mover_pieza(ini, desti, colorContrari);
                        while (!Tauler.mover_pieza(ini, desti, colorContrari));
                        int aux = minimax(depth - 1, true);
                        if (aux < min) {
                            System.out.println("---------------------");
                            System.out.println("ACTUAL " + aux + " MIN " + min + "\n");
                            min = aux;
                        }
                        //System.out.println("- PUNT MIN " + min + "  ACTUAL " + actual);
                        desferMoviment(ini, desti, auxPeca, colorContrari);
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }

    private int evaluar() {
        int total = 0;
        Peca pecesTau[][] = Tauler.getTauler();

        int escac_mat1 = Tauler.escac_i_mat(colorContrari);
        if (escac_mat1 == 1) {
            total += 1000;
        }
        else if (escac_mat1 == 0) {
            total += 250;
        }

        int escac_mat2 = Tauler.escac_i_mat(colorJugador);
        if (escac_mat2 == 1) {
            total -= 1000;
        }
        else if (escac_mat2 == 0) {
            total -= 250;
        }

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                //if ((pecesTau[i][j].getColor() == colorJugador) && !pecesTau[i][j].getTipus().equals(define.PECA_NULA)) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    total++;
                    total += puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        if (pecesTau[desti.x][desti.y].getColor() != colorJugador) {
                            total++;
                        }
                    }
                }
                else if (pecesTau[i][j].getColor() == (colorContrari)) {
                    total--;
                    total -= puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        if (pecesTau[desti.x][desti.y].getColor() == colorJugador) {
                            total--;
                        }
                    }
                }
            }
        }
        //System.out.println("Puntuacio: " + total);

        if (pecesTau[6][6].getTipus() == define.TORRE && pecesTau[5][3].getTipus() == define.ALFIL) {
            System.out.println("*************************************************");
            Tauler.printTauler();
            System.out.println("MAQUINA1: " + Tauler.escac_i_mat(colorContrari));
            System.out.println("ADV: " + Tauler.escac_i_mat(colorJugador));
            System.out.println("PUNTS : " + total);
        }
        System.out.println("PUNTS : " + total);
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
}