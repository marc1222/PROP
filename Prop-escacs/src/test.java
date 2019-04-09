import java.util.Scanner;

public class test {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Peca mat[][] = new Peca[8][8] ;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                mat[i][j] = new Peca_Nula(0);
            }
        }
        mat[2][7] = new Cavall(define.BLACK);

        mat[5][6] = new Peo(define.WHITE, true);
        mat[6][6] = new Alfil(define.WHITE);

        mat[2][5] = new Peo(define.BLACK, true);
        mat[4][5] = new Cavall(define.WHITE);
        mat[5][5] = new Rei(define.WHITE);

        mat[5][4] = new Alfil(define.WHITE);
        mat[7][4] = new Alfil(define.BLACK);


        mat[1][3] = new Peo(define.WHITE, true);
        mat[2][3] = new Peo(define.BLACK, true);
        mat[7][3] = new Peo(define.BLACK, true);

        mat[0][2] = new Peo(define.WHITE, true);
        mat[2][2] = new Rei(define.BLACK);
        mat[3][2] = new Peo(define.BLACK, true);
        mat[5][2] = new Peo(define.WHITE, true);
        mat[7][2] = new Cavall(define.BLACK);

        mat[0][1] = new Reina(define.WHITE);
        mat[5][1] = new Peo(define.BLACK, true);

        mat[5][0] = new Cavall(define.WHITE);
        mat[7][0] = new Torre(define.BLACK);


        Taulell Tauler = new Taulell(mat);



        Posicion inici = new Posicion(0, 0);
        Posicion fi = new Posicion(0, 0);
        int aux;

        Jugador BUsr = new Usuari(false);
        Jugador WMaq = new Naive(Tauler, define.WHITE);

        String finalParitda = "no";
        while(!finalParitda.equals("si")) {
            Tauler.printTauler();


            WMaq.moviment(inici, fi);
            System.out.println("\nMAQ1 " + inici.x + " " + inici.y + " - " + fi.x + " " + fi.y);
            Tauler.mover_pieza(inici, fi, define.WHITE);

            BUsr.moviment(inici, fi);
            System.out.println("\nUSR1 " + inici.x + " " + inici.y + " - " + fi.x + " " + fi.y);
            Tauler.mover_pieza(inici, fi, define.BLACK);

            /*
            BUsr.moviment(inici, fi);
            System.out.println("\nUSR2 " + inici.x + " " + inici.y + " - " + fi.x + " " + fi.y);
            Tauler.mover_pieza(inici, fi, define.WHITE);
            */




            Tauler.printTauler();


            aux = Tauler.escac_i_mat(define.WHITE);
            if (aux == 1) { //jaque mate
                System.out.println("--- FI DE LA PARTIDA --- ESCAC I MAT WITHE ---");
            }
            else if (aux == 0) { //jaque
                System.out.println("--- ATENCIÓ --- ESCAC WITHE---");
            }

            aux = Tauler.escac_i_mat(define.BLACK);
            if (aux == 1) { //jaque mate
                System.out.println("--- FI DE LA PARTIDA --- ESCAC I MAT BLACK ---");
            }
            else if (aux == 0) { //jaque
                System.out.println("--- ATENCIÓ --- ESCAC BLACK ---");
            }


            finalParitda = sc.nextLine();
        }
    }
}