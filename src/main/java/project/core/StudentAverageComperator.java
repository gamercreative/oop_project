package project.core;

import java.util.Comparator;

public class StudentAverageComperator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.CalculateAverage(), s1.CalculateAverage());
    }
}
