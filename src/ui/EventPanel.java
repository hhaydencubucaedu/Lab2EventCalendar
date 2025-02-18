package ui;

import model.Completable;
import model.Event;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays an individual event.
 * If the event is Completable (like a Deadline or Meeting), it shows a checkbox to mark it as complete.
 */
public class EventPanel extends JPanel {

    /**
     * Constructor for the EventPanel.
     * Displays the event name and, if applicable, a checkbox to mark it as complete.
     * @param event The event to display.
     */
    public EventPanel(Event event) {
        setLayout(new BorderLayout()); // Using BorderLayout to organize components.

        // Display the event name in the center of the panel.
        JLabel nameLabel = new JLabel(event.getName());
        add(nameLabel, BorderLayout.CENTER);

        // If the event is something that can be completed (like a Meeting or Deadline), add a checkbox.
        if (event instanceof Completable) {
            JCheckBox completeCheckBox = new JCheckBox("Completed"); // Checkbox to mark event as done.
            completeCheckBox.setSelected(((Completable) event).isComplete()); // Pre-check if it's already complete.

            // When the checkbox is clicked, mark the event as complete and disable the checkbox.
            completeCheckBox.addActionListener(_ -> {
                ((Completable) event).complete(); // Marks the event as complete.
                completeCheckBox.setEnabled(false); // Disables the checkbox so it can't be unchecked.
            });

            add(completeCheckBox, BorderLayout.EAST); // Checkbox appears on the right side.
        }
    }
}
