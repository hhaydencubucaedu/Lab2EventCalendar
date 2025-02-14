package model;

import java.time.LocalDateTime;

/**
 * Abstract class representing an event.
 * Basically, this is the blueprint for all events.
 * It implements Comparable so events can be sorted by dateTime.
 */
public abstract class Event implements Comparable<Event> {
    protected String name; // The name of the event (duh).
    protected LocalDateTime dateTime; // When the event is happening.

    /**
     * Constructor to initialize an event with a name and date/time.
     * @param name The name of the event.
     * @param dateTime The date and time the event takes place.
     */
    public Event(String name, LocalDateTime dateTime) {
        this.name = name; // Assigns the name to the event.
        this.dateTime = dateTime; // Sets the event's date and time.
    }

    /**
     * Abstract method to get the event name.
     * Each event type (like Deadline or Meeting) will implement this.
     */
    public abstract String getName();

    /**
     * Returns the event’s date and time.
     * @return The LocalDateTime of the event.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Updates the date and time of the event.
     * @param dateTime The new date/time.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Updates the name of the event.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this event with another event based on dateTime.
     * @param e The event to compare with.
     * @return Positive if this event happens later,
     *         negative if earlier, and 0 if they happen at the same time.
     */
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime); // Just using Java's built-in compareTo for dates.
    }
}
