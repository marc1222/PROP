import java.io.*;
import java.util.ArrayList;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

/**
 * @author Marian Dumitru Danci
 */
public class Usuari extends Jugador{
    String idUsuari;
    int color;
    int tipus;


    // Fitxer ons es guarden els usuaris
    static String fitxerUsuaris = "./files/usuaris.txt";

    Scanner sc = new Scanner(System.in);

    public Usuari () {
        idUsuari = "Anònim";
        color = define.NULL_COLOR;
        tipus = define.USER;
    }

    public Usuari (int color) {
        idUsuari = "Anònim";
        this.color = color;
        tipus = define.USER;
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

    public boolean iniciarSessio() {
        String nomUsuari, contrasenya;

        System.out.println("Nom usuari:");
        nomUsuari = sc.nextLine();

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
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
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
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
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
            System.out.println("Problem reading file.");
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
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] totsUsuaris = new String[usuaris.size()];
        totsUsuaris = usuaris.toArray(totsUsuaris);
        return totsUsuaris;
    }

    public long moviment(Posicion ini, Posicion fi) {
        long iniCrono = 0;

        iniCrono = System.currentTimeMillis();

        String posPeca, destiPeca;


        System.out.println("Escull la peça que vols moure\n");
        posPeca = sc.nextLine();
        String[] posIni = posPeca.split("\\s+");
        ini.x = Integer.parseInt(posIni[0]);
        ini.y = Integer.parseInt(posIni[1]);

        System.out.println("Escriu la posició de destí\n");
        destiPeca = sc.nextLine();
        String[] posDesti = destiPeca.split("\\s+");
        fi.x = Integer.parseInt(posDesti[0]);
        fi.y = Integer.parseInt(posDesti[1]);

        long time = 0;
        long tempsCrono = System.currentTimeMillis() - iniCrono;
        time += tempsCrono;
        return time;
    }

    /*
    private boolean movimentCorrecte() {

    }

    public void juagr() {
        // Opcions sessio
        System.out.println("Modes de joc:" +
                "\nUsuari 1 vs Usuari 2 (1)\nUsuari 1 vs Maquina (2)" +
                "\nMaquina vs Usuari 1 (3)\nMaquina 1 vs Maquina1(4)");
        String mode = sc.nextLine();
        System.out.println("Mode: " + mode + ".\n");
    }
    */
}