package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Base class for all frames in the application,
 * custom {@link JFrame} that simply sets some colors for the title bar.
 */
public class MyJFrame extends JFrame {

    public MyJFrame() {
        getRootPane().putClientProperty("JRootPane.titleBarBackground", UiUtils.MAIN_COLOR);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
    }
}
