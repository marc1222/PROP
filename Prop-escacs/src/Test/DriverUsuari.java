import java.util.Scanner;

public class DriverUsuari {
    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./files/testUsuaris.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuari.setFitxerUsuaris(fitxerUsuaris);

        String opcio;
        do {
            System.out.println("\n----- OPCIONS INICI -----\n" +
                    "1 - Mostrar contingut fitxer\n" +
                    "2 - Usuaris registrats\n" +
                    "3 - Iniciar sessió\n" +
                    "4 - Registrar-se\n" +
                    "0 - Sortir");

            opcio = sc.nextLine();

            Usuari usuari = new Usuari();
            boolean sessioIniciada = false;
            switch (opcio) {
                case "1": {
                    System.out.println("Usuari Contrasenya");
                    Usuari.mostrarsetFitxerUsuaris();
                    break;
                }
                case "2": {
                    String[] totsUsuaris = Usuari.totsUsuaris();
                    for (int i = 0; i < totsUsuaris.length; ++i) {
                        System.out.println(totsUsuaris[i]);
                    }
                    break;
                }
                case "3": {
                    //ensenyar usuaris disponibles
                    System.out.println("\n---Iniciar Sessió---\n");
                    String[] Users = Usuari.totsUsuaris();


                    System.out.println("Selecciona un usuari");
                    for (int i = 0; i < Users.length; ++i) {
                        System.out.println(i + " - " + Users[i]);
                    }
                    int usrSeleccionat = sc.nextInt();
                    if(usrSeleccionat >= 0 && usrSeleccionat < Users.length) {
                        sessioIniciada = usuari.iniciarSessio(Users[usrSeleccionat]);
                    }
                    else {
                        System.out.println("Usuari seleccionat no vàlid");
                    }
                    break;
                }
                case "4": {
                    System.out.println("\n---Registrar-se---\n");
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
                case "0": {
                    System.out.println("Sortint...");
                    break;
                }
                default: {
                    System.out.println("Opció invalida.");
                }
            }

            while (sessioIniciada) {
                System.out.println("\nSessió de l'usuari " + usuari.getNom() + ".");
                System.out.println("\n----- OPCIONS SESSIÓ -----\n" +
                        "1 - Donar-se de baixa\n" +
                        "2 - Moviment\n" +
                        "3 - Tancar sessió\n" +
                        "0 - Sortir");

                opcio = sc.nextLine();
                // Salta la seguent linea


                switch (opcio) {
                    case "1": {
                        String aux = usuari.getNom();
                        if (usuari.baixa()) {
                            Estadistica.eliminarStatsUsuari(aux);
                        }
                        sessioIniciada = false;
                        break;
                    }
                    case "2": {
                        long temps = 0;
                        Posicion origen = new Posicion();
                        Posicion desti = new Posicion();
                        temps = usuari.moviment(origen, desti);

                        System.out.println("Moviment de " + origen.x + "-" + origen.y +
                                " a " + desti.x + "-" + desti.y +
                                "\nTemps del moviemnt en milisegons: " + temps);
                        break;
                    }
                    case "3": {
                        System.out.println("Tancant sessió");
                        sessioIniciada = false;
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

            }
        } while (!opcio.equals("0"));
    }
}