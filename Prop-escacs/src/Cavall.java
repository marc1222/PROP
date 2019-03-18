public class Cavall extends Peca {
    int id;

    Cavall () {
        id = -1;
    }

    Cavall (int id) {
        this.id = id;
    }

    public void movimientos_validos() {
        // i + 1, j + 2;  i + 1, j - 2;  i + 2, j + 1;  i + 2, j - 1;
        // i - 1, j + 2;  i - 1, i - 2;  i - 2, j + 1;  i - 2, j - 1;
    }

    public void validar_movimiento() {
        // if not occupied by same color
        // if not out of bounds
    }
}
