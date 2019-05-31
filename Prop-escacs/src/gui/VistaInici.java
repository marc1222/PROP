package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaInici {
    private GameFrame main;
    private JFrame master;
    private ControladorDomini ctrlDomini;


    private JPanel panelInici = new JPanel();
    private JPanel panelContent = new JPanel();

    private JLabel labelUsuari = new JLabel("Usuari:");
    private JTextField fieldUsuari = new JTextField();
    private JLabel labelContra = new JLabel("Contrasenya:");
    private JPasswordField fieldContra = new JPasswordField();
    private JButton btnEntrar = new JButton("Entrar");

    private JLabel labelRegistrar = new JLabel("Clica aquí per registrar-te");

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
        gbc.insets = new Insets(20,50,0,50);
        gbc.ipady = 30;      //altura
        gbc.ipadx = 50;      //anchura

        //gbc.gridx = 0;
        gbc.gridy = 1;
        panelContent.add(labelUsuari, gbc);

        gbc.insets = new Insets(0,50,0,50);
        //gbc.gridx = 1;
        gbc.gridy = 2;
        panelContent.add(fieldUsuari, gbc);

        //gbc.gridx = 0;
        gbc.gridy = 3;
        panelContent.add(labelContra, gbc);
        //gbc.gridx = 1;
        gbc.gridy = 4;
        panelContent.add(fieldContra, gbc);

        //gbc.gridx = 0;
        gbc.insets = new Insets(30,50,0,50);
        gbc.gridy = 5;
        panelContent.add(btnEntrar, gbc);


        gbc.insets = new Insets(0,90,20,50);
        gbc.gridy = 6;
        panelContent.add(labelRegistrar, gbc);


        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));


        panelInici.setLayout(new GridBagLayout());
        panelInici.add(panelContent, new GridBagConstraints());
        setFont();
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
                actionIniciarSessio();
            }
        });

        labelRegistrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                VistaRegistrar reg = new VistaRegistrar(main, ctrlDomini);
            }
        });



        btnEntrar.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    /*
                    int key = e.getKeyCode();
                    String btn = "IF: " + key;
                    JOptionPane.showMessageDialog(master,btn,"Boton",JOptionPane.INFORMATION_MESSAGE);
                    */
                    actionIniciarSessio();
                }
            }
        });
    }

    private void actionIniciarSessio() {
        String usuari, contra;
        usuari = fieldUsuari.getText();
        contra = new String(fieldContra.getPassword());

        if(ctrlDomini.iniciarSessio(usuari, contra)) {
            main.setUsuari(usuari);
            VistaMenuPrincipal mainview = new VistaMenuPrincipal(main, ctrlDomini);
        }
        else {
            fieldUsuari.setText("");
            fieldContra.setText("");
            String error = "Dades incorrectes";
            JOptionPane.showMessageDialog(master,error,"Alerta",JOptionPane.WARNING_MESSAGE);
            btnEntrar.setFocusable(false);
        }
        btnEntrar.setFocusable(true);
    }

    private void setFont() {
        Font fntLabel = labelUsuari.getFont();
        Font fntBold = new Font(fntLabel.getName(), Font.BOLD, 20);
        Font fntPlain = new Font(fntLabel.getName(), Font.PLAIN, 18);

        labelUsuari.setFont(fntBold);
        labelContra.setFont(fntBold);
        fieldUsuari.setFont(fntPlain);
        fieldContra.setFont(fntPlain);
        labelRegistrar.setFont(new Font(fntLabel.getName(), Font.PLAIN, 15));
    }
}
