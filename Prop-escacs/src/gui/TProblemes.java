package gui;

import domini.Problema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TProblemes extends JPanel {
    private JTable taula;

    public TProblemes(boolean foo) {
        super(new GridLayout(1,0));

        String[] nomColumnes = {"ID",
                "Num. de Jugades",
                "Color que comença",
                "Dificultat (0-10)",
                "Posició inicial FEN"};

        String[][] dades = Problema.consultarProblemes();

        for (String[] prob : dades) {
            if (prob[2].equals("0")) {
                prob[2] = "Blanc";
            }
            else prob[2] = "Negre";
        }

        //final JTable taula = new JTable(dades, nomColumnes);
        taula = new JTable(dades, nomColumnes);
        taula.setPreferredScrollableViewportSize(new Dimension(500, 70));
        taula.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(taula);

        add(scrollPane);
    }

    public TProblemes() {
        super(new GridLayout(1, 0));

        //JTable taula = new JTable(new myTaula());
        taula = new JTable(new myTaula());
        taula.setPreferredScrollableViewportSize(new Dimension(500, 70));
        taula.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(taula);

        taula.setDefaultRenderer(Color.class, new RenderColor(true));

        add(scrollPane);
    }

    public JTable getTaula() {
        return taula;
    }

    class myTaula extends AbstractTableModel {
        private String[] nomColumnes = {"ID",
                                        "Num. de Jugades",
                                        "Color que comença",
                                        "Posició inicial FEN",
                                        "Dificultat (0-10)",
                                        "ID Usuari Creador"};

        private Object[][] dades = getDades();

        private Object[][] getDades() {
            String[][] problemes = Problema.consultarProblemes();
            Object[][] res = new Object[problemes.length][6];

            for (int i = 0; i < problemes.length; ++i) {
                for (int j = 0; j < problemes[0].length; ++j) {
                    if (j != 2) res[i][j] = problemes[i][j];
                    else {
                        if (problemes[i][j].equals("0")) {
                            res[i][j] = new Color(215, 215, 215);
                        } else res[i][j] = new Color(0, 0, 0);
                    }
                }
            }
            return res;
        }

        public int getColumnCount() {
            return nomColumnes.length;
        }

        public int getRowCount() {
            return dades.length;
        }

        public String getColumnName(int col) {
            return nomColumnes[col];
        }

        public Object getValueAt(int row, int col) {
            return dades[row][col];
        }

        public Class getColumnClass(int c) {
            Class ret = null;
            try {
                ret = getValueAt(0, c).getClass();
            } catch (Exception e) {
                ret = null;
            }
            finally {
                return ret;
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TProblemes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TProblemes newContentPane = new TProblemes();
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
