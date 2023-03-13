package tracker;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private String courseName;
    private int enrolledStudents;
    private int activity;
    private int averageScore;
    private int maxPoints;

    public Course(String courseName, int enrolledStudents, int activity, int averageScore, int maxPoints) {
        this.courseName = courseName;
        this.enrolledStudents = enrolledStudents;
        this.activity = activity;
        this.averageScore = averageScore;
        this.maxPoints = maxPoints;
    }

    public void addEnrolledStudent() {
        this.enrolledStudents += 1;
    }

    public void addActivity(int activity) {
        this.activity += activity;

        if(this.enrolledStudents != 0) {
            this.averageScore = this.activity / enrolledStudents;
        }

    }

}
