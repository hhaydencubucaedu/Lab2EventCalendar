package ui;

import model.Completable;
import model.Event;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays an individual event.
 */
public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(event.getName());
        add(nameLabel, BorderLayout.CENTER);

        // If the event is completable, add a button to mark it as complete.
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeButton.setEnabled(false); // Disable button after completion.
            });
            add(completeButton, BorderLayout.EAST);
        }
    }
}
