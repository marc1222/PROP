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

    JLabel labelUsuari = new JLabel("Usuari:");
    final JTextField fieldUsuari = new JTextField();
    JLabel labelContra1 = new JLabel("Contrasenya:");
    final JPasswordField fieldContra1 = new JPasswordField();
    JLabel labelContra2 = new JLabel("Repetir contrasenya:");
    final JPasswordField fieldContra2 = new JPasswordField();
    JButton btnRegistrar = new JButton("Registrar");

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


        gbc.insets = new Insets(20,50,0,50);
        gbc.ipady = 30;
        gbc.ipadx = 50;

        gbc.gridy = 1;
        panelContent.add(labelUsuari, gbc);

        gbc.insets = new Insets(0,50,0,50);
        gbc.gridy = 2;
        panelContent.add(fieldUsuari, gbc);

        gbc.gridy = 3;
        panelContent.add(labelContra1, gbc);
        gbc.gridy = 4;
        panelContent.add(fieldContra1, gbc);

        gbc.gridy = 5;
        panelContent.add(labelContra2, gbc);
        gbc.gridy = 6;
        panelContent.add(fieldContra2, gbc);


        gbc.insets = new Insets(30,50,0,50);
        gbc.gridy = 7;
        panelContent.add(btnRegistrar, gbc);

        gbc.insets = new Insets(0,90,20,50);
        gbc.gridy = 8;
        panelContent.add(labelInici, gbc);




        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));


        panelRegistrar.setLayout(new GridBagLayout());
        panelRegistrar.add(panelContent, new GridBagConstraints());

        setFont();
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
                actionRegistrar();
            }
        });

        labelInici.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                VistaInici startview = new VistaInici(main, ctrlDomini);
            }
        });

        btnRegistrar.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionRegistrar();
                }
            }
        });
    }

    private void actionRegistrar() {
        String usuari, contra1, contra2;
        usuari = fieldUsuari.getText();
        contra1 = new String(fieldContra1.getPassword());
        contra2 = new String(fieldContra2.getPassword());

        if(usuari == null || usuari.isEmpty() ||
                contra1 == null || contra1.isEmpty() ||
                contra2 == null || contra2.isEmpty()) {
            String error = "Omple tots els camps.";
            JOptionPane.showMessageDialog(master,error,"Alerta",JOptionPane.WARNING_MESSAGE);
            btnRegistrar.setFocusable(false);
        }
        else if(!contra1.equals(contra2)) {
            String error = "La contrasenya no concideix";
            JOptionPane.showMessageDialog(master,error,"Alerta",JOptionPane.WARNING_MESSAGE);
            btnRegistrar.setFocusable(false);
        }
        else if(usuari.equals("Convidat")) {
            String error = "El nom d'usuari no pot ser 'Convidat'.";
            JOptionPane.showMessageDialog(master,error,"Alerta",JOptionPane.WARNING_MESSAGE);
            btnRegistrar.setFocusable(false);
        }
        else {
            if (ctrlDomini.registrar(usuari, contra1, contra2)) {
                main.setUsuari(usuari);
                VistaMenuPrincipal mainview = new VistaMenuPrincipal(main, ctrlDomini);
                //JOptionPane.showMessageDialog(master,"T'has registrat correctament","Inici",JOptionPane.INFORMATION_MESSAGE);
            } else {
                String error = "L'usuari ja existeix.";
                JOptionPane.showMessageDialog(master,error,"Alerta",JOptionPane.WARNING_MESSAGE);
                btnRegistrar.setFocusable(false);
            }
        }
        fieldUsuari.setText("");
        fieldContra1.setText("");
        fieldContra2.setText("");
        btnRegistrar.setFocusable(true);
    }

    private void setFont() {
        Font fntLabel = labelUsuari.getFont();
        Font fntBold = new Font(fntLabel.getName(), Font.BOLD, 20);
        Font fntPlain = new Font(fntLabel.getName(), Font.PLAIN, 18);

        labelUsuari.setFont(fntBold);
        labelContra1.setFont(fntBold);
        labelContra2.setFont(fntBold);
        fieldUsuari.setFont(fntPlain);
        fieldContra1.setFont(fntPlain);
        fieldContra2.setFont(fntPlain);
        labelInici.setFont(new Font(fntLabel.getName(), Font.PLAIN, 15));
    }
}
