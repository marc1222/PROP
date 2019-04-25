import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

/**
 * Classe que tracta
 * @author Marian Dumitru Danci
 */
public class Usuari extends Jugador{
    private String idUsuari;
    private int color;
    private static final int tipus = define.USER;

    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./files/usuaris.txt";

    Scanner sc = new Scanner(System.in);

    /**
     * Constructora per defecte, crea un usuari Convidat amb color null.
     */
    public Usuari () {
        idUsuari = "Convidat";
        color = define.NULL_COLOR;
    }

    /**
     * Constructora que crea un usuari 'Convidat' amb un color determinat
     * @param color Color de les peces de l'usuari
     */
    public Usuari (int color) {
        idUsuari = "Convidat";
        this.color = color;
    }

    /**
     *
     * @return Nom del fitxer os es guarden els usuaris
     */
    public static String getFitxerUsuaris() {
        return fitxerUsuaris;
    }

    /**
     * Canvia el fitxer os es guarden els usuaris
     * @param fitxer Nom del nou fitxer os es guarden els usuaris
     */
    public static void setFitxerUsuaris(String fitxer) {
        fitxerUsuaris = fitxer;
    }

    /**
     *
     * @return Retorna el tipus de jugador que es l'usuari
     */
    public int getTipus() {
        return this.tipus;
    }

    /**
     *
     * @return Nom de l'usuari
     */
    public String getNom() {
        return idUsuari;
    }

    /**
     *
     * @param nom Nom a l'usuari
     */
    public void setNom(String nom) {
        idUsuari = nom;
    }

    /**
     *
     * @return Color de les peces de l'usuari
     */
    public int getColor() {
        return this.color;
    }

    /**
     *
     * @param color Color de les peces
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Segons l'usuari escollit demana la contrasenya i comproba que tot
     * es correcte
     * @param nomUsuari Nom d'usuari
     * @return Retorna true si l'inici de sessio es correcte
     */
    public boolean iniciarSessio(String nomUsuari) {
        String contrasenya;

        System.out.println("Contrasenya:");
        contrasenya = sc.nextLine();

        // Comproba que el tipus de dades es correcte
        if(nomUsuari != null && !nomUsuari.isEmpty()) {
            if (contrasenya == null && contrasenya.isEmpty()) {
                System.out.println("Contrasenya no vàlida.\n");
                return false;
            }
        }
        else {
            System.out.println("Usuari no vàlida.\n");
            return false;
        }

        // Si nom i contrasenya coherents comprobar les dades en el fitxer
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line;
            // Recorre fitxer per cada linia
            while ((line = br.readLine()) != null) {
                // Separa nom i contrasenya
                String[] dades = line.split("\\s+");
                if(dades[0].equals(nomUsuari)) {
                    if (dades[1].equals(contrasenya)) {
                        idUsuari =  nomUsuari;
                        return true;
                    }
                    else {
                        System.out.println("Contraseya incorrecta.\n");
                        return false;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        }
        catch (IOException e) {
            System.out.println("El format d'entrada o sortida no és correcte");
        }
        catch (Exception e) {

        }
        System.out.println("Usuari no existeix.\n");
        return false;
    }

    /**
     * Demana les dades necesaries per registrar un usuari, so tot es correcte
     * guarda les dades al fitxer
     * @return Retorna true si el registre s'ha completat amb exit
     */
    public boolean registrar() {
        String nomUsuari, contrasenya1, contrasenya2;
        System.out.println("Usuari:");
        nomUsuari = sc.nextLine();

        System.out.println("Contrasenya:");
        contrasenya1 = sc.nextLine();

        System.out.println("Repeteix contrasenya:");
        contrasenya2 = sc.nextLine();

        if(nomUsuari == null || nomUsuari.isEmpty() || nomUsuari.equals("Convidat")) {
            System.out.println("Usuari no vàlida.\n");
        }
        else if(contrasenya1 == null || contrasenya1.isEmpty() ||
                contrasenya2 == null || contrasenya2.isEmpty()) {
            System.out.println("Contrasenya no vàlida.\n");
        }
        else if (!contrasenya1.equals(contrasenya2)) {
            System.out.println("La contrasenya no concideix.\n");
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
                String line;

                // Recorre fitxer per cada linia
                while ((line = br.readLine()) != null) {
                    // Separa nom i contrasenya
                    String[] dades = line.split("\\s+");
                    if(dades[0].equals(nomUsuari)) {
                        if (dades[0].equals(nomUsuari)) {
                            System.out.println("L'usuari ja existeix.\n");
                            return false;
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("El fitxer no existeix");
            }
            catch (IOException e) {
                System.out.println("Error en la entrada i sortida da dades.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Registre correcte i es guarda usuari a fitxer
            String[] dades = {nomUsuari, contrasenya2};

            FileWriter fichero = null;
            PrintWriter pw = null;
            try {
                fichero = new FileWriter(fitxerUsuaris,true);
                pw = new PrintWriter(fichero);
                // Junta els parametres en un String i separar per un espai
                String aux = String.join(" ",dades);
                // Afegeix a l'ultima linea
                pw.println(aux);
            }
            catch (FileNotFoundException e) {
                System.out.println("El fitxer no existeix");
            }
            catch (IOException e) {
                System.out.println("Error en la entrada i sortida da dades.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (null != fichero) fichero.close();
                }
                catch (FileNotFoundException e) {
                    System.out.println("El fitxer no existeix");
                }
                catch (IOException e) {
                    System.out.println("Error en la entrada i sortida da dades.");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Si l'usuari s'ha registrar correctament se li dona el nom
            // d'usuari que ha fet el registre
            idUsuari = nomUsuari;
            System.out.println("T'has registrat correctament.\n");
            return  true;
        }
        return false;
    }

    /**
     * D'ona de baixa el propi usuari eliminatlo del fitxer i pasa a
     * ser un usuari 'Convidat'
     * @return Retorna true si l'usuari s'ha donat de baixa correctament
     */
    public boolean baixa() {
        int opcio;
        do {
            System.out.println("Estàs segur que et vols donar de baixa?\n" +
                    "    1 - Si\n" +
                    "    2 - No\n");

            opcio = sc.nextInt();
            switch (opcio) {
                case 1: {
                    eliminarUsuari(idUsuari);
                    idUsuari = "Convidat";
                    System.out.println("T'has donat de baixa correctament.\n");
                    break;
                }
                case 2: {
                    System.out.println("S'ha cancelat la baixa.\n");
                    break;
                }
                default: {
                    System.out.println("Opcio invalida.\n");
                }
            }
        } while(opcio != 1 && opcio != 2);

        if (opcio == 1) return true;
        return false;
    }

    /*
     * Elimina el registre de l'usuari al fitxer
     * @param usuari Nom de l'usuari
     */
    private void eliminarUsuari(String usuari) {
        try {
            // Per seguretat (no perdre les dades en cas d'algun imprevist)
            // es crea un fitxer temporal
            File tempFile = File.createTempFile("./files/tmpUsuaris.txt", "");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            BufferedReader reader = new BufferedReader(new FileReader(fitxerUsuaris));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = currentLine.split("\\s+");
                // Quan es troba l'usuari no s'escriu al fitxer temporal
                if(dades[0].equals(usuari)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            File oldFile = new File(fitxerUsuaris);
            // S'elimina el fitxer antic i es renombra el temporal
            if (oldFile.delete()) {
                tempFile.renameTo(oldFile);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Conjunt de Strings amb tots els usuaris registrats
     */
    public static String[] totsUsuaris() {
        ArrayList<String> usuaris = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line;
            // Recorrer fitxer per cada linea
            while ((line = br.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = line.split("\\s+");
                usuaris.add(dades[0]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String[] totsUsuaris = new String[usuaris.size()];
        totsUsuaris = usuaris.toArray(totsUsuaris);
        return totsUsuaris;
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return
     */
    public long moviment(Posicion origen, Posicion desti) {
        // Inici del cronometre
        long iniCrono = System.currentTimeMillis();

        String posPeca, destiPeca;
        boolean entradaValida;
        do {
            entradaValida = true;

            System.out.println("-- MOVIMENT --\n" +
                    "Selecciona la peça que vols moure ('x y'):\n");
            posPeca = sc.nextLine();
            String[] posIni = posPeca.split("\\s+");

            try {
                origen.x = Integer.parseInt(posIni[0]);
                origen.y = Integer.parseInt(posIni[1]);
            }
            catch (NumberFormatException e) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }
            if(origen.x > 7 || origen.x < 0 ||
                    origen.y > 7 || origen.y < 0) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }

            if (entradaValida) {
                System.out.println("Selecciona la casella de destí ('x y'):\n");
                destiPeca = sc.nextLine();
                String[] posDesti = destiPeca.split("\\s+");
                try {
                    desti.x = Integer.parseInt(posDesti[0]);
                    desti.y = Integer.parseInt(posDesti[1]);
                }
                catch (NumberFormatException e) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }

                if(desti.x > 7 || desti.x < 0 ||
                        desti.y > 7 || desti.y < 0) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }
            }
        } while(!entradaValida);

        long tempsMoviment = System.currentTimeMillis() - iniCrono;;
        return tempsMoviment;
    }

    /**
     * Mostra el contingut complet del fitxer on es guarda els usuaris
     */
    public static void mostrarsetFitxerUsuaris() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}