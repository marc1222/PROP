package presentacio;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
    private JFrame frameVista = new JFrame("Vista Principal");
    private JPanel panelContenidos = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JButton btnJugar = new JButton("Jugar");
    private JButton btnGestioProblema = new JButton("Gestio Problema");
    private JButton btnBaixa = new JButton("Baixa");
    private JButton btnEstadistica = new JButton("Estadistica");
    private JButton btnTancarSessio = new JButton("Tancar sessio");

    public VistaPrincipal() {
        inicializarComponentes();
        hacerVisible();
    }

    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    private void inicializarComponentes() {
        inicializar_frameVista();
        inicializar_panelContenidos();
        inicializar_panelBotones();
        //asignar_listenersComponentes();
    }

    private void inicializar_frameVista() {
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
        contentPane.add(panelContenidos);
    }


    private void inicializar_panelContenidos() {
        // Layout
        panelContenidos.setLayout(new GridBagLayout());
        // Paneles
        panelContenidos.add(panelBotones);
    }

    private void inicializar_panelBotones() {
        panelBotones.setMinimumSize(new Dimension(300, 300));
        //panelBotones.setMaximumSize(new Dimension(300, 300));
        panelBotones.setPreferredSize(new Dimension(300, 300));
        // El panelInformacion es solo un contenedor para panelInformacionA, que
        // contendra panelInformacion1 (inicialmente) o panelInformacion2
        panelBotones.setLayout(new GridLayout(5, 1, 0,10));
        panelBotones.add(btnJugar);
        panelBotones.add(btnGestioProblema);
        panelBotones.add(btnBaixa);
        panelBotones.add(btnEstadistica);
        panelBotones.add(btnTancarSessio);
    }




}
