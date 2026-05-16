package project.core;

public class Enrollment { 
    private int entrollmentId;
    private Student student;
    private Course course;
    private double grade;
    private double attendance;

    public Enrollment() {
        this.entrollmentId = 0;
        this.grade = 0.0;
        this.attendance = 0;
    }

    public Enrollment(int enrollmentId, Student student, Course course, double grade, double attendance) {
        this.entrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.attendance = attendance;
    }

    public int getEntrollmentId() {
        return entrollmentId;
    }

    public double getGrade(){
        return this.grade;
    }

    public int getStudentId() {
        return this.student.getStudentId();
    }

    public int getCourseId() {
        return this.course.getCourseId();
    }

    public void setStudentId(int id) {
        this.student.setStudentId(id);
    }

    public void setCourseId(int id) {
        this.course.setCourseId(id);;
    }

    public void UpdateGrade(double grade) {
        this.grade = grade;
    } 
    
    public double getAttendance() {
        return this.attendance;
    } 

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEntrollmentId(int entrollmentId) {
        this.entrollmentId = entrollmentId;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void UpdateAttendance(double attendance) {
        this.attendance = attendance;
    }

    public boolean IsPassed() {
        return this.grade > 12;
    }

    public void PrintEnrollment(){
        System.out.println("Enrollmentid: "+ this.entrollmentId);
        this.student.PrintStudentInfo();
        this.course.CourseSummary();
        System.out.println("grade: "+ this.grade + " attendance: " + this.attendance + " passed: " + (this.IsPassed() ? "yes" : "no"));
    }
}
