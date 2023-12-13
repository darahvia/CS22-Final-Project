import java.util.Queue;
import java.time.LocalTime;

public class UnscheduledEntryStrategy {
    public static void scheduleUnscheduledEntries(Queue<UnscheduledEntry> unscheduledEntriesQueue, TimeblockManager timeblockManager){
        while (!unscheduledEntriesQueue.isEmpty()){
            UnscheduledEntry unscheduledEntry = unscheduledEntriesQueue.poll();
            LocalTime availableTime = timeblockManager.findAvailableTimeslot(unscheduledEntry.getUnits(), unscheduledEntry.getUnitsPerTimeslot());
        }
    }
}
