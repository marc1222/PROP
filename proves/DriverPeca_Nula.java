public class DriverPeca_Nula {
    public void testConstructor() {
        System.out.println("Constructora");
        Peca_Nula pb = new Peca_Nula ();
        System.out.println(String.valueOf(pb.color) + " " + String.valueOf(pb.id)); //-1 -1
        Peca_Nula pn = new Peca_Nula ();
        System.out.println(String.valueOf(pn.color) + " " + String.valueOf(pn.id)); //-1 -1
    }

    public void testMovimientos_validos() {
        System.out.println("Movimientos_validos");
        Peca_Nula p = new Peca_Nula (define.WHITE);
        Posicion pos[] = p.movimientos_validos(new Posicion(3, 3));
        if (pos != null){
            for (int i = 0; i < pos.length; ++i) {
                System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y)); //no output
            }
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Peca_Nula p = new Peca_Nula (define.WHITE);
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 4)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(3, 4)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 3)))); //false
        System.out.println(String.valueOf(p.rango(new Posicion(3,3), new Posicion(4, 5)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Peca_Nula p = new Peca_Nula(define.WHITE);
        System.out.println(p.getTipus()); //Peca_Nula
    }

    public static void main(String[] args) {
        DriverPeca_Nula dp = new DriverPeca_Nula();
        dp.testConstructor();
        dp.testMovimientos_validos();
        dp.testRango();
        dp.testGetTipus();
    }
}
