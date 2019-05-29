package gui;

import domini.ControladorDomini;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaRegistrar {
    private GameFrame main;
    private JFrame master;
    private ControladorDomini ctrlDomini;

    private JPanel panelRegistrar = new JPanel();
    private JPanel panelContent = new JPanel();
    JLabel lbUsuariR = new JLabel("Usuari:");
    final JTextField UsuariR = new JTextField();
    JLabel lbContraR = new JLabel("Contrasenya:");
    final JPasswordField ContraR = new JPasswordField();
    JLabel lbContraR2 = new JLabel("Repetir contrasenya:");
    final JPasswordField ContraR2 = new JPasswordField();
    JButton btnRegistrar = new JButton("Registrar");
    final JLabel lbMssgR = new JLabel();
    private JLabel labelInici = new JLabel("Clica aquí per iniciar sessió");

    public VistaRegistrar(GameFrame mainGame, ControladorDomini ctrlDomini) {
        this.master = mainGame.getGameFrame();
        this.main = mainGame;
        this.ctrlDomini = ctrlDomini;

        initComponents();
        asignar_listenersComponentes();
        ferVisible();
    }

    private void initComponents() {
        panelContent = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,50,10,50);
        gbc.ipady = 40;      //altura
        gbc.ipadx = 50;      //anchura

        gbc.gridy = 1;
        lbUsuariR.setFont(new Font("Tahoma", 0, 18));
        panelContent.add(lbUsuariR, gbc);
        gbc.gridy = 2;
        panelContent.add(UsuariR, gbc);

        gbc.gridy = 3;
        panelContent.add(lbContraR, gbc);
        gbc.gridy = 4;
        panelContent.add(ContraR, gbc);

        gbc.gridy = 5;
        panelContent.add(lbContraR2, gbc);
        gbc.gridy = 6;
        panelContent.add(ContraR2, gbc);

        gbc.gridy = 7;
        panelContent.add(btnRegistrar, gbc);

        gbc.gridy = 8;
        panelContent.add(labelInici, gbc);

        gbc.gridy = 9;
        panelContent.add(lbMssgR, gbc);



        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));


        panelRegistrar.setLayout(new GridBagLayout());
        panelRegistrar.add(panelContent, new GridBagConstraints());

        //pRegistrar.setLayout(new BorderLayout());
        //pRegistrar.setLayout(null);
        //panelContent.setLayout(new GridLayout(5, 2, 25, 25));
        //panelRegistrar.add(panelContent, BorderLayout.CENTER);
    }

    public void ferVisible() {
        this.master.getContentPane().removeAll();
        panelRegistrar.add(panelContent);
        this.master.add(panelRegistrar);
        this.master.repaint();
        this.master.setVisible(true);
    }

    private void asignar_listenersComponentes() {
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
                    if (ctrlDomini.registrar(usuari, contra1, contra2)) {
                        //String data = "Usuari REGISTRAT";
                        //lbMssgR.setText(data);
                        //VistaMenuPrincipal3 vmp = new VistaMenuPrincipal3(usuari);
                        //vmp.setVisible(true);
                        //master.dispose();

                        //String data = "REGISTRAT.";
                        //lbMssgR.setText(data);
                        VistaMenuPrincipal mainview = new VistaMenuPrincipal(main, ctrlDomini, usuari);
                    } else {
                        String data = "L'usuari ja existeix.";
                        lbMssgR.setText(data);
                    }
                }
                UsuariR.setText("");
                ContraR.setText("");
                ContraR2.setText("");
            }
        });

        labelInici.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(master,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                VistaInici startview = new VistaInici(main, ctrlDomini);
            }
        });
    }
}
