import java.util.Arrays;

public class Taulell {
    protected Peca T[][];

    //creadora/s

    Taulell() {
        T = new Peca[8][8]; //que sera de peces
    }
    //operacions

    //privades
    private void crea_peca_xy(int x, int y, char color,String tipus) {
        T[x][y] = new Peca(x,y,color,tipus);
    }


    //publiques

    //instancia al tauler una nova peça, es a
    //dir, seteja un  pointer al
    // id de peça a la casella indicada
    public void crear_peça(int x, int y, char color, String tipus) {
        try {
            if (x < 0 || y < 0 || x > 7 || y > 7)
                throw new IllegalArgumentException("X o Y valores inválidos");

            if (color != 'w' || color != 'b')
                throw new IllegalArgumentException("Color inválido");
            String [] peces = {"alfil","rei","reina","peo","torre","cavall","null"};

            if(!Arrays.asList(peces).contains(tipus))
                throw new IllegalArgumentException("Tipus de peça invàlid");

            crea_peca_xy(x,y,color,tipus);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }



}