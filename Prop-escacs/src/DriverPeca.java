import java.util.Scanner;

public class DriverPeca {
    public void testGetNextId() {
        System.out.println("Get Next Id");
        System.out.println(Peca.getNextId());
    }

    public void testGetId() {
        System.out.println("Get Id");
        StubReina reina = new StubReina();
        System.out.println(reina.getId());
    }

    public void testGetColor(int color) {
        System.out.println("Get Color");
        StubReina reina = new StubReina(color);
        System.out.println(reina.getColor());
    }

    public void testSetId(int id) {
        System.out.println("Set Id");
        StubReina reina = new StubReina();
        reina.setId(id);
        System.out.println(reina.id);
    }

    public void testSetColor(int color) {
        System.out.println("Set Color");
        StubReina reina = new StubReina();
        reina.setColor(color);
        System.out.println(reina.color);
    }

    public static void main (String[] args) {
        DriverPeca dp = new DriverPeca();
        //dp.testGetNextId();
        //dp.testGetId();
        //dp.testGetColor();
        //dp.testSetId();
        //dp.testSetColor();
        Scanner sc = new Scanner(System.in);
        int val = 0;
        while (val != 6) {
            System.out.println("Tria el mètode que vols provar");
            System.out.println("1 - getNextId()");
            System.out.println("2 - getId()");
            System.out.println("3 - getColor() [color]");
            System.out.println("4 - setId(id)");
            System.out.println("5 - setColor(color)");
            System.out.println("6 - Sortir");
            val = sc.nextInt();
            switch (val) {
                case 1:
                    dp.testGetNextId();
                    break;
                case 2:
                    dp.testGetId();
                    break;
                case 3:
                    System.out.println("Introdueix el color (0 blanc)");
                    int color = sc.nextInt();
                    dp.testGetColor(color);
                    break;
                case 4:
                    System.out.println("Introdueix l'id");
                    int id = sc.nextInt();
                    dp.testSetId(id);
                    break;
                case 5:
                    System.out.println("Introdueix el color (0 blanc)");
                    int color2 = sc.nextInt();
                    dp.testSetColor(color2);
                default:
                    break;
            }
        }
    }
}
