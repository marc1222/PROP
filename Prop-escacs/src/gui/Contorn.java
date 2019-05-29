package gui;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;

public class Contorn {
    Border contorn;

    Contorn(int top, int left, int bottom, int right, Color color) {
        contorn = BorderFactory.createMatteBorder(top, left, bottom, right, color);
    }
    Border getBorder() {
        return contorn;
    }
}
