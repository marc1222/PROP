package Test;

class StubEstadistica {

    StubEstadistica() {
        System.out.println("Instancia estadistica");
    }

    public static void guardarTiempo(String problema, String usuari, String movimentsMat, String temps) {
        System.out.println("Guarda estadistica, en forma: " + problema + " " +
                usuari + " " + movimentsMat + " " + temps);
    }

    public static void estadistiquesProblema(String problema) {
        System.out.println("Estadistiques de " + problema + " (Usuari Mat Temps)");
    }

    public static void estadistiquesUsuari(String usuari) {
        System.out.println("Estadistiques de " + usuari + " (Problema Mat Temps)");
    }

    public static void eliminatStatsUsuari(String usuari) {
        System.out.println("Es remplaça " + usuari + " per Anònim");
    }
}
