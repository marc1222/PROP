import java.io.*;
import java.util.ArrayList;

public class Problema {
    private int id;
    private int jugades;
    private int primer;
    private String ini_pos; // String?
    private int dificultat;
    //usuari creador pels permissos

    static String fitxer = "./files/problemes.txt";
    static String fitxerId = "./files/index.txt";
    private static int index = -1;

    private static int getNextId() {
        if (index == -1) {
            index = llegirId();
        }
        ++index;
        guardarId(); // fi de programa?
        return index;
    }

    private static void guardarId() {
        try (FileWriter fileWriter = new FileWriter(fitxerId, false); PrintWriter pw = new PrintWriter(fileWriter)) {
            pw.println(String.valueOf(index));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static int llegirId() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            if ((line = br.readLine()) != null) {
                return Integer.parseInt(line);
            }
            else return -1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // try return
    }

    Problema () {
        id = getNextId();
        jugades = -1;
        primer = -1;
        ini_pos = null;
        dificultat = -1;
    }

    Problema (int jugades, int primer) { //int id
        this.id = getNextId();
        this.jugades = jugades;
        this.primer = primer;
        ini_pos = null;
    } //?

    Problema (int jugades, int primer, String ini_pos, int dificultat) { //int id
        this.id = getNextId();
        this.jugades = jugades;
        this.primer = primer;
        this.ini_pos = ini_pos;
        this.dificultat = dificultat;
    }

    Problema (int jugades, String fen) { //int id
        this.id = getNextId();
        int i = fen.indexOf(' ');
        char prim = fen.charAt(i + 1);
        if (prim == 'w') {
            this.primer = define.WHITE;
        }
        else if (prim == 'b') {
            this.primer = define.BLACK;
        }
        else; // invalid fen
        this.ini_pos = fen.substring(0, i);
        this.jugades = jugades;
        this.dificultat = calculaDif(this.ini_pos, jugades);
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
        return this.ini_pos;
    }

    public int getDificultat() {
        return this.dificultat;
    }

    public int setId(int id) { // needed?
        if (id >= 0) {
            this.id = id;
            return 0;
        }
        else return -1;
    }

    public int setNumJugades(int jugades) {
        if (jugades > 0) {
            this.jugades = jugades;
            return 0;
        }
        else return -1;
    }

    public int setPrimer(int primer) {
        if (primer == define.BLACK || primer == define.WHITE) {
            this.primer = primer;
            return 0;
        }
        else return -1;
    }

    public int setPosIni(String ini_pos) {
        int count = 0;
        for (int i = 0; i < ini_pos.length(); ++i) {
            if (ini_pos.charAt(i) == '/') ++count;
        }
        if (count == 7) {
            this.ini_pos = ini_pos;
            return 0;
        }
        else return -1;
    }

    private static int calculaDif(String pos_ini, int njug) {
        int count = 0;
        for (int j = 0; j < pos_ini.length(); ++j) {
            if (pos_ini.charAt(j) < '0' || pos_ini.charAt(j) > '8') ++count;
        }
        int res = ((count - 7) * njug) / 10;
        if (res > 10) res = 10;
        return res;
    }

    /** crea un problema a partir de un id de problema, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer
     */
    public int crear_problema(int njug, String fen) { // public void? //if not validar return codi error?  //creadora abans especifica o no?
        //prob_id? //int prob_id, String fen, int njug
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
            System.out.println("FEN invàlid");
            return -2;
        }
        this.ini_pos = fen.substring(0, i);
        this.dificultat = calculaDif(this.ini_pos, njug);

        //Problema p = new Problema(prob_id, njug, prim, pos);

        if (this.validar_problema(this.primer, new Taulell(this.getPeces()), this.jugades, true)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(this.jugades);
                    String sprim = String.valueOf(this.primer);
                    String sdif = String.valueOf(this.dificultat);
                    if (camps[1].equals(snjug) && camps[2].equals(sprim) && camps[3].equals(this.ini_pos)) { //o nomes prob_id?
                        //problema ja existeix
                        System.out.println("El problema ja existex");
                        return -3;
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, sprim, this.ini_pos, sdif};
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
            return -1;
        }
        return 0;
    }

    /** modifica un problema a partir d'un problema anterior, l'string FEN complert i el nombre de jugades i el valida,
     *  si es valid l'escriu al fitxer sense borrar l'anterior
     */
    public int modificar_problema(String fen, int njug) {
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
            return -2;
        }
        this.ini_pos = fen.substring(0, i);
        this.dificultat = calculaDif(this.ini_pos, njug);

        if (this.validar_problema(this.primer, new Taulell(this.getPeces()), this.jugades, true)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] camps = line.split("\\s+");
                    String snjug = String.valueOf(njug);
                    String sprimer = String.valueOf(this.primer);
                    String sdif = String.valueOf(this.dificultat);
                    if (camps[1].equals(snjug) && camps[2].equals(sprimer) && camps[3].equals(this.ini_pos)) { //o nomes prob_id?
                        //problema ja existeix
                        System.out.println("El nou problema ja existeix");
                        return -3;
                    }
                    else {
                        input_output in_out = new input_output();
                        String[] linia = {String.valueOf(this.id), snjug, sprimer, this.ini_pos, sdif};
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
            return -1;
        }
        return 0;
    }

    /*
    private void fen_to_prob(String fen, char prim, String pos) {
        int i = fen.indexOf(' ');
        prim = fen.charAt(i+1);
        pos = fen.substring(0, i);
    }*/

    /** elimina un problema p de la base de dades
     */
    public int eliminar_problema() { //prob_id? //borrar objecte?
        //permissos?
        String borra_linia = String.valueOf(this.id) + " " + String.valueOf(this.jugades) + " " +
                             String.valueOf(this.primer) + " " + this.ini_pos + " " + this.dificultat;
        File tempfile = new File ("./files/mytemp.txt");
        File inputfile = new File (fitxer);
        boolean trobat = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(borra_linia)) {
                    writer.write(line + System.getProperty("line.separator"));
                }
                else {
                    trobat = true;
                }
            }
            //writer.close();
            //reader.close();
            if (trobat) {
                inputfile.delete(); //needed?
                tempfile.renameTo(inputfile); //successful?
                System.out.println("El problema s'ha eliminat");
            }
            else {
                // no existeix problema
                System.out.println("El problema no existeix");
                return -1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** retorna una matriu de peces de 8x8 amb les peces posades al seu lloc i peces nules als espais buits
     */
    public Peca[][] getPeces() {
        Peca mat[][] = new Peca[8][8];
        int k = 0;
        for (int i = 7; i >= 0; --i) {
            for (int j = 0; j < 8; ++j) {
                char act = this.ini_pos.charAt(k);
                if ((act >= 'a' && act <= 'z') || (act >= 'A' && act <= 'Z')) { //class character method
                    switch(act) {
                        case 'r':
                            mat[j][i] = new Torre(define.BLACK);
                            break;
                        case 'n':
                            mat[j][i] = new Cavall(define.BLACK);
                            break;
                        case 'b':
                            mat[j][i] = new Alfil(define.BLACK);
                            break;
                        case 'q':
                            mat[j][i] = new Reina(define.BLACK);
                            break;
                        case 'k':
                            mat[j][i] = new Rei(define.BLACK);
                            break;
                        case 'p':
                            mat[j][i] = new Peo(define.BLACK, true);
                            break;
                        case 'R':
                            mat[j][i] = new Torre(define.WHITE);
                            break;
                        case 'N':
                            mat[j][i] = new Cavall(define.WHITE);
                            break;
                        case 'B':
                            mat[j][i] = new Alfil(define.WHITE);
                            break;
                        case 'Q':
                            mat[j][i] = new Reina(define.WHITE);
                            break;
                        case 'K':
                            mat[j][i] = new Rei(define.WHITE);
                            break;
                        case 'P':
                            mat[j][i] = new Peo(define.WHITE, true);
                            break;
                        default:
                            //
                            break;
                    }
                }
                else if (act >= '0' && act <= '8'){                             //class character method
                    for (int z = 0; z < Character.getNumericValue(act); ++z) {
                        mat[j][i] = new Peca_Nula();
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

    /*public static Problema[] consultarProblemes() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            ArrayList<Problema> probs = new ArrayList<Problema>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                Problema prob = new Problema(Integer.parseInt(camps[0]), Integer.parseInt(camps[1]), Integer.parseInt(camps[2]), camps[3]);
                //NumberFormatException0
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
    }*/

    /** retorna un array de tots els problemes del fitxer
     */
    public static String[][] consultarProblemes() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            ArrayList<String[]> probs = new ArrayList<String[]>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] camps = line.split("\\s+");
                //NumberFormatException0
                probs.add(camps);
            }
            String res[][] = new String[probs.size()][5];
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
                    p.dificultat = Integer.parseInt(camps[4]);
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

    /** valida si un problema p es pot resoldre en un nombre de jugades p.njug
     */
    public boolean validar_problema(int color_act, Taulell tau, int njug, boolean atk) { //private?
        //Peca pec_mat[][] = this.getPeces();
        //Taulell tau = new Taulell(pec_mat);
        if (njug == 0) return false;
        int color_cont;
        if (color_act == define.WHITE) color_cont = define.BLACK;
        else color_cont = define.WHITE;
        //for (int i = 0; i < 8; ++i) {
            //for (int j = 0; j < 8; ++j) {
        Posicion pec_pos[] = tau.getPosColor(color_act);
        for (int i = 0; i < pec_pos.length; ++i) {
            // if peça nula
            //Posicion mov[] = pec_mat[i][j].movimientos_posibles(new Posicion(i, j));
            //Posicion pos_act = new Posicion(i, j);
            Posicion mov[] = tau.todos_movimientos(pec_pos[i]);
            for (int k = 0; i < mov.length; ++k) {
                boolean ret;
                tau.mover_pieza(pec_pos[i], mov[k], color_act);
                if (atk && tau.escac_i_mat(color_cont) == 1) return true;
                if (atk) {
                    ret = this.validar_problema(color_cont, tau, njug, false);
                }
                else {
                    if (njug != 1) ret = this.validar_problema(color_cont, tau, njug - 1, true); // if njug == 1 false
                    else ret = false;
                }
                if (ret) return true;
                tau.mover_pieza(mov[k], pec_pos[i], color_act); // o new tau?
            }
                    //moure peça
        }
        return false;
        /*if (true) { //
            System.out.println("El problema és vàlid");
            return true;
        }
        else {
            return false;
        }*/
    }

}