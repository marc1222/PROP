package domini;

import gestioDades.GestorPersistenciaUsuari;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class


/**
 * Classe domini.Usuari
 * @author Marian Dumitru Danci
 */
public class Usuari extends Jugador {
    private String idUsuari;
    private int color;
    private static final int tipus = define.USER;

    /**
     * Constructora per defecte, crea un usuari Convidat amb color null.
     */
    public Usuari () {
        idUsuari = "Convidat";
        color = define.NULL_COLOR;
    }

    /**
     * Constructora que crea un usuari 'Convidat' amb un color determinat
     * @param color Color de les peces de l'usuari
     */
    public Usuari (int color) {
        idUsuari = "Convidat";
        this.color = color;
    }

    /**
     * @return Retorna el tipus de jugador
     */
    public int getTipus() {
        return this.tipus;
    }

    /**
     *
     * @return Nom de l'usuari
     */
    public String getNom() {
        return idUsuari;
    }

    /**
     *
     * @param nom Nom a l'usuari
     */
    public void setNom(String nom) {
        idUsuari = nom;
    }

    /**
     *
     * @return Color de les peces de l'usuari
     */
    public int getColor() {
        return this.color;
    }

    /**
     *
     * @param color Color de les peces
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Segons l'usuari escollit demana la contrasenya i comproba que tot
     * els les dades concideixen amb les del fitxer
     * @param nomUsuari Nom d'usuari
     * @return Cert si l'inici de sessio es correcte, fals en cas contari
     */
    public boolean iniciarSessio(String nomUsuari, String contrasenya) {
        boolean iniciCorrecte = false;
        // Comproba que el tipus de dades es correcte
        if(nomUsuari != null && !nomUsuari.isEmpty()) {
            if (contrasenya == null && contrasenya.isEmpty()) {
                System.out.println("Contrasenya no vàlida.\n");
                return false;
            }
        }
        else {
            System.out.println("Usuari no vàlida.\n");
            return false;
        }

        iniciCorrecte = GestorPersistenciaUsuari.buscarUsuari(nomUsuari, contrasenya);
        if (iniciCorrecte) {
            idUsuari = nomUsuari;
        }
        return iniciCorrecte;
    }

    /**
     * Comproba que les dades son correctes i que el nom d'usuari no estigui registrat,
     * si tot es correcte s'afegeix una linea al fitxer amb les dades del nou usuari.
     * @param nomUsuari Nom d'usuari
     * @param contrasenya1 Contrasenya de l'usuari
     * @param contrasenya2 Contrasenya repetida de l'usuari
     * @return Cert si s'ha registrat i fals en cas contrari
     */
    public boolean registrar(String nomUsuari, String contrasenya1, String contrasenya2) {
        boolean iniciCorrecte = false;
        if(nomUsuari == null || nomUsuari.isEmpty() || nomUsuari.equals("Convidat")) {
            System.out.println("domini.Usuari no vàlida.\n");
        }
        else if(contrasenya1 == null || contrasenya1.isEmpty() ||
                contrasenya2 == null || contrasenya2.isEmpty()) {
            System.out.println("Contrasenya no vàlida.\n");
        }
        else if (!contrasenya1.equals(contrasenya2)) {
            System.out.println("La contrasenya no concideix.\n");
        }
        else {
            iniciCorrecte = GestorPersistenciaUsuari.registrar(nomUsuari, contrasenya1);
            if (iniciCorrecte) {
                // Si l'usuari s'ha registrar correctament se li dona el nom
                // d'usuari que ha fet el registre
                idUsuari = nomUsuari;
            }
        }
        return iniciCorrecte;
    }

    /**
     * D'ona de baixa el propi usuari eliminat-lo del fitxer i passa a
     * ser un usuari 'Convidat'
     * @return Cert si l'usuari s'ha donat de baixa correctament, fals altrament
     */
    public boolean eliminarUsuari(String usuari) {
        boolean eliminat = GestorPersistenciaUsuari.eliminarUsuari(usuari);
        if (eliminat) {
            idUsuari = "Convidat";
        }
        return eliminat;
    }

    /**
     *
     * @return Conjunt de Strings amb tots els usuaris registrats
     */
    public static String[] totsUsuaris() {
        ArrayList<String> usuaris = GestorPersistenciaUsuari.totsUsuaris();
        String[] totsUsuaris = new String[usuaris.size()];
        totsUsuaris = usuaris.toArray(totsUsuaris);

        return totsUsuaris;
    }

    /**
     *
     * @param origen Posicio peca seleccionada per fer el moviment
     * @param desti Posicio desti on vol que es mogui la peca
     * @return Retorna el temps (en milisegons) que es triga en fer el moviment
     */
    public long moviment(Posicion origen, Posicion desti) {
        // Inici del cronometre
        long iniCrono = System.currentTimeMillis();

        String posPeca, destiPeca;
        boolean entradaValida;
        do {
            entradaValida = true;

            System.out.println("-- MOVIMENT --\n" +
                    "Selecciona la peça que vols moure ('x y'):\n");
            Scanner sc = new Scanner(System.in);
            posPeca = sc.nextLine();
            String[] posIni = posPeca.split("\\s+");

            try {
                origen.x = Integer.parseInt(posIni[0]);
                origen.y = Integer.parseInt(posIni[1]);
            }
            catch (NumberFormatException e) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }
            if(origen.x > 7 || origen.x < 0 ||
                    origen.y > 7 || origen.y < 0) {
                entradaValida = false;
                System.out.println("Entrada invalida.");
            }

            if (entradaValida) {
                System.out.println("Selecciona la casella de destí ('x y'):\n");
                destiPeca = sc.nextLine();
                String[] posDesti = destiPeca.split("\\s+");
                try {
                    desti.x = Integer.parseInt(posDesti[0]);
                    desti.y = Integer.parseInt(posDesti[1]);
                }
                catch (NumberFormatException e) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }

                if(desti.x > 7 || desti.x < 0 ||
                        desti.y > 7 || desti.y < 0) {
                    entradaValida = false;
                    System.out.println("Entrada invalida.");
                }
            }
        } while(!entradaValida);

        long tempsMoviment = System.currentTimeMillis() - iniCrono;;
        return tempsMoviment;
    }
}
