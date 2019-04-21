import java.util.Scanner;

public class DriverEstadistica {
    // Fitxer ons es guarden les estadistiques
    private static String fitxerProvesStats = "./files/testEstadistiques.txt";

    public static void main(String[] args) {
        Estadistica.setFitxerStats(fitxerProvesStats);
        Scanner sc = new Scanner(System.in);

        int opcio;
        do {
            System.out.println("\n----- OPCIONS ESTADISTIQUES -----\n" +
                    "1 - Mostrar contingut fitxer\n" +
                    "2 - Guardar temps\n" +
                    "3 - Consultar estadistiques problema\n" +
                    "4 - Consultar estadistiques usuari\n"+
                    "5 - Eliminar usuari\n" +
                    "6 - Mostrar ruta fitxer\n" +
                    "7 - Canviar ruta fitxer\n" +
                    "0 - Sortir");

            opcio = sc.nextInt();
            // Salta la seguent linea
            sc.nextLine();
            switch (opcio) {
                case 1: {
                    Estadistica.mostrarsetFitxerStats();
                    break;
                }
                case 2: {
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
                case 3: {
                    String problema;

                    System.out.println("Nom problema: ");
                    problema = sc.nextLine();

                    Estadistica.estadistiquesProblema(problema);
                    break;
                }
                case 4: {
                    String usuari;

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    Estadistica.estadistiquesUsuari(usuari);
                    break;
                }
                case 5: {
                    String usuari;

                    System.out.println("Nom usuari: ");
                    usuari = sc.nextLine();

                    Estadistica.eliminatStatsUsuari(usuari);
                    break;
                }
                case 6: {
                    String fitxer = Estadistica.getFitxerStats();
                    System.out.println(fitxer);
                    break;
                }
                case 7: {
                    String fitxer;

                    System.out.println("Ruta fitxer: ");
                    fitxer = sc.nextLine();

                    Estadistica.setFitxerStats(fitxer);
                    break;
                }
                case 0: {
                    System.out.println("Sortint...");
                    break;
                }
                default: {
                    System.out.println("Opció invalida.");
                }
            }
        } while (opcio != 8);
    }
}
