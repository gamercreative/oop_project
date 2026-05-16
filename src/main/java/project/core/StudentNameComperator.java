package project.core;

import java.util.Comparator;

public class StudentNameComperator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getFullName().compareToIgnoreCase(s2.getFullName());
    }
}
