public class DriverPeo {
    public void testConstructor() {
        System.out.println("Constructora");
        Peo pb = new Peo (define.WHITE, true);
        System.out.println(String.valueOf(pb.color) + String.valueOf(pb.primer_mov)); //0true
        Peo pn = new Peo (define.BLACK, false);
        System.out.println(String.valueOf(pn.color) + String.valueOf(pn.primer_mov)); //1false
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Peo p = new Peo (define.WHITE, true);
        Posicion pos[] = p.movimientos_validos(new Posicion(0, 1));
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y)); //0 2, 0 3, -1 2, 1 2
        }
        System.out.println("Peo negre");
        Peo pn = new Peo (define.BLACK, false);
        Posicion posn[] = pn.movimientos_validos(new Posicion(7, 6));
        for (int i = 0; i < posn.length; ++i) {
            System.out.println(String.valueOf(posn[i].x) + " " + String.valueOf(posn[i].y)); //7 5, -1 -1, 6 5, 8 5
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Peo p = new Peo (define.WHITE, true);
        System.out.println(String.valueOf(p.rango(new Posicion(0,1), new Posicion(0, 3)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(0,1), new Posicion(0, 0)))); //false
        System.out.println("Peo negre");
        Peo pn = new Peo (define.BLACK, false);
        System.out.println(String.valueOf(pn.rango(new Posicion(7, 6), new Posicion(7,4)))); //false
        System.out.println(String.valueOf(pn.rango(new Posicion(7, 6), new Posicion(8,6)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Peo p = new Peo(define.WHITE, true);
        System.out.println(p.getTipus()); //Peo
        Peo pn = new Peo(define.BLACK, false);
        System.out.println(pn.getTipus()); //Peo
    }

    public static void main(String[] args) {
        DriverPeo dp = new DriverPeo();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
