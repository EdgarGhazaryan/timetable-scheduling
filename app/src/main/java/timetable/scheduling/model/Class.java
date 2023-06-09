package timetable.scheduling.model;

import java.util.ArrayList;
import java.util.List;

public class Class implements Cloneable {
    private int id;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingTime;
    private Room room;
    private Group group;
    private List<Group> groupableWith = new ArrayList<>();

    public Class(Course course) {
        this.course = course;
    }

    public Class(int id, Course course) {
        this.id = id;
        this.course = course;
    }

    public Class(int id, Course course, Group group) {
        this.id = id;
        this.course = course;
        this.group = group;
    }

    public Class(int id, Course course, Instructor instructor, MeetingTime meetingTime, Room room, Group group, List<Group> groupableWith) {
        this.id = id;
        this.course = course;
        this.instructor = instructor;
        this.meetingTime = meetingTime;
        this.room = room;
        this.group = group;
        this.groupableWith = groupableWith;
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

    public List<Group> getGroupableWith() {
        return groupableWith;
    }

    public void setGroupableWith(List<Group> groupableWith) {
        this.groupableWith = groupableWith;
    }

    @Override
    public String toString() {
        return "[" +
                (group != null ? group.getName() : groupableWith) +
                "," +
                course.getNumber() +
                "," +
                groupableWith +
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
        return new Class(id, course, instructor, meetingTime, room, group, groupableWith);
    }
}
