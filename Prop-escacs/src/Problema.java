import java.io.*;

public class Problema {
    private int id;
    private int jugades;
    private char primer;
    private String ini_pos; // String?

    static String fitxer = "./files/problemes.txt";

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

    Problema (int id, int jugades, String fen) {
        this.id = id;
        int i = fen.indexOf(' ');
        this.primer = fen.charAt(i + 1);
        this.ini_pos = fen.substring(0, i);
        this.jugades = jugades;
    }

    /** crea un problema a partir de un id de problema, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer
     */
    public void crear_problema() { // public void? //if not validar return codi error?  //creadora abans especifica o no?
        //prob_id? //int prob_id, String fen, int njug

        /*int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        String pos = fen.substring(0, i);*/

        //Problema p = new Problema(prob_id, njug, prim, pos);

        if (this.validar_problema()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(this.jugades);
                    String sprim = String.valueOf(this.primer);
                    if (camps[1].equals(snjug) && camps[2].equals(sprim) && camps[3].equals(this.ini_pos)) { //o nomes prob_id?
                        //problema ja existeix
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, sprim, this.ini_pos};
                        in_out.write(fitxer, linia);
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {

        }



    }

    /** modifica un problema a partir d'un problema anterior, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer sense borrar l'anterior
     */
    public void modificar_problema(String fen, int njug) {
        //modificar objecte o crear nou?
        this.jugades = njug;

        int i = fen.indexOf(' ');
        this.primer = fen.charAt(i+1);
        this.ini_pos = fen.substring(0, i);

        if (this.validar_problema()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(njug);
                    String sprimer = String.valueOf(this.primer);
                    if (camps[1].equals(snjug) && camps[2].equals(sprimer) && camps[3].equals(this.ini_pos)) { //o nomes prob_id?
                        //problema ja existeix
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, String.valueOf(this.primer), this.ini_pos};
                        in_out.write(fitxer, linia);
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {

        }
    }

    /*
    private void fen_to_prob(String fen, char prim, String pos) {
        int i = fen.indexOf(' ');
        prim = fen.charAt(i+1);
        pos = fen.substring(0, i);
    }*/

    /** elimina un problema p de la base de dades
     */
    public void eliminar_problema() { //prob_id? //borrar objecte?
        //permissos?
        String borra_linia = String.valueOf(this.id) + " " + String.valueOf(this.jugades) + " " +
                             String.valueOf(this.primer) + " " + this.ini_pos;
        File tempfile = new File ("./files/mytemp.txt");
        File inputfile = new File (fitxer);
        boolean trobat = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fitxer));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(borra_linia)) {
                    writer.write(line + System.getProperty("line.separator"));
                }
                else {
                    trobat = true;
                }
            }
            writer.close();
            reader.close();
            if (trobat) {
                inputfile.delete(); //needed?
                tempfile.renameTo(inputfile); //successful?
            }
            else {
                // no existeix problema
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** retorna una matriu de peces de 8x8 amb les peces posades al seu lloc i peces nules als espais buits
     */
    public Peca[][] getPeces() {
        Peca mat[][] = new Peca[8][8];
        int k = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char act = this.ini_pos.charAt(k);
                if ((act >= 'a' && act <= 'z') || (act >= 'A' && act <= 'Z')) { //class character method
                    switch(act) {
                        case 'r':
                            mat[i][j] = new Torre(k, define.BLACK);
                            break;
                        case 'n':
                            mat[i][j] = new Cavall(k, define.BLACK);
                            break;
                        case 'b':
                            mat[i][j] = new Alfil(k, define.BLACK);
                            break;
                        case 'q':
                            mat[i][j] = new Reina(k, define.BLACK);
                            break;
                        case 'k':
                            mat[i][j] = new Rei(k, define.BLACK);
                            break;
                        case 'p':
                            mat[i][j] = new Peo(k, define.BLACK, true);
                            break;
                        case 'R':
                            mat[i][j] = new Torre(k, define.WHITE);
                            break;
                        case 'N':
                            mat[i][j] = new Cavall(k, define.WHITE);
                            break;
                        case 'B':
                            mat[i][j] = new Alfil(k, define.WHITE);
                            break;
                        case 'Q':
                            mat[i][j] = new Reina(k, define.WHITE);
                            break;
                        case 'K':
                            mat[i][j] = new Rei(k, define.WHITE);
                            break;
                        case 'P':
                            mat[i][j] = new Peo(k, define.WHITE, true);
                            break;
                        default:
                            //
                            break;
                    }
                }
                else if (act >= '0' && act <= '8'){                             //class character method
                    for (int z = 0; z < Character.getNumericValue(act); ++z) {
                        mat[i][j] = new Peca_Nula(k);
                        //tau_mat[i][j] = define.PECA_NULA;
                        ++j;
                    }
                    --j;
                }
                ++k;
            }
            ++k;
        }
        return mat;
    }

    /** valida si un problema p es pot resoldre en un nombre de jugades p.njug
     */
    public boolean validar_problema() { //private?
        /*char tau_mat[][] = new char[8][8]; // list?
        //String tau_mat[][] = new String[8][8];
        int k = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char act = this.ini_pos.charAt(k);
                if ((act >= 'a' && act <= 'z') || (act >= 'A' && act <= 'Z')) { //class character method
                    tau_mat[i][j] = act;
                    /*if (Character.isUpperCase(act)) act = Character.toLowerCase(act);
                    switch(act) {
                        case 'r':
                            tau_mat[i][j] = define.TORRE;
                            break;
                        case 'n':
                            tau_mat[i][j] = define.CAVALL;
                            break;
                        case 'b':
                            tau_mat[i][j] = define.ALFIL;
                            break;
                        case 'q':
                            tau_mat[i][j] = define.REINA;
                            break;
                        case 'k':
                            tau_mat[i][j] = define.REI;
                            break;
                        case 'p':
                            tau_mat[i][j] = define.PEO;
                            break;
                        default:
                            //
                            break;
                    }//
                }
                else if (act >= '0' && act <= '8'){                             //class character method
                    for (int z = 0; z < Character.getNumericValue(act); ++z) {
                        tau_mat[i][j] = '0';
                        //tau_mat[i][j] = define.PECA_NULA;
                        ++j;
                    }
                    --j;
                }
                ++k;
            }
            ++k;
        }*/
        Peca tau_mat[][] = this.getPeces();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                //char act = tau_mat[i][j];
                //if (Character.isUpperCase(act)) act = Character.toLowerCase(act);
                /*switch(act) {
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
                }*/
                int mov[][] = tau_mat[i][j].movimientos_validos(i, j);
                for (int k = 0; i < tau_mat.length; ++k) {
                    //tau_mat[k].length
                }
            }
        }
        return false;
    }

}