public class Rei extends Peca {
    //int id;

    Rei () {
        id = getNextId();
        color = -1;
    }

    Rei (int id) {
        this.id = id;
        color = -1;
    }

    Rei (int id, int color) {
        this.id = id;
        this.color = color;
    }

    /*public int[][] movimientos_validos(int posI, int posJ) {
        // i + (-1, 1), j + (-1, 1)
        int res[][] = {{posI - 1, posJ - 1}, {posI - 1, posJ}, {posI - 1, posJ + 1},
                       {posI, posJ - 1}, {posI, posJ + 1},
                       {posI + 1, posJ - 1}, {posI + 1, posJ}, {posI + 1, posJ + 1}};
        return res;
    }*/

    public Posicion[] movimientos_validos(Posicion pos) {
        // i + (-1, 1), j + (-1, 1)
        // new posicion res[] ?
        Posicion res[] = {new Posicion(pos.x - 1, pos.y - 1), new Posicion(pos.x - 1, pos.y),
                new Posicion(pos.x - 1, pos.y + 1), new Posicion(pos.x, pos.y - 1),
                new Posicion(pos.x, pos.y + 1), new Posicion(pos.x + 1, pos.y - 1),
                new Posicion(pos.x + 1, pos.y), new Posicion(pos.x + 1, pos.y + 1)};
        return res;
    }

    public void validar_movimiento() {
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return ((finI <= iniI + 1) && (finI >= iniI - 1) && (finJ <= iniJ + 1) && (finJ >= iniJ - 1));
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        return ((fin.x <= ini.x + 1) && (fin.x >= ini.x - 1) && (fin.y <= ini.y + 1) && (fin.y >= ini.y - 1));
    }

    public String getTipus() {
        return define.REI;
    }
}
