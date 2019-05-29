package domini;
import java.util.ArrayList;

public class Reina extends Peca {

    public Reina () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /**
     * Crea una instància de reina amb el color passat per paràmetre i el següent Id (pot no ser únic)
     * Pre: color blanc o negre (0 o 1)
     * @param color color de la reina creada
     */
    public Reina (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Reina (int id, int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public Reina (int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Posicion[] movimientos_posibles(Posicion pos) { //i,j => x,y no arreglat
        // i + (-7, 7)
        // j + (-7, 7)
        // i + x, j + x; x in (-7, 7);
        Posicion res[] = new Posicion[56];
        for (int i = -7; i < 0; ++i) {
            res[i + 7] = new Posicion(pos.x + i, pos.y + i);
            res[i + 21] = new Posicion(pos.x + i, pos.y - i);
            res[i + 35] = new Posicion(pos.x + i, pos.y);
            res[i + 49] = new Posicion(pos.x, pos.y + i);
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6] = new Posicion(pos.x + i, pos.y + i);
            res[i + 20] = new Posicion(pos.x + i, pos.y - i);
            res[i + 34] = new Posicion(pos.x + i, pos.y);
            res[i + 48] = new Posicion(pos.x, pos.y + i);
        }
        return res;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        return (((fin.x - ini.x) == 0) || ((fin.y - ini.y) == 0) || (Math.abs(fin.x - ini.x) == Math.abs(fin.y - ini.y)));
    }

    public String getTipus() {
        return define.REINA;
    }
}
