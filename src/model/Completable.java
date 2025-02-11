package model;

/**
 * Interface for events that can be marked as complete.
 */
public interface Completable {
    void complete();
    boolean isComplete();
}
