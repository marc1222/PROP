package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaInici {
    private GameFrame main;
    private JFrame master;
    private ControladorDomini ctrlDomini;


    private JPanel panelInici = new JPanel();
    //private JPanel jPanel2 = new JPanel();
    private JPanel panelContent = new JPanel();
    private JLabel lbUsuariIS = new JLabel("Usuari:");
    private JTextField UsuariIS = new JTextField();
    private JLabel lbContraIS = new JLabel("Contrasenya:");
    private JPasswordField ContraIS = new JPasswordField();
    private JButton btnEntrar = new JButton("Entrar");
    private JLabel lbMssg = new JLabel();
    private JLabel labelRegistrar = new JLabel("Clica aquí per registrar-te");
    private JLabel jLabel1 = new JLabel();

    public VistaInici(GameFrame mainGame, ControladorDomini ctrlDomini) {
        this.master = mainGame.getGameFrame();
        this.main = mainGame;
        this.ctrlDomini = ctrlDomini;

        /*
        ImageIcon image = new ImageIcon("/asset/fondo.png");
        JLabel imagelabel = new JLabel(image);
        jPanel2.setBounds(0, 0, 730, 540);
        imagelabel.setBounds(0, 0, 730, 540);
        jPanel2.add(imagelabel, BorderLayout.CENTER);
        panelInici.add(jPanel2);
        */

        initComponents();
        asignar_listenersComponentes();
        ferVisible();
    }


    public void initComponents() {
        //lbUsuariIS.setFont(new Font(lbUsuariIS.getFont().getName(), lbUsuariIS.getFont().getStyle(), 20));
        //btnEntrar.setPreferredSize(new Dimension(70, 50));


        //panelContent = new JPanel(new GridBagLayout());
        panelContent = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /*
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,50,0);
        jPanel1.add(jLabel1, gbc);
        */
        gbc.insets = new Insets(0,50,10,50);
        gbc.ipady = 40;      //altura
        gbc.ipadx = 50;      //anchura

        //gbc.gridx = 0;
        gbc.gridy = 1;
        lbUsuariIS.setFont(new Font("Tahoma", 0, 18));
        panelContent.add(lbUsuariIS, gbc);
        //gbc.gridx = 1;
        gbc.gridy = 2;
        panelContent.add(UsuariIS, gbc);

        //gbc.gridx = 0;
        gbc.gridy = 3;
        panelContent.add(lbContraIS, gbc);
        //gbc.gridx = 1;
        gbc.gridy = 4;
        panelContent.add(ContraIS, gbc);

        //gbc.gridx = 0;
        gbc.gridy = 5;
        panelContent.add(btnEntrar, gbc);

        gbc.gridy = 6;
        panelContent.add(labelRegistrar, gbc);

        gbc.gridy = 7;
        panelContent.add(lbMssg, gbc);


        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));


        panelInici.setLayout(new GridBagLayout());
        panelInici.add(panelContent, new GridBagConstraints());

    }

    public void ferVisible() {
        this.master.getContentPane().removeAll();
        panelInici.add(panelContent);
        this.master.add(panelInici);
        this.master.repaint();
        this.master.setVisible(true);
    }


    private void asignar_listenersComponentes() {
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuari, contra;
                usuari = UsuariIS.getText();
                contra = new String(ContraIS.getPassword());

                if(ctrlDomini.iniciarSessio(usuari, contra)) {
                    //String data = "Usuari CORRECTE";
                    //lbMssg.setText(data);
                    //master.dispose();
                    //VistaMenuPrincipal3 vmp = new VistaMenuPrincipal3(usuari);
                    //vmp.setVisible(true);

                    String data = "SESSIO INICIADA";
                    lbMssg.setText(data);
                    //JugarPartidaView partida = new JugarPartidaView(main, true, ctrlDomini);
                    VistaMenuPrincipal mainview = new VistaMenuPrincipal(main, ctrlDomini, usuari);
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
                //JOptionPane.showMessageDialog(master,"afdadfadf","Alerta",JOptionPane.WARNING_MESSAGE);
                VistaRegistrar reg = new VistaRegistrar(main, ctrlDomini);
            }
        });
    }


}
