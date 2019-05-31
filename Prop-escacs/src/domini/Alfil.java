package domini;

import java.util.ArrayList;

public class Alfil extends Peca {

    public Alfil () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /**
     * Crea una instància d'alfil amb el color passat per paràmetre i el següent Id (pot no ser únic)
     * Pre: color blanc o negre (0 o 1)
     * @param color color del alfil creat
     */
    public Alfil (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Alfil (int id, int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public Alfil (int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Posicion[] movimientos_posibles(Posicion pos) {
        // i + x, j + x; x in (-7,7)
        Posicion res[] = new Posicion[28];
        for (int i = -7; i < 0; ++i) {
            res[i + 7] = new Posicion(pos.x + i, pos.y + i); //i,j => x,y mo arreglat
            res[i + 21] = new Posicion(pos.x + i, pos.y - i);
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6] = new Posicion(pos.x + i, pos.y + i);
            res[i + 20] = new Posicion(pos.x + i, pos.y - i);
        }
        return res;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        return (Math.abs(fin.x - ini.x) == Math.abs(fin.y - ini.y));
    }

    public String getTipus() {
        return define.ALFIL;
    }
}
