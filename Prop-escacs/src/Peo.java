public class Peo extends Peca {
    boolean primer_mov;

    Peo () {
        id = getNextId();
        color = -1;
        primer_mov = false;
        amenacades = null;
        amenaces = null;
    }

    Peo (int id) {
        this.id = id;
        color = -1;
        primer_mov = false;
        amenacades = null;
        amenaces = null;
    }

    Peo (int color, boolean primer_mov) {
        this.id = getNextId();
        this.color = color;
        this.primer_mov = primer_mov;
        amenacades = null;
        amenaces = null;
    }

    Peo (int id, int color, boolean primer_mov, Posicion[] amenacades, Posicion[] amenaces) {
        this.id = id;
        this.color = color;
        this.primer_mov = primer_mov;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    /*public int[][] movimientos_posibles(int posI, int posJ) {
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
    }*/

    public Posicion[] movimientos_posibles(Posicion pos) {
        // i - 1 negras;; i + 1 blancas
        // i - 2 if first_move; i + 2
        // i + 1, j + 1 if i + 1, j + 1 ocuppied and color != own_color; i - 1, j + 1
        // i + 1, j - 1; i - 1; j - 1    "                "               "
        Posicion res[] = new Posicion[4];
        if (this.color == define.WHITE) {
            res[0] = new Posicion(pos.x, pos.y + 1);
            if (this.primer_mov) {
                res[1] = new Posicion(pos.x, pos.y + 2);
            }
            else {
                res[1] = new Posicion(-1, -1);
            }
            res[2] = new Posicion(pos.x - 1, pos.y + 1);
            res[3] = new Posicion(pos.x + 1, pos.y + 1);
        }
        else if (this.color == define.BLACK) {
            res[0] = new Posicion(pos.x, pos.y - 1);
            if (this.primer_mov) {
                res[1] = new Posicion(pos.x, pos.y - 2);
            }
            else {
                res[1] = new Posicion(-1, -1);
            }
            res[2] = new Posicion(pos.x - 1, pos.y - 1);
            res[3] = new Posicion(pos.x + 1, pos.y - 1);
        }
        return res;
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
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
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        boolean rangj = fin.x == ini.x + 1 || fin.x == ini.x - 1 || fin.x == ini.x;
        if (color == define.WHITE) {
            if (fin.y == ini.y + 1) {
                return rangj;
            }
            if (primer_mov) {
                if (fin.y == ini.y + 2) {
                    return (fin.x == ini.x);
                }
            }
            return false;
        }
        else if (color == define.BLACK) {
            if (fin.y == ini.y - 1) {
                return rangj;
            }
            if (primer_mov) {
                if (fin.y == ini.y - 2) {
                    return (fin.x == ini.x);
                }
            }
            return false;
        }
        return false;
    }

    public String getTipus() {
        return define.PEO;
    }
}
