package presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaRegistrar {
    JFrame f = new JFrame("Registrar-se");
    JLabel lbUsuariR = new JLabel("Usuari:");
    final JTextField UsuariR = new JTextField();
    JLabel lbContraR = new JLabel("Contrasenya:");
    final JPasswordField ContraR = new JPasswordField();
    JLabel lbContraR2 = new JLabel("Repetir contrasenya:");
    final JPasswordField ContraR2 = new JPasswordField();
    JButton btnRegistrar = new JButton("Registrar");
    final JLabel lbMssgR = new JLabel();

    public VistaRegistrar() {
        /*
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(f,"Are you sure?", "Question", JOptionPane.YES_NO_OPTION );
                if(a == JOptionPane.YES_OPTION){
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });
        */

        lbUsuariR.setBounds(20,20, 80,30);
        UsuariR.setBounds(140,20, 100,30);


        lbContraR.setBounds(20,75, 80,30);
        ContraR.setBounds(140,75,100,30);


        lbContraR2.setBounds(20,125, 120,30);
        ContraR2.setBounds(140,125,100,30);


        btnRegistrar.setBounds(140,170, 80,30);

        lbMssgR.setBounds(20,205, 300,50);

        f.add(lbUsuariR); f.add(UsuariR);
        f.add(lbContraR); f.add(ContraR);
        f.add(lbContraR2); f.add(ContraR2);
        f.add(btnRegistrar); f.add(lbMssgR);


        f.setSize(700,700);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }

    private void asignar_listenersComponentes() {
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
