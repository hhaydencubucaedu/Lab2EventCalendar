package model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Class representing a meeting event.
 * Meetings have a start time, an end time, and a location.
 * Implements Completable so we can mark them as done.
 */
public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime; // When the meeting ends.
    private String location; // Where the meeting is happening.
    private boolean complete; // Whether the meeting is done or not.

    /**
     * Constructor for a Meeting.
     * @param name The name of the meeting.
     * @param start The start time of the meeting.
     * @param end The end time of the meeting.
     * @param location The location of the meeting.
     */
    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start); // Calls the Event constructor to set the name and start time.
        this.endDateTime = end; // Sets the meeting end time.
        this.location = location; // Assigns the location.
        this.complete = false; // Meetings start off as incomplete.
    }

    /**
     * Gets the name of the meeting.
     * @return The name of the meeting.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the end time of the meeting.
     * @return The LocalDateTime when the meeting ends.
     */
    public LocalDateTime getEndTime() {
        return endDateTime;
    }

    /**
     * Calculates how long the meeting lasts.
     * @return A Duration object representing the length of the meeting.
     */
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime); // Subtracts start time from end time.
    }

    /**
     * Gets the location of the meeting.
     * @return The location of the meeting.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Updates the end time of the meeting.
     * @param end The new end time.
     */
    public void setEndTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    /**
     * Updates the location of the meeting.
     * @param location The new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Marks the meeting as complete.
     * Now you can check it off your to-do list!
     */
    @Override
    public void complete() {
        this.complete = true;
    }

    /**
     * Checks if the meeting is complete.
     * @return true if complete, false if not.
     */
    @Override
    public boolean isComplete() {
        return complete; // If it's done, return true. Otherwise, return false.
    }
}
