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
 * Panel that holds and displays a list of events, with sorting and filtering options.
 */
public class EventListPanel extends JPanel {
    private final ArrayList<Event> events;
    private final JPanel displayPanel;
    private final JComboBox<String> sortDropDown;
    private final JCheckBox filterCompleted, filterMeetings, filterDeadlines;

    public EventListPanel() {
        this.events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Sorting Dropdown
        String[] sortOptions = {"Sort by Name", "Sort by Name (Reverse)", "Sort by Date & Time", "Sort by Date & Time (Reverse)"};
        sortDropDown = new JComboBox<>(sortOptions);
        sortDropDown.addActionListener(_ -> sortEvents());
        add(sortDropDown, BorderLayout.NORTH);

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        // Filter Checkboxes
        filterCompleted = new JCheckBox("Hide Completed");
        filterMeetings = new JCheckBox("Show Only Meetings");
        filterDeadlines = new JCheckBox("Show Only Deadlines");

        filterCompleted.addActionListener(_ -> refreshEventDisplay());
        filterMeetings.addActionListener(_ -> refreshEventDisplay());
        filterDeadlines.addActionListener(_ -> refreshEventDisplay());

        JPanel filterPanel = new JPanel();
        filterPanel.add(filterCompleted);
        filterPanel.add(filterMeetings);
        filterPanel.add(filterDeadlines);
        add(filterPanel, BorderLayout.WEST);

        // ✅ Fix: Add Event Button at the bottom
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(_ -> new AddEventModal(this).setVisible(true));
        add(addEventButton, BorderLayout.SOUTH);
    }

    /**
     * ✅ Fix: Adds default events if none exist.
     */
    public static void addDefaultEvents(EventListPanel eventListPanel) {
        if (eventListPanel.events.isEmpty()) { // Only add if the list is empty
            eventListPanel.addEvent(new Deadline("Submit Report", LocalDateTime.now().plusDays(1)));
            eventListPanel.addEvent(new Meeting("Team Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room 101"));
        }
    }

    /**
     * Adds an event to the list and updates the display.
     */
    public void addEvent(Event event) {
        events.add(event);
        refreshEventDisplay();
    }

    /**
     * Refreshes the event list based on sorting and filtering conditions.
     */
    private void refreshEventDisplay() {
        displayPanel.removeAll();

        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : events) {
            boolean addEvent = !filterCompleted.isSelected() || !(event instanceof Completable) || !((Completable) event).isComplete();
            addEvent &= !filterMeetings.isSelected() || event instanceof Meeting;
            addEvent &= !filterDeadlines.isSelected() || event instanceof Deadline;

            if (addEvent) {
                filteredEvents.add(event);
            }
        }

        for (Event event : filteredEvents) {
            displayPanel.add(new EventPanel(event));
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }

    /**
     * Sorts events based on user selection.
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
        refreshEventDisplay();
    }
}
