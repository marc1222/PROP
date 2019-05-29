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
    private JugarPartidaView jugarview;

    private JFrame master;
    /**
     * 0 -> jugar
     * 1 -> simulacio
     * 2 -> estadistica
     */
    private int where;


    public SeleccioProblema(JugarPartidaView jpv, int where, JFrame frame) {
        //menuBar.add(crea_menu());
        //frameVista.setJMenuBar(menuBar);
        //frameVista.setLayout(new BorderLayout());
        // //frameVista.setResizable(false);
        //frameVista.add(llistaProbSeleccioProblemalemes, BorderLayout.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.master = frame;
        this.add(llistaProblemes);
        bcont.setMnemonic(KeyEvent.VK_C); //Alt+C
        bcont.addActionListener(this);
        bcont.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        bcont.setAlignmentX(CENTER_ALIGNMENT);
        //frameVista.add(bcont, BorderLayout.PAGE_END);
        this.where = where;
        this.jugarview = jpv;

        if (this.where == define.simulacio) {
            lMaquina.setPreferredSize(new Dimension(500, 50));
            lMaquina.setAlignmentX(CENTER_ALIGNMENT);
            lMaquina.setFont (lMaquina.getFont ().deriveFont (18.0f));
            this.add(lMaquina);

            selMaquina.setLayout(new BoxLayout(selMaquina, BoxLayout.X_AXIS));
            lBlanc.setMaximumSize(new Dimension(500, 70));
            lBlanc.setFont (lBlanc.getFont ().deriveFont (15.0f));
            selMaquina.add(lBlanc);

            ((JLabel) cbBlanc.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            cbBlanc.setSelectedIndex(0);
            cbBlanc.setMaximumSize(new Dimension(500, 70));
            cbBlanc.setFont (cbBlanc.getFont ().deriveFont (15.0f));
            selMaquina.add(cbBlanc);

            lNegre.setMaximumSize(new Dimension(500, 70));
            lNegre.setFont (lNegre.getFont ().deriveFont (15.0f));
            selMaquina.add(lNegre);

            ((JLabel) cbNegre.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
            cbNegre.setMaximumSize(new Dimension(500, 700));
            cbNegre.setSelectedIndex(0);
            cbNegre.setFont (cbNegre.getFont ().deriveFont (15.0f));
            selMaquina.add(cbNegre);

            this.add(selMaquina);
            this.where = 1;

        }
        //frameVista.getRootPane().setDefaultButton(bcont);
        //frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameVista.pack();
        //frameVista.setVisible(true);

        bcont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        bcont.setMinimumSize(new Dimension(Integer.MAX_VALUE, 60));
       // bcont.setAlignmentX(CENTER_ALIGNMENT);
        bcont.setHorizontalTextPosition(AbstractButton.CENTER);
        bcont.setFont (bcont.getFont ().deriveFont (30.0f));

        this.add(bcont);
        
        master.setContentPane(this);
        master.pack();
        master.setVisible(true);

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

    public SeleccioProblema(JFrame master, int where) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(llistaProblemes);
        bcont.setMnemonic(KeyEvent.VK_C); //Alt+C
        bcont.addActionListener(this);
        bcont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bcont.setAlignmentX(CENTER_ALIGNMENT);
        this.where = where;
        bcont.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        bcont.setAlignmentX(CENTER_ALIGNMENT);
        this.add(bcont);
        this.master = master;
    }

    public void actionPerformed(ActionEvent e) {
        if (where == define.normal || where == define.estadistica || where == define.MOD_FEN
                || where == define.MOD_GRAFIC || where == define.BORRAR) {
            if (llistaProblemes.getTaula().getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Cap problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (llistaProblemes.getTaula().getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(this, "Més d'un problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                int i = llistaProblemes.getTaula().getSelectedRow();

                if (this.where == define.normal) {
                    String id = (String) llistaProblemes.getTaula().getValueAt(i, 0);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            jugarview.setProblemView(Integer.valueOf(id));
                        }
                    });
                }
                else if (this.where == define.simulacio){
                    //this.where == define.estadistica
                    //TODO CRIDAR a gestor de les vistes d'estadistca perque faci el canvi,
                    // o directament passan el JFRAME i guardantlo
                }
                else if (this.where == define.MOD_FEN || this.where == define.MOD_GRAFIC) {
                    String fen = (String) llistaProblemes.getTaula().getValueAt(i, 3);
                    int njug = Integer.parseInt((String)llistaProblemes.getTaula().getValueAt(i, 1));
                    Color c = (Color) llistaProblemes.getTaula().getValueAt(i, 2);
                    int prim;
                    if (c.equals(Color.BLACK)) prim = define.BLACK;
                    else prim = define.WHITE;
                    if (this.where == define.MOD_FEN) {
                        FENProblema fp = new FENProblema(fen, njug, prim);
                        master.setContentPane(fp);
                        master.pack();
                        master.setVisible(true);
                    }
                    else {
                        CrearProblema cp = new CrearProblema(fen, njug);
                        master.setContentPane(cp);
                        master.pack();
                        master.setVisible(true);
                    }
                }
            }
        }
        else {
            //this.where == define.SIMULACIO
            int k = llistaProblemes.getTaula().getSelectedRowCount();
            if (k == 0) {
                JOptionPane.showMessageDialog(this, "Cap problema seleccionat.", "Error de selecció",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                int rows[] = llistaProblemes.getTaula().getSelectedRows();
                int ids[] = new int[rows.length];
                for (int i = 0; i < rows.length; ++i) {
                    ids[i] = Integer.valueOf(((String) llistaProblemes.getTaula().getValueAt(rows[i], 0)));
                }
                int blanc = (cbBlanc.getSelectedItem().toString().equals("Naive"))?define.NAIVE:define.SMART;
                int negre = (cbNegre.getSelectedItem().toString().equals("Naive"))?define.NAIVE:define.SMART;
                jugarview.startSimulacio(ids, blanc, negre);
            }

        }
    }

//    private JMenu crea_menu() {
//        final JMenu aux = new JMenu("Options");
//        final JMenuItem exit = new JMenuItem("Exit app");
//        exit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//        aux.add(exit);
//        return aux;
//    }

//    private static void iniciar() {
//        SeleccioProblema vista = new SeleccioProblema();
//    }

//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                iniciar();
//            }
//        });
//    }
}
