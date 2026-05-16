package project.core;
	
import java.util.ArrayList;
import java.util.HashMap;

public class Report {

    private StudentSystem sys;
    
    public StudentSystem getSys() {            //added for file manager
    	return sys;
    }

    public Report(StudentSystem sys){
        this.sys = sys;
    }

    public Report() {
        this.sys = new StudentSystem();
    }

    public int getNumberOfStudents(){
        return this.sys.getStudents().size();
    }

    public int getNumberOfCourses(){
        return this.sys.getCourses().size();
    }
    
  

    public void AddStudent(Student s) {
        this.sys.addStudent(s);
    }

    public void AddCourse(Course c) {
        this.sys.addCourse(c);
    }

    public void UpdateGrade(int studentId, int courseId, double grade) {
        this.sys.UpdateGrade(studentId, courseId, grade);
    }

    public void EnrollStudent(int studentId, int courseId) {
        this.sys.EnrollStudent(studentId, courseId);
    }

    public HashMap<Integer, Double> getAverageGradePerCourse(){
        HashMap<Integer, Double> set = new HashMap<>();
        for(Course c : this.sys.getCourses()) {
            set.put(c.getCourseId(), c.getAverageGrade());
        }

        return set;
    }

    public Student getTopStudent() {
        double max = Double.MIN_VALUE;
        double temp;
        Student topStudent = null;

        for(Student s : this.sys.getStudents()) {
            temp = s.CalculateAverage();
            if ( max < temp) {
                max = temp;
                topStudent = s;
            }
        }

        return topStudent;
    }

    public ArrayList<Student> getStudentsByCourse(int courseId) {
        return this.sys.FindCourse(courseId).getStudents();
    }

    public HashMap<Integer, ArrayList<Student>> getStudentsByCourses() {
        HashMap<Integer, ArrayList<Student>> set = new HashMap<>();
        for(Course c : this.sys.getCourses()) {
            set.put(c.getCourseId(), c.getStudents());
        }

        return set;
    }

    public void PrintStudents() {
        this.sys.PrintStudents();
    }

    public void PrintCourses() {
        this.sys.PrintCourses();
    }

    public void PrintReport() {
        int numberOfStudents = this.getNumberOfStudents();
        int numberOfCourses = this.getNumberOfCourses();
        HashMap<Integer,Double> averagePerCourse = this.getAverageGradePerCourse();
        Student s = this.getTopStudent();
        HashMap<Integer, ArrayList<Student>> studentsPerCourse = this.getStudentsByCourses();

        System.out.println("Report");

        System.out.println("number of students is " + numberOfStudents);
        System.out.println("number of courses is " + numberOfCourses);

        if (averagePerCourse.isEmpty()) {
            System.out.println("there are no students enrolled in any class");
        } else {
            for(int id : averagePerCourse.keySet()) {
                Course c = this.sys.FindCourse(id);
                System.out.println("for course " + c.getName() + " the average is " + averagePerCourse.get(id));
            }
        }

        if (s == null) {
            System.out.println("no top student present");
        } else {
            System.out.println("the top student is " + s.toString());
        }
        
        if (studentsPerCourse.isEmpty()) {
            System.out.println("there are no students enrolled in any class");
            
        } else {
            for(int id : studentsPerCourse.keySet()) {
                Course c = this.sys.FindCourse(id);
                System.out.println("the students in class " + c.getName() + " are: ");
                for (Student student : studentsPerCourse.get(id)) {
                    student.PrintStudentInfo();
                }
            }
        }
    }
}


