package gui.tabs;

import persistence.dao.TurnoDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * One of the tabs in the main window of the application.
 * Contains a calendar of appointments for services.
 */
public class AgendaTab extends JPanel {

    public AgendaTab(TurnoDAO turnoDAO) {
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
