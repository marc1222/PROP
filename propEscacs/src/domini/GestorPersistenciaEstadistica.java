package gestioDades;

import java.io.*;
import java.util.ArrayList;

public class GestorPersistenciaEstadistica {
    // Fitxer ons es guarden les estadistiques
    static String fitxerStats = "./files/estadistiques.txt";

    /**
     *
     * @return Nom del fitxer ons es guarda les estadistiques
     */
    public static String getFitxerStats() {
        return fitxerStats;
    }

    /**
     *
     * @param nouFitxer nom de la ruta del nou fitxer on es guardin les estadistiques
     */
    public static void setFitxerStats(String nouFitxer) {
        fitxerStats = nouFitxer;
    }

    /**
     * S'obte el contingut complet del fitxer on es guarden les estadistiques
     * @return Conjunt de string de cada linea del fitxer
     */
    public static ArrayList<String> mostrarFitxerStats() {
        ArrayList<String> stats = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                stats.add(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix.");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            System.out.println("Error en tractar el fitxer.");
        }

        return stats;
    }

    public static void guardarTemps(String dades) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(fitxerStats,true);
            pw = new PrintWriter(fichero);
            // Afegeix a l'ultima linea
            pw.println(dades);
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            System.out.println("Eror en tractar el fitxer.");
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
                System.out.println("El fitxer no s'ha pogut tancar.");
            }
        }
    }

    public static void eliminarStatsUsuari(String usuari) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(fitxerStats));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            // Safegeix espais al String, perque si hi han usuari que
            // content el els mateixos caracteres dins del seu nom no
            // es remplacin
            usuari = " " + usuari + " ";
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            // Converteix tot el contingut en un sol String
            String inputStr = inputBuffer.toString();

            file.close();

            // Es remplaca totes les aparicions d'usuari al String
            inputStr = inputStr.replace(usuari, " Convidat ");

            // Escriu el nou String amb la modficacio en les lineas sobre el mateix fitxer
            FileOutputStream fileOut = new FileOutputStream(fitxerStats);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix.");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (Exception e) {
            System.out.println("Error en tractar el fitxer.");
        }
    }

    public static void eliminarStatsProblema(String problema) {
        try {
            // Per seguretat (no perdre les dades en cas d'algun imprevist)
            // es crea un fitxer temporal
            File inputFile = new File(fitxerStats);
            File tempFile = new File("./files/tmpEstadistiques.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = currentLine.split("\\s+");
                // Quan es troba l'usuari no s'escriu al fitxer temporal
                if(dades.length != 0) {
                    if(dades[0].equals(problema)) continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            // S'elimina el fitxer antic i es renombra el temporal
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
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

    public static ArrayList<String> buscar(String nom, int buscar) {
        ArrayList<String> marques = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Separa la linea adel fitxer per els espais
                String[] dades = line.split("\\s+");
                // Segons el valor de buscar, busca en la primer columna del
                // fitxer o en la segona, i compara amb el nom donat
                if(dades[buscar].equals(nom)) {
                    // Nombre de mat a enter
                    int mat = Integer.parseInt(dades[2]);
                    // Milisegons a enter
                    long temps = Long.parseLong(dades[3]);
                    marques.add(dades[0] + " " + dades[1] + " " + mat + " " + temps);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix.");
        }
        catch (IOException e) {
            System.out.println("Error en la entrada i sortida da dades.");
        }
        catch (NumberFormatException e) {
            System.out.println("Error en el format de nombres.");
        }

        return marques;
    }
}