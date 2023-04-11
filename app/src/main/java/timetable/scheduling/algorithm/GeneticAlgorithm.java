package timetable.scheduling.algorithm;

import java.util.List;
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
        return mutatePopulation(crossoverPopulation(population));
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
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, data); // TODO ?
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
}
