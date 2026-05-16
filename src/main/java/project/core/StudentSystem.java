package project.core;

import java.util.ArrayList;

public class StudentSystem {
    
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Enrollment> enrollments;

    public StudentSystem() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    public StudentSystem(ArrayList<Student> students, ArrayList<Course> courses, ArrayList<Enrollment> enrollments) {
        this.students = students;
        this.courses = courses;
        this.enrollments = enrollments;
    }

    public void addStudent(Student s){
        this.students.add(s);
    }

    public void addCourse(Course c){
        this.courses.add(c);
    }

    public ArrayList<Student>  getStudents(){
        return this.students;
    }

    public ArrayList<Course>  getCourses(){
        return this.courses;
        
    }
    public ArrayList<Enrollment>  getEnrollments(){
        return this.enrollments;
        
    }

    public Student FindStudent(int studentId){
    	
    	if(this.students.isEmpty()) {
    		return null;
    	}
        for(int i = 0 ; i < this.students.size();i++){
            if(this.students.get(i).getStudentId() == studentId){
                return students.get(i);
            }
        }

        return null;
    }
    

    public Course FindCourse(int courseId){
    	
    	if(this.courses.isEmpty()) {
    		return null;
    	}
        for(int i = 0 ; i < this.courses.size();i++){
            if(this.courses.get(i).getCourseId() == courseId){
                return courses.get(i);
            }
        }

        return null;
    }

    public Enrollment FindAttendance(int enrollmentId){
    	
    	if(this.enrollments.isEmpty()) {
    		return null;
    	}
        for(int i = 0 ; i < this.enrollments.size();i++){
            if(this.enrollments.get(i).getEntrollmentId() == enrollmentId){
                return enrollments.get(i);
            }
        }

        return null;
    }

    public void EnrollStudent(int studentId, int courseId) {
        Course c = this.FindCourse(courseId);
        Student s = this.FindStudent(studentId);

        Enrollment e = new Enrollment(courseId, s, c, 0.0, 0.0);
        s.AddCourse(e);
        c.AddStudent(s);

        this.enrollments.add(e);
    }

    public void UpdateGrade(int studentId, int courseId, double grade) {
        Student s = this.FindStudent(studentId);

        s.UpdateGrade(courseId, grade);

        for (Enrollment e : this.enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId) {
                e.UpdateGrade(grade);
            }
        }
    }

    public void UpdateAttendance(int studentId, int courseId, double grade) {
        Student s = this.FindStudent(studentId);

        s.UpdateAttendance(courseId, grade);

        for (Enrollment e : this.enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId) {
                e.UpdateAttendance(grade);
            }
        }
    }

    public ArrayList<Student> filterStudents(StudentFilter filter) {

        ArrayList<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (filter.apply(student)) {
                result.add(student);
            }
        }

        return result;
    }

    public void PrintStudents() {
        for(Student s : this.students) {
            s.PrintStudentInfo();
        }
    }

    public void PrintCourses() {
        for(Course c : this.courses) {
            c.CourseSummary();
        }
    }
}
