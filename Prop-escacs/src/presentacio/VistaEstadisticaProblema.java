package presentacio;

import javax.swing.*;

public class VistaEstadisticaProblema {
    private JFrame f = new JFrame("Estadistiques problema");

    public VistaEstadisticaProblema() {
        String column[]={"Usuari","Mat", "Temps"};
        String data[][]={ {"Usr12","2", "51526"},
                {"Usr2","3", "56646"},
                {"Usr4","2", "26626"}};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300,400);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }
}
