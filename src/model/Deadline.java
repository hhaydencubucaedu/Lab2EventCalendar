package model;

import java.time.LocalDateTime;

/**
 * Class representing a deadline event.
 * Implements Completable so it can be marked as complete.
 */
public class Deadline extends Event implements Completable {
    private boolean complete;

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
        this.complete = false; // Initially, the deadline is not complete.
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void complete() {
        this.complete = true; // Marks the deadline as complete.
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
