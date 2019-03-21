import java.util.Scanner; // import the Scanner class
import java.util.HashMap; // import the HashMap class

public class Huma {
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
}