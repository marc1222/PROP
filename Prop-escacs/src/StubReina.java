public class StubReina extends Peca {
    StubReina () {
        System.out.println("Reina creada");
    }

    StubReina(int color) {
        this.color = color;
        System.out.println("Color assignat");
    }

    public Posicion[] movimientos_posibles(Posicion pos) {
        System.out.println("Movimientos posibles");
        return null;
    }

    public boolean rango(Posicion ini, Posicion fin) {
        System.out.println("Rango");
        return true;
    }

    public String getTipus() {
        System.out.println("Get tipus");
        return "StubReina";
    }
}
