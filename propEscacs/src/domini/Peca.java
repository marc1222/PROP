package domini;

import java.util.ArrayList;

public abstract class Peca {
    protected int id;
    protected int color;
    protected ArrayList<Posicion> amenacades; // posicions de les peces que amenaço
    protected ArrayList<Posicion> amenaces;   // posicions de les peces que m'estan amenaçant

    protected static int index = 0;

    /**
     * Assigna els Id a les peces
     * Pre: true
     * @return el següent Id (pot no ser únic)
     */
    protected static int getNextId() {
        return ++index;
    }

    /**
     * Consulta els moviments possibles d'una peça
     * Pre: pos != null
     * @param pos posició de la peça, no pot ser null però pot tenir qualsevol valor
     * @return un array de les posicions on es pot moure la peça paràmetre implícit(poden ser no vàlides: fora
     * del tauler, ocupades o amb camí obstruït), en el cas de peça nula retorna null
     */
    abstract public Posicion[] movimientos_posibles(Posicion pos); //variable size?

    /**
     * Comprova si la peça pot fer un moviment (des del punt de vista de la peça)
     * Pre: ini, fin != null
     * @param ini posició inicial de la peça (no null)
     * @param fin posició destí del moviment de la peça (no null)
     * @return true si el moviment està dins del rang de la peça paràmetre implícit (el moviment pot no ser vàlid)
     * o false altrament, en el cas de peça nula retorna false
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
    public void add_amenaca(Posicion a) {
        amenaces.add(a);
    }
    public void add_amenacada(Posicion a) {
        amenacades.add(a);
    }

    /**
     * Borra els arraysList d'amenaces i d'amenaçades de la peça
     * Pre: true
     */
    public void reset() {
        amenacades = null;
        amenacades = new ArrayList<>();
        amenaces = null;
        amenaces = new ArrayList<>();
    }

    public ArrayList<Posicion> getAmenacades() {
        return amenacades;
    }

    public ArrayList<Posicion> getAmenaces() {
        return amenaces;
    }

    /**
     * Consulta de quin dels 7 tipus és la peça
     * Pre: true
     * @return el define del tipus de peça del paràmetre implícit
     */
    abstract public String getTipus();
}
