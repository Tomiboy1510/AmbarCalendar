package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

/**
 * Utility class that provides constants and methods for setting up the look and feel of the application.
 */
public class UiUtils {

    /**
     * Main color of the app, used for frame title bars
     */
    public static final Color MAIN_COLOR = new Color(23,180,252);

    /**
     * Font used all throughout the application
      */
    public static final Font FONT = loadFont("OpenSans-VariableFont_wdth,wght.ttf");

    /**
     * Greyscale colors used for various purposes, from darker to lighter
     */
    public static final Color[] GREYSCALE = {
            new Color(210, 210, 210),
            new Color(222, 222, 222),
            new Color(228, 228, 228),
            new Color(230, 230, 230)
    };

    /**
     * Sets up {@link FlatIntelliJLaf} look and feel and then changes some UI properties
     */
    public static void setup() {
        FlatIntelliJLaf.setup();
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("defaultFont", FONT);
        UIManager.put("ScrollBar.thumb", GREYSCALE[0]);
        UIManager.put("ScrollBar.width", 15);
    }

    /**
     * Loads a .ttf file and creates a Font object with it
     * @param fontResourcePath the path of the font file
     * @return a Font object created from the file at {@code fontResourcePath}.
     * If an exception is thrown, a default Font is returned instead
     */
    public static Font loadFont(String fontResourcePath) {
        try (InputStream stream = UiUtils.class.getClassLoader().getResourceAsStream(fontResourcePath)) {
            return Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(stream)).deriveFont(Font.PLAIN, 15);
        } catch (Exception e) {
            // Return default font
            return new Font("Verdana", Font.PLAIN, 15);
        }
    }
}
