import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Taulell {

    private Peca T[][];
    //creadora/s

    public Taulell() {
        T = new Peca[8][8]; //que sera de peces
    }
    public Taulell(Peca t[][]) {
        T = new Peca[8][8];
        this.T = t; //que sera de peces
    }

    //Taulell deep copy
    public Taulell(Peca t[][], boolean b) {
        Peca p[][] = new Peca[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                try {
                    p[i][j] = (Peca) Class.forName(t[i][j].getTipus()).getConstructor(int.class,ArrayList.class, ArrayList.class).newInstance(t[i][j].getColor(),t[i][j].getAmenacades(),t[i][j].getAmenaces());
                    if ((t[i][j].getTipus()).equals(define.PEO)) {
                        Peo p2 = (Peo) p[i][j];
                        if (t[i][j].getColor() == define.WHITE) p2.setPeoPrimer(j == 1);
                        else p2.setPeoPrimer(j == 6);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        T = p;
    }
    public Taulell(Taulell tau) {
        T = new Peca[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                try {
                    T[i][j] = (Peca) Class.forName(tau.T[i][j].getTipus()).getConstructor(int.class).newInstance(tau.T[i][j].getColor());
                    if ((tau.T[i][j].getTipus()).equals(define.PEO)) {
                        Peo p2 = (Peo) T[i][j];
                        if (tau.T[i][j].getColor() == define.WHITE) p2.setPeoPrimer(j == 1);
                        else p2.setPeoPrimer(j == 6);
                    }
                    T[i][j].reset();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        recalcular_amanaça_tauler();
    }
    //operacions
    private void reset_amenaces_tauler() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                T[i][j].reset();
            }
        }
    }
    //S'encarrega d'actualitzar totes les amaneces donat el taulell
    //Pre: T ha de ser un taulell valid i de peces inicialitzades
    public void recalcular_amanaça_tauler() {
        Posicion[] whitys = getPosColor(define.WHITE);
        Posicion[] blackys = getPosColor(define.BLACK);
        //per cada relació de peces blanques i negres, mirem si aquestes s'amanacen les unes a les altres
        //total iteracions -> peces_blanques x peces negres iteracions
        for (int i = 0; i < whitys.length; ++i) {
            for (int j = 0; j < blackys.length; ++j) {
                recalcular_amenaça_pos(whitys[i],blackys[j]);
            }
        }
    }
    //donada una posició inicial es comprova si aquesta amanenaça a la fi i en cas afirmatiu
    //s'afegeix en la pos ini la pos que amenaça, i en pos fi es posa com amenaçadda

    //a continuació es comprova si fi amanenaça a la ini i en cas afirmatiu
    //s'afegeix en la pos fi la pos que amenaça (ini), i en pos ini es posa fi com amenaçades
    //pre: a posicion ini hi ha d'haver una pos valida, igual que a fi, i diferent de PECA_NULA
    private void recalcular_amenaça_pos(Posicion ini, Posicion fi) {
        int act_color = T[ini.x][ini.y].getColor();
        try {
            //peces valides -> moviment ini -> fi
            boolean ret = validar_moviment(ini, fi, act_color);
            if (ret) {
                //moviment possible
                T[ini.x][ini.y].add_amenacada(fi);
                T[fi.x][fi.y].add_amenaca(ini);
            }
        } catch (ChessException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            //peces valides -> moviment fi -> ini
            act_color = T[fi.x][fi.y].getColor();
            boolean ret = validar_moviment(fi, ini, act_color);
            if (ret) {
                //moviment valid
                T[fi.x][fi.y].add_amenacada(ini);
                T[ini.x][ini.y].add_amenaca(fi);
            }
        } catch (ChessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void printTauler() {
        String type;
        char p;
        System.out.println("Y ---------------------");
        for (int i = 0; i < 9; ++i) {
            if (i < 8) {
                System.out.print((7-i) + " ");
                System.out.print("|");
            }
            for (int j = 0; j < 8; ++j) {
                if (i == 8) {
                    if (j == 0) {
                        System.out.println("-----------------------");
                        System.out.print("X  ");
                    }
                    System.out.print(j+ " ");

                }
                else {
                    p = 'n';
                    Peca a = T[j][7-i];
                    type = a.getTipus();
                    switch (type) {
                        case define.ALFIL:
                            if (a.getColor() == define.WHITE) p = 'A';
                            else p = 'a';
                            break;
                        case define.CAVALL:
                            if (a.getColor() == define.WHITE) p = 'C';
                            else p = 'c';
                            break;
                        case define.REI:
                            if (a.getColor() == define.WHITE) p = 'R';
                            else p = 'r';
                            break;
                        case define.REINA:
                            if (a.getColor() == define.WHITE) p = 'X';
                            else p = 'x';
                            break;
                        case define.PEO:
                            if (a.getColor() == define.WHITE) p = 'P';
                            else p = 'p';
                            break;
                        case define.TORRE:
                            if (a.getColor() == define.WHITE) p = 'T';
                            else p = 't';
                            break;
                        case define.PECA_NULA:
                            p = '-';
                            break;
                    }
                    System.out.print(p + "|");
                }
            }
            System.out.println("");
        }
    }

    //privades
    //crea una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7, color = {'b','w','-'}, tipus = tots els tipus (null inclòs)
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de la peça creada
    private void crea_peca_xy(Posicion Pos, int color, String tipus) {
        try {
            Peca aux = (Peca) Class.forName(tipus).getConstructor(int.class).newInstance(color);
            if (tipus.equals(define.PEO)) {
                Peo p = (Peo)aux;
                if (color == define.WHITE) {
                    p.setPeoPrimer(Pos.y == 1);
                }
                else {
                    p.setPeoPrimer(Pos.y == 6);
                }
                aux = (Peca)p;
            }
            T[Pos.x][Pos.y] = aux;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //borra una peça
    //pre: x,y pertanyen a l'interval 0 <= valor <= 7
    //post: a la matriu de peces T en posició ( x, y ) s'hi troba una instancia de peça nula
    private void borra_peca_xy(Posicion pos) {
        crea_peca_xy(pos,define.NULL_COLOR,define.PECA_NULA);
    }


    //publiques
    public Peca[][] getTauler() {
        return T;
    }
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
    private boolean validar_moviment(Posicion inici, Posicion fi, int color) throws IllegalArgumentException, ChessException {
        //start checks
        //System.out.println(inici.x+"-"+inici.y+"----"+fi.x+"-"+fi.y);
        if (inici.x < 0 || inici.y < 0 || inici.x > 7 || inici.y > 7 || fi.x < 0 || fi.y < 0 || fi.x > 7 || fi.y > 7)
            return false;
        if (inici.x == fi.x && inici.y == fi.y) return false;
        if (color != define.WHITE && color != define.BLACK) return false;
        Peca aux = T[fi.x][fi.y];
        if (aux.getColor() == color) return false;
        aux = T[inici.x][inici.y];
        if ((aux.getTipus()).equals(define.PECA_NULA)) return false;

        if (aux.getColor() != color) return false;
        if (!((aux.getTipus()).equals(define.CAVALL)) && descartar_movimiento(inici, fi)) return false;
        if ((aux.getTipus()).equals(define.PEO)) { //descartar mov peo que no tingui enemic
            if (Math.abs(inici.x - fi.x) == Math.abs(inici.y - fi.y)) {
                //diagonal && hay un enemigo ->
                if (T[fi.x][fi.y].getColor() == color || T[fi.x][fi.y].getColor() == define.NULL_COLOR) return false;
            }
            else if (T[fi.x][fi.y].getColor() != define.NULL_COLOR) return false;
        }
        //end checks call peça checker -> booleanç

        return aux.rango(inici, fi);
    }
    //donada una posició inici i final es mira si les caselles que estan entre aquestes (forman vertical/diagonal/horitzontal)
    //tenen alguna peça la cual impedeixi el moviment cap a la posicio desti

    //pre: a pos inici hi ha un peça no nula del jugador color, i posició inici && desti son posicions valides
    //

    //post: retorna true si el moviment proposat no es pot acomplir o be perquè no és un
    // moviment horitzontal/diagonal/vertical o be perque s'hi interposa alguna
    //peça entre totes les caselles que haura de recorrer per realitzar el desplaçament cap al destí
    //retorna fals en cas que no s'hagi de descartar el moviment i per tant aquest es consideri
    //un moviment valid dins les dinàmiques establertes per la funció
    private boolean descartar_movimiento(Posicion inici, Posicion desti) {
        //check cami fins destí
        double dx = inici.x - desti.x;
        double dy = inici.y -desti.y;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        Peca aux;
        int i,j;
        Posicion pos = new Posicion(inici.x,inici.y);
        boolean ret = true;
        if (dy == 0) { //mov. horitzontal
            if (inici.x < desti.x) i = 1;
            else i = -1;
            for (int k = 0; k < (dx - 1); ++k) {
                pos.x += i;
                aux = T[pos.x][inici.y];
                if (!((aux.getTipus()).equals(define.PECA_NULA))) return true;
            }
            return false;
        }
        else if (dx == 0) { //mov. vertical
            if (inici.y < desti.y) i = 1;
            else i = -1;
            for (int k = 0; k < (dy - 1); ++k) {
                pos.y += i;
                aux = T[inici.x][pos.y];
                if (!((aux.getTipus()).equals(define.PECA_NULA))) return true;
            }
            return false;
        }
        else if (dx == dy) { //dx && dy != 0 -> mov. diagonal
            if (inici.x < desti.x && inici.y < desti.y) {i = 1; j = 1;}
            else if (inici.x < desti.x && inici.y > desti.y) {i = 1; j = -1;}
            else if (inici.x > desti.x && inici.y < desti.y) {i = -1; j = 1;}
            else {i = -1; j = -1;}
            //System.out.println(i+"   "+j);
            for (int k = 0; k < (dx - 1); ++k) {
                pos.x += i;
                pos.y += j;
                aux = T[pos.x][pos.y];
                if (!((aux.getTipus()).equals(define.PECA_NULA))) return true;
            }
            return false;
        }
        return true;
    }

    public Posicion[] todos_movimientos(Posicion inici) {
        Posicion[] all_pos;
        try {
            if (inici.x < 0 || inici.y < 0 || inici.x > 7 || inici.y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            Peca aux = T[inici.x][inici.y];
            String tipus = aux.getTipus();
            int color = aux.getColor();
            if (tipus.equals(define.PECA_NULA))
                throw new IllegalArgumentException("Taulell: No hi ha cap peça");

            all_pos = aux.movimientos_posibles(inici);
            ArrayList<Posicion> tmp = new ArrayList<>();
            int act_color;
            Posicion act_pos;
            for (int i = 0; i < all_pos.length; ++i) {
                act_pos = all_pos[i];
                if (act_pos.x >= 0 && act_pos.y >= 0 && act_pos.x < 8 && act_pos.y < 8) {
                    act_color = T[all_pos[i].x][all_pos[i].y].getColor();
                    if (act_color != color) {
                        if (tipus.equals(define.CAVALL)) tmp.add(act_pos);
                        else if (!descartar_movimiento(inici, act_pos)) {
                            if (tipus.equals(define.REI)) { //descartar mov de mat
                                Posicion[] peces = getPosColor((color==define.WHITE)?define.BLACK:define.WHITE);
                                if (!escac(peces,act_pos,inici)) tmp.add(act_pos);
                            }
                            else if (tipus.equals(define.PEO)) { //descartar mov peo que no tingui enemic
                                if (Math.abs(inici.x - act_pos.x) == Math.abs(inici.y - act_pos.y)) {
                                    //diagonal && hay un enemigo ->
                                    if (T[act_pos.x][act_pos.y].getColor() == ((color == define.WHITE) ? define.BLACK : define.WHITE))
                                        tmp.add(act_pos);
                                }
                                else if (act_color == define.NULL_COLOR) tmp.add(act_pos);
                            }
                            else tmp.add(act_pos);
                        }
                    }
                }
            }
            all_pos = tmp.toArray(new Posicion[tmp.size()]);
            return all_pos;

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            all_pos = null;
            return all_pos;
        }
    }
    //sempre existira un rei amb un color
    //get la posició del rei pel color indicat pel parametre
    private Posicion getReiPos(int color) {
        Peca aux;
        for (int i= 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                aux = T[i][j];
                if ((aux.getTipus()).equals(define.REI) && aux.getColor() == color) {
                    return new Posicion(i,j);
                }
            }
        }
        return new Posicion();
    }
    //get posicions de totes les peces d'un color
    public Posicion[] getPosColor(int color) {
        Peca aux;
        ArrayList<Posicion> tmp = new ArrayList<>();

        for (int i= 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (T[i][j].getColor() == color) {
                    tmp.add(new Posicion(i,j));
                }
            }
        }
        return tmp.toArray(new Posicion[tmp.size()]);
    }
    //para un conjunto de piezas determinar si amenazan a la posicion Rei,
    //si hay un camino valido entre cada una de ellas
    //pre --> Rei: posición donde se debe simular el jaque
    //      ReiIni: posición inicial donde se encuentra el rei en el tablero para tenerla
    //              en cuenta como una casilla vacia (es la que el rei en la simulación abandonaria para desplazarse a la posicio Rei)

    //post--> devuelve true si alguna de esas piezas amenaza la posición
    public boolean escac(Posicion[] Peces, Posicion Rei, Posicion ReiIni) {
        boolean aux;
        aux = (Rei.x != ReiIni.x) || (Rei.y != ReiIni.y);
        Peca tmp = T[Rei.x][Rei.y];
        Peca ini = T[ReiIni.x][ReiIni.y];
        if (aux) {
            T[Rei.x][Rei.y] = ini;
            borra_peca_xy(ReiIni);
        }
        int color = T[Rei.x][Rei.y].getColor();
        boolean ret;
        for (int i = 0; i < Peces.length; ++i) {
            //hacer modificacion temporal al tablero
            //ejecutar la consulta sobre el tablero "modificado temporalmente"
            try {
                ret = validar_moviment(Peces[i], Rei, (color == define.WHITE) ? define.BLACK : define.WHITE);
                //   System.out.println("Mov: " + Peces[i].x + "-" + Peces[i].y + "--->" + Rei.x + "-" + Rei.y + " " + ret);
                if (ret) { //si no se descarta el movimiento significa que hay un posible desplazamiento -> jaque
                    if (aux) {
                        T[ReiIni.x][ReiIni.y] = ini;
                        T[Rei.x][Rei.y] = tmp;
                    }
                    return true;
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        //restablecer el tablero
        if (aux) {
            T[ReiIni.x][ReiIni.y] = ini;
            T[Rei.x][Rei.y] = tmp;
        }
        //no hay camino -> no hi ha escac
        return false;
    }

    //hem de veure si donat un color, les peces d'aquell color (incluit el mateix rei)
    //son capaces de generar una situació al taulell tal que puguin
    //conseguir que el rei tingui 0 amenaces en alguna posició destí posible
    //pre: color == WHITE o color == BLACK
    //post: retorna true si s'ha trobat una solució valida, altrament false
    private boolean generar_situacio(int color) {
        if (color != define.BLACK && color != define.WHITE) return false;
        //agafem la posició del rei
        Posicion Rei = getReiPos(color);
        ArrayList<Posicion> perill = T[Rei.x][Rei.y].getAmenaces();
        if (perill.size() > 0) {
            //simulem una situació en un taulell auxiliar amb totes les posicions del rei
            //i guardem l'estat original del taulell en aux
            Posicion[] moves = T[Rei.x][Rei.y].movimientos_posibles(Rei);
            boolean moved;
            Taulell T2;
            for (int i = 0; i < moves.length; ++i) {
                T2 = new Taulell(this.T, true);
                moved = T2.mover_pieza(Rei,moves[i],color);
                //System.out.println(moves[i].x+","+moves[i].y+" - "+moved);
                if (moved) { //si sha pogut moure mirem si té 0 amenaces
                    if(T2.T[moves[i].x][moves[i].y].getAmenaces().size() == 0) return true;
                }
            }
            //si arribem aqui, cap posicio del rei es capaç de generar una situació de no escac i mat
            //mirem si algun moviment posible de les altres peces que no siguin el rei poden evitarho
            Posicion[] peces = getPosColor(color);
            for (int i = 0; i < peces.length; ++i) {
                if (peces[i] != Rei) { //si es difernet al rei que ja l'hem mirat
                    moves = T[peces[i].x][peces[i].y].movimientos_posibles(peces[i]);
                    for (int j = 0; j < moves.length; ++j) {
                        T2 = new Taulell(this.T, true);
                        moved = T2.mover_pieza(peces[i],moves[j],color);
                        //  System.out.println(moves[j].x+","+moves[j].y+" - "+moved);
                        if (moved) { //si sha pogut moure mirem si té 0 amenaces, altrament restaurem el taulell
                            if(T2.T[Rei.x][Rei.y].getAmenaces().size() == 0) return true;
                        }
                    }
                }
            }
        }
        //si arribem aqui no em generat cap situació
        return false;
    }
    //pre -> color pertany define.WHITE || define.BLACK
    //post --> retorna 1 si hi ha un escac i mat al rei del color indicat pel parametre color,
    // 0 si nomes hi ha escac,
    //2 si el rei esta ofegat
    // -1 altrament
    public int escac_i_mat(int color) {
        //get posició del rei
        Posicion Rei = getReiPos(color);
        //System.out.println(Rei.x+"-"+Rei.y);
        //get peces que ataquen
        Posicion Peces_atacant[] = getPosColor((color==define.WHITE)?define.BLACK:define.WHITE);
        //TODOS_MOVIMIENTOS RETURNS POS VALIDES REI ON NO ES TROBA EN ESCAC
        Posicion Rei_moves[] = todos_movimientos(Rei);
        //primeo validar el jaque a la posición inicial del Rei
        if (escac(Peces_atacant,Rei,Rei)){
            //comprovar si es escac i mat
            if (Rei_moves != null) {
                for (int i = 0; i < Rei_moves.length; ++i) {
                    if (!escac(Peces_atacant, Rei_moves[i], Rei)) return 0;
                }
            }
            if (generar_situacio(color)) return 0;

            return 1;
        }
        else if (Rei_moves.length == 0) {
            //mirem si alguna peca te algun mov valid, sino jugador ofegat
            Posicion[] aliats = getPosColor(color);
            if (aliats.length > 1) { //si hi ha alguna peça mes que el rei
                Posicion act;
                for (int i = 0; i < aliats.length; ++i) {
                    act = aliats[i];
                    if (act != Rei) {//si la pos es diferent de la del rei, mirem si te algun mov valid
                        if (todos_movimientos(act).length > 0) return -1; //te algun mov valid, return -1
                    }
                }
            }
            return 2;  //si arribem aqui no hi ha movs valids per cap peça o be solament hi ha el rei
        }
        return -1;
    }
    //instancia al tauler una nova peça
    //pre: true
    //post: es comprova que x,y pertanyin a l'interval 0 <= valor <= 7, que color pertanyi a algun dels jugadors, o be que "-" en cas de peca nula
    //i es crea la peça amb els parametres instanciats
    public void crear_peça(Posicion pos, int color, String tipus) {
        try {
            if (pos.x < 0 || pos.y < 0 || pos.x > 7 || pos.y > 7)
                throw new IllegalArgumentException("Taulell: X o Y valores inválidos");
            if (color != define.BLACK && color != define.WHITE && color != define.NULL_COLOR)
                throw new IllegalArgumentException("Taulell: Color inválido");
            if ( (color == define.NULL_COLOR && !(tipus.equals(define.PECA_NULA))) || (tipus.equals(define.PECA_NULA) && color != define.NULL_COLOR) )
                throw new IllegalArgumentException(("Taulell: Peça NULL invalida"));

            crea_peca_xy(pos,color,tipus);
            //actualitzar amenaces
            reset_amenaces_tauler();
            recalcular_amanaça_tauler();

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
            if ((p.getTipus()).equals(define.PECA_NULA))
                throw new ChessException("Taulell: No hay ninguna pieza que destruir en la posición: ["+pos.x+"] ["+pos.y+"]");

            borra_peca_xy(pos);
            reset_amenaces_tauler();
            recalcular_amanaça_tauler();

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
    public boolean mover_pieza(Posicion inici, Posicion fi, int color) {
        boolean ret = false;
        try {
            if (!validar_moviment(inici,fi,color)) ret = false;
            else {
                ret = true;
                Peca aux = T[inici.x][inici.y];
                if ((aux.getTipus()).equals(define.REI))  {
                    Posicion[] peces = getPosColor((color==define.WHITE)?define.BLACK:define.WHITE);
                    if (escac(peces,fi,inici)) ret = false;
                }
                else { //la peca que volem moure te un moviment valid, pero NO es un rei, mirem si el rei esta en escac
                    Posicion Rei = getReiPos(color);
                    if (this.T[Rei.x][Rei.y].getAmenaces().size() > 0) {
                        //Simulem el moviment si el rei es troba en escac
                        Taulell T2 = new Taulell(this.T,true);
                        T2.borra_peca_xy(inici);
                        T2.crea_peca_xy(fi, color, aux.getTipus());
                        T2.reset_amenaces_tauler();
                        T2.recalcular_amanaça_tauler();
                        Rei = T2.getReiPos(color);
                        if (T2.T[Rei.x][Rei.y].getAmenaces().size() != 0) {
                            //System.out.print("Has de protegir el rei, està en ESCAC!!!\n");
                            ret = false;
                        }
                    }
                }

                if (ret) {
                    if ((aux.getTipus()).equals(define.PEO)) {
                        if ((aux.getColor() == define.WHITE && fi.y == 7) || (aux.getColor() == define.BLACK && fi.y == 0)) {
                            borra_peca_xy(inici);
                            crea_peca_xy(fi, color, define.REINA);
                        } else {
                            borra_peca_xy(inici);
                            crea_peca_xy(fi, color, aux.getTipus());
                        }
                    } else {
                        borra_peca_xy(inici);
                        crea_peca_xy(fi, color, aux.getTipus());
                    }
                    reset_amenaces_tauler();
                    recalcular_amanaça_tauler();
                }
            }
        } catch (ChessException ex) {
            System.out.println(ex.getMessage());
        } finally {
            return ret;
        }
    }

}
