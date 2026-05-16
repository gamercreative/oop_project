package project.core;

import java.util.ArrayList;

public class GraduateStudent extends Student {
    
    private String thesisTitle;
    private String supervisorName;

    public GraduateStudent() {

    }

    public GraduateStudent(int studentId, String fullName, String email , String major ,String thesisTitle , String supervisorName){
        super(studentId, fullName , email , major);
        this.thesisTitle = thesisTitle;
        this.supervisorName=supervisorName;
    }
    
    public String getThesisTitle() {
    	return this.thesisTitle;
    }
    
    public String getSupervisorName() {
    	return this.supervisorName;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setName(String name) {
        super.setFullName(name);
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
        System.out.println(super.toString() + " thesis_title " + this.thesisTitle + " supervisor_name " + this.supervisorName);
    }

}
