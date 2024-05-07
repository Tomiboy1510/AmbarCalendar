package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends MyJFrame {

    public MainFrame() {
        super();
        setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
        setTitle("Ámbar Beauty & Spa");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (0.7 * screenSize.getWidth()), (int) (0.7 * screenSize.getHeight()));

        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
    }
}
