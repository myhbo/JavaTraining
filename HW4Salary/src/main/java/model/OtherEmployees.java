package model;

import java.util.Date;

public class OtherEmployees extends Employee {
    private String name;
    private Date birthDate;
    private Date startingWork;
    private String department;
    private String description;
    private final long MAIN_SALARY = 10000;
    private final long ADDITIONAL_SALARY= 500;

    @Override
    public long getSalary() {
        Date currentDate = new Date();
        if (birthDate.getMonth() == currentDate.getMonth())
            return getMAIN_SALARY() + getADDITIONAL_SALARY();
        else return getMAIN_SALARY();
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getStartingWork() {
        return startingWork;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public long getMAIN_SALARY() {
        return MAIN_SALARY;
    }

    public long getADDITIONAL_SALARY() {
        return ADDITIONAL_SALARY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setStartingWork(Date startingWork) {
        this.startingWork = startingWork;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
