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

        Jugador master = new Usuari();
        Jugador second = new Jugador();
        Jugador tmp = new Maquina();
        String[] Users;
        switch (val) {
            case 1: {
                //ensenyar usuaris disponibles
                System.out.println("\n---Iniciar Sessió---\n");
                //test
                //Users = new String[]{"Pedro","Peponcio","Pepita","Grillo"};
                Users = totsUsuaris();
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
                master.login(Users[val-1]);
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
        Problema p;
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

                    // fer bucles per mostar problemes
                    first = true;
                    do {
                        if (!first) {
                            System.out.println("!!!!!!Error!!!!!!!");
                        } else first = false;
                        System.out.println("\nSelecciona un problema");
                        System.out.println("--------------------------\n");
                        for (int i = 0; i < problemes.length; ++i) {
                            System.out.println("   " + (i + 1) + " - " + problemes[i]);
                        }
                        Scanner sc = new Scanner(System.in);
                        val = sc.nextInt();
                    } while ((val > problemes.length || val < 1));
                    p = new Problema();
                    getProblemaId(problemes[val-1][0], p);

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
                        Scanner sc = new Scanner(System.in);
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
                        Scanner sc = new Scanner(System.in);
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
                    else if (ataca == 2 && val == 2){
                        //defender    &&   //naive
                        second = new Usuari(p.getPrimer());
                        master.setColor((p.getPrimer()==define.WHITE)?define.BLACK:define.WHITE);
                    }
                    else ;
                    System.out.println("\nCreant partida...");
                     Partida pa = new Partida(p, (master.getColor()==define.WHITE)?master:second, (master.getColor()==define.WHITE)?second:master,(ataca==1)?true:false);
                    //jugar partida
                    System.out.println("\nComença la partida...  A JUGAR :D");
                    pa.jugarPartida();
                    break;
                }
                case 2: {
                    // p = new Problema();
                    //scanejar jugades i fen
                    // p.crearProblema(jugades, fen)
                    // cancelar
                    //afegir
                    break;
                }
                case 3: {
                    // consultar problemes
                    // seleccionar problema
                    // p = getProblemaId
                    // modificar problema
                    // p.modificarProblema(jugades, fen);
                    break;
                }
                case 4: {
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