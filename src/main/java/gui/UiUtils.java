package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class UiUtils {

    public static final Color color = new Color(23,180,252);

    public static final Font font = new Font("Verdana", Font.PLAIN, 15);

    public static final Color[] greyscale = {
            new Color(210, 210, 210),
            new Color(222, 222, 222),
            new Color(228, 228, 228),
            new Color(230, 230, 230)
    };

    public static void setup() {
        FlatIntelliJLaf.setup();

        JFrame.setDefaultLookAndFeelDecorated(true);

        UIManager.put("defaultFont", font);
        UIManager.put("ScrollBar.thumb", greyscale[0]);
        UIManager.put("ScrollBar.width", 15);
    }
}
