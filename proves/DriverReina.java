public class DriverReina {
    public void testConstructor() {
        System.out.println("Constructora");
        Reina pb = new Reina (define.WHITE);
        System.out.println(String.valueOf(pb.color) + " " + String.valueOf(pb.id)); //0 1
        Reina pn = new Reina (define.BLACK);
        System.out.println(String.valueOf(pn.color) + " " + String.valueOf(pn.id)); //1 2
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Reina p = new Reina (define.WHITE);
        Posicion pos[] = p.movimientos_validos(new Posicion(4, 4));
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Reina p = new Reina (define.WHITE);
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 4)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(3, 4)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 3)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(2, 3)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(3, 2)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 5)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Reina p = new Reina(define.WHITE);
        System.out.println(p.getTipus()); //Reina
    }

    public static void main(String[] args) {
        DriverReina dp = new DriverReina();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
