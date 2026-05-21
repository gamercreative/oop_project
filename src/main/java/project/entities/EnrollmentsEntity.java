package project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="enrollments", uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "course_id"})})
public class EnrollmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    private double attendance;
    private double grade;

    public EnrollmentsEntity() {
        
    }

    public double getAttendance() {
        return attendance;
    }

    public double getGrade() {
        return grade;
    }
    
    public CourseEntity getCourse() {
        return course;
    }
    
    public StudentEntity getStudent() {
        return student;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    

}
