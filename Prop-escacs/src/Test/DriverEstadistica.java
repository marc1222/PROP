public class DriverEstadistica {
    public static void main(String[] args) {
        System.out.println("\n----- RUTA FITXER -----");
        String fitxer = Estadistica.getFitxerStats();
        System.out.println(fitxer);


        System.out.println("\n----- ASSIGNAR NOU FITXER -----");
        System.out.println("---- ORIGINAL:");
        Estadistica.mostrarsetFitxerStats();
        Estadistica.setFitxerStats("./files/provesEstadistiques.txt");
        String nouFitxer = Estadistica.getFitxerStats();
        System.out.println("\n---- NOU:");
        System.out.println("Ruta nou fitxer: " + nouFitxer);


        System.out.println("\n----- GUARDAR TEMPS -----");
        System.out.println("---- ORIGINAL:");
        Estadistica.mostrarsetFitxerStats();
        Estadistica.guardarTemps("problema1", "usuari5", "3" , "13552");
        System.out.println("\n---- NOU:");
        Estadistica.mostrarsetFitxerStats();


        System.out.println("\n----- ESTADISTIQUES PROBLEMA 'problema1' -----");
        System.out.println("---- ORIGINAL:");
        Estadistica.mostrarsetFitxerStats();
        System.out.println("\n---- RESULTAT:");
        Estadistica.estadistiquesProblema("problema1");


        System.out.println("\n----- ESTADISTIQUES USUARI 'usuari3' -----");
        System.out.println("---- ORIGINAL:");
        Estadistica.mostrarsetFitxerStats();
        System.out.println("\n---- RESULTAT:");
        Estadistica.estadistiquesUsuari("usuari3");


        System.out.println("\n----- ELIMINAR USUARI 'usuari1' -----");
        System.out.println("---- ORIGINAL:");
        Estadistica.mostrarsetFitxerStats();
        Estadistica.eliminatStatsUsuari("usuari1");
        System.out.println("\n---- NOU:");
        Estadistica.mostrarsetFitxerStats();
    }
}
