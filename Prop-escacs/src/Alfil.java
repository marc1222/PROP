public class Alfil extends Peca {
    int id;

    Alfil () {
        id = -1;
    }

    Alfil (int id) {
        this.id = id;
    }

    public void movimientos_validos() {
        // i + x, j + x; x in (-7,7)
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }
}
