package project.core;

import java.util.ArrayList;

public class UndergraduateStudent extends Student {

    private int yearLevel;

    public UndergraduateStudent(int studentId, String fullName, String email , String major,int yearLevel) {
        super(studentId, fullName ,email , major);
        this.yearLevel = yearLevel;
    }

    public UndergraduateStudent() {
    
    }
    
    public int getYearLevel() {
    	return this.yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public void setStudentId(int studentId) {
        super.setStudentId(studentId);
    }

    @Override
    public void setMajor(String major) {
        super.setMajor(major);
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    @Override
    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        super.setEnrollments(enrollments);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public double CalculateAverage(){
        double total = 0.0;
        for(Enrollment e: this.getEnrollments()) {  
            total += e.getGrade();
        }
        double average = total / (this.getEnrollmentCount() == 0 ? 1 : this.getEnrollmentCount());
        return average;
    }

    @Override
    public void PrintStudentInfo() {
        // ends in major
        System.out.println(super.toString() + " year level " + this.yearLevel);
    }
    
    
}
