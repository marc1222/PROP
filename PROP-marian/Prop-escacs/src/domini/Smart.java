package domini;

import java.util.*;

/**
 * Classe Smart
 *
 * @author Marian Dumitru Danci
 */
public class Smart extends Maquina {
    private int profunditat;
    private int mat;
    private int colorJugador;
    private int colorContrari;

    public Smart(int color) {
        colorJugador = color;
        colorContrari = color ^ 1;
    }

    /**
     * @return Tipus de jugador
     */
    public int getTipus() {
        return super.getTipus();
    }

    /**
     * @param tauler Tauler de la partida
     */
    public void setTauler(Taulell tauler) {
        super.setTaulerMaquina(tauler);
    }

    /**
     * Segons el nombre de jugades en fer mat s'especifica una profunditat,
     * especificant un maxim
     *
     * @param mat Nombre de jugades en asolir mat del problema
     */
    public void setProfunditat(int mat) {
        this.mat = mat * 2 - 2;
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
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti  Posicio desti on vol que es mogui la peca
     * @return Retorna 0
     */
    public long moviment(Posicion origen, Posicion desti) {
        System.out.println("\nCarregant moviment...");
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF = -9999;


        Posicion[] peces = super.getPosColor(colorJugador);
        for (Posicion ini : peces) {
            Posicion[] tots = super.totsMovimentsPeca(ini);
            List<Posicion> movimentsOrdenats = avaluarMoviment(tots, ini, colorContrari, super.getTau());

            for (Posicion moviment : movimentsOrdenats) {
                // Fer tots els moviments que pot fer la peca
                //for (Posicion moviment : tots) {
                // Si el moviment es a una casella amb una peca de
                // l'oponent, es guarda el tipus de la peca
                /*
                String auxPeca = define.PECA_NULA;
                if (super.getColorPeca(moviment) != define.NULL_COLOR) {
                    auxPeca = super.getTipusPeca(moviment);
                }
                */
                Taulell auxTau = new Taulell(super.getTau().getTauler(), true);
                if (super.mourePeca(ini, moviment, colorJugador, auxTau)) {
                    // Si s'ha fet el moviment correctament,
                    // cridar al algortime minimax
                    int aux = alphaBeta(profunditat, false, -99999, 99999, auxTau);
                    //super.desferMoviment(ini, moviment, auxPeca, colorJugador);
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

        origen.x = mejorIni.x;
        origen.y = mejorIni.y;
        desti.x = mejorDesti.x;
        desti.y = mejorDesti.y;

        mat -= 2;
        actualitzarProfunditat();
        return 0;
    }

    /**
     * @param depth      Profunditat que es vol arribar
     * @param maximitzar Bolea que determina si es vol maximitzar valor dels nodes
     * @return Retorna el valor seleccionat pel algoritme
     */
    private int alphaBeta(int depth, boolean maximitzar, int alpha, int beta, Taulell tau) {
        int estatPropi = super.estatMat(colorContrari);
        int estatOponent = super.estatMat(colorJugador);

        // L'algoritme termina si s'ha arribat a la maxima profunditat,
        // hi ha un escac i mat o si hi ha un mat i no es el torn del
        // jugador que esta en mat
        if (depth == 0 ||
                (estatPropi == 1) || (estatOponent == 1) ||
                (estatPropi == 2 && !maximitzar) || (estatOponent == 2 && maximitzar)) {
            return super.evaluar(colorJugador, tau);
        }
        int min, max;
        max = -9999;
        min = 9999;


        // Cas en que es vol maximitzar i la peca es del jugador
        if (maximitzar) {
            Posicion[] peces = tau.getPosColor(colorJugador);
            for (Posicion ini : peces) {
                Posicion[] movimentsPosibles = tau.todos_movimientos(ini);

                List<Posicion> movimentsOrdenats = avaluarMoviment(movimentsPosibles, ini, colorContrari, tau);
                for (Posicion desti : movimentsOrdenats) {
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    if (super.mourePeca(ini, desti, colorJugador, auxTau)) {
                        int aux = alphaBeta(depth - 1, false, alpha, beta, auxTau);
                        if (alpha < aux) {
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
        }
        // Cas en que es vol minimitzar i la peca es del oponent
        else if (!maximitzar) {
            Posicion[] peces = tau.getPosColor(colorContrari);
            for (Posicion ini : peces) {
                Posicion[] movimentsPosibles = tau.todos_movimientos(ini);

                List<Posicion> movimentsOrdenats = avaluarMoviment(movimentsPosibles, ini, colorJugador, tau);
                for (Posicion desti : movimentsOrdenats) {
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    if (super.mourePeca(ini, desti, colorContrari, auxTau)) {
                        int aux = alphaBeta(depth - 1, true, alpha, beta, auxTau);
                        if (beta > aux) {
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
        if (maximitzar) return max;
        return min;
    }

    private List<Posicion> avaluarMoviment(Posicion[] tots, Posicion ini, int color, Taulell tau) {
        List<Posicion> movimentsEscac = new ArrayList<Posicion>();
        List<Posicion> movimentsNoEscac = new ArrayList<Posicion>();

        Posicion posRei = tau.getReiPos(color);
        for (Posicion desti : tots) {
            boolean esEscac = false;
            Peca p = tau.getPecaPosicio(ini);
            Posicion[] movePosiblesPeca = p.movimientos_posibles(desti);
            for (Posicion desti2: movePosiblesPeca) {
                if (posRei.x == desti2.x && posRei.y == desti2.y) {
                    esEscac = true;
                    break;
                }
            }
            if(esEscac) movimentsEscac.add(desti);
            else movimentsNoEscac.add(desti);
        }
        List<Posicion> movimentsOrdenats = movimentsEscac;
        movimentsOrdenats.addAll(movimentsNoEscac);

        return  movimentsOrdenats;
    }
}
