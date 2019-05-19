package domini;

import gestioDades.GestorPersistenciaEstadistica;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe domini.Estadistica
 * @author Marian Dumitru Danci
 */
public class Estadistica {
    /**
     * Es guarda a l'última line del fitxer les dades que es passen
     * @param problema nom del problema resolt
     * @param usuari nom de l'usuari que l'ha resolt
     * @param movimentsMat nombre de moviments en fer el mat
     * @param temps en milisegonds
     */
    public static void guardarTemps(String problema, String usuari, String movimentsMat, String temps) {
        String[] dades = {problema, usuari, movimentsMat, temps};
        // Junta els parametres en un String i separar per un espai
        String aux = String.join(" ",dades);
        GestorPersistenciaEstadistica.guardarTemps(aux);
    }

    /**
     * pre: problema existeix
     * post: totes les aparicions del problema en les estadistiques, ordenades
     *       primer segons el numero de moviments en fer mat i segon pel temps.
     * @param problema nom del problema que es vol consultar.
     * @return Conjunt de string de cada aparicio del problema
     */
    public static ArrayList<String> estadistiquesProblema(String problema) {
        List<Marca> marquesOrdenades = estadistiquesOrdenades(problema, 0);
        ArrayList<String> statsProblema = new ArrayList<String>();

        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            String marca = m.getUsuari() + "  " + m.getMat() + "  " + temps;
            statsProblema.add(marca);
        }
        return statsProblema;
    }

    /**
     * pre: usuari existeix
     * post: totes les aparicions de l'usuari en les estadistiques, ordenades
     *       primer segons el numero de moviments en fer mat i segon pel temps.
     * @param usuari nom de l'usuari que es vol consultar.
     * @return Conjunt de string de cada aparicio de l'usuari
     */
    public static ArrayList<String> estadistiquesUsuari(String usuari) {
        List<Marca> marquesOrdenades = estadistiquesOrdenades(usuari, 1);
        ArrayList<String> statsUsuari = new ArrayList<String>();

        for(Marca m : marquesOrdenades) {
            String temps = milisATemps(m.getTemps());
            String marca = m.getProblema() + "  " + m.getMat() + "  " + temps;
            statsUsuari.add(marca);
        }
        return statsUsuari;
    }

    /**
     * pre: usuari existeix
     * post: les aparicions de l'usuari a les estadistiques passen a ser de
     *       l'usuari general 'Convidat'
     * @param usuari usuari que es vol remplacar
     */
    public static void eliminarStatsUsuari(String usuari) {
        GestorPersistenciaEstadistica.eliminarStatsUsuari(usuari);
    }

    /**
     * pre: problema existeix
     * post: s'elimina totes els registres del problema en el fitxer
     * @param problema problema a eliminar les estadistiques
     */
    public static void eliminarStatsProblema(String problema) {
        GestorPersistenciaEstadistica.eliminarStatsProblema(problema);
    }

    /**
     * Retorna una llista ordenada per dos valors, el numero en que s'ha fet
     * mat i el temps en milisegons en que s'ha fet.
     * @param nom nom de l'usuari o el problema a buscar
     * @param buscar 0 retorna marques d'un problema
     *               1 retorna marques d'un usuari
     * @return retorna la llista amb les maqrques ordenades
     */
    private static List<Marca> estadistiquesOrdenades(String nom, int buscar) {
        /*
        ArrayList<Marca> marques = new ArrayList<>();
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
                    marques.add(new Marca(dades[0], dades[1], mat, temps));
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
        */
        ArrayList<String> result = GestorPersistenciaEstadistica.buscar(nom, buscar);
        ArrayList<Marca> marques = new ArrayList<>();
        for (String dada : result) {
            // Separa la linea adel fitxer per els espais
            String[] dades = dada.split("\\s+");

            // Nombre de mat a enter
            int mat;
            // Milisegons a enter
            long temps;
            try {
                mat = Integer.parseInt(dades[2]);
                temps = Long.parseLong(dades[3]);
            }
            catch (NumberFormatException e)
            {
                mat = 0;
                temps = 0;
            }
            marques.add(new Marca(dades[0], dades[1], mat, temps));
        }


        // S'ordena les marques pel nombre de mat
        Comparator<Marca> comparador = Comparator.comparing(m -> m.getMat());
        // Sense alterar l'ordenacio de marques per mat, s'ordena les marques
        // pel temps
        comparador = comparador.thenComparing(Comparator.comparing(m -> m.getTemps()));
        // S'aplica el comparador a les marques obtingudes i es converteix en una llista
        List<Marca> marquesOrdenades = marques.stream().sorted(comparador).collect(Collectors.toList());
        return marquesOrdenades;
    }

    /**
     * Converteix els milisegons a minuts i segons
     * @param milis Milisegons
     * @return String amb minuts i segons
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
     * Classe que representa un fila del fitxer d'estadisiques
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
