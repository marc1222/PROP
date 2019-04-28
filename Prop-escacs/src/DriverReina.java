import java.util.Scanner;

public class DriverReina {
    public void testConstructor() {
        System.out.println("Constructora");
        Reina pb = new Reina (define.WHITE);
        System.out.println(String.valueOf(pb.id) + " " + String.valueOf(pb.color)); //0 1
        Reina pn = new Reina (define.BLACK);
        System.out.println(String.valueOf(pn.id) + " " + String.valueOf(pn.color)); //1 2
    }

    public void testMovimientos_posibles(Posicion pos_act) {
        System.out.println("Movimientos_posibles");
        Reina p = new Reina ();
        Posicion pos[] = p.movimientos_posibles(pos_act);
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y));
        }
    }

    public void testRango(Posicion pos_ini, Posicion pos_fin) {
        System.out.println("Rango");
        Reina p = new Reina ();
        System.out.println(String.valueOf(p.rango(pos_ini, pos_fin))); //false
    }

    public void testGetTipus() {
        System.out.println("Get Tipus");
        Reina p = new Reina();
        System.out.println(p.getTipus()); //Reina
    }

    public static void main(String[] args) {
        DriverReina dp = new DriverReina();
        //dp.testConstructor();
        //dp.testMovimientos_validos();
        //dp.testRango();
        //dp.testGetTipus();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        while (val != 5) {
            System.out.println("Tria el mètode que vols provar");
            System.out.println("1 - Reina(blanc), Reina(negre)");
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
                    System.out.println("Introdueix la posició X Y");
                    Posicion pos = new Posicion(sc.nextInt(), sc.nextInt());
                    dp.testMovimientos_posibles(pos);
                    break;
                case 3:
                    System.out.println("Introdueix posició inicial X Y i la posició final X Y");
                    Posicion pos_ini = new Posicion(sc.nextInt(), sc.nextInt());
                    Posicion pos_fin = new Posicion(sc.nextInt(), sc.nextInt());
                    dp.testRango(pos_ini, pos_fin);
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
