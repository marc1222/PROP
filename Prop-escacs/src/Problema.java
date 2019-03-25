import java.io.*;

public class Problema {
    int id;
    int jugades;
    char primer;
    String ini_pos; // String?

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

    /** crea un problema a partir de un id de problema, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer
     */
    public void crear_problema(int prob_id, String fen, int njug) { // public void? //if not validar return codi error?
        //prob_id?
        int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        String pos = fen.substring(0, i);

        Problema p = new Problema(prob_id, njug, prim, pos);

        if (validar_problema(p)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(njug);
                    String sprim = String.valueOf(prim);
                    if (camps[1].equals(snjug) && camps[2].equals(sprim) && camps[3].equals(pos)) { //o nomes prob_id?
                        //problema ja existeix
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(prob_id), snjug, sprim, pos};
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
    public void modificar_problema(Problema p, String fen, int njug) {
        p.jugades = njug;

        int i = fen.indexOf(' ');
        p.primer = fen.charAt(i+1);
        p.ini_pos = fen.substring(0, i);

        if (validar_problema(p)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(njug);
                    String sprimer = String.valueOf(p.primer);
                    if (camps[1].equals(snjug) && camps[2].equals(sprimer) && camps[3].equals(p.ini_pos)) { //o nomes prob_id?
                        //problema ja existeix
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(p.id), snjug, String.valueOf(p.primer), p.ini_pos};
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
    public void eliminar_problema(Problema p) { //prob_id? //borrar objecte?
        //permissos?
        String borra_linia = String.valueOf(p.id) + " " + String.valueOf(p.jugades) + " " + String.valueOf(p.primer) + " "
                        + p.ini_pos;
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

    /** valida si un problema p es pot resoldre en un nombre de jugades p.njug
     */
    public boolean validar_problema(Problema p) { //private?
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
                if (Character.isUpperCase(act)) act = Character.toLowerCase(act);
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