package project.core;

import java.util.Scanner;

public class Utils {
    
    public static void PrintMainMenu() {
        System.out.println("""
            1. ADD STUDENT
            2. ADD COURSE
            3. ENROLL STUDENT
            4. UPDATE GRADE
            5. SHOW STUDENTS
            6. SHOW COURSES
            7. SHOW REPORT
            8. SAVE DATA
            9. LOAD DATA
            10. SAVE REPORT
            11. EXIT
        """);
    }

    public static void PrintAddStudentMenu() {
        System.out.println("""
                1. Add Undergraduate student
                2. Add Graduate student
                3. Add Exchange student
        """);
    }

    public static ExchangeStudent FillExchangeStudent(Scanner sc) {
    	int studentId;  //added reem
        String fullName;
        String email;
        String major;
        String homeUniversity;
        int exchangePeriod;

        System.out.println("Fill Exchange Student Info");
     
        System.out.println("Enter student ID");
        studentId = sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Enter exchange Student full name");
        fullName = sc.nextLine();

        System.out.println("Enter exchange student email");
        email = sc.nextLine();
        
        System.out.println("Enter exchange student major");
        major = sc.nextLine();

        System.out.println("Enter exchange student home university");
        homeUniversity = sc.nextLine();

        System.out.println("Enter exchange student exchange period");
        exchangePeriod = sc.nextInt();
        sc.nextLine();

        return new ExchangeStudent(studentId, fullName, email, major, homeUniversity, exchangePeriod);
    }

    public static UndergraduateStudent FillUndergraduateStudent(Scanner sc) {
    	int studentId; //added reem
        String fullName;
        String email;
        String major;
        int yearLevel;

        System.out.println("Fill Undergraduate Student Info");
        
        System.out.println("Enter student ID");
        studentId = sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Enter Undergraduate Student full name");
        fullName = sc.nextLine();

        System.out.println("Enter Undergraduate student email");
        email = sc.nextLine();
        
        System.out.println("Enter Undergraduate student major");
        major = sc.nextLine();

        System.out.println("Enter Undergraduate university year");
        yearLevel = sc.nextInt();
        sc.nextLine();

        return new UndergraduateStudent(studentId, fullName, email, major, yearLevel);
    }

    public static GraduateStudent FillGraduateStudent(Scanner sc) {
    	int studentId; //added reem
        String fullName;
        String email;
        String major;
        String thesisTitle;
        String supervisorName;

        System.out.println("Fill Graduate Student Info");
        
        System.out.println("Enter student ID");
        studentId = sc.nextInt();
        
        sc.nextLine();
        
        System.out.println("Enter graudate Student full name");
        fullName = sc.nextLine();

        System.out.println("Enter graudate student email");
        email = sc.nextLine();
        
        System.out.println("Enter graudate student major");
        major = sc.nextLine();

        System.out.println("Enter graudate student thesis title");
        thesisTitle = sc.nextLine();

        System.out.println("Enter graudate student supervisor name");
        supervisorName = sc.nextLine();
   
        return new GraduateStudent(studentId, fullName, email, major, thesisTitle, supervisorName);
    }

    public static Course FillCourse(Scanner sc) {
    	int courseId; // added reem
        int credits;
        String courseName;
        String instructorName;

        System.out.println("Fill Course Info");
        
        System.out.println("Enter course ID");
        courseId = sc.nextInt();
        
        System.out.println("Enter course credits");
        credits = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter course name");
        courseName = sc.nextLine();
        
        System.out.println("Enter course instructor name");
        instructorName = sc.nextLine();

        return new Course(courseId, credits, courseName, instructorName);
    }

    public static void EnrollStudent(Report report, Scanner sc) {
        int studentId;
        int courseId;

        System.out.println("enter student id");
        studentId = sc.nextInt();

        System.out.println("enter course id");
        courseId = sc.nextInt();
        sc.nextLine();
        
        report.EnrollStudent(studentId, courseId);
    }

    public static void UpdateGrade(Report report, Scanner sc) {
        int studentId;
        int courseId;
        double grade;

        System.out.println("enter student id");
        studentId = sc.nextInt();

        System.out.println("enter course id");
        courseId = sc.nextInt();
        
        System.out.println("enter course grade");
        grade = sc.nextDouble();
        sc.nextLine();
        
        report.UpdateGrade(studentId, courseId, grade);
    }

    public static Student HandleStudentMenu(Scanner sc) {
        int choice;
        PrintAddStudentMenu();
        
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                return FillUndergraduateStudent(sc);

            case 2:
                return FillGraduateStudent(sc);

            case 3:
                return FillExchangeStudent(sc);

            default:
                throw new AssertionError();
        }
    }

    public static void HandleMainMenu(Report report, Scanner sc, int choice, Repo repo) {
        StudentSystem sys = report.getSys();

        switch (choice) {
            case 1:
                Student s = HandleStudentMenu(sc);
                report.AddStudent(s);
                break;

            case 2:
                Course c = FillCourse(sc);
                report.AddCourse(c);
                break;

            case 3:
                EnrollStudent(report, sc);
                System.out.println(
                    sys.getEnrollments().size()
                );

                break;

            case 4:
                UpdateGrade(report, sc);
                break;

            case 5:
                report.PrintStudents();
                break;

            case 6:
                report.PrintCourses();
                break;

            case 7:
                report.PrintReport();
                break;

            case 8:
                repo.SaveData(sys); // added reem
                break;
                
            case 9:
                repo.LoadData(sys); //added reem
                break;

            case 10: 
                repo.SaveReport(sys);
                break;
           
            case 11:
                sc.close();
            	repo.Close();
            	return;
                      

            default:
                throw new AssertionError();
        }
    }
}
