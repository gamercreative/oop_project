package project.core;

import java.util.ArrayList;


public class ExchangeStudent extends Student{

    private String homeUniversity;
    private  int exchangePeriod;

    public ExchangeStudent(int studentId , String fullName , String email ,String major, String homeUniversity , int exchangePeriod){
        super(studentId, fullName , email , major);
        this.homeUniversity=homeUniversity;
        this.exchangePeriod=exchangePeriod; 
    }

    public ExchangeStudent() {
        
    }
    
    public String getHomeUniversity() {
    	return this.homeUniversity;
    }
    
    public int getExchanegPeriod() {
    	return this.exchangePeriod;
    	
    }

    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }

    public void setHomeUniversity(String homeUniversity) {
        this.homeUniversity = homeUniversity;
    }

    @Override
    public void setMajor(String major) {
        super.setMajor(major);
    }

    @Override
    public void setStudentId(int studentId) {
        super.setStudentId(studentId);
    }

    public void setExchangePeriod(int exchangePeriod) {
        this.exchangePeriod = exchangePeriod;
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
        System.out.println(super.toString() + " home_university " + this.homeUniversity + " exchange_period " + this.exchangePeriod);
    }
    
    

}
