import java.util.*;

public class TaskScheduler {
    private List<Entry> scheduledEntries;
    private PriorityQueue<UnscheduledEntry> unscheduledEntries;

    public TaskScheduler() {
        scheduledEntries = new LinkedList<>();
        unscheduledEntries = new PriorityQueue<>();
    }

    public void addScheduledEntry(String name, String day, String time) {
        ScheduledEntry scheduledEntry = new ScheduledEntry(name, day, time);
        scheduledEntries.add(scheduledEntry);
    }

    public void addUnscheduledEntry(String name, String dueDate, int units, int unitsPerDay, int priority) {
        UnscheduledEntry unscheduledEntry = new UnscheduledEntry(name, dueDate, units, unitsPerDay, priority);
        unscheduledEntries.add(unscheduledEntry);
    }

    public void generateCalendar() {
        distributeUnscheduledEntries();
        
        // Print the scheduled entries
        System.out.println("Scheduled Entries:");
        for (Entry entry : scheduledEntries) {
            System.out.println(entry);
        }
        
        // Print the distributed unscheduled entries
        System.out.println("Distributed Unscheduled Entries:");
        while (!unscheduledEntries.isEmpty()) {
            System.out.println(unscheduledEntries.poll());
        }
    }

    private void distributeUnscheduledEntries() {
        // Implement the strategy pattern for distributing unscheduled entries
        // You may use a different strategy based on the unitsPerDay and priority
        // Here, I'm assuming a simple strategy of distributing from the start.
        for (UnscheduledEntry unscheduledEntry : unscheduledEntries) {
            scheduledEntries.add(new ScheduledEntry(
                    unscheduledEntry.getName(),
                    unscheduledEntry.getDueDate(),
                    "Start"
            ));
        }
    }

    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();

        // Add scheduled entries
        taskScheduler.addScheduledEntry("Meeting", "2023-01-01", "10:00 AM");

        // Add unscheduled entries
        taskScheduler.addUnscheduledEntry("Study", "2023-01-05", 10, 2, 1);

        // Generate and print the calendar
        taskScheduler.generateCalendar();
    }
}
