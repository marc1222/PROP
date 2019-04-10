
public class StubProblema {

    StubProblema() {
        System.out.println("Problema creat");
    }

    public int getId() {
        return 1;
    }

    public int getNumJugades() {
        return 3;
    }

    public int getPrimer() {
        return define.WHITE;
    }

    public String getPosIni() {
        return "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8";
    }

    public int getDificultat() {
        return 3;
    }

    public void setId(int id) {
        System.out.println("S'ha modificat el Id");
    }

    public void setNumJugades(int jugades) {
        System.out.println("S'ha modificat el nombre de jugades");
    }

    public void setPrimer(int primer) {
        System.out.println("S'ha modificat el color que va primer");
    }

    public void setPosIni(String ini_pos) {
        System.out.println("S'ha modificar la posició inicial");
    }

    public void crear_problema(int njug, String fen) {
        System.out.println("S'ha escrit el problema en el fitxer");
    }

    public void modificar_problema(String fen, int njug) {
        System.out.println("S'ha escrit el problema modificat en el fitxer");
    }

    public void eliminar_problema() {
        System.out.println("S'ha eliminat el problema del fitxer");
    }

    public Peca[][] getPeces() {
        Peca mat[][] = new Peca[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (i == 0 && j == 0) mat[i][j] = new Rei(define.WHITE);
                else if (i == 7 && j == 7) mat[i][j] = new Rei(define.BLACK);
                else mat[i][j] = new Peca_Nula();
            }
        }
        return mat;
    }

    public static String[][] consultarProblemes() {
        String res [][] = {{"1", "3", String.valueOf(define.WHITE), "8/8/2Pb2pk/1R1B4/6Pp/5R1K/2r5/8"}};
        return res;
    }

    public static int getProblemaId(int id, StubProblema sp) {
        sp = new StubProblema();
        return 0;
    }
}
