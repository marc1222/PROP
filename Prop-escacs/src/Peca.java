public abstract class Peca {
    protected int id;
    protected int color;
    protected Posicion[] amenacades; // quines posicions amenacço
    protected Posicion[] amenaces;   // quines posicions m'estan amenaçant

    protected static int index = 0;

    /** Retorna el següent Id (pot no ser únic)
     */
    protected static int getNextId() {
        return ++index;
    }

    /** Retorna un array de les posicions on es pot moure la peça paràmetre implícit(poden ser no vàlides: fora
     *  del tauler, ocupades o amb camí obstruït), en el cas de peça nula retorna null
     */
    abstract public Posicion[] movimientos_posibles(Posicion pos); //variable size?

    /** Rep una posició inicial i una posició destí del desplaçament de la peça
     *  Retorna true si el moviment està dins del rang de la peça paràmetre implícit o false altrament
     * (el moviment pot no ser vàlid), en el cas de peça nula retorna false
     */
    abstract public boolean rango(Posicion ini, Posicion fin);

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

    /** Retorna el define del tipus de peça del paràmetre implícit
     */
    abstract public String getTipus();
}
