package presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaEstadistica {
    public VistaEstadistica() {
        JFrame f=new JFrame();


        //---------------------------------------------------------------------
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(f,"Are you sure?", "Question", JOptionPane.YES_NO_OPTION );
                if(a == JOptionPane.YES_OPTION){
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });


        //---------------------------------------------------------------------
        // Menu
        JMenu menu, submenu, help   ;
        JMenuItem i1, i2, i3, i4, i5;
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");
        help=new JMenu("Help");
        submenu=new JMenu("Sub Menu");
        i1=new JMenuItem("Sortir");
        i2=new JMenuItem("Item 2");
        i3=new JMenuItem("Item 3");
        i4=new JMenuItem("Item 4");
        i5=new JMenuItem("Item 5");

        i1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String error = "Sortir...";
                JOptionPane.showMessageDialog(f,error,"Error",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu.add(i1); menu.add(i2); menu.add(i3);
        submenu.add(i4); submenu.add(i5);
        menu.add(submenu);
        mb.add(menu);
        mb.add(help);
        f.setJMenuBar(mb);


        //---------------------------------------------------------------------
        //Taula

        String column[]={"Problema","Usuari","Mat", "Temps"};
        String data[][]={ {"Prob1","Usr12","2", "51526"},
                {"Prob1","Usr2","3", "56646"},
                {"Prob2","Usr4","2", "26626"}};

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300,400);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }
}
