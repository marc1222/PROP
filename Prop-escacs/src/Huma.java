import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

public class Huma {
    // Fitxer ons es guarden els usuaris
    static String file = "./files/usuaris.txt";
    // nom usuari si fa login
    String idUsuari;

    Scanner sc = new Scanner(System.in);

    public void inici() {
        idUsuari = "";
        while (!opcionsEntrada());
        System.out.println("Benvingut "+ idUsuari + "!\n");
        while (opcionsSessio());
    }

    public boolean opcionsEntrada() {
        // Opcions dentrada
        System.out.println("Entrar (1)\n" +
                "Registrarse(2)");
        String opcio = sc.nextLine();

        if (opcio.equals("1")) {
            if (entrar()) {
                return true;
            }
        }
        else if (opcio.equals("2")) {
            if(resigstrar()) {
                return true;
            }
        }
        else {
            System.out.println("Opció no vàlida.\n");
        }
        return false;
    }

    public boolean entrar() {
        String contrasenya, nomUsuari;

        System.out.println("Nom usuari:");
        nomUsuari = sc.nextLine();

        if(nomUsuari != null && !nomUsuari.isEmpty()) {
            System.out.println("Contrasenya:");
            contrasenya = sc.nextLine();
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

    public boolean resigstrar() {
        System.out.println("Usuari:");
        String nomUsuari = sc.nextLine();

        System.out.println("Contrasenya:");
        String contrasenya1 = sc.nextLine();

        System.out.println("Repeteix contrasenya:");
        String contrasenya2 = sc.nextLine();

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

            idUsuari = nomUsuari;
            System.out.println("T'has registrat correctament.\n");
            return  true;
        }
    }

    public boolean opcionsSessio() {
        Scanner sc = new Scanner(System.in);

        // Opcions sessio
        System.out.println("Opcions de joc:\n" +
                "Jugar (1)\n" +
                "Veure solució (2)\n" +
                "Donar de baixa (3)\n" +
                "Tancar sessió(4)\n" +
                "Sortir del joc (5)\n");
        String opcio = sc.nextLine();

        if (opcio.equals("1")) {
            juagr();
        }
        else if(opcio.equals("2")) {
            System.out.println("Veure solució...\n");
        }
        else if(opcio.equals("3")) {
            baixa();
        }
        else if(opcio.equals("4")) {
            System.out.println("La sessió s'ha tancat.\n");
            inici();
        }
        else if(opcio.equals("5")) {
            return false;
        }
        return true;
    }

    public void baixa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Estàs segur que et vols donar de baixa? (si) o (no)");
        String confirmacio = sc.nextLine();
        if(confirmacio.equals("si")) {
            System.out.println("T'has donat de baixa correctament (MENTIRA).\n");
            inici();
        }
        else if(confirmacio.equals("no")) {
            System.out.println("No t'has donat de baixa.\n");
        }
        else {
            System.out.println("La de reposta ha de ser 'si' o 'no'.\n");
            baixa();
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


    /*
    public static void main(String[] args) {
        HashMap<String, String> Usuaris = new HashMap<String, String>();

        Scanner myObj = new Scanner(System.in);
        String userName, password1, password2;

        // Enter username and press Enter
        System.out.println("Usuari");
        userName = myObj.nextLine();
        userName = userName.toLowerCase();
        boolean usuariExisteix = false;
        // keys
        for (String i : Usuaris.keySet()) {
            if (i.equals(userName)) {
                System.out.println("L'usuari ja existeix");
                usuariExisteix = true;
            }
        }
        if (!usuariExisteix) {
            // Enter password and press Enter
            System.out.println("Constrasenya");
            password1 = myObj.nextLine();
            System.out.println("Repeteix contrasenya");
            password2 = myObj.nextLine();

            if (!password1.equals(password2)) {
                System.out.println("La contrasenya no concideix");
            }
            else {
                if(!usuariExisteix) Usuaris.put(userName, password1);
            }
            System.out.println("Username is: " + userName + "\nContra1: " + password1 + "\nContra2: " + password2);
        }

        System.out.println("MapSize: " + Usuaris.size() + "\nUsuaris: ");
        // Print keys
        for (String i : Usuaris.keySet()) {
            System.out.println(i);
        }
    }
    */
}