package timetable.scheduling.model;

public class Class {
    private int id;
    private String name;
    private int timeSlot;

    public Class(int id, String name, int timeSlot) {
        this.id = id;
        this.name = name;
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimeSlot() {
        return timeSlot;
    }
}
