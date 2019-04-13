public class Peca_Nula extends Peca {

    public Peca_Nula() {
        id = getNextId();
        //color = -1;
        color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }

    public Peca_Nula(int color) {
        this.id = getNextId();
        this.color = define.NULL_COLOR;
        amenacades = null;
        amenaces = null;
    }



    /*public int[][] movimientos_posibles(int posI, int posJ) {
        int res[][] = new int[0][0];
        return res;
    }*/

    public Posicion[] movimientos_posibles(Posicion pos) {
        return null;
    }

    /*public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return false;
    }*/

    public boolean rango(Posicion ini, Posicion fin) {
        return false;
    }

    public String getTipus() {
        return define.PECA_NULA;
    }
}
