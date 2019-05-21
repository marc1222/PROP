package presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaEstadistica {
    private JFrame f = new JFrame("Estadistiques");
    JLabel lbUsuariIS = new JLabel("Usuari:");
    final JTextField UsuariIS = new JTextField();
    JButton btnEntrar = new JButton("Buscar");
    JPanel p1 = new JPanel();

    final JLabel lbMssg = new JLabel();
    final JTextField UsuariR = new JTextField();
    JButton btnRegistrar = new JButton("Buscar");
    JPanel p2 = new JPanel();
    JTabbedPane tp = new JTabbedPane();
    JLabel lbUsuariR = new JLabel("Problema:");

    public VistaEstadistica() {
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
        // Usuari

        lbUsuariIS.setBounds(20,20, 80,30);
        UsuariIS.setBounds(100,20, 100,30);

        btnEntrar.setBounds(250,20, 80,30);

        //lbMssg.setBounds(20,150, 200,50);

        //p1.setLayout(new BorderLayout());
        p1.setLayout(null);
        p1.setBounds(10,10,500,500);

        p1.add(lbUsuariIS); p1.add(UsuariIS);
        p1.add(btnEntrar);  p1.add(lbMssg);


        //---------------------------------------------------------------------
        // Problema
        lbUsuariR.setBounds(20,20, 80,30);
        UsuariR.setBounds(140,20, 100,30);

        btnRegistrar.setBounds(140,170, 80,30);

        final JLabel lbMssgR = new JLabel();
        lbMssgR.setBounds(20,205, 300,50);

        p2.setLayout(null);
        p2.setBounds(10,10,500,500);

        p2.add(lbUsuariR); p2.add(UsuariR);
        p2.add(btnRegistrar);  p2.add(lbMssgR);


        //---------------------------------------------------------------------
        // Pestanyes
        tp.setBounds(20,20,600,600);
        tp.add("Estadistiques usuari",p1);
        tp.add("Estadistiques problema",p2);


        asignar_listenersComponentes();
        f.add(tp);
        f.setSize(700,700);
        f.setLayout(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }

    private void asignar_listenersComponentes() {
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buscar = UsuariIS.getText();
                taulaUsuari(buscar);
            }
        });
    }

    private void taulaUsuari(String dades) {
        String column[]={"Problema","Mat", "Temps"};

        String data[][];
        if (dades.equals("1")) {
            data = dades1();
        }
        else {
            data = dades2();
        }

        JTable jt = new JTable(data,column);
        jt.setBounds(80,100,150,350);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(80,150,150,350);
        p1.add(sp);
    }

    private void taulaProblema() {
        String column[]={"Usuari","Mat", "Temps"};
        String data[][]= { {"Usr12","2", "51526"},
                {"Usr2","3", "56646"},
                {"Usr4","2", "26626"}};

        JTable jt = new JTable(data,column);
        jt.setBounds(80,100,150,350);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(80,150,150,350);
        p1.add(sp);
    }

    private String[][] dades1() {
        String data[][]={ {"Prob1","2", "51526"},
                {"Prob1","3", "56646"},
                {"Prob2","2", "26626"}};
        return data;
    }

    private String[][] dades2() {
        String data[][]={ {"EQFEW","2", "51526"},
                {"WEF","3", "56646"},
                {"ProWEFWFE","2", "26626"}};
        return data;
    }
}
