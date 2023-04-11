package timetable.scheduling.model;

public class Class implements Cloneable {
    private int id;
//    private Department department;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingTime;
    private Room room;
    private Group group;

    public Class(Course course) {
        this.course = course;
    }

    public Class(int id, Course course, Group group) {
        this.id = id;
        this.course = course;
        this.group = group;
    }

    public Class(int id, Course course, Instructor instructor, MeetingTime meetingTime, Room room, Group group) {
        this.id = id;
        this.course = course;
        this.instructor = instructor;
        this.meetingTime = meetingTime;
        this.room = room;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "[" +
                group.getName() +
                "," +
                course.getNumber() +
                "," +
                room.getNumber() +
                "," +
                instructor.getId() +
                "," +
                meetingTime.getId() +
                "]";
    }

    @Override
    public Class clone() {
        Class clone = new Class(id, course, instructor, meetingTime, room, group);
        return clone;
    }
}
