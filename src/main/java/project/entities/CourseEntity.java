package project.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // reem: "strategy"
    private int courseId;

    private int credits;
    private String courseName;
    private String InstructorName;

    @OneToMany(mappedBy = "course")
    private List<EnrollmentsEntity> enrollments;

    public CourseEntity() {

    }

    public CourseEntity(int credits, String courseName, String instructorName) {
        this.credits = credits;
        this.courseName = courseName;
        this.InstructorName = instructorName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructorName(String InstructorName) {
        this.InstructorName = InstructorName;
    }

    public void setEnrollments(List<EnrollmentsEntity> enrollments) {
        this.enrollments = enrollments;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public List<EnrollmentsEntity> getEnrollments() {
        return enrollments;
    }

    public String getInstructorName() {
        return InstructorName;
    }
}
