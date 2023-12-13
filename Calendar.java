import java.util.Scanner;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Calendar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimeblockManager timeblockManager = new TimeblockManager();
        Queue<Entry> allEntries = new LinkedList<>();
        PriorityQueue<UnscheduledEntry> unscheduledEntriesQueue = new PriorityQueue<>(UnscheduledEntry::compareTo);
    
        // scheduled task
        System.out.println("What are your scheduled agenda today? ");
        while (true){
            System.out.print("Enter Unscheduled Task Name: (exit to finish)");
            String taskName = scanner.nextLine();

            if (taskName.equalsIgnoreCase("exit")){
                break;
            }

            System.out.print("Enter start time (HH:mm): ");
            String startTimeStr = scanner.nextLine();
            System.out.print("Enter end time (HH:mm): ");
            String endTimeStr = scanner.nextLine();

            try{
                LocalTime startTime = LocalTime.parse(startTimeStr);
                LocalTime endTime = LocalTime.parse(endTimeStr);

                timeblockManager.addTimeBlock(startTime, endTime, taskName);
                timeblockManager.updateTimeslots();

                allEntries.add(new ScheduledEntry(taskName, startTime, endTime));
            } catch (DateTimeParseException e){
                System.out.println(e);
            }
        }

        while (true){
            System.out.print("Enter Unscheduled Task Name: (exit to finish)");
            String taskName = scanner.nextLine();

            if (taskName.equalsIgnoreCase("exit")){
                break;
            }

            System.out.print("Enter start time (HH:mm): ");
            String startTimeStr = scanner.nextLine();
            System.out.print("Enter end time (HH:mm): ");
            String endTimeStr = scanner.nextLine();

            try{
                LocalTime startTime = LocalTime.parse(startTimeStr);
                LocalTime endTime = LocalTime.parse(endTimeStr);

                timeblockManager.addTimeBlock(startTime, endTime, taskName);
                timeblockManager.updateTimeslots();

                allEntries.add(new ScheduledEntry(taskName, startTime, endTime));
            } catch (DateTimeParseException e){
                System.out.println(e);
            }

            System.out.println("Add Unscheduled Entries: ");
            while (true) {
                System.out.print("Enter Task Name: (exit to finish)");
                String taskName = scanner.nextLine();

                if (taskName.equalsIgnoreCase("exit")){
                    break;
                }

                System.out.print("Enter due time (HH:mm): ");
                String dueTimeStr = scanner.nextLine();
                System.out.print("Enter units: ");
                int units = scanner.nextInt();  
                System.out.print("Enter units per timeslot: ");
                int unitsPerTimeslot = scanner.nextInt();
                System.out.print("Enter priority: ");
                int priority = scanner.nextInt();
                System.out.print("Enter timeslot: ");
                int timeslot = scanner.nextInt();           

            }
        }
    }
}
