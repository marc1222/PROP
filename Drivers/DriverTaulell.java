public class DriverTaulell {
    public static void main(String[] args) {
        StubProblema sp = new StubProblema();
        Taulell tauler = new Taulell(sp.getPeces());
       // tauler.printTauler();
        Posicion n = new Posicion(3,3);
        tauler.crear_peça(n,define.WHITE,define.ALFIL);
        tauler.crear_peça(new Posicion(1,6),define.WHITE,define.CAVALL);

        tauler.crear_peça(new Posicion(4,5),define.WHITE,define.TORRE);
        tauler.crear_peça(new Posicion(2,1),define.BLACK,define.REINA);
        tauler.crear_peça(new Posicion(0,2),define.BLACK,define.TORRE);

        tauler.printTauler();
        boolean ret = tauler.mover_pieza(new Posicion(4,5),new Posicion(2,5),define.WHITE);
        System.out.println(ret);
        ret = tauler.mover_pieza(new Posicion(2,5),new Posicion(0,5),define.WHITE);
        System.out.println(ret);
        //tauler.destrueix_peça(n);
        ret = tauler.mover_pieza(new Posicion(3,3),new Posicion(1,5),define.WHITE);
        System.out.println(ret);

        ret = tauler.mover_pieza(new Posicion(3,3),new Posicion(2,4),define.WHITE);
        System.out.println(ret);
        Posicion[] aux = tauler.todos_movimientos(new Posicion(1,6));
//        for (int i = 0; i < aux.length; ++i) {
//            System.out.print("NewPos: "+aux[i].x+"-"+aux[i].y+"---");
//        }
        System.out.println(ret);
        int res = tauler.escac_i_mat(define.WHITE);
        System.out.println(res);


        tauler.printTauler();

    }
}
