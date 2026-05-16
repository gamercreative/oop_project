package project.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Graduate")
public class GraduateStudentEntity extends StudentEntity {
    private String thesisTitle;
    private String supervisorName;

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
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

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }
}
