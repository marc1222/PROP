package domini;

import java.util.ArrayList;

public class Cavall extends Peca {

    public Cavall () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /**
     * Crea una instància de cavall amb el color passat per paràmetre i el següent Id (pot no ser únic)
     * Pre: color blanc o negre (0 o 1)
     * @param color color del cavall creat
     */
    public Cavall (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }
    public Cavall (int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public Cavall (int id, int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Posicion[] movimientos_posibles(Posicion pos) {
        // i + 1, j + 2;  i + 1, j - 2;  i + 2, j + 1;  i + 2, j - 1;
        // i - 1, j + 2;  i - 1, i - 2;  i - 2, j + 1;  i - 2, j - 1;
        Posicion res[] = {new Posicion(pos.x - 2, pos.y - 1), new Posicion(pos.x - 2, pos.y + 1),
                new Posicion(pos.x - 1, pos.y - 2), new Posicion(pos.x - 1, pos.y + 2),
                new Posicion(pos.x + 1, pos.y - 2), new Posicion(pos.x + 1, pos.y + 2),
                new Posicion(pos.x + 2, pos.y - 1), new Posicion(pos.x + 2, pos.y + 1)}; //i,j => x,y no arreglat
        return res;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        if (fin.x == ini.x + 1 || fin.x == ini.x - 1) {
            return (fin.y == ini.y + 2 || fin.y == ini.y - 2);
        }
        else if (fin.x == ini.x + 2 || fin.x == ini.x - 2) {
            return (fin.y == ini.y + 1 || fin.y == ini.y - 1);
        }
        return false;
    }

    public String getTipus() {
        return define.CAVALL;
    }
}
