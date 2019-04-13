import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que trata les estadistiques de les partides
 * @author Marian Dumitru Danci
 */
public class Estadistica {
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
     * Mostra el contingut complet del fitxer
     */
    public static void mostrarsetFitxerStats() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerStats))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Problema en llegir el fitxer.");
        }
    }

    /**
     * pre: problema i usuari existeix
     * psot: es guarda una la linea al fitxer (problema usuari temps)
     * @param problema
     * @param usuari
     * @param movimentsMat
     * @param temps en milisegonds
     */


    /**
     * Es guarda a l'última line del fitxer les dades que es pasen
     * @param problema
     * @param usuari
     * @param movimentsMat
     * @param temps
     */
    public static void guardarTemps(String problema, String usuari, String movimentsMat, String temps) {
        //input_output IO = new input_output();
        String[] dades = {problema, usuari, movimentsMat, temps};
        //IO.write(fitxerStats, dades);

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(fitxerStats,true);
            pw = new PrintWriter(fichero);
            String aux = String.join(" ",dades);
            pw.println(aux);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    // pre: usuari existeix
    // post: retorna tots els problemes resolts amb el seu temps

    /**
     * Mostra tots els usuaris que han resolt el problema, ordenats primer
     * per el numero de moviments que ha fet mat i segon per el temps
     * @param problema
     */
    public static void estadistiquesProblema(String problema) {
        System.out.println("Jugador Mat Temps");
        List<Marca> marquesOrdenades = estadistiquesOrdenades(problema, 0);
        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            System.out.println(m.getUsuari() + "  " + m.getMat() + "  " + temps);
        }
        if (marquesOrdenades.isEmpty()) {
            System.out.println("No hi han registres del problema");
        }
    }

    /**
     * Mostra tots els problemes que han resolt l'usuari, ordenats primer
     * per el numero de moviments que ha fet mat i segon per el temps
     * @param usuari
     */
    public static void estadistiquesUsuari(String usuari) {
        System.out.println("Problema Mat Temps");
        List<Marca> marquesOrdenades = estadistiquesOrdenades(usuari, 1);
        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            System.out.println(m.getProblema() + "         " + m.getMat() + "  " + temps);
        }
        if (marquesOrdenades.isEmpty()) {
            System.out.println("No hi han registres de l'usuari");
        }
    }

    /**
     * Posa com a 'Anónim' totes les aparicions de l'usuari en el fitxer
     * @param usuari
     */
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



    /**
     * Retorna una llista ordenada del problema o l'usuari
     * @param nom nom de l'usuari o el problema
     * @param buscar 0 problema, 1 usuari
     * @return
     */
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

    /**
     * Passa els milisegons a minuts y segons
     * @param milis
     * @return
     */
    private static String milisATemps(long milis) {
        int sec, min;
        String temps;

        sec = (int) (milis / 1000) % 60 ;
        min = (int) (milis / (1000*60));
        temps = min + "min " + sec + "s";

        return temps;
    }

    /**
     * Classe que guarda les dades de una linea del fitxer
     */
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
