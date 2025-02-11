package ui;

import model.Deadline;
import model.Event;
import model.Meeting;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Panel that holds and displays a list of events.
 */
public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;

    public EventListPanel() {
        this.events = new ArrayList<>();
        setLayout(new BorderLayout());

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> new AddEventModal(this).setVisible(true));
        add(addEventButton, BorderLayout.SOUTH);
    }

    public void addEvent(Event event) {
        events.add(event);
        displayPanel.add(new EventPanel(event));
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    /**
     * Adds some default events for testing.
     */
    public static void addDefaultEvents(EventListPanel eventListPanel) {
        eventListPanel.addEvent(new Deadline("Submit Report", LocalDateTime.now().plusDays(1)));
        eventListPanel.addEvent(new Meeting("Team Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Room 101"));
    }
}
