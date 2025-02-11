package model;

import java.time.LocalDateTime;

/**
 * Abstract class representing an event.
 * Implements Comparable to allow sorting events by dateTime.
 */
public abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;

    /**
     * Constructor to initialize the event with a name and date/time.
     * @param name The name of the event.
     * @param dateTime The start date and time of the event.
     */
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    /**
     * Abstract method to get the name of the event.
     */
    public abstract String getName();

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this event with another event based on dateTime.
     * @param e The event to compare with.
     * @return Positive if this event comes later, negative if earlier, and 0 if same.
     */
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
