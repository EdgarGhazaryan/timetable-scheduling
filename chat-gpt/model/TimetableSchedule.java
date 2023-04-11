package timetable.scheduling.model;

import java.util.*;

public class TimetableSchedule {
    private List<Class> classes;
    private List<Room> rooms;
    private List<Instructor> instructors;
    private int[][] classSchedule;
    private double fitness = -1;

    public TimetableSchedule(List<Class> classes, List<Room> rooms, List<Instructor> instructors) {
        this.classes = classes;
        this.rooms = rooms;
        this.instructors = instructors;
        classSchedule = new int[classes.size()][3];
        initialize();
    }

    public List<Class> getClasses() {
        return classes;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setClass(int classIndex, Class classObj) {
        classSchedule[classIndex][0] = classObj.getId();
    }

    public Class getClass(int classIndex) {
        return classes.get(classSchedule[classIndex][0]);
    }

    public void setClassRoom(int classIndex, Room room) {
        classSchedule[classIndex][1] = room.getId();
    }

    public Room getClassRoom(int classIndex) {
        return rooms.get(classSchedule[classIndex][1]);
    }

    public void setClassInstructor(int classIndex, Instructor instructor) {
        classSchedule[classIndex][2] = instructor.getId();
    }

    public Instructor getClassInstructor(int classIndex) {
        return instructors.get(classSchedule[classIndex][2]);
    }

    public int getNumberOfClasses() {
        return classes.size();
    }

    public int[][] getClassSchedule() {
        return classSchedule;
    }

    public double getFitness() {
        if (fitness == -1) {
            fitness = calculateFitness();
        }
        return fitness;
    }

    private void initialize() {
        for (int i = 0; i < classes.size(); i++) {
            Class classObj = classes.get(i);
            Room room = rooms.get((int) (Math.random() * rooms.size()));
            Instructor instructor = instructors.get((int) (Math.random() * instructors.size()));
            classSchedule[i][0] = classObj.getId();
            classSchedule[i][1] = room.getId();
            classSchedule[i][2] = instructor.getId();
        }
    }

    private double calculateFitness() {
        int conflicts = 0;
        for (int i = 0; i < classes.size(); i++) {
            Class classObj = getClass(i);
            Room room = getClassRoom(i);
            Instructor instructor = getClassInstructor(i);
            int timeSlot = classObj.getTimeSlot();
            for (int j = 0; j < classes.size(); j++) {
                if (i == j) {
                    continue;
                }
                Class otherClass = getClass(j);
                Room otherRoom = getClassRoom(j);
                Instructor otherInstructor = getClassInstructor(j);
                int otherTimeSlot = otherClass.getTimeSlot();
                if (timeSlot == otherTimeSlot && (room == otherRoom || instructor == otherInstructor)) {
                    conflicts++;
                }
            }
        }
        return 1 / (double) (conflicts + 1);
    }

    public List<TimetableSchedule> createPopulation(int populationSize) {
        List<TimetableSchedule> population = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            population.add(i, new TimetableSchedule(classes, rooms, instructors));
            population.get(i).initialize();
        }

        return population;
    }

    @Override
    public String toString() {
        return "TimetableSchedule{" +
                "classes=" + classes +
                ", rooms=" + rooms +
                ", instructors=" + instructors +
                ", classSchedule=" + Arrays.toString(classSchedule) +
                ", fitness=" + fitness +
                '}';
    }
}