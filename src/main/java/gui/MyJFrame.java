package gui;

import javax.swing.*;
import java.awt.*;

public abstract class MyJFrame extends JFrame {

    public MyJFrame() {
        getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(23,180,252));
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
    }
}
