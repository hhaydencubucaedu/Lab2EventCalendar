package ui;

import model.Deadline;
import model.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * Dialog that allows the user to add new events.
 */
public class AddEventModal extends JDialog {
    public AddEventModal(EventListPanel eventListPanel) {
        setTitle("Add Event");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        JButton addMeeting = new JButton("Add Meeting");
        JButton addDeadline = new JButton("Add Deadline");

        // Adds a new meeting event when the button is clicked.
        addMeeting.addActionListener(e -> eventListPanel.addEvent(new Meeting("New Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "New Location")));

        // Adds a new deadline event when the button is clicked.
        addDeadline.addActionListener(e -> eventListPanel.addEvent(new Deadline("New Deadline", LocalDateTime.now().plusDays(3))));

        add(addMeeting);
        add(addDeadline);
    }
}
