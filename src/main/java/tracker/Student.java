package tracker;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int [] points;
    List<String> completedCourses;

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        points = new int[4];
        completedCourses = new ArrayList<>();
    }

    public void setPoints(int[] arrPoints) {
        int i = 0;
        points[i] += arrPoints[i++];
        points[i] += arrPoints[i++];
        points[i] += arrPoints[i++];
        points[i] += arrPoints[i++];
    }

    @Override
    public String toString() {
        return String.format("%d points: Java=%d DSA=%d Databases=%d Spring=%d\n",
                id, points[0], points[1], points[2], points[3]);
    }

}
