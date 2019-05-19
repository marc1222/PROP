package domini;
public class ControladorDominiJugador {
    private Jugador White;
    private Jugador Black;
    ControladorDominiJugador() {
        //FALTA
    }
    Jugador getWhite() {
        return this.White;
    }

    Jugador getBlack() {
        return this.Black;
    }

    /**
     * pre: color == define.WHITE || color == define.BALCK
     * @param color - color del jugador
     * @return tipus del jugador en concret
     */
    int getJugadorTipus(int color) {
        if (color == define.WHITE) {
            return this.White.getTipus();
        }
        else { // (color == define.BLACK)
            return this.Black.getTipus();
        }
    }
}
