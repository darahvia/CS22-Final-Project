import java.util.*;
import java.time.LocalTime;


public class TimeblockManager {
    private TreeMap<LocalTime, String> allEntries;

    public TimeblockManager(){
        allEntries = new TreeMap<>();
    }
    final static int MAX_TIME_SLOTS = 24;
    boolean[] occupiedTimeslots = new boolean[MAX_TIME_SLOTS]
}
