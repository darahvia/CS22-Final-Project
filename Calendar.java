import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.TreeMap;

public class Calendar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimeblockManager timeblockManager = new TimeblockManager();
        TreeMap<Integer, Entry> allEntries = new TreeMap<>();
        Queue<UnscheduledEntry> unscheduledEntriesQueue = new LinkedList<>();

        // scheduled task
        System.out.println("What are your scheduled agenda today? ");
        while (true) {
            System.out.print("Enter Scheduled Task Name: (exit to finish)");
            String taskName = scanner.nextLine();

            if (taskName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter start time (HH:mm): ");
            String startTimeStr = scanner.nextLine();
            System.out.print("Enter end time (HH:mm): ");
            String endTimeStr = scanner.nextLine();

            try {
                LocalTime startTime = LocalTime.parse(startTimeStr);
                LocalTime endTime = LocalTime.parse(endTimeStr);

                timeblockManager.addTimeBlock(startTime, endTime, taskName);
                TreeMap<Integer, String> timeslotsToUpdate = new TreeMap<>();
                int startMinutes = startTime.getHour() * 4 + startTime.getMinute();
                int endMinutes = endTime.getHour() * 4 + endTime.getMinute();

                for (int i = startMinutes; i <= endMinutes; i++) {
                    timeslotsToUpdate.put(i, taskName);
                }

                timeblockManager.updateTimeslots(timeslotsToUpdate);


                allEntries.put(startMinutes, new ScheduledEntry(taskName, startTime, endTime));
                allEntries.put(endMinutes, null);
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }

        System.out.println("Add Unscheduled Entries: ");

        while (true) {
            System.out.print("Enter Task Name: (exit to finish)");
            String taskName = scanner.nextLine();

            if (taskName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter due time (HH:mm): ");
            String dueTime = scanner.nextLine();
            System.out.print("Enter units: ");
            int units = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter units per timeslot: ");
            int unitsPerTimeslot = scanner.nextInt();
            scanner.nextLine();

            try {
                LocalTime duetime = LocalTime.parse(dueTime);
                unscheduledEntriesQueue.add(new UnscheduledEntry(taskName, dueTime, units, unitsPerTimeslot));
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }

        UnscheduledEntryStrategy unscheduledEntryStrategy = new UnscheduledEntryStrategy();
        unscheduledEntryStrategy.scheduleUnscheduledEntries(unscheduledEntriesQueue, timeblockManager, allEntries);


        displayAllEntries(allEntries);

        // timeblockManager.displayOccupiedTimeslots();
    }

    private static void displayAllEntries(TreeMap<Integer, Entry> allEntries) {
        System.out.println("\nAll Entries:");
    
        for (int i = 0; i < TimeblockManager.MAX_TIME_SLOTS; i++) {
            int hour = i / 4;
            int minute = (i % 4) * 15;
    
            String timeBlock = String.format("%02d:%02d", hour, minute);
    
            Map.Entry<Integer, Entry> floorEntry = allEntries.floorEntry(i);
            String taskName = (floorEntry != null && floorEntry.getValue() != null) ? floorEntry.getValue().getName() : "N/A";
            String status = (floorEntry != null && floorEntry.getValue() != null) ? "Occupied" : "Available";
    
            System.out.println("Time Slot " + i + " (" + timeBlock + "): " +
                    status + " - Task: " + taskName);
        }
    }
    
// this is a test comment... ignore lang 
    


    
}
