import java.util.ArrayList;

public class Torre extends Peca {
    //int id;

    public Torre () {
        id = getNextId();
        color = -1;
        amenacades = null;
        amenaces = null;
    }

    /*Torre (int id) {
        this.id = id;
        color = -1;
        amenacades = null;
        amenaces = null;
    }*/

    public Torre (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Torre (int id, int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public Torre (int color, ArrayList<Posicion> amenacades, ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    /*public int[][] movimientos_posibles(int posI, int posJ) {
        // i + (-7, 7)
        // j + (-7, 7)
        int res[][] = new int[28][2];
        for (int i = -7; i < 0; ++i) {
            res[i + 7][0] = posI + i;
            res[i + 7][1] = posJ;
            res[i + 21][0] = posI;
            res[i + 21][1] = posJ + i;
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6][0] = posI + i;
            res[i + 6][1] = posJ;
            res[i + 20][0] = posI;
            res[i + 20][1] = posJ + i;
        }
        return res;
    }*/

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

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return (finI - iniI == 0 || finJ - iniJ == 0);
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        return (fin.x - ini.x == 0 || fin.y - ini.y == 0);
    }

    public String getTipus() {
        return define.TORRE;
    }
}

