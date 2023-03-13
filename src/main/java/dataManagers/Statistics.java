package dataManagers;

import tracker.Course;

import java.util.List;

public class Statistics {

    private List<Course> courses;

    public Statistics(List<Course> courses) {
        this.courses = courses;
    }

    public String getMostPopularAndHighestActivityCourse() {
        courses.sort(((o1, o2) -> o2.getEnrolledStudents() - o1.getEnrolledStudents()));

        int topEnrolled = courses.get(0).getEnrolledStudents();
        StringBuilder mostPopular = new StringBuilder();
        if(checkIfDataExists()) {
            for(Course course: courses) {
                if(course.getEnrolledStudents() != 0 && course.getEnrolledStudents() == topEnrolled) {
                    mostPopular.append(course.getCourseName()).append(", ");
                }
            }
            return mostPopular.substring(0, mostPopular.lastIndexOf(","));
        }
        else return "n/a";
    }

    public String getLeastPopularAndLowestActivityCourse() {
        courses.sort(((o1, o2) -> o2.getEnrolledStudents() - o1.getEnrolledStudents()));

        return courses.get(courses.size() - 1).getActivity() > 0  &&
                courses.get(courses.size() - 1).getActivity() > courses.get(0).getActivity() ?
                courses.get(courses.size() - 1).getCourseName() : "n/a";
    }

    public String getEasiestCourse() {
        courses.sort( ((o1, o2) -> o2.getAverageScore() - o1.getAverageScore()));

        if(checkIfDataExists() ) {
            return courses.get(0).getCourseName();
        }
        else return "n/a";
    }

    public String getHardestCourse() {
        courses.sort( ((o1, o2) -> o2.getAverageScore() - o1.getAverageScore()));

        if(checkIfDataExists()) {
            return courses.get(courses.size() - 1).getCourseName();
        }
        else return "n/a";
    }

    private boolean checkIfDataExists() {
        for(Course course: courses) {
            if(course.getEnrolledStudents() != 0) {
                return true;
            }
        }
        return false;
    }

}
