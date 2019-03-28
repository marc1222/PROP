public class Reina extends Peca {
    //int id;

    Reina () {
        id = -1;
        color = -1;
    }

    Reina (int id) {
        this.id = id;
        color = -1;
    }

    Reina (int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int[][] movimientos_validos(int posI, int posJ) {
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
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return (((finI - iniI) == 0) || ((finJ - iniJ) == 0) || (Math.abs(finI - iniI) == Math.abs(finJ - iniJ)));
    }
}
