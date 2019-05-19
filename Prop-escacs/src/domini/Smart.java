package domini;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Smart
 * @author Marian Dumitru Danci
 */
public class Smart extends Maquina {
    private int profunditat;
    private int mat;
    private int colorJugador;
    private int colorContrari;
    public int x;

    public Smart(int color) {
        colorJugador = color;
        colorContrari = color ^ 1;
    }

    /**
     *
     * @return Tipus de jugador
     */
    public int getTipus() {
        return super.getTipus();
    }

    /**
     *
     * @param tauler Tauler de la partida
     */
    public void setTauler(Taulell tauler) {
        super.setTaulerMaquina(tauler);
    }

    /**
     * Segons el nombre de jugades en fer mat s'especifica una profunditat,
     * especificant un maxim
     * @param mat Nombre de jugades en asolir mat del problema
     */
    public void setProfunditat(int mat) {
        this.mat = mat*2 - 2;
        actualitzarProfunditat();
    }

    /**
     * Actualitza la profunditat limit
     */
    public void actualitzarProfunditat() {
        if (mat > 3) profunditat = 3;
        else profunditat = mat;
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna 0
     */
    public long moviment(Posicion origen, Posicion desti) {
        x = 0;
        System.out.println("\nCarregant moviment..." + profunditat);
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF = -9999;

        // Recorre el tauler
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Posicion ini = new Posicion(i, j);
                // Tractar peca propia
                if (super.getColorPeca(ini) == colorJugador) {
                    Posicion[] tots =  super.totsMovimentsPeca(ini);



                    ArrayList<ValorPosicion> movimentsOrdenats = new ArrayList<>();
                    for (Posicion moviment : tots) {
                        Posicion aux = new Posicion(i, j);
                        //int valorMoviment = super.avaluarMoviment(aux, moviment);

                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(moviment) != 2) {
                            auxPeca = super.getTipusPeca(moviment);
                        }
                        super.mourePeca(ini, moviment, colorJugador);
                        int valorMoviment = super.evaluar(colorJugador);
                        super.desferMoviment(ini, moviment, auxPeca, colorJugador);
                        System.out.println("    " + moviment.x + "-" + moviment.y + " :" + valorMoviment);
                        movimentsOrdenats.add(new ValorPosicion(moviment, valorMoviment));
                    }
                    movimentsOrdenats.sort(Comparator.comparing(ValorPosicion::getValor).reversed());

                    System.out.println("\n\n\n");
                    for (Posicion moviment : tots) {
                        System.out.println(moviment.x + "-" + moviment.y);
                    }
                    System.out.println("*********************************************************");
                    for (ValorPosicion x : movimentsOrdenats) {
                        Posicion moviment = x.getPos();
                        System.out.println(moviment.x + "-" + moviment.y);
                    }





                    // Fer tots els moviments que pot fer la peca
                    for (ValorPosicion x : movimentsOrdenats) {
                        Posicion moviment = x.getPos();
                        // Si el moviment es a una casella amb una peca de
                        // l'oponent, es guarda el tipus de la peca
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(moviment) != 2) {
                            auxPeca = super.getTipusPeca(moviment);
                        }
                        if (super.mourePeca(ini, moviment, colorJugador)) {
                            // Si s'ha fet el moviment correctament,
                            // cridar al algortime minimax
                            int aux = minimax(profunditat, false, -99999, 99999);
                            super.desferMoviment(ini, moviment, auxPeca, colorJugador);
                            // Si el valor que s'obte de l'algoritme es major
                            // del que es te del millor moviment, agafar
                            // aquest com a millor
                            if (aux > maxF) {
                                mejorIni.x = ini.x;
                                mejorIni.y = ini.y;
                                mejorDesti.x = moviment.x;
                                mejorDesti.y = moviment.y;

                                maxF = aux;
                            }
                        }
                    }
                }
            }
        }

        origen.x = mejorIni.x;
        origen.y = mejorIni.y;
        desti.x = mejorDesti.x;
        desti.y = mejorDesti.y;

        mat -= 2;
        actualitzarProfunditat();
        System.out.println("*****" + x + "*****");
        return 0;
    }

    /**
     *
     * @param depth Profunditat que es vol arribar
     * @param maximitzar Bolea que determina si es vol maximitzar valor dels nodes
     * @return Retorna el valor seleccionat pel algoritme
     */
    private int minimax(int depth, boolean maximitzar, int alpha, int beta) {
        //System.out.println("---" + x);
        ++x;
        int estatPropi = super.estatMat(colorContrari);
        int estatOponent = super.estatMat(colorJugador);

        // L'algoritme termina si s'ha arribat a la maxima profunditat,
        // hi ha un escac i mat o si hi ha un mat i no es el torn del
        // jugador que esta en mat
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

                // Cas en que es vol maximitzar i la peca es del jugador
                if (maximitzar && (super.getColorPeca(ini) == colorJugador)) {
                    Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                    // Fer tots els moviments que pot fer la peca
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(desti) != 2) {
                            auxPeca = super.getTipusPeca(desti);
                        }
                        if (super.mourePeca(ini, desti, colorJugador)) {
                            int aux = minimax(depth - 1, false, alpha, beta);
                            super.desferMoviment(ini, desti, auxPeca, colorJugador);
                            //alpha = Math.max(alpha, aux);
                            if(alpha < aux) {
                                alpha = aux;
                            }
                            if (beta <= alpha) {
                                return aux;
                            }
                            if (max < aux) {
                                max = aux;
                            }
                        }
                    }
                }
                // Cas en que es vol minimitzar i la peca es del oponent
                else if (!maximitzar && (super.getColorPeca(ini) == (colorContrari))) {
                    Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                    // Fer tots els moviments que pot fer la peca
                    for (Posicion desti : movimentsPosibles) {
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(desti) != 2) {
                            auxPeca = super.getTipusPeca(desti);
                        }

                        if (super.mourePeca(ini, desti, colorContrari)) {
                            int aux = minimax(depth - 1, true, alpha, beta);
                            desferMoviment(ini, desti, auxPeca, colorContrari);
                            if(beta > aux) {
                                beta = aux;
                            }
                            if (beta <= alpha) {
                                return aux;
                            }
                            if (aux < min) {
                                min = aux;
                            }
                        }
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }


    /*
    private int avaluarMoviment(Posicion origen, Posicion desti) {
        if()
    }
    */

    private static class ValorPosicion {
        private Posicion pos;
        private int valor;

        public ValorPosicion(Posicion pos, int valor) {
            this.pos = pos;
            this.valor = valor;
        }

        public Posicion getPos() {
            return pos;
        }

        public int getValor() {
            return valor;
        }
    }
}
