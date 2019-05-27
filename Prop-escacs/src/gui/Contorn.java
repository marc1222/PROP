package gui;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;

public class Contorn {
    Border contorn;

    Contorn(int thickness, Color color) {
        contorn = BorderFactory.createLineBorder(color, thickness);
    }
    Border getBorder() {
        return contorn;
    }
}
