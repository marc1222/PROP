import java.util.Scanner;

public class DriverPeo {
    public void testConstructor() {
        System.out.println("Constructora");
        Peo pb = new Peo (define.WHITE);
        System.out.println(String.valueOf(pb.id) + " " + String.valueOf(pb.color) + " " + String.valueOf(pb.primer_mov)); //0 true
        Peo pn = new Peo (define.BLACK, false);
        System.out.println(String.valueOf(pn.id) + " " + String.valueOf(pn.color) + " " + String.valueOf(pn.primer_mov)); //1 false
        Peo pp = new Peo (define.WHITE, true);
        System.out.println(String.valueOf(pp.id) + " " + String.valueOf(pp.color) + " " + String.valueOf(pp.primer_mov));
    }

    public void testSetPeoPrimer(boolean b) {
        System.out.println("SetPeoPrimer");
        Peo p = new Peo (define.WHITE);
        p.setPeoPrimer(b);
        System.out.println(String.valueOf(p.primer_mov));
    }

    public void testMovimientos_posibles(Posicion pos_act, int color, boolean primer) {
        System.out.println("Movimientos_posibles");
        Peo p = new Peo (color, primer);
        Posicion pos[] = p.movimientos_posibles(pos_act);
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(String.valueOf(pos[i].x) + " " + String.valueOf(pos[i].y)); //0 2, 0 3, -1 2, 1 2
        }
    }

    public void testRango(Posicion pos_ini, Posicion pos_fin, int color, boolean primer) {
        System.out.println("Rango");
        Peo p = new Peo (color, primer);
        System.out.println(String.valueOf(p.rango(pos_ini, pos_fin)));
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
        //dp.testConstructor();
        //dp.testMovimientos_validos();
        //dp.testRango();
        //dp.testGetTipus();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        while (val != 6) {
            System.out.println("Tria el mètode que vols provar");
            System.out.println("1 - Peo(blanc), Peo(negre, false), Peo(blanc, true)");
            System.out.println("2 - setPeoPrimer(boolean)");
            System.out.println("3 - movimientos_posibles(pos) [color, primer]");
            System.out.println("4 - rango(pos_ini, pos_fin) [color, primer]");
            System.out.println("5 - getTipus()");
            System.out.println("6 - Sortir");
            val = sc.nextInt();
            switch (val) {
                case 1:
                    dp.testConstructor();
                    break;
                case 2:
                    System.out.println("Introdueix si és el primer moviment del peo");
                    boolean b = sc.nextBoolean();
                    dp.testSetPeoPrimer(b);
                    break;
                case 3:
                    System.out.println("Introdueix la posició X Y, el color (0 blanc) i si pot fer el 1er moviment");
                    Posicion pos = new Posicion(sc.nextInt(), sc.nextInt());
                    int col = sc.nextInt();
                    boolean b2 = sc.nextBoolean();
                    dp.testMovimientos_posibles(pos, col, b2);
                    break;
                case 4:
                    System.out.println("Introdueix posició inicial X Y, posició final X Y, el color i si pot fer el 1er moviment");
                    Posicion pos_ini = new Posicion(sc.nextInt(), sc.nextInt());
                    Posicion pos_fin = new Posicion(sc.nextInt(), sc.nextInt());
                    int col2 = sc.nextInt();
                    boolean b3 = sc.nextBoolean();
                    dp.testRango(pos_ini, pos_fin, col2, b3);
                    break;
                case 5:
                    dp.testGetTipus();
                    break;
                default:
                    break;
            }
        }
    }

}
