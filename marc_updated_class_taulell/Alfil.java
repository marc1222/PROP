public class Alfil extends Peca {
    //int id;

    Alfil () {
        id = getNextId();
        color = -1;
        amenacades = null;
        amenaces = null;
    }

    /*Alfil (int id) {
        this.id = id;
        color = -1;
        amenacades = null;
        amenaces = null;
    }*/

    public Alfil (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    Alfil (int id, int color, Posicion[] amenacades, Posicion[] amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    /*public int[][] movimientos_posibles(int posI, int posJ) {
        // i + x, j + x; x in (-7,7)
        int res[][] = new int[28][2];
        for (int i = -7; i < 0; ++i) {
            res[i + 7][0] = posI + i;
            res[i + 7][1] = posJ + i;
            res[i + 21][0] = posI + i;
            res[i + 21][1] = posJ - i;
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6][0] = posI + i;
            res[i + 6][1] = posJ + i;
            res[i + 20][0] = posI + i;
            res[i + 20][1] = posJ - i;
        }
        return res;
    }*/

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

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return (Math.abs(finI - iniI) == Math.abs(finJ - iniJ));
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        return (Math.abs(fin.x - ini.x) == Math.abs(fin.y - ini.y));
    }

    public String getTipus() {
        return define.ALFIL;
    }
}
