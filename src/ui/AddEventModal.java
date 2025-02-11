package ui;

import model.Deadline;
import model.Event;
import model.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * Dialog window to allow users to add a new event (Deadline or Meeting).
 */
public class AddEventModal extends JDialog {
    private JTextField nameField;
    private JComboBox<String> eventTypeDropdown;
    private JTextField dateTimeField;
    private JTextField locationField;
    private JTextField endTimeField;
    private EventListPanel eventListPanel;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Name field
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        // Event Type Dropdown (Meeting or Deadline)
        add(new JLabel("Event Type:"));
        eventTypeDropdown = new JComboBox<>(new String[]{"Meeting", "Deadline"});
        add(eventTypeDropdown);

        // DateTime field
        add(new JLabel("Date & Time (YYYY-MM-DD HH:MM):"));
        dateTimeField = new JTextField();
        add(dateTimeField);

        // Location (Only for Meetings)
        add(new JLabel("Location (Meetings Only):"));
        locationField = new JTextField();
        add(locationField);

        // End Time (Only for Meetings)
        add(new JLabel("End Time (Meetings Only - YYYY-MM-DD HH:MM):"));
        endTimeField = new JTextField();
        add(endTimeField);

        // Submit Button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> addEvent());
        add(addButton);
    }

    private void addEvent() {
        String name = nameField.getText();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText().replace(" ", "T"));
        String eventType = (String) eventTypeDropdown.getSelectedItem();

        if ("Meeting".equals(eventType)) {
            LocalDateTime endTime = LocalDateTime.parse(endTimeField.getText().replace(" ", "T"));
            String location = locationField.getText();
            eventListPanel.addEvent(new Meeting(name, dateTime, endTime, location));
        } else {
            eventListPanel.addEvent(new Deadline(name, dateTime));
        }

        dispose(); // Close modal after adding event
    }
}