import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

import java.util.Map;
import java.util.TreeMap;

/**
 * Classe que guarda y devuelve las estadisticas de las partidas
 * @author Marian Dumitru Danci
 */
public class Estadistica {
    // Fitxer ons es guarden les estadistiques
    static String fitxerStats = "./files/estadistiques.txt";

    // pre: problema i usuari existeix
    // psot: es guarda una la linea al fitxer (problema usuari temps)

    /**
     *
     * @param problema
     * @param usuari
     * @param movimentsMat
     * @param temps en milisegonds
     */
    public void guardarTiempo(String problema, String usuari, String movimentsMat, String temps) {
        input_output IO = new input_output();
        String[] dades = {problema, usuari, movimentsMat, temps};
        IO.write(fitxerStats, dades);
    }

    // pre: problema existeix
    // post: retorna tots els usuaris que han resolt el problema amb el seu temps

    /**
     *
     * @param problema
     */
    public void estadistiquesProblema(String problema) {
        Map<Long, String> estProblema = new TreeMap<Long, String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line;
            System.out.println("Jugador Mat Temps");
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[0].equals(problema)) {
                    Long temps = Long.parseLong(dades[3]);
                    String probMat = dades[1] + " " + dades[2];
                    estProblema.put(temps, probMat);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        // Iteracio sobre els resultats ordenats
        for (Map.Entry<Long, String> entry : estProblema.entrySet()) {
            Long temps = entry.getKey();
            int sec = (int) (temps / 1000) % 60 ;
            int min = (int) (temps / (1000*60));
            System.out.println(entry.getValue() + " " + min + ":" + sec);
        }
    }

    // pre: usuari existeix
    // post: retorna tots els problemes resolts amb el seu temps

    /**
     *
     * @param usuari
     */
    public void estadistiquesUsuari(String usuari) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line;
            System.out.println("Problema Mat Temps");
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[1].equals(usuari)) {
                    long temps = Long.parseLong(dades[3]);
                    int sec = (int) (temps / 1000) % 60 ;
                    int min = (int) (temps / (1000*60));
                    String probMat = dades[1] + " " + dades[2];
                    System.out.println(probMat + " " + min + ":" + sec);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminatStatsUsuari(String usuari) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(fitxerStats));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

            //System.out.println(inputStr);

            inputStr = inputStr.replace(usuari, "Anònim");

            //System.out.println("----------------------------------\n"  + inputStr);

            // escriure el nou String amb la modficacio en les lineas sobre el mateix fitxer
            FileOutputStream fileOut = new FileOutputStream(fitxerStats);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}