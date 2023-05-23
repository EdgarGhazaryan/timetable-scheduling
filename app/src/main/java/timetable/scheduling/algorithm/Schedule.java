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
    private int numberOfConflicts = 0;
    private double fitness = -1;
    private boolean isFitnessChanged = true;

    public Schedule(Data data) {
        this.data = data;
        this.classes = new ArrayList<>(data.getNumberOfClasses());
    }

    public Schedule(List<Class> classes, Data data, int classNumb, int numberOfConflicts, double fitness, boolean isFitnessChanged) {
        this.classes = classes;
        this.data = data;
        this.classNumb = classNumb;
        this.numberOfConflicts = numberOfConflicts;
        this.fitness = fitness;
        this.isFitnessChanged = isFitnessChanged;
    }

    public Schedule init() {
        data.getCourses().forEach(course -> {
            AtomicInteger countPerWeek = new AtomicInteger(course.getCountPerWeek());
            Instructor instructor = course.getInstructors().get((int) (course.getInstructors().size() * Math.random()));

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
        numberOfConflicts = 0;
        classes.forEach(x -> {
            if (!x.getGroupableWith().isEmpty()) {
                long studentsSumCount = x.getGroupableWith().stream()
                        .collect(Collectors.summarizingInt(Group::getStudentsCount))
                        .getSum();
                if (x.getRoom().getSeatingCapacity() < studentsSumCount) {
                    numberOfConflicts++;
                }
            } else if (x.getRoom().getSeatingCapacity() < x.getGroup().getStudentsCount()) {
                numberOfConflicts++;
            }
            classes.stream().filter(y -> classes.indexOf(y) > classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
                    if (x.getRoom().getNumber().equals(y.getRoom().getNumber())) {
                        numberOfConflicts++;
                    }
                    if (x.getInstructor().getId().equals(y.getInstructor().getId())) {
                        numberOfConflicts++;
                    }
                    if (x.getGroup() != null) {
                        if (y.getGroup() != null && x.getGroup().getName().equals(y.getGroup().getName())) {
                            numberOfConflicts++;
                        }
                        if (!y.getGroupableWith().isEmpty()) {
                            y.getGroupableWith().forEach(group -> {
                                if (x.getGroup().getName().equals(group.getName())) {
                                    numberOfConflicts++;
                                }
                            });
                        }
                    } else if (!x.getGroupableWith().isEmpty()) {
                        if (y.getGroup() != null) {
                            x.getGroupableWith().forEach(group -> {
                                if (group.getName().equals(y.getGroup().getName())) {
                                    numberOfConflicts++;
                                }
                            });
                        } else if (!y.getGroupableWith().isEmpty()) {
                            x.getGroupableWith().forEach(group -> {
                                y.getGroupableWith().forEach(secondGroup -> {
                                    if (group.getName().equals(secondGroup.getName())) {
                                        numberOfConflicts++;
                                    }
                                });
                            });
                        }
                    }
                }
            });
        });

        return 1 / (double) (numberOfConflicts + 1);
    }

    public Data getData() {
        return data;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
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
