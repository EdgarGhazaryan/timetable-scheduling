package timetable.scheduling;

import timetable.scheduling.algorithm.Data;
import timetable.scheduling.algorithm.GeneticAlgorithm;
import timetable.scheduling.algorithm.Population;
import timetable.scheduling.algorithm.Schedule;
import timetable.scheduling.model.*;
import timetable.scheduling.model.Class;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static timetable.scheduling.algorithm.GeneticAlgorithm.POPULATION_SIZE;

public class App {

    private static Data data = new Data(); // TODO check the source
    private static int scheduleNumb = 0;
    private static int classNumb = 0;

    public static void main(String[] args) {
        int generationNumber = 0;
        System.out.println(">> Generation # " + generationNumber);
        System.out.print(" Schedule # |                    ");
        System.out.println("Classes [dept, class, room, instructor, meeting-time]                   | Fitness | Conflicts   ");
        System.out.println("-----------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data);
        Population population = new Population(POPULATION_SIZE, data).sortByFitness();
        population.getSchedules().forEach(schedule -> {
            System.out.println("       " + scheduleNumb++ +
                    "            | " + schedule + " | " +
                    schedule.getFitness() +
                    "  |  " + schedule.getNumberOfConflict());
        });

        printAsTable(population.getSchedules().get(0), generationNumber);

        while (population.getSchedules().get(0).getFitness() != 1.0) {
            System.out.println(">> Generation # " + ++generationNumber);
            System.out.print(" Schedule # |                    ");
            System.out.println("Classes [dept, class, room, instructor, meeting-time]                   | Fitness | Conflicts   ");
            System.out.println("---------------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            scheduleNumb = 0;
            population.getSchedules().forEach(schedule -> {
                System.out.println("           " + scheduleNumb++ + "         | " + schedule + " | " +
                        String.format("%.5f", schedule.getFitness()) + " | " + schedule.getNumberOfConflict());
            });
            printAsTable(population.getSchedules().get(0), generationNumber);
            System.out.println("FITNESSSS");
            population.getSchedules().get(0).calculateFitness();

            classNumb = 1;
        }
    }

    private static void printAsTable(Schedule schedule, int generation) {
        List<Class> classes = schedule.getClasses();
        System.out.println("");
        System.out.println("Class # | Dept | Course (number, max # of students) | Room (Capacity) | Instructor (id) | Meeting Time (Id)");
        System.out.println("                               ");
        System.out.println("---------------------------------------------------------------------------------");
        classes.forEach(x -> {
//            Department department = data.getDepartments().stream().filter(d -> d.getName().equals(x.getDepartment().getName())).findFirst().get();
            Course course = data.getCourses().stream().filter(c -> c.getNumber().equals(x.getCourse().getNumber())).findFirst().get();
            Room room = data.getRooms().stream().filter(r -> r.getNumber().equals(x.getRoom().getNumber())).findFirst().get();
            Instructor instructor = data.getInstructors().stream().filter(i -> i.getId().equals(x.getInstructor().getId())).findFirst().get();
            MeetingTime meetingTime = data.getMeetingTimes().stream().filter(m -> m.getId().equals(x.getMeetingTime().getId())).findFirst().get();

            System.out.print(String.format("   %1$02d  ", classNumb) + "  |  ");
            System.out.print(String.format("%1$4s  ", x.getGroup() != null ? x.getGroup().getName() : x.getGroupableWith()) + "  |  ");
            System.out.print(String.format("%1$21s  ", course.getName() +
                    " (" + course.getNumber() + ", " +
                    (x.getGroup() != null ? x.getGroup().getStudentsCount() : x.getGroupableWith().stream().collect(Collectors.summarizingInt(Group::getStudentsCount)).getSum())
            ) + ")       | ");
            System.out.print(String.format("%1$10s  ", room.getNumber() +
                    " (" + room.getSeatingCapacity()) + ")     | ");
            System.out.print(String.format("%1$15s  ", instructor.getName() +
                    " (" + instructor.getId() + ")") + "   |  ");
            System.out.println(meetingTime.getTime() +
                    " (" +  meetingTime.getId() + ")");
            classNumb++;
        });

        if (schedule.getFitness() == 1) {
            System.out.println("> Solution Found in " + (generation + 1) + " generations");
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
