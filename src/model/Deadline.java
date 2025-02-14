package model;

import java.time.LocalDateTime;

/**
 * This class represents a deadline event.
 * It extends Event (because deadlines are events) and
 * implements Completable (so we can mark them as done).
 */
public class Deadline extends Event implements Completable {
    private boolean complete; // This keeps track of whether the deadline is done or not.

    /**
     * Constructor for a Deadline.
     * @param name The name of the deadline.
     * @param deadline The date and time the deadline is due.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline); // Calls the constructor of the Event class.
        this.complete = false; // Deadlines start off as incomplete (duh).
    }

    /**
     * Gets the name of the deadline.
     * @return The name of the deadline.
     */
    @Override
    public String getName() {
        return name; // Just returning the name, nothing fancy here.
    }

    /**
     * Marks this deadline as complete.
     * Basically flips the 'complete' switch to true.
     */
    @Override
    public void complete() {
        this.complete = true; // Boom. Now it's done.
    }

    /**
     * Checks if the deadline is complete.
     * @return true if complete, false if not.
     */
    @Override
    public boolean isComplete() {
        return complete; // If it's done, it's true. If not, it's false. Simple.
    }
}
