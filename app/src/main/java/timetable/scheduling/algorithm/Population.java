package timetable.scheduling.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Population {
    private List<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(i -> schedules.add(new Schedule(data).init()));
    }

    public Population sortByFitness() {
        schedules.sort((s1, s2) -> Double.compare(s2.getFitness(), s1.getFitness()));
        return this;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    @Override
    public String toString() {
        return "Population{" +
                "schedules=" + schedules +
                '}';
    }
}
