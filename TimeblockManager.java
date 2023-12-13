import java.util.*;
import java.time.LocalTime;


public class TimeblockManager {
    private TreeMap<LocalTime, String> allEntries;

    public TimeblockManager(){
        allEntries = new TreeMap<>();
    }

    final static int MAX_TIME_SLOTS = 24;
    boolean[] occupiedTimeslots = new boolean[MAX_TIME_SLOTS];

    public void addTimeBlock(LocalTime startTime, LocalTime endTime, String entryName){
        allEntries.put(startTime, entryName);
        allEntries.put(endTime, null);
    }

    public void updateTimeslots() {
        Arrays.fill(occupiedTimeslots, false);
        LocalTime prevEndTime = null;
        for (Map.Entry<LocalTime, String> entry : allEntries.entrySet()){
            LocalTime currentTime = entry.getKey();
            if (prevEndTime != null){
                int startSlot = prevEndTime.getHour();
                int endSlot = currentTime.getHour();

                for (int i = startSlot; i < endSlot; i++){
                    occupiedTimeslots[i] = true;
                }
            } 
            prevEndTime = allEntries.higherKey(currentTime);
        }
    }
}
