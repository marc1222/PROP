
/**
 * Esta clase da movientos en la partida de ajedrez, etc
 * @author Marian Dumitru Danci
 */
public class Naive extends Maquina {
    private Taulell Tauler;
    private static int profunditat = 4;
    private int colorJugador;

    public Naive(Taulell Tauler, int color) {
        this.Tauler = Tauler;
        colorJugador = color;
    }

    public void moviment(Posicion inici, Posicion fi) {
        Peca pecesTau[][] = Tauler.getTauler();
        //hay que hacer deep copy de Taullel, solo se ha de copiar matriz
        // se da por hecho que se quiere maximizar colorJugador, colorJugador = colorActual
        Posicion mejorIni, mejorDesti;
        int actual, max, colorActual;
        max = Integer.MIN_VALUE;
        colorActual = colorJugador;

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        Tauler.mover_pieza(ini, desti, colorJugador);
                        colorActual ^= 1;
                        actual = minimax(colorActual, profunditat - 1);
                        Tauler.mover_pieza(desti, ini, colorJugador);
                        if (actual > max) {
                            mejorIni = ini;
                            mejorDesti = desti;
                            max = actual;
                        }
                    }
                }
            }
        }
        //caso de que se pierde seguro hacer movimiento random
        inici = mejorIni;
        fi = mejorDesti;
    }

    //
    private int minimax(int depth, int colorActual) {
        //or si es mate del algun jugador
        if (depth == 0) {
            return evaluar();
        }
        int min, max, actual;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        boolean maximitzar = (colorActual == colorJugador);
        colorActual ^= 1;
        Peca pecesTau[][] = Tauler.getTauler();

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (maximitzar && (pecesTau[i][j].getColor() == colorJugador)) {
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        Tauler.mover_pieza(ini, desti, colorJugador);
                        actual = minimax(colorActual, depth - 1);
                        if (actual > max) max = actual;
                        Tauler.mover_pieza(desti, ini, colorJugador);
                    }
                }
                else if (!maximitzar && (pecesTau[i][j].getColor() == (colorJugador ^ 1))) {
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        Tauler.mover_pieza(ini, desti, colorJugador);
                        actual = minimax(colorActual, depth - 1);
                        if (actual < min) min = actual;
                        Tauler.mover_pieza(desti, ini, colorJugador);
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }

    //
    private int evaluar() {
        int amenazaPropia, amenazaOponent, mobilitatPropia, mobilitatOponent,
                puntPropia, puntOponent;
        amenazaPropia = amenazaOponent = mobilitatPropia = mobilitatOponent =
                puntPropia = puntOponent = 0;

        Peca pecesTau[][] = Tauler.getTauler();
        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    mobilitatPropia++;
                    puntPropia += puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        if(pecesTau[desti.x][desti.y].getColor() != colorJugador) {
                            amenazaPropia++;
                        }
                    }
                }
                else if (pecesTau[i][j].getColor() == (colorJugador ^ 1)) {
                    mobilitatOponent++;
                    puntOponent += puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : Tauler.todos_movimientos(ini)) {
                        if(pecesTau[desti.x][desti.y].getColor() == colorJugador) {
                            amenazaOponent++;
                        }
                    }
                }
            }
        }
        return ((mobilitatPropia-mobilitatOponent) + (puntPropia-puntOponent) + (amenazaPropia-amenazaOponent));
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