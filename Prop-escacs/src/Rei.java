public class Rei extends Peca {
    int id;

    Rei () {
        id = -1;
    }

    Rei (int id) {
        this.id = id;
    }

    public void movimientos_validos() {
        // i + (-1, 1), j + (-1, 1)
    }

    public void validar_movimiento() {
        // if not occupied by same color
        // if not out of bounds
    }
}
