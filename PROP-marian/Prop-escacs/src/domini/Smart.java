package domini;

import javafx.geometry.Pos;

import java.util.*;
import java.util.stream.Collectors;

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
    public int x;

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
        x = 0;
        System.out.println("\nCarregant moviment..." + profunditat);
        Posicion mejorIni = new Posicion(0, 0);
        Posicion mejorDesti = new Posicion(0, 0);
        int maxF = -9999;

        // Recorre el tauler
        Posicion[] peces = super.getPosColor(colorJugador);

        for (Posicion ini : peces) {
            // Tractar peca propia
            Posicion[] tots = super.totsMovimentsPeca(ini);


            List<Posicion> movimentsOrdenats = avaluarMoviment(tots, ini, colorContrari, super.getTau());


            //ArrayList<ValorPosicion> movimentsOrdenats = movimentsOrdenats(tots, ini, colorJugador);
            //for (ValorPosicion x : movimentsOrdenats) {
                //Posicion moviment = x.getPos();

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
        System.out.println("*****" + x + "*****");
        return 0;
    }

    /**
     * @param depth      Profunditat que es vol arribar
     * @param maximitzar Bolea que determina si es vol maximitzar valor dels nodes
     * @return Retorna el valor seleccionat pel algoritme
     */
    private int alphaBeta(int depth, boolean maximitzar, int alpha, int beta, Taulell tau) {
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
            return super.evaluar(colorJugador, tau);
        }
        int min, max;
        max = -9999;
        min = 9999;


        // Cas en que es vol maximitzar i la peca es del jugador
        if (maximitzar) {
            //Posicion[] peces = super.getPosColor(colorJugador);
            Posicion[] peces = tau.getPosColor(colorJugador);
            for (Posicion ini : peces) {
                //Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                Posicion[] movimentsPosibles = tau.todos_movimientos(ini);
                // Fer tots els moviments que pot fer la peca

                List<Posicion> movimentsOrdenats = avaluarMoviment(movimentsPosibles, ini, colorContrari, tau);
                //ArrayList<ValorPosicion> movimentsOrdenats = movimentsOrdenats(movimentsPosibles, ini, colorJugador);
               // for (ValorPosicion x : movimentsOrdenats) {
                    //Posicion desti = x.getPos();
                    // Fer tots els moviments que pot fer la peca
                    //for (Posicion desti : movimentsPosibles) {
                for (Posicion desti : movimentsOrdenats) {
                    /*
                    String auxPeca = define.PECA_NULA;
                    if (super.getColorPeca(desti) != define.NULL_COLOR) {
                        auxPeca = super.getTipusPeca(desti);
                    }
                    */
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    //if (super.mourePeca(ini, desti, colorJugador)) {
                    if (super.mourePeca(ini, desti, colorJugador, auxTau)) {
                        int aux = alphaBeta(depth - 1, false, alpha, beta, auxTau);
                        //super.desferMoviment(ini, desti, auxPeca, colorJugador);
                        //alpha = Math.max(alpha, aux);
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
            //Posicion[] peces = super.getPosColor(colorContrari);
            Posicion[] peces = tau.getPosColor(colorContrari);
            for (Posicion ini : peces) {
                //Posicion[] movimentsPosibles = super.totsMovimentsPeca(ini);
                Posicion[] movimentsPosibles = tau.todos_movimientos(ini);
                // Fer tots els moviments que pot fer la peca

                List<Posicion> movimentsOrdenats = avaluarMoviment(movimentsPosibles, ini, colorJugador, tau);


                //ArrayList<ValorPosicion> movimentsOrdenats = movimentsOrdenats(movimentsPosibles, ini, colorContrari);
                //for (ValorPosicion x : movimentsOrdenats) {
                    //Posicion desti = x.getPos();
                for (Posicion desti : movimentsOrdenats) {
                    // Fer tots els moviments que pot fer la peca
                    //for (Posicion desti : movimentsPosibles) {
                    /*
                    String auxPeca = define.PECA_NULA;
                    /*
                    if (super.getColorPeca(desti) != define.NULL_COLOR) {
                        auxPeca = super.getTipusPeca(desti);
                    }
                    */
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    //if (super.mourePeca(ini, desti, colorContrari)) {
                    if (super.mourePeca(ini, desti, colorContrari, auxTau)) {
                        int aux = alphaBeta(depth - 1, true, alpha, beta, auxTau);
                        //desferMoviment(ini, desti, auxPeca, colorContrari);
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
            /*
            String auxPeca = define.PECA_NULA;
            if (super.getColorPeca(x) != define.NULL_COLOR) {
                auxPeca = super.getTipusPeca(x);
            }
            boolean esEscac = false;
            if (super.mourePeca(ini, x, colorJugador)) {
                Posicion posReiContrari = getReiPos(colorContrari);
                Posicion[] aux = {x};
                esEscac = super.escac(aux, posReiContrari, posReiContrari);
                super.desferMoviment(ini, x, auxPeca, colorJugador);
            }
            */
            boolean esEscac = false;
            Peca p = tau.getPecaPosicio(ini);
            Posicion[] movePosiblesPeca = p.movimientos_posibles(desti);
            for (Posicion desti2: movePosiblesPeca) {
                if (posRei.x == desti2.x && posRei.y == desti2.y) {
                    esEscac = true;
                    //System.out.println("\n\n" + posRei.x + "-" + posRei.y + " DESTI: " + desti2.x + "-" + desti2.y);
                    break;
                }
            }
            if(esEscac) movimentsEscac.add(desti);
            else movimentsNoEscac.add(desti);
        }
        List<Posicion> movimentsOrdenats = movimentsEscac;
        movimentsOrdenats.addAll(movimentsNoEscac);

        /*
        for (Posicion x : tots) {
            System.out.println(x.x + "-" + x.y);
        }

        System.out.println("-------------------------");

        for (Posicion x : movimentsOrdenats) {
            System.out.println(x.x + "-" + x.y);
        }
        System.out.println("************************\n");
        */

        return  movimentsOrdenats;
    }


    /*
    private ArrayList<ValorPosicion> movimentsOrdenats(Posicion[] tots, Posicion ini, int color) {
        ArrayList<ValorPosicion> movimentsOrdenats = new ArrayList<>();
        for (Posicion moviment : tots) {
            //int valorMoviment = super.avaluarMoviment(aux, moviment);
            String auxPeca = define.PECA_NULA;
            if (super.getColorPeca(moviment) != define.NULL_COLOR) {
                auxPeca = super.getTipusPeca(moviment);
            }
            int valorMoviment = 0;
            if (super.mourePeca(ini, moviment, color)) {
                valorMoviment = super.evaluar(colorJugador);
                super.desferMoviment(ini, moviment, auxPeca, color);
            }
            //System.out.println("    " + moviment.x + "-" + moviment.y + " :" + valorMoviment);
            movimentsOrdenats.add(new ValorPosicion(moviment, valorMoviment));
        }

        movimentsOrdenats.sort(Comparator.comparing(ValorPosicion::getValor).reversed());

        for (Posicion moviment : tots) {
            System.out.println(moviment.x + "-" + moviment.y);
        }
        System.out.println("*********************************************************");
        for (ValorPosicion x : movimentsOrdenats) {
            Posicion moviment = x.getPos();
            System.out.println(moviment.x + "-" + moviment.y);
        }
        System.out.println("\n\n\n");

        return movimentsOrdenats;
    }




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
    */
}
