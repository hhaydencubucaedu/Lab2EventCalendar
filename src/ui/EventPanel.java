package ui;

import model.Completable;
import model.Event;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays an individual event with a checkbox for completion.
 */
public class EventPanel extends JPanel {
    private Event event;
    private JCheckBox completeCheckBox; // Changed from JButton to JCheckBox

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(event.getName());
        add(nameLabel, BorderLayout.CENTER);

        // If the event is completable, add a checkbox to mark it as complete
        if (event instanceof Completable) {
            completeCheckBox = new JCheckBox("Completed");
            completeCheckBox.setSelected(((Completable) event).isComplete());

            completeCheckBox.addActionListener(e -> {
                ((Completable) event).complete();
                completeCheckBox.setEnabled(false); // Disable checkbox after completion
            });

            add(completeCheckBox, BorderLayout.EAST);
        }
    }
}
