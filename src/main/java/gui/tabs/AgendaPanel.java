package gui.tabs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AgendaPanel extends JPanel {

    public AgendaPanel() {
        super();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        topPanel.add(new JButton("1"));
        topPanel.add(new JButton("2"));
        topPanel.add(new JButton("3"));

        JPanel bottomPanel = new JPanel();

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
    }
}
