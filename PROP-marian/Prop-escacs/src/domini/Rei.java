package domini;
import java.util.ArrayList;

public class Rei extends Peca {

    public Rei () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /**
     * Crea una instància de rei amb el color passat per paràmetre i el següent Id (pot no ser únic)
     * Pre: color blanc o negre (0 o 1)
     * @param color color del rei creat
     */
    public Rei (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Rei (int id, int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public Rei (int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Posicion[] movimientos_posibles(Posicion pos) { //i,j => x,y no arreglat
        // i + (-1, 1), j + (-1, 1)
        // new posicion res[] ?
        Posicion res[] = {new Posicion(pos.x - 1, pos.y - 1), new Posicion(pos.x - 1, pos.y),
                new Posicion(pos.x - 1, pos.y + 1), new Posicion(pos.x, pos.y - 1),
                new Posicion(pos.x, pos.y + 1), new Posicion(pos.x + 1, pos.y - 1),
                new Posicion(pos.x + 1, pos.y), new Posicion(pos.x + 1, pos.y + 1)};
        return res;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        return ((fin.x <= ini.x + 1) && (fin.x >= ini.x - 1) && (fin.y <= ini.y + 1) && (fin.y >= ini.y - 1));
    }

    public String getTipus() {
        return define.REI;
    }
}
