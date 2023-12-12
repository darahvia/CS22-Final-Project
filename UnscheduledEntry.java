
class UnscheduledEntry implements Entry, Comparable<UnscheduledEntry> {
    protected String name;
    protected String dueDate;
    protected int units;
    protected int unitsPerDay;
    protected int priority;
    protected int timeslot;

    public UnscheduledEntry(String name, String dueDate, int units, int unitsPerDay, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.units = units;
        this.unitsPerDay = unitsPerDay;
        this.priority = priority;
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
                ", dueDate='" + dueDate + '\'' +
                ", units=" + units +
                ", unitsPerDay=" + unitsPerDay +
                ", priority=" + priority +
                '}';
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getUnits() {
        return units;
    }

    public int getUnitsPerDay() {
        return unitsPerDay;
    }

    public int compareTo(UnscheduledEntry otherEntry){
        return Integer.compare(otherEntry.getPriority(), this.getPriority());
    }

    public int getTimeslot(){
        return timeslot;
    }
}