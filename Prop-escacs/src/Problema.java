public class Problema {
    int jugades;
    char primer;
    String ini_pos; // String?

    Problema () {
        jugades = 0;
        primer = '\0';
        ini_pos = null;
    }

    Problema (int jugades, char primer) {
        this.jugades = jugades;
        this.primer = primer;
        ini_pos = null;
    }

    Problema (int jugades, char primer, String ini_pos) {
        this.jugades = jugades;
        this.primer = primer;
        this.ini_pos = ini_pos;
    }

    public void crear_problema(String fen, int njug) { // public void?
        char prim = '\0';
        String pos = null;
        fen_to_prob(fen, prim, pos);
        Problema p = new Problema(njug, prim, pos);

        // validar problema
    }

    public void modificar_problema(Problema p, String fen, int njug) {
        p.jugades = njug;
        char prim = '\0';
        String pos = null;
        fen_to_prob(fen, prim, pos); //parametre p.primer?
        p.primer = prim;
        p.ini_pos = pos;

        // validar problema
    }

    private void fen_to_prob(String fen, char prim, String pos) {
        int i = fen.indexOf(' ');
        prim = fen.charAt(i+1);
        pos = fen.substring(0, i);
    }

    public void validar_problema(Problema p) {
        char tau_mat[][] = new char[8][8];
        int k = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char act = p.ini_pos.charAt(k);
                if ((act >= 'a' && act <= 'z') || (act >= 'A' && act <= 'Z')) { //class character method
                    tau_mat[i][j] = act;
                }
                else if (act >= '0' && act <= '8'){                             //class character method
                    for (int z = 0; z < Character.getNumericValue(act); ++z) {
                        tau_mat[i][j] = '0';
                        ++j;
                    }
                    --j;
                }
                ++k;
            }
            ++k;
        }
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char act = tau_mat[i][j];
                if (Character.isUpperCase(act)) Character.toLowerCase(act);
                switch(act) {
                    case 'r':

                        break;
                    case 'n':

                        break;
                    case 'b':

                        break;
                    case 'q':

                        break;
                    case 'k':

                        break;
                    case 'p':

                        break;
                    default:

                        break;
                }
            }
        }
    }

}