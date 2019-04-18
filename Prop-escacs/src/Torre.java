public class Torre extends Peca {

    public Torre () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /** Crea una instància de torre amb el color passat per paràmetre i el següent Id (pot no ser únic)
     */
    public Torre (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Torre (int id, int color, Posicion[] amenacades, Posicion[] amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Posicion[] movimientos_posibles(Posicion pos) { //i,j => x,y no arreglat
        // i + (-7, 7)
        // j + (-7, 7)
        Posicion res[] = new Posicion[28];
        for (int i = -7; i < 0; ++i) {
            res[i + 7] = new Posicion(pos.x + i, pos.y);
            res[i + 21] = new Posicion(pos.x, pos.y + i);
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6] = new Posicion(pos.x + i, pos.y);
            res[i + 20] = new Posicion(pos.x, pos.y + i);
        }
        return res;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        return (fin.x - ini.x == 0 || fin.y - ini.y == 0);
    }

    public String getTipus() {
        return define.TORRE;
    }
}

