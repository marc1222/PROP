package gui;

import domini.Usuari;

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
    private JLabel labelRegistrar = new JLabel("Clica aquí per registrar-te");
    private JLabel labelInici = new JLabel("Clica aquí per iniciar sessió");





    JLabel lbUsuariR = new JLabel("Usuari:");
    final JTextField UsuariR = new JTextField();
    JLabel lbContraR = new JLabel("Contrasenya:");
    final JPasswordField ContraR = new JPasswordField();
    JLabel lbContraR2 = new JLabel("Repetir contrasenya:");
    final JPasswordField ContraR2 = new JPasswordField();
    JButton btnRegistrar = new JButton("Registrar");
    final JLabel lbMssgR = new JLabel();

    public VistaInici() {
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

        frameVista.add(panel);
        frameVista.setSize(700,650);
        //panel.setLayout(new BorderLayout());
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setLayout(new GridBagLayout());
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /*
        // Tamanyo
        frameVista.setMinimumSize(new Dimension(700,400));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setResizable(false);
        // Posicion y operaciones por defecto
        frameVista.setLocationRelativeTo(null);
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
        //panel.add(panelInformacionA, JPanel.CENTER_ALIGNMENT);
        panel.add(panelInformacionA);
        frameVista.pack();
        frameVista.setSize(700,650);
        frameVista.repaint();
    }

    public void hacerVisible() {
        frameVista.setVisible(true);
    }


    public void VistaInici() {
        //lbUsuariIS.setFont(new Font(lbUsuariIS.getFont().getName(), lbUsuariIS.getFont().getStyle(), 20));
        //btnEntrar.setPreferredSize(new Dimension(70, 50));

        pInici.add(lbUsuariIS); pInici.add(UsuariIS);
        pInici.add(lbContraIS); pInici.add(ContraIS);
        pInici.add(btnEntrar);  pInici.add(lbMssg);
        pInici.add(labelRegistrar);

        pInici.setLayout(new GridLayout(4, 2, 25, 25));
    }

    private void asignar_listenersComponentes() {
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra;
                usuari = UsuariIS.getText();
                contra = new String(ContraIS.getPassword());

                Usuari usr = new Usuari();
                if(usr.iniciarSessio(usuari, contra)) {
                    //String data = "Usuari CORRECTE";
                    //lbMssg.setText(data);
                    frameVista.dispose();
                    VistaMenuPrincipal3 vmp = new VistaMenuPrincipal3(usuari);
                    vmp.setVisible(true);
                }
                else {
                    UsuariIS.setText("");
                    ContraIS.setText("");
                    String data = "Usuari incorrecte";
                    lbMssg.setText(data);
                }

                /*
                String data = "Usuari " + usuari +
                        ", Contrasenya: " + contra;
                lbMssg.setText(data);
                if(!usuari.equals("usr")) {
                    String error = "Usuari incorrecte ('usr')";
                    JOptionPane.showMessageDialog(frameVista,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
                */
            }
        });

        labelRegistrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frameVista,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                canviarPanel();
            }
        });
    }

    public void VistaRegistrar() {
        pRegistrar.add(lbUsuariR); pRegistrar.add(UsuariR);
        pRegistrar.add(lbContraR); pRegistrar.add(ContraR);
        pRegistrar.add(lbContraR2); pRegistrar.add(ContraR2);
        pRegistrar.add(btnRegistrar); pRegistrar.add(lbMssgR);
        pRegistrar.add(labelInici);

        //pRegistrar.setLayout(new BorderLayout());
        //pRegistrar.setLayout(null);
        pRegistrar.setLayout(new GridLayout(5, 2, 25, 25));
    }

    private void asignar_listenersComponentesRegistre() {
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra1, contra2;
                usuari = UsuariR.getText();
                contra1 = new String(ContraR.getPassword());
                contra2 = new String(ContraR2.getPassword());

                if(usuari == null || usuari.isEmpty() ||
                        contra1 == null || contra1.isEmpty() ||
                        contra2 == null || contra2.isEmpty()) {
                    String error = "Omple tots els camps.";
                    lbMssgR.setText(error);
                }
                else if(!contra1.equals(contra2)) {
                    String error = "La contrasenya no concideix";
                    lbMssgR.setText(error);
                    //JOptionPane.showMessageDialog(frameVista,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                }
                else if(usuari.equals("Convidat")) {
                    String error = "El nom d'usuari no pot ser 'Convidat'.";
                    lbMssgR.setText(error);
                }
                else {
                    Usuari usr = new Usuari();
                    if (usr.registrar(usuari, contra1, contra2)) {
                        //String data = "Usuari REGISTRAT";
                        //lbMssgR.setText(data);
                        VistaMenuPrincipal3 vmp = new VistaMenuPrincipal3(usuari);
                        vmp.setVisible(true);
                        frameVista.dispose();
                    } else {
                        String data = "L'usuari ja existeix.";
                        lbMssgR.setText(data);
                    }
                }
                UsuariIS.setText("");
                ContraIS.setText("");
            }
        });

        labelInici.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frameVista,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                canviarPanel();
            }
        });
    }
}
