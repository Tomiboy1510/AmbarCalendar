package gui;

import javax.swing.*;
import java.awt.*;

public abstract class MyJFrame extends JFrame {

    public MyJFrame() {
        getRootPane().putClientProperty("JRootPane.titleBarBackground", MyUiConstants.color);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
    }
}
