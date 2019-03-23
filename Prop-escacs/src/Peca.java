public abstract class Peca {
    int id;
    char color;

    abstract public int[][] movimientos_validos(int posI, int posJ); //variable size?
}
