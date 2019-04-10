public class DriverAlfil {
    public void testConstructor() {
        System.out.println("Constructora");
        Alfil pb = new Alfil (define.WHITE);
        System.out.println(String.valueOf(pb.color) + " " + String.valueOf(pb.id)); //0 1
        Alfil pn = new Alfil (define.BLACK);
        System.out.println(String.valueOf(pn.color) + " " + String.valueOf(pn.id)); //1 2
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Alfil p = new Alfil (define.WHITE);
        Posicion pos[] = p.movimientos_validos(new Posicion(0, 1));
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Alfil p = new Alfil (define.WHITE);
        System.out.println(String.valueOf(p.rango(new Posicion(0,1), new Posicion(0, 3)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(0,0), new Posicion(1, 0)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(5,3), new Posicion(7, 5)))); //true
        System.out.println(String.valueOf(p.rango(new Posicion(1,0), new Posicion(4, 4)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Alfil p = new Alfil(define.WHITE);
        System.out.println(p.getTipus()); //Alfil
    }

    public static void main(String[] args) {
        DriverAlfil dp = new DriverAlfil();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
