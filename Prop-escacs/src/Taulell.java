import java.util.Arrays;

public class Taulell {
    private Peca T[][];

    //creadora/s

    Taulell() {
        T = new Peca[8][8]; //que sera de peces
    }
    //operacions

    //privades
    //crea una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7, color = {'b','w','-'}, tipus = tots els tipus (null inclòs)
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de la peça creada
    private void crea_peca_xy(int x, int y, char color, String tipus) {
        Peca aux = new Peca(x,y,color,tipus);
        T[x][y] = aux;
    }
    //borra una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de peça nula
    private void borra_peca_xy(int x, int y) {
        crea_peca_xy(x, y,'-',"null");
    }


    //publiques

    //pre: x0,y0,x,y han de pertanyer a l'intèrval següent: 0 <= valor <= 7
    //post: es retorna la instancia de la peça en la posicio (x,y) de T
    public Peca GetPecaPosició(int x, int y) {
        return T[x][y];
    }

    //s'encarrega donat dues coordenades dins el taulell verificar que no infringiran les regles d'integritat que el taulell comporta
    //pre: true
    //post:check que x0,y0,x,y han de pertanyer a l'intèrval següent: 0 <= valor <= 7,
    //    que a la posició inici (x0,y0) hi ha una peça, i que aquesta pertany del jugador que tira (color indica el jugador que tira),
    //    que a la posició desti (x,y) NO hi ha una peça del jugador que tira
    //        ---------------potser falta algo---------------
    //    i executa la validació del moviment que comprova les regles d'integritat del moviment de la peça
    private void validar_moviment(int x0, int y0, int x, int y, char color)
    {
        try {
            if (x0 < 0 || y0 < 0 || x0 > 7 || y0 > 7 || x < 0 || y < 0 || x > 7 || y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            if (color != 'w' || color != 'b')
                throw new IllegalArgumentException("Taulell: Color inválido");
            Peca aux = GetPecaPosició(x,y);
            if (aux.getColor() == color)
                throw new ChessException("Taulell: La casilla DESTINO no puede tener una pieza del mismo color");
            aux = GetPecaPosició(x0,y0);
            if (aux.getTipus() == "null")
                throw new ChessException("Taulell: La casilla INICIAL está vacia");
            if (aux.getColor() != color)
                throw new ChessException("Taulell: La casilla INICIAL no puede tener una pieza de otro color");

            aux.validar_moviment(x0,y0,x,y);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (ChessException e) {
            System.out.println(e.getMessage());
        }
    }

    // instancia al tauler una nova peça que no sigui de tipus null
    //pre: true
    //post: es comprova que x,y pertanyin a l'interval 0 <= valor <= 7, que color pertanyi a algun dels jugadors,
    // que tipus pertanyi al array peces i es crea la peça amb els parametres instanciats
    public void crear_peça(int x, int y, char color, String tipus) {
        try {
            if (x < 0 || y < 0 || x > 7 || y > 7)
                throw new IllegalArgumentException("X o Y valores inválidos");

            if (color != 'w' || color != 'b')
                throw new IllegalArgumentException("Color inválido");

            String [] peces = {"alfil","rei","reina","peo","torre","cavall"};
            if(!Arrays.asList(peces).contains(tipus))
                throw new IllegalArgumentException("Tipus de peça invàlid");

            crea_peca_xy(x,y,color,tipus);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
    // instancia al tauler una nova peça que no sigui de tipus null
    //pre: true
    //post: es comprova que sigui una posició valida, que hi hagi alguna instancia de peça diferent de null i es borra la peça
    public void destrueix_peça(int x, int y) {
        try {
            if (x < 0 || y < 0 || x > 7 || y > 7)
                throw new IllegalArgumentException("X o Y valores inválidos");
            Peca p = T[x][y];
            if (p.getTipus() == "null")
                throw new ChessException("Taulell: No hay ninguna pieza que destruir en la posición: ["+x+"] ["+y+"]");

            borra_peca_xy(x,y);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());

        } catch (ChessException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //encarregada de moure una peça de una posició a una altre
    //pre: true -> rep parametres x0,y0 (pos inicial) i parametres x,y (pos desti) i  color (peces que es mouen)
    //post: invoca el metoda que valida totes les regles d'integritat del moviment indicat,
    // instancia una peça a la posició x,y
    // i borra la de x0,y0 es produeix una excpeció
    public void mover_pieza(int x0, int y0, int x, int y, char color) {
        validar_moviment(x0,y0,x,y,color);
        borra_peca_xy(x0,y0);
        Peca aux = T[x0][y0];
        crea_peca_xy(x,y,color,aux.getTipus());
    }
}