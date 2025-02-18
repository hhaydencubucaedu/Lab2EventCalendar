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
 * Dialog window that lets users add a new event (either a Meeting or a Deadline).
 * Basically, this pops up when someone wants to add an event to the calendar.
 */
public class AddEventModal extends JDialog {
    private final JTextField nameField; // Where the user types the event name.
    private final JComboBox<String> eventTypeDropdown; // Dropdown for choosing Meeting or Deadline.
    private final JTextField dateTimeField; // Where the user enters the date/time.
    private final JTextField locationField; // Only used for Meetings.
    private final JTextField durationField; // Only used for Meetings (in minutes).
    private final EventListPanel eventListPanel; // Reference to the event list.

    /**
     * Constructor for the AddEventModal window.
     * @param eventListPanel The panel where events are stored.
     */
    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event"); // Window title.
        setSize(400, 300); // Sets the size of the pop-up.
        setLayout(new GridLayout(6, 2)); // Makes a simple 6-row, 2-column grid layout.

        // Event Name field
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        // Event Type Dropdown (Meeting or Deadline)
        add(new JLabel("Event Type:"));
        eventTypeDropdown = new JComboBox<>(new String[]{"Meeting", "Deadline"});
        eventTypeDropdown.addActionListener(_ -> toggleFields()); // Changes input fields based on selection.
        add(eventTypeDropdown);

        // Date & Time field (User must enter in format YYYY-MM-DD HH:MM)
        add(new JLabel("Date & Time (YYYY-MM-DD HH:MM):"));
        dateTimeField = new JTextField();
        add(dateTimeField);

        // Location field (Only for Meetings)
        add(new JLabel("Location (Meetings Only):"));
        locationField = new JTextField();
        add(locationField);

        // Duration field (Only for Meetings)
        add(new JLabel("Duration (Minutes) (Meetings Only):"));
        durationField = new JTextField();
        add(durationField);

        // Submit Button (adds the event when clicked)
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(_ -> addEvent()); // Calls addEvent() when clicked.
        add(addButton);

        toggleFields(); // Hides fields that don’t apply to the selected event type.
    }

    /**
     * Enables/disables input fields based on whether the user selects Meeting or Deadline.
     * If it's a Meeting, location and duration fields show up.
     * If it's a Deadline, those fields disappear.
     */
    private void toggleFields() {
        boolean isMeeting = "Meeting".equals(eventTypeDropdown.getSelectedItem());

        locationField.setEnabled(isMeeting);
        durationField.setEnabled(isMeeting);
        locationField.setVisible(isMeeting);
        durationField.setVisible(isMeeting);
    }

    /**
     * Validates user input and adds the event to the event list.
     * Shows error messages if something is entered incorrectly.
     */
    private void addEvent() {
        try {
            // Get the event name and make sure it's not empty.
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Event name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convert the date/time input into a LocalDateTime object.
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText().replace(" ", "T"));

            // Figure out if the user is adding a Meeting or a Deadline.
            String eventType = (String) eventTypeDropdown.getSelectedItem();

            if ("Meeting".equals(eventType)) {
                // Make sure location and duration aren't empty.
                if (locationField.getText().trim().isEmpty() || durationField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter location and duration for the meeting!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String location = locationField.getText();
                int durationMinutes = Integer.parseInt(durationField.getText().trim());

                // Make sure duration is a positive number.
                if (durationMinutes <= 0) {
                    JOptionPane.showMessageDialog(this, "Duration must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculate when the meeting will end based on duration.
                LocalDateTime endTime = dateTime.plus(Duration.ofMinutes(durationMinutes));

                // Add the new Meeting to the event list.
                eventListPanel.addEvent(new Meeting(name, dateTime, endTime, location));

            } else {
                // If it's not a Meeting, it's a Deadline. Just add it to the list.
                eventListPanel.addEvent(new Deadline(name, dateTime));
            }

            dispose(); // Close the modal after adding the event.

        } catch (DateTimeParseException e) {
            // If the user entered the date/time incorrectly, show an error.
            JOptionPane.showMessageDialog(this, "Invalid date format! Please use YYYY-MM-DD HH:MM", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            // If the duration isn't a valid number, show an error.
            JOptionPane.showMessageDialog(this, "Duration must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
