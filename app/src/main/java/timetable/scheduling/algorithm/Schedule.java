package timetable.scheduling.algorithm;

import timetable.scheduling.model.Class;
import timetable.scheduling.model.Group;
import timetable.scheduling.model.Instructor;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Schedule {
    private List<Class> classes;
    private Data data;
    private int classNumb = 0;
    private int numberOfConflict = 0;
    private double fitness = -1;
    private boolean isFitnessChanged = true;

    public Schedule(Data data) {
        this.data = data;
        this.classes = new ArrayList<>(data.getNumberOfClasses());
    }

    public Schedule init() {
//        Map<String, Boolean> coursesMap = new HashMap<>();

        data.getCourses().forEach(course -> {
//            String key = course.getName() + ":" + course.getGroup().getName();
//            if (coursesMap.getOrDefault(key, false)) {
//                return;
//            }

//            if (course.getGroup() != null) {
                AtomicInteger countPerWeek = new AtomicInteger(course.getCountPerWeek());
                Instructor instructor = course.getInstructors().get((int) (course.getInstructors().size() * Math.random()));
//            if (!course.getGroupableWith().isEmpty()) {
//                course.getGroupableWith().forEach(group -> {
//                    while (countPerWeek.getAndDecrement() != 0) {
//                        Class newClass = new Class(classNumb++, course, group);
//                        newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
//                        newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
//                        newClass.setInstructor(instructor);
//                        classes.add(newClass);
//
//                        coursesMap.put(course.getName() + ":" + group.getName(), true);
//                    }
//                });
//            }
                while (countPerWeek.getAndDecrement() != 0) {
                    Class newClass = new Class(classNumb++, course);
                    newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
                    newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                    newClass.setInstructor(instructor);

                    if (course.getGroup() != null) {
                        newClass.setGroup(course.getGroup());
                    } else if (course.getGroupableWith() != null && !course.getGroupableWith().isEmpty()) {
                        newClass.setGroupableWith(course.getGroupableWith());
                    } else {
                        throw new InvalidParameterException("Either group or groupableWith should be present");
                    }

                    classes.add(newClass);
                }

                return;
//            }

//            if (!course.getGroupableWith().isEmpty()) {
//                AtomicInteger countPerWeek = new AtomicInteger(course.getCountPerWeek());
//                Instructor instructor = course.getInstructors().get((int) (course.getInstructors().size() * Math.random()));
//
//                while (countPerWeek.getAndDecrement() != 0) {
//                    Class newClass = new Class(course);
//                    newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
//                    newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
//                    newClass.setInstructor(instructor);
//
//                    course.getGroupableWith().forEach(group -> {
//                        Class groupClass = newClass.clone();
//                        groupClass.setId(classNumb++);
//                        groupClass.setGroup(group);
//
//                        classes.add(groupClass);
//                    });
//                }
//            }
//            coursesMap.put(key, true);
        });

        return this;
    }

    public double getFitness() {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    public double calculateFitness() {
        numberOfConflict = 0;
        classes.forEach(x -> {
//            if (x.getRoom().getSeatingCapacity() < x.getGroup().getStudentsCount())
//                numberOfConflict++;

            if (!x.getGroupableWith().isEmpty()) {
                long studentsSumCount = x.getGroupableWith().stream().collect(Collectors.summarizingInt(Group::getStudentsCount)).getSum();
                if (x.getRoom().getSeatingCapacity() < studentsSumCount) {
                    numberOfConflict++;
                }
            } else if (x.getRoom().getSeatingCapacity() < x.getGroup().getStudentsCount()) {
                numberOfConflict++;
            }

            classes.stream().filter(y -> classes.indexOf(y) > classes.indexOf(x)).forEach(y -> {
//                if (!x.getCourse().getGroupableWith().isEmpty() && !y.getCourse().getGroupableWith().isEmpty() &&
//                        x.getCourse().getNumber().equals(y.getCourse().getNumber()) &&
//                        (x.getMeetingTime() != y.getMeetingTime() || x.getRoom() != y.getRoom())
//                ) {
//                    System.out.println("CONFLICT LEKCIA: " + x + " " + y);
//                    numberOfConflict++;
//                }

                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
                    if (x.getCourse().getNumber().equals(y.getCourse().getNumber())) {
                        return;
                    }

                    if (x.getRoom().getNumber().equals(y.getRoom().getNumber())) {
//                        System.out.println("CONFLICT ROOM: " + x + " " + y);
                        numberOfConflict++;
                    }

                    if (x.getInstructor().getId().equals(y.getInstructor().getId())) {
//                        System.out.println("CONFLICT INSTR: " + x + " " + y);
                        numberOfConflict++;
                    }

                    if (x.getGroup() != null && y.getGroup() != null && x.getGroup().getName().equals(y.getGroup().getName())) { // TODO fix here check
//                        System.out.println("CONFLICT GROUP: " + x + " " + y);
                        numberOfConflict++;
                    } else if (!x.getGroupableWith().isEmpty() && y.getGroup() != null) {
                        x.getGroupableWith().forEach(group -> {
                            if (group.getName().equals(y.getGroup().getName())) {
//                                System.out.println("CONFLICT GROUPABLE: " + x + " " + y);
                                numberOfConflict++;
                            }
                        });
                    }
                }

//                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId() &&
//                        (x.getRoom().getNumber().equals(y.getRoom().getNumber()) ||
//                                x.getInstructor().getId().equals(y.getInstructor().getId()) ||
//                                (x.getGroup() != null && x.getGroup().getName().equals(y.getGroup().getName()))
//                        )
//                ) {
//                    System.out.println("CONFLICT: " + x + " " + y);
//                    numberOfConflict++;
//                }
            });
        });

        return 1 / (double) (numberOfConflict + 1);
    }

    public Data getData() {
        return data;
    }

    public int getNumberOfConflict() {
        return numberOfConflict;
    }

    public List<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    @Override
    public String toString() {
        if (classes.isEmpty())
            return "";

        StringBuilder s = new StringBuilder();
        int i = 0;
        for (; i < classes.size() - 1; i++) {
            s.append(classes.get(i)).append(",");
        }
        s.append(classes.get(i));
        return s.toString();
    }
}
