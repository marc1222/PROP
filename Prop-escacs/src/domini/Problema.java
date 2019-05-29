package domini;
import gestioDades.GestorPersistenciaProblema;

public class Problema {
    private int id;
    private int jugades;
    private int primer;
    private String ini_pos;
    private int dificultat;
    private String idCreador;
    //usuari creador pels permissos (not implemented)

    //static private String fitxer = "./files/problemes.txt";     //branca main
    //static private String fitxerId = "./files/index.txt";       //branca main
    //private static int index = -1;

    /**
     * Assigna els Id als problemes
     * Pre: true
     * @return el següent Id de problema disponible o 0 si és el primer (identificador únic)
     */
    /*private static int getNextId() {
        if (index == -1) {
            index = llegirId();
        }
        ++index;
        guardarId();
        return index;
    }*/

    /**
     * Escriu l'últim Id de problema assignat en el fitxer index.txt i si no existia el crea
     * Pre: true
     */
    /*private static void guardarId() {
        try (FileWriter fileWriter = new FileWriter(fitxerId, false); PrintWriter pw = new PrintWriter(fileWriter)) {
            pw.println(String.valueOf(index));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }*/

    /**
     * Llegeix l'id actual del fitxer
     * Pre: true
     * @return l'últim Id de problema assignat del fitxer index.txt si exitiex, si no retorna -1 o -2 en cas d'error
     */
    /*private static int llegirId() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerId))) {
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
        return -2; // try return
    }*/

    /**
     * Crea un problema amb el següent Id disponible i la resta de camps buits
     * Pre: true
     */
    public Problema () {
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        id = gpp.getNextId();
        jugades = -1;
        primer = -1;
        ini_pos = null;
        dificultat = -1;
        idCreador = "-1";
    }

    Problema (int jugades, int primer) {
        //this.id = getNextId();
        this.jugades = jugades;
        this.primer = primer;
        ini_pos = null;
    }

    Problema (int jugades, int primer, String ini_pos, int dificultat) {
        //this.id = getNextId();
        this.jugades = jugades;
        this.primer = primer;
        this.ini_pos = ini_pos;
        this.dificultat = dificultat;
    }

    Problema (int jugades, String fen) {
        //this.id = getNextId();
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

    public String getIdCreador() {return this.idCreador; }

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
            this.dificultat = calculaDif(this.ini_pos, jugades);
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
            this.dificultat = calculaDif(ini_pos, this.jugades);
            return 0;
        }
        else return -1;
    }

    public void setIdCreador(String idc) {
        // idc >= 0?
        this.idCreador = idc;
    }

    /**
     * Calcula la dificultat d'un problema segons el número de peces i les jugades per resoldre
     * Pre: pos_ini és un FEN vàlid
     * @param pos_ini posició incial del problema en format FEN
     * @param njug número de jugades del problema
     * @return la dificultat del problema del 0 al 10
     */
    private static int calculaDif(String pos_ini, int njug) {
        int count = 0;
        int res = 0;
        if (pos_ini != null) {
            for (int j = 0; j < pos_ini.length(); ++j) {
                if (pos_ini.charAt(j) < '0' || pos_ini.charAt(j) > '8') ++count;
            }
            res = ((count - 7) * njug) / 10;
            if (res > 10) res = 10;
            if (res < 0) res = 0;
        }
        return res;
    }

    /**
     * Crea un problema a partir d'un string FEN complert i el nombre de jugades i el valida, si es vàlid i encara
     * no existeix l'escriu al fitxer
     * Pre: njug >= 0, fen ha de contenir 1 o més espais
     * @param njug número de jugades del problema
     * @param fen FEN de la posició inicial del problema
     * @return 0 si s'ha creat el problema, -1 si no és vàlid, -2 si el FEN és invàlid i -3 si el problema ja existia
     */
    public int crear_problema(int njug, String fen) { //usuari creador
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
        String snjug = String.valueOf(njug);
        String sprim = String.valueOf(this.primer);
        String sdif = String.valueOf(this.dificultat);
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        if (gpp.comprovarExistencia(snjug, sprim, sdif, this.ini_pos, idCreador)) {
            //problema ja existeix
            System.out.println("El problema ja existex");
            return -3;
        }
        System.out.println("Validant el problema...");
        if (this.validar_problema2(this.primer, new Taulell(new Taulell(this.getPeces())), this.jugades)) {
            gpp.escriuProblema(String.valueOf(this.id), snjug, sprim, this.ini_pos, sdif, idCreador);
            System.out.println("S'ha creat el problema");
        }
        else {
            System.out.println("Problema sense solució");
            return -1;
        }
        return 0;
    }

    /**
     * Modifica un problema a partir d'un problema anterior, l'string FEN complert i el nombre de jugades i el valida,
     * si es vàlid i no existeix al fitxer sense borrar l'anterior
     * Pre: njug >= 0, fen ha contenir 1 o més espais
     * @param fen FEN de la posició inicial del problema
     * @param njug número de jugades del problema
     * @return 0 si s'ha creat el problema, -1 si no és vàlid, -2 si el FEN és invàlid i -3 si el problema ja existia
     */
    public int modificar_problema(String fen, int njug) { //usuari creador
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

        String snjug = String.valueOf(njug);
        String sprim = String.valueOf(this.primer);
        String sdif = String.valueOf(this.dificultat);
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        if (gpp.comprovarExistencia(snjug, sprim, sdif, this.ini_pos, idCreador)) {
            //problema ja existeix
            System.out.println("El nou problema ja existeix");
            return -3;
        }
        System.out.println("Validant el problema...");
        if (this.validar_problema2(this.primer, new Taulell(new Taulell(this.getPeces())), this.jugades)) {
            gpp.escriuProblema(String.valueOf(this.id), snjug, sprim, this.ini_pos, sdif, this.idCreador);
            System.out.println("S'ha clonat i modificat el problema");
        }
        else {
            System.out.println("El nou problema no té solució");
            return -1;
        }
        return 0;
    }

    /**
     * Elimina el problema paràmetre implícit del fitxer
     * Pre: true
     * @return 0 si s'ha eliminat o -1 si el problema no existia
     */
    public int eliminar_problema() { //prob_id? //borrar objecte?
        //permissos?
        String borra_linia = this.id + " " + this.jugades + " " +
                             this.primer + " " + this.ini_pos + " " + this.dificultat + " " + this.idCreador;
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        if ((gpp.borrarProblema(borra_linia)) == -1) {
            // no existeix problema
            //System.out.println("El problema no existeix");
            return -1;
        }
        else System.out.println("El problema s'ha eliminat");
        return 0;
    }

    /**
     * Genera la matriu de peces de la posició inicial del problema
     * Pre: true
     * @return una matriu de peces de 8x8 amb les peces posades al seu lloc i peces nules als espais buits del problema
     * paràmetre implícit si la posició inicial no és null, altrament retorna null
     */
    public Peca[][] getPeces() {
        Peca mat[][] = new Peca[8][8];
        int k = 0;
        if (this.ini_pos == null) return null;
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
                            if (i == 6) mat[j][i] = new Peo(define.BLACK, true);
                            else mat[j][i] = new Peo(define.BLACK);
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
                            if (i == 1) mat[j][i] = new Peo(define.WHITE, true);
                            else mat[j][i] = new Peo(define.WHITE);
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

    /**
     * Consulta tots els problemes creats i guardats en el fitxer
     * Pre: true
     * @return un array d'strings de tots els problemes del fitxer on cada fila de la matriu és un problema i cada
     * columna un atribut (5), si el fitxer no existeix retorna null
     */
    public static String[][] consultarProblemes() {
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        String[][] res = gpp.llegirProblemes();
        if (res != null && res[0].length == 1) {
            System.out.println("No hi ha cap problema afegit");
            return null;
        }
        return res;
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

    /**
     * Consulta el problema amb l'id donat
     * Pre: true
     * @param id id del problema que es vol buscar
     * @param p paràmetre de sortida on es retorna el problema
     * @return 0 0 si ha retornat el problema pel paràmetre p, -1 si no s'ha trobat el problema amb aquest id o -2
     * si no hi ha cap problema creat (el fitxer no existeix)
     */
    public static int getProblemaId(int id, Problema p) { //comprobar problema.id != id
        GestorPersistenciaProblema gpp = new GestorPersistenciaProblema();
        String[] res = gpp.llegirProblemaId(id);
        if (res.length == 1) {
            System.out.println("No hi ha cap problema afegit");
            return -2;
        }
        else if (res.length == 2) {
            //no existeix el problema amb aquest id
            return -1;
        }
        else {
            p.id = id;
            p.jugades = Integer.parseInt(res[1]);
            p.primer = Integer.parseInt(res[2]);
            p.ini_pos = res[3];
            p.dificultat = Integer.parseInt(res[4]);
            p.idCreador = res[5];
            return 0;
        }
    }

    public boolean validar_problema2(int color_act, Taulell tau, int njug) { //private?
        //System.out.println("Actual " + njug);
        //tau.printTauler();
        if (njug == 0) return false;
        int color_cont;
        if (color_act == define.WHITE) color_cont = define.BLACK;
        else color_cont = define.WHITE;
        Posicion pec_pos[] = tau.getPosColor(color_act);
        Posicion Reid = tau.getReiPos(color_cont);
        boolean noEscac[][] = new boolean[pec_pos.length][];
        for (int i = 0; i < pec_pos.length; ++i) {
            Posicion mov[] = tau.todos_movimientos(pec_pos[i]);
            noEscac[i] = new boolean[mov.length];
            for (int k = 0; k < mov.length; ++k) {
                if (mov[k].x == Reid.x && mov[k].y == Reid.y) return true;
                Taulell tau2 = new Taulell(tau.getTauler(), true);
                if (tau2.mover_pieza(pec_pos[i], mov[k], color_act)) {
                    //System.out.println("Atac " + njug);
                    //tau2.printTauler();
                    if (tau2.escac_i_mat(color_cont) == 1) return true;
                    if (tau2.escac(Reid, Reid)) {
                        boolean def = true;
                        if (njug != 1) {
                            Posicion pec_cont[] = tau2.getPosColor(color_cont);
                            for (int j = 0; (j < pec_cont.length) && def; ++j) {
                                Posicion mov_cont[] = tau2.todos_movimientos(pec_cont[j]);
                                for (int l = 0; (l < mov_cont.length) && def; ++l) {
                                    Taulell tau3 = new Taulell(tau2.getTauler(), true);
                                    if (tau3.mover_pieza(pec_cont[j], mov_cont[l], color_cont)) {
                                        //System.out.println("Defensa " + njug);
                                        //tau3.printTauler();
                                        //if (tau3.escac_i_mat(color_act) == 1) def = false;
                                        if (!validar_problema2(color_act, tau3, njug - 1)) def = false;
                                    }
                                }
                            }
                        } else def = false;
                        if (def) return true;
                    }
                    else noEscac[i][k] = true;
                }
            }
        }
        for (int i = 0; i < pec_pos.length; ++i) {
            Posicion mov[] = tau.todos_movimientos(pec_pos[i]);
            for (int k = 0; k < mov.length; ++k) {
                if (noEscac[i][k]) {
                    Taulell tau2 = new Taulell(tau);
                    if (tau2.mover_pieza(pec_pos[i], mov[k], color_act)) {
                        boolean def = true;
                        if (njug != 1) {
                            Posicion pec_cont[] = tau2.getPosColor(color_cont);
                            for (int j = 0; (j < pec_cont.length) && def; ++j) {
                                Posicion mov_cont[] = tau2.todos_movimientos(pec_cont[j]);
                                for (int l = 0; (l < mov_cont.length) && def; ++l) {
                                    Taulell tau3 = new Taulell(tau2);
                                    if (tau3.mover_pieza(pec_cont[j], mov_cont[l], color_cont)) {
                                        //System.out.println("Defensa " + njug);
                                        //tau3.printTauler();
                                        //if (tau3.escac_i_mat(color_act) == 1) def = false;
                                        if (!validar_problema2(color_act, tau3, njug - 1)) def = false;
                                    }
                                }
                            }
                        } else def = false;
                        if (def) return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Valida si el problema paràmetre implícit té solució en njug jugades
     * Pre: njug >= 0
     * @param color_act color del jugador que va primer i ha de resoldre el problema (atacant)
     * @param tau situació actual de les peces
     * @param njug número de jugades màxim per resoldre el problema
     * @return true si el problema té solució en njug jugades o false altrament
     */
    public boolean validar_problema(int color_act, Taulell tau, int njug) { //private?
        //System.out.println("Actual " + njug);
        //tau.printTauler();
        if (njug == 0) return false;
        int color_cont;
        if (color_act == define.WHITE) color_cont = define.BLACK;
        else color_cont = define.WHITE;
        Posicion pec_pos[] = tau.getPosColor(color_act);
        for (int i = 0; i < pec_pos.length; ++i) {
            Posicion mov[] = tau.todos_movimientos(pec_pos[i]);
            for (int k = 0; k < mov.length; ++k) {
                if (((tau.getPecaPosicio(mov[k])).getTipus()).equals(define.REI)) return true;
                Taulell tau2 = new Taulell(tau.getTauler(),true);
                if (tau2.mover_pieza(pec_pos[i], mov[k], color_act)) {
                    //System.out.println("Atac " + njug);
                    //tau2.printTauler();
                    if (tau2.escac_i_mat(color_cont) == 1) return true;
                    boolean def = true;
                    if (njug != 1) {
                        Posicion pec_cont[] = tau2.getPosColor(color_cont);
                        for (int j = 0; (j < pec_cont.length) && def; ++j) {
                            Posicion mov_cont[] = tau2.todos_movimientos(pec_cont[j]);
                            for (int l = 0; (l < mov_cont.length) && def; ++l) {
                                Taulell tau3 = new Taulell(tau2.getTauler(), true);
                                if (tau3.mover_pieza(pec_cont[j], mov_cont[l], color_cont)) {
                                    //System.out.println("Defensa " + njug);
                                    //tau3.printTauler();
                                    //if (tau3.escac_i_mat(color_act) == 1) def = false;
                                    if (!validar_problema(color_act, tau3, njug - 1)) def = false;
                                }
                            }
                        }
                    } else def = false;
                    if (def) return true;
                }
            }
        }
        return false;
    }

    public static String graficToFEN(String tipus[][], int colors[][], int primer) {
        StringBuilder sbFEN = new StringBuilder();
        for (int i = 7; i >= 0; --i) {
            int cont = 0;
            for (int j = 0; j < 8; ++j) {
                if (tipus[j][i].equals(define.PECA_NULA)) cont++;
                else {
                    if (cont > 0) {
                        sbFEN.append(cont);
                        cont = 0;
                    }
                    switch (tipus[j][i]) {
                        case define.PEO:
                            if (colors[j][i] == define.WHITE) sbFEN.append("P");
                            else sbFEN.append("p");
                            break;
                        case define.TORRE:
                            if (colors[j][i] == define.WHITE) sbFEN.append("R");
                            else sbFEN.append("r");
                            break;
                        case define.CAVALL:
                            if (colors[j][i] == define.WHITE) sbFEN.append("N");
                            else sbFEN.append("n");
                            break;
                        case define.ALFIL:
                            if (colors[j][i] == define.WHITE) sbFEN.append("B");
                            else sbFEN.append("b");
                            break;
                        case define.REINA:
                            if (colors[j][i] == define.WHITE) sbFEN.append("Q");
                            else sbFEN.append("q");
                            break;
                        case define.REI:
                            if (colors[j][i] == define.WHITE) sbFEN.append("K");
                            else sbFEN.append("k");
                            break;
                        default:
                            break;
                    }
                }
            }
            if (cont > 0) sbFEN.append(cont);
            sbFEN.append("/");
        }
        if (primer == define.WHITE) sbFEN.append(" w");
        else sbFEN.append(" b");
        return sbFEN.toString();
    }

    /*
    public boolean validar_problema3(int color_act, Taulell tau, int njug, boolean atk) { //private?
        //Peca pec_mat[][] = this.getPeces();
        //Taulell tau = new Taulell(pec_mat);
        //System.out.println("Color " + String.valueOf(color_act) + " jugades " + String.valueOf(njug) + " torn " + String.valueOf(atk));
        if (njug == 0) return false;
        int color_cont;
        if (color_act == define.WHITE) color_cont = define.BLACK;
        else color_cont = define.WHITE;
        Posicion pec_pos[] = tau.getPosColor(color_act);
        for (int i = 0; i < pec_pos.length; ++i) {
            //System.out.println("Peça " + String.valueOf(i) + " pos " + String.valueOf(pec_pos[i].x) + " " + String.valueOf(pec_pos[i].y));
            // if peça nula
            //Posicion mov[] = pec_mat[i][j].movimientos_posibles(new Posicion(i, j));
            //Posicion pos_act = new Posicion(i, j);
            Posicion mov[] = tau.todos_movimientos(pec_pos[i]);
            if (mov != null) {
                for (int k = 0; k < mov.length; ++k) {
                    //System.out.println("Moviment " + String.valueOf(k) + " de " + String.valueOf(mov.length) + " color " + String.valueOf(color_act));
                    //System.out.println("mov " + String.valueOf(mov[k].x) + " " + String.valueOf(mov[k].y));
                    boolean ret;
                    Taulell tau2 = new Taulell(tau);
                    //System.out.println("Actual");
                    //tau.printTauler();
                    tau2.mover_pieza(pec_pos[i], mov[k], color_act);
                    //System.out.println("Nou");
                    //tau2.printTauler();
                    if (atk && tau.escac_i_mat(color_cont) == 1) return true;
                    if (atk) {
                        ret = this.validar_problema3(color_cont, tau2, njug, false);
                        if (ret) return true;
                    } else {
                        if (njug != 1)
                            ret = this.validar_problema3(color_cont, tau2, njug - 1, true); // if njug == 1 false
                        else ret = false;
                        if (!ret) return false;
                    }
                    //tau.mover_pieza(mov[k], pec_pos[i], color_act); // o new tau?
                    //System.out.println("desf " + String.valueOf(pec_pos[i].x) + " " + String.valueOf(pec_pos[i].y));
                }
            }
            //moure peça
        }
        if (!atk) return true;
        return false;
        /*if (true) { //
            System.out.println("El problema és vàlid");
            return true;
        }
        else {
            return false;
        }
    }*/

}