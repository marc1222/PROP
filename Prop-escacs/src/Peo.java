public class Peo extends Peca {
    int id;

    Peo () {
        id = -1;
    }

    Peo (int id) {
        this.id = id;
    }

    public void movimientos_validos() {
        // i + 1 negras;; i - 1 blancas
        // i + 2 if first_move; i - 2
        // i + 1, j + 1 if i + 1, j + 1 ocuppied and color != own_color; i - 1, j + 1
        // i + 1, j - 1; i - 1; j - 1
    }

    public void validar_movimiento() {
        // if not in the way
        // if not occupied by same color
        // if not out of bounds
    }
}
