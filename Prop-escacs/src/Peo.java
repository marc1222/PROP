public class Peo extends Peca {
    boolean primer_mov;

    Peo () {
        id = -1;
        color = -1;
        primer_mov = false;
    }

    Peo (int id) {
        this.id = id;
        color = -1;
        primer_mov = false;
    }

    Peo (int id, int color, boolean primer_mov) {
        this.id = id;
        this.color = color;
        this.primer_mov = primer_mov;
    }

    public int[][] movimientos_validos(int posI, int posJ) {
        // i - 1 negras;; i + 1 blancas
        // i - 2 if first_move; i + 2
        // i + 1, j + 1 if i + 1, j + 1 ocuppied and color != own_color; i - 1, j + 1
        // i + 1, j - 1; i - 1; j - 1    "                "               "
        int res[][] = new int[4][2];
        if (color == 'B') {
            res[0][0] = posI + 1;
            res[0][1] = posJ;
            if (primer_mov) {
                res[1][0] = posI + 2;
                res[1][1] = posJ;
            }
            else {
                res[1][0] = -1;
                res[1][1] = -1;
            }
            res[2][0] = posI + 1;
            res[2][1] = posJ - 1;
            res[3][0] = posI + 1;
            res[3][1] = posJ + 1;
        }
        else if (color == 'N') {
            res[0][0] = posI - 1;
            res[0][1] = posJ;
            if (primer_mov) {
                res[1][0] = posI - 2;
                res[1][1] = posJ;
            } else {
                res[1][0] = -1;
                res[1][1] = -1;
            }
            res[2][0] = posI - 1;
            res[2][1] = posJ - 1;
            res[3][0] = posI - 1;
            res[3][1] = posJ + 1;
        }
        return res;
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        boolean rangj = finJ == iniJ + 1 || finJ == iniJ - 1 || finJ == iniJ;
        if (color == 'B') {
            if (finI == iniI + 1) {
                return rangj;
            }
            if (primer_mov) {
                if (finI == iniI + 2) {
                    return (finJ == iniJ);
                }
            }
            return false;
        }
        else if (color == 'N') {
            if (finI == iniI - 1) {
                return rangj;
            }
            if (primer_mov) {
                if (finI == iniI - 2) {
                    return (finJ == iniJ);
                }
            }
            return false;
        }
        return false;
    }
}
