package timetable.scheduling.model;

public class MeetingTime implements Comparable<MeetingTime> {
    private static int COUNTER = 0;
    private String id;
    private String time;
    private final int order;

    public MeetingTime(String id, String time) {
        this.id = id;
        this.time = time;
        this.order = COUNTER++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(MeetingTime other) {
        return Integer.compare(order, other.order);
    }
}
