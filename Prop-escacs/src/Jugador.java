import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

/**
 * @author Marian Dumitru Danci
 */
public class Jugador {
    // Fitxer ons es guarden els usuaris
    static String file = "./files/usuaris.txt";

    /**
     *
     * @param nomUsuari
     * @param contrasenya
     * @return
     */
    public boolean entrar(String nomUsuari, String contrasenya) {
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

        // Si nom i contrasenya no buits comprobar que existeix lusuari
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Recorrer fitxer per cada linea
            while ((line = br.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = line.split("\\s+");
                if(dades[0].equals(nomUsuari)) {
                    if (dades[1].equals(contrasenya)) {
                        //idUsuari =  nomUsuari;
                        return true;
                    }
                    else {
                        System.out.println("Contraseya incorrecta.\n");
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Usuari no existeix.\n");
        return false;
    }

    /**
     *
     * @param nomUsuari
     * @param contrasenya1
     * @param contrasenya2
     * @return
     */
    public boolean resigstrar(String nomUsuari, String contrasenya1, String contrasenya2) {
        if(!contrasenya1.equals(contrasenya2)) {
            System.out.println("La contrasenya no concideix.\n");
            return false;
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                // Recorrer fitxer per linies
                while ((line = br.readLine()) != null) {
                    // Separar nom i contrasenya
                    String[] dades = line.split("\\s+");
                    if(dades[0].equals(nomUsuari)) {
                        if (dades[0].equals(nomUsuari)) {
                            System.out.println("L'usuari ja existeix.\n");
                            return false;
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Registre correcte i es guarda usuari a fitxer
            input_output IO = new input_output();
            String[] dades = {nomUsuari, contrasenya2};
            IO.write(file, dades);

            //idUsuari = nomUsuari;
            System.out.println("T'has registrat correctament.\n");
            return  true;
        }
    }

    public String[] totsUsuaris() {
        ArrayList<String> usuaris = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Recorrer fitxer per cada linea
            while ((line = br.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = line.split("\\s+");
                usuaris.add(dades[0]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] totsUsuaris = new String[usuaris.size()];
        totsUsuaris = usuaris.toArray(totsUsuaris);
        return totsUsuaris;
    }
}