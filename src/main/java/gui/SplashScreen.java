package gui;

import javax.swing.*;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        setSize(400, 300);
        getContentPane().setBackground(MyUiConstants.color);
        setType(Type.POPUP);

        JLabel label = new JLabel(new ImageIcon("splashscreen.png"));
        getContentPane().add(label);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
