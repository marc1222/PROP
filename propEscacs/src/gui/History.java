package gui;

import javafx.geometry.Pos;

import java.util.Stack;

public class History {

    public class Movement {
        int color;
        Posicion ini;
        Posicion fi;

        Movement(int color, Posicion ini, Posicion fi) {
            this.color = color;
            this.ini = ini;
            this.fi = fi;
        }
    }

    Stack<Movement> history;
    /**
     * Instanciamos array
     */
    History() {
        history = new Stack<>();
    }

    /**
     * Appends a new entry on correct stack
     */
    public void add(int color, Posicion ini, Posicion fi) {
        history.push(new Movement(color, ini, fi));
    }

    /**
     *  Removes (pops desired stack)
     * @param color
     * @return - return 1 on OK pop, -1 on error pop
     */
    public int remove(int color) {
        if (history.size() > 0) history.pop();
        else return -1;
        return 1;
    }

    /**
     *
     * @return - all history rregistry
     */
    public Movement[] getAll() {
        return (history.toArray(new Movement[history.size()]));
    }
}
