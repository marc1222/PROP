package domini;
/**
 * Classe Jugador
 * @author Marian Dumitru Danci
 */
public abstract class Jugador {

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna el temps (en milisegons) que es triga en fer el moviment
     */
    public abstract long moviment(Posicion origen, Posicion desti);

    /**
     *
     * @return Retorn el tipus de jugador
     */
    public abstract int getTipus();
}
