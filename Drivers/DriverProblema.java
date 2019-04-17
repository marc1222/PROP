import java.util.Scanner;

public class DriverProblema {
    public void testConstructor() {
        System.out.println("Constructora");
        Problema p = new Problema();
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + String.valueOf(p.getPosIni()) + " " +
                String.valueOf(p.getDificultat())); //0 -1 -1 null -1
    }

    public void testSetters(int id, int njug, String fen, int prim) {
        System.out.println("Setters");
        Problema p = new Problema();
        p.setId(id);
        p.setNumJugades(njug);
        p.setPosIni(fen);
        p.setPrimer(prim);
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //444 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public void testCrearProblema(int njug, String fen) {
        System.out.println("Crear Problema");
        System.out.println(fen);
        Problema p = new Problema ();
        //int res = p.crear_problema(4, "8/8/8/7k/8/8/1R6/R7 w - - 0 0");
        //int res = p.crear_problema(3, "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 w - - 0 0");
        //int res = p.crear_problema(2, "8/8/8/5K2/5p2/4k3/1Q6/5R2 w - - 0 0");
        //int res = p.crear_problema(2, "7k/8/8/8/8/8/1R6/R6K w - - 0 0");
        //int res = p.crear_problema(2, "7k/8/8/8/8/8/1R6/R7 w - - 0 0");
        //int res = p.crear_problema(1, "7k/R7/8/8/8/8/8/1R6 w - - 0 0");
        int res = p.crear_problema(njug, fen);

        System.out.println(String.valueOf(res));
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public void testModificarProblema(String fen, int njug) {
        System.out.println("Modificar Problema");
        Problema p = new Problema ();
        //int res = p.modificar_problema( "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 w - - 0 0", 3);
        //int res = p.modificar_problema("8/8/8/5K2/5p2/4k3/1Q6/5R2 w - - 0 0", 2);
        int res = p.modificar_problema(fen, njug);
        System.out.println(String.valueOf(res));
    }

    public void testEliminarProblema(int id, int njug, String fen, int prim) {
        System.out.println("Eliminar Problema");
        Problema p = new Problema ();
        p.setId(id);
        p.setNumJugades(njug);
        p.setPosIni(fen);
        p.setPrimer(prim);
        int res = p.eliminar_problema();
        System.out.println(String.valueOf(res));
    }

    public void testGetPeces(String fen) {
        System.out.println("Get Peces");
        Problema p = new Problema();
        //p.setId(445);
        //p.setNumJugades(3);
        p.setPosIni(fen);
        //p.setPrimer(define.WHITE);
        Taulell tau = new Taulell(p.getPeces());
        tau.printTauler();
    }

    public void testConsultarProblemes() {
        System.out.println("Consultar Problemes");
        String res[][] = Problema.consultarProblemes();
        //System.out.println(res[0][0] + " " + res[0][1] + " " + res[0][2] + " " + res[0][3] + " " + res[0][4]);
        //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
        for (int i = 0; i < res.length; ++i) {
            System.out.println(res[i][0] + " " + res[i][1] + " " + res[i][2] + " " + res[i][3] + " " + res[i][4]);
        }
    }

    public void testGetProblemaId(int id) {
        System.out.println("Get Problema Id");
        Problema p = new Problema ();
        int res = Problema.getProblemaId(id, p);
        System.out.println(String.valueOf(res));
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public static void main(String[] args) {
        DriverProblema dp = new DriverProblema();
        //dp.testConstructor();
        //dp.testSetters();
        //dp.testCrearProblema();
        //dp.testModificarProblema();
        //dp.testGetPeces();
        //dp.testConsultarProblemes();
        //dp.testGetProblemaId();
        //dp.testEliminarProblema();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        while (val != 9) {
            System.out.println("Tria el mètode que vols provar");
            System.out.println("1 - Problema()");
            System.out.println("2 - setId(int), setNumJugades(int), setPosIni(String), setPrimer(int)");
            System.out.println("3 - crear_problema(num_jugades, fen_pos_ini)");
            System.out.println("4 - modificar_problema(fen_pos_ini, num_jugades)");
            System.out.println("5 - eliminar_problema() [Id, numJugades, fen_pos_ini, primer]");
            System.out.println("6 - getPeces() [fen_pos_ini]");
            System.out.println("7 - consultarProblemes()");
            System.out.println("8 - getProblemaId(id, problema[out])");
            System.out.println("9 - Sortir");
            val = sc.nextInt();
            switch (val) {
                case 1:
                    dp.testConstructor();
                    break;
                case 2:
                    System.out.println("Introdueix el Id, numJugades, PosIni i Primer(0 blanc)");
                    int id = sc.nextInt();
                    int njug = sc.nextInt();
                    sc.useDelimiter("\\s+");
                    String fen = sc.next();
                    int prim = sc.nextInt();
                    dp.testSetters(id, njug, fen, prim);
                    break;
                case 3:
                    System.out.println("Introdueix numJugades");
                    int njug2 = sc.nextInt();
                    System.out.println("Introdueix fen");
                    sc.useDelimiter(System.lineSeparator());
                    String fen2 = sc.next();
                    sc.useDelimiter("\\s+");
                    dp.testCrearProblema(njug2, fen2);
                    break;
                case 4:
                    System.out.println("Introdueix numJugades");
                    int njug3 = sc.nextInt();
                    System.out.println("Introdueix fen");
                    sc.useDelimiter(System.lineSeparator());
                    String fen3 = sc.next();
                    sc.useDelimiter("\\s+");
                    dp.testModificarProblema(fen3, njug3);
                    break;
                case 5:
                    System.out.println("Introdueix el Id, numJugades, PosIni i Primer(0 blanc)");
                    int id4 = sc.nextInt();
                    int njug4 = sc.nextInt();
                    sc.useDelimiter("\\s+");
                    String fen4 = sc.next();
                    int prim4 = sc.nextInt();
                    dp.testEliminarProblema(id4, njug4, fen4, prim4);
                    break;
                case 6:
                    System.out.println("Introdueix PosIni");
                    sc.useDelimiter("\\s+");
                    String fen5 = sc.next();
                    dp.testGetPeces(fen5);
                    break;
                case 7:
                    dp.testConsultarProblemes();
                    break;
                case 8:
                    System.out.println("Introdueix l'id");
                    int id6 = sc.nextInt();
                    dp.testGetProblemaId(id6);
                    break;
                default:
                    break;
            }
        }
    }
}