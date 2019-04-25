import java.util.ArrayList;

class StubEstadistica {
    public static String getFitxerStats() {
        return "Nom fitxer";
    }

    public static void setFitxerStats(String nouFitxer) {
        System.out.println("Nou fitxer d'estadistiques.");
    }

    public static ArrayList<String> mostrarFitxerStats() {
        ArrayList<String> al = new ArrayList<String >();
        return al;
    }

    public static void guardarTemps(String problema, String usuari, String movimentsMat, String temps) {
        System.out.println("Problema guardat.");
    }

    public static ArrayList<String> estadistiquesProblema(String problema) {
        ArrayList<String> al = new ArrayList<String >();
        return al;
    }


    public static ArrayList<String> estadistiquesUsuari(String usuari) {
        ArrayList<String> al = new ArrayList<String >();
        return al;
    }

    public static void eliminarStatsUsuari(String usuari) {
        System.out.println("Usuari eliminat de les estadistiques.");
    }

    public static void eliminarStatsProblema(String problema) {
        System.out.println("Problema eliminat de les estadistiques.");
    }
}

