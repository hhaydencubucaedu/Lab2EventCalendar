package ui;

import model.Completable;
import model.Deadline;
import model.Event;
import model.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Panel that holds and displays a list of events.
 * Users can sort and filter events here. Basically, it's where all events show up.
 */
public class EventListPanel extends JPanel {
    private final ArrayList<Event> events; // Stores all the events.
    private final JPanel displayPanel; // Where events are displayed.
    private final JComboBox<String> sortDropDown; // Dropdown for sorting events.
    private final JCheckBox filterCompleted, filterMeetings, filterDeadlines; // Checkboxes for filtering.

    /**
     * Constructor that sets up the event list panel.
     * Includes sorting, filtering, and an "Add Event" button.
     */
    public EventListPanel() {
        this.events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Sorting Dropdown
        String[] sortOptions = {
                "Sort by Name",
                "Sort by Name (Reverse)",
                "Sort by Date & Time",
                "Sort by Date & Time (Reverse)"
        };
        sortDropDown = new JComboBox<>(sortOptions);
        sortDropDown.addActionListener(_ -> sortEvents()); // Sorts events when an option is selected.
        add(sortDropDown, BorderLayout.NORTH);

        // Display panel where event panels will be added.
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        // Filter Checkboxes
        filterCompleted = new JCheckBox("Hide Completed");
        filterMeetings = new JCheckBox("Show Only Meetings");
        filterDeadlines = new JCheckBox("Show Only Deadlines");

        // Adding action listeners so events refresh when filters are changed.
        filterCompleted.addActionListener(_ -> refreshEventDisplay());
        filterMeetings.addActionListener(_ -> refreshEventDisplay());
        filterDeadlines.addActionListener(_ -> refreshEventDisplay());

        // Panel to hold filters
        JPanel filterPanel = new JPanel();
        filterPanel.add(filterCompleted);
        filterPanel.add(filterMeetings);
        filterPanel.add(filterDeadlines);
        add(filterPanel, BorderLayout.WEST);

        // ✅ Fix: Add Event Button at the bottom
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(_ -> new AddEventModal(this).setVisible(true)); // Opens the AddEventModal.
        add(addEventButton, BorderLayout.SOUTH);
    }

    /**
     * ✅ Fix: Adds default events if the list is empty.
     * This makes sure there's something on the screen when the app starts.
     */
    public static void addDefaultEvents(EventListPanel eventListPanel) {
        if (eventListPanel.events.isEmpty()) { // Only add if the list is empty
            eventListPanel.addEvent(new Deadline("Submit Report", LocalDateTime.now().plusDays(1)));
            eventListPanel.addEvent(new Meeting("Team Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room 101"));
        }
    }

    /**
     * Adds an event to the list and refreshes the display.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
        refreshEventDisplay();
    }

    /**
     * Refreshes the display by applying sorting and filtering conditions.
     * Only shows events that meet the filter criteria.
     */
    private void refreshEventDisplay() {
        displayPanel.removeAll(); // Clears the display.

        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : events) {
            boolean addEvent = true;

            // If "Hide Completed" is checked, remove completed events.
            if (filterCompleted.isSelected() && event instanceof Completable && ((Completable) event).isComplete()) {
                addEvent = false;
            }

            // If "Show Only Meetings" is checked, only show Meeting events.
            if (filterMeetings.isSelected() && !(event instanceof Meeting)) {
                addEvent = false;
            }

            // If "Show Only Deadlines" is checked, only show Deadline events.
            if (filterDeadlines.isSelected() && !(event instanceof Deadline)) {
                addEvent = false;
            }

            if (addEvent) {
                filteredEvents.add(event); // Add event to the filtered list.
            }
        }

        // Add each filtered event to the display.
        for (Event event : filteredEvents) {
            displayPanel.add(new EventPanel(event));
        }

        displayPanel.revalidate(); // Refreshes UI.
        displayPanel.repaint();
    }

    /**
     * Sorts events based on user selection.
     * Sort options: Name, Reverse Name, Date & Time, Reverse Date & Time.
     */
    private void sortEvents() {
        String selected = (String) sortDropDown.getSelectedItem();

        if ("Sort by Name".equals(selected)) {
            events.sort(Comparator.comparing(Event::getName));
        } else if ("Sort by Name (Reverse)".equals(selected)) {
            events.sort(Comparator.comparing(Event::getName).reversed());
        } else if ("Sort by Date & Time".equals(selected)) {
            events.sort(Comparator.comparing(Event::getDateTime));
        } else if ("Sort by Date & Time (Reverse)".equals(selected)) {
            events.sort(Comparator.comparing(Event::getDateTime).reversed());
        }

        refreshEventDisplay(); // Update the display after sorting.
    }
}
