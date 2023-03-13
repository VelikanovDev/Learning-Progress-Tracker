package tracker;

import dataManagers.Utils;

public class Tracker {
    final static String EXIT = "exit";
    final static String BACK = "back";
    final static String ADD = "add students";
    final static String LIST = "list";
    final static String FIND = "find";
    final static String ADD_P = "add points";
    final static String STAT = "statistics";
    final static String NOTIFY = "notify";

    private static ClassRoom classRoom;

    public Tracker() {
        classRoom = new ClassRoom();
    }

    public void start() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            switch (Utils.getLine()) {
                case "" -> System.out.println("No input.");
                case EXIT -> {
                    System.out.println("Bye!");
                    return;
                }
                case BACK -> System.out.println("Enter 'exit' to exit the program.");
                case ADD -> addStudents();
                case LIST -> showStudents();
                case FIND -> findStudents();
                case ADD_P -> addPoints();
                case STAT -> getStats();
                case NOTIFY -> notifyStudents();
                default -> System.out.println("Unknown command!");
            }
        }
    }

    private static void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        int count = 0;

        while (true) {
            String input = Utils.getLine();

            if(input.equals(BACK)) {
                System.out.printf("Total %d students have been added.\n", count);
                return;
            }
            else {
                if(classRoom.addStudent(input)) count++;
            }
        }
    }

    private static void showStudents() {

        if(classRoom.getStudents().size() == 0) {
            System.out.println("No students found");
            return;
        }

        System.out.println("Students:");
        for(var student: classRoom.getStudents().entrySet()) {
            System.out.println(student.getKey());
        }
    }

    private static void findStudents() {
        System.out.println("Enter an id or 'back' to return");

        while (true) {
            String input = Utils.getLine();

            if (input.equals(BACK)) {
                return;
            } else {
                classRoom.find(input);
            }
        }
    }

    private static void addPoints() {
        System.out.println("Enter an id and points or 'back' to return");

        while (true) {
            String input = Utils.getLine();

            if (input.equals(BACK)) {
                return;
            }

            else {
                classRoom.addPoints(input);
            }
        }
    }

    private static void getStats() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");

        System.out.println("Most popular: " + classRoom.getMostPopularCourse());
        System.out.println("Least popular: " + classRoom.getLeastPopularCourse());
        System.out.println("Highest activity: " + classRoom.getHighestActivity());
        System.out.println("Lowest activity: " + classRoom.getLowestActivity());
        System.out.println("Easiest course: " + classRoom.getEasiestCourse());
        System.out.println("Hardest course: " + classRoom.getHardestCourse());

        while (true) {
            String input = Utils.getLine().toLowerCase();

            if(input.equals(BACK)) {
                return;
            }

            switch (input) {
                case "java", "dsa", "databases", "spring" -> classRoom.showCourseDetails(input);
                default -> System.out.println("Unknown course.");
            }
        }
    }

    public static void notifyStudents() {
        System.out.printf("Total %d students have been notified.", classRoom.notifyStudents());
    }

}
