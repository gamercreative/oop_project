package project.core;

import java.util.ArrayList;

public abstract class Student {
    private int studentId;
    private String fullName;
    private String email;
    private String major;
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public Student(int studentId , String fullName , String email ,String major){
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.major = major;
    }

    public Student() {
        this.studentId = 0;
        this.fullName = "";
        this.email = "";
        this.major = "";
    }

    public String getMajor() {
        return this.major;
    }
    

    public String getEmail() {
        return this.email;
    }

    public int getEnrollmentCount() {
        return this.enrollments.size();
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public ArrayList<Enrollment>  getEnrollments(){
        return this.enrollments;   
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void AddCourse(Enrollment e){
        this.enrollments.add(e);

    }

    public void RemoveCourse(Enrollment e){
        this.enrollments.remove(e);
        
    }

    public void UpdateGrade(int courseId, double grade) {
       for (Enrollment e : this.enrollments) {
            if (e.getCourseId() == courseId) {
                e.UpdateGrade(grade);
            }
        }
    }

    public void UpdateAttendance(int courseId, double attendance) {
       for (Enrollment e : this.enrollments) {
            if (e.getCourseId() == courseId) {
                e.UpdateAttendance(attendance);;
            }
        }
    }

    public Enrollment getEnrollmentElement(int courseId) {
        for (Enrollment e : this.enrollments) {
            if (e.getCourseId() == courseId) {
                return e;
            }
        }

        return null;
    }

    public abstract double CalculateAverage();

    public void PrintStudentInfo() {
        System.out.println("StudentId: " + this.studentId + " FullName: " + this.fullName + " Email: " + this.email + " Major: " + this.major);
    }

    @Override
    public String toString() {
        return "StudentId: " + this.studentId + " FullName: " + this.fullName + " Email: " + this.email + " Major: " + this.major;
    }
    
}
