public class Problema {
    int id;
    int jugades;
    char primer;
    String ini_pos; // String?

    Problema () {
        id = -1;
        jugades = 0;
        primer = '\0';
        ini_pos = null;
    }

    Problema (int id, int jugades, char primer) {
        this.id = id;
        this.jugades = jugades;
        this.primer = primer;
        ini_pos = null;
    }

    Problema (int id, int jugades, char primer, String ini_pos) {
        this.id = id;
        this.jugades = jugades;
        this.primer = primer;
        this.ini_pos = ini_pos;
    }

    public void crear_problema(int prob_id, String fen, int njug) { // public void?
        int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        String pos = fen.substring(0, i);

        Problema p = new Problema(prob_id, njug, prim, pos);

        if (validar_problema(p)) {

        }
        else {

        }
    }

    public void modificar_problema(Problema p, String fen, int njug) {
        p.jugades = njug;

        int i = fen.indexOf(' ');
        p.primer = fen.charAt(i+1);
        p.ini_pos = fen.substring(0, i);

        if (validar_problema(p)) {

        }
        else {

        }
    }

    //
    /*
    private void fen_to_prob(String fen, char prim, String pos) {
        int i = fen.indexOf(' ');
        prim = fen.charAt(i+1);
        pos = fen.substring(0, i);
    }*/

    public boolean validar_problema(Problema p) {
        char tau_mat[][] = new char[8][8]; // list?
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
                        //movimientos_validos

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
        return false;
    }

}