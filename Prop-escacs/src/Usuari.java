import java.util.Scanner;
import java.io.*;
// Import the IOException class to handle errors
// Import the FileWriter class

public class Usuari {
    // Fitxer ons es guarden els usuaris
    static String file = "./files/usuaris.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrar (0) o registrarse(1)");


        int tipus = sc.nextInt();
        sc.nextLine();
        Usuari usuari = new Usuari();
        if (tipus == 0) {
            // String input
            System.out.println("Usuari:");
            String idUsuari = sc.nextLine();
            if(idUsuari != null && !idUsuari.isEmpty()) {
                System.out.println("Contrasenya:");
                String contrasenya = sc.nextLine();
                usuari.entrar(idUsuari, contrasenya);
            }
        }
        else if (tipus == 1) {
            System.out.println("Usuari:");
            String idUsuari = sc.nextLine();
            System.out.println("Contrasenya:");
            String contrasenya1 = sc.nextLine();
            System.out.println("Repeteix contrasenya:");
            String contrasenya2 = sc.nextLine();
            usuari.resigstrar(idUsuari, contrasenya1, contrasenya2);
        }
        else {
            System.out.println("0 o 1 dije");
        }
    }

    public void entrar(String idUsuari, String constrasenya) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean existeix = false;
            String line;
            while ((line = br.readLine()) != null) {
                String[] dades = line.split("\\s+");
                if(dades[0].equals(idUsuari)) {
                    if (dades[1].equals(constrasenya)) {
                        System.out.println("Has entrat amb exit.");
                    }
                    else {
                        System.out.println("Contraseya incorrecta.");
                    }
                    existeix = true;
                }
            }
            if (!existeix) {
                System.out.println("Usuari no existeix.");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resigstrar(String idUsuari, String contrasenya1, String contrasenya2) {
        if(!contrasenya1.equals(contrasenya2)) {
            System.out.println("La contrasenya no concideix.");
        }
        else {
            boolean existeix = false;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] dades = line.split("\\s+");
                    if(dades[0].equals(idUsuari) && !existeix) {
                        if (dades[0].equals(idUsuari)) {
                            System.out.println("L'usuari ja existeix.");
                            existeix = true;
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if(!existeix) {
                input_output IO = new input_output();
                String[] dades = {idUsuari, contrasenya2};
                IO.write(file, dades);
                System.out.println("Registrat correctament.");
            }
        }
    }
}