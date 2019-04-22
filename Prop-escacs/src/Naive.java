import javafx.geometry.Pos;

/**
 * Esta clase da movientos en la partida de ajedrez, etc
 * @author Marian Dumitru Danci
 */
public class Naive extends Maquina {
    private int profunditat;
    private int colorJugador;
    private int colorContrari;

    public Naive(int color) {
        colorJugador = color;
        colorContrari = color ^ 1;
    }

    public int getTipus() {
        return super.getTipusMaquina();
    }

    public void setTauler(Taulell tauler) {
        super.setTaulerMaquina(tauler);
    }

    public void setProfunditat(int mat) {
        if (mat > 2) profunditat = 2;
        else profunditat = mat;
    }

    public long moviment(Posicion inici, Posicion fi) {
        System.out.println("Loading...");
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF;
        maxF = -9999;

        outerloop:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion ini = new Posicion(i, j);
                if (super.getColorPeca(ini) == colorJugador) {
                    Posicion[] tots =  super.totsMovimentsPeca(ini);
                    for (Posicion desti : tots) {
                        //desti.mostrar();
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(desti) != 2) {
                            auxPeca = super.getTipusPeca(desti);
                        }
                        //while (!Tauler.mover_pieza(ini, desti, colorJugador));
                        super.mourePeca(ini, desti, colorJugador);

                        int aux = minimax(profunditat, false);
                        while (!super.desferMoviment(ini, desti, auxPeca, colorJugador));
                        if (aux > maxF) {
                            mejorIni.x = ini.x;
                            mejorIni.y = ini.y;
                            mejorDesti.x = desti.x;
                            mejorDesti.y = desti.y;
                            maxF = aux;
                        }
                    }
                }
            }
        }
        //System.out.println("\n\nRESULTAT : " + maxF);

        inici.x = mejorIni.x;
        inici.y = mejorIni.y;
        fi.x = mejorDesti.x;
        fi.y = mejorDesti.y;

        --profunditat;
        return 0;
    }

    private int minimax(int depth, boolean maximitzar) {
        /*
        if (pecesTau[6][3].getTipus() == define.CAVALL && pecesTau[4][3].getTipus() == define.PEO) {
        //if (pecesTau[7][5].getTipus() == define.CAVALL) {
            System.out.println("*************************************************");
            //System.out.println("PUNTS : " + total);
            System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
            System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));

            Tauler.printTauler();
        }
        */

        int estatPropi = super.estatMat(colorContrari);
        int estatOponent = super.estatMat(colorJugador);
        if (depth == 0 ||
                (estatPropi == 1) || (estatOponent == 1) ||
                (estatPropi == 2 && !maximitzar) || (estatOponent == 2 && maximitzar)) {
            //System.out.println("1- " + Tauler.escac_i_mat(colorContrari) + " 2- " + Tauler.escac_i_mat(colorJugador));
            return super.evaluar(colorJugador);
        }
        int min, max;
        max = -9999;
        min = 9999;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion ini = new Posicion(i, j);
                if (maximitzar && (super.getColorPeca(ini) == colorJugador)) {
                    //System.out.println("+++ MAX +++");
                    //System.out.println("POS: " + i + "-" + j);
                    Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(desti) != 2) {
                            auxPeca = super.getTipusPeca(desti);
                        }
                        if (!super.mourePeca(ini, desti, colorJugador)) {
                            return super.evaluar(colorJugador);
                        }
                        int aux = minimax(depth - 1, false);
                        if (aux > max) {
                            max = aux;
                        }
                        while (!super.desferMoviment(ini, desti, auxPeca, colorJugador));
                    }
                }
                else if (!maximitzar && (super.getColorPeca(ini) == (colorContrari))) {
                    //System.out.println("--- MIN ---");
                    //System.out.println("POS: " + i + "-" + j);
                    Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(desti) != 2) {
                            auxPeca = super.getTipusPeca(desti);
                        }
                        //while (!Tauler.mover_pieza(ini, desti, colorContrari));
                        if (!super.mourePeca(ini, desti, colorContrari)) {
                            return super.evaluar(colorJugador);
                        }
                        int aux = minimax(depth - 1, true);
                        if (aux < min) {
                            min = aux;
                        }
                        while (!desferMoviment(ini, desti, auxPeca, colorContrari));
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }
}