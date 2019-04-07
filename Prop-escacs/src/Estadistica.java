import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que guarda y devuelve las estadisticas de las partidas
 * @author Marian Dumitru Danci
 */
public class Estadistica {
    // Fitxer ons es guarden les estadistiques
    static String fitxerStats = "./files/estadistiques.txt";

    /**
     * pre: problema i usuari existeix
     * psot: es guarda una la linea al fitxer (problema usuari temps)
     * @param problema
     * @param usuari
     * @param movimentsMat
     * @param temps en milisegonds
     */
    public static void guardarTiempo(String problema, String usuari, String movimentsMat, String temps) {
        input_output IO = new input_output();
        String[] dades = {problema, usuari, movimentsMat, temps};
        IO.write(fitxerStats, dades);
    }

    // pre: problema existeix
    // post: retorna tots els usuaris que han resolt el problema amb el seu temps
    // buscar: 0 problema, 1 usuari
    private static List<Marca> estadistiquesOrdenades(String nom, int buscar) {
        ArrayList<Marca> marques = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[buscar].equals(nom)) {
                    int mat = Integer.parseInt(dades[2]);
                    long temps = Long.parseLong(dades[3]);
                    marques.add(new Marca(dades[0], dades[1], mat, temps));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        Comparator<Marca> comparador = Comparator.comparing(m -> m.getMat());
        comparador = comparador.thenComparing(Comparator.comparing(m -> m.getTemps()));
        List<Marca> marquesOrdenades = marques.stream().sorted(comparador).collect(Collectors.toList());
        return marquesOrdenades;
    }

    // pre: usuari existeix
    // post: retorna tots els problemes resolts amb el seu temps
    public static void estadistiquesProblema(String problema) {
        System.out.println("Jugador Mat Temps");
        List<Marca> marquesOrdenades = estadistiquesOrdenades(problema, 0);
        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            System.out.println(m.getUsuari() + " " + m.getMat() + " " + temps);
        }
    }

    public static void estadistiquesUsuari(String usuari) {
        System.out.println("Problema Mat Temps");
        List<Marca> marquesOrdenades = estadistiquesOrdenades(usuari, 1);
        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            System.out.println(m.getProblema() + " " + m.getMat() + " " + temps);
        }
    }

    public static void eliminatStatsUsuari(String usuari) {
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

    private static String milisATemps(long milis) {
        int sec, min;
        String temps;

        sec = (int) (milis / 1000) % 60 ;
        min = (int) (milis / (1000*60));
        temps = min + ":" + sec;

        return temps;
    }

    private static class Marca {
        private String problema;
        private String usuari;
        private int mat;
        private long temps;

        Marca(String problema, String usuari, int mat, long temps) {
            this.problema = problema;
            this.usuari = usuari;
            this.mat = mat;
            this.temps = temps;
        }

        public String getProblema() {
            return problema;
        }

        public String getUsuari() {
            return usuari;
        }

        public int getMat() {
            return mat;
        }

        public long getTemps() {
            return temps;
        }
    }
}