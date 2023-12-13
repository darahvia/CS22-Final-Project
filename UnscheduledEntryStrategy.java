import java.time.LocalTime;
import java.util.*;

public class UnscheduledEntryStrategy {

    public static void scheduleUnscheduledEntries(Queue<UnscheduledEntry> unscheduledEntriesQueue,
                                                  TimeblockManager timeblockManager,
                                                  TreeMap<Integer, Entry> allEntries) {
        List<Integer> availableSlots = timeblockManager.getAvailableSlots();
        while (!unscheduledEntriesQueue.isEmpty()) {
        
            scheduleEntry(unscheduledEntriesQueue, availableSlots, timeblockManager, allEntries);
        }
    }



    private static void scheduleEntry(Queue<UnscheduledEntry> unscheduledEntriesQueue,
                                    List<Integer> availableSlots,
                                    TimeblockManager timeblockManager,
                                    TreeMap<Integer, Entry> allEntries) {
        while (!unscheduledEntriesQueue.isEmpty() && !availableSlots.isEmpty()) {
            UnscheduledEntry unscheduledEntry = unscheduledEntriesQueue.poll();
            List<Integer> distribution = unscheduledEntry.distributeUnits();

            for (int units : distribution) {
                if (availableSlots.isEmpty()) {
                    System.out.println("Not enough available slots for scheduling remaining units.");
                    break;
                }

                int timeslot = availableSlots.remove(0);
                LocalTime startTime = calculateStartTime(timeslot);
                LocalTime endTime = calculateEndTime(timeslot + units);

                timeblockManager.addTimeBlock(startTime, endTime, unscheduledEntry.getName());
                timeblockManager.updateTimeslots();

                int startMinutes = startTime.getHour() * 4 + startTime.getMinute();
                int endMinutes = endTime.getHour() * 4 + endTime.getMinute();

                allEntries.put(startMinutes, new ScheduledEntry(unscheduledEntry.getName(), startTime, endTime));
                allEntries.put(endMinutes, null);
            }
        }
    }

    private static LocalTime calculateStartTime(int timeslot) {
        int hour = timeslot / 4;
        int minute = (timeslot % 4) * 15;
        return LocalTime.of(hour, minute);
    }

    private static LocalTime calculateEndTime(int timeslot) {
        int hour = timeslot / 4;
        int minute = (timeslot % 4) * 15;
        return LocalTime.of(hour, minute);
    }


        /*
        example:

        laundry:
        dueTime: 19:00
        units: 3
        unitsPerTimeSlot: 1

        final project:
        dueTime: 21:00
        units: 6
        unitsPerTimeSlot: 2

        what happens next is:
        find available timeslot and fill with alternating unitspertimeslot laundry and final project
        
        01:00 - 02:00 laundry
        02:00 - 04:00 final project
        04:00 - 04:15 occupied
        04:15 - 05:15 laundry
         */
}
