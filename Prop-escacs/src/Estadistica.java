import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

public class Estadistica {
    // Fitxer ons es guarden les estadistiques
    static String file = "./files/estadistiques.txt";

    /*
    public static void main(String[] args) {
        input_output IO = new input_output();
        Estadistica est = new Estadistica();

        est.guardarTiempo("1", "2", "4:10");
        est.estadistiquesProblema("p1");
        est.estadistiquesUsuari("u2");
    }
    */

    // pre: problema i usuari existeix
    // psot: es guarda una la linea al fitxer (problema usuari temps)
    public void guardarTiempo(String problema, String usuari, String temps) {
        input_output IO = new input_output();
        String[] dades = {problema, usuari, temps};
        IO.write(file, dades);
    }

    // pre: problema existeix
    // post: retorna tots els usuaris que han resolt el problema amb el seu temps
    public void estadistiquesProblema(String problema) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Jugador Temps");
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[0].equals(problema)) {
                    System.out.println(dades[1] + "  " + dades[2]);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // pre: usuari existeix
    // post: retorna tots els problemes resolts amb el seu temps
    public void estadistiquesUsuari(String usuari) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Problema Temps");
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[1].equals(usuari)) {
                    System.out.println(dades[0] + "  " + dades[2]);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /*

    int linea = 1;
    String[] z = IO.read(file, linea);
    for (String i : z) {
        System.out.println(i);
    }

    private int novaLinea() {
        int numLinea;
        input_output IO = new input_output();
        String[] z = IO.read("../Dades/estadistiques.txt", 1);
        numLinea = Integer.parseInt(z[0]) + 1;
        String[] x = {String.valueOf(numLinea)};
        IO.write("../Dades/estadistiques.txt", x);
        return Integer.parseInt(z[0]);
    }

    public void estadisticasProblema(String problema) {
        input_output IO = new input_output();
        String[] dades = {"0"};
        int linea = 1;

        dades = IO.read("./files/estadistiques.txt", linea);
        while (!"NF".equals(dades[0])) {
            if (dades[1].equals(problema)) {
                System.out.println(dades[2] + " " + dades[3]);
            }
            //System.out.println("linea "+ linea + " " + dades[0]);
            ++linea;
            dades = IO.read("./files/estadistiques.txt", linea);
        }
    }

    public void estadisticasUsuari(String problema) {
        input_output IO = new input_output();
        String[] dades = {"0"};
        int linea = 1;

        dades = IO.read("./files/estadistiques.txt", linea);
        while (!"NF".equals(dades[0])) {
            if (dades[2].equals(problema)) {
                System.out.println(dades[1] + " " + dades[3]);
            }
            //System.out.println("linea "+ linea + " " + dades[0]);
            ++linea;
            dades = IO.read("./files/estadistiques.txt", linea);
        }
    }
    */
}