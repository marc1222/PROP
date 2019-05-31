package domini;

/**
 * Classe Naive
 *
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
        if (mat > 2) profunditat = 2;
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
            for (Posicion moviment : tots) {
                Taulell auxTau = new Taulell(super.getTau().getTauler(), true);
                if (super.mourePeca(ini, moviment, colorJugador, auxTau)) {
                    int aux = minimax(profunditat, false, auxTau);
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
    private int minimax(int depth, boolean maximitzar, Taulell tau) {
        int estatPropi = tau.escac_i_mat(colorContrari);
        int estatOponent = tau.escac_i_mat(colorJugador);

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
                // Fer tots els moviments que pot fer la peca
                for (Posicion desti : movimentsPosibles) {
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    //if (super.mourePeca(ini, desti, colorJugador)) {
                    if (super.mourePeca(ini, desti, colorJugador, auxTau)) {
                        int aux = minimax(depth - 1, false, auxTau);
                        if (aux > max) {
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
                for (Posicion desti : movimentsPosibles) {
                    Taulell auxTau = new Taulell(tau.getTauler(), true);
                    if(super.mourePeca(ini, desti, colorContrari, auxTau)) {
                        int aux = minimax(depth - 1, true, auxTau);
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
}
