import java.util.ArrayList;
import java.util.List;

class UnscheduledEntry implements Entry {
    protected String name;
    protected String dueTime;
    protected int units;
    protected int unitsPerTimeslot;
    protected int unitsRemaining;

    public UnscheduledEntry(String name, String dueTime, int units, int unitsPerTimeslot) {
        this.name = name;
        this.dueTime = dueTime;
        this.units = units;
        this.unitsPerTimeslot = unitsPerTimeslot;
        this.unitsRemaining = units;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "UnscheduledEntry{" +
                "name='" + name + '\'' +
                ", dueTime ='" + dueTime + '\'' +
                ", units=" + units +
                '}';
    }

    public String getDueTime() {
        return dueTime;
    }

    public int getUnits() {
        return units;
    }

    public int getUnitsPerTimeslot(){
        return unitsPerTimeslot;
    }

    public List<Integer> distributeUnits() {
            List<Integer> distribution = new ArrayList<>();
            unitsRemaining = units;

            while (unitsRemaining > 0) {
                if (unitsRemaining >= unitsPerTimeslot) {
                    distribution.add(unitsPerTimeslot);
                    unitsRemaining -= unitsPerTimeslot;
                } else {
                    distribution.add(unitsRemaining);
                    unitsRemaining = 0;
                }
            }

            return distribution;
        }


}