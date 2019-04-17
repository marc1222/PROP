public class Reina extends Peca {
    //int id;

    public Reina () {
        id = getNextId();
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    /*Reina (int id) {
        this.id = id;
        color = -1;
        amenacades = null;
        amenaces = null;
    }*/

    public Reina (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    public Reina (int id, int color, Posicion[] amenacades, Posicion[] amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    /*public int[][] movimientos_posibles(int posI, int posJ) {
        // i + (-7, 7)
        // j + (-7, 7)
        // i + x, j + x; x in (-7, 7);
        int res[][] = new int[56][2];
        for (int i = -7; i < 0; ++i) {
            res[i + 7][0] = posI + i;
            res[i + 7][1] = posJ + i;
            res[i + 21][0] = posI + i;
            res[i + 21][1] = posJ - i;
            res[i + 35][0] = posI + i;
            res[i + 35][1] = posJ;
            res[i + 49][0] = posI;
            res[i + 49][1] = posJ + i;
        }
        for (int i = 1; i < 8; ++i) {
            res[i + 6][0] = posI + i;
            res[i + 6][1] = posJ + i;
            res[i + 20][0] = posI + i;
            res[i + 20][1] = posJ - i;
            res[i + 34][0] = posI + i;
            res[i + 34][1] = posJ;
            res[i + 48][0] = posI;
            res[i + 48][1] = posJ + i;
        }
        return res;
    }*/

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

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return (((finI - iniI) == 0) || ((finJ - iniJ) == 0) || (Math.abs(finI - iniI) == Math.abs(finJ - iniJ)));
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        return (((fin.x - ini.x) == 0) || ((fin.y - ini.y) == 0) || (Math.abs(fin.x - ini.x) == Math.abs(fin.y - ini.y)));
    }

    public String getTipus() {
        return define.REINA;
    }
}
