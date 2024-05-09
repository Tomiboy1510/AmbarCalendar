package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class UiUtils {

    public static final Color MAIN_COLOR = new Color(23,180,252);

    public static final Font FONT = loadFont("OpenSans-VariableFont_wdth,wght.ttf");

    public static final Color[] GREYSCALE = {
            new Color(210, 210, 210),
            new Color(222, 222, 222),
            new Color(228, 228, 228),
            new Color(230, 230, 230)
    };

    public static void setup() {
        FlatIntelliJLaf.setup();
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("defaultFont", FONT);
        UIManager.put("ScrollBar.thumb", GREYSCALE[0]);
        UIManager.put("ScrollBar.width", 15);
    }

    public static Font loadFont(String fontResourcePath) {
        try (InputStream stream = UiUtils.class.getClassLoader().getResourceAsStream(fontResourcePath)) {
            return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(Font.PLAIN, 15);
        } catch (Exception e) {
            return new Font("Verdana", Font.PLAIN, 15);
        }
    }
}
