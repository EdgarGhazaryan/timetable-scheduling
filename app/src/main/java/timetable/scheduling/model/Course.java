package timetable.scheduling.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String number;
    private String name;
    private int countPerWeek;
    private Group group;
    private List<Instructor> instructors;
    private List<Group> groupableWith = new ArrayList<>();

    public Course(String number, String name, List<Instructor> instructors, Group group, int countPerWeek) {
        this.number = number;
        this.name = name;
        this.instructors = instructors;
        this.group = group;
        this.countPerWeek = countPerWeek;
    }

    public Course(String number, String name, List<Instructor> instructors, Group group, int countPerWeek, List<Group> groupableWith) {
        this.number = number;
        this.name = name;
        this.instructors = instructors;
        this.group = group;
        this.countPerWeek = countPerWeek;
        this.groupableWith = groupableWith;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPerWeek() {
        return countPerWeek;
    }

    public void setCountPerWeek(int countPerWeek) {
        this.countPerWeek = countPerWeek;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
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
        var result =  name;
        if (group != null) {
            result +=  " " + group.getName();
        } else {
            result +=  " " + groupableWith;
        }
        return result;
    }
}
