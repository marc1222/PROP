public class Cavall extends Peca {
    //int id;

    Cavall () {
        id = getNextId();
        color = -1;
        amenacades = null;
        amenaces = null;
    }

    /*Cavall (int id) {
        this.id = id;
        color = -1;
        amenacades = null;
        amenaces = null;
    }*/

    public Cavall (int color) {
        this.id = getNextId();
        this.color = color;
        amenacades = null;
        amenaces = null;
    }

    Cavall (int id, int color, Posicion[] amenacades, Posicion[] amenaces) {
        this.id = id;
        this.color = color;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    /*public int[][] movimientos_posibles(int posI, int posJ) {
        // i + 1, j + 2;  i + 1, j - 2;  i + 2, j + 1;  i + 2, j - 1;
        // i - 1, j + 2;  i - 1, i - 2;  i - 2, j + 1;  i - 2, j - 1;
        int res[][] = {{posI - 2, posJ - 1}, {posI - 2, posJ + 1}, {posI - 1, posJ - 2}, {posI - 1, posJ + 2},
                       {posI + 1, posJ - 2}, {posI + 1, posJ + 2}, {posI + 2, posJ - 1}, {posI + 2, posJ + 1}};
        return res;
    }*/

    public Posicion[] movimientos_posibles(Posicion pos) {
        // i + 1, j + 2;  i + 1, j - 2;  i + 2, j + 1;  i + 2, j - 1;
        // i - 1, j + 2;  i - 1, i - 2;  i - 2, j + 1;  i - 2, j - 1;
        Posicion res[] = {new Posicion(pos.x - 2, pos.y - 1), new Posicion(pos.x - 2, pos.y + 1),
                new Posicion(pos.x - 1, pos.y - 2), new Posicion(pos.x - 1, pos.y + 2),
                new Posicion(pos.x + 1, pos.y - 2), new Posicion(pos.x + 1, pos.y + 2),
                new Posicion(pos.x + 2, pos.y - 1), new Posicion(pos.x + 2, pos.y + 1)}; //i,j => x,y no arreglat
        return res;
    }

    public void validar_movimiento() {
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        if (finI == iniI + 1 || finI == iniI - 1) {
            return (finJ == iniJ + 2 || finJ == iniJ - 2);
        }
        else if (finI == iniI + 2 || finI == iniI - 2) {
            return (finJ == iniJ + 1 || finJ == iniJ - 1);
        }
        return false;
    }*/

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
