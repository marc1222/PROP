import java.util.Scanner;

public class DriverUsuari {
    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./testUsuaris.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuari.setFitxerUsuaris(fitxerUsuaris);

        String opcio;
        do {
            System.out.println("\n----- OPCIONS INICI -----\n" +
                    "1 - Mostrar contingut fitxer\n" +
                    "2 - Registrar-se\n" +
                    "3 - Iniciar sessió\n" +
                    "0 - Sortir");

            opcio = sc.nextLine();

            Usuari usuari = new Usuari();
            boolean sessioIniciada = false;
            switch (opcio) {
                case "1": {
					System.out.println("\n-- Mostrar contingut fitxer --\n");
                    System.out.println("Usuari Contrasenya");
                    Usuari.mostrarsetFitxerUsuaris();
                    break;
                }
                case "2": {
                    System.out.println("\n-- Registrar-se --\n");

                    String nomUsuari, contrasenya1, contrasenya2;

                    System.out.println("Usuari:");
                    nomUsuari = sc.nextLine();

                    System.out.println("Contrasenya:");
                    contrasenya1 = sc.nextLine();

                    System.out.println("Repeteix contrasenya:");
                    contrasenya2 = sc.nextLine();

                    sessioIniciada = usuari.registrar(nomUsuari, contrasenya1, contrasenya2);
                    break;
                }
                case "3": {
                    //ensenyar usuaris disponibles
                    System.out.println("\n-- Iniciar Sessió --\n");
                    String[] Users = Usuari.totsUsuaris();


                    System.out.println("Selecciona un usuari");
                    for (int i = 0; i < Users.length; ++i) {
                        System.out.println(i + " - " + Users[i]);
                    }
                    String select = sc.nextLine();
                    int usrSeleccionat;
                    try {
                        usrSeleccionat = Integer.parseInt(select);
                    }
                    catch (NumberFormatException e)
                    {
                        usrSeleccionat = -1;
                    }
                    if(usrSeleccionat >= 0 && usrSeleccionat < Users.length) {
                        sessioIniciada = usuari.iniciarSessio(Users[usrSeleccionat]);
                    }
                    else {
                        System.out.println("Usuari seleccionat no vàlid");
                    }
                    break;
                }
                case "0": {
                    System.out.println("\n-- Sortint... --\n");
                    break;
                }
                default: {
                    System.out.println("Opció invalida.");
                }
            }

            while (sessioIniciada) {
                System.out.println("\nSessió de l'usuari " + usuari.getNom() + ".");
                System.out.println("\n----- OPCIONS SESSIÓ -----\n" +
						"1 - Moviment\n" +
                        "2 - Donar-se de baixa\n" +
                        "3 - Tancar sessió\n" +
                        "0 - Sortir");

                opcio = sc.nextLine();

                switch (opcio) {
					case "1": {
						System.out.println("\n-- Moviment --\n");
                        long temps = 0;
                        Posicion origen = new Posicion();
                        Posicion desti = new Posicion();
                        temps = usuari.moviment(origen, desti);

                        System.out.println("Moviment de " + origen.x + "-" + origen.y +
                                " a " + desti.x + "-" + desti.y +
                                "\nTemps del moviemnt en milisegons: " + temps);
                        break;
                    }
                    case "2": {
						System.out.println("\n-- Donar-se de baixa --\n");
                        String aux = usuari.getNom();
                        if (usuari.baixa()) {
                            Estadistica.eliminarStatsUsuari(aux);
                        }
                        sessioIniciada = false;
                        break;
                    }
                    case "3": {
						System.out.println("\n-- Tancar sessio --\n");
                        sessioIniciada = false;
                        break;
                    }
                    case "0": {
                        System.out.println("\n-- Sortir --\n");
                        sessioIniciada = false;
                        break;
                    }
                    default: {
                        System.out.println("\n-- Opcio invalida --\n");
                    }
                }

            }
        } while (!opcio.equals("0"));
    }
}
