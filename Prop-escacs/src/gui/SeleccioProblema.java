package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SeleccioProblema extends JPanel  implements ActionListener  {
  //  private JFrame frameVista = new JFrame("Selecció de problema");
  // private JMenuBar menuBar = new JMenuBar();
    private TProblemes llistaProblemes = new TProblemes();
    private JButton bcont = new JButton("Continua");
    private JLabel lMaquina = new JLabel("Selecciona quina màquina jugarà cada color", JLabel.CENTER);
    private JPanel selMaquina = new JPanel();
    private JLabel lBlanc = new JLabel("Blanques", JLabel.CENTER);
    private String[] sMaquina = {"Naive", "Smart"};
    private JComboBox cbBlanc = new JComboBox(sMaquina);
    private JLabel lNegre = new JLabel("Negres", JLabel.CENTER);
    private JComboBox cbNegre = new JComboBox(sMaquina);

    /**
     * 0 -> jugar
     * 1 -> simulacio
     * 2 -> estadistica
     */
    private int where;


    public SeleccioProblema() {//JugarPartidaView jpv) {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        // //frameVista.setResizable(false);
        //frameVista.add(llistaProblemes, BorderLayout.CENTER);
        //this.setLayout(new GridLayout(0,1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(llistaProblemes);
        bcont.setMnemonic(KeyEvent.VK_C); //Alt+C
        bcont.addActionListener(this);
        bcont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bcont.setAlignmentX(CENTER_ALIGNMENT);
        //frameVista.add(bcont, BorderLayout.PAGE_END);
        this.add(bcont);
        this.where = 2;


        //frameVista.getRootPane().setDefaultButton(bcont);
        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);
    }

    public SeleccioProblema(boolean jugar) {//JugarPartidaView jpv, boolean jugar) {
        //this.setLayout(new GridLayout(0,1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(llistaProblemes);
        bcont.setMnemonic(KeyEvent.VK_C); //Alt+C
        bcont.addActionListener(this);

        if (!jugar) {
            lMaquina.setAlignmentX(CENTER_ALIGNMENT);
            this.add(lMaquina);
            selMaquina.setLayout(new BoxLayout(selMaquina, BoxLayout.X_AXIS));
            lBlanc.setMaximumSize(new Dimension(500, 30));
            selMaquina.add(lBlanc);
            cbBlanc.setSelectedIndex(0);
            cbBlanc.setMaximumSize(new Dimension(500, 30));
            selMaquina.add(cbBlanc);
            lNegre.setMaximumSize(new Dimension(500, 30));
            selMaquina.add(lNegre);
            cbNegre.setSelectedIndex(0);
            cbNegre.setMaximumSize(new Dimension(500, 30));
            selMaquina.add(cbNegre);
            this.add(selMaquina);
            this.where = 1;
        }
        else this.where = 0;
        bcont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bcont.setAlignmentX(CENTER_ALIGNMENT);
        this.add(bcont);

    }

    public void actionPerformed(ActionEvent e) {
        //bcont.setEnabled(false);
        if (where == 0 || where == 2) {
            if (llistaProblemes.getTaula().getSelectedRowCount() == 0) {
            /*JOptionPane.showMessageDialog(frameVista, "Cap problema seleccionat.", "Error de selecció",
                    JOptionPane.ERROR_MESSAGE);*/
                JOptionPane.showMessageDialog(this, "Cap problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (llistaProblemes.getTaula().getSelectedRowCount() > 1) {
            /*JOptionPane.showMessageDialog(frameVista, "Més d'un problema seleccionat.", "Error de selecció",
                    JOptionPane.ERROR_MESSAGE);*/
                JOptionPane.showMessageDialog(this, "Més d'un problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                int i = llistaProblemes.getTaula().getSelectedRow();
                String id = (String) llistaProblemes.getTaula().getValueAt(i, 0);

            }
        }
        else {
            int k = llistaProblemes.getTaula().getSelectedRowCount();
            if (k == 0) {
                JOptionPane.showMessageDialog(this, "Cap problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                int rows[] = llistaProblemes.getTaula().getSelectedRows();
                String ids[] = new String[rows.length];
                for (int i = 0; i < rows.length; ++i) {
                    ids[i] = (String) llistaProblemes.getTaula().getValueAt(i, 0);
                }
            }


        }
    }

    private JMenu crea_menu() {
        final JMenu aux = new JMenu("Options");
        final JMenuItem exit = new JMenuItem("Exit app");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        aux.add(exit);
        return aux;
    }

    private static void iniciar() {
        SeleccioProblema vista = new SeleccioProblema();
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Seleccio Problema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        SeleccioProblema newContentPane = new SeleccioProblema(false);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
