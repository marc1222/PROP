package gui;

import javax.swing.*;
import java.awt.*;

public class GUIOption extends JPanel {
    private static Dimension OPTION_SIZE = new Dimension(700,80);

    GUIOption() {
        super(new GridBagLayout());
        setPreferredSize(OPTION_SIZE);
        setBackground(new Color(0,0,0));
        validate();
    }

}
