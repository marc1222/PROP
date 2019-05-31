package domini;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //pantalla 1
        System.out.println("Benvingut/da al menu principal");
        int val;

        Usuari master = new Usuari();
        Jugador second;

        do {
            boolean sessioIniciada = false;

            System.out.println("\nQuè desitja realitzar?\n" +
                    "    1 - Iniciar sessió\n" +
                    "    2 - Registrar-se\n" +
                    "    0 - Sortir de la app\n");

            Scanner sc = new Scanner(System.in);
            val = sc.nextInt();
            //salt de linea per permetre nova entrada de dadesa al entrar val
            sc.nextLine();
            switch (val) {
                case 1: {
                    // Mostrar usuaris disponibles
                    System.out.println("\n---Iniciar Sessió---\n");
                    String[] Users = Usuari.totsUsuaris();

                    if (Users.length != 0) {
                        System.out.println("Selecciona un usuari:");
                        for (int i = 0; i < Users.length; ++i) {
                            System.out.println(i + " - " + Users[i]);
                        }
                        int usrSeleccionat = sc.nextInt();
                        if(usrSeleccionat >= 0 && usrSeleccionat < Users.length) {
                            sc.nextLine();
                            System.out.println("Contrasenya:");
                            String contrasenya = sc.nextLine();
                            sessioIniciada = master.iniciarSessio(Users[usrSeleccionat], contrasenya);
                        }
                        else {
                            System.out.println("Usuari seleccionat no vàlid");
                        }
                    }
                    else {
                        System.out.println("No hi han usuaris registrats.");
                    }
                    break;
                }
                case 2: {
                    // Registre de l'usuari master
                    System.out.println("\n---Registrar-se---\n");

                    String nomUsuari, contrasenya1, contrasenya2;

                    System.out.println("Usuari:");
                    nomUsuari = sc.nextLine();

                    System.out.println("Contrasenya:");
                    contrasenya1 = sc.nextLine();

                    System.out.println("Repeteix contrasenya:");
                    contrasenya2 = sc.nextLine();

                    sessioIniciada = master.registrar(nomUsuari, contrasenya1, contrasenya2);
                    break;
                }
                case 0: {
                    System.out.println("Sortint...");
                    return;
                }
                default: {
                    System.out.println("Opció invalida.");
                    break;
                }
            }


            boolean first;
            //aqui master es un usuari valid, li mostrem els problemes
            first = true;
            while (sessioIniciada) {
                if (!first) {
                    System.out.println("!!!!!!Error!!!!!!!");
                } else first = false;

                System.out.println("\nBenvnvingut al teu menú d'usuari/a " + master.getNom() + "\n");
                System.out.println("Què desitja realitzar?\n" +
                        "    1 - Jugar un problema\n" +
                        "    2 - Afegir un problema\n" +
                        "    3 - Modificar un problema\n" +
                        "    4 - Borrar un problema\n" +
                        "    5 - Mirar les estadístiques\n" +
                        "    6 - Donar-se de baixa\n" +
                        "    7 - Tancar sessió\n" +
                        "    0 - Sortir de la app\n");

                val = sc.nextInt();
                switch (val) {
                    case 1: {
                        String[][] problemes = Problema.consultarProblemes();
                        System.out.println("Id  #Jug Color Dif  Posició inicial en FEN");
                        for (int i = 0; i < problemes.length; ++i) {
                            System.out.println(i + "  -  " + problemes[i][1] + "    " + problemes[i][2] + "    "
                                    + problemes[i][4] + "   " + problemes[i][3]);
                        }
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
                            System.out.println("    2 - Maquina");
                            System.out.println("    3 - Maquina VS Maquina");
                            System.out.println("    4 - Maquina Smart");
                            sc = new Scanner(System.in);
                            val = sc.nextInt();
                        } while ((val > 4 || val < 1));

                        Partida pa = new Partida();
                        if (val != 3) {
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
                                second = new Usuari((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                                master.setColor(p.getPrimer());
                            } else if (ataca == 2 && val == 1) {
                                //defender      &&  //Invitado
                                second = new Usuari(p.getPrimer());
                                master.setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                            } else if (ataca == 1 && val == 2) {
                                //atacar   &&    //naive
                                second = new Naive((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                                master.setColor(p.getPrimer());
                            } else if (ataca == 2 && val == 2) {
                                //defender      &&  //naive
                                second = new Naive(p.getPrimer());
                                master.setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                            } else if (ataca == 1 && val == 4) {
                                //atacar   &&    //smart
                                second = new Smart((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                                master.setColor(p.getPrimer());
                            } else if(ataca == 2 && val == 4) {
                                //defender      &&  //naive
                                second = new Smart(p.getPrimer());
                                master.setColor((p.getPrimer() == define.WHITE) ? define.BLACK : define.WHITE);
                            } else {
                                //defender    &&   //usuari
                                second = new Usuari(p.getPrimer());
                                //master.setColor((p.getPrimer()==domini.define.WHITE)?domini.define.BLACK:domini.define.WHITE);
                            }
                            //else ;
                            System.out.println("\nCreant partida...");
                            pa = new Partida(p, (master.getColor() == define.WHITE) ? master : second, (master.getColor() == define.WHITE) ? second : master, (ataca == 1) ? true : false);
                            //jugar partida

                            if (second.getTipus() == define.MAQUINA) {
                                Maquina n = (Maquina) second;
                                n.setTauler(pa.getTauler());
                                n.setProfunditat(pa.getMat());
                            }
                        }
                        else {
                            Jugador naive1 = new Naive(define.WHITE);
                            Jugador naive2 = new Naive(define.BLACK);
                            pa = new Partida(p, naive1, naive2, true);

                            Maquina m1 = (Maquina) naive1;
                            m1.setTauler(pa.getTauler());
                            m1.setProfunditat(pa.getMat());

                            Maquina m2 = (Maquina) naive2;
                            m2.setTauler(pa.getTauler());
                            m2.setProfunditat(pa.getMat());
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
                            if (!primer) {
                                System.out.println("Error, escriu -1 per sortir o qualsevol altre número per tornar a introduir el problema");
                                if (sc.nextInt() == -1) break;
                            } else primer = false;
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
                            if (!prim) {
                                System.out.println("Error, torna a introduir el nou problema o -1 per sortir");
                                if (sc.nextInt() == -1) break;
                            } else prim = false;
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
                            } else System.out.println("El fen actual és: " + p.getPosIni() + " b - - 0 1");
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
                        int op = 0;
                        boolean primer = true;
                        while (op < 1 || op > (problemes.length - 1)) {
                            if (!primer) {
                                System.out.println("Error, escriu -1 per sortir o qualsevol altre número per tornar a introduir el problema");
                                if (sc.nextInt() == -1) break;
                            } else primer = false;
                            System.out.println("Selecciona un problema");
                            op = sc.nextInt();
                        }
                        if (op > -1 && op < problemes.length) {
                            Problema p = new Problema();
                            int res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);
                            if (res < 1) ; //
                            p.eliminar_problema(); //errors?
                            Estadistica.eliminarStatsProblema(String.valueOf(problemes[op][0]));
                        }
                        // consultar problemes
                        // p = getProblemaId
                        // p.eliminarProblema()
                        break;
                    }
                    case 5: {
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
                                    int opcio1 = sc.nextInt();
                                    ;
                                    if (opcio1 >= 0 && opcio1 < problemes.length) {
                                        ArrayList<String> statsProblema = Estadistica.estadistiquesProblema(String.valueOf(opcio1));
                                        if (statsProblema.isEmpty()) {
                                            System.out.println("No hi han registres del problema");
                                        } else {
                                            System.out.println("Jugador  Mat  Temps");
                                        }

                                        for (String marca : statsProblema) {
                                            System.out.println(marca);
                                        }
                                    } else {
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
                                        ArrayList<String> statsUsuari = Estadistica.estadistiquesUsuari(Usuaris[opcio2]);

                                        if (statsUsuari.isEmpty()) {
                                            System.out.println("No hi han registres de l'usuari");
                                        } else {
                                            System.out.println("Problema  Mat  Temps");
                                        }
                                        for (String marca : statsUsuari) {
                                            System.out.println(marca);
                                        }
                                    } else {
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
                        if (master.eliminarUsuari(aux)) {
                            Estadistica.eliminarStatsUsuari(aux);
                        }
                        else {
                            System.out.println("Usuari no eliminat");
                        }
                        sessioIniciada = false;
                        break;
                    }
                    case 7: {
                        System.out.println("Tancant sessió...");
                        sessioIniciada = false;
                        break;
                    }
                    case 0: {
                        System.out.println("Sortint...");
                        sessioIniciada = false;
                        break;
                    }
                    default: {
                        System.out.println("Opció invalida.");
                        break;
                    }
                }
            }
        } while (val != 0);
    }
}
