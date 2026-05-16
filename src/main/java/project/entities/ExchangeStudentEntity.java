package project.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Exchange")
public class ExchangeStudentEntity extends StudentEntity {
    private String homeUniversity;
    private  int exchangePeriod;

    public void setHomeUniversity(String homeUniversity) {
        this.homeUniversity = homeUniversity;
    }

    public void setExchangePeriod(int exchangePeriod) {
        this.exchangePeriod = exchangePeriod;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public int getExchangePeriod() {
        return exchangePeriod;
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    public String getHomeUniversity() {
        return homeUniversity;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getMajor() {
        return super.getMajor();
    }
}
