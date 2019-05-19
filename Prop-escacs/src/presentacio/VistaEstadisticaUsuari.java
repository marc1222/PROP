package presentacio;

import javax.swing.*;

public class VistaEstadisticaUsuari {
    private JFrame f = new JFrame("Estadistiques usuari");

    public VistaEstadisticaUsuari() {
        String column[]={"Problema","Mat", "Temps"};
        String data[][]={ {"Prob1","2", "51526"},
                {"Prob1","3", "56646"},
                {"Prob2","2", "26626"}};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300,400);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }
}
