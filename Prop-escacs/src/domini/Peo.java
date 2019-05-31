package domini;

import java.util.ArrayList;

public class Peo extends Peca {
    boolean primer_mov;

    public Peo () {
        id = getNextId();
        color = -1;
        primer_mov = false;
        amenacades = null;
        amenaces = null;
    }
    /**
     * Crea una instància de peó amb el color passat per paràmetre i el següent Id (pot no ser únic)
     * Pre: color blanc o negre (0 o 1)
     * @param color color del peó creat
     */
    public Peo (int color) {
        this.id = getNextId();
        this.color = color;
        primer_mov = false;
        amenacades = null;
        amenaces = null;
    }

    public Peo (int color, boolean primer_mov) {
        this.id = getNextId();
        this.color = color;
        this.primer_mov = primer_mov;
        amenacades = null;
        amenaces = null;
    }

    public Peo (int id, int color, boolean primer_mov, ArrayList<Posicion> amenacades,ArrayList<Posicion> amenaces) {
        this.id = id;
        this.color = color;
        this.primer_mov = primer_mov;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }

    public Peo (int color, ArrayList<Posicion> amenacades,ArrayList<Posicion> amenaces) {
        this.id = getNextId();
        this.color = color;
        this.primer_mov = false;
        this.amenacades = amenacades;
        this.amenaces = amenaces;
    }
    public void setPeoPrimer(boolean a) {
        this.primer_mov = a;
    }

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
