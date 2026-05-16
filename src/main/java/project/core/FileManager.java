package project.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager implements Repo {
    String path;
    String reportPath;

    public FileManager(String path, String reportPath) {
        this.path = path;
        this.reportPath = reportPath;
    }

    @Override
    public void SaveData(StudentSystem sys) {
        
    	try {
    		FileWriter fileName = new FileWriter(path);
    		BufferedWriter buff = new BufferedWriter(fileName);
    		
            this.SaveStudents(sys.getStudents(), buff); // s...
            buff.write("");
            this.SaveCourses(sys.getCourses(), buff); // c...
            buff.write("");
            this.SaveEnrollments(sys.getEnrollments(), buff); // e...

            buff.close();
            
            System.out.println("Data Saved Successfully");

        }

        catch(IOException e) {

            System.out.println("Error While Saving File " + e);

        }
    }

    private  void SaveCourses(ArrayList<Course> courses, BufferedWriter buff) throws IOException { 
        for(Course c : courses) {
            buff.write("c" + " , " + c.getCourseId() + " , " + c.getName() + " , " + c.getInstructorName() + " , " + c.getCredits());
            
            buff.newLine();
        }
    }

    private void SaveStudents(ArrayList<Student> students, BufferedWriter buff) throws IOException { 
        for(Student s : students) {
            String type;
            String base;
            
            if(s.getClass().getSimpleName().equals("UndergraduateStudent")) {   //“What class is this object REALLY from?”
                type = "Undergraduate";
                UndergraduateStudent u = (UndergraduateStudent) s;
                base = "s" + " , " + type + " , " + s.getStudentId() + " , "+ s.getFullName() + " , " + s.getEmail() +" , "+ s.getMajor();
                
                buff.write(base + " , "+ u.getYearLevel());
            }

            else if(s.getClass().getSimpleName().equals("GraduateStudent")) {
                type = "Graduate";
                GraduateStudent g = (GraduateStudent) s;
                base = "s" + " , " + type + " , " + s.getStudentId() + " , "+ s.getFullName() + " , " + s.getEmail() +" , "+ s.getMajor();

                buff.write(base + " , "+g.getThesisTitle() +" , "+ g.getSupervisorName());
            }

            else if(s.getClass().getSimpleName().equals("ExchangeStudent")) {
                type = "Exchange";
                ExchangeStudent x = (ExchangeStudent) s;
                base = "s" + " , " + type + " , " + s.getStudentId() + " , "+ s.getFullName() + " , " + s.getEmail() +" , "+ s.getMajor();

                buff.write(base +" , "+ x.getHomeUniversity() +" , "+ x.getExchanegPeriod());
            }
            
            buff.newLine();
        }
    }

    private void SaveEnrollments(ArrayList<Enrollment> enrollements, BufferedWriter buff) throws IOException { 
        for(Enrollment e : enrollements) {
            buff.write("e" + " , " + e.getEntrollmentId() +  " , "  + e.getStudentId() + " , " + e.getCourseId() + " , " + e.getGrade() + " , " + e.getAttendance());
            
            buff.newLine();
        }
    }
 
    @Override
    public void LoadData(StudentSystem sys) {
    	try {
            FileReader fileName = new FileReader(path);
            BufferedReader buff = new BufferedReader(fileName);
            
            String line;
            
            while((line = buff.readLine()) != null) {
                String[] data = line.split(" , ");
                
                switch (data[0].strip()) {
                    case "s":
                        LoadStudents(sys, data);
                        break;

                    case "c":
                        LoadCourses(sys, data);
                        break;

                    case "e":
                        LoadEnrollments(sys, data);
                        break;

                    default:
                        System.out.println("error was found in file skipped line");
                        break;
                }
            }

            buff.close();

            System.out.println("Data Loaded Successfully");
        }
    	

    	catch(IOException e) {
            System.out.println("Error While Loading File");
        }

        catch(NumberFormatException e) {
            System.out.println("Invalid Data Format " + e);
        }

        catch(Exception e) {
            System.out.println("error " + e);
        }
    }
    
    private void LoadStudents(StudentSystem sys, String[] data) {
        String type = data[1].strip();
        int studentId = Integer.parseInt(data[2].strip());
        String fullName = data[3].strip();
        String email = data[4].strip();
        String major = data[5].strip();

        Student s = null;
        
        if(type.equalsIgnoreCase("Undergraduate")) {    
            int yearLevel = Integer.parseInt(data[6].strip());
                s = new UndergraduateStudent(studentId ,fullName, email, major, yearLevel);
        }
        
        else if (type.equalsIgnoreCase("Graduate")) {
            String thesisTitle = data[6].strip();
            String supervisorName = data[7].strip();
            s = new GraduateStudent(studentId ,fullName, email, major, thesisTitle , supervisorName);
        
        }
        
        else if(type.equalsIgnoreCase("Exchange")) {
            String homeUniversity = data[6].strip();
            int exchangePeriod = Integer.parseInt(data[7].strip());
            s = new ExchangeStudent(studentId ,fullName, email, major, homeUniversity, exchangePeriod);
        }

        if(sys.FindStudent(studentId) == null) {
            sys.addStudent(s);
        }
    }

    private void LoadCourses(StudentSystem sys, String[] data) {
        int courseId = Integer.parseInt(data[1].strip());
        String courseName = data[2].strip();
        String instructorName = data[3].strip();
        int credits = Integer.parseInt(data[4].strip());

        Course c = new Course(courseId, credits,courseName, instructorName);

        if (sys.FindCourse(courseId) == null) {
            sys.addCourse(c);
        }
    }

    private void LoadEnrollments(StudentSystem sys, String[] data) {
        int enrollmentId = Integer.parseInt(data[1].strip());
        int studentId = Integer.parseInt(data[2].strip());
        int coursetId = Integer.parseInt(data[3].strip());
        double grade = Double.parseDouble(data[4].strip());
        double attendance = Double.parseDouble(data[5].strip());

        if (sys.FindAttendance(enrollmentId) == null) {
            sys.EnrollStudent(studentId, coursetId);
            sys.UpdateGrade(studentId, coursetId, grade);
            sys.UpdateAttendance(studentId, coursetId, attendance);
        }
    }

    @Override
    public void SaveReport(StudentSystem sys) {
        try {
            FileWriter fileName = new FileWriter(reportPath);
            BufferedWriter buff = new BufferedWriter(fileName);
    
            for(Student s : sys.getStudents()) {

                for(Enrollment e : s.getEnrollments()) {
                    String type= "";

                    if(s.getClass().getSimpleName().equals("UndergraduateStudent")) {   //“What class is this object REALLY from?”
                        type = "Undergraduate";
                        UndergraduateStudent u = (UndergraduateStudent) s;
                        buff.write(type + "," + s.getStudentId() + ","+ s.getFullName() + "," + s.getEmail() +","+ s.getMajor() + ","+ e.getCourseId()+","+ e.getGrade()+","+ e.getAttendance() +","+ u.getYearLevel());
                                
                    }

                    else if(s.getClass().getSimpleName().equals("GraduateStudent")) {
                        type = "Graduate";
                        GraduateStudent g = (GraduateStudent) s;
                        buff.write(type + "," + s.getStudentId() + ","+ s.getFullName() + "," + s.getEmail() +","+ s.getMajor() + ","+ e.getCourseId()+","+ e.getGrade()+","+ e.getAttendance()+","+g.getThesisTitle() +","+ g.getSupervisorName());
                    }

                    else if(s.getClass().getSimpleName().equals("ExchangeStudent")) {
                        type = "Exchange";
                        ExchangeStudent x = (ExchangeStudent) s;

                        buff.write(type + "," + s.getStudentId() + ","+ s.getFullName() + "," + s.getEmail() +","+ s.getMajor() + ","+ e.getCourseId()+","+ e.getGrade()+","+ e.getAttendance() +","+ x.getHomeUniversity() +","+ x.getExchanegPeriod());
                    }
                    
                    buff.newLine();
                }
            
            buff.newLine();
        }
    
            buff.close();
            System.out.println("Report Saved Successfully");
            
        } catch (Exception e) {
            System.out.println("error while saving report " + e);
        }

    }

    public void Close() {
        System.out.println("closed file");
    }
}
    


            
               
    	
   
       

