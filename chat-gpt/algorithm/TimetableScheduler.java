package timetable.scheduling.algorithm;

import timetable.scheduling.model.Instructor;
import timetable.scheduling.model.Room;
import timetable.scheduling.model.Class;
import timetable.scheduling.model.TimetableSchedule;

import java.util.*;

public class TimetableScheduler {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    private int tournamentSize;

    private List<Class> classes;
    private List<Room> rooms;
    private List<Instructor> instructors;

    public TimetableScheduler(int populationSize, double mutationRate, double crossoverRate,
                              int elitismCount, int tournamentSize,
                              List<Class> classes, List<Room> rooms, List<Instructor> instructors) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
        this.classes = classes;
        this.rooms = rooms;
        this.instructors = instructors;
    }

    public TimetableSchedule evolve(int generations) {
        TimetableSchedule schedule = new TimetableSchedule(classes, rooms, instructors);
        List<TimetableSchedule> population = schedule.createPopulation(populationSize);

        for (int i = 0; i < generations; i++) {
            List<TimetableSchedule> newPopulation = new ArrayList<>();
            for (int j = 0; j < elitismCount; j++) {
                newPopulation.add(population.get(j));
            }
            for (int j = elitismCount; j < populationSize; j++) {
                TimetableSchedule parent1 = tournamentSelection(population);
                TimetableSchedule parent2 = tournamentSelection(population);
                TimetableSchedule child = crossover(parent1, parent2);
                mutate(child);
                newPopulation.add(child);
            }
            population = newPopulation;
        }

        return Collections.max(population, Comparator.comparing(TimetableSchedule::getFitness));
    }

//    public void generatePopulation() {
//        population = new TimetableSchedule[populationSize];
//        for (int i = 0; i < populationSize; i++) {
//            TimetableSchedule schedule = new TimetableSchedule(classes, rooms, instructors);
//            schedule.generateSchedule();
//            population[i] = schedule;
//        }
//    }

//    public TimetableSchedule getBestSchedule() {
//        double bestFitness = Double.MAX_VALUE;
//        int bestIndex = 0;
//
//        for (int i = 0; i < populationSize; i++) {
//            if (population[i].getFitness() < bestFitness) {
//                bestFitness = population[i].getFitness();
//                bestIndex = i;
//            }
//        }
//
//        return population[bestIndex];
//    }

    private TimetableSchedule tournamentSelection(List<TimetableSchedule> population) {
        List<TimetableSchedule> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get((int) (Math.random() * populationSize)));
        }
        return Collections.max(tournament, Comparator.comparing(TimetableSchedule::getFitness));
    }

    private TimetableSchedule crossover(TimetableSchedule parent1, TimetableSchedule parent2) {
        TimetableSchedule child = new TimetableSchedule(classes, rooms, instructors);
        for (int i = 0; i < child.getNumberOfClasses(); i++) {
            if (Math.random() < crossoverRate) {
                child.setClass(i, parent1.getClass(i));
            } else {
                child.setClass(i, parent2.getClass(i));
            }
        }
        return child;
    }

    private void mutate(TimetableSchedule schedule) {
        for (int i = 0; i < schedule.getNumberOfClasses(); i++) {
            if (Math.random() < mutationRate) {
                int roomId = (int) (Math.random() * rooms.size());
                int instructorId = (int) (Math.random() * instructors.size());
                schedule.setClassRoom(i, rooms.get(roomId));
                schedule.setClassInstructor(i, instructors.get(instructorId));
            }
        }
    }
}

