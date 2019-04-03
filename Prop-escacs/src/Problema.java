import java.io.*;
import java.util.ArrayList;

public class Problema {
    private int id;
    private int jugades;
    private int primer;
    private String ini_pos; // String?
    //usuari creador

    static String fitxer = "./files/problemes.txt";
    static String fitxerId = "./files/indexId.txt";
    private static int index = 0;

    private static int getNextId() {
        return ++index;
    }

    Problema () {
        id = getNextId();
        jugades = -1;
        primer = -1;
        ini_pos = null;
    }

    Problema (int id, int jugades, int primer) {
        this.id = id;
        this.jugades = jugades;
        this.primer = primer;
        ini_pos = null;
    } //?

    Problema (int id, int jugades, int primer, String ini_pos) {
        this.id = id;
        this.jugades = jugades;
        this.primer = primer;
        this.ini_pos = ini_pos;
    }

    Problema (int id, int jugades, String fen) {
        this.id = id;
        int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        if (prim == 'w') {
            this.primer = define.WHITE;
        }
        else if (prim == 'b') {
            this.primer = define.BLACK;
        }
        else ; // invalid fen
        this.ini_pos = fen.substring(0, i);
        this.jugades = jugades;
    }

    public int getId() {
        return this.id;
    }



    public int getNumJugades() {
        return this.jugades;
    }

    public int getPrimer() {
        return this.primer;
    }

    public String getPosIni() {
        return ini_pos;
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else ; //
    }

    public void setNumJugades(int jugades) {
        if (jugades > 0) this.jugades = jugades;
        else ; //
    }

    public void setPrimer(int primer) {
        if (primer == define.BLACK || primer == define.WHITE) this.primer = primer;
        else ; //
    }

    public void setPosIni(String ini_pos) {
        // if fen
        this.ini_pos = ini_pos;
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
                        System.out.println("El problema ja existex");
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, sprim, this.ini_pos};
                        in_out.write(fitxer, linia);
                        System.out.println("S'ha creat el problema");
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
            System.out.println("Problema sense solució");
        }



    }

    /** modifica un problema a partir d'un problema anterior, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer sense borrar l'anterior
     */
    public void modificar_problema(String fen, int njug) {
        //modificar objecte o crear nou?
        this.jugades = njug;

        int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        if (prim == 'w') {
            this.primer = define.WHITE;
        }
        else if (prim == 'b') {
            this.primer = define.BLACK;
        }
        else {
            //invalid fen
            System.out.println("Problema o FEN invàlid");
        }
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
                        System.out.println("El nou problema ja existeix");
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, String.valueOf(this.primer), this.ini_pos};
                        in_out.write(fitxer, linia);
                        System.out.println("S'ha clonat i modificat el problema");
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
            System.out.println("El nou problema no té solució");

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
                System.out.println("El problema s'ha eliminat");
            }
            else {
                // no existeix problema
                System.out.println("El problema no existeix");
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

    /** retorna un array de tots els problemes del fitxer
     */
    public static Problema[] consultarProblemes() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            ArrayList<Problema> probs = new ArrayList<Problema>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                Problema prob = new Problema(Integer.parseInt(camps[0]), Integer.parseInt(camps[1]), Integer.parseInt(camps[2]), camps[3]);
                //NumberFormatException
                probs.add(prob);
            }
            Problema res[] = new Problema[probs.size()];
            res = probs.toArray(res);
            return res;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null; //try return?
    }

    /*public static Problema getProblemaId(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            boolean trobat = false;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                String sid = String.valueOf(id);
                if (camps[0].equals(sid)) {
                    trobat = true;
                    //problema ja existeix
                    Problema res = new Problema(Integer.parseInt(camps[0]), Integer.parseInt(camps[1]), Integer.parseInt(camps[2]), camps[3]);
                    return res;
                }
            }
            if (!trobat) {
                Problema res = new Problema();
                return res;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null; //try return?
    }*/
    /** retorna el problema amb el id passat per paràmetre pel paràmetre p i 0 o -1 si s'ha trobat o no
     *  i un -2 si unexpected error
     */
    public static int getProblemaId(int id, Problema p) { //comprobar problema.id != id
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            boolean trobat = false;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                String sid = String.valueOf(id);
                if (camps[0].equals(sid)) {
                    trobat = true;
                    p.id = id;
                    p.jugades = Integer.parseInt(camps[1]);
                    p.primer = Integer.parseInt(camps[2]);
                    p.ini_pos = camps[3];
                    return 0;
                }
            }
            if (!trobat) {
                //no existeix el problema amb aquest id
                return -1;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -2; //try return?
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
                Posicion mov[] = tau_mat[i][j].movimientos_validos(new Posicion(i, j));
                for (int k = 0; i < tau_mat.length; ++k) {
                    //tau_mat[k].length

                    //moure peça
                }
            }
        }
        if (true) { //
            System.out.println("El problema és vàlid");
            return true;
        }
        else {
            return false;
        }
    }

}