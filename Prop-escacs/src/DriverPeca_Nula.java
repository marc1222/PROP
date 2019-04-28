import java.util.Scanner;

public class DriverPeca_Nula {
    public void testConstructor() {
        System.out.println("Constructora");
        Peca_Nula pb = new Peca_Nula (define.WHITE);
        System.out.println(String.valueOf(pb.id) + " " + String.valueOf(pb.color)); //0 1
        Peca_Nula pn = new Peca_Nula (define.BLACK);
        System.out.println(String.valueOf(pn.id) + " " + String.valueOf(pn.color)); //1 2
    }

    public void testMovimientos_posibles() {
        System.out.println("Movimientos_posibles");
        Peca_Nula p = new Peca_Nula ();
        Posicion pos[] = p.movimientos_posibles(new Posicion(0, 0));
        if (pos != null) {
            for (int i = 0; i < pos.length; ++i) {
                System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
            }
        }
    }

    public void testRango() {
        System.out.println("Rango");
        Peca_Nula p = new Peca_Nula ();
        System.out.println(String.valueOf(p.rango(new Posicion(0, 0), new Posicion(0, 0)))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Peca_Nula p = new Peca_Nula();
        System.out.println(p.getTipus()); //Peca_Nula
    }

    public static void main(String[] args) {
        DriverPeca_Nula dp = new DriverPeca_Nula();
        //dp.testConstructor();
        //dp.testMovimientos_validos();
        //dp.testRango();
        //dp.testGetTipus();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        while (val != 5) {
            System.out.println("Tria el mètode que vols provar");
            System.out.println("1 - Peca_Nula(blanc), Peca_Nula(negre)");
            System.out.println("2 - movimientos_posibles(pos)");
            System.out.println("3 - rango(pos_ini, pos_fin)");
            System.out.println("4 - getTipus()");
            System.out.println("5 - Sortir");
            val = sc.nextInt();
            switch (val) {
                case 1:
                    dp.testConstructor();
                    break;
                case 2:
                    dp.testMovimientos_posibles();
                    break;
                case 3:
                    dp.testRango();
                    break;
                case 4:
                    dp.testGetTipus();
                    break;
                default:
                    break;
            }
        }
    }
}
