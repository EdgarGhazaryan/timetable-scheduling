package timetable.scheduling.model;

public class Group {
    private String name;
    private int year;
    private int studentsCount;

    public Group(String name, int year, int studentsCount) {
        this.name = name;
        this.year = year;
        this.studentsCount = studentsCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String toString() {
        return "Group{" +
                "year=" + year +
                ", name=" + name +
                ", studentsCount=" + studentsCount +
                '}';
    }
}
