package test;

import model.*;

import java.time.LocalDateTime;

/**
 * Class to test the functionality of the Event system.
 */
public class EventTester {
    public static void main(String[] args) {
        testEvents();
    }

    public static void testEvents() {
        // Create some sample dates
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plusDays(2);
        LocalDateTime meetingEnd = now.plusHours(2);

        // Create a Deadline event
        Deadline deadline = new Deadline("Project Submission", later);

        // Create a Meeting event
        Meeting meeting = new Meeting("Team Sync", now, meetingEnd, "Conference Room A");

        // Test Deadline
        System.out.println("Testing Deadline:");
        System.out.println("Name: " + deadline.getName());
        System.out.println("Complete? " + deadline.isComplete());
        deadline.complete();
        System.out.println("Complete after update? " + deadline.isComplete());

        // Test Meeting
        System.out.println("\nTesting Meeting:");
        System.out.println("Name: " + meeting.getName());
        System.out.println("Location: " + meeting.getLocation());
        System.out.println("Duration: " + meeting.getDuration().toMinutes() + " minutes");
        System.out.println("Complete? " + meeting.isComplete());
        meeting.complete();
        System.out.println("Complete after update? " + meeting.isComplete());
    }
}