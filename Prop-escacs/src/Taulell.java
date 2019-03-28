import javafx.geometry.Pos;

import java.lang.reflect.InvocationTargetException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;

public class Taulell {

    private Peca T[][];
        //creadora/s

    Taulell() {
        T = new Peca[8][8]; //que sera de peces
    }
    Taulell(Peca t[][]) {
        this.T = t; //que sera de peces
    }
    //operacions

    //privades
    //crea una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7, color = {'b','w','-'}, tipus = tots els tipus (null inclòs)
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de la peça creada
    private void crea_peca_xy(Posicion Pos, int color, String tipus) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Peca aux = Peca.forName(tipus).getConstructor(Posicion.class,Integer.class).newInstance(Pos,color);
        T[Pos.x][Pos.y] = aux;
    }
    //borra una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de peça nula
    private void borra_peca_xy(Posicion pos) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            crea_peca_xy(pos,define.NULL_COLOR,define.PECA_NULA);
    }


    //publiques

    //pre: x0,y0,x,y han de pertanyer a l'intèrval següent: 0 <= valor <= 7
    //post: es retorna la instancia de la peça en la posicio (x,y) de T
    public Peca getPecaPosició(Posicion pos) {
        return T[pos.x][pos.y];
    }

    //s'encarrega donat dues coordenades dins el taulell verificar que no infringiran les regles d'integritat que el taulell comporta
    //pre: true
    //post:check que x0,y0,x,y han de pertanyer a l'intèrval següent: 0 <= valor <= 7,
    //    que a la posició inici (x0,y0) hi ha una peça, i que aquesta pertany del jugador que tira (color indica el jugador que tira),
    //    que a la posició desti (x,y) NO hi ha una peça del jugador que tira
    //        ---------------potser falta algo---------------
    //    i executa la validació del moviment que comprova les regles d'integritat del moviment de la peça
    private void validar_moviment(Posicion inici, Posicion fi, int color) throws IllegalArgumentException, ChessException
    {
            if (inici.x < 0 || inici.y < 0 || inici.x > 7 || inici.y > 7 || fi.x < 0 || fi.y < 0 || fi.x > 7 || fi.y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            if (color != 'w' || color != 'b')
                throw new IllegalArgumentException("Taulell: Color inválido");
            Peca aux = T[fi.x][fi.y];
            if (aux.getColor() == color)
                throw new ChessException("Taulell: La casilla DESTINO no puede tener una pieza del mismo color");
            aux = T[inici.x][inici.y];
            if (aux.getTipus() == "null")
                throw new ChessException("Taulell: La casilla INICIAL está vacia");
            if (aux.getColor() != color)
                throw new ChessException("Taulell: La casilla INICIAL no puede tener una pieza de otro color");

            aux.rango(inici, fi);
    }

    // instancia al tauler una nova peça
    //pre: true
    //post: es comprova que x,y pertanyin a l'interval 0 <= valor <= 7, que color pertanyi a algun dels jugadors, o be que "-" en cas de peca nula
    // que tipus pertanyi al array peces i es crea la peça amb els parametres instanciats
    public void crear_peça(Posicion pos, char color, String tipus) {
        try {
            if (x < 0 || y < 0 || x > 7 || y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");

            if (color != define.BLACK || color != define.WHITE || color != define.NULL_COLOR)
                throw new IllegalArgumentException("Taulell: Color inválido");

            if(!Arrays.asList(TIPUS_PECA).contains(tipus)) {
                throw new IllegalArgumentException("Taulell: Tipus de peça invàlid");
            }
            if ( (color == define.NULL_COLOR && tipus != define.PECA_NULA) || (tipus == define.PECA_NULA && color != define.NULL_COLOR) )
                throw new IllegalArgumentException(("Taulell: Peça NULL invalida"));

            crea_peca_xy(pos,color,tipus);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // instancia al tauler una nova peça que no sigui de tipus null
    //pre: true
    //post: es comprova que sigui una posició valida, que hi hagi alguna instancia de peça diferent de null i es borra la peça
    public void destrueix_peça(Posicion pos) {
        try {
            if (pos.x < 0 || pos.y < 0 || pos.x > 7 || pos.y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            Peca p = T[pos.x][pos.y];
            if (p.getTipus() == define.PECA_NULA)
                throw new ChessException("Taulell: No hay ninguna pieza que destruir en la posición: ["+pos.x+"] ["+pos.y+"]");

            borra_peca_xy(pos);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());

        } catch (ChessException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //encarregada de moure una peça de una posició a una altre
    //pre: true -> rep parametres x0,y0 (pos inicial) i parametres x,y (pos desti) i  color (peces que es mouen)
    //post: invoca el metoda que valida totes les regles d'integritat del moviment indicat,
    // instancia una peça a la posició x,y
    // i borra la de x0,y0 es produeix una excpeció
    public void mover_pieza(Posicion inici, Posicion fi, int color) {
        try {
            validar_moviment(inici,fi,color);
            Peca aux = T[inici.x][inici.y];
            borra_peca_xy(inici);
            //a.getClass().getSimpleName() --> class name
            //getClass().getName() --> package + class name
            crea_peca_xy(fi,color,aux.getTipus());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean descartar_movimiento(Posicion inici, Posicion desti, int color) {
        return true;
    }
    public Posicion[] todos_movimientos(Posicion inici) {
        Posicion[] all_pos;
        try {
            if (inici.x < 0 || inici.y < 0 || inici.x > 7 || inici.y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            Peca aux = T[inici.x][inici.y];
            String tipus = aux.getTipus();
            if (tipus == define.PECA_NULA)
                throw new IllegalArgumentException("Taulell: No hi ha cap peça");
            all_pos = aux.getMovimientos();
            if (tipus != define.CAVALL) {
                ArrayList<Posicion> tmp = new ArrayList<>();;
                for (int i = 0; i < all_pos.length; ++i) {
                    if (!descartar_movimiento(inici,all_pos[i],aux.getColor())) tmp.add(all_pos[i]);
                }
                all_pos = new Posicion[tmp.size()];
                all_pos = tmp.toArray(all_pos);
            }
            return all_pos;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            all_pos = null;
            return all_pos;
        }
    }
}