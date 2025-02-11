package ui;

import model.Deadline;
import model.Event;
import model.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Dialog window to allow users to add a new event (Meeting or Deadline).
 */
public class AddEventModal extends JDialog {
    private JTextField nameField;
    private JComboBox<String> eventTypeDropdown;
    private JTextField dateTimeField;
    private JTextField locationField;
    private JTextField durationField;
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
        eventTypeDropdown.addActionListener(e -> toggleFields()); // Ensure fields enable correctly
        add(eventTypeDropdown);

        // DateTime field
        add(new JLabel("Date & Time (YYYY-MM-DD HH:MM):"));
        dateTimeField = new JTextField();
        add(dateTimeField);

        // Location (Only for Meetings)
        add(new JLabel("Location (Meetings Only):"));
        locationField = new JTextField();
        add(locationField);

        // Duration (Only for Meetings) - Changed from "End Time"
        add(new JLabel("Duration (Minutes) (Meetings Only):"));
        durationField = new JTextField();
        add(durationField);

        // Submit Button
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> addEvent());
        add(addButton);

        // Ensure the correct fields are enabled at startup
        toggleFields();
    }

    /**
     * Toggles input fields based on event type selection.
     */
    private void toggleFields() {
        boolean isMeeting = eventTypeDropdown.getSelectedItem().equals("Meeting");

        // Enable/Disable fields based on event type
        locationField.setEnabled(isMeeting);
        durationField.setEnabled(isMeeting);

        // Clear fields when switching event types to avoid incorrect input
        if (!isMeeting) {
            locationField.setText("");
            durationField.setText("");
        }
    }

    /**
     * Validates input and adds the event to the list.
     */
    private void addEvent() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Event name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText().replace(" ", "T"));

            String eventType = (String) eventTypeDropdown.getSelectedItem();

            if ("Meeting".equals(eventType)) {
                if (locationField.getText().trim().isEmpty() || durationField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter location and duration for the meeting!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String location = locationField.getText();
                int durationMinutes = Integer.parseInt(durationField.getText().trim());

                if (durationMinutes <= 0) {
                    JOptionPane.showMessageDialog(this, "Duration must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDateTime endTime = dateTime.plus(Duration.ofMinutes(durationMinutes));
                eventListPanel.addEvent(new Meeting(name, dateTime, endTime, location));

            } else {
                eventListPanel.addEvent(new Deadline(name, dateTime));
            }

            dispose(); // Close modal after adding event
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please use YYYY-MM-DD HH:MM", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duration must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
