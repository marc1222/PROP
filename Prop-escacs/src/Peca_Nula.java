public class Peca_Nula extends Peca {

    /** Crea una instància de peça nula amb el color null i el següent Id (pot no ser únic)
     */
    public Peca_Nula() {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    public Peca_Nula(int color) {
        this.id = getNextId();
        this.color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    public Posicion[] movimientos_posibles(Posicion pos) {
        return null;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        return false;
    }

    public String getTipus() {
        return define.PECA_NULA;
    }
}
