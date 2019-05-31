package gui;

import domini.ControladorDomini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProblema extends JPanel implements ActionListener {
    //private JFrame frameVista = new JFrame("Crear Problema FEN");
    //private JMenuBar menuBar = new JMenuBar();
    private JFrame master;
    private VistaMenuPrincipal menuPrincipal;
    private JButton bAFEN = new JButton("Crear problema");
    private JButton bMFEN = new JButton("Modificar problema");
    private JLabel lFEN = new JLabel("Afegir problema FEN", JLabel.CENTER);
    private JButton bAG = new JButton("Crear problema");
    private JButton mAG = new JButton("Modificar problema");
    private JLabel lG = new JLabel("<html>Afegir problema <br/> gràficament</html>", JLabel.CENTER);
    private JButton bBorrar = new JButton("Borrar problema");
    private JPanel lrow = new JPanel(new GridLayout(1, 2));
    private JPanel crow = new JPanel(new GridLayout(1, 2));
    private JPanel mrow = new JPanel(new GridLayout(1, 2));


    public MenuProblema(JFrame master, VistaMenuPrincipal vmp) {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new GridLayout(4, 1));

        super(new GridLayout(4, 1));

        bAFEN.setFont(bAFEN.getFont().deriveFont(20.0f));
        bMFEN.setFont(bMFEN.getFont().deriveFont(20.0f));
        bAG.setFont(bAG.getFont().deriveFont(20.0f));
        mAG.setFont(mAG.getFont().deriveFont(20.0f));
        bBorrar.setFont(bBorrar.getFont().deriveFont(20.0f));
        lG.setFont(lG.getFont().deriveFont(25.0f));
        lFEN.setFont(lFEN.getFont().deriveFont(25.0f));

        this.menuPrincipal = vmp;
        lrow.add(lFEN);
        lrow.add(lG);
        //frameVista.add(lrow);
        this.add(lrow);
        bAFEN.addActionListener(this);
        bAFEN.setActionCommand("crear fen");
        crow.add(bAFEN);
        bAG.addActionListener(this);
        bAG.setActionCommand("crear grafic");
        crow.add(bAG);
        //frameVista.add(crow);
        this.add(crow);
        bMFEN.addActionListener(this);
        bMFEN.setActionCommand("modificar fen");
        mrow.add(bMFEN);
        mAG.setActionCommand("modificar grafic");
        mAG.addActionListener(this);
        mrow.add(mAG);
        //frameVista.add(mrow);
        this.add(mrow);
        //frameVista.add(bBorrar);
        bBorrar.setActionCommand("borrar");
        bBorrar.addActionListener(this);
        this.add(bBorrar);

        this.master = master;
        master.add(this);
        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
        String c = e.getActionCommand();
        switch (c) {
            case "crear fen":
                FENProblema fp = new FENProblema(master, menuPrincipal);
                master.setContentPane(fp);
                //master.pack();
                master.setVisible(true);
                break;
            case "crear grafic":
                CrearProblema cp = new CrearProblema(master, menuPrincipal);
                master.setContentPane(cp);
                //master.pack();
                master.setVisible(true);
                break;
            case "modificar fen":
                SeleccioProblema sp = new SeleccioProblema(master, menuPrincipal, define.MOD_FEN);
                master.setContentPane(sp);
                //master.pack();
                master.setVisible(true);
                break;
            case "modificar grafic":
                SeleccioProblema spg = new SeleccioProblema(master, menuPrincipal, define.MOD_GRAFIC);
                master.setContentPane(spg);
                //master.pack();
                master.setVisible(true);
                break;
            case "borrar":
                SeleccioProblema spb = new SeleccioProblema(master, menuPrincipal, define.BORRAR);
                master.setContentPane(spb);
                //master.pack();
                master.setVisible(true);
                break;
        }
    }
}
