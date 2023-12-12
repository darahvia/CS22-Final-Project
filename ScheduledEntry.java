class ScheduledEntry implements Entry {
    protected String name;
    protected String day;
    protected String time;

    public ScheduledEntry(String name, String day, String time) {
        this.name = name;
        this.day = day;
        this.time = time;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ScheduledEntry{" +
                "name='" + name + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}