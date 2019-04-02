public abstract class Peca {
    protected int id;
    protected int color;

    protected static int index = 0;

    protected static int getNextId() {
        return ++index;
    }

    /** retorna una matriu de ints on cada fila son les coordenades x, y de la nova posició, en el cas de peça nula
     *  retorna un array buit
     */
    abstract public Posicion[] movimientos_validos(Posicion pos); //variable size?
    //abstract public int[][] movimientos_validos(int posI, int posJ);

    /** retorna true si el moviment està dins del rang de la peça
     */
    abstract public boolean rango(Posicion ini, Posicion fin);
    //abstract public boolean rango(int iniI, int iniJ, int finI, int finJ);

    public int getId() {
        return this.id;
    }
    public int getColor() {
        return this.color;
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else ; //
    }

    public void setColor(int color) {
        if (color == define.BLACK || color == define.WHITE) this.color = color;
        else ; //
    }

    /** retorna el define del tipus de peça del paràmetre implícit
     */
    abstract public String getTipus();
}
