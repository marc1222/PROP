public class DriverTorre {
    public void testConstructor() {
        System.out.println("Constructora");
        Torre pb = new Torre (define.WHITE);
        System.out.println(String.valueOf(pb.color) + " " + String.valueOf(pb.id)); //0 1
        Torre pn = new Torre (define.BLACK);
        System.out.println(String.valueOf(pn.color) + " " + String.valueOf(pn.id)); //1 2
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Torre p = new Torre (define.WHITE);
        Posicion pos[] = p.movimientos_validos(new Posicion(3, 3));
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Torre p = new Torre (define.WHITE);
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 4)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(3, 4)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 3)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 5)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Torre p = new Torre(define.WHITE);
        System.out.println(p.getTipus()); //Torre
    }

    public static void main(String[] args) {
        DriverTorre dp = new DriverTorre();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
