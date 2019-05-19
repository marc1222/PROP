package domini;

public class ControladorDominiProblema {

    private Problema problema;
    ControladorDominiProblema() {
        //FALTA
    }

    int getPrimer() {
        return this.problema.getPrimer();
    }

    Problema getProblema() { //-> no
        return this.problema;
    }

}
