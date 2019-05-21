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

public class VistaInici2 {
    private JFrame frameVista = new JFrame("Inici");
    private JPanel panel = new JPanel();
    private JPanel pInici = new JPanel();
    private JPanel pRegistrar = new JPanel();

    private JPanel panelInformacionA = new JPanel();
    private int iPanelActivo = 0;


    private JLabel lbUsuariIS = new JLabel("Usuari:");
    private JTextField UsuariIS = new JTextField();
    private JLabel lbContraIS = new JLabel("Contrasenya:");
    private JPasswordField ContraIS = new JPasswordField();
    private JButton btnEntrar = new JButton("Entrar");
    private JLabel lbMssg = new JLabel();
    private JLabel registrar = new JLabel("Clica aquí per registrar-te");





    JLabel lbUsuariR = new JLabel("Usuari:");
    final JTextField UsuariR = new JTextField();
    JLabel lbContraR = new JLabel("Contrasenya:");
    final JPasswordField ContraR = new JPasswordField();
    JLabel lbContraR2 = new JLabel("Repetir contrasenya:");
    final JPasswordField ContraR2 = new JPasswordField();
    JButton btnRegistrar = new JButton("Registrar");
    final JLabel lbMssgR = new JLabel();

    public VistaInici2() {

        frameVista.add(panel);
        //frameVista.add(pInici);
        frameVista.setSize(500,500);
        //panel.setLayout(null);
        panel.setLayout(new BorderLayout());
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /*
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(700,400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        // Posicion y operaciones por defecto
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Se agrega panelContenidos al contentPane (el panelContenidos se
        // podria ahorrar y trabajar directamente sobre el contentPane)
        JPanel contentPane = (JPanel) frameVista.getContentPane();
        contentPane.add(panel);
        */


        panelInformacionA = pInici;
        iPanelActivo = 1;
        //panelInformacionA = pRegistrar;
        //iPanelActivo = 2;
        panel.add(panelInformacionA);

        activarInici();
        activarRegistre();

        hacerVisible();
    }

    public void activarInici() {
        VistaInici();
        asignar_listenersComponentes();
    }

    public void activarRegistre() {
        VistaRegistrar();
        asignar_listenersComponentesRegistre();
    }

    public void canviarPanel() {
        panel.remove(panelInformacionA);
        if (iPanelActivo == 1) {
            panelInformacionA = pRegistrar;
            iPanelActivo = 2;
        }
        else if(iPanelActivo == 2) {
            panelInformacionA = pInici;
            iPanelActivo = 1;
        }
        //panel.setLayout(new BorderLayout());
        //panelInformacionA.setLayout(new BorderLayout());
        panel.add(panelInformacionA);
        frameVista.pack();
        frameVista.repaint();
    }

    public void hacerVisible() {
        //frameVista.pack();
        frameVista.setVisible(true);
    }


    public void VistaInici() {
        lbUsuariIS.setBounds(20,20, 80,30);
        UsuariIS.setBounds(100,20, 100,30);


        lbContraIS.setBounds(20,75, 80,30);
        ContraIS.setBounds(100,75,100,30);
        ContraIS.setToolTipText("Contrasenya de X a Y...");


        btnEntrar.setBounds(100,120, 80,30);

        registrar.setBounds(20,150, 200,50);

        lbMssg.setBounds(20,175, 200,50);


        pInici.add(lbUsuariIS); pInici.add(UsuariIS);
        pInici.add(lbContraIS); pInici.add(ContraIS);
        pInici.add(btnEntrar);  pInici.add(lbMssg);
        pInici.add(registrar);

        pInici.setLayout(null);
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
                    JOptionPane.showMessageDialog(frameVista,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        registrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frameVista,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                canviarPanel();
            }
        });
    }

    public void VistaRegistrar() {
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

        pRegistrar.add(lbUsuariR); pRegistrar.add(UsuariR);
        pRegistrar.add(lbContraR); pRegistrar.add(ContraR);
        pRegistrar.add(lbContraR2); pRegistrar.add(ContraR2);
        pRegistrar.add(btnRegistrar); pRegistrar.add(lbMssgR);

        //pRegistrar.setLayout(new BorderLayout());
        //pRegistrar.setLayout(null);
    }

    private void asignar_listenersComponentesRegistre() {
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra, contra2;
                usuari = UsuariR.getText();
                contra = new String(ContraR.getPassword());
                contra2 = new String(ContraR2.getPassword());

                if(!contra.equals(contra2)) {
                    String error = "La contrasenya no concideix";
                    JOptionPane.showMessageDialog(frameVista,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
                String data = "Usuari " + usuari +
                        ", Contrasenya: " + contra +
                        ", Contra2: " + contra2;
                lbMssgR.setText(data);
                if(!usuari.equals("usr")) {
                    String error = "Usuari incorrecte ('usr')";
                    JOptionPane.showMessageDialog(frameVista,error,"Error",JOptionPane.ERROR_MESSAGE);
                }
                canviarPanel();
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
