package timetable.scheduling.algorithm;

import timetable.scheduling.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    private List<Room> rooms;
    private List<Instructor> instructors;
    private final List<Course> courses = new ArrayList<>();
    private List<Department> departments;
    private List<MeetingTime> meetingTimes;
    private List<Group> groups;
    private int numberOfClasses = 0;

    public Data() {
        init();
    }

    private Data init() {
        rooms = List.of(
                new Room("101", 55),
                new Room("102", 55),
                new Room("103", 55),
                new Room("104", 55),
                new Room("105", 55),
                new Room("106", 55),
                new Room("107", 55),
                new Room("108", 55),
                new Room("109", 55),
                new Room("110", 55),
                new Room("111", 55),
                new Room("112", 55),
                new Room("201", 55),
                new Room("202", 55),
                new Room("203", 55),
                new Room("204", 55),
                new Room("205", 55),
                new Room("206", 55),
                new Room("207", 55),
                new Room("208", 55),
                new Room("209", 55),
                new Room("210", 55),
                new Room("211", 55)
        );

        meetingTimes = List.of(
                new MeetingTime("MON-1", "MON 09:30 - 10:55"),
                new MeetingTime("MON-2", "MON 11:05 - 12:30"),
                new MeetingTime("MON-3", "MON 12:50 - 14:15"),
                new MeetingTime("MON-4", "MON 14:30 - 15:55"),
                new MeetingTime("TUE-1", "TUE 09:30 - 10:55"),
                new MeetingTime("TUE-2", "TUE 11:05 - 12:30"),
                new MeetingTime("TUE-3", "TUE 12:50 - 14:15"),
                new MeetingTime("WED-1", "WED 09:30 - 10:55"),
                new MeetingTime("WED-2", "WED 11:05 - 12:30"),
                new MeetingTime("WED-3", "WED 12:50 - 14:15"),
                new MeetingTime("THU-1", "THU 09:30 - 10:55"),
                new MeetingTime("THU-2", "THU 11:05 - 12:30"),
                new MeetingTime("THU-3", "THU 12:50 - 14:15"),
                new MeetingTime("THU-4", "THU 14:30 - 15:55"),
                new MeetingTime("FRI-1", "FRI 09:30 - 10:55"),
                new MeetingTime("FRI-2", "FRI 11:05 - 12:30"),
                new MeetingTime("FRI-3", "FRI 12:50 - 14:15")
        );

        Instructor hovhannisyanA = new Instructor("I1", "Հովհաննիստան Ա.");
        Instructor danoyanE = new Instructor("I2", "Դանոյան Է.");
        Instructor movsisyanH = new Instructor("I3", "Մովսիսյան Հ.");
        Instructor gasparyanH = new Instructor("I4", "Գասպարյան Հ.");
        Instructor budaghyanL = new Instructor("I5", "Բուդաղյան Լ.");
        Instructor chubaryanA = new Instructor("I6", "Չուբարյան Ա.");
        Instructor gabrielyanV = new Instructor("I7", "Գաբրիելյան Վ.");
        Instructor hovhannisyanZ = new Instructor("I8", "Հովհաննիսյան Զ.");
        Instructor sahakyanS = new Instructor("I9", "Սահակյան Ս.");
        Instructor bolibekyanH = new Instructor("I10", "Բոլիբեկյան Հ.");
        Instructor galstyanK = new Instructor("I11", "Գալստյան Կ.");
        Instructor hakobyanH = new Instructor("I12", "Հակոբյան Հ.");
        Instructor araqelyanA = new Instructor("I13", "Առաքելյան Ա.");
        Instructor smbatyanX = new Instructor("I14", "Սմբատյան Խ.");
        Instructor toroyanS = new Instructor("I15", "Թորոյան Ս.");
        Instructor manukyanS = new Instructor("I16", "Մանուկյան Ս.");
        Instructor dumanyanV = new Instructor("I17", "Դումանյան Վ.");
        Instructor dashtoyanL = new Instructor("I18", "Դաշտոյան Լ.");
        Instructor tamazyanH = new Instructor("I19", "Թամազյան Հ.");
        Instructor arabyanM = new Instructor("I20", "Արաբյան Մ.");
        instructors = List.of(
                hovhannisyanA,
                danoyanE,
                movsisyanH,
                gasparyanH,
                budaghyanL,
                chubaryanA,
                gabrielyanV,
                hovhannisyanZ,
                sahakyanS,
                bolibekyanH,
                galstyanK,
                hakobyanH,
                araqelyanA,
                smbatyanX,
                toroyanS,
                manukyanS,
                dumanyanV,
                dashtoyanL,
                tamazyanH,
                arabyanM
        );

        Group group901 = new Group( "901", 1, 25);
        Group group902 = new Group( "902", 1, 26);
        Group group903 = new Group( "903", 1, 27);
        Group group904 = new Group( "904", 1, 28);
        Group group905 = new Group( "905", 1, 28);
        Group group906 = new Group( "906", 1, 28);
        Group group907 = new Group( "907", 1, 28);
        Group group908 = new Group( "908", 1, 28);
        Group group909 = new Group( "909", 1, 28);
        groups = List.of(
                group901,
                group902,
                group903,
                group904,
                group905,
                group906,
                group907,
                group908,
                group909
        );

        groups.forEach(group -> {
            Course course1 = new Course("C1", "Մաթֆիզ - գործնական", List.of(hovhannisyanA, hovhannisyanZ, toroyanS, arabyanM), group, 2);
            Course course2 = new Course("C2", "Թվային մեթոդներ - գործնական", List.of(danoyanE, sahakyanS, manukyanS), group, 1);
            Course course3 = new Course("C3", "Մաթ տրամաբանություն - գործնական", List.of(movsisyanH, smbatyanX, dashtoyanL, tamazyanH), group, 1);
            Course course4 = new Course("C4", "Տվյալների հենքեր - գործնական", List.of(budaghyanL, galstyanK), group, 1);
            courses.addAll(List.of(course1, course2, course3, course4));
        });

        List.of(
                List.of(group901, group902, group903),
                List.of(group904, group905, group906),
                List.of(group907, group908, group909)
        ).forEach(list -> {
            courses.addAll(List.of(
//                new Course("C6", "Թվային մեթոդներ - դասախոսություն", List.of(danoyanE, sahakyanS, manukyanS), null, 2, list),
//                new Course("C7", "Կոմպյուտերային ցանցեր - դասախոսություն", List.of(gasparyanH), null, 1, list),
//                new Course("C8", "Տվյալների հենքեր - դասախոսություն", List.of(budaghyanL, araqelyanA), null, 1, list)
//                new Course("C9", "Մաթֆիզ - դասախոսություն", List.of(hovhannisyanA, hovhannisyanZ, dumanyanV), null, 2, list),
//                new Course("C10", "Մաթ տրամաբանություն - դասախոսություն", List.of(chubaryanA, bolibekyanH, dashtoyanL), null, 1, list),
//                new Course("C11", "Կոմբինատոր ալգորիթմներ - դասախոսություն", List.of(gabrielyanV, hakobyanH), null, 2, list)
            ));
        });

        System.out.println("coursescourses " + courses);

//        departments = List.of(
//                new Department("Անալիզ", new ArrayList<>(List.of(course3, course4))),
//                new Department("Դիսկրետ", new ArrayList<>(List.of(course5, course6))),
//                new Department("Ծրագրավորում", new ArrayList<>(List.of(course1, course3))),
//                new Department("Լեզուներ", new ArrayList<>(List.of(course7)))
//        );
//        departments.forEach(x -> numberOfClasses += x.getCourses().size());

        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }
}
