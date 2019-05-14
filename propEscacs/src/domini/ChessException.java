//aquesta classe simplement hereda de excepció i els seus mètodes també.
//Serverix alhora de Distingir per exemple l'excepció que sallta per arguments invàlids,
// contra uns arguments que no comlpeixin les regles d'integritat de classe avaluada

//ex. dús a la classe avaluada:
//    throw new ChessException("NOM_CLASESE: DESC_GENERICA DEL ERROR"+value+"END OR WHATEVER");
package domini;

public class ChessException extends Exception {
    public ChessException() {
        super();
    }
    public ChessException(String s) {
        super(s);
    }
}
