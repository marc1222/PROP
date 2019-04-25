import java.util.Scanner;

public class DriverNaive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String opcio;
        do {
            System.out.println("\n----- OPCIONS INICI -----\n" +
                    "1 - Seleccionar problema\n" +
                    "2 - Donar moviment\n" +
                    "0 - Sortir");

            opcio = sc.nextLine();

            int color = sc.nextInt();
            Naive maqNaive = new Naive(color);

            switch (opcio) {
                case "1": {
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

                    Problema p = new Problema();
                    int res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);

                    Taulell Tauler = new Taulell(new Taulell(p.getPeces()));


                    Naive withe = new Naive(define.WHITE);
                    Naive black = new Naive(define.BLACK);

                    int ronda = 0;
                    int rondesTotals = (p.getNumJugades()*2)-1;
                    boolean seguir = true;
                    while (ronda < rondesTotals && seguir) {
                        Posicion origen = new Posicion(0, 0);
                        Posicion desti = new Posicion(0, 0);

                        int colorActual;
                        if(ronda%2 == 0) {
                            withe.moviment(origen, desti);
                            colorActual = define.WHITE;
                        }
                        else {
                            black.moviment(origen, desti);
                            colorActual = define.BLACK;
                        }
                        Tauler.mover_pieza(origen, desti, colorActual);
                        ++ronda;
                        String opcioSeguir = sc.nextLine();
                        if(opcioSeguir.equals("2")) seguir = false;
                        else System.out.println("Opcio invalida.");
                    }

                    break;
                }
                case "2": {
                    break;
                }
                case "0": {
                    System.out.println("Sortint...");
                    break;
                }
                default: {
                    System.out.println("Opcio invalida.");
                    break;
                }
            }
        } while (!opcio.equals("0"));
    }
}