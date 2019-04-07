import java.util.Scanner;

public class Main2{
    private static Scanner sc = new Scanner(System.in);
    private static Usuari usr = new Usuari();


    public static void main(String[] args) {
        inici();
    }


    private static void inici() {
        int estat;
        do {
            estat = opcionsEntrada();
        } while (estat == 0);
        while (estat != -1) {
            estat = opcionsSessio();
        }
    }

    private static int opcionsEntrada() {
        //pantalla 1
        System.out.println("Benvingut/da (menu principal)");

        // Opcions dentrada
        System.out.println("\n" +
                "    1 - Iniciar sessió\n" +
                "    2 - Registrar-se\n" +
                "    3 - Sortir de la app\n");

        String opcio = sc.nextLine();

        int estat = 0;
        switch (opcio) {
            case "1": {
                System.out.println("\n---Iniciar Sessió---\n");
                /*
                //ensenyar usuaris disponibles
                String[] users = Usuari.totsUsuaris();
                for (int i = 0; i < users.length; ++i) {
                    System.out.println("   " + (i + 1) + " - " + users[i]);
                }

                // usuari seleccionat
                String opcio = sc.nextLine();
                */
                if(usr.iniciarSessio()) {
                    estat = 1;
                }
                break;
            }

            case "2": {
                if(usr.resigstrar()) {
                    estat = 1;
                }
                break;
            }
            case "3": {
                System.out.println("Sortint...");
                estat = -1;
                break;
            }
            default: {
                System.out.println("Opció no vàlida.\n");
            }
        }
        return estat;
    }

    private static int opcionsSessio() {
        System.out.println("\nMenú d'usuari/a\n" +
                "    1 - Jugar un problema\n" +
                "    2 - Veure solució a un problema\n" +
                "    3 - Afegir un probleman\n" +
                "    4 - Modificar un problema\n" +
                "    5 - Borrar un problema\n" +
                "    6 - Mirar les estadístiques\n" +
                "    7 - Donar-se de baixa\n" +
                "    8 - Sortir de la app\n");

        String opcio = sc.nextLine();
        int estat = 0;
        switch (opcio) {
            case "1": {
                /*
                String[][] problemes = consultarProblemes();
                for (int i = 0; i < problemes.length(); ++i) {
                    System.out.println(problemes[i][0] + " " + problemes[i][1] + " " problemes[i][2] + " "
                            + problemes[i][3] + " " + problemes[i][4]);
                }
                Scanner sc = new Scanner(System.in);
                int op = 0;
                boolean primer = true;
                while (op < 1 || op > (problemes.length() - 1)) {
                    if (!primer) System.out.println("Error");
                    else primer = false;
                    System.out.println("Selecciona un problema");
                    op = sc.nextInt();
                }

                // fer bucles per mostar problemes

                Problema p = new Problema();
                int res = getProblemaId(problemes[op][0], p);
                if (res < 1) ; //



                // seleccionar oponent: maquina o usuari

                // seleccionar atacar o defendre

                // Partida pa = new Partida(p, atacant, defenent);
                //jugar partida
                // pa.jugarPartida();
                */

                System.out.println("Jugar partida...");
                break;
            }

            case "2": {
                System.out.println("Veure solució a un problema");
                break;
            }
            case "3": {
                /*
                int res;
                boolean primer = true;
                do {
                    Scanner sc = new Scanner(System.in);
                    if (!primer) {
                        System.out.println("Error, torna a introduir el problema o -1 per sortir");
                        if (sc.nextInt() == -1) break;
                    }
                    else primer = false;
                    System.out.println("Escriu el nombre de jugades del problema");
                    int njug = 0;
                    boolean prim = true;
                    while (njug < 1) {
                        if (!prim) System.out.println("Error");
                        else prim = false;
                        njug = sc.nextInt();
                    }
                    System.out.println("Escriu el FEN del problema");
                    Scanner scs = new Scanner(System.in).useDelimiter(System.lineSeparator());
                    String fen;
                    fen = scs.next();
                    Problema p = new Problema();
                    //scanejar jugades i fen
                    res = p.crearProblema(njug, fen);
                    // cancelar
                    //afegir
                } while (res < 0);
                */

                System.out.println("Afegint problema...");
                break;
            }
            case "4": {
                /*
                int res;
                boolean prim = true;
                String[][] problemes = consultarProblemes();
                do {
                    Scanner sc = new Scanner(System.in);
                    if (!prim) {
                        System.out.println("Error, torna a introduir el nou problema o -1 per sortir");
                        if (sc.nextInt() == -1) break;
                    }
                    else prim = false;
                    for (int i = 0; i < problemes.length(); ++i) {
                        System.out.println(problemes[i][0] + " " + problemes[i][1] + " " problemes[i][2] + " "
                                + problemes[i][3] + " " + problemes[i][4]);
                    }
                    int op = 0;
                    boolean primer = true;
                    while (op < 1 || op > (problemes.length() - 1)) {
                        if (!primer) System.out.println("Error");
                        else primer = false;
                        System.out.println("Selecciona un problema");
                        op = sc.nextInt();
                    }
                    Problema p = new Problema();
                    int res = getProblemaId(problemes[op][0], p);
                    if (res < 1) ;
                    System.out.println("Escriu el nou nombre de jugades del problema");
                    int njug = 0;
                    boolean prim = true;
                    while (njug < 1) {
                        if (!prim) System.out.println("Error");
                        else prim = false;
                        njug = sc.nextInt();
                    }
                    if (p.getPrimer() == define.WHITE) {
                        System.out.println("El fen actual és: " + p.getPosIni() + " w - - 0 1");
                    }
                    else System.out.println("El fen actual és: " + p.getPosIni() + " b - - 0 1");
                    System.out.println("Escriu el nou FEN del problema");
                    Scanner scs = new Scanner(System.in).useDelimiter(System.lineSeparator());
                    String fen;
                    fen = scs.next();
                    res = p.modificar_problema(fen, njug);
                } while (res < 0);

                // consultar problemes
                // seleccionar problema
                // p = getProblemaId
                // modificar problema
                // p.modificarProblema(jugades, fen);
                */

                System.out.println("Modificant problema...");
                break;
            }
            case "5": {
                /*
                String[][] problemes = consultarProblemes();
                for (int i = 0; i < problemes.length(); ++i) {
                    System.out.println(problemes[i][0] + " " + problemes[i][1] + " " problemes[i][2] + " "
                            + problemes[i][3] + " " + problemes[i][4]);
                }
                Scanner sc = new Scanner(System.in);
                int op = 0;
                boolean primer = true;
                while (op < 1 || op > (problemes.length() - 1)) {
                    if (!primer) System.out.println("Error");
                    else primer = false;
                    System.out.println("Selecciona un problema");
                    op = sc.nextInt();
                }

                Problema p = new Problema();
                int res = getProblemaId(problemes[op][0], p);
                if (res < 1) ; //
                p.eliminar_problema(); //errors?
                // consultar problemes
                // p = getProblemaId
                // p.eliminarProblema()
                */

                System.out.println("Borrant problema...");
                break;
            }
            case "6": {
                //Opcions probl o usrPropi

                //String[][] problemes = consultarProblemes();
                //consultarEstadisticaProblema(String idProblema);

                //consultarEstadisticaUsuari(master.getName());
                //mirar stats stats

                System.out.println("Mirant estadistiques...");
                break;
            }
            case "7": {
                /*
                master.eliminarUsuari(master.getNom());
                */
                System.out.println("Eliminant usuari...");
                break;
            }
            case "8": {
                System.out.println("Sortint...");
                estat = -1;
                break;
            }
            default: {
                System.out.println("Opció no vàlida.\n");
            }
        }
        return estat;
    }
}