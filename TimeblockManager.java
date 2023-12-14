import java.time.LocalTime;
import java.util.*;

 class TimeblockManager {
    private TreeMap<Integer, String> allEntries;

    public TimeblockManager() {
        allEntries = new TreeMap<>();
    }

    final static int MAX_TIME_SLOTS = 24 * 4; 
    boolean[] occupiedTimeslots = new boolean[MAX_TIME_SLOTS];;


    public void addTimeBlock(LocalTime startTime, LocalTime endTime, String entryName) {
        int startMinutes = startTime.getHour() * 4 + startTime.getMinute();
        int endMinutes = endTime.getHour() * 4 + endTime.getMinute();

        allEntries.put(startMinutes, entryName);
        for (int i = startMinutes + 1; i < endMinutes; i++) {
            allEntries.put(i, entryName); // Mark the time slots between start and end as occupied
        }
        allEntries.put(endMinutes, null);
    }

    public void updateTimeslots(Map<Integer, String> timeslotsToUpdate) {
        for (Map.Entry<Integer, String> entry : timeslotsToUpdate.entrySet()) {
            int timeslot = entry.getKey();
            System.out.println("timeslot" + timeslot);
            String entryName = entry.getValue();
            
            if (entryName != null) {
                // Mark the timeslot as occupied
                occupiedTimeslots[timeslot] = true;
            } else {
                // Mark the timeslot as unoccupied
                occupiedTimeslots[timeslot] = false;
            }
        }
    }

    public boolean isTimeslotOccupied(int timeslot) {
        return occupiedTimeslots[timeslot];
    }

    public List<Integer> getAvailableSlots() {
        List<Integer> availableSlots = new ArrayList<>();
        for (int i = 0; i < MAX_TIME_SLOTS; i++) {
            if (!isTimeslotOccupied(i)) {
                availableSlots.add(i);
            }
        }
        return availableSlots;
    }


    public void displayOccupiedTimeslots() {
        System.out.println("Occupied Time Slots:");
        System.out.println(Arrays.toString(occupiedTimeslots));
    }

    public TreeMap<Integer, String> getAllEntries() {
            return allEntries;
    }
}

// public class TimeblockManagers {
//     public static void main(String[] args) {
//         // Create an instance of TimeblockManager
//         TimeblockManager timeblockManager = new TimeblockManager();

//         // Add sample time blocks
//         timeblockManager.addTimeBlock(LocalTime.of(0, 0), LocalTime.of(9, 0), "sleep");
//         timeblockManager.addTimeBlock(LocalTime.of(9, 0), LocalTime.of(10, 0), "Meeting A");
//         timeblockManager.addTimeBlock(LocalTime.of(11, 30), LocalTime.of(13, 30), "Lunch");
//         timeblockManager.addTimeBlock(LocalTime.of(15, 0), LocalTime.of(16, 30), "Meeting B");

//         // Update occupied time slots
//         timeblockManager.updateTimeslots();
        
//         timeblockManager.displayOccupiedTimeslots();;
//         // Display the status of each time slot
//         for (int i = 0; i < TimeblockManager.MAX_TIME_SLOTS; i++) {
//             int hour = i / 4;
//             int minute = (i % 4) * 15;
            
//             boolean isOccupied = timeblockManager.isTimeslotOccupied(i);
//             String taskName = isOccupied ? timeblockManager.getAllEntries().get(i) : "N/A";
        
//             System.out.println("Time Slot " + i + " (" + hour + ":" + minute + "): " +
//                     (isOccupied ? "Occupied - Task: " + taskName : "Available"));
//         }
        
//     }
// }
