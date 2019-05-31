package gui;

import java.awt.*;

public class define {
    public static final Color LIGHT_COLOR = new Color(214,177,139);
    public static final Color DARK_COLOR = new Color(165,122,96);
    public static final Color BoardBorderColor = new Color(107,60,33);
    public static final Color TileSelectedColor = new Color(119,119,119);

    public static final String icons_route = "./asset/pieces/";

    //juegador
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public static final int NULL_COLOR = 2;
    public static final int USER = 3;
    public static final int MAQUINA = 4;
    public static final int NAIVE = 404;
    public static final int SMART = 405;

    //peça
    public static final String ALFIL = "Alfil";
    public static final String TORRE = "Torre";
    public static final String REI = "Rei";
    public static final String CAVALL ="Cavall";
    public static final String REINA = "Reina";
    public static final String PEO = "Peo";
    public static final String PECA_NULA = "Peca_Nula";


    //id de les vistes de jugar una partida

    public static final int simulacio = 113;
    public static final int normal = 114;
    public static final int estadistica = 115;

    public static final int MOD_FEN = 116;
    public static final int MOD_GRAFIC = 117;
    public static final int BORRAR = 118;
    public static final int ESTAD = 119;


}
