import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        //pantalla 1
        System.out.println("Benvingut/da (menu principal)");
        int val;
        boolean first = true;
        do {
            if (!first) {
                System.out.println("!!!!!!Error!!!!!!!");
            } else first = false;
            System.out.println("\nQuè desitja realitzar?");
            System.out.println("    1 - Iniciar sessió");
            System.out.println("    2 - Registrar-se");
            System.out.println("    3 - Sortir de la app\n");

            Scanner sc = new Scanner(System.in);
            val = sc.nextInt();
        } while ((val > 3 || val < 1));

//        Jugador master = new Usuari();
//        Jugador second;
        //Jugador tmp = new Maquina();
        String[] Users;
        switch (val) {
            case 1: {
                //ensenyar usuaris disponibles
                System.out.println("\n---Iniciar Sessió---\n");
                //test
                Users = new String[]{"Pedro","Peponcio","Pepita","Grillo"};
                //Users = totsUsuaris();
                first = true;
                do {
                    if (!first) {
                        System.out.println("!!!!!!Error!!!!!!!");
                    } else first = false;
                    System.out.println("Selecciona un usuari");
                    for (int i = 0; i < Users.length; ++i) {
                        System.out.println("   " + (i + 1) + " - " + Users[i]);
                    }
                    Scanner sc = new Scanner(System.in);
                    val = sc.nextInt();
                } while ((val > Users.length || val < 1));

                //fer login de l'user master
                // master.login(Users[val-1]);
                break;
            }
            case 2: {
                //register al master
                System.out.println("\n---Registrarte---\n");
                //String name = master.register();
                //master.login(name);
                break;
            }
            case 3: {
                System.out.println("Sortint...");
                return;
            }
        }
        //aqui master es un usuari valid, li mostrem els problemes
        //Problema p;
        int ret = 0;
        while (ret != 5) {
            first = true;
            do {
                if (!first) {
                    System.out.println("!!!!!!Error!!!!!!!");
                } else first = false;

                System.out.println("\nBenvnvingut al teu menú d'usuari/a\n");
                System.out.println("Què desitja realitzar?");
                System.out.println("    1 - Jugar un problema");
                System.out.println("    2 - Afegir un problema");
                System.out.println("    3 - Modificar un problema");
                System.out.println("    4 - Borrar un problema");
                System.out.println("    5 - Mirar les estadístiques");
                System.out.println("    6 - Sortir de la app\n");

                Scanner sc = new Scanner(System.in);
                val = sc.nextInt();
            } while ((val > 5 || val < 1));
            switch (val) {
                case 1: {
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
                    break;
                }
                case 2: {
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
                    break;
                }
                case 3: {
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
                    break;
                }
                case 4: {
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
                    break;
                }
                case 5: {
                    //Opcions probl o usrPropi

                    //String[][] problemes = consultarProblemes();
                    //consultarEstadisticaProblema(String idProblema);

                    //consultarEstadisticaUsuari(master.getName());
                    //mirar stats stats
                    break;
                }
                case 6: {
                    System.out.println("Sortint...");
                    return;
                }
            }
            ret = val;
        }
    }
}