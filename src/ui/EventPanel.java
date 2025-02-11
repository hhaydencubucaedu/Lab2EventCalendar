package ui;

import model.Completable;
import model.Event;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays an individual event with a checkbox for completion.
 */
public class EventPanel extends JPanel {
    private final Event event;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(event.getName());
        add(nameLabel, BorderLayout.CENTER);

        if (event instanceof Completable) {
            JCheckBox completeCheckBox = new JCheckBox("Completed");
            completeCheckBox.setSelected(((Completable) event).isComplete());

            completeCheckBox.addActionListener(_ -> {
                ((Completable) event).complete();
                completeCheckBox.setEnabled(false);
            });

            add(completeCheckBox, BorderLayout.EAST);
        }
    }
}
