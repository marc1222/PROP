import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

/**
 * @author Marian Dumitru Danci
 */
public class Usuari extends Jugador{
    private String idUsuari;
    private int color;
    private static final int tipus = define.USER;

    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./files/usuaris.txt";

    Scanner sc = new Scanner(System.in);

    public Usuari () {
        idUsuari = "Anònim";
        color = define.NULL_COLOR;
    }

    public Usuari (int color) {
        idUsuari = "Anònim";
        this.color = color;
    }

    public static String getFitxerUsuaris() {
        return fitxerUsuaris;
    }

    public static void setFitxerUsuaris(String fitxer) {
        fitxerUsuaris = fitxer;
    }

    public int getTipus() {
        return this.tipus;
    }

    public String getNom() {
        return idUsuari;
    }

    public void setNom(String nom) {
        idUsuari = nom;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean iniciarSessio(String nomUsuari) {
        String contrasenya;

        System.out.println("Contrasenya:");
        contrasenya = sc.nextLine();

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
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line;
            // Recorrer fitxer per cada linea
            while ((line = br.readLine()) != null) {
                // Separar nom i contrasenya
                String[] dades = line.split("\\s+");
                if(dades[0].equals(nomUsuari)) {
                    if (dades[1].equals(contrasenya)) {
                        idUsuari =  nomUsuari;
                        return true;
                    }
                    else {
                        System.out.println("Contraseya incorrecta.\n");
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El fitxer no existeix");
        } catch (IOException ex) {
            System.out.println("El format d'entrada o sortida no és correcte");
        }
        System.out.println("Usuari no existeix.\n");
        return false;
    }

    public boolean registrar() {
        String nomUsuari, contrasenya1, contrasenya2;
        System.out.println("Usuari:");
        nomUsuari = sc.nextLine();

        System.out.println("Contrasenya:");
        contrasenya1 = sc.nextLine();

        System.out.println("Repeteix contrasenya:");
        contrasenya2 = sc.nextLine();


        if(!contrasenya1.equals(contrasenya2)) {
            System.out.println("La contrasenya no concideix.\n");
            return false;
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
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
                System.out.println("El fitxer no existeix");
            } catch (IOException ex) {
                System.out.println("El format d'entrada o sortida no és correcte");
            }
            // Registre correcte i es guarda usuari a fitxer
            input_output IO = new input_output();
            String[] dades = {nomUsuari, contrasenya2};
            IO.write(fitxerUsuaris, dades);

            idUsuari = nomUsuari;
            System.out.println("T'has registrat correctament.\n");
            return  true;
        }
    }

    public boolean baixa() {
        System.out.println("Estàs segur que et vols donar de baixa? (si) o (no)");
        String confirmacio = sc.nextLine();
        if(confirmacio.equals("si")) {
            eliminarUsuari(idUsuari);
            idUsuari = "Anònim";
            System.out.println("T'has donat de baixa correctament.\n");
            return true;
        }
        else if(confirmacio.equals("no")) {
            System.out.println("S'ha cancelat la baixa.\n");
        }
        else {
            System.out.println("La de reposta ha de ser 'si' o 'no'.\n");
            baixa();
        }
        return false;
    }

    private void eliminarUsuari(String usuari) {
        try {
            File tempFile = File.createTempFile("./files/tmpUsuaris.txt", "");

            BufferedReader reader = new BufferedReader(new FileReader(fitxerUsuaris));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                String[] dades = currentLine.split("\\s+");
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(dades[0].equals(usuari)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            File oldFile = new File(fitxerUsuaris);
            if (oldFile.delete()) {
                tempFile.renameTo(oldFile);
            }

        } catch (Exception e) {
            System.out.println("El fitxer no existeix");
        }
    }

    public static String[] totsUsuaris() {
        ArrayList<String> usuaris = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line;
            // Recorrer fitxer per cada linea
            while ((line = br.readLine()) != null) {
                // Separar nom i contrasenya per espai
                String[] dades = line.split("\\s+");
                usuaris.add(dades[0]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El fitxer no existeix");
        } catch (IOException ex) {
            System.out.println("El format d'entrada o sortida no és correcte");
        }

        String[] totsUsuaris = new String[usuaris.size()];
        totsUsuaris = usuaris.toArray(totsUsuaris);
        return totsUsuaris;
    }

    public long moviment(Posicion origen, Posicion desti) {
        long iniCrono = 0;
        iniCrono = System.currentTimeMillis();

        String posPeca, destiPeca;
        boolean entradaValida;

        do {
            entradaValida = true;

            System.out.println("-- MOVIMENT --\n" +
                    "Selecciona la peça que vols moure ('x y'):\n");
            posPeca = sc.nextLine();
            String[] posIni = posPeca.split("\\s+");

            try {
                origen.x = Integer.parseInt(posIni[0]);
                origen.y = Integer.parseInt(posIni[1]);
            } catch (NumberFormatException e) {
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
                } catch (NumberFormatException e) {
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

        long time = 0;
        long tempsCrono = System.currentTimeMillis() - iniCrono;
        time += tempsCrono;
        return time;
    }

    public static void mostrarsetFitxerUsuaris() {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerUsuaris))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("El fitxer no existeix");
        }
    }
}