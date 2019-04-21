import javafx.geometry.Pos;

/**
 * Esta clase da movientos en la partida de ajedrez, etc
 * @author Marian Dumitru Danci
 */
public class Smart extends Maquina {
    private int profunditat;
    private int colorJugador;
    private int colorContrari;

    public Smart(int color) {
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
        if (profunditat > 6) profunditat = 6;
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
                        if (!super.mourePeca(ini, desti, colorJugador)) {
                            System.out.println("MATA AL REI");
                        }

                        int aux = alphaBeta(profunditat,false, -10000, 10000);
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

    private int alphaBeta(int depth, boolean maximitzar, int alpha, int beta) {
        int estatPropi = super.estatMat(colorContrari);
        int estatOponent = super.estatMat(colorJugador);
        if (depth == 0 ||
                (estatPropi == 1) || (estatOponent == 1) ||
                (estatPropi == 2 && !maximitzar) || (estatOponent == 2 && maximitzar)) {
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
                        int aux = alphaBeta(depth - 1, false, alpha, beta);
                        if (aux > max) {
                            max = aux;
                        }
                        alpha = Math.max(alpha, aux);
                        if (beta <= alpha) {
                            return aux;
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
                        int aux = alphaBeta(depth - 1, true, alpha, beta);
                        if (aux < min) {
                            min = aux;
                        }
                        beta = Math.min(beta, aux);
                        if (beta <= alpha) {
                            return aux;
                        }
                        while (!desferMoviment(ini, desti, auxPeca, colorContrari));
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }

    /*
    public void valorPosicio() {
        double[][] evalPeoW =
                {
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0},
                        {1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0},
                        {0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5},
                        {0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0},
                        {0.5, -0.5, -1.0, 0.0, 0.0, -1.0, -0.5, 0.5},
                        {0.5, 1.0, 1.0, -2.0, -2.0, 1.0, 1.0, 0.5},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
                };
        double[][] evalPeoB = tranposar(evalPeoW);


        double[][] evalCavall =
                {
                        {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
                        {-4.0, -2.0, 0.0, 0.0, 0.0, 0.0, -2.0, -4.0},
                        {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0, -3.0},
                        {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5, -3.0},
                        {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0, -3.0},
                        {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5, -3.0},
                        {-4.0, -2.0, 0.0, 0.5, 0.5, 0.0, -2.0, -4.0},
                        {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
                };

        double[][] evalAlfilW = [
            [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0],
            [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
            [ -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0],
            [ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0],
            [ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0],
            [ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0],
            [ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0],
            [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0]
            ];

        double[][] evalAlfilB = transposar(evalAlfilW);

        double[][]  evalTorreW = [
            [  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
            [  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5],
            [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
            [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
            [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
            [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
            [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
            [  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0]
            ];

        double[][]  evalTorreB = transposar(evalTorreW);

        double[][] evalReina =
    [
            [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0],
            [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
            [ -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
            [ -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
            [  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
            [ -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
            [ -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0],
            [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0]
            ];

        double[][] evalReiW = [

            [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
            [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
            [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
            [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
            [ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0],
            [ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0],
            [  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 ],
            [  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 ]
            ];

        double[][] evalReiB = transposar(evalReiW);

    }
    private double[][] transposar(double[][] matriu) {
        double[][] novaMatriu = matriu;
        for(int j = 0; j < novaMatriu.length; j++){
            for(int i = 0; i < novaMatriu[j].length / 2; i++) {
                double temp = novaMatriu[j][i];
                novaMatriu[j][i] = novaMatriu[j][novaMatriu[j].length - i - 1];
                novaMatriu[j][novaMatriu[j].length - i - 1] = temp;
            }
        }
        return novaMatriu;
    }
    */
}