import java.util.ArrayList;
import java.util.Scanner;

public class DriverEstadistica {
    public static void main(String[] args) {
		// Fitxer ons es guarden les estadistiques
		String fitxerProvesStats = "./testEstadistiques.txt";
		
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
					System.out.println("\n-- Mostrar contingut --\n");
                    ArrayList<String> stats = Estadistica.mostrarFitxerStats();

                    if (stats.isEmpty()) {
                        System.out.println("No hi ha registres.");
                    }
                    else {
                        System.out.println("Problema Jugador Mat Temps");
                    }
                    for(String marca : stats) {
                        System.out.println(marca);
                    }
                    break;
                }
                case "2": {
					System.out.println("\n-- Guardar temps --\n");
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
					System.out.println("\n-- Consultar estadistiques problema --\n");
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
					System.out.println("\n-- Consultar estadistiques usuari --\n");
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
					System.out.println("\n-- Eliminar usuari --\n");
                    String usuari;

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    Estadistica.eliminarStatsUsuari(usuari);
                    break;
                }
                case "6": {
					System.out.println("\n-- Eliminar problema --\n");
                    String problema;

                    System.out.println("Nom problema: ");
                    problema = sc.nextLine();
                    System.out.println("Eliminant problema...");
                    Estadistica.eliminarStatsProblema(problema);

                    break;
                }
                case "0": {
                    System.out.println("\n-- Sortir --\n");
                    break;
                }
                default: {
                    System.out.println("\n-- Opcio invalida --\n");
                }
            }
        } while (!opcio.equals("0"));
    }
}
