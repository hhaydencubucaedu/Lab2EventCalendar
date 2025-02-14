package test;

import model.*;

import java.time.LocalDateTime;

/**
 * Class to test the functionality of the Event system.
 * Basically, just runs some test cases to make sure everything works.
 */
public class EventTester {
    public static void main(String[] args) {
        testEvents(); // Runs the test method to check if deadlines and meetings behave correctly.
    }

    /**
     * Creates and tests Deadline and Meeting events.
     * Prints out results so we can see if everything is working as expected.
     */
    public static void testEvents() {
        // Create some sample dates for testing
        LocalDateTime now = LocalDateTime.now(); // Current time
        LocalDateTime later = now.plusDays(2); // 2 days from now
        LocalDateTime meetingEnd = now.plusHours(2); // 2 hours from now

        // Create a Deadline event (due in 2 days)
        Deadline deadline = new Deadline("Project Submission", later);

        // Create a Meeting event (happening now, ends in 2 hours)
        Meeting meeting = new Meeting("Team Sync", now, meetingEnd, "Conference Room A");

        // Testing Deadline
        System.out.println("Testing Deadline:");
        System.out.println("Name: " + deadline.getName()); // Should print "Project Submission"
        System.out.println("Complete? " + deadline.isComplete()); // Should be false
        deadline.complete(); // Mark it as complete
        System.out.println("Complete after update? " + deadline.isComplete()); // Should be true

        // Testing Meeting
        System.out.println("\nTesting Meeting:");
        System.out.println("Name: " + meeting.getName()); // Should print "Team Sync"
        System.out.println("Location: " + meeting.getLocation()); // Should print "Conference Room A"
        System.out.println("Duration: " + meeting.getDuration().toMinutes() + " minutes"); // Should print 120 minutes
        System.out.println("Complete? " + meeting.isComplete()); // Should be false
        meeting.complete(); // Mark it as complete
        System.out.println("Complete after update? " + meeting.isComplete()); // Should be true
    }
}
