public class Peca_Nula extends Peca {

    Peca_Nula() {
        id = -1;
        color = -1;
    }

    Peca_Nula(int id) {
        this.id = id;
        color = -1;
    }

    public int[][] movimientos_validos(int posI, int posJ) {
        int res[][] = new int[0][0];
        return res;
    }

    public boolean rango(int iniI, int iniJ, int finI, int finJ) {
        return false;
    }
}
