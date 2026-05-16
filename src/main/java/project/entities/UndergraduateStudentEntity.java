package project.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("UnderGraduate")
public class UndergraduateStudentEntity extends StudentEntity {
    private int yearLevel;

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getMajor() {
        return super.getMajor();
    }

    public int getYearLevel() {
        return yearLevel;
    }
    
}
