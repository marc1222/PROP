//import com.sun.java.swing.plaf.windows.TMSchema;

public class Naive extends Maquina {
    char tauler[][] = new char[8][8];
/*
    public void minimax (int depth, boolean maximitzar, Partida par) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int actual;

        Moviment millorMoviment;
        Moviment moviments;
        moviments = par.movimentsValids();
        for (int i = 0; i < moviments.length; i++) {
            par.ferMoviment(moviments[i]);

            if(maximitzar) {
                actual = Math.max(max, max(depth-1, par));
                if(actual >= max) {
                    max = actual;
                    millorMoviment = moviments[i];
                }
            }
            else {
                actual = Math.min(min, min(depth-1, par));
                if(actual <= min) {
                    min = actual;
                    millorMoviment = moviments[i];
                }
            }
        }

    }

    private int min(int depth, Partida par) {
        if (depth == 0 || par.esMat()) {
            return avalua(par);
        }
    }

    private int max(int depth, Partida par) {
        if (depth == 0 || par.esMat()) {
            return avalua(par);
        }
    }

    private avalua(Partida par) {


    }
*/
}