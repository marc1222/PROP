public class DriverCavall {
    public void testConstructor() {
        System.out.println("Constructora");
        Cavall pb = new Cavall (define.WHITE);
        System.out.println(String.valueOf(pb.color) + " " + String.valueOf(pb.id)); //0 1
        Cavall pn = new Cavall (define.BLACK);
        System.out.println(String.valueOf(pn.color) + " " + String.valueOf(pn.id)); //1 2
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Cavall p = new Cavall (define.WHITE);
        Posicion pos[] = p.movimientos_validos(new Posicion(3, 3));
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Cavall p = new Cavall (define.WHITE);
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 4)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(3, 4)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 3)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 5)))); //true
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Cavall p = new Cavall(define.WHITE);
        System.out.println(p.getTipus()); //Cavall
    }

    public static void main(String[] args) {
        DriverCavall dp = new DriverCavall();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
