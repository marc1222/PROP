public abstract class Peca {
    int id;
    char color;

    /** retorna una matriu de ints on cada fila son les coordenades x, y de la nova posició
     */
    abstract public int[][] movimientos_validos(int posI, int posJ); //variable size?

    /** retorna true si el moviment està dins del rang de la peça
     */
    abstract public boolean rango(int iniI, int iniJ, int finI, int finJ);
}
