//import com.sun.java.swing.plaf.windows.TMSchema;
import

import java.util.ArrayList;

public class Naive extends Maquina {
    char tauler[][] = new char[8][8];
    static int depth = 4;
    static int colorJugador;
    static int importanciaMobilitat = 10;

    public Naive(int color) {
        colorJugador = color;
    }

    /* Minimax */
    public boolean mejorMovimiento(Taulell tau, int depth) {
        //hay que hacer deep copy de Taullel, solo se ha de copiar matriz
        // se da por hecho que se quiere maximizar colorJugador, colorJugador = colorActual
        Posicion mejorIni, mejorDesti;
        int actual, max, colorActual;
        max = Integer.MIN_VALUE;
        colorActual = colorJugador;

        //ArrayList<Posicion> moviments = new ArrayList<Posicion>();

        for (int i = 0; i < tau.length; i++) {
            for (int j = 0; j < tau[0].length; j++) {
                if (tau[i][j].getColor() == colorJugador) {
                    //moviments.addAll(tau.todos_movimientos(i, j));
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : tau.todos_movimientos(ini)) {
                        tau.mover_pieza(ini, desti, colorJugador);
                        colorActual ^= 1;
                        actual = minimax(tau, colorActual, depth - 1);
                        tau.mover_pieza(desti, ini, colorJugador);
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
        tau.mover_pieza(mejorIni, mejorDesti, colorJugador);
        return true;
    }

    private static int minimax(Taulell tau, int depth, int colorActual) {
        //or si es mate del algun jugador
        if (depth == 0) {
            return evaluar(tau);
        }
        int min, max, actual;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        boolean maximitzar = (colorActual == colorJugador);
        colorActual ^= 1;

        for (int i = 0; i < tau.length; i++) {
            for (int j = 0; j < tau[0].length; j++) {
                if (maximitzar && (tau[i][j].getColor() == colorJugador)) {
                    //moviments.addAll(tau.todos_movimientos(i, j));
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : tau.todos_movimientos(ini)) {
                        tau.mover_pieza(ini, desti, colorJugador);
                        actual = minimax(tau, colorActual, depth - 1);
                        if (actual > max) max = actual;
                        tau.mover_pieza(desti, ini, colorJugador);
                    }
                }
                else if (!maximitzar && (tau[i][j].getColor() == (colorJugador ^ 1))) {
                    Posicion ini = new Posicion(i, j);
                    for (Posicion desti : tau.todos_movimientos(ini)) {
                        tau.mover_pieza(ini, desti, colorJugador);
                        actual = minimax(tau, colorActual, depth - 1);
                        if (actual < min) min = actual;
                        tau.mover_pieza(desti, ini, colorJugador);
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }

    private static int evaluar(Taulell tau) {
        int captura, mobilitatPropia, mobilitatOponent, total, puntPropia, puntOponent;
        captura = mobilitatPropia = mobilitatOponent = total = puntPropia = puntOponent = 0;
        for (int i = 0; i < tau.length; i++) {
            for (int j = 0; j < tau[0].length; j++) {
                if (tau[i][j].getColor() == colorJugador) {
                    mobilitatPropia++;
                    puntPropia += puntuacioPeca(tau[i][j].getTipus());
                }
                else if (tau[i][j].getColor() == (colorJugador ^ 1)) {
                    mobilitatOponent++;
                    puntOponent += puntuacioPeca(tau[i][j].getTipus());
                }
            }
        }
        total = (int)(10*(mobilitatPropia-mobilitatOponent) + (puntPropia-puntOponent));
        return total;
    }

    private static int puntuacioPeca(String tipus) {
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