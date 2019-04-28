 import java.util.Scanner;

public class DriverNaive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String opcio;
        do {
            System.out.println("\n----- OPCIONS INICI -----\n" +
                    "1 - Seleccionar problema per veure moviments\n" +
                    "0 - Sortir");

            opcio = sc.nextLine();
            
            Jugador naive1 = new Naive(define.WHITE);
            Jugador naive2 = new Naive(define.BLACK);
            
            Maquina m1 = (Maquina) naive1;
            Maquina m2 = (Maquina) naive2;
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
                        String aux = sc.nextLine();
                        try {
                        	op = Integer.parseInt(aux);
                        }
                        catch (NumberFormatException e){
                        	op = -1;
                        }
                    }

                    Problema p = new Problema();
                    int res = Problema.getProblemaId(Integer.parseInt(problemes[op][0]), p);

                    Taulell Tauler = new Taulell(new Taulell(p.getPeces()));
                    
                    m1.setTauler(Tauler);
                    m1.setProfunditat(p.getNumJugades());
                    
                    m2.setTauler(Tauler);
                    m2.setProfunditat(p.getNumJugades());

                    int ronda = 0;
                    int rondesTotals = (p.getNumJugades()*2)-1;
                    boolean seguir = true;
                    
                    while (ronda < rondesTotals && seguir) {
                    	Tauler.printTauler();
                        Posicion origen = new Posicion(0, 0);
                        Posicion desti = new Posicion(0, 0);

                        int colorActual;
                        if(ronda%2 == 0) {
                            m1.moviment(origen, desti);
                            colorActual = define.WHITE;
                        }
                        else {
                            m2.moviment(origen, desti);
                            colorActual = define.BLACK;
                        }
                        
                        Tauler.mover_pieza(origen, desti, colorActual);
                        
                        System.out.println("\nMoviment de " + origen.x + "-" + origen.y + " a " + desti.x + "-" +desti.y);
                        
                        
                        int matW, matB;
                        matB = Tauler.escac_i_mat(define.WHITE);
                        matW = Tauler.escac_i_mat(define.BLACK);
                        
                        if(matB == 1) {
							System.out.println("\n-- Guanyen blanques --\n");
						}
						else if(matW == 1) {
							System.out.println("\n-- Guanyen negres --\n");
						}
						else if(matW == 2 || matB == 2) {
							System.out.println("\n-- Empat --\n");
						}
                        
                        ++ronda;
                    }

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
