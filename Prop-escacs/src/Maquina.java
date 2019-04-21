/**
 * @author Marian Dumitru Danci
 */
public abstract class Maquina extends Jugador {
    public abstract long moviment(Posicion inici, Posicion fi);

    public abstract int getTipus();
    public abstract void setTauler(Taulell t);
    /*
    evaluar()
    puntuacioPeca()
     */
}