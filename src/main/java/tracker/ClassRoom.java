package tracker;

import dataManagers.Statistics;
import dataManagers.StudentValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRoom {
    Map<Integer, Student> students;
    int currId;
    Statistics statistics;
    List<Course> courses = new ArrayList<>();

    public ClassRoom() {
        initCourses();
        students = new HashMap<>();
        currId = 10000;
        statistics = new Statistics(courses);
    }

    public void initCourses() {
        courses.add(new Course("Java", 0, 0, 0, 600));
        courses.add(new Course("DSA", 0, 0, 0, 400));
        courses.add(new Course("Databases", 0, 0, 0, 480));
        courses.add(new Course("Spring", 0, 0, 0, 550));
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    private int nextId() {
        return currId++;
    }


    public boolean addStudent(String input) {
        String [] inputArr = input.split(" ");

        if(inputArr.length < 3) System.out.println("Incorrect credentials.");

        else {
            String firstName = inputArr[0];
            String lastName = input.substring(input.indexOf(" "),
                    input.lastIndexOf(" ")).trim();
            String email = inputArr[inputArr.length - 1];

            Student student = createStudent(firstName, lastName, email);

            if(student != null) {
                students.put(student.getId(), student);
                return true;
            }
        }
        return false;
    }

    private Student createStudent(String firstName, String lastName, String email) {

        if(!StudentValidator.validateFirstName(firstName)) {
            System.out.println("Incorrect first name.");
            return null;
        }
        if(!StudentValidator.validateLastName(lastName)) {
            System.out.println("Incorrect last name.");
            return null;
        }
        if(!StudentValidator.validateEmail(email)) {
            System.out.println("Incorrect email.");
            return null;
        }

        if(!checkEmailIsNotUsed(email)) {
            System.out.println("This email is already taken");
            return null;
        }

        System.out.println("The student has been added.");
        return new Student(nextId(), firstName, lastName, email);

    }

    public void find(String studentId) {
        int id = Integer.parseInt(studentId);

        if (students.containsKey(id))
            System.out.println(students.get(id).toString());
        else {
            System.out.printf("No student is found for id=%d\n", id);
        }
    }

    public void addPoints(String input) {
        String [] data = input.split(" ");

        int id;
        Student student;

        try {
            id = Integer.parseInt(data[0]);
            student = students.get(id);
        } catch (Exception e) {
            System.out.printf("No student is found for id=%s\n", data[0]);
            return;
        }

        if (!StudentValidator.validatePointsFormat(input)) {
            System.out.println("Incorrect points format.");
            return;
        }

        int[] arrPoints = new int[4];
        for (int i = 1; i < data.length; i++) {
            arrPoints[i - 1] = Integer.parseInt(data[i]);

            if(arrPoints[i-1] > 0 && students.get(id).getPoints()[i-1] == 0) {
                courses.get(i-1).addEnrolledStudent();
                courses.get(i-1).addActivity(arrPoints[i-1]);
            }
            else {
                courses.get(i-1).addActivity(arrPoints[i-1]);
            }
        }

        student.setPoints(arrPoints);
        System.out.println("Points updated.");

    }

    private boolean checkEmailIsNotUsed(String email) {
        for (var student : students.entrySet()) {
            if (email.equals(student.getValue().getEmail()))
                return false;
        }
        return true;
    }

    public String getMostPopularCourse() {
        return statistics.getMostPopularAndHighestActivityCourse();
    }

    public String getLeastPopularCourse() {
        return statistics.getLeastPopularAndLowestActivityCourse();
    }

    public String getHighestActivity() {
        return statistics.getMostPopularAndHighestActivityCourse();
    }

    public String getLowestActivity() {
        return statistics.getLeastPopularAndLowestActivityCourse();
    }

    public String getEasiestCourse() {
        return statistics.getEasiestCourse();
    }

    public String getHardestCourse() {
        return statistics.getHardestCourse();
    }

    public void showCourseDetails(String course) {
        System.out.printf("%s\nid    points    completed\n", course);

        Course courseToShow = courses.stream()
                .filter(c -> c.getCourseName()
                        .toLowerCase()
                        .equals(course))
                .findAny()
                .orElse(null);

        int id = courses.indexOf(courseToShow);

        List<Student> studentList = new ArrayList<>(students.values());

        studentList.sort((s1, s2) -> s2.getPoints()[id] - s1.getPoints()[id]);

        for (Student student : studentList) {
            if (student.getPoints()[id] > 0) {
                System.out.printf("%d %d    %.1f%s\n", student.getId(), student.getPoints()[id],
                        ((double) student.getPoints()[id]) / courseToShow.getMaxPoints() * 100, "%");
            }
        }
    }

    public int notifyStudents() {
        int notifiedStudents = 0;
        for(Student student: students.values()) {
            boolean hasNotified = false;

            for(int i = 0; i < student.getPoints().length; i++) {
                if(courses.get(i).getMaxPoints() == student.getPoints()[i]
                        && !student.getCompletedCourses().contains(courses.get(i).getCourseName())) {

                    System.out.printf("To: %s\nRe: Your Learning Progress\n" +
                                    "Hello, %s! You have accomplished our %s course!\n",
                            student.getEmail(), student.getFirstName() + " " + student.getLastName(),
                            courses.get(i).getCourseName());

                    hasNotified = true;
                    student.getCompletedCourses().add(courses.get(i).getCourseName());
                }
            }
            if(hasNotified) notifiedStudents++;
        }

        return notifiedStudents;
    }

}
