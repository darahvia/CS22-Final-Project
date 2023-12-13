
class UnscheduledEntry implements Entry, Comparable<UnscheduledEntry> {
    protected String name;
    protected String dueTime;
    protected int units;
    protected int unitsPerTimeslot;
    protected int priority;
    protected int timeslot;

    public UnscheduledEntry(String name, String dueTime, int units, int unitsPerTimeslot, int priority, int timeslot) {
        this.name = name;
        this.dueTime = dueTime;
        this.units = units;
        this.unitsPerTimeslot = unitsPerTimeslot;
        this.priority = priority;
        this.timeslot = timeslot;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "UnscheduledEntry{" +
                "name='" + name + '\'' +
                ", dueTime ='" + dueTime + '\'' +
                ", units=" + units +
                ", priority=" + priority +
                ", timeslot =" + timeslot +
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

    public int priority() {
        return priority;
    }

    public int getTimeslot(){
        return timeslot;
    }

    public int compareTo(UnscheduledEntry otherEntry){
        return Integer.compare(otherEntry.getPriority(), this.getPriority());
    }


}