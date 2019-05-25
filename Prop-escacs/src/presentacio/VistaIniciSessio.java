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

public class VistaIniciSessio {
    private JFrame f = new JFrame("Inici");
    private JLabel lbUsuariIS = new JLabel("Usuari:");
    private JTextField UsuariIS = new JTextField();
    private JLabel lbContraIS = new JLabel("Contrasenya:");
    private JPasswordField ContraIS = new JPasswordField();
    private JButton btnEntrar = new JButton("Entrar");
    private JLabel lbMssg = new JLabel();
    private JLabel registrar = new JLabel("Clica aquí per registrar-te");

    public VistaIniciSessio(){

        lbUsuariIS.setBounds(20,20, 80,30);
        UsuariIS.setBounds(100,20, 100,30);


        lbContraIS.setBounds(20,75, 80,30);
        ContraIS.setBounds(100,75,100,30);
        ContraIS.setToolTipText("Contrasenya de X a Y...");


        btnEntrar.setBounds(100,120, 80,30);

        registrar.setBounds(20,150, 200,50);

        lbMssg.setBounds(20,175, 200,50);


        f.add(lbUsuariIS); f.add(UsuariIS);
        f.add(lbContraIS); f.add(ContraIS);
        f.add(btnEntrar);  f.add(lbMssg);
        f.add(registrar);

        asignar_listenersComponentes();

        f.setSize(500,500);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);

    }

    private void asignar_listenersComponentes() {
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

        registrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                //anarRegistrar(e);
            }
        });
    }
    /*
    private void anarRegistrar(MouseEvent e) {
        VistaRegistrar vr = new VistaRegistrar();
        vr.setVisible(true);
        vr.pack();
        vr.setLocationRelativeTo(null);
        vr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
    */
}
