package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccioJugarPartida extends JPanel {
    private static Dimension SIZE = new Dimension(600,600);
    private boolean ataca;
    private int oponent;
    SeleccioJugarPartida(JugarPartidaView JPV) {
        super(new GridLayout(0,1));
        setPreferredSize(SIZE);
        ataca = false;
        oponent = 0;
        //JPanel left = new JPanel(new GridLayout(0));
        JLabel label1 = new JLabel("Selecciona un rol", JLabel.CENTER);
        label1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        label1.setFont (label1.getFont ().deriveFont (23.0f));
        add(label1);
        JRadioButton atacaButton = new JRadioButton("Atacar");
        atacaButton.setHorizontalAlignment(SwingConstants.CENTER);
        atacaButton.setFont (atacaButton.getFont ().deriveFont (15.0f));

        atacaButton.setSelected(true);

        JRadioButton defensaButton = new JRadioButton("Defensar");
        defensaButton.setHorizontalAlignment(SwingConstants.CENTER);
        defensaButton.setFont (defensaButton.getFont ().deriveFont (15.0f));

        ButtonGroup groupleft = new ButtonGroup();
        groupleft.add(atacaButton);
        groupleft.add(defensaButton);

        add(atacaButton);
        add(defensaButton);

        //add(left);

       // JPanel right = new JPanel(new GridLayout(0,1));
        JLabel label2 = new JLabel("Selecciona un oponent", JLabel.CENTER);
        label2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        label2.setFont (label2.getFont ().deriveFont (23.0f));

        JRadioButton userButton = new JRadioButton("Usuari 2");
        userButton.setHorizontalAlignment(SwingConstants.CENTER);
        userButton.setFont (userButton.getFont ().deriveFont (15.0f));

        userButton.setSelected(true);

        JRadioButton naiveButton = new JRadioButton("Naive (Maquina)");
        naiveButton.setHorizontalAlignment(SwingConstants.CENTER);
        naiveButton.setFont (naiveButton.getFont ().deriveFont (15.0f));

        JRadioButton smartButton = new JRadioButton("Smart (Maquina)");
        smartButton.setHorizontalAlignment(SwingConstants.CENTER);
        smartButton.setFont (smartButton.getFont ().deriveFont (15.0f));


        ButtonGroup groupright = new ButtonGroup();
        groupright.add(userButton);
        groupright.add(naiveButton);
        groupright.add(smartButton);

        add(label2);
        add(userButton);
        add(naiveButton);
        add(smartButton);

        JButton continuar = new JButton("Continua");
        continuar.setHorizontalTextPosition(AbstractButton.CENTER);
        continuar.setFont (continuar.getFont ().deriveFont (30.0f));

        continuar.setEnabled(true);
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ataca = false;
                if (atacaButton.isSelected()) {
                    ataca = true;
                }

                int oponent = define.USER; //3
                if (naiveButton.isSelected()) {
                    oponent = define.NAIVE; //4
                } else if (smartButton.isSelected()) {
                    oponent = define.SMART; //5
                }

                //TODO: SALTAR VISTA PARTIDA COGIENDO RESULTS
                JPV.change_to_play_view(ataca, oponent);

            }
        });

        continuar.setPreferredSize(new Dimension(40, 40));
        add(continuar);

        validate();
    }
}
