package ui;

import javax.swing.*;

/**
 * Main entry point for the Event Planner GUI.
 * Creates a JFrame and adds an EventListPanel to it.
 */
public class EventPlanner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Event Planner");
            EventListPanel eventListPanel = new EventListPanel();
            EventListPanel.addDefaultEvents(eventListPanel);

            frame.add(eventListPanel);
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

