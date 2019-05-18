package presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
messageType:
    ERROR_MESSAGE
    INFORMATION_MESSAGE
    WARNING_MESSAGE
    QUESTION_MESSAGE
    PLAIN_MESSAGE

optionType:
    DEFAULT_OPTION
    YES_NO_OPTION
    YES_NO_CANCEL_OPTION
    OK_CANCEL_OPTION
*/

public class VistaInici {
    public VistaInici(){
        JFrame f = new JFrame("Inici");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(f,"Are you sure?", "Question", JOptionPane.YES_NO_OPTION );
                if(a == JOptionPane.YES_OPTION){
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });


        //---------------------------------------------------------------------
        // Iniciar sessio
        JLabel lbUsuariIS = new JLabel("Usuari:");
        lbUsuariIS.setBounds(20,20, 80,30);
        final JTextField UsuariIS = new JTextField();
        UsuariIS.setBounds(100,20, 100,30);

        JLabel lbContraIS = new JLabel("Contrasenya:");
        lbContraIS.setBounds(20,75, 80,30);
        final JPasswordField ContraIS = new JPasswordField();
        ContraIS.setBounds(100,75,100,30);
        ContraIS.setToolTipText("Contrasenya de X a Y...");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(100,120, 80,30);

        final JLabel lbMssg = new JLabel();
        lbMssg.setBounds(20,150, 200,50);


        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(10,10,500,500);

        p1.add(lbUsuariIS); p1.add(UsuariIS);
        p1.add(lbContraIS); p1.add(ContraIS);
        p1.add(btnEntrar);  p1.add(lbMssg);


        //---------------------------------------------------------------------
        // Registre
        JLabel lbUsuariR = new JLabel("Usuari:");
        lbUsuariR.setBounds(20,20, 80,30);
        final JTextField UsuariR = new JTextField();
        UsuariR.setBounds(140,20, 100,30);

        JLabel lbContraR = new JLabel("Contrasenya:");
        lbContraR.setBounds(20,75, 80,30);
        final JPasswordField ContraR = new JPasswordField();
        ContraR.setBounds(140,75,100,30);

        JLabel lbContraR2 = new JLabel("Repetir contrasenya:");
        lbContraR2.setBounds(20,125, 120,30);
        final JPasswordField ContraR2 = new JPasswordField();
        ContraR2.setBounds(140,125,100,30);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(140,170, 80,30);

        final JLabel lbMssgR = new JLabel();
        lbMssgR.setBounds(20,205, 300,50);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(10,10,500,500);

        p2.add(lbUsuariR); p2.add(UsuariR);
        p2.add(lbContraR); p2.add(ContraR);
        p2.add(lbContraR2); p2.add(ContraR2);
        p2.add(btnRegistrar);  p2.add(lbMssgR);



        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(20,20,600,600);
        tp.add("Iniciar sessio",p1);
        tp.add("Registrar-se",p2);

        f.add(tp);
        f.setSize(700,700);
        f.setLayout(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);


        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra;
                usuari = UsuariIS.getText();
                contra = new String(ContraIS.getPassword());
                String data = "Usuari " + usuari +
                        ", Contrasenya: " + contra;
                lbMssg.setText(data);
                if(!usuari.equals("usr")) {
                    String error = "Usuari incorrecte ('usr')";
                    JOptionPane.showMessageDialog(f,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra, contra2;
                usuari = UsuariR.getText();
                contra = new String(ContraR.getPassword());
                contra2 = new String(ContraR2.getPassword());

                if(!contra.equals(contra2)) {
                    String error = "La contrasenya no concideix";
                    JOptionPane.showMessageDialog(f,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
                String data = "Usuari " + usuari +
                        ", Contrasenya: " + contra +
                        ", Contra2: " + contra2;
                lbMssgR.setText(data);
                if(!usuari.equals("usr")) {
                    String error = "Usuari incorrecte ('usr')";
                    JOptionPane.showMessageDialog(f,error,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}
