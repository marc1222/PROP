public class Rei extends Peca {
    //int id;

    Rei () {
        id = -1;
        color = '\0';
    }

    Rei (int id) {
        this.id = id;
        color = '\0';
    }

    Rei (int id, char color) {
        this.id = id;
        this.color = color;
    }

    public int[][] movimientos_validos(int posI, int posJ) {
        // i + (-1, 1), j + (-1, 1)
        int res[][] = {{posI - 1, posJ - 1}, {posI - 1, posJ}, {posI - 1, posJ + 1},
                       {posI, posJ - 1}, {posI, posJ + 1},
                       {posI + 1, posJ - 1}, {posI + 1, posJ}, {posI + 1, posJ + 1}};
        return res;

    }

    public void validar_movimiento() {
        // if not occupied by same color
        // if not out of bounds
    }

    public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return ((finI <= iniI + 1) && (finI >= iniI - 1) && (finJ <= iniJ + 1) && (finJ >= iniJ - 1));
    }
}
