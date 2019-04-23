import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

/**
 * @author Marian Dumitru Danci
 */
public abstract class Jugador {

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna el temps (en milisegons) que triga en fer un moviment
     */
    public abstract long moviment(Posicion origen, Posicion desti);

    /**
     *
     * @return Retorn el tipus de jugador que es
     */
    public abstract int getTipus();
}