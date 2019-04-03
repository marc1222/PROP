public class Peca_Nula extends Peca {

    Peca_Nula() {
        id = -1;
        color = -1;
        amenacades = null;
        amenaces = null;
    }

    Peca_Nula(int id) {
        this.id = id;
        color = -1;
        amenacades = null;
        amenaces = null;
    }



    /*public int[][] movimientos_validos(int posI, int posJ) {
        int res[][] = new int[0][0];
        return res;
    }*/

    public Posicion[] movimientos_validos(Posicion pos) {
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
