class ScheduledEntry implements Entry {
    protected String name;
    protected String startTime;
    protected String endTime;

    public ScheduledEntry(String name, String startTime, String endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ScheduledEntry{" +
                "name='" + name + '\'' +
                ", day='" + startTime + '\'' +
                ", time='" + endTime + '\'' +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}