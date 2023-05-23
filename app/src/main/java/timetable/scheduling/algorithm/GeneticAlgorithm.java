package timetable.scheduling.algorithm;

import timetable.scheduling.model.*;
import timetable.scheduling.model.Class;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
    public static final int POPULATION_SIZE = 9;
    static final double MUTATION_RATE = 0.1;
    static final double CROSSOVER_RATE = 0.9;
    static final int TOURNAMENT_SELECTION_SIZE = 3;
    static final int NUMB_OF_ELITE_SCHEDULES = 2;
    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    public Population evolve(Population population) {
        return mutatePopulation(
                crossoverPopulation(population)
        );
    }

    Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getSchedules().size(), data);
        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES).forEach(i -> crossoverPopulation.getSchedules().set(i, population.getSchedules().get(i)));
        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(i -> {
            if (CROSSOVER_RATE > Math.random()) {
                Schedule first = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule second = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                crossoverPopulation.getSchedules().set(i, crossoverSchedule(first, second));
            } else {
                crossoverPopulation.getSchedules().set(i, population.getSchedules().get(i));
            }
        });

        return crossoverPopulation;
    }

    Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, data);
        IntStream.range(0, TOURNAMENT_SELECTION_SIZE).forEach(i ->
                tournamentPopulation.getSchedules().set(i, population.getSchedules().get((int) (Math.random() * population.getSchedules().size()))));

        return tournamentPopulation;
    }

    Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getSchedules().size(), data);
        List<Schedule> schedules = mutatePopulation.getSchedules();
        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES).forEach(i -> schedules.set(i, population.getSchedules().get(i)));
        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size())
                .forEach(i -> schedules.set(i, mutateSchedule(schedules.get(i))));

        return mutatePopulation;
    }

    Schedule mutateSchedule(Schedule schedule) {
        Schedule mutateSchedule = new Schedule(data).init();
        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(i -> {
            if (MUTATION_RATE > Math.random()) {
                mutateSchedule.getClasses().set(i, schedule.getClasses().get(i));
            }
        });

        return mutateSchedule;
    }

    Schedule crossoverSchedule(Schedule first, Schedule second) {
        Schedule crossoverSchedule = new Schedule(data).init();
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(i -> {
            if (Math.random() > 0.5) {
                crossoverSchedule.getClasses().set(i, first.getClasses().get(i));
            } else {
                crossoverSchedule.getClasses().set(i, second.getClasses().get(i));
            }
        });

        return crossoverSchedule;
    }

    private static Data initialData = new Data();
    private static int scheduleNumb = 0;
    private static int classNumb = 0;

    public Schedule run() {
        int generationNumber = 0;
        System.out.println(">> Generation # " + generationNumber);
        System.out.print(" Schedule # |                    ");
        System.out.println("Classes [dept, class, room, instructor, meeting-time]                   | Fitness | Conflicts   ");
        System.out.println("-----------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initialData);
        Population population = new Population(POPULATION_SIZE, initialData).sortByFitness();
        population.getSchedules().forEach(schedule -> {
            System.out.println("       " + scheduleNumb++ +
                    "            | " + schedule + " | " +
                    schedule.getFitness() +
                    "  |  " + schedule.getNumberOfConflicts());
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
                        String.format("%.5f", schedule.getFitness()) + " | " + schedule.getNumberOfConflicts());
            });
            printAsTable(population.getSchedules().get(0), generationNumber);
            population.getSchedules().get(0).calculateFitness();

            classNumb = 1;
        }

        return population.getSchedules().get(0);
    }

    private static void printAsTable(Schedule schedule, int generation) {
        List<Class> classes = schedule.getClasses();
        System.out.println("");
        System.out.println("Class # | Dept | Course (number, max # of students) | Room (Capacity) | Instructor (id) | Meeting Time (Id)");
        System.out.println("                               ");
        System.out.println("---------------------------------------------------------------------------------");
        classes.forEach(x -> {
            Course course = initialData.getCourses().stream().filter(c -> c.getNumber().equals(x.getCourse().getNumber())).findFirst().get();
            Room room = initialData.getRooms().stream().filter(r -> r.getNumber().equals(x.getRoom().getNumber())).findFirst().get();
            Instructor instructor = initialData.getInstructors().stream().filter(i -> i.getId().equals(x.getInstructor().getId())).findFirst().get();
            MeetingTime meetingTime = initialData.getMeetingTimes().stream().filter(m -> m.getId().equals(x.getMeetingTime().getId())).findFirst().get();

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
                    " (" + meetingTime.getId() + ")");
            classNumb++;
        });

        if (schedule.getFitness() == 1) {
            System.out.println("> Solution Found in " + (generation + 1) + " generations");
            System.out.println("---------------------------------------------------------------------------------");
        }
    }
}
