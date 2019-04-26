import java.util.ArrayList;
import java.util.Scanner;

public class DriverEstadistica {
    // Fitxer ons es guarden les estadistiques
    private static String fitxerProvesStats = "./files/testEstadistiques.txt";

    public static void main(String[] args) {
        Estadistica.setFitxerStats(fitxerProvesStats);
        Scanner sc = new Scanner(System.in);

        String opcio;
        do {
            System.out.println("\n----- OPCIONS ESTADISTIQUES -----\n" +
                    "1 - Mostrar contingut fitxer\n" +
                    "2 - Guardar temps\n" +
                    "3 - Consultar estadistiques problema\n" +
                    "4 - Consultar estadistiques usuari\n"+
                    "5 - Eliminar usuari\n" +
                    "6 - Eliminar problema\n" +
                    "0 - Sortir");

            opcio = sc.nextLine();
            switch (opcio) {
                case "1": {
                    ArrayList<String> stats = Estadistica.mostrarFitxerStats();

                    if (stats.isEmpty()) {
                        System.out.println("No hi ha registres.");
                    }
                    else {
                        System.out.println("Problema Jugador  Mat  Temps");
                    }
                    for(String marca : stats) {
                        System.out.println(marca);
                    }
                    break;
                }
                case "2": {
                    String problema, usuari, mat, temps;

                    System.out.println("Nom problema: ");
                    problema = sc.nextLine();

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    System.out.println("Mat: ");
                    mat = sc.nextLine();

                    System.out.println("Temps (en milisegons): ");
                    temps = sc.nextLine();

                    Estadistica.guardarTemps(problema, usuari, mat, temps);
                    break;
                }
                case "3": {
                    String problema;

                    System.out.println("Nom problema: ");
                    problema = sc.nextLine();

                    ArrayList<String> statsProblema = Estadistica.estadistiquesProblema(problema);
                    if (statsProblema.isEmpty()) {
                        System.out.println("No hi han registres del problema");
                    }
                    else {
                        System.out.println("Jugador  Mat  Temps");
                    }

                    for(String marca : statsProblema) {
                        System.out.println(marca);
                    }

                    break;
                }
                case "4": {
                    String usuari;

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    Estadistica.estadistiquesUsuari(usuari);

                    ArrayList<String> statsUsuari = Estadistica.estadistiquesUsuari(usuari);

                    if (statsUsuari.isEmpty()) {
                        System.out.println("No hi han registres de l'usuari");
                    }
                    else {
                        System.out.println("Problema  Mat  Temps");
                    }
                    for(String marca : statsUsuari) {
                        System.out.println(marca);
                    }
                    break;
                }
                case "5": {
                    String usuari;

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    Estadistica.eliminarStatsUsuari(usuari);
                    break;
                }
                case "6": {
                    String problema;

                    System.out.println("Nom problema: ");
                    problema = sc.nextLine();
                    System.out.println("Eliminant problema...");
                    Estadistica.eliminarStatsProblema(problema);

                    break;
                }
                case "0": {
                    System.out.println("Sortint...");
                    break;
                }
                default: {
                    System.out.println("Opció invalida.");
                }
            }
        } while (opcio != "0");
    }
}
