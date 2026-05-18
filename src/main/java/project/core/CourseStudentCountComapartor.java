package project.core;

import java.util.Comparator;

public class CourseStudentCountComapartor implements Comparator<Course> {

	    @Override
	    public int compare(Course c1, Course c2) {
	        return Integer.compare(c2.getStudentCount(), c1.getStudentCount());
	    }
	}

