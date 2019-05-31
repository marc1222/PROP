package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIOption extends JPanel {
    private static Dimension OPTION_SIZE = new Dimension(700,100);

    private Timer timer = null;
    private float time_count = 0;
    private int actual;
    private boolean crono_ences = false;
    private int num_ronda = 0;
    private int prob_max_jug;
    private Contorn margin;
    public boolean simulacio;
    private boolean timer_stat;

    private JLabel info_timer = new JLabel("Temps: 0 s", JLabel.CENTER);
    private JLabel info_act = new JLabel("Torn: ", JLabel.CENTER);
    private JLabel info_ronda;
    private JLabel info_prob = new JLabel("Problema: ", JLabel.CENTER);

    private JPanel info_players = new JPanel(new GridLayout(0,1));
    private JPanel panel_main = new JPanel(new GridLayout(0,1));
    private JPanel panel_second = new JPanel(new GridLayout(0,1));


    private JLabel white_player = new JLabel("B: ", JLabel.LEFT);
    private JLabel black_player = new JLabel("N: ", JLabel.LEFT);

    public void resetSim(int actual) {
        crono_ences = false;
        num_ronda = 0;
        this.actual = actual;
    }
    public int getclock() {
        return (int)(this.time_count*1000);
    }

    GUIOption(boolean simulacio, int actual) {
        super(new GridLayout(1, 3));
        this.simulacio = simulacio;
        this.actual = actual;
        System.out.print(actual);
        margin = new Contorn(20,50, 0, 50, define.BoardBorderColor);
        setBorder(margin.getBorder());
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(OPTION_SIZE);
        setBackground(define.BoardBorderColor);

        white_player.setFont (white_player.getFont ().deriveFont (23.0f));
        black_player.setFont (white_player.getFont ().deriveFont (23.0f));
        info_timer.setFont (white_player.getFont ().deriveFont (23.0f));
        info_prob.setFont (white_player.getFont ().deriveFont (23.0f));
        info_players.setFont (white_player.getFont ().deriveFont (23.0f));
        info_ronda = new JLabel("Ronda 0/", JLabel.CENTER);
        info_ronda.setFont (white_player.getFont ().deriveFont (23.0f));
        info_act.setFont (white_player.getFont ().deriveFont (23.0f));

        white_player.setForeground(Color.white);
        black_player.setForeground(Color.white);
        info_act.setForeground(Color.white);
        info_ronda.setForeground(Color.white);
        info_players.setForeground(Color.white);
        info_prob.setForeground(Color.white);
        info_timer.setForeground(Color.white);


        info_players.add(white_player);
        info_players.add(black_player);
        info_players.setBackground(define.BoardBorderColor);
        add(info_players);

        panel_main.add(info_timer);
        panel_main.add(info_act);
        panel_main.setBackground(define.BoardBorderColor);
        add(panel_main);

        panel_second.add(info_prob);
        panel_second.add(info_ronda);
        panel_second.setBackground(define.BoardBorderColor);
        add(panel_second);

        validate();

    }

    /**
     *
     */
    public void setInfo(String white, String black, int idprob, int max_rondes) {
        if (!white.equals("null")) white_player.setText("B: "+white);
        if (!black.equals("null"))black_player.setText("N: "+black);
        info_prob.setText("Problema: "+idprob);
        this.prob_max_jug = max_rondes;
        info_ronda.setText("Ronda: 1/"+max_rondes);

        if (this.simulacio) info_timer.setText("SIMULACIÓ");
    }

    public void initCrono(boolean ataca) {
        this.timer_stat = ataca;
        this.crono_ences = ataca;
    }

    public void set_torn(int color) {
        if (color == define.WHITE) {
            info_act.setText("Torn: Blanques");
        } else {
            info_act.setText("Torn: Negres");
        }
        this.actual = this.actual==define.WHITE?define.BLACK:define.WHITE;
    }

    public void start_timer() {
        if (this.num_ronda<=this.prob_max_jug && timer_stat && !this.simulacio) {
            timer = new Timer(200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time_count += 0.2;
                    if (time_count < 200000) {
                        info_timer.setText("Temps: " + String.format("%.2f", time_count) + " s");
                    } else {
                        ((Timer) (e.getSource())).stop();
                    }
                }
            });
            timer.start();
        }
    }
    public void stop_timer() {
        if (timer != null) timer.stop();
        //timer = null;
    }
    public void update_stats() {
        //update label torn
        set_torn(this.actual);
        //update crono
        if (crono_ences){
            //CRONO PARAT, per a ser ences
            timer_stat = true;
            start_timer();
            crono_ences = false;
        } else {
           //CRONO ENCES, per ser parat
            timer_stat = false;
            stop_timer();
            crono_ences = true;
        }
        num_ronda++;
        info_ronda.setText("Ronda "+num_ronda+"/"+this.prob_max_jug);
    }

}
