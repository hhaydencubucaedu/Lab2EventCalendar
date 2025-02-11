package model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Class representing a meeting event.
 * Implements Completable so it can be marked as complete.
 */
public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDateTime getEndTime() {
        return endDateTime;
    }

    /**
     * Returns the duration of the meeting.
     * @return Duration object representing meeting length.
     */
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);
    }

    public String getLocation() {
        return location;
    }

    public void setEndTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void complete() {
        this.complete = true; // Marks the meeting as complete.
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
