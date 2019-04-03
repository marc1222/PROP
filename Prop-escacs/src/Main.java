import java.util.Scanner; // import the Scanner class

public class Main{
    static Scanner sc = new Scanner(System.in);
    static Jugador jug = new Jugador();

    public static void main(String[] args) {
        /*
        inici();
        */

        /*
        String[] x = jug.totsUsuaris();
        for (String s : x) {
            System.out.println(s);
        }
        */

        /*
        Estadistica est = new Estadistica();
        //est.eliminatStatsUsuari("u2");
        //est.estadistiquesProblema("p1");
        //est.estadistiquesUsuari("u2");
        */

        Usuari usr = new Usuari();
        usr.eliminarUsuari("usr2");

    }

    public static void inici() {
        while (!opcionsEntrada());
        System.out.println("Benvingut!\n");
        while (opcionsSessio());
    }

    public static boolean opcionsEntrada() {
        // Opcions dentrada
        System.out.println("Entrar (1)\n" +
                "Registrarse(2)");
        String opcio = sc.nextLine();

        if (opcio.equals("1")) {
            String nomUsuari, contrasenya;

            System.out.println("Nom usuari:");
            nomUsuari = sc.nextLine();

            System.out.println("Contrasenya:");
            contrasenya = sc.nextLine();

            if (jug.entrar(nomUsuari, contrasenya)) {
                return true;
            }
        }
        else if (opcio.equals("2")) {
            String nomUsuari, contrasenya1, contrasenya2;
            System.out.println("Usuari:");
            nomUsuari = sc.nextLine();

            System.out.println("Contrasenya:");
            contrasenya1 = sc.nextLine();

            System.out.println("Repeteix contrasenya:");
            contrasenya2 = sc.nextLine();
            if(jug.resigstrar(nomUsuari, contrasenya1, contrasenya2)) {
                return true;
            }
        }
        else {
            System.out.println("Opció no vàlida.\n");
        }
        return false;
    }

    public static boolean opcionsSessio() {
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
            System.out.println("Usuari vs Usuari\n" +
                    "Usuari vs Naive\n" +
                    "Naive vs Usuari\n" +
                    "Naive vs Naive\n");
        }
        else if(opcio.equals("2")) {
            System.out.println("Veure solució...\n");
        }
        else if(opcio.equals("3")) {
            System.out.println("Donar de baix usuari\n");
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
}