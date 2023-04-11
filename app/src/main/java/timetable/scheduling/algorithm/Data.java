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
        rooms = Arrays.asList(
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

        meetingTimes = Arrays.asList(
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

        Instructor kirakosyan = new Instructor("I1", "Կիրակոսյան");
        Instructor suqiasyan = new Instructor("I2", "Սուքիասյան");
        Instructor tonoyan = new Instructor("I3", "Տոնոյան");
        Instructor arabyan = new Instructor("I4", "Արաբյան");
        Instructor hakobyanH = new Instructor("I5", "Հակոբյան Հ.");
        Instructor petrosyan = new Instructor("I6", "Պետրոսյան");
        Instructor mkhitaryan = new Instructor("I7", "Մխիթարյան");
        Instructor hayrapetyan = new Instructor("I8", "Հայրապետյան");
        Instructor avagyan = new Instructor("I9", "Ավագյան");
        Instructor grigoryan = new Instructor("I10", "Գրիգորյան");
        Instructor serobyan = new Instructor("I11", "Սերոբյան");
        Instructor manukyan = new Instructor("I12", "Մանուկյան");
        Instructor nersisyan = new Instructor("I13", "Ներսիսյան");
        Instructor araqelyan = new Instructor("I14", "Առաքելյան");
        Instructor hakobyanS = new Instructor("I15", "Հակոբյան Ս.");
        Instructor vardanyan = new Instructor("I16", "Վարդանյան");
        Instructor miqayelyan = new Instructor("I17", "Միքայելյան");
        Instructor andreasyan = new Instructor("I18", "Անդրեասյան");
        Instructor xachatryan = new Instructor("I19", "Խաչատրյան");
        Instructor voskanyan = new Instructor("I20", "Ոսկանյան");
        Instructor martirosyan = new Instructor("I21", "Մարտիրոսյան");
        Instructor ghazaryan = new Instructor("I22", "Ղազարյան");
        Instructor eghiazaryan = new Instructor("I23", "Եղիազարյան");
        Instructor gabrielyan = new Instructor("I24", "Գաբրիելյան");
        Instructor falakyan = new Instructor("I25", "Ֆալակյան");
        Instructor muradyan = new Instructor("I26", "Մուրադյան");
        Instructor tutxalyan = new Instructor("I27", "Թութխալյան");
        Instructor sargsyan = new Instructor("I28", "Սարգսյան");
        Instructor bolibekyan = new Instructor("I29", "Բոլիբեկյան");
        Instructor aslam = new Instructor("I30", "Ասլամաջյան");
        instructors = Arrays.asList(
                kirakosyan,
                suqiasyan,
                tonoyan,
                arabyan,
                hakobyanH,
                petrosyan,
                mkhitaryan,
                hayrapetyan,
                avagyan,
                grigoryan,
                serobyan,
                manukyan,
                nersisyan,
                araqelyan,
                hakobyanS,
                vardanyan,
                miqayelyan,
                andreasyan,
                xachatryan,
                voskanyan,
                martirosyan,
                ghazaryan,
                eghiazaryan,
                gabrielyan,
                falakyan,
                muradyan,
                tutxalyan,
                sargsyan,
                aslam,
                bolibekyan
        );

        Group group201 = new Group( "201", 1, 25);
        Group group202 = new Group( "202", 1, 26);
        Group group203 = new Group( "203", 1, 27);
        Group group204 = new Group( "204", 1, 28);
        Group group205 = new Group( "205", 1, 28);
        Group group206 = new Group( "206", 1, 28);
        Group group207 = new Group( "207", 1, 28);
        Group group208 = new Group( "208", 1, 28);
        Group group209 = new Group( "209", 1, 28);
        Group group210 = new Group( "210", 1, 28);
        Group group211 = new Group( "211", 1, 28);
        Group group212 = new Group( "212", 1, 28);
        groups = Arrays.asList(
                group201,
                group202,
                group203
//                group204,
//                group205,
//                group206,
//                group207
//                group208,
//                group209,
//                group210,
//                group211,
//                group212
        );

        groups.forEach(group -> {
            Course course1 = new Course("C1", "ԷՀՄ", new ArrayList<>(Arrays.asList(manukyan, araqelyan, hakobyanS, andreasyan, martirosyan, tutxalyan)), group, 1);
            Course course2 = new Course("C2", "Մաթ Անալիզ", new ArrayList<>(Arrays.asList(arabyan, hakobyanH, vardanyan, miqayelyan, voskanyan)), group, 1);
            Course course3 = new Course("C3", "Անալիտիկ", new ArrayList<>(Arrays.asList(serobyan, gabrielyan, sargsyan, bolibekyan)), group, 1);
            Course course4 = new Course("C4", "Դիսկրետ", new ArrayList<>(List.of(petrosyan, xachatryan, eghiazaryan, ghazaryan, aslam)), group, 1);
            Course course5 = new Course("C5", "Լեզու", new ArrayList<>(Arrays.asList(kirakosyan, suqiasyan, tonoyan, nersisyan, falakyan)), group, 1);
//            Course course6 = new Course("C6", "Անգլերեն", new ArrayList<>(Arrays.asList(mkhitaryan, hayrapetyan, avagyan, grigoryan, muradyan)), group, 1);
            courses.addAll(Arrays.asList(course1, course2, course3, course4, course5));
        });
        Course course7 = new Course("C6", "Անգլերեն", new ArrayList<>(Arrays.asList(mkhitaryan, hayrapetyan, avagyan, grigoryan, muradyan)), null, 1, Arrays.asList(group201, group202, group203));
        courses.add(course7);

        System.out.println("coursescourses " + courses);

//        departments = Arrays.asList(
//                new Department("Անալիզ", new ArrayList<>(Arrays.asList(course3, course4))),
//                new Department("Դիսկրետ", new ArrayList<>(Arrays.asList(course5, course6))),
//                new Department("Ծրագրավորում", new ArrayList<>(Arrays.asList(course1, course3))),
//                new Department("Լեզուներ", new ArrayList<>(Arrays.asList(course7)))
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
