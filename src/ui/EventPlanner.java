package ui;

import javax.swing.*;

/**
 * Main entry point for the Event Planner GUI.
 * This sets up the main window and displays the event list panel.
 */
public class EventPlanner {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater ensures the GUI runs on the correct thread.
        SwingUtilities.invokeLater(() -> {
            // Create the main application window (JFrame).
            JFrame frame = new JFrame("Event Planner");

            // Create the panel that holds the list of events.
            EventListPanel eventListPanel = new EventListPanel();

            // Add some default events so the app isn’t empty when it starts.
            EventListPanel.addDefaultEvents(eventListPanel);

            // Add the event list panel to the frame.
            frame.add(eventListPanel);

            // Set the window size (600px by 400px).
            frame.setSize(600, 400);

            // Make sure the program exits when the window is closed.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Make the window visible.
            frame.setVisible(true);
        });
    }
}
