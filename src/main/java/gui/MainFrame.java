package gui;

import java.awt.*;

public class MainFrame extends MyJFrame {
    public MainFrame() {
        super();
        setTitle("Ámbar Beauty & Spa");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (0.7 * screenSize.getWidth()), (int) (0.7 * screenSize.getHeight()));
        setLocationRelativeTo(null);

        setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
    }
}
