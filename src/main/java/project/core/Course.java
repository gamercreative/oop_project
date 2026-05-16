package project.core;

import java.util.ArrayList;

public class Course {
	private int courseId;
	private int credits;
	private String couresName;
	private String instructorName;
	private ArrayList<Student> students = new ArrayList<>();

	public Course() {
		this.courseId = 0;
		this.credits = 0;
		this.couresName = "";
		this.instructorName = "";
	}

	public Course(int courseId, int credits, String courseName, String instructorName) {
		this.courseId = courseId;
		this.credits = credits;
		this.couresName = courseName;
		this.instructorName = instructorName;
	}

	public int getCourseId(){
		return this.courseId;
	}

	public String getName() {
		return this.couresName;
	}

	public String getInstructorName() {
		return this.instructorName;
	}

	public int getStudentCount() {
		return this.students.size();
	}

	public int getCredits() {
		return this.credits;
	}

	public String getCouresName() {
		return couresName;
	}

    public void setCouresName(String couresName) {
        this.couresName = couresName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
		
	
	public void AddStudent(Student s) {
		this.students.add(s);
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public void RemoveStudent(Student s) {
		this.students.remove(s);
	}

	public double getAverageGrade() {
		double total = 0.0;
		for(Student s : this.students) {
			total += s.getEnrollmentElement(this.courseId).getGrade();
		}
		double average = total / (this.students.isEmpty() ? 1 : this.students.size());

		return average;
	}

	public void PrintStudentsEnrolled() {
		System.out.println("The students  ");
		for (Student s : this.students) {
			s.PrintStudentInfo();
		}
	}

	public void CourseSummary() {
		System.out.println("Course Summary");
		System.out.println("CourseID: " + this.courseId + " CourseCredits: " + this.credits + " CourseName: " + this.couresName + " InstructorName: " + this.instructorName);
	}

	public void CourseSummaryWithStudents() {
		this.CourseSummary();
		this.PrintStudentsEnrolled();
	}

}
