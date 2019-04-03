import java.io.*;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

/**
 * @author Marian Dumitru Danci
 */
public class Usuari extends Jugador{
    // Fitxer ons es guarden les estadistiques
    static String fitxerUsuaris = "./files/usuaris.txt";
    // nom usuari si fa login
    String idUsuari;
    boolean ataca;
    long time;

    Scanner sc = new Scanner(System.in);

    public Usuari () {
        time = 0;
    }

    public boolean baixa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Estàs segur que et vols donar de baixa? (si) o (no)");
        String confirmacio = sc.nextLine();
        if(confirmacio.equals("si")) {
            eliminarUsuari(idUsuari);
            System.out.println("T'has donat de baixa correctament (MENTIRA).\n");
            return true;
        }
        else if(confirmacio.equals("no")) {
            System.out.println("No t'has donat de baixa.\n");
        }
        else {
            System.out.println("La de reposta ha de ser 'si' o 'no'.\n");
            baixa();
        }
        return false;
    }

    public void eliminarUsuari(String usuari) {
        /*
        try {
            BufferedReader file = new BufferedReader(new FileReader(fitxerUsuaris));
            String line;
            StringBuffer inputBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            String inputStr = inputBuffer.toString();

            file.close();

            // escriure el nou String amb la modficacio en les lineas sobre el mateix fitxer
            FileOutputStream fileOut = new FileOutputStream(fitxerUsuaris);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
        */
        try {
            File inputFile = new File(fitxerUsuaris);
            File tempFile = new File("./files/tmpUsuaris.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
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
            boolean successful = tempFile.renameTo(inputFile);

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }


    }

    public void juagr() {
        // Opcions sessio
        System.out.println("Modes de joc:" +
                "\nUsuari 1 vs Usuari 2 (1)\nUsuari 1 vs Maquina (2)" +
                "\nMaquina vs Usuari 1 (3)\nMaquina 1 vs Maquina1(4)");
        String mode = sc.nextLine();
        System.out.println("Mode: " + mode + ".\n");
    }

    public void moure() {
        long iniCrono = System.currentTimeMillis();


        long tempsCrono = System.currentTimeMillis() - iniCrono;
        time += tempsCrono;
    }
}