package domini;

import java.util.ArrayList;

/**
 * Classe Maquina
 * @author Marian Dumitru Danci
 */
public abstract class Maquina extends Jugador {
    private Taulell tauX;
    private Peca pecesTauX[][];

    /**
     *
     * @return Retorna el tipus de Jugador
     */
    public int getTipus() {
        return define.MAQUINA;
    }

    public Taulell getTau() {
        return tauX;
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
     * @param torn Nombre de moviment en fer mat
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
        this.tauX = tauler;
        pecesTauX = tauX.getTauler();
    }

    /**
     *
     * @param pos Posicio del tauler
     * @return Color de le peca que es troba en la posicio donada
     */
    public int getColorPeca(Posicion pos) {
        return pecesTauX[pos.x][pos.y].getColor();
    }

    /**
     *
     * @param pos Posicio del tauler
     * @return Tipus de peca que es troba a la posicio donada
     */
    public String getTipusPeca(Posicion pos) {
        return pecesTauX[pos.x][pos.y].getTipus();
    }

    /**
     *
     * @param pos Posicio del tauler on hi ha una peca
     * @return Tots els moviments que pot fer aquella peca
     */
    public Posicion[] totsMovimentsPeca(Posicion pos) {
        return tauX.todos_movimientos(pos);
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @param color Color de la peca que es vol moure
     * @return Retorna cert si s'ha fet el moviment
     */
    public boolean mourePeca(Posicion origen, Posicion desti, int color, Taulell tau) {
        Peca pecesTau[][] = tau.getTauler();
        if(pecesTau[desti.x][desti.y].getTipus() == define.REI) {
            return false;
        }
        if(!tau.mover_pieza(origen, desti, color)) {
            return false;
        }
        return true;
    }

    /**
     * Segons l'estat del tauler li dona una puntuacio al jugador especificat
     * @param colorJugador Color del jugador que es vol puntuar
     * @return Puntuacio del tauler
     */
    public int evaluar(int colorJugador, Taulell tauFinal) {
        Peca pecesTau[][] = tauFinal.getTauler();

        int colorContrari = colorJugador ^ 1;
        int total = 0;

        // Estat del rei de l'adversari
        int escac_mat1 = tauFinal.escac_i_mat(colorContrari);
        // Mat
        if (escac_mat1 == 1) {
            total += 5000;
        }
        // Escac
        else if (escac_mat1 == 0) {
            total += 500;
        }
        // Ofegat
        else if (escac_mat1 == 2){
            total -= 1000;
        }

        // Estat del propi rei
        int escac_mat2 = tauFinal.escac_i_mat(colorJugador);
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

        Posicion[] peces = tauFinal.getPosColor(colorJugador);


        for (Posicion ini : peces) {
            // Suma valor segons tipus de peca
            total += puntuacioPeca(pecesTau[ini.x][ini.y].getTipus());

            ArrayList<Posicion> amenacades = tauFinal.getPecaPosicio(ini).getAmenacades();
            total = total + 2*amenacades.size();
        }

        Posicion[] pecesContrari = tauFinal.getPosColor(colorJugador);
        for (Posicion ini : pecesContrari) {
            total -= puntuacioPeca(pecesTau[ini.x][ini.y].getTipus());

            ArrayList<Posicion> amenacades = tauFinal.getPecaPosicio(ini).getAmenacades();
            total = total - 2*amenacades.size();
        }

        return total;
    }

    /**
     *
     * @param color Color del rei
     * @return Retorna el seguents valors segons l'estat del rei especificat
     *         0 : Escac
     *         1 : Escac i mat
     *         2: domini.Rei ofegat
     *         -1 : Altrament
     *
     */
    public int estatMat(int color) {
        return tauX.escac_i_mat(color);
    }

    /**
     * @param origen Posicio on estaba la peca
     * @param desti Posicio on es troba la peca
     * @param peca Valor de la peca que es trobaba en desti posteriorment
     * @param color Color del jugador que vol desfer un moviment
     * @return Retorna cert si el moviment s'ha desfet
     */
    public void desferMoviment(Posicion origen, Posicion desti, String peca, int color) {
        Peca aux = pecesTauX[desti.x][desti.y];

        tauX.destrueix_peca(desti);

        tauX.crear_peca(origen, color, aux.getTipus());

        // Si el moviment que s'ha fet no ha sigut sobre una peca nula es crea
        // la peca eliminada de l'oponent
        if(!peca.equals(define.PECA_NULA)) {
            tauX.crear_peca(desti, color ^ 1, peca);
        }
    }

    public Posicion[] getPosColor(int color) {
        return tauX.getPosColor(color);
    }

    public boolean escac(Posicion[] peces, Posicion rei, Posicion reiIni) {
        //return tau.escac(peces, rei, reiIni);
        return false;
    }

    public Posicion[] moviments_posibles_peca(Posicion ini, Posicion pos) {
        Peca p = tauX.getPecaPosicio(ini);
        return  p.movimientos_posibles(pos);
    }

    public Posicion getReiPos(int color) {
        return tauX.getReiPos(color);
    }
}
