/**
 * @author Marian Dumitru Danci
 */
public abstract class Maquina extends Jugador {
    private Taulell Tauler;
    private Peca pecesTau[][];
    private static final int tipus = define.MAQUINA;


    /**
     *
     * @return Retorna el tipus de Jugador
     */
    public int getTipusMaquina() {
        return this.tipus;
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna 0
     */
    public abstract long moviment(Posicion origen, Posicion desti);

    /**
     *
     * @param t Tauler de la partida
     */
    public abstract void setTauler(Taulell t);

    /**
     *
     * @param torn Nombre de mat
     */
    public abstract void setProfunditat(int torn);

    /**
     * Segons el tipus de peca retorna el valor que es dona segons
     * l'importancia que te per guanyar una partida
     * @param tipus Tipus de peca
     * @return Valor de la peca
     */
    public int puntuacioPeca(String tipus) {
        int puntuacio = 0;
        switch (tipus) {
            case define.PEO:
                puntuacio = 10;
                break;
            case define.CAVALL:
                puntuacio = 35;
                break;
            case define.ALFIL:
                puntuacio = 35;
                break;
            case define.TORRE:
                puntuacio = 52;
                break;
            case define.REINA:
                puntuacio = 100;
                break;
        }
        return puntuacio;
    }

    /**
     *
     * @param tauler Tauler de la partida
     */
    public void setTaulerMaquina(Taulell tauler) {
        this.Tauler = tauler;
        pecesTau = Tauler.getTauler();
    }

    /**
     *
     * @param pos Posicio del tauler
     * @return Color de le peca que es troba en la posicio donada
     */
    public int getColorPeca(Posicion pos) {
        return pecesTau[pos.x][pos.y].getColor();
    }

    /**
     *
     * @param pos Posicio del tauler
     * @return Tipus de peca que es troba a la posicio donada
     */
    public String getTipusPeca(Posicion pos) {
        return pecesTau[pos.x][pos.y].getTipus();
    }

    /**
     *
     * @param pos Posicio del tauler on hi ha una peca
     * @return Tots els moviments que pot fer aquella peca
     */
    public Posicion[] totsMovimentsPeca(Posicion pos) {
        return Tauler.todos_movimientos(pos);
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @param color Color de la peca que es vol moure
     * @return Retorna cert si s'ha fet el moviment
     */
    public boolean mourePeca(Posicion origen, Posicion desti, int color) {
        //pintar();
        //System.out.println("****");
        if(pecesTau[desti.x][desti.y].getTipus() == define.REI) {
            //System.out.println("\nMATA REI");
            //System.out.println("POS: "+ origen.x + "-" + origen.y + "  " + desti.x + "-" + desti.y);
            //pintar();
            return false;
        }
        if(!Tauler.mover_pieza(origen, desti, color)) {
            //System.out.println("\nPOS: "+ origen.x + "-" + origen.y + "  " + desti.x + "-" + desti.y);
            return false;
        }
        return true;
    }

    /**
     * Segons l'estat del tauler li dona una puntuacio al jugador donat
     * @param colorJugador Color del jugador que es vol puntuar
     * @return Puntuacio del tauler
     */
    public int evaluar(int colorJugador) {
        int colorContrari = colorJugador ^ 1;
        int total = 0;

        // Estat del rei de l'adversari
        int escac_mat1 = Tauler.escac_i_mat(colorContrari);
        // Mat
        if (escac_mat1 == 1) {
            total += 5000;
        }
        // Escac
        else if (escac_mat1 == 0) {
            total += 1000;
        }
        // Ofegat
        else if (escac_mat1 == 2){
            total -= 1000;
        }

        // Estat del propi rei
        int escac_mat2 = Tauler.escac_i_mat(colorJugador);
        // Mat
        if (escac_mat2 == 1) {
            total -= 5000;
        }
        // Escac
        else if (escac_mat2 == 0) {
            total -= 500;
        }
        // Ofegat
        else if (escac_mat2 == 2){
            total += 1000;
        }

        for (int i = 0; i < pecesTau.length; i++) {
            for (int j = 0; j < pecesTau[0].length; j++) {
                if (pecesTau[i][j].getColor() == colorJugador) {
                    Posicion ini = new Posicion(i, j);

                    // Suma valor segons tipus de peca
                    total += puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    // Suma la mobilitat de la peca
                    total += movimentsPosibles.length;

                    // Suma les peces que esta amenacant
                    for (Posicion desti : movimentsPosibles) {
                        if (pecesTau[desti.x][desti.y].getColor() != colorJugador) {
                            total += 2;
                        }
                    }
                }
                else if (pecesTau[i][j].getColor() == colorContrari) {
                    Posicion ini = new Posicion(i, j);

                    // Resta valor segons el tipus de peca
                    total -= puntuacioPeca(pecesTau[i][j].getTipus());

                    Posicion[] movimentsPosibles = Tauler.todos_movimientos(ini);
                    // Resta la mobilitat de la peca
                    total -= movimentsPosibles.length;

                    // Resta les peces que esta amenacant
                    for (Posicion desti : movimentsPosibles) {
                        if (pecesTau[desti.x][desti.y].getColor() == colorJugador) {
                            total -= 2;
                        }
                    }
                }
            }
        }
        //System.out.println("PUNTS : " + total);
        //System.out.println("ESCAC A PROPI: " + Tauler.escac_i_mat(colorJugador));
        //System.out.println("ESCAC A ADV: " + Tauler.escac_i_mat(colorContrari));
        //Tauler.printTauler();
        return total;
    }

    /**
     *
     * @param color Color del rei
     * @return Retorna el seguents valors segons l'estat del rei especificat
     *         0 : Escac
     *         1 : Escac i mat
     *         2: Rei ofegat
     *         -1 : Altrament
     *
     */
    public int estatMat(int color) {
        return Tauler.escac_i_mat(color);
    }

    /**
     * @param origen Posicio on estaba la peca
     * @param desti Posicio on es troba la peca
     * @param peca Valor de la peca que es trobaba en desti posteriorment
     * @param color Color del jugador que vol desfer un moviment
     * @return Retorna cert si el moviment s'ha desfet
     */
    public void desferMoviment(Posicion origen, Posicion desti, String peca, int color) {
        //System.out.println("\n+++++\n" + peca + " ORIG: "+ origen.x + "-" + origen.y + " " + desti.x + "-" + desti.y);
        //pintar();
        Peca aux = pecesTau[desti.x][desti.y];
        Tauler.destrueix_peça(desti);
        //pintar();
        Tauler.crear_peça(origen, color, aux.getTipus());
        //pintar();

        // Si el moviment que s'ha fet no ha sigut sobre una peca nula es crea
        // la peca eliminada de l'oponent
        if(!peca.equals(define.PECA_NULA)) {
            Tauler.crear_peça(desti, color ^ 1, peca);
        }
    }

    public void pintar() {
        Tauler.printTauler();
    }
}