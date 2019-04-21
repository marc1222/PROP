import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //pantalla 1
        System.out.println("Benvingut/da (menu principal)");
        int val;

        Usuari master = new Usuari();
        Jugador second;

        boolean sessioIniciada = false;
        do {
            System.out.println("Què desitja realitzar?");
            System.out.println("    1 - Iniciar sessió");
            System.out.println("    2 - Registrar-se");
            System.out.println("    3 - Sortir de la app\n");

            Scanner sc = new Scanner(System.in);
            val = sc.nextInt();

            switch (val) {
                case 1: {
                    //ensenyar usuaris disponibles
                    System.out.println("\n---Iniciar Sessió---\n");
                    String[] Users = Usuari.totsUsuaris();


                    System.out.println("Selecciona un usuari");
                    for (int i = 0; i < Users.length; ++i) {
                        System.out.println(i + " - " + Users[i]);
                    }
                    int usrSeleccionat = sc.nextInt();
                    if(usrSeleccionat >= 0 && usrSeleccionat < Users.length) {
                        sessioIniciada = master.iniciarSessio(Users[usrSeleccionat]);
                    }
                    else {
                        System.out.println("Usuari seleccionat no vàlid");
                    }


                    break;
                }
                case 2: {
                    //register al master
                    System.out.println("\n---Registrarte---\n");
                    sessioIniciada = master.registrar();
                    break;
                }
                case 3: {
                    System.out.println("Sortint...");
                    return;
                }
                default: {
                    System.out.println("Opció no vàlida.");
                    break;
                }
            }
        } while (!sessioIniciada);

        boolean first;
        //aqui master es un usuari valid, li mostrem els problemes
        int ret = 0;
        while (ret != 7) {
            first = true;
            do {
                if (!first) {
                    System.out.println("!!!!!!Error!!!!!!!");
                } else first = false;

                System.out.println("\nBenvnvingut al teu menú d'usuari/a " + master.getNom() + "\n");
                System.out.println("Què desitja realitzar?");
                System.out.println("    1 - Jugar un problema");
                System.out.println("    2 - Afegir un problema");
                System.out.println("    3 - Modificar un problema");
                System.out.println("    4 - Borrar un problema");
                System.out.println("    5 - Mirar les estadístiques");
                System.out.println("    6 - Donar-se de baixa");
                System.out.println("    7 - Sortir de la app\n");

                Scanner sc = new Scanner(System.in);
                val = sc.nextInt();
            } while ((val > 7 || val < 1));
            switch (val) {
                case 1: {
                    String[][] problemes = Problema.consultarProblemes();
                    System.out.println("Id  #Jug Color Dif  Posició inicial en FEN");
                    for (int i = 0; i < problemes.length; ++i) {
                        System.out.println(i + "  -  " + problemes[i][1] + "    " + problemes[i][2] + "    "
                                + problemes[i][4] + "   " + problemes[i][3]);
                    }
                    Scanner sc = new Scanner(System.in);
                    int op = -1;
                    boolean primer = true;
                    while (op < 0 || op > (problemes.length - 1)) {
                        if (!primer) System.out.println("Error");
                        else primer = false;
                        System.out.println("Selecciona un problema");
                        op = sc.nextInt();
                    }

                    // fer bucles per mostar problemes

                    Problema p = new Problema();
                    int res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);
                    //if (res < 1) ; //
                    // seleccionar oponent: maquina o usuari
                    first = true;
                    do {
                        if (!first) {
                            System.out.println("!!!!!!Error!!!!!!!");
                        } else first = false;
                        System.out.println("\nContra qui vols jugar?");
                        System.out.println("    1 - Invitado");
                        System.out.println("    2 - Maquina tontita");
                        System.out.println("    3 - Maquina smart");
                        sc = new Scanner(System.in);
                        val = sc.nextInt();
                    } while ((val > 3 || val < 1));

                    int ataca;
                    first = true;
                    // seleccionar atacar o defendre
                    do {
                        if (!first) {
                            System.out.println("!!!!!!Error!!!!!!!");
                        } else first = false;
                        System.out.println("\nVols atacar o bé t'agradaria defensar?");
                        System.out.println("    1 - Atacar");
                        System.out.println("    2 - Defensar");
                        sc = new Scanner(System.in);
                        ataca = sc.nextInt();
                    } while ((ataca > 2 || ataca < 1));

                    if (ataca == 1 && val == 1) {
                        //atacar  && //invitado
                        second = new Usuari((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                        master.setColor(p.getPrimer());
                    }
                    else if (ataca == 2 && val == 1) {
                        //defender      &&  //Invitado
                        second = new Usuari(p.getPrimer());
                        master.setColor((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                    }
                    else if (ataca == 1 && val == 2){
                        //atacar   &&    //naive
                        second = new Naive((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                        master.setColor(p.getPrimer());
                    }
                    else if(ataca == 2 && val == 2){
                        //defender      &&  //naive
                        second = new Naive(p.getPrimer());
                        master.setColor((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                    }
                    else {
                        //defender    &&   //naive
                        second = new Usuari(p.getPrimer());
                        master.setColor((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                    }
                    //else ;
                    System.out.println("\nCreant partida...");
                    Partida pa = new Partida(p, (master.getColor()==define.WHITE)?master:second, (master.getColor()==define.WHITE)?second:master,(ataca==1)?true:false);
                    //jugar partida

                    if (second.getTipus() == define.MAQUINA) {
                        Maquina n = (Maquina)second;
                        n.setTauler(pa.getTauler());
                        n.setProfunditat(pa.getMat());
                    }
                    System.out.println("\nComença la partida...  A JUGAR :D");
                    pa.jugar_partida();
                    break;


                    // seleccionar oponent: maquina o usuari
                    // seleccionar atacar o defendre

                    // Partida pa = new Partida(p, atacant, defenent);
                    //jugar partida
                    // pa.jugarPartida();
                }
                case 2: {
                    int res;
                    boolean primer = true;
                    do {
                        Scanner sc = new Scanner(System.in);
                        if (!primer) {
                            System.out.println("Error, escriu -1 per sortir o qualsevol altre número per tornar a introduir el problema");
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
                        res = p.crear_problema(njug, fen);
                        // cancelar
                        //afegir
                    } while (res < 0);
                    break;
                }
                case 3: {
                    int res;
                    boolean prim = true;
                    String[][] problemes = Problema.consultarProblemes();
                    do {
                        Scanner sc = new Scanner(System.in);
                        if (!prim) {
                            System.out.println("Error, torna a introduir el nou problema o -1 per sortir");
                            if (sc.nextInt() == -1) break;
                        }
                        else prim = false;
                        System.out.println("Id  #Jug Color Dif  Posició inicial en FEN");
                        for (int i = 0; i < problemes.length; ++i) {
                            System.out.println(i + "  -  " + problemes[i][1] + "    " + problemes[i][2] + "    "
                                    + problemes[i][4] + "   " + problemes[i][3]);
                        }
                        int op = 0;
                        boolean primer = true;
                        while (op < 1 || op > (problemes.length - 1)) {
                            if (!primer) System.out.println("Error");
                            else primer = false;
                            System.out.println("Selecciona un problema");
                            op = sc.nextInt();
                        }
                        Problema p = new Problema();
                        res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);
                        if (res < 1) ;
                        System.out.println("Escriu el nou nombre de jugades del problema");
                        int njug = 0;
                        prim = true;
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
                    String[][] problemes = Problema.consultarProblemes();
                    System.out.println("Id  #Jug Color Dif  Posició inicial en FEN");
                    for (int i = 0; i < problemes.length; ++i) {
                        System.out.println(i + "  -  " + problemes[i][1] + "    " + problemes[i][2] + "    "
                                + problemes[i][4] + "   " + problemes[i][3]);
                    }
                    Scanner sc = new Scanner(System.in);
                    int op = 0;
                    boolean primer = true;
                    while (op < 1 || op > (problemes.length - 1)) {
                        if (!primer) System.out.println("Error");
                        else primer = false;
                        System.out.println("Selecciona un problema");
                        op = sc.nextInt();
                    }

                    Problema p = new Problema();
                    int res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);
                    if (res < 1) ; //
                    p.eliminar_problema(); //errors?
                    // consultar problemes
                    // p = getProblemaId
                    // p.eliminarProblema()
                    break;
                }
                case 5: {
                    Scanner sc = new Scanner(System.in);
                    int opcio;
                    do {
                        System.out.println("\n" +
                                "    1 - Consultar estadistiques problema\n" +
                                "    2 - Consultar estadistiques usuari\n" +
                                "    3 - Tornar al menu d'usuari");
                        opcio = sc.nextInt();
                        switch (opcio) {
                            case 1: {
                                System.out.println("Selecciona un problema:");
                                String[][] problemes = Problema.consultarProblemes();
                                System.out.println("Id  #Jug Color Dif  Posició inicial en FEN");
                                for (int i = 0; i < problemes.length; ++i) {
                                    System.out.println(i + "  -  " + problemes[i][1] + "    " + problemes[i][2] + "    "
                                            + problemes[i][4] + "   " + problemes[i][3]);
                                }
                                int opcio1 = sc.nextInt();;
                                if (opcio1 >= 0 && opcio1 < problemes.length) {
                                    Estadistica.estadistiquesProblema(String.valueOf(opcio1));
                                }
                                else {
                                    System.out.println("Problema no vàlid");
                                }
                                break;
                            }
                            case 2: {
                                String[] Usuaris = Usuari.totsUsuaris();
                                System.out.println("Selecciona un usuari:");
                                for (int i = 0; i < Usuaris.length; ++i) {
                                    System.out.println(i + " - " + Usuaris[i]);
                                }
                                int opcio2 = sc.nextInt();
                                if (opcio2 >= 0 && opcio2 < Usuaris.length) {
                                    Estadistica.estadistiquesUsuari(Usuaris[opcio2]);
                                }
                                else {
                                    System.out.println("Usuari no vàlid");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Sortint menú estadistiques");
                                break;
                            }
                            default: {
                                System.out.println("Opció no vàlida");
                                break;
                            }
                        }
                    } while (opcio != 3);
                    break;
                }
                case 6: {
                    String aux = master.getNom();
                    if (master.baixa()) {
                        Estadistica.eliminatStatsUsuari(aux);
                    }
                    break;
                }
                case 7: {
                    /*
                    System.out.println("Crear Problema");
                    Problema p = new Problema ();
                    int res = p.crear_problema(2, "5Br1/6P1/5KBk/8/8/8/8/8 w - - 0 1");
                    System.out.println(String.valueOf(res));
                    System.out.println(String.valueOf(p.getId()) + " " + String.valueOf(p.getNumJugades()) + " " +
                            String.valueOf(p.getPrimer()) + " " + p.getPosIni() + " " +
                            String.valueOf(p.getDificultat()));
                    */
                    System.out.println("Sortint...");
                    break;
                }
            }
            ret = val;
        }
    }
}