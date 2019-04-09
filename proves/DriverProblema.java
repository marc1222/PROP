public class DriverProblema {
    public void testConstructor() {
        System.out.println("Constructora");
        Problema p = new Problema();
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + String.valueOf(p.getPosIni()) + " " +
                String.valueOf(p.getDificultat())); //0 -1 -1 null -1
    }

    public void testSetters() {
        System.out.println("Setters");
        Problema p = new Problema();
        p.setId(444);
        p.setNumJugades(3);
        p.setPosIni("8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8");
        p.setPrimer(define.WHITE);
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //444 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public void testCrearProblema() {
        System.out.println("Crear Problema");
        Problema p = new Problema ();
        //int res = p.crear_problema(3, "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 w - - 0 0");
        int res = p.crear_problema(2, "8/8/8/5K2/5p2/4k3/1Q6/5R2 w - - 0 0");
        //int res = p.crear_problema(2, "7k/8/8/8/8/8/1R6/R6K w - - 0 0");
        //int res = p.crear_problema(2, "7k/8/8/8/8/8/1R6/R7 w - - 0 0");
        //int res = p.crear_problema(1, "7k/R7/8/8/8/8/8/1R6 w - - 0 0");

        System.out.println(String.valueOf(res));
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public void testModificarProblema() {
        System.out.println("Modificar Problema");
        Problema p = new Problema ();
        int res = p.modificar_problema( "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 w - - 0 0", 3);
        System.out.println(String.valueOf(res));
    }

    public void testEliminarProblema() {
        System.out.println("Eliminar Problema");
        Problema p = new Problema ();
        p.setId(61);
        p.setNumJugades(2);
        p.setPosIni("7k/8/8/8/8/8/1R6/R7");
        p.setPrimer(define.WHITE);
        int res = p.eliminar_problema();
        System.out.println(String.valueOf(res));
    }

    public void testGetPeces() {
        System.out.println("Get Peces");
        Problema p = new Problema();
        p.setId(445);
        p.setNumJugades(3);
        p.setPosIni("8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8");
        p.setPrimer(define.WHITE);
        Taulell tau = new Taulell(p.getPeces());
        tau.printTauler();
    }

    public void testConsultarProblemes() {
        System.out.println("Consultar Problemes");
        String res[][] = Problema.consultarProblemes();
        System.out.println(res[0][0] + " " + res[0][1] + " " + res[0][2] + " " + res[0][3] + " " + res[0][4]);
        //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public void testGetProblemaId() {
        System.out.println("Get Problema Id");
        Problema p = new Problema ();
        int res = Problema.getProblemaId(61, p);
        System.out.println(String.valueOf(res));
        System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                String.valueOf(p.getDificultat())); //2 3 0 8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8 3
    }

    public static void main(String[] args) {
        DriverProblema dp = new DriverProblema();
        dp.testConstructor();
        dp.testSetters();
        dp.testCrearProblema();
        dp.testModificarProblema();
        dp.testGetPeces();
        dp.testConsultarProblemes();
        dp.testGetProblemaId();
        dp.testEliminarProblema();
    }
}