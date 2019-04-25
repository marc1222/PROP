/**
 * Classe que juga una partida amb uns recursos limitats, pels cuals no sempre
 * aconsegueix fer el mat en el minim de jugades.
 * S'aplica l'algoritme Minimax de la teoria de jocs, un tipus de
 * 'backtracking' amb una profunditat limitada i a on a cada node se li dona
 * un valor. Segons el valor del node es minimitza la maxima perdua esperada.
 * @author Marian Dumitru Danci
 */
public class Naive extends Maquina {
    private int profunditat;
    private int mat;
    private int colorJugador;
    private int colorContrari;

    public Naive(int color) {
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
        this.mat = mat;
        actualitzarProfunditat();
    }

    /**
     * Actualitza la profunditat limit
     */
    public void actualitzarProfunditat() {
        if (mat > 2) profunditat = 2;
        else profunditat = mat;
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna 0
     */
    public long moviment(Posicion origen, Posicion desti) {
        System.out.println("\nCarregant moviment...");
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
                    // Fer tots els moviments que pot fer la peca
                    for (Posicion moviment : tots) {
                        // Si el moviment es a una casella amb una peca de
                        // l'oponent, es guarda el tipus de la peca
                        String auxPeca = define.PECA_NULA;
                        if(super.getColorPeca(moviment) != 2) {
                            auxPeca = super.getTipusPeca(moviment);
                        }
                        if (super.mourePeca(ini, moviment, colorJugador)) {
                            // Si s'ha fet el moviment correctament,
                            // cridar al algortime minimax
                            int aux = minimax(profunditat, false);
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

        --mat;
        actualitzarProfunditat();
        return 0;
    }

    /**
     *
     * @param depth Profunditat que es vol arribar
     * @param maximitzar Bolea que determina si es vol maximitzar valor dels nodes
     * @return Retorna el valor seleccionat pel algoritme
     */
    private int minimax(int depth, boolean maximitzar) {
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
                            int aux = minimax(depth - 1, false);
                            if (aux > max) {
                                max = aux;
                            }
                            super.desferMoviment(ini, desti, auxPeca, colorJugador);
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
                            int aux = minimax(depth - 1, true);
                            if (aux < min) {
                                min = aux;
                            }
                            desferMoviment(ini, desti, auxPeca, colorContrari);
                        }
                    }
                }
            }
        }
        if (maximitzar) return max;
        return  min;
    }
}