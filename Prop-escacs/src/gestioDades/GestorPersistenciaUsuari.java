package gestioDades;

import java.io.*;
import java.util.ArrayList;

public class GestorPersistenciaUsuari {
    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./files/usuaris.txt";

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

    public static boolean registrar(String nomUsuari, String contrasenya) {
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
        String[] dades = {nomUsuari, contrasenya};

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
        System.out.println("T'has registrat correctament.\n");
        return  true;
    }

    public static boolean buscarUsuari(String nomUsuari, String contrasenya) {
        // Si nom i contrasenya coherents comprobar les dades en el fitxer
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line;
            // Recorre fitxer per cada linia
            while ((line = br.readLine()) != null) {
                // Separa nom i contrasenya
                String[] dades = line.split("\\s+");
                if(dades[0].equals(nomUsuari)) {
                    if (dades[1].equals(contrasenya)) {
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
        System.out.println("domini.Usuari no existeix.\n");
        return false;
    }

    /**
     * pre: usuari es troba al fitxer d'usuaris
     * post: usuari ja no es troba en el fitxer
     * @param usuari Nom de l'usuari
     */
    public static boolean eliminarUsuari(String usuari) {
        boolean eliminat = false;
        /*System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
                */
        try {
            // Per seguretat (no perdre les dades en cas d'algun imprevist)
            // es crea un fitxer temporal
            File inputFile = new File(fitxerUsuaris);
            File tempFile = new File("./files/tmpUsuaris.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                //System.out.println(currentLine);
                // Separar nom i contrasenya per espai
                String[] dades = currentLine.split("\\s+");
                // Quan es troba l'usuari no s'escriu al fitxer temporal
                if(dades.length != 0) {
                    if(dades[0].equals(usuari)) continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            /*
            if (tempFile.exists()) {
                System.out.println("Fichero tmpUsuaris existe");
            }
            */

            // S'elimina el fitxer antic i es renombra el temporal
            if (inputFile.delete()) {
                eliminat = tempFile.renameTo(inputFile);
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

        return eliminat;
    }

    public static ArrayList<String> totsUsuaris() {
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

        return usuaris;
    }

    /**
     * Mostra el contingut complet del fitxer on es guarda els usuaris
     */
    public static void mostrarFitxerUsuaris() {
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