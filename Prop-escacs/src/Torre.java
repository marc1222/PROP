public class Torre extends Peca {
    int id;

    Torre () {
        id = -1;
    }

    Torre (int id) {
        this.id = id;
    }

    public void movimientos_validos() {
        // i + (-7, 7)
        // j + (-7, 7)
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }
}

