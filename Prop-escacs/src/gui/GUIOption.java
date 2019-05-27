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
    boolean crono_ences;
    private int num_ronda = 0;

    private JLabel info_timer = new JLabel("TEMPS: 00:00", JLabel.CENTER);
    private JLabel info_act = new JLabel("TORN: ", JLabel.CENTER);
    private JLabel info_ronda = new JLabel("RONDA 1", JLabel.CENTER);



    GUIOption() {
        super(new GridLayout(1, 3));
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(OPTION_SIZE);
        setBackground(new Color(0,0,0));
        add(info_timer);
        add(info_act);
        add(info_ronda);
        validate();

    }

    /**
     *
     * @param ataca - true si el user loggegat ataca, false altrament
     */
    public void initCrono(boolean ataca) {
        this.crono_ences = ataca;
    }

    public void set_torn(int color) {
        if (color == define.WHITE) {
            info_act.setText("TORN: BLANQUES");
        } else {
            info_act.setText("TORN: NEGRES");
        }
        this.actual = this.actual==define.WHITE?define.BLACK:define.WHITE;
    }

    public void start_timer() {
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time_count += 0.5;
                if (time_count < 200000) {
                    info_timer.setText("TEMPS: "+Float.toString(time_count)+" s");
                } else {
                    ((Timer) (e.getSource())).stop();
                }
            }
        });
        //timer.setInitialDelay(0);
        timer.start();
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
            start_timer();
            crono_ences = false;
        } else {
           //CRONO ENCES, per ser parat
            stop_timer();
            crono_ences = true;
        }
        num_ronda++;
        info_ronda.setText("RONDA "+num_ronda);
    }

}
