import java.time.LocalTime;
import java.util.*;

public class UnscheduledEntryStrategy {

    public static void scheduleUnscheduledEntries(Queue<UnscheduledEntry> unscheduledEntriesQueue,
                                                  TimeblockManager timeblockManager,
                                                  TreeMap<Integer, Entry> allEntries) {


        int currentEntryIndex = 0; // Keep track of the index of the entry to be scheduled next

        // while (!unscheduledEntriesQueue.isEmpty()) {
        //     UnscheduledEntry unscheduledEntry = unscheduledEntriesQueue.poll();
        //     List<Integer> distribution = unscheduledEntry.distributeUnits();
        //     // System.out.println("(2, 2, 1)): " + distribution);

        //     for (int units : distribution) {
        //         List<Integer> availableSlots = timeblockManager.getAvailableSlots();
        //         if (availableSlots.isEmpty()) {
        //             // System.out.println("Not enough available slots for scheduling remaining units.");
        //             break;
        //         }

        //         int timeslot = availableSlots.get(0);
        //         // System.out.println("before" + timeslot);
        //         LocalTime startTime = calculateTime(timeslot);
        //         LocalTime endTime = calculateTime(timeslot + units * 4);
        //         // System.out.println(startTime);
        //         // System.out.println(endTime);

        //         TreeMap<Integer, String> timeslotsToUpdate = new TreeMap<>();
        //         for (int i = timeslot; i < timeslot + units * 4; i++) {
        //             timeslotsToUpdate.put(i, unscheduledEntry.getName());
        //         }
        //         timeblockManager.addTimeBlock(startTime, endTime, unscheduledEntry.getName());
        //         timeblockManager.updateTimeslots(timeslotsToUpdate);
        //         // System.out.println("available slots" + availableSlots);

        //         // System.out.println("after" + timeslot);

        //         int startMinutes = startTime.getHour() * 4 + startTime.getMinute();
        //         int endMinutes = endTime.getHour() * 4 + endTime.getMinute();

        //         allEntries.put(startMinutes, new ScheduledEntry(unscheduledEntry.getName(), startTime, endTime));
        //         allEntries.put(endMinutes, null);
        //     }

        //     // Alternate the index for the next entry

        // if (!unscheduledEntriesQueue.isEmpty()) {
        //     currentEntryIndex = (currentEntryIndex + 1) % unscheduledEntriesQueue.size();
        //     break;
        // }

        // }

        while (!unscheduledEntriesQueue.isEmpty()) {
            UnscheduledEntry unscheduledEntry = unscheduledEntriesQueue.poll();
            List<Integer> distribution = unscheduledEntry.distributeUnits();
        
            int maxUnits = distribution.stream().mapToInt(Integer::intValue).max().orElse(0);
        
            // Loop through each unit
            for (int unitIndex = 0; unitIndex < maxUnits; unitIndex++) {
                // Loop through each task
                for (int taskIndex = 0; taskIndex < distribution.size(); taskIndex++) {
                    int units = distribution.get(taskIndex);
        
                    List<Integer> availableSlots = timeblockManager.getAvailableSlots();
                    if (availableSlots.isEmpty()) {
                        // System.out.println("Not enough available slots for scheduling remaining units.");
                        break;
                    }
        
                    int timeslot = availableSlots.get(0);
                    LocalTime startTime = calculateTime(timeslot);
                    LocalTime endTime = calculateTime(timeslot + units * 4);
        
                    TreeMap<Integer, String> timeslotsToUpdate = new TreeMap<>();
                    for (int i = timeslot; i < timeslot + units * 4; i++) {
                        timeslotsToUpdate.put(i, unscheduledEntry.getName());
                    }
                    timeblockManager.addTimeBlock(startTime, endTime, unscheduledEntry.getName());
                    timeblockManager.updateTimeslots(timeslotsToUpdate);
        
                    int startMinutes = startTime.getHour() * 4 + startTime.getMinute();
                    int endMinutes = endTime.getHour() * 4 + endTime.getMinute();
        
                    allEntries.put(startMinutes, new ScheduledEntry(unscheduledEntry.getName(), startTime, endTime));
                    allEntries.put(endMinutes, null);
                }
            }
        }
        
        // Alternate the index for the next entry
        if (!unscheduledEntriesQueue.isEmpty()) {
            currentEntryIndex = (currentEntryIndex + 1) % unscheduledEntriesQueue.size();
        }
        
        
        
    }

    private static LocalTime calculateTime(int timeslot) {
        int hour = timeslot / 4;
        int minute = (timeslot % 4) * 15;
        return LocalTime.of(hour, minute);
    }
}
