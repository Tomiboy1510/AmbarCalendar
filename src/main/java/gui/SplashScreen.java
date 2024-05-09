package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        setSize(400, 300);
        setType(Type.POPUP);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(SplashScreen.class.getClassLoader().getResource("splash.png")));
        ImageIcon resizedIcon = new ImageIcon(icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH));

        JLabel label = new JLabel(resizedIcon);
        getContentPane().add(label, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
